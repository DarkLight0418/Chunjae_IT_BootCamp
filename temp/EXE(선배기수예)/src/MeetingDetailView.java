package main.java.views;

import main.java.models.MariaDBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URL;

public class MeetingDetailView extends JPanel {
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
    private static final int IMAGE_SIZE = 80;
    private static final int ICON_SIZE = 15;
    private static final int SPACING = 10;
    private static final int PROFILE_SIZE = 40;

    // UI 색상 상수
    private static final Color MAIN_COLOR = new Color(144, 203, 251);
    private static final Color INACTIVE_COLOR = Color.GRAY;

    // UI 폰트 상수
    private static final Font TITLE_FONT = new Font("맑은 고딕", Font.BOLD, 15);
    private static final Font SUBTITLE_FONT = new Font("맑은 고딕", Font.BOLD, 13);
    private static final Font BUTTON_FONT = new Font("맑은 고딕", Font.BOLD, 15);

    // SQL 쿼리 상수
    private static final String PARTICIPANTS_QUERY = "SELECT u.user_number, u.nickname, u.sex, u.profile_img " +
            "FROM participant p JOIN user u ON p.user_number = u.user_number " +
            "WHERE p.meeting_number = ?";

    private static final String MEETING_DETAILS_QUERY = "SELECT m.recruit_limit, m.title, m.content, m.meeting_date, " +
            "u.user_number, u.nickname, u.profile_img, u.sex, " +
            "r.restaurant_number, r.restaurant_name, r.address_detail, r.restaurant_img, r.map_link " +
            "FROM meeting m " +
            "JOIN user u ON m.user_number = u.user_number " +
            "JOIN restaurant r ON m.restaurant_number = r.restaurant_number " +
            "WHERE m.meeting_number = ?";

    private static final String JOIN_MEETING_QUERY = "INSERT INTO participant (meeting_number, user_number) VALUES (?, ?)";
    private static final String LEAVE_MEETING_QUERY = "DELETE FROM participant WHERE meeting_number = ? AND user_number = ?";
    private static final String DELETE_MEETING_QUERY = "DELETE FROM meeting WHERE meeting_number = ? AND user_number = ?";
    private static final String DELETE_PARTICIPANTS_QUERY = "DELETE FROM participant WHERE meeting_number = ?";

    // UI 컴포넌트
    private JScrollPane meetingDetailJs;
    private JPanel center;
    private GridBagConstraints gbc;
    private JPanel participantsPanel;
    private JButton buttonJoin = new JButton("모임 참여 신청");

    // 상태 변수
    private int meetingNumber;
    private int restaurantNumber;
    private int maleCount = 0;
    private int femaleCount = 0;
    private boolean joined = false;
    private boolean full = false;
    private boolean timeOver = false;
    private final int userNumber = UserInformation.getInstance().getUserNumber();
    private int leaderNumber;
    private boolean isLeader = false;

    public MeetingDetailView(int meetingNumber) {
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }
        setLayout(new BorderLayout());
        this.meetingNumber = meetingNumber;

        // 버튼 기본 설정
        buttonJoin.setFont(BUTTON_FONT);
        buttonJoin.setPreferredSize(new Dimension(WINDOW_WIDTH, BUTTON_HEIGHT));
        buttonJoin.setBackground(MAIN_COLOR);
        add(buttonJoin, BorderLayout.SOUTH);

        // 버튼 리스너는 한 번만 등록
        setupButtonListener();

