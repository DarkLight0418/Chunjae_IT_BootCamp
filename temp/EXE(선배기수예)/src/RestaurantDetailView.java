package main.java.views;

import main.java.models.MariaDBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.sql.*;

public class RestaurantDetailView extends JPanel {
    // 이미지 경로 상수
    private static final String DEFAULT_IMAGE_PATH = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "images" + File.separator + "noImage.png";

    private static final String DEFAULT_PROFILEIMAGE_PATH = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "images" + File.separator + "my1.png";

    // UI 크기 상수
    private static final int WINDOW_WIDTH = 330;
    private static final int WINDOW_HEIGHT = 800;
    private static final int BUTTON_HEIGHT = 50;
    private static final int IMAGE_SIZE = 320;
    private static final int ICON_SIZE = 15;
    private static final int SPACING = 5;
    private static final int VERTICAL_SPACING = 20;

    // UI 색상 상수
    private static final Color MAIN_COLOR = new Color(144, 203, 251);
    private static final Color TEXT_COLOR = Color.WHITE;

    // UI 폰트 상수
    private static final Font TITLE_FONT = new Font("맑은 고딕", Font.BOLD, 18);
    private static final Font SUBTITLE_FONT = new Font("맑은 고딕", Font.BOLD, 13);
    private static final Font BUTTON_FONT = new Font("맑은 고딕", Font.BOLD, 15);

    // SQL 쿼리 상수
    private static final String RESTAURANT_QUERY = "SELECT restaurant_name, address_detail, restaurant_img, restaurant_contact, map_link "
            +
            "FROM restaurant WHERE restaurant_number = ?";
    private static final String BOOKMARKERS_QUERY = "SELECT u.profile_img, u.nickname, u.user_number FROM bookmark b " +
            "JOIN user u ON b.user_number = u.user_number " +
            "WHERE b.restaurant_number = ?";
    private static final String MEETINGS_QUERY = "SELECT meeting_number, title FROM meeting WHERE restaurant_number = ?";
    private static final String BOOKMARK_INSERT_QUERY = "INSERT INTO bookmark (user_number, restaurant_number) VALUES (?, ?)";
    private static final String BOOKMARK_DELETE_QUERY = "DELETE FROM bookmark WHERE user_number = ? AND restaurant_number = ?";

    // UI 컴포넌트
    private JScrollPane RestaurantDetailJs;
    private JPanel center;
    private GridBagConstraints gbc;
    private JLabel restaurantName;
    private JPanel location;
    private JButton showMap;
    private JButton bookmark = new JButton("찜하기");
    private JPanel restaurantPanel;
    private JPanel bookmarkerPanel;
    private JPanel meetingsPanel;

    // 상태 변수
    private int restaurantNumber;
    private String rName;
    private int bookmarkerCount = 0;
    private int meetingCount = 0;
    private boolean bookmarked = false;

    public RestaurantDetailView() {
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }

