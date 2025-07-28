package main.java.views;

import main.java.models.MariaDBConnection;

import java.io.File;
import java.net.URL;
import java.sql.*;

import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectedBookmarkView extends JPanel {
    // UI 크기 상수
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 30;
    private static final int IMAGE_SIZE = 80;
    private static final int MAX_ADDRESS_LENGTH = 17;
    private static final int PANEL_HEIGHT = 100;
    private static final int INSET_SIZE = 2;

    // UI 폰트 상수
    private static final Font TITLE_FONT = new Font("맑은 고딕", Font.BOLD, 16);
    private static final Font NORMAL_FONT = new Font("맑은 고딕", Font.PLAIN, 13);

    // 이미지 경로 상수
    private static final String DEFAULT_IMAGE_PATH = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "images" + File.separator + "noImage.png";

    // SQL 쿼리 상수
    private static final String BOOKMARK_QUERY = "SELECT r.restaurant_number, r.restaurant_name, r.address_detail, r.restaurant_img "
            +
            "FROM bookmark b JOIN restaurant r ON b.restaurant_number = r.restaurant_number " +
            "WHERE b.user_number = ?";
    private static final String BOOKMARK_COUNT_QUERY = "SELECT COUNT(*) AS bookmark_count From bookmark b WHERE b.restaurant_number = ?";

    // UI 컴포넌트
    private final JButton selectButton = createSelectButton();
    private JPanel bookMark;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    // 선택된 식당 정보
    private int rNumber;
    private String rName;

    public SelectedBookmarkView() {
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }

        setLayout(new BorderLayout());
        this.bookMark = createBookmarkPanel();

        initializeUI();
        loadBookmarks();
    }

    private void initializeUI() {
        add(new HeaderView(MainFrame.getInstance(), "찜해둔 장소", true), BorderLayout.NORTH);

        JScrollPane bookMarkSP = new JScrollPane(bookMark);
        bookMarkSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        bookMarkSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(bookMarkSP, BorderLayout.CENTER);

        add(selectButton, BorderLayout.SOUTH);
    }

    private JPanel createBookmarkPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        return panel;
    }

    private JButton createSelectButton() {
        JButton button = new JButton("선택 완료");
        button.setFont(TITLE_FONT);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT * 2));
        button.setBackground(new Color(144, 203, 251));
        button.setForeground(Color.WHITE);
        button.addActionListener(e -> handleSelection());
        return button;
    }

    private void handleSelection() {
        if (buttonGroup.getSelection() == null) {
            showWarningMessage("식당을 선택해주세요");
            return;
        }

        String selectedCommand = buttonGroup.getSelection().getActionCommand();
        String[] parts = selectedCommand.split("\\*");
        rNumber = Integer.parseInt(parts[0].trim());
        rName = parts[1].trim();
        openMeetingMakeView(rNumber, rName);
    }

    private void openMeetingMakeView(int rNumber, String rName) {
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }
        this.rNumber = rNumber;
        this.rName = rName;
        MeetingMakeView meetingMakeView = new MeetingMakeView(rNumber, rName);
        MainFrame.getInstance().showPanel("MeetingMakeView", meetingMakeView);
    }

    private void loadBookmarks() {
        bookMark.removeAll();  // 기존 패널의 모든 컴포넌트 삭제
        bookMark.revalidate();
        bookMark.repaint();

        try (Connection conn = MariaDBConnection.getInstance().getConnection()) {
            int userNumber = UserInformation.getInstance().getUserNumber();

            try (PreparedStatement pstmt = conn.prepareStatement(BOOKMARK_QUERY)) {
                pstmt.setInt(1, userNumber);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int restaurantNumber = rs.getInt("restaurant_number");
                        String restaurantName = getValidString(rs.getString("restaurant_name"), "식당 정보 없음");
                        String address = getValidString(rs.getString("address_detail"), "주소 정보 없음");
                        String imageUrl = getValidString(rs.getString("restaurant_img"), DEFAULT_IMAGE_PATH);

                        int bookmarkCount = getBookmarkCount(conn, restaurantNumber);
                        createBookmarkItem(restaurantName, address, imageUrl, bookmarkCount, restaurantNumber);
                    }
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

    private void createBookmarkItem(String restaurantName, String address, String imageUrl, int count,
            int restaurantNumber) {
        JPanel bookmarkData = createBaseItemPanel();
        GridBagConstraints gbc = createGridBagConstraints();

        addImageLabel(bookmarkData, imageUrl, gbc);
        addRestaurantName(bookmarkData, restaurantName, gbc);
        addAddress(bookmarkData, address, gbc);
        addBookmarkCount(bookmarkData, count, gbc);
        addRadioButton(bookmarkData, restaurantNumber, restaurantName, gbc);

        bookmarkData.setMaximumSize(new Dimension(Integer.MAX_VALUE, PANEL_HEIGHT));
        bookMark.add(bookmarkData);

        bookmarkData.revalidate();
        bookmarkData.repaint();
    }

    private JPanel createBaseItemPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return panel;
    }

    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        return gbc;
    }

    private void addImageLabel(JPanel panel, String imageUrl, GridBagConstraints gbc) {
        JLabel imageLabel = new JLabel(loadAndScaleImage(imageUrl));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(imageLabel, gbc);
    }

    private void addRestaurantName(JPanel panel, String name, GridBagConstraints gbc) {
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(TITLE_FONT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0.8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);
    }

    private void addAddress(JPanel panel, String address, GridBagConstraints gbc) {
        if (address.length() > MAX_ADDRESS_LENGTH) {
            address = address.substring(0, MAX_ADDRESS_LENGTH) + "...";
        }
        JLabel addressLabel = new JLabel(address);
        addressLabel.setFont(NORMAL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(addressLabel, gbc);
    }

    private void addBookmarkCount(JPanel panel, int count, GridBagConstraints gbc) {
        JLabel countLabel = new JLabel(count + "명이 찜");
        countLabel.setFont(NORMAL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        panel.add(countLabel, gbc);
    }

    private void addRadioButton(JPanel panel, int restaurantNumber, String restaurantName, GridBagConstraints gbc) {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setActionCommand(restaurantNumber + "*" + restaurantName);
        buttonGroup.add(radioButton);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(radioButton, gbc);
    }

    private ImageIcon loadAndScaleImage(String imagePath) {
        try {
            ImageIcon icon;
            if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
                icon = new ImageIcon(new URL(imagePath));
            } else {
                icon = new ImageIcon(imagePath);
            }
            Image scaledImage = icon.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            ImageIcon defaultIcon = new ImageIcon(DEFAULT_IMAGE_PATH);
            Image scaledImage = defaultIcon.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
    }

    private String getValidString(String value, String defaultValue) {
        return (value == null || value.trim().isEmpty()) ? defaultValue : value;
    }

    private void handleDatabaseError(String message, Exception e) {
        e.printStackTrace();
        showErrorMessage(message);
    }

    private void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "안내", JOptionPane.WARNING_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "오류", JOptionPane.ERROR_MESSAGE);
    }
}
