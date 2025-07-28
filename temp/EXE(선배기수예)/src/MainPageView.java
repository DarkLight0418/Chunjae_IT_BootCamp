package main.java.views;

import main.java.models.MariaDBConnection;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPageView extends JPanel {
    // 파일 경로 상수
    private static final String DEFAULT_IMAGE_PATH = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "images" + File.separator + "noImage.png";

    // UI 크기 상수
    private static final int PANEL_WIDTH = 320;
    private static final int PANEL_HEIGHT = 800;
    private static final int ITEM_HEIGHT = 80;
    private static final int BUTTON_HEIGHT = 40;
    private static final int IMAGE_SIZE = 70;
    private static final int PADDING = 10;
    private static final int SPACING = 5;

    // UI 색상 상수
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color BUTTON_COLOR = new Color(144, 203, 251);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Color ITEM_BACKGROUND_COLOR = Color.WHITE;

    // UI 폰트 상수
    private static final Font TITLE_FONT = new Font("맑은 고딕", Font.BOLD, 20);
    private static final Font NORMAL_FONT = new Font("맑은 고딕", Font.BOLD, 14);
    private static final Font DESCRIPTION_FONT = new Font("맑은 고딕", Font.PLAIN, 12);

    // 애니메이션 상수
    private static final int TOTAL_ANIMATION_FRAMES = 20;
    private static final int ANIMATION_DELAY = 50;

    // SQL 쿼리 상수
    private static final String RESTAURANT_QUERY = "SELECT r.restaurant_number, r.restaurant_name, r.address_detail, r.restaurant_img, l.district "
            +
            "FROM restaurant r " +
            "JOIN location l ON r.location_id = l.location_id " +
            "WHERE l.district = ? " +
            "LIMIT 3";

    private static final String MEETING_QUERY = "SELECT m.meeting_number, m.title, m.content, m.meeting_date, r.restaurant_img " +
            "FROM meeting m " +
            "JOIN restaurant r ON m.restaurant_number = r.restaurant_number " +
            "JOIN location l ON r.location_id = l.location_id " +
            "%s LIMIT 3";

    // 컴포넌트 필드
    private JScrollPane mainJs;
    private JPanel meetingListPanel;
    private JPanel meetingListContainer;
    private JPanel listPanel;
    private String currentDistrict;
    private String randomCategory;
    private List<String> menuCategories = new ArrayList<>();
    private Timer animationTimer;
    private int currentMenuIndex = 0;
    private int animationCount = 0;

    public MainPageView() {
        // 로그인 체크
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }

        this.currentDistrict = MainHeaderView.getCurrentUserDistrict();
        initializeUI();
        loadData();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        // Header 패널 추가
        add(new MainHeaderView(MainFrame.getInstance()).header, BorderLayout.NORTH);

        // Footer 패널 추가
        add(Footer.getInstance(), BorderLayout.SOUTH);

        // 메인 컨텐츠 초기화
        initializeMainContent();
    }

    private void initializeMainContent() {
        // 메인 중간 패널 (스크롤될 컨텐츠를 담을 패널)
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(BACKGROUND_COLOR);
        mainContent.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, 15, 15));

        // 모든 내부 컴포넌트들에 대해 최대 너비 제한 설정
        Dimension maxSize = new Dimension(PANEL_WIDTH, Integer.MAX_VALUE);

        // 추천 메뉴 패널 추가
        mainContent.add(createRecommendMenuPanel(maxSize));
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));

        // 리스트 컨테이너 초기화
        initializeListContainers(mainContent, maxSize);

        // 스크롤팬 설정
        mainJs = new JScrollPane(mainContent);
        configureScrollPane();
        add(mainJs, BorderLayout.CENTER);
    }

    private JPanel createRecommendMenuPanel(Dimension maxSize) {
        JPanel recommendMenu = new JPanel();
        recommendMenu.setLayout(new BorderLayout(0, PADDING));
        recommendMenu.setMaximumSize(maxSize);
        recommendMenu.setPreferredSize(new Dimension(PANEL_WIDTH, 150));
        recommendMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        recommendMenu.setBackground(BACKGROUND_COLOR);
        recommendMenu.setBorder(BorderFactory.createEmptyBorder(PADDING, 0, 20, 0));

        // 랜덤 추천 메뉴 레이블
        JLabel recommendText = new JLabel("오늘의 추천 메뉴는?", SwingConstants.CENTER);
        recommendText.setFont(TITLE_FONT);
        recommendText.setPreferredSize(new Dimension(PANEL_WIDTH, 30));
        recommendMenu.add(recommendText, BorderLayout.NORTH);

        // 버튼 패널 추가
        recommendMenu.add(createRecommendButtonPanel(recommendText), BorderLayout.SOUTH);

        return recommendMenu;
    }

    private JPanel createRecommendButtonPanel(JLabel recommendText) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, PADDING, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        // 메뉴 추천 버튼
        JButton rerollButton = createStyledButton("메뉴 추천!");
        rerollButton.addActionListener(e -> handleRerollButtonClick(rerollButton, recommendText));

        // 검색 버튼
        JButton searchButton = createStyledButton("이 메뉴 맛집검색!");
        searchButton.addActionListener(e -> handleSearchButtonClick());

        buttonPanel.add(rerollButton);
        buttonPanel.add(searchButton);

        return buttonPanel;
    }

    private void handleRerollButtonClick(JButton rerollButton, JLabel recommendText) {
        rerollButton.setEnabled(false);
        getMenu(recommendText);
        rerollButton.setText("다시 추천!");

        Timer enableTimer = new Timer(ANIMATION_DELAY * TOTAL_ANIMATION_FRAMES + 500, event -> {
            rerollButton.setEnabled(true);
            ((Timer) event.getSource()).stop();
        });
        enableTimer.setRepeats(false);
        enableTimer.start();
    }

    private void handleSearchButtonClick() {
        if (randomCategory == null || randomCategory.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "메뉴 추천을 먼저 해주세요!",
                    "알림",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        openSearchView(currentDistrict, "restaurant", randomCategory);
    }

    private void configureScrollPane() {
        mainJs.setBackground(BACKGROUND_COLOR);
        mainJs.setBorder(null);
        mainJs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainJs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainJs.getVerticalScrollBar().setUnitIncrement(16);
        mainJs.getViewport().setBackground(BACKGROUND_COLOR);
    }

    private void loadData() {
        loadRestaurants();
        loadMeetings();
    }

    public void refreshLists(String district) {
        this.currentDistrict = district;

        listPanel.removeAll();
        meetingListPanel.removeAll();

        loadRestaurants();
        loadMeetings();

        listPanel.revalidate();
        listPanel.repaint();

        meetingListPanel.revalidate();
        meetingListPanel.repaint();
    }

    private void loadRestaurants() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            MariaDBConnection dbConnection = MariaDBConnection.getInstance();
            conn = dbConnection.getConnection();

            pstmt = conn.prepareStatement(RESTAURANT_QUERY);
            pstmt.setString(1, currentDistrict);
            rs = pstmt.executeQuery();

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(0, 0, 5, 0);

            while (rs.next()) {
                String restaurantName = rs.getString("restaurant_name");
                String address = rs.getString("address_detail");
                String imagePath = rs.getString("restaurant_img");
                String district = rs.getString("district");
                int restaurantNumber = rs.getInt("restaurant_number");

                // 이미지 경로 처리
                String finalImagePath = validateImagePath(imagePath);

                JPanel item = createLocationItem(finalImagePath, restaurantName, address, district);
                item.setMaximumSize(new Dimension(320, 80));

                // 각 아이템에 클릭 이벤트 추가
                item.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        openRestaurantDetail(restaurantNumber);
                    }
                });

                listPanel.add(item, gbc);
                gbc.gridy++;
            }

            // "추천 장소 전체 보기" 버튼
            JButton viewAllButton = createStyledButton("추천 장소 전체 보기");
            viewAllButton.setPreferredSize(new Dimension(200, 40));
            viewAllButton.setMinimumSize(new Dimension(200, 40));
            viewAllButton.setMaximumSize(new Dimension(200, 40));
            viewAllButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            viewAllButton.addActionListener(e -> {
                openSearchView(currentDistrict, "restaurant");
            });

            gbc.gridy++;
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.fill = GridBagConstraints.NONE;
            gbc.insets = new Insets(5, 0, 10, 0);
            listPanel.add(viewAllButton, gbc);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터를 불러오는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }

    private void loadMeetings() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            MariaDBConnection dbConnection = MariaDBConnection.getInstance();
            conn = dbConnection.getConnection();

            String query = String.format(MEETING_QUERY, currentDistrict.equals("전체") ? "" : "WHERE l.district = ?");
            pstmt = conn.prepareStatement(query);
            if (!currentDistrict.equals("전체")) {
                pstmt.setString(1, currentDistrict);
            }
            rs = pstmt.executeQuery();

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(0, 0, 5, 0);

            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                String imagePath = rs.getString("restaurant_img");
                int meetingNumber = rs.getInt("meeting_number");
                java.sql.Date meetingDate = rs.getDate("meeting_date");

                if(meetingDate.before(new java.util.Date())) continue;

                String finalImagePath = validateImagePath(imagePath);

                JPanel item = createMeetingItem(finalImagePath, title, content, meetingDate);
                item.setMaximumSize(new Dimension(320, 80));

                // 각 모임 아이템에 클릭 이벤트 추가
                item.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        openMeetingDetail(meetingNumber);
                    }
                });

                meetingListPanel.add(item, gbc);
                gbc.gridy++; // 다음 아이템은 아래로
            }

            // "추천 모임 전체 보기" 버튼
            JButton viewAllButton = createStyledButton("추천 모임 전체");
            viewAllButton.setPreferredSize(new Dimension(150, 40));
            viewAllButton.setMinimumSize(new Dimension(150, 40));
            viewAllButton.setMaximumSize(new Dimension(150, 40));
            viewAllButton.addActionListener(e -> {
                openSearchView(currentDistrict, "meeting");
            });

            gbc.gridy++; // 다음 row로
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.fill = GridBagConstraints.NONE;
            gbc.insets = new Insets(0, 0, 0, 5);
            meetingListPanel.add(viewAllButton, gbc);

            // 모임 만들기 버튼
            JButton createMeetingButton = createStyledButton("모임 만들기");
            createMeetingButton.setPreferredSize(new Dimension(150, 40));
            createMeetingButton.setMinimumSize(new Dimension(150, 40));
            createMeetingButton.setMaximumSize(new Dimension(150, 40));
            createMeetingButton.addActionListener(e -> {
                openMeetingMakeSelect();
            });

            gbc.anchor = GridBagConstraints.LINE_END;
            meetingListPanel.add(createMeetingButton, gbc);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "모임 데이터를 불러오는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeResources(rs, pstmt, conn);
        }
    }

    private JPanel createLocationItem(String imagePath, String title, String description, String district) {
        JPanel locItemPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 그림자 효과
                g2.setColor(new Color(0, 0, 0, 20));
                g2.fillRoundRect(3, 3, getWidth() - 4, getHeight() - 4, 20, 20);

                // 메인 배경
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, 20, 20);

                g2.dispose();
            }
        };

        locItemPanel.setLayout(new BorderLayout(10, 0));
        locItemPanel.setPreferredSize(new Dimension(320, 80));
        locItemPanel.setMaximumSize(new Dimension(320, 80));
        locItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        locItemPanel.setBackground(ITEM_BACKGROUND_COLOR);
        locItemPanel.setOpaque(false);

        // 둥근 테두리 효과 추가
        locItemPanel.setBorder(new RoundedBorder(ITEM_BACKGROUND_COLOR, 15));

        // 이미지 처리 (URL을 처리하기 위해 ImageIcon을 생성)
        ImageIcon imageIcon = null;
        try {
            // URL이면 URL을 이용해 ImageIcon 생성
            imageIcon = new ImageIcon(new java.net.URL(imagePath));
        } catch (java.net.MalformedURLException e) {
            // URL이 아니면 로컬 파일 경로 처리
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                imageIcon = new ImageIcon(imagePath);
            } else {
                imageIcon = new ImageIcon(DEFAULT_IMAGE_PATH); // 기본 이미지 경로로 처리
            }
        }

        Image image = imageIcon.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setPreferredSize(new Dimension(IMAGE_SIZE, IMAGE_SIZE));

        // 텍스트 패널
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(ITEM_BACKGROUND_COLOR);
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descriptionLabel = new JLabel("<html>" + description + "</html>");
        descriptionLabel.setFont(DESCRIPTION_FONT);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(titleLabel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textPanel.add(descriptionLabel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        // 컴포넌트 한 번만 추가
        locItemPanel.add(imageLabel, BorderLayout.WEST);
        locItemPanel.add(textPanel, BorderLayout.CENTER);

        return locItemPanel;
    }

    private JPanel createMeetingItem(String imagePath, String title, String content, java.sql.Date date) {
        JPanel itemPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 그림자 효과
                g2.setColor(new Color(0, 0, 0, 20));
                g2.fillRoundRect(3, 3, getWidth() - 4, getHeight() - 4, 20, 20);

                // 메인 배경
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, 20, 20);

                g2.dispose();
            }
        };

        itemPanel.setLayout(new BorderLayout(10, 0));
        itemPanel.setPreferredSize(new Dimension(320, 80));
        itemPanel.setMaximumSize(new Dimension(320, 80));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemPanel.setBackground(ITEM_BACKGROUND_COLOR);
        itemPanel.setOpaque(false);

        // 둥근 테두리 효과 추가
        itemPanel.setBorder(new RoundedBorder(ITEM_BACKGROUND_COLOR, 15));

        // 이미지 처리 (URL을 처리하기 위해 ImageIcon을 생성)
        ImageIcon imageIcon = null;
        try {
            // URL이면 URL을 이용해 ImageIcon 생성
            imageIcon = new ImageIcon(new java.net.URL(imagePath));
        } catch (java.net.MalformedURLException e) {
            // URL이 아니면 로컬 파일 경로 처리
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                imageIcon = new ImageIcon(imagePath);
            } else {
                imageIcon = new ImageIcon(DEFAULT_IMAGE_PATH); // 기본 이미지 경로로 처리
            }
        }

        Image image = imageIcon.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setPreferredSize(new Dimension(IMAGE_SIZE, IMAGE_SIZE));

        // 텍스트 패널
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(ITEM_BACKGROUND_COLOR);
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String displayContent = content.length() > 20? content.substring(0, 20) + "..." : content;
        JLabel contentLabel = new JLabel(displayContent);
        contentLabel.setFont(DESCRIPTION_FONT);
        contentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH시 mm분");
        JLabel dateLabel = new JLabel(formatter.format(date));
        dateLabel.setFont(DESCRIPTION_FONT);
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(titleLabel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        textPanel.add(contentLabel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textPanel.add(dateLabel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        textPanel.add(Box.createVerticalGlue()); // 남은 공간을 채우기 위한 여백 추가

        itemPanel.add(imageLabel, BorderLayout.WEST);
        itemPanel.add(textPanel, BorderLayout.CENTER);

        return itemPanel;
    }

    void getMenu(JLabel recommendText) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            MariaDBConnection dbConnection = MariaDBConnection.getInstance();
            conn = dbConnection.getConnection();

            String query = "SELECT minor_category, menu_number FROM menu";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            menuCategories.clear(); // 기존 리스트 초기화
            while (rs.next()) {
                menuCategories.add(rs.getString("minor_category"));
            }

            if (!menuCategories.isEmpty()) {
                // 애니메이션 시작
                startMenuAnimation(recommendText);
            } else {
                recommendText.setText("추천할 메뉴가 없어요.");
            }

        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
            recommendText.setText("추천 메뉴를 가져오는 중 문제가 발생했습니다.");
        } finally {
            closeResources(rs, stmt, conn);
        }
    }

    private void startMenuAnimation(JLabel recommendText) {
        // 기존 타이머가 실행 중이면 중지
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        currentMenuIndex = 0;
        animationCount = 0;

        // 애니메이션 타이머 생성
        animationTimer = new Timer(ANIMATION_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 현재 메뉴 표시
                recommendText.setText(menuCategories.get(currentMenuIndex));

                // 다음 메뉴로 이동
                currentMenuIndex = (currentMenuIndex + 1) % menuCategories.size();

                // 애니메이션 프레임 카운트 증가
                animationCount++;

                // 애니메이션 종료 조건
                if (animationCount >= TOTAL_ANIMATION_FRAMES) {
                    animationTimer.stop();
                    // 최종 선택된 메뉴 표시
                    Random random = new Random();
                    randomCategory = menuCategories.get(random.nextInt(menuCategories.size()));

                    // 선택된 메뉴에 효과 추가
                    highlightFinalSelection(recommendText, randomCategory);
                }
            }
        });

        // 애니메이션 시작
        animationTimer.start();
    }

    private void highlightFinalSelection(JLabel recommendText, String finalMenu) {
        // 최종 선택된 메뉴를 강조하여 표시
        recommendText.setText(finalMenu);
        recommendText.setFont(new Font(recommendText.getFont().getName(), Font.BOLD, 24));

        // 0.5초 후에 폰트 크기를 원래대로 복구
        Timer resetTimer = new Timer(500, e -> {
            recommendText.setFont(TITLE_FONT);
            ((Timer) e.getSource()).stop();
        });
        resetTimer.setRepeats(false);
        resetTimer.start();
    }

    private String validateImagePath(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return DEFAULT_IMAGE_PATH; // 기본 이미지 경로
        } else {
            try {
                // URL 형식인지 확인
                new java.net.URL(imagePath); // URL 형식이 맞으면 예외 발생 안 함
                return imagePath; // URL이 유효하면 그대로 반환
            } catch (java.net.MalformedURLException e) {
                // URL이 아닌 경우 로컬 파일 경로 처리
                File imageFile = new File(imagePath);
                return imageFile.exists() ? imagePath : DEFAULT_IMAGE_PATH;
            }
        }
    }

    private void closeResources(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void openRestaurantDetail(int restaurantNumber) {
        RestaurantDetailView restaurantDetail = new RestaurantDetailView();
        restaurantDetail.getRestaurant(restaurantNumber);
        MainFrame.getInstance().showPanel("RestaurantDetail", restaurantDetail);
    }

    private void openMeetingDetail(int meetingNumber) {
        MeetingDetailView meetingDetail = new MeetingDetailView(meetingNumber);
        MainFrame.getInstance().showPanel("MeetingDetail", meetingDetail);
    }

    private void openSearchView(String searchDistrict, String searchType) {
        SearchView searchView = new SearchView(searchDistrict, searchType);
        MainFrame.getInstance().showPanel("Search", searchView);
    }

    private void openMeetingMakeSelect() {
        // 로그인 체크
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }
        MeetingMakeSelect MeetingMakeSelect = new MeetingMakeSelect();
        MainFrame.getInstance().showPanel("MeetingMakeSelect", MeetingMakeSelect);
    }

    private void openSearchView(String searchDistrict, String searchType, String randomCategory) {
        String finalDistrict = searchDistrict.equals("전체") ? searchDistrict : MainHeaderView.getCurrentUserDistrict();
        SearchView searchView = new SearchView(finalDistrict, searchType, randomCategory);
        MainFrame.getInstance().showPanel("Search", searchView);
    }

    // 스타일된 버튼 생성 메소드 수정
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        button.setFont(NORMAL_FONT);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        button.setOpaque(false);
        return button;
    }

    // 둥근 테두리를 위한 커스텀 Border 클래스 추가
    private static class RoundedBorder extends AbstractBorder {
        private final Color color;
        private final int radius;

        RoundedBorder(Color color, int radius) {
            this.color = color;
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius / 2, radius / 2, radius / 2, radius / 2);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }

    private void initializeListContainers(JPanel mainContent, Dimension maxSize) {
        // 리스트 컨테이너 패널
        JPanel listContainer = new JPanel();
        listContainer.setLayout(new BorderLayout(0, PADDING));
        listContainer.setMaximumSize(maxSize);
        listContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        listContainer.setBackground(BACKGROUND_COLOR);
        listContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // 제목
        JLabel recommendListTitle = new JLabel("추천 장소!");
        recommendListTitle.setFont(TITLE_FONT);
        recommendListTitle.setHorizontalAlignment(SwingConstants.LEFT);
        listContainer.add(recommendListTitle, BorderLayout.NORTH);
        mainContent.add(listContainer);

        // 리스트 패널 (실제 아이템들이 들어갈 패널)
        this.listPanel = new JPanel();
        listPanel.setLayout(new GridBagLayout());
        listPanel.setBackground(BACKGROUND_COLOR);
        listContainer.add(listPanel, BorderLayout.CENTER);

        // 모임 리스트 컨테이너
        meetingListContainer = new JPanel();
        meetingListContainer.setLayout(new BorderLayout(0, PADDING));
        meetingListContainer.setMaximumSize(maxSize);
        meetingListContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        meetingListContainer.setBackground(BACKGROUND_COLOR);
        meetingListContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // 모임 제목
        JLabel meetingListTitle = new JLabel("추천 모임!");
        meetingListTitle.setFont(TITLE_FONT);
        meetingListTitle.setHorizontalAlignment(SwingConstants.LEFT);
        meetingListContainer.add(meetingListTitle, BorderLayout.NORTH);
        mainContent.add(meetingListContainer);

        // 모임 리스트 패널 (실제 아이템들이 들어갈 패널)
        this.meetingListPanel = new JPanel();
        meetingListPanel.setLayout(new GridBagLayout());
        meetingListPanel.setBackground(BACKGROUND_COLOR);
        meetingListContainer.add(meetingListPanel, BorderLayout.CENTER);

        meetingListContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("모임 상세 페이지로 넘어가기!");
            }
        });
    }
}