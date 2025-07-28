package main.java.views;

import main.java.models.MariaDBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class NewMeeting {
    // SQL 쿼리 상수
    private static final String USER_QUERY = "SELECT user_number FROM user WHERE id = ?";
    private static final String INSERT_MEETING_QUERY = "INSERT INTO meeting (recruit_limit, posted_date, title, content, meeting_date, user_number, restaurant_number) "
            +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    // 모임 정보
    private final String title;
    private final String meetingDate;
    private final int recruitNumber;
    private final String message;
    private final int restaurantNumber;
    private final MainFrame mainFrame;

    public NewMeeting(String title, String meetingDate, int recruitNumber, String message, int restaurantNumber) {
        this.title = title;
        this.meetingDate = meetingDate;
        this.recruitNumber = recruitNumber;
        this.message = message;
        this.restaurantNumber = restaurantNumber;
        this.mainFrame = MainFrame.getInstance();

        createMeeting();
    }

    private void createMeeting() {
        try (Connection conn = MariaDBConnection.getInstance().getConnection()) {
            int userNumber = getUserNumber(conn);
            if (userNumber == -1) {
                showError("사용자 정보를 찾을 수 없습니다.");
                return;
            }

            if (insertMeeting(conn, userNumber)) {
                //showSuccess("모임이 만들어졌습니다");
                openMyMeetingPage();
            } else {
                showError("모임 생성에 실패했습니다.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            handleDatabaseError("모임 생성 중 오류가 발생했습니다", e);
        }
    }

    private int getUserNumber(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(USER_QUERY)) {
            String loggedInUserId = SessionManagerView.getInstance().getLoggedInUserId();
            pstmt.setString(1, loggedInUserId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_number");
                }
            }
        }
        return -1;
    }

    private boolean insertMeeting(Connection conn, int userNumber) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_MEETING_QUERY)) {
            String postedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            pstmt.setInt(1, recruitNumber);
            pstmt.setString(2, postedDate);
            pstmt.setString(3, title);
            pstmt.setString(4, message);
            pstmt.setString(5, meetingDate);
            pstmt.setInt(6, userNumber);
            pstmt.setInt(7, restaurantNumber);

            return pstmt.executeUpdate() > 0;
        }
    }

    private void openMyMeetingPage() {
        try {
            mainFrame.showPanel("MyMeeting", new MyMeetingView());
        } catch (Exception e) {
            handleDatabaseError("화면 전환 중 오류가 발생했습니다", e);
        }
    }

//    private void showSuccess(String message) {
//        JOptionPane.showMessageDialog(mainFrame, message, "성공", JOptionPane.INFORMATION_MESSAGE);
//    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(mainFrame, message, "오류", JOptionPane.ERROR_MESSAGE);
    }

    private void handleDatabaseError(String message, Exception e) {
        e.printStackTrace();
        showError(message + ": " + e.getMessage());
    }
}