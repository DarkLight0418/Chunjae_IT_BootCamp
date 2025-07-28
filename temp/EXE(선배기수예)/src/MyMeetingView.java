package main.java.views;

import main.java.models.MariaDBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;

public class MyMeetingView extends JPanel {
    // 이미지 경로 상수
    private static final String DEFAULT_IMAGE_PATH = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "images" + File.separator + "noImage.png";

    // UI 크기 상수
    private static final int PANEL_HEIGHT = 100;
    private static final int IMAGE_SIZE = 80;
    private static final int SMALL_IMAGE_SIZE = 50;
    private static final int MAX_NAME_LENGTH = 25;

    // UI 폰트 상수
    private static final Font TITLE_FONT = new Font("맑은 고딕", Font.BOLD, 16);
    private static final Font NORMAL_FONT = new Font("맑은 고딕", Font.BOLD, 13);
    private static final Font TAB_FONT = new Font("맑은 고딕", Font.BOLD, 13);

    // SQL 쿼리 상수
    private static final String HOSTED_MEETINGS_QUERY = "SELECT DISTINCT m.meeting_number, r.restaurant_number, m.title, m.meeting_date, "
            +
            "m.recruit_limit, r.restaurant_name, r.restaurant_img " +
            "FROM meeting m JOIN restaurant r ON m.restaurant_number = r.restaurant_number " +
            "WHERE m.user_number = ? ORDER BY m.meeting_date DESC";

    private static final String PARTICIPATED_MEETINGS_QUERY = "SELECT m.meeting_number, m.title, m.meeting_date, m.recruit_limit, r.restaurant_name, r.restaurant_img "
            + "FROM meeting m "
            + "JOIN restaurant r ON m.restaurant_number = r.restaurant_number "
            + "JOIN participant p ON p.meeting_number = m.meeting_number "
            + "WHERE p.user_number = ? "
            + "AND m.user_number != p.user_number "  // 본인이 만든 모임 제외
            + "ORDER BY m.meeting_date DESC";

    private static final String PARTICIPANT_COUNT_QUERY = "SELECT COUNT(*) AS participant_count From participant p WHERE p.meeting_number = ?";

    // UI 컴포넌트
    private final JTabbedPane mymeetingtab = new JTabbedPane();
    private final JPanel hostedMeetings = new JPanel();
    private final JPanel participatedMeetings = new JPanel();
    private int mNumber;

    public MyMeetingView() {
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }

        UserInformation userInfo = UserInformation.getInstance();
        int userNumber = userInfo.getUserNumber();

        setLayout(new BorderLayout());
        initializeUI(userNumber);
        loadMeeting(HOSTED_MEETINGS_QUERY, userNumber, 0);
    }

    private void initializeUI(int userNumber) {
        add(new HeaderView(MainFrame.getInstance(), "내 모임", true), BorderLayout.NORTH);
        add(Footer.getInstance(), BorderLayout.SOUTH);

        mymeetingtab.setFont(TAB_FONT);

        initializeHostedMeetingsPanel();
        initializeParticipatedMeetingsPanel();
        setupTabChangeListener(userNumber);

        add(mymeetingtab, BorderLayout.CENTER);
    }

    private void initializeHostedMeetingsPanel() {
        hostedMeetings.setLayout(new BoxLayout(hostedMeetings, BoxLayout.Y_AXIS));
        hostedMeetings.setAlignmentY(Component.TOP_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(hostedMeetings);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mymeetingtab.addTab("내가 만든 모임", scrollPane);
    }

    private void initializeParticipatedMeetingsPanel() {
        participatedMeetings.setLayout(new BoxLayout(participatedMeetings, BoxLayout.Y_AXIS));
        participatedMeetings.setAlignmentY(Component.TOP_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(participatedMeetings);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mymeetingtab.addTab("참여 모임", scrollPane);
    }

    private void setupTabChangeListener(int userNumber) {
        mymeetingtab.addChangeListener(e -> {
            int selectedTabIndex = mymeetingtab.getSelectedIndex();
            if (selectedTabIndex == 0) {
                hostedMeetings.removeAll();
                loadMeeting(HOSTED_MEETINGS_QUERY, userNumber, 0);
            } else if (selectedTabIndex == 1) {
                participatedMeetings.removeAll();
                loadMeeting(PARTICIPATED_MEETINGS_QUERY, userNumber, 1);
            }
        });
    }

    void loadMeeting(String selectedSql, int userNumber, int checkNumber) {
        try (Connection conn = MariaDBConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(selectedSql)) {

            pstmt.setInt(1, userNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    processMeetingData(rs, conn, checkNumber);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleError("데이터를 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    private void processMeetingData(ResultSet rs, Connection conn, int checkNumber) throws SQLException {
        mNumber = rs.getInt("m.meeting_number");
        String meetingTitle = getValidString(rs.getString("title"), "모임 정보 없음");
        Date meetingDate = rs.getDate("meeting_date");
        int meetingLimit = rs.getInt("recruit_limit");
        String restaurantName = getValidString(rs.getString("restaurant_name"), "식당 정보 없음");
        String restaurantImage = getValidString(rs.getString("restaurant_img"), DEFAULT_IMAGE_PATH);

        int participantCount = getParticipantCount(conn, mNumber);

        createMeetingPanel(meetingTitle, meetingDate, meetingLimit, participantCount,
                restaurantName, restaurantImage, checkNumber, mNumber);
    }

    private int getParticipantCount(Connection conn, int meetingNumber) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(PARTICIPANT_COUNT_QUERY)) {
            pstmt.setInt(1, meetingNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt("participant_count") : 0;
            }
        }
    }

    private JPanel createMeetingPanel(String meetingTitle, Date meetingDate, int meetingLimit,
            int count, String restaurantName, String imageUrl, int checkNumber, int meetingNumber) {
        JPanel meetingData = new JPanel(new GridBagLayout());
        meetingData.setAlignmentX(Component.LEFT_ALIGNMENT);
        meetingData.setAlignmentY(Component.TOP_ALIGNMENT);
        meetingData.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        meetingData.setMaximumSize(new Dimension(Integer.MAX_VALUE, PANEL_HEIGHT));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        addRestaurantImage(meetingData, imageUrl, gbc);
        addMeetingTitle(meetingData, meetingTitle, gbc);
        addRestaurantName(meetingData, restaurantName, gbc);
        addMeetingTime(meetingData, meetingDate, gbc);
        addParticipantCount(meetingData, count, meetingLimit, gbc);

        addClickListener(meetingData, meetingNumber);
        addToPanel(meetingData, checkNumber);

        return meetingData;
    }

    private void addRestaurantImage(JPanel panel, String imageUrl, GridBagConstraints gbc) {
        JLabel imageLabel = new JLabel(loadImage(imageUrl));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(imageLabel, gbc);
    }

    private void addMeetingTitle(JPanel panel, String title, GridBagConstraints gbc) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(TITLE_FONT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(titleLabel, gbc);
    }

    private void addRestaurantName(JPanel panel, String name, GridBagConstraints gbc) {
        String displayName = name.length() > MAX_NAME_LENGTH ? name.substring(0, MAX_NAME_LENGTH) + "..." : name;
        JLabel nameLabel = new JLabel(displayName);
        nameLabel.setFont(NORMAL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);
    }

    private void addMeetingTime(JPanel panel, Date date, GridBagConstraints gbc) {
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd    HH시 mm분");
        JLabel timeLabel = new JLabel(formatter.format(date));
        timeLabel.setFont(NORMAL_FONT);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(timeLabel, gbc);
    }

    private void addParticipantCount(JPanel panel, int count, int limit, GridBagConstraints gbc) {
        JLabel countLabel = new JLabel(String.format("인원: %d/%d명", count, limit));
        countLabel.setFont(NORMAL_FONT);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(countLabel, gbc);
    }

    private void addClickListener(JPanel panel, int meetingNumber) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openMeetingDetailView(meetingNumber);
            }
        });
    }

    private void addToPanel(JPanel meetingData, int checkNumber) {
        if (checkNumber == 0) {
            hostedMeetings.add(meetingData);
        } else {
            participatedMeetings.add(meetingData);
        }
        meetingData.revalidate();
        meetingData.repaint();
    }

    private ImageIcon loadImage(String imagePath) {
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
            Image scaledImage = defaultIcon.getImage().getScaledInstance(SMALL_IMAGE_SIZE, SMALL_IMAGE_SIZE,
                    Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
    }

    private String getValidString(String value, String defaultValue) {
        return (value == null || value.trim().isEmpty()) ? defaultValue : value;
    }

    private void handleError(String message, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, message, "오류", JOptionPane.ERROR_MESSAGE);
    }

    public void openMeetingDetailView(int meetingNumber) {
        MeetingDetailView meetingDetail = new MeetingDetailView(meetingNumber);
        MainFrame.getInstance().showPanel("MeetingDetail", meetingDetail);
    }
}