package main.java.views;

import main.java.models.MariaDBConnection;
import main.java.views.LoginView;
import main.java.views.MainFrame;
import main.java.views.SessionManagerView;
import main.java.views.UserInformation;
import net.miginfocom.swing.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 계정 설정 화면을 구현한 클래스입니다.
 * 로그아웃과 계정 삭제 기능을 제공합니다.
 */
public class SetAccountView extends JPanel implements ActionListener {
    // UI 상수
    private static final int WINDOW_WIDTH = 360;
    private static final int WINDOW_HEIGHT = 800;
    private static final int BUTTON_PADDING = 10;
    private static final int ARROW_SIZE = 20;
    private static final Font MENU_FONT = new Font("맑은 고딕", Font.PLAIN, 15);

    // UI 컴포넌트
    private final String[] menuTitles = { "로그아웃", "계정 삭제" };
    private final JButton[] menuButtons = new JButton[menuTitles.length];
    private JPanel mainPanel;

    /**
     * SetAccountView 생성자
     * UI를 초기화하고 로그인 상태를 확인합니다.
     */
    public SetAccountView() {
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(new HeaderView(MainFrame.getInstance(), "Set Account", true), BorderLayout.NORTH);
        add(Footer.getInstance(), BorderLayout.SOUTH);

        initMainPanel();
        add(mainPanel, BorderLayout.CENTER);
    }

    private void initMainPanel() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        for (int i = 0; i < menuTitles.length; i++) {
            gbc.gridy = i;
            JPanel menuItem = createMenuItem(i);
            mainPanel.add(menuItem, gbc);
        }

        // 더미 패널에도 배경색 설정
        gbc.gridy = menuTitles.length;
        gbc.weighty = 1.0;
        JPanel dummyPanel = new JPanel();
        dummyPanel.setBackground(Color.WHITE);
        mainPanel.add(dummyPanel, gbc);
    }

    private JPanel createMenuItem(int index) {
        JPanel menu = new JPanel(new BorderLayout());
        menu.setBackground(Color.WHITE);
        menu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        menuButtons[index] = createMenuButton(menuTitles[index]);
        menu.add(menuButtons[index], BorderLayout.CENTER);

        JLabel arrowLabel = createArrowLabel();
        if (arrowLabel != null) {
            menu.add(arrowLabel, BorderLayout.EAST);
        }

        return menu;
    }

    private JButton createMenuButton(String title) {
        JButton button = new JButton(title);
        button.setFont(MENU_FONT);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(
                BorderFactory.createEmptyBorder(BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addActionListener(this);
        return button;
    }

    private JLabel createArrowLabel() {
        try {
            Image arrowImg = ImageIO.read(getClass().getResource("/images/arrow.png"));
            Image scaledArrow = arrowImg.getScaledInstance(ARROW_SIZE, ARROW_SIZE, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledArrow));
        } catch (IOException e) {
            System.err.println("화살표 이미지를 불러오는 데 실패했습니다: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < menuButtons.length; i++) {
            if (source == menuButtons[i]) {
                handleMenuAction(menuTitles[i]);
                break;
            }
        }
    }

    private void handleMenuAction(String menuTitle) {
        switch (menuTitle) {
            case "로그아웃":
                handleLogout();
                break;
            case "계정 삭제":
                handleAccountDeletion();
                break;
        }
    }

    private void handleLogout() {
        SessionManagerView.getInstance().logout();
        showMessage("로그아웃 되었습니다.");
        navigateToLogin();
    }

    private void handleAccountDeletion() {
        if (confirmAccountDeletion()) {
            try {
                String currentUserId = UserInformation.getInstance().getId();
                deleteAccountFromDB(currentUserId);
                showMessage("계정이 삭제되었습니다.");
                SessionManagerView.getInstance().logout();
                navigateToLogin();
            } catch (Exception e) {
                handleError("계정 삭제 중 오류가 발생했습니다.", e);
            }
        }
    }

    private boolean confirmAccountDeletion() {
        return JOptionPane.showConfirmDialog(this,
                "계정을 삭제하시겠습니까?",
                "확인",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void deleteAccountFromDB(String userId) throws SQLException, ClassNotFoundException {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 사용자 ID입니다.");
        }

        Connection conn = null;
        try {
            conn = MariaDBConnection.getInstance().getConnection();
            conn.setAutoCommit(false); // 트랜잭션 시작

            // 사용자 번호 조회
            int userNumber;
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT user_number FROM user WHERE id = ?")) {
                pstmt.setString(1, userId);
                var rs = pstmt.executeQuery();
                if (!rs.next()) {
                    throw new SQLException("사용자를 찾을 수 없습니다.");
                }
                userNumber = rs.getInt("user_number");
            }

            // 1. 북마크 테이블에서 해당 사용자의 데이터 삭제
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM bookmark WHERE user_number = ?")) {
                pstmt.setInt(1, userNumber);
                pstmt.executeUpdate();
            }

            // 2. 참가자 테이블에서 해당 사용자와 관련된 모든 데이터 삭제
            try (PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM participant WHERE user_number = ? OR meeting_number IN (SELECT meeting_number FROM meeting WHERE user_number = ?)")) {
                pstmt.setInt(1, userNumber);
                pstmt.setInt(2, userNumber);
                pstmt.executeUpdate();
            }

            // 3. 미팅 테이블에서 해당 사용자가 만든 미팅 삭제
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM meeting WHERE user_number = ?")) {
                pstmt.setInt(1, userNumber);
                pstmt.executeUpdate();
            }

            // 4. 사용자 테이블에서 계정 삭제
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user WHERE user_number = ?")) {
                pstmt.setInt(1, userNumber);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("계정 삭제 실패: 계정이 존재하지 않습니다");
                }
            }

            conn.commit(); // 트랜잭션 커밋
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback(); // 오류 발생시 롤백
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // auto-commit 모드 복구
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void navigateToLogin() {
        MainFrame.getInstance().showPanel("Login", new LoginView());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void handleError(String message, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, message, "오류", JOptionPane.ERROR_MESSAGE);
    }
}
