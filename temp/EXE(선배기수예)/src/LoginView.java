package main.java.views;

import main.java.models.MariaDBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginView extends JPanel {
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JLabel idLabel;
    private JLabel passwordLabel;

    private static final Color BUTTON_COLOR = new Color(144, 203, 251);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;

    public LoginView() {
        setLayout(new BorderLayout());

        // 메인 패널에 GridLayout 사용
        JPanel mainPanel = new JPanel(new GridLayout(6, 1, 0, 20)); // 6개의 행, 1개의 열, 세로 간격 20
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel logoPanel = new JPanel();
        JLabel iconLabel = new JLabel(createBowlIcon());
        logoPanel.setMaximumSize(new Dimension(320, 80));
        mainPanel.add(iconLabel); // 로고 패널 추가

        // 아이디 입력 필드와 라벨을 포함한 패널 추가
        JPanel idPanel = createInputPanel("아이디", "아이디", false);
        mainPanel.add(idPanel); // 아이디 패널 추가

        // 비밀번호 입력 필드와 라벨을 포함한 패널 추가
        JPanel passwordPanel = createInputPanel("비밀번호", "비밀번호", true);
        mainPanel.add(passwordPanel); // 비밀번호 패널 추가

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 중앙 정렬
        loginButton = createStyledButton("로그인");
        signupButton = createStyledButton("회원가입");

        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        mainPanel.add(buttonPanel); // 버튼 패널 추가

        // 메인 패널을 프레임에 추가
        add(mainPanel, BorderLayout.CENTER);

        // 탭 순서 설정
        idField.setFocusTraversalKeysEnabled(true);
        passwordField.setFocusTraversalKeysEnabled(true);
        loginButton.setFocusTraversalKeysEnabled(true);
        signupButton.setFocusTraversalKeysEnabled(true);

        // 엔터 키 이벤트 추가
        KeyStroke enter = KeyStroke.getKeyStroke("ENTER");

        loginButton.registerKeyboardAction(
                e -> loginButton.doClick(),
                enter,
                JComponent.WHEN_FOCUSED);

        signupButton.registerKeyboardAction(
                e -> signupButton.doClick(),
                enter,
                JComponent.WHEN_FOCUSED);

        // 포커스 표시를 위한 키 리스너 추가
        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    loginButton.doClick();
                }
            }
        });

        signupButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    signupButton.doClick();
                }
            }
        });

        // 초기 포커스 설정
        idField.requestFocusInWindow();

        loginButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();

            SwingUtilities.invokeLater(() -> {
                if (validateLogin(id, password)) {
                    MainFrame.getInstance().showPanel("MainPage", new MainPageView());
                } else {
                    if (!JOptionPane.getFrameForComponent(LoginView.this).isVisible()) {
                        JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호를 확인하세요.");
                    }
                }
            });
        });

        signupButton.addActionListener(e -> {
            MainFrame.getInstance().showPanel("Signup", new SignupView());
        });
    }

    // 로고 아이콘 생성 (이미지 파일 경로 수정 필요)
    private ImageIcon createBowlIcon() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("images/logo.png");
            if (resource == null) {
                throw new Exception("이미지 파일이 존재하지 않습니다.");
            }
            ImageIcon icon = new ImageIcon(resource);

            Image img = icon.getImage();
            Image resizedImg = img.getScaledInstance(320, 80, Image.SCALE_SMOOTH);

            return new ImageIcon(resizedImg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 입력 필드와 라벨을 하나의 패널로 만드는 함수
    private JPanel createInputPanel(String label, String placeholder, boolean isPassword) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // 왼쪽 정렬, 가로 여백 10, 세로 여백 5

        JLabel inputLabel = new JLabel(label);
        if (isPassword) {
            passwordField = createStyledPasswordField(placeholder);
            panel.add(inputLabel);
            panel.add(passwordField);
        } else {
            idField = createStyledTextField(placeholder);
            panel.add(inputLabel);
            panel.add(idField);
        }

        return panel;
    }

    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField(placeholder);
        field.setPreferredSize(new Dimension(300, 40));
        field.setMaximumSize(new Dimension(300, 40));
        field.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        field.setForeground(Color.GRAY);
        field.setEchoChar((char) 0);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setEchoChar('*');
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(field.getPassword()).isEmpty()) {
                    field.setText(placeholder);
                    field.setEchoChar((char) 0);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        return field;
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setPreferredSize(new Dimension(300, 40));
        field.setMaximumSize(new Dimension(300, 40));
        field.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return field;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed() || isFocusOwner()) {
                    g2.setColor(BUTTON_COLOR.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(BUTTON_COLOR.brighter());
                } else {
                    g2.setColor(BUTTON_COLOR);
                }

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // 포커스 테두리 그리기
                if (isFocusOwner()) {
                    g2.setColor(new Color(0, 0, 0, 50));
                    g2.setStroke(new BasicStroke(2));
                    g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 18, 18);
                }

                FontMetrics metrics = g2.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

                g2.setColor(getModel().isRollover() ? Color.BLACK : BUTTON_TEXT_COLOR);
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };

        button.setPreferredSize(new Dimension(100, 40));
        button.setMaximumSize(new Dimension(100, 40));
        button.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        // 마우스 호버 효과
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        // 포커스 가능하도록 설정
        button.setFocusable(true);

        return button;
    }

    private boolean validateLogin(String id, String password) {
        try (Connection conn = MariaDBConnection.getInstance().getConnection()) {

            String query = "SELECT user.user_number, user.id, user.password, user.email, user.nickname, " +
                          "user.sex, user.profile_img, user.birth_date, location.district " +
                          "FROM user " +
                          "LEFT JOIN location ON user.location_id = location.location_id " +
                          "WHERE user.id = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, id);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String dbPassword = rs.getString("password");
                    if (password.equals(dbPassword)) {
                        SessionManagerView.getInstance().login(id);

                        UserInformation userInfo = UserInformation.getInstance();
                        userInfo.setUserNumber(rs.getInt("user_number"));
                        userInfo.setId(rs.getString("id"));
                        userInfo.setNickname(rs.getString("nickname"));
                        userInfo.setEmail(rs.getString("email"));
                        userInfo.setGender(rs.getString("sex"));
                        userInfo.setProfileImg(rs.getString("profile_img"));
                        userInfo.setLocation(rs.getString("district"));

                        // MainHeaderView의 currentUserDistrict 업데이트
                        MainHeaderView.setCurrentUserDistrict(rs.getString("district"));
                        
                        java.sql.Date birthDate = rs.getDate("birth_date");
                        if (birthDate != null) {
                            userInfo.setBirthDate(birthDate.toLocalDate());
                        }

                        // 로그인 성공 후 MainPage로 이동
                        MainFrame.getInstance().showPanel("MainPage", new MainPageView());
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터베이스 연결 중 오류가 발생했습니다: " + e.getMessage(),
                    "로그인 오류", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