        getMeeting(meetingNumber);
    }

    private void setupButtonListener() {
        buttonJoin.addActionListener(e -> {
            if (!SessionManagerView.getInstance().isLoggedIn()) {
                SessionManagerView.getInstance().checkLoginAndRedirect();
                return;
            }

            if (isLeader && !timeOver) {
                if (maleCount + femaleCount > 1) {
                    JOptionPane.showMessageDialog(this, "다른 참여자가 있어 삭제할 수 없습니다.");
                    return;
                }
                int result = JOptionPane.showConfirmDialog(
                        this,
                        "모임을 삭제하시겠습니까?",
                        "모임 삭제",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    deleteMeeting();
                }
            } else if (!joined && !full && !timeOver) {
                JoinMeeting();
            } else if (joined && !timeOver) {
                int result = JOptionPane.showConfirmDialog(
                        this,
                        "신청을 취소할까요?",
                        "참여 취소",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    leaveMeeting();
                }
            }
        });
    }

    void getMeeting(int meetingNumber) {
        try (Connection conn = MariaDBConnection.getInstance().getConnection()) {
            // joined 상태 초기화
            joined = false;
            maleCount = 0;
            femaleCount = 0;

            // participantsPanel 초기화
            participantsPanel = new JPanel();
            participantsPanel.setLayout(new BoxLayout(participantsPanel, BoxLayout.Y_AXIS));

            // 참여자 정보 로드
            try (PreparedStatement pstmt = conn.prepareStatement(PARTICIPANTS_QUERY)) {
                pstmt.setInt(1, meetingNumber);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int participantNumber = rs.getInt("user_number");
                        String sex = rs.getString("sex");

                        if (sex.equals("M")) maleCount++;
                        else if (sex.equals("F")) femaleCount++;

                        if (participantNumber == userNumber) {
                            joined = true;
                        }

                        participantsPanel(rs.getString("nickname"), rs.getString("profile_img"));
                    }
                }
            }

            loadMeetingDetails(conn);
            init();
        } catch (SQLException | ClassNotFoundException e) {
            handleError("모임 정보를 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    private void loadMeetingDetails(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(MEETING_DETAILS_QUERY)) {
            pstmt.setInt(1, meetingNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    processMeetingDetails(rs);
                }
            }
        }
    }

    private void processMeetingDetails(ResultSet rs) throws SQLException {
        int recruitLimit = rs.getInt("recruit_limit");
        String title = rs.getString("title");
        String content = rs.getString("content");
        Date meetingDate = rs.getDate("meeting_date");
        String nickname = rs.getString("nickname");
        String profileImg = getValidString(rs.getString("profile_img"), DEFAULT_PROFILEIMAGE_PATH);
        String sex = rs.getString("sex");
        restaurantNumber = rs.getInt("restaurant_number");
        String restaurantName = rs.getString("restaurant_name");
        String addressDetail = rs.getString("address_detail");
        String restaurantImg = getValidString(rs.getString("restaurant_img"), DEFAULT_IMAGE_PATH);
        String mapLink = getValidString(rs.getString("map_link"), "지도 링크 없음");
        leaderNumber = rs.getInt("user_number");
        isLeader = (leaderNumber == userNumber);

        updateMeetingStatus(recruitLimit, meetingDate);
        meetingDetailPanel(recruitLimit, title, content, meetingDate, nickname, profileImg, sex,
                restaurantName, addressDetail, restaurantImg, mapLink);
    }

    private void updateMeetingStatus(int recruitLimit, Date meetingDate) {
        full = recruitLimit == (maleCount + femaleCount);
        timeOver = meetingDate.before(new java.util.Date());
    }

    void init() {
        // Header 패널 추가
        add(new HeaderView(MainFrame.getInstance(), "모임 상세", true), BorderLayout.NORTH);

        gbc.gridy = 11;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        center.add(participantsPanel, gbc);

        // 마지막 행에 가중치 추가로 나머지 공간 채우기
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.weighty = 1.0;
        center.add(Box.createVerticalGlue(), gbc);

        updateJoinButton();
    }

    private void updateJoinButton() {
        if (isLeader) {
            setJoinButtonState("모임 삭제", true, Color.BLACK);
        } else if (joined) {
            setJoinButtonState("참여 신청 완료", true, Color.BLACK);
        } else if (timeOver) {
            setJoinButtonState("끝난 모임", false, INACTIVE_COLOR);
        } else if (full) {
            setJoinButtonState("인원 마감", false, INACTIVE_COLOR);
        } else {
            setJoinButtonState("모임 참여 신청", true, Color.BLACK);
        }
    }

    private void setJoinButtonState(String text, boolean enabled, Color color) {
        buttonJoin.setText(text);
        buttonJoin.setEnabled(enabled);
        buttonJoin.setBackground(MAIN_COLOR);
        buttonJoin.setForeground(color);
    }

    void JoinMeeting() {
        try (Connection conn = MariaDBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(JOIN_MEETING_QUERY)) {

            pstmt.setInt(1, meetingNumber);
            pstmt.setInt(2, userNumber);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                refresh();  // 전체 UI 업데이트로 변경
            } else {
                handleError("참가자 추가에 실패했습니다.", null);
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleError("모임 참여 중 오류가 발생했습니다.", e);
        }
    }

    private void leaveMeeting() {
        try (Connection conn = MariaDBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(LEAVE_MEETING_QUERY)) {

            pstmt.setInt(1, meetingNumber);
            pstmt.setInt(2, userNumber);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                refresh();
            } else {
                handleError("참여 취소에 실패했습니다.", null);
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleError("참여 취소 중 오류가 발생했습니다.", e);
        }
    }

    private void deleteMeeting() {
        try (Connection conn = MariaDBConnection.getInstance().getConnection()) {
            // 1. 먼저 participant 테이블에서 해당 모임의 참여자들을 삭제
            try (PreparedStatement pstmt = conn.prepareStatement(DELETE_PARTICIPANTS_QUERY)) {
                pstmt.setInt(1, meetingNumber);
                pstmt.executeUpdate();
            }

            // 2. 그 다음 meeting 테이블에서 모임을 삭제
            try (PreparedStatement pstmt = conn.prepareStatement(DELETE_MEETING_QUERY)) {
                pstmt.setInt(1, meetingNumber);
                pstmt.setInt(2, userNumber);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    //JOptionPane.showMessageDialog(this, "모임이 삭제되었습니다.");
                    MainFrame.getInstance().showPanel("MainPage", new MainPageView());
                } else {
                    handleError("모임 삭제에 실패했습니다.", null);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleError("모임 삭제 중 오류가 발생했습니다.", e);
        }
    }

    void meetingDetailPanel(int recruitLimit, String title, String content, Date meetingDate,
                            String nickname, String profileImg, String sex,
                            String restaurantName, String addressDetail, String restaurantImg, String mapLink) {

        initializeCenterPanel();
        addImageAndTitle(restaurantImg, title);
        addLocationInfo(restaurantName, addressDetail, mapLink);
        addDateTimeInfo(meetingDate);
        addRecruitmentInfo(recruitLimit);
        addParticipantInfo();
        addLeaderInfo(nickname, profileImg);
        addContentArea(content);
        addParticipantsSection();
    }

    private void initializeCenterPanel() {
        center = new JPanel();
        center.setLayout(new GridBagLayout());
        center.setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        meetingDetailJs = new JScrollPane(center, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(meetingDetailJs, BorderLayout.CENTER);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
    }

    private void addImageAndTitle(String restaurantImg, String title) {
        JPanel imageTitle = new JPanel();
        imageTitle.setLayout(new BoxLayout(imageTitle, BoxLayout.X_AXIS));
        imageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon icon = loadImage(restaurantImg, IMAGE_SIZE);
        imageTitle.add(new JLabel(icon));
        imageTitle.add(Box.createHorizontalStrut(SPACING));

        JLabel titleLabel = new JLabel("<html>" + title + "</html>");
        titleLabel.setFont(TITLE_FONT);
        imageTitle.add(titleLabel);

        addToCenter(imageTitle, 0);
    }

    private void addLocationInfo(String restaurantName, String addressDetail, String mapLink) {
        JPanel locationMap = createLocationPanel(restaurantName, addressDetail);
        JLabel showMap = createMapLink(mapLink);
        locationMap.add(Box.createHorizontalStrut(SPACING));
        locationMap.add(showMap);

        addToCenter(locationMap, 1);
    }

    private JPanel createLocationPanel(String restaurantName, String addressDetail) {
        JPanel locationMap = new JPanel();
        locationMap.setLayout(new BoxLayout(locationMap, BoxLayout.X_AXIS));
        locationMap.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon mapIcon = loadIcon("/images/map.png", ICON_SIZE);
        locationMap.add(new JLabel(mapIcon));
        locationMap.add(Box.createHorizontalStrut(SPACING));

        JPanel location = new JPanel(new GridLayout(2, 1));
        JLabel nameLabel = createRestaurantNameLabel(restaurantName);
        location.add(nameLabel);
        location.add(new JLabel("<html>" + addressDetail + "</html>"));
        locationMap.add(location);

        return locationMap;
    }

    private JLabel createRestaurantNameLabel(String restaurantName) {
        JLabel label = new JLabel("<html>" + restaurantName + "</html>");
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openRestaurantView(restaurantNumber);
            }
        });
        return label;
    }

    private JLabel createMapLink(String mapLink) {
        JLabel label = new JLabel("지도 보기>");
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openMap(mapLink);
            }
        });
        return label;
    }

    private void addDateTimeInfo(Date meetingDate) {
        JPanel calendar = new JPanel();
        calendar.setLayout(new BoxLayout(calendar, BoxLayout.X_AXIS));
        calendar.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon calendarIcon = loadIcon("/images/calendar.png", ICON_SIZE);
        calendar.add(new JLabel(calendarIcon));
        calendar.add(Box.createHorizontalStrut(SPACING));

        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd    HH시 mm분");
        calendar.add(new JLabel(formatter.format(meetingDate)));

        addToCenter(calendar, 2);
    }

    private void addRecruitmentInfo(int recruitLimit) {
        JPanel recruitmentCount = new JPanel();
        recruitmentCount.setLayout(new BoxLayout(recruitmentCount, BoxLayout.X_AXIS));
        recruitmentCount.setAlignmentX(Component.LEFT_ALIGNMENT);

        recruitmentCount.add(new JLabel("모집 인원"));
        recruitmentCount.add(Box.createHorizontalStrut(20));
        recruitmentCount.add(new JLabel(recruitLimit + "명"));

        addToCenter(recruitmentCount, 3);
    }

    private void addParticipantInfo() {
        JPanel participantCount = new JPanel();
        participantCount.setLayout(new BoxLayout(participantCount, BoxLayout.X_AXIS));
        participantCount.setAlignmentX(Component.LEFT_ALIGNMENT);

        participantCount.add(new JLabel("참여 인원"));
        participantCount.add(Box.createHorizontalStrut(20));
        participantCount.add(new JLabel((maleCount + femaleCount) + "명"));
        participantCount.add(Box.createHorizontalStrut(20));
        participantCount.add(new JLabel("남자 " + maleCount + " / 여자 " + femaleCount + "명"));

        addToCenter(participantCount, 4);
    }

    private void addLeaderInfo(String nickname, String profileImg) {
        addToCenter(Box.createVerticalStrut(20), 5);

        JLabel leaderLabel = new JLabel("모임장");
        leaderLabel.setFont(SUBTITLE_FONT);
        addToCenter(leaderLabel, 6);

        JPanel leader = new JPanel();
        leader.setLayout(new BoxLayout(leader, BoxLayout.X_AXIS));
        leader.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 프로필 이미지 처리
        ImageIcon profileIcon;
        if (profileImg == null || profileImg.equals("default.jpg")) {
            // 기본 이미지 사용
            profileIcon = new ImageIcon(getClass().getResource("/images/default.jpg"));
        } else {
            // 사용자가 업로드한 이미지 사용
            profileIcon = new ImageIcon(profileImg);
        }

        // 이미지 크기 조정
        Image scaledImage = profileIcon.getImage().getScaledInstance(PROFILE_SIZE, PROFILE_SIZE, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(scaledImage);

        leader.add(new JLabel(profileIcon));
        leader.add(Box.createHorizontalStrut(SPACING));
        leader.add(new JLabel(nickname));

        addToCenter(leader, 7);
    }

    private void addContentArea(String content) {
        JTextArea textArea = new JTextArea(content);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        addToCenter(textArea, 8);
        addToCenter(Box.createVerticalStrut(20), 9);
    }

    private void addParticipantsSection() {
        JLabel participantsLabel = new JLabel("참여자");
        participantsLabel.setFont(SUBTITLE_FONT);
        addToCenter(participantsLabel, 10);
    }

    private void addToCenter(Component comp, int gridy) {
        gbc.gridy = gridy;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        center.add(comp, gbc);
    }

    void openRestaurantView(int restaurantNumber) {
        RestaurantDetailView restaurantDetail = new RestaurantDetailView();
        restaurantDetail.getRestaurant(restaurantNumber);
        MainFrame.getInstance().showPanel("RestaurantDetail", restaurantDetail);
    }

    private void openMap(String mapLink) {
        try {
            if (mapLink.equals("지도 링크 없음")) {
                JOptionPane.showMessageDialog(null, "지도 링크 없음", "알림", JOptionPane.INFORMATION_MESSAGE);
            } else {
                new MapView(mapLink).setVisible(true);
            }
        } catch (Exception ex) {
            handleError("지도를 열 수 없습니다.", ex);
        }
    }

    void participantsPanel(String nickname, String profileImg) {
        JPanel participant = new JPanel();
        participant.setLayout(new BoxLayout(participant, BoxLayout.X_AXIS));
        participant.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 프로필 이미지 처리
        ImageIcon profileIcon;
        if (profileImg == null || profileImg.equals("default.jpg")) {
            // 기본 이미지 사용
            profileIcon = new ImageIcon(getClass().getResource("/images/default.jpg"));
        } else {
            // 사용자가 업로드한 이미지 사용
            profileIcon = new ImageIcon(profileImg);
        }

        // 이미지 크기 조정
        Image scaledImage = profileIcon.getImage().getScaledInstance(PROFILE_SIZE, PROFILE_SIZE, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(scaledImage);

        participant.add(new JLabel(profileIcon));
        participant.add(Box.createHorizontalStrut(SPACING));
        participant.add(new JLabel(nickname));

        participantsPanel.add(participant);
        participantsPanel.add(Box.createRigidArea(new Dimension(0, SPACING)));
    }

    private ImageIcon loadImage(String path, int size) {
        try {
            ImageIcon icon;
            if (path.startsWith("http://") || path.startsWith("https://")) {
                icon = new ImageIcon(new URL(path));
            } else {
                icon = new ImageIcon(path);
            }
            Image scaledImage = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            return new ImageIcon(DEFAULT_IMAGE_PATH);
        }
    }

    private ImageIcon loadIcon(String resourcePath, int size) {
        ImageIcon icon = new ImageIcon(getClass().getResource(resourcePath));
        Image scaledImage = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
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

    void refresh() {
        center.removeAll();
        maleCount = 0;
        femaleCount = 0;
        getMeeting(meetingNumber);
        center.revalidate();
        center.repaint();
    }
}