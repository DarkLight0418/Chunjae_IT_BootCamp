package main.java.views;

import main.java.models.MariaDBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class SearchSelectView extends JPanel implements ActionListener {
    // UI 크기 상수
    private static final int WINDOW_WIDTH = 330;
    private static final int WINDOW_HEIGHT = 800;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 30;
    private static final int LOCATION_WIDTH = 115;
    private static final int SEARCH_WIDTH = 150;
    private static final int ITEM_WIDTH = 310;
    private static final int ITEM_HEIGHT = 80;
    private static final int IMAGE_SIZE = 80;
    private static final int ICON_SIZE = 20;
    private static final int SPACING = 5;

    // UI 색상 상수
    private static final Color MAIN_COLOR = new Color(144, 203, 251);
    private static final Color TEXT_COLOR = Color.WHITE;

    // UI 폰트 상수
    private static final Font TITLE_FONT = new Font("맑은 고딕", Font.BOLD, 15);
    private static final Font NORMAL_FONT = new Font("맑은 고딕", Font.BOLD, 14);

    // 이미지 경로 상수
    private static final String DEFAULT_IMAGE_PATH = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "images" + File.separator + "noImage.png";

    // SQL 쿼리 상수
    private static final String LOCATION_QUERY = "SELECT DISTINCT l.district, l.location_id FROM location l";
    private static final String RESTAURANT_SEARCH_QUERY = "SELECT r.restaurant_number, r.restaurant_name, r.address_detail, r.restaurant_img, l.district, "
            +
            "m.major_category, m.minor_category " +
            "FROM restaurant r " +
            "JOIN location l ON r.location_id = l.location_id " +
            "JOIN menu m ON r.menu_number = m.menu_number " +
            "WHERE (? = '전체 위치' OR l.district = ?) " +
            "AND (? = 0 OR r.menu_number = ?) " +
            "AND (? = 0 OR r.location_id = ?) " +
            "AND (r.restaurant_name LIKE ? OR r.address_detail LIKE ? " +
            "OR m.major_category LIKE ? OR m.minor_category LIKE ?)";

    // UI 컴포넌트
    private final JScrollPane searchJs;
    private final JPanel result;
    private final JButton searchButton;
    private final JComboBox<String> locationCombo;
    private final JComboBox<String> searchTypeCombo;
    private final JTextField searchText;
    private final ArrayList<String> locations;
    private final ButtonGroup buttonGroup;

    public SearchSelectView(String initialDistrict, String initialSearchType) {
        setLayout(new BorderLayout());

        // 컴포넌트 초기화
        this.locations = new ArrayList<>();
        this.buttonGroup = new ButtonGroup();
        this.searchTypeCombo = createSearchTypeCombo(initialSearchType);
        this.locationCombo = createLocationCombo(initialDistrict);
        this.searchText = createSearchTextField();
        this.searchButton = createSearchButton();
        this.result = new JPanel();

        // 메인 패널 초기화
        JPanel centerPanel = createMainPanel();
        this.searchJs = new JScrollPane(centerPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        initializeUI();
        loadLocations();
        performSearch();
    }

    private void initializeUI() {
        add(new HeaderView(MainFrame.getInstance(), "검색하기", true), BorderLayout.NORTH);
        add(searchJs, BorderLayout.CENTER);
        add(createSelectButton(), BorderLayout.SOUTH);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();

        JPanel choicePanel = createChoicePanel();
        panel.add(choicePanel, gbc);

        gbc.gridy = 1;
        panel.add(result, gbc);

        gbc.gridy = 13;
        gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(SPACING, SPACING, SPACING, SPACING);
        return gbc;
    }

    private JPanel createChoicePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(createSearchTypePanel());
        panel.add(createLocationPanel());
        panel.add(createSearchPanel());
        return panel;
    }

    private JPanel createSearchTypePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("검색 종류:"));
        panel.add(searchTypeCombo);
        return panel;
    }

    private JComboBox<String> createSearchTypeCombo(String initialSearchType) {
        String[] searchTypes = { "식당" };
        JComboBox<String> combo = new JComboBox<>(searchTypes);
        combo.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        combo.setSelectedItem(initialSearchType.equals("restaurant") ? "식당" : "모임");
        return combo;
    }

    private JPanel createLocationPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("위치:"));
        panel.add(locationCombo);
        return panel;
    }

    private JComboBox<String> createLocationCombo(String initialDistrict) {
        JComboBox<String> combo = new JComboBox<>();
        combo.setPreferredSize(new Dimension(LOCATION_WIDTH, BUTTON_HEIGHT));
        combo.setSelectedItem(initialDistrict);
        return combo;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(searchText);
        panel.add(searchButton);
        return panel;
    }

    private JTextField createSearchTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(SEARCH_WIDTH, BUTTON_HEIGHT));
        return field;
    }

    private JButton createSearchButton() {
        ImageIcon icon = loadAndScaleIcon("/images/search.png", ICON_SIZE, ICON_SIZE);
        JButton button = new JButton(icon);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        return button;
    }

    private JButton createSelectButton() {
        JButton button = new JButton("선택 완료");
        button.setFont(TITLE_FONT);
        button.setPreferredSize(new Dimension(WINDOW_WIDTH, BUTTON_HEIGHT * 2));
        button.setBackground(MAIN_COLOR);
        button.setForeground(TEXT_COLOR);
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
        int rNumber = Integer.parseInt(parts[0].trim());
        String rName = parts[1].trim();
        openMeetingMakeView(rNumber, rName);
    }

    private void loadLocations() {
        try (Connection conn = MariaDBConnection.getInstance().getConnection();
                PreparedStatement stmt = conn.prepareStatement(LOCATION_QUERY);
                ResultSet rs = stmt.executeQuery()) {

            locations.add("전체 위치");
            while (rs.next()) {
                locations.add(rs.getString("district"));
            }

            for (String location : locations) {
                locationCombo.addItem(location);
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleDatabaseError("위치 정보를 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    private void performSearch() {
        String location = locationCombo.getSelectedItem().toString();
        String keyword = searchText.getText().trim();

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        try (Connection conn = MariaDBConnection.getInstance().getConnection();
                PreparedStatement pstmt = createSearchPreparedStatement(conn, location, keyword);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                JPanel item = createRestaurantItem(rs);
                resultPanel.add(item);
                resultPanel.add(Box.createRigidArea(new Dimension(0, SPACING)));
            }

            updateResultPanel(resultPanel);
        } catch (SQLException | ClassNotFoundException e) {
            handleDatabaseError("검색 중 오류가 발생했습니다.", e);
        }
    }

    private PreparedStatement createSearchPreparedStatement(Connection conn, String location, String keyword)
            throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(RESTAURANT_SEARCH_QUERY);
        pstmt.setString(1, location);
        pstmt.setString(2, location);
        pstmt.setInt(3, 0);
        pstmt.setInt(4, 0);
        pstmt.setInt(5, 0);
        pstmt.setInt(6, 0);

        String wildcardKeyword = "%" + keyword + "%";
        for (int i = 7; i <= 10; i++) {
            pstmt.setString(i, wildcardKeyword);
        }

        return pstmt;
    }

    private void updateResultPanel(JPanel resultPanel) {
        result.removeAll();
        result.add(resultPanel);
        result.revalidate();
        result.repaint();
    }

    private JPanel createRestaurantItem(ResultSet rs) throws SQLException {
        String restaurantName = rs.getString("restaurant_name");
        String address = rs.getString("address_detail");
        String imagePath = rs.getString("restaurant_img");
        int restaurantNumber = rs.getInt("restaurant_number");

        JPanel item = createBaseItemPanel();
        JLabel imageLabel = createImageLabel(getValidImagePath(imagePath));
        JPanel textInfo = createTextInfoPanel(restaurantName, address);
        JRadioButton radioButton = createRadioButton(restaurantNumber, restaurantName);

        item.add(imageLabel, BorderLayout.WEST);
        item.add(textInfo, BorderLayout.CENTER);
        item.add(radioButton, BorderLayout.EAST);

        addRestaurantClickListener(item, restaurantNumber);
        return item;
    }

    private JPanel createBaseItemPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(ITEM_WIDTH, ITEM_HEIGHT));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return panel;
    }

    private JLabel createImageLabel(String imagePath) {
        ImageIcon icon = loadImage(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }

    private ImageIcon loadImage(String imagePath) {
        try {
            if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
                return new ImageIcon(new URL(imagePath));
            }
            return new ImageIcon(imagePath);
        } catch (Exception e) {
            return new ImageIcon(DEFAULT_IMAGE_PATH);
        }
    }

    private JPanel createTextInfoPanel(String name, String address) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(NORMAL_FONT);
        panel.add(nameLabel);
        panel.add(new JLabel(address));

        return panel;
    }

    private JRadioButton createRadioButton(int restaurantNumber, String restaurantName) {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setActionCommand(restaurantNumber + "*" + restaurantName);
        buttonGroup.add(radioButton);
        return radioButton;
    }

    private void addRestaurantClickListener(JPanel item, int restaurantNumber) {
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openRestaurantDetail(restaurantNumber);
            }
        });
    }

    private String getValidImagePath(String imagePath) {
        return (imagePath == null || imagePath.trim().isEmpty()) ? DEFAULT_IMAGE_PATH : imagePath;
    }

    private ImageIcon loadAndScaleIcon(String resourcePath, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(resourcePath));
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void openRestaurantDetail(int restaurantNumber) {
        RestaurantDetailView restaurantDetail = new RestaurantDetailView();
        restaurantDetail.getRestaurant(restaurantNumber);
        MainFrame.getInstance().showPanel("RestaurantDetail", restaurantDetail);
    }

    private void openMeetingMakeView(int restaurantNumber, String restaurantName) {
        MeetingMakeView meetingMakeView = new MeetingMakeView(restaurantNumber, restaurantName);
        MainFrame.getInstance().showPanel("MeetingMakeView", meetingMakeView);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            performSearch();
        }
    }
}