        setLayout(new BorderLayout());
        initializePanels();
    }

    private void initializePanels() {
        // ** Header 패널 추가 **
        JPanel RestaurantDetailHeader = new HeaderView(MainFrame.getInstance(), "맛집 상세", true);
        add(RestaurantDetailHeader, BorderLayout.NORTH);

        // ** Main Content Area **
        center = new JPanel();
        center.setLayout(new GridBagLayout());
        center.setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        RestaurantDetailJs = new JScrollPane(center, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(RestaurantDetailJs, BorderLayout.CENTER);

        // Initialize panels with layouts
        restaurantPanel = new JPanel();
        restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.Y_AXIS));

        bookmarkerPanel = new JPanel();
        bookmarkerPanel.setLayout(new BoxLayout(bookmarkerPanel, BoxLayout.Y_AXIS));

        meetingsPanel = new JPanel();
        meetingsPanel.setLayout(new BoxLayout(meetingsPanel, BoxLayout.Y_AXIS));

        setupMainLayout();
    }

    private void setupMainLayout() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        // Restaurant Panel
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        center.add(restaurantPanel, gbc);

        // Spacing
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        center.add(Box.createVerticalStrut(20), gbc);

        // Bookmarkers Section
        Font fontSubTitle = new Font("맑은 고딕", Font.BOLD, 13);
        JLabel bookmarkers = new JLabel("이 장소를 찜한 사람들 (" + bookmarkerCount + "명)");
        bookmarkers.setFont(fontSubTitle);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        center.add(bookmarkers, gbc);

        gbc.gridy = 4;
        center.add(bookmarkerPanel, gbc);

        // Spacing
        gbc.gridy = 5;
        center.add(Box.createVerticalStrut(20), gbc);

        // Meetings Section
        JLabel meetings = new JLabel("이 장소의 모임 (" + meetingCount + "개)");
        meetings.setFont(fontSubTitle);

        gbc.gridy = 6;
        gbc.gridx = 0;
        center.add(meetings, gbc);

        gbc.gridy = 7;
        center.add(meetingsPanel, gbc);

        // Meeting Creation Button
        JButton makeMeeting = new JButton("모임 만들기");
        makeMeeting.setForeground(Color.WHITE);
        makeMeeting.setBackground(new Color(144, 203, 251));
        gbc.gridy = 8;
        center.add(makeMeeting, gbc);

        makeMeeting.addActionListener(e -> {
            openMeetingMakeView(restaurantNumber, rName);
        });

        // Bookmark Button
        bookmark.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        bookmark.setPreferredSize(new Dimension(360, 50));
        bookmark.setBackground(new Color(144, 203, 251));
        add(bookmark, BorderLayout.SOUTH);

        bookmark.addActionListener(e -> {
            if (!SessionManagerView.getInstance().isLoggedIn()) {
                SessionManagerView.getInstance().checkLoginAndRedirect();
                return;
            }

            if (!bookmarked) {
                bookmarkInsert();
            } else {
                int result = JOptionPane.showConfirmDialog(
                        this,
                        "북마크를 취소하시겠습니까?",
                        "북마크 취소",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    bookmarkDelete();
                }
            }
        });

        // Fill remaining space
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        center.add(Box.createVerticalGlue(), gbc);
    }

    void openMeetingMakeView(int restaurantNumber, String rName) {
        MeetingMakeView meetingMakeView = new MeetingMakeView(restaurantNumber, rName);
        MainFrame.getInstance().showPanel("meetingMakeView", meetingMakeView);
    }

    public void getRestaurant(int restaurantNumber) {
        this.restaurantNumber = restaurantNumber;
        bookmarkerCount = 0;
        meetingCount = 0;

        MainFrame.getInstance().setCurrentRestaurantNumber(restaurantNumber);

        try (Connection conn = MariaDBConnection.getInstance().getConnection()) {
            loadRestaurantInfo(conn);
            loadBookmarkers(conn);
            loadMeetings(conn);
            updateBookmarkButton();
            updateCounts();
        } catch (SQLException | ClassNotFoundException e) {
            handleError("데이터를 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    private void loadRestaurantInfo(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(RESTAURANT_QUERY)) {
            pstmt.setInt(1, restaurantNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    rName = getValidString(rs.getString("restaurant_name"), "식당 정보 없음");
                    String rAddress = getValidString(rs.getString("address_detail"), "주소 정보 없음");
                    String rImageUrl = getValidString(rs.getString("restaurant_img"), DEFAULT_IMAGE_PATH);
                    String rContact = rs.getString("restaurant_contact");
                    String rMapUrl = getValidString(rs.getString("map_link"), "지도 링크 없음");

                    restaurantPanel(rName, rAddress, rImageUrl, rContact, rMapUrl);
                }
            }
        }
    }

    private void loadBookmarkers(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(BOOKMARKERS_QUERY)) {
            pstmt.setInt(1, restaurantNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String profileImg = rs.getString("profile_img");
                    String nickname = rs.getString("nickname");
                    int uNumber = rs.getInt("user_number");

                    bookmarkerPanel(profileImg, nickname);
                    bookmarkerCount++;

                    checkUserBookmark(uNumber);
                }

                if (bookmarkerCount == 0) {
                    bookmarkerPanel.add(new JLabel("   찜하기를 눌러보세요!")).setForeground(Color.GRAY);
                }
            }
        }
    }

    private void loadMeetings(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(MEETINGS_QUERY)) {
            pstmt.setInt(1, restaurantNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int meetingNumber = rs.getInt("meeting_number");
                    String meetingTitle = rs.getString("title");

                    meetingsPanel(meetingNumber, meetingTitle);
                    meetingCount++;
                }

                if (meetingCount == 0) {
                    meetingsPanel.add(new JLabel("   모임 만들기를 눌러보세요!")).setForeground(Color.GRAY);
                }
            }
        }
    }

    private String getValidString(String value, String defaultValue) {
        return (value == null || value.trim().isEmpty()) ? defaultValue : value;
    }

    private void handleError(String message, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, message, "오류", JOptionPane.ERROR_MESSAGE);
    }

    private void updateBookmarkButton() {
        if (bookmarked) {
            bookmark.setText("찜 취소");
        } else {
            bookmark.setText("찜하기");
        }
    }

    private void updateCounts() {
        for (Component comp : center.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText().startsWith("이 장소를 찜한 사람들")) {
                    label.setText("이 장소를 찜한 사람들 (" + bookmarkerCount + "명)");
                } else if (label.getText().startsWith("이 장소의 모임")) {
                    label.setText("이 장소의 모임 (" + meetingCount + "개)");
                }
            }
        }
    }

    void restaurantPanel(String rName, String rAddress, String finalImagePath, String rContact, String rMapUrl) {
        restaurantPanel.removeAll();

        // Restaurant image 처리
        ImageIcon iconRestaurant = null;
        try {
            if (finalImagePath.startsWith("http://") || finalImagePath.startsWith("https://")) {
                URL imageUrl = new URL(finalImagePath);
                iconRestaurant = new ImageIcon(imageUrl);
            } else {
                iconRestaurant = new ImageIcon(finalImagePath);
            }

            Image scaledImageRestaurant = iconRestaurant.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE,
                    Image.SCALE_SMOOTH);
            iconRestaurant = new ImageIcon(scaledImageRestaurant);
        } catch (Exception e) {
            iconRestaurant = new ImageIcon(DEFAULT_IMAGE_PATH);
        }

        JLabel imageLabel = new JLabel(iconRestaurant);
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        restaurantPanel.add(imageLabel);

        restaurantPanel.add(Box.createVerticalStrut(5));

        // Restaurant name
        Font fontTitle = new Font("맑은 고딕", Font.BOLD, 18);
        restaurantName = new JLabel("<html>" + rName + "</html>");
        restaurantName.setFont(fontTitle);
        restaurantName.setAlignmentX(Component.LEFT_ALIGNMENT);
        restaurantPanel.add(restaurantName);

        restaurantPanel.add(Box.createVerticalStrut(10));

        // Location info
        location = new JPanel();
        location.setLayout(new BoxLayout(location, BoxLayout.X_AXIS));
        location.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon iconMap = new ImageIcon(getClass().getResource("/images/map.png"));
        Image scaledImageMap = iconMap.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        iconMap = new ImageIcon(scaledImageMap);

        location.add(new JLabel(iconMap));
        location.add(Box.createHorizontalStrut(SPACING * 2));
        location.add(new JLabel(rAddress));
        restaurantPanel.add(location);

        restaurantPanel.add(Box.createVerticalStrut(5));

        // Contact info
        JPanel contact = new JPanel();
        contact.setLayout(new BoxLayout(contact, BoxLayout.X_AXIS));
        contact.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon iconCall = new ImageIcon(getClass().getResource("/images/call.png"));
        Image scaledImageCall = iconCall.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        iconCall = new ImageIcon(scaledImageCall);

        contact.add(new JLabel(iconCall));
        contact.add(Box.createHorizontalStrut(SPACING * 2));
        contact.add(new JLabel(rContact));
        restaurantPanel.add(contact);

        // Map button
        showMap = new JButton("지도 보기");
        showMap.setForeground(TEXT_COLOR);
        showMap.setBackground(MAIN_COLOR);
        showMap.setAlignmentX(Component.LEFT_ALIGNMENT);
        showMap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openMap(rMapUrl);
            }
        });

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        center.add(showMap, gbc);
    }

    private void openMap(String rMapUrl) {
        try {
            if (rMapUrl.equals("지도 링크 없음")) {
                JOptionPane.showMessageDialog(null, "지도 링크 없음", "알림", JOptionPane.INFORMATION_MESSAGE);
            } else {
                new MapView(rMapUrl).setVisible(true);
            }
        } catch (Exception ex) {
            handleError("지도를 열 수 없습니다.", ex);
        }
    }

    void bookmarkerPanel(String profileImg, String nickname) {
        JPanel bookmarkerHere = new JPanel();
        bookmarkerHere.setLayout(new BoxLayout(bookmarkerHere, BoxLayout.X_AXIS));
        bookmarkerHere.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 프로필 이미지 처리
        ImageIcon iconPic;
        if (profileImg == null || profileImg.equals("default.jpg")) {
            // 기본 이미지 사용
            iconPic = new ImageIcon(getClass().getResource("/images/default.jpg"));
        } else {
            // 사용자가 업로드한 이미지 사용
            iconPic = new ImageIcon(profileImg);
        }

        // 이미지 크기 조정
        Image scaledImage = iconPic.getImage().getScaledInstance(ICON_SIZE * 2, ICON_SIZE * 2, Image.SCALE_SMOOTH);
        iconPic = new ImageIcon(scaledImage);

        bookmarkerHere.add(new JLabel(iconPic));
        bookmarkerHere.add(Box.createHorizontalStrut(SPACING * 2));
        bookmarkerHere.add(new JLabel(nickname));

        bookmarkerPanel.add(bookmarkerHere);
        bookmarkerPanel.add(Box.createRigidArea(new Dimension(0, SPACING * 2)));
    }

    void meetingsPanel(int meetingNumber, String meetingTitle) {
        JPanel meetingsHere = new JPanel();
        meetingsHere.setLayout(new BoxLayout(meetingsHere, BoxLayout.X_AXIS));
        meetingsHere.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon iconMeeting = new ImageIcon(getClass().getResource("/images/meet1.png"));
        Image scaledImageMeeting = iconMeeting.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        iconMeeting = new ImageIcon(scaledImageMeeting);

        meetingsHere.add(new JLabel(iconMeeting));
        meetingsHere.add(Box.createHorizontalStrut(SPACING * 2));
        meetingsHere.add(new JLabel("<html>" + meetingTitle + "</html>"));

        meetingsHere.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openMeetingView(meetingNumber);
            }
        });

        meetingsPanel.add(meetingsHere);
        meetingsPanel.add(Box.createRigidArea(new Dimension(0, SPACING * 2)));
    }

    private void openMeetingView(int meetingNumber) {
        MeetingDetailView meetingDetail = new MeetingDetailView(meetingNumber);
        meetingDetail.getMeeting(meetingNumber);
        MainFrame.getInstance().showPanel("MeetingDetail", meetingDetail);
    }

    void bookmarkInsert() {
        try (Connection conn = MariaDBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(BOOKMARK_INSERT_QUERY)) {

            pstmt.setInt(1, UserInformation.getInstance().getUserNumber());
            pstmt.setInt(2, restaurantNumber);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                refresh();
            } else {
                handleError("북마크 추가에 실패했습니다.", null);
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleError("북마크 추가 중 오류가 발생했습니다.", e);
        }
    }

    void bookmarkDelete() {
        try (Connection conn = MariaDBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(BOOKMARK_DELETE_QUERY)) {

            pstmt.setInt(1, UserInformation.getInstance().getUserNumber());
            pstmt.setInt(2, restaurantNumber);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                bookmarked = false;
                refresh();
            } else {
                handleError("찜하기 취소에 실패했습니다.", null);
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleError("찜하기 취소 중 오류가 발생했습니다.", e);
        }
    }

    void refresh() {
        // 기존 패널들 초기화
        if (restaurantPanel != null)
            restaurantPanel.removeAll();
        if (bookmarkerPanel != null)
            bookmarkerPanel.removeAll();
        if (meetingsPanel != null)
            meetingsPanel.removeAll();

        // 카운트 초기화
        bookmarkerCount = 0;
        meetingCount = 0;

        // 식당 정보 다시 불러오기
        getRestaurant(restaurantNumber);

        // UI 업데이트
        center.revalidate();
        center.repaint();
    }

    public void clearPanels() {
        // 각 패널의 내용을 초기화
        if (restaurantPanel != null)
            restaurantPanel.removeAll();
        if (bookmarkerPanel != null)
            bookmarkerPanel.removeAll();
        if (meetingsPanel != null)
            meetingsPanel.removeAll();

        // 카운트 초기화
        bookmarkerCount = 0;
        meetingCount = 0;

        // UI 업데이트
        revalidate();
        repaint();
    }

    private void checkUserBookmark(int uNumber) {
        if (SessionManagerView.getInstance().isLoggedIn()) {
            UserInformation userInfo = UserInformation.getInstance();
            if (uNumber == userInfo.getUserNumber()) {
                bookmarked = true;
            }
        }
    }
}