package main.java.views;

import main.java.models.MariaDBConnection;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookmarkView extends JPanel {
    // 파일 경로 상수
    private static final String DEFAULT_IMAGE_PATH = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "images" + File.separator + "noImage.png";

    // UI 크기 상수
    private static final int PANEL_HEIGHT = 100;
    private static final int IMAGE_SIZE = 80;
    private static final int ICON_SIZE = 30;
    private static final int TITLE_FONT_SIZE = 16;
    private static final int NORMAL_FONT_SIZE = 13;
    private static final int MAX_ADDRESS_LENGTH = 18;

    // UI 색상 상수
    private static final Color BORDER_COLOR = Color.GRAY;

    // UI 폰트 상수
    private static final Font TITLE_FONT = new Font("맑은 고딕", Font.BOLD, TITLE_FONT_SIZE);
    private static final Font NORMAL_FONT = new Font("맑은 고딕", Font.PLAIN, NORMAL_FONT_SIZE);
    private static final Font COUNT_FONT = new Font("맑은 고딕", Font.BOLD, NORMAL_FONT_SIZE);

    // SQL 쿼리 상수
    private static final String BOOKMARK_QUERY = "SELECT r.restaurant_number, r.restaurant_name, r.address_detail, r.restaurant_img "
            +
            "FROM bookmark b JOIN restaurant r ON b.restaurant_number = r.restaurant_number " +
            "WHERE b.user_number = ?";

    private static final String BOOKMARK_COUNT_QUERY = "SELECT COUNT(*) AS bookmark_count From bookmark b WHERE b.restaurant_number = ?";

    private static final String DELETE_BOOKMARK_QUERY = "DELETE FROM bookmark WHERE restaurant_number = ? and user_number = ?";

    // 컴포넌트 필드
    private final JPanel bookMark;
    private final JButton bookmarkButton;
    private final int userNumber;

    public BookmarkView() {
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            throw new IllegalStateException("User must be logged in");
        }

        this.userNumber = UserInformation.getInstance().getUserNumber();
        this.bookMark = createMainPanel();
        this.bookmarkButton = createBookmarkButton();

        setLayout(new BorderLayout());
        initializeUI();
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        return panel;
    }

    private void initializeUI() {
        // Header 추가
        add(new HeaderView(MainFrame.getInstance(), "북마크", true), BorderLayout.NORTH);

        // Footer 추가
        add(Footer.getInstance(), BorderLayout.SOUTH);

        // 북마크 패널 초기화
        initializeBookmarkPanel();

        // 데이터 로드
        loadBookmarks();
    }

    private void initializeBookmarkPanel() {
        bookMark.setLayout(new BoxLayout(bookMark, BoxLayout.Y_AXIS));
        bookMark.setAlignmentY(Component.TOP_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(bookMark);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createBookmarkButton() {
        ImageIcon bookMarkIcon = new ImageIcon(getClass().getResource("/images/star2.png"));
        Image resizedImage = bookMarkIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(resizedImage));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }

    private void loadBookmarks() {
        try (Connection conn = MariaDBConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(BOOKMARK_QUERY)) {

            pstmt.setInt(1, userNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int restaurantNumber = rs.getInt("restaurant_number");
                    String restaurantName = getValidString(rs.getString("restaurant_name"), "식당 정보 없음");
                    String address = getValidString(rs.getString("address_detail"), "주소 정보 없음");
                    String imageUrl = getValidString(rs.getString("restaurant_img"), DEFAULT_IMAGE_PATH);

                    int bookmarkCount = getBookmarkCount(conn, restaurantNumber);
                    createBookmarkPanel(restaurantName, address, imageUrl, bookmarkCount, restaurantNumber);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleDatabaseError("데이터를 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    private int getBookmarkCount(Connection conn, int restaurantNumber) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(BOOKMARK_COUNT_QUERY)) {
            pstmt.setInt(1, restaurantNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt("bookmark_count") : 0;
            }
        }
    }

    private String getValidString(String value, String defaultValue) {
        return (value == null || value.trim().isEmpty()) ? defaultValue : value;
    }

    private void createBookmarkPanel(String restaurantName, String address, String imageUrl, int bookmarkCount,
            int restaurantNumber) {
        JPanel bookmarkData = createBookmarkContainer();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        // 이미지 추가
        addRestaurantImage(bookmarkData, imageUrl, gbc);

        // 식당 정보 추가
        addRestaurantInfo(bookmarkData, restaurantName, address, bookmarkCount, gbc);

        // 북마크 버튼 추가
        addBookmarkButton(bookmarkData, restaurantNumber, gbc);

        // 패널 크기 설정
        bookmarkData.setMaximumSize(new Dimension(Integer.MAX_VALUE, PANEL_HEIGHT));

        // 클릭 이벤트 추가
        addClickListener(bookmarkData, restaurantNumber);

        // 북마크 패널에 추가
        bookMark.add(bookmarkData);
        bookmarkData.revalidate();
        bookmarkData.repaint();
    }

    private JPanel createBookmarkContainer() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        return panel;
    }

    private void addRestaurantImage(JPanel container, String imageUrl, GridBagConstraints gbc) {
        JLabel imageLabel = new JLabel();
        ImageIcon icon = loadImageIcon(imageUrl);
        Image scaledImage = icon.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(imageLabel, gbc);
    }

    private void addRestaurantInfo(JPanel container, String name, String address, int count, GridBagConstraints gbc) {
        // 식당 이름
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(TITLE_FONT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0.8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        container.add(nameLabel, gbc);

        // 주소
        String truncatedAddress = truncateString(address, MAX_ADDRESS_LENGTH);
        JLabel addressLabel = new JLabel(truncatedAddress);
        addressLabel.setFont(NORMAL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 1;
        container.add(addressLabel, gbc);

        // 북마크 수
        JLabel countLabel = new JLabel(count + "명이 찜");
        countLabel.setFont(COUNT_FONT);
        gbc.gridx = 1;
        gbc.gridy = 2;
        container.add(countLabel, gbc);
    }

    private void addBookmarkButton(JPanel container, int restaurantNumber, GridBagConstraints gbc) {
        JButton button = createBookmarkButton();
        button.addActionListener(e -> handleBookmarkButton(restaurantNumber));

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        container.add(button, gbc);
    }

    private void addClickListener(JPanel panel, int restaurantNumber) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openRestaurantDetail(restaurantNumber);
            }
        });
    }

    private ImageIcon loadImageIcon(String imageUrl) {
        try {
            return new ImageIcon(new URL(imageUrl));
        } catch (MalformedURLException e) {
            return new ImageIcon(DEFAULT_IMAGE_PATH);
        }
    }

    private String truncateString(String text, int maxLength) {
        return text.length() > maxLength ? text.substring(0, maxLength) + "..." : text;
    }

    private void openRestaurantDetail(int restaurantNumber) {
        RestaurantDetailView restaurantDetail = new RestaurantDetailView();
        restaurantDetail.getRestaurant(restaurantNumber);
        MainFrame.getInstance().showPanel("RestaurantDetail", restaurantDetail);
    }

    private void handleBookmarkButton(int restaurantNumber) {
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }

        try (Connection conn = MariaDBConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(DELETE_BOOKMARK_QUERY)) {

            pstmt.setInt(1, restaurantNumber);
            pstmt.setInt(2, userNumber);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                showSuccessMessage("북마크가 삭제되었습니다");
                refreshBookmarkPanel();
            } else {
                showErrorMessage("데이터를 삭제하는 중 오류가 발생했습니다.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleDatabaseError("북마크를 삭제하는 중 오류가 발생했습니다.", e);
        }
    }

    private void handleDatabaseError(String message, Exception e) {
        e.printStackTrace();
        showErrorMessage(message);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "오류", JOptionPane.ERROR_MESSAGE);
    }

    private void refreshBookmarkPanel() {
        bookMark.removeAll();
        loadBookmarks();
        bookMark.revalidate();
        bookMark.repaint();
    }
}
