package main.java.views;

import main.java.models.MariaDBConnection;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import com.toedter.calendar.JDateChooser;

public class ProfileManagementView extends JPanel implements ActionListener {
    // Profile management components
    private JLabel titleLabel;
    private JPanel profileImagePanel;
    private JButton imageChangeButton;
    private JTextField nicknameField;
    private JComboBox<String> locationComboBox;
    private JTextField idField;
    private JTextField emailField;
    private JComboBox<String> birthYearComboBox;
    private JComboBox<String> birthMonthComboBox;
    private JComboBox<String> birthDayComboBox;
    private JToggleButton maleToggleButton;
    private JToggleButton femaleToggleButton;
    private ButtonGroup genderGroup;
    private JButton saveButton;
    private JDateChooser birthDateChooser;
    private String currentGender;
    private CirclePanel circlePanel;
    private String profileImagePath;
    private String userId;
    private UserInformation userInfo;

    // Constants for dimensions
    private static final int SCREEN_WIDTH = 360;
    private static final int SCREEN_HEIGHT = 800;
    private static final int BUTTON_SIZE = 40;
    private static final int ICON_SIZE = 24;

    // 스타일 상수 추가
    private static final Color BUTTON_COLOR = new Color(144, 203, 251);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Font BUTTON_FONT = new Font("맑은 고딕", Font.BOLD, 14);

    // locations 배열을 SignupView와 동일하게 수정
    String[] locations = { "강서구", "양천구", "구로구", "금천구", "관악구",
            "동작구", "영등포구", "서초구", "강남구", "송파구",
            "강동구", "광진구", "중랑구", "노원구", "도봉구",
            "강북구", "성북구", "종로구", "중구", "동대문구",
            "성동구", "마포구", "서대문구", "은평구", "용산구"
    };

    public ProfileManagementView() {
        // UserInformation에서 정보 가져오기
        this.userInfo = UserInformation.getInstance();
        this.profileImagePath = userInfo.getProfileImg();
        this.userId = userInfo.getId();
        this.currentGender = userInfo.getGender();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        add(new HeaderView(MainFrame.getInstance(), "프로필 관리", true), BorderLayout.NORTH);
        
        // 메인 패널 생성
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("fillx, insets 20, gap 0", "[grow,center]", "[]20[]20[]"));
        mainPanel.setBackground(Color.WHITE);

        // 이미지 섹션
        JPanel imageSection = new JPanel(new MigLayout("fillx", "[grow,center]", "[]15[]"));
        imageSection.setBackground(Color.WHITE);

        // CirclePanel 초기화 및 추가
        circlePanel = new CirclePanel(profileImagePath);
        circlePanel.setPreferredSize(new Dimension(150, 150));
        imageSection.add(circlePanel, "cell 0 0, align center");

        // 이미지 변경 버튼
        imageChangeButton = createStyledButton("이미지 변경", this);
        imageChangeButton.setPreferredSize(new Dimension(250, 30));
        imageSection.add(imageChangeButton, "cell 0 1, align center");

        mainPanel.add(imageSection, "wrap");

        // 입력 필드 섹션
        JPanel formSection = new JPanel();
        formSection.setLayout(new MigLayout("fillx, insets 0", "[grow,center]", "[]15[]15[]15[]15[]15[]"));
        formSection.setBackground(Color.WHITE);

        // 입력 필드들 초기화
        nicknameField = createStyledTextField(250);
        emailField = createStyledTextField(250);
        idField = createStyledTextField(250);

        nicknameField.setText(userInfo.getNickname());
        emailField.setText(userInfo.getEmail());
        idField.setText(userInfo.getId());
        idField.setEditable(false);

        // 생년월일 선택기
        birthDateChooser = new JDateChooser();
        birthDateChooser.setPreferredSize(new Dimension(250, 30));
        
        // UserInformation에서 생년월일 가져오기
        if (userInfo.getBirthDate() != null) {
            System.out.println("Birth date from UserInfo: " + userInfo.getBirthDate()); // 디버깅용
            birthDateChooser.setDate(java.sql.Date.valueOf(userInfo.getBirthDate()));
        }

        // 지역 선택 콤보박스 초기화 및 데이터 로드
        locationComboBox = new JComboBox<>();
        loadLocationData();
        locationComboBox.setPreferredSize(new Dimension(250, 30));

        // 성별 선택 패널
        JPanel genderPanel = createStyledGenderPanel();
        if ("M".equals(currentGender)) {
            maleToggleButton.setSelected(true);
        } else if ("F".equals(currentGender)) {
            femaleToggleButton.setSelected(true);
        }

        // 각 컴포넌트의 배경색 설정
        locationComboBox.setBackground(Color.WHITE);
        birthDateChooser.setBackground(Color.WHITE);
        
        // 성별 패널 배경색 설정
        genderPanel.setBackground(Color.WHITE);

        // 필드들을 폼 섹션에 추가 - 간격 조정
        formSection.add(createLabeledField("닉네임", nicknameField), "wrap, gaptop 5");
        formSection.add(createLabeledField("아이디", idField), "wrap, gaptop 15");
        formSection.add(createLabeledField("이메일", emailField), "wrap, gaptop 15");
        formSection.add(createLabeledField("생년월일", birthDateChooser), "wrap, gaptop 15");
        formSection.add(createLabeledField("지역", locationComboBox), "wrap, gaptop 15");
        formSection.add(genderPanel, "wrap, gaptop 15");

        mainPanel.add(formSection, "wrap, gaptop 20");

        // 저장 버튼
        saveButton = createStyledButton("저장", this);
        saveButton.setPreferredSize(new Dimension(250, 30));
        mainPanel.add(saveButton, "align center, gaptop 20");

        // 스크롤 패널에 메인 패널 추가
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
        add(Footer.getInstance(), BorderLayout.SOUTH);

        // 프로필 이미지 설정
        if (userInfo.getProfileImg() != null) {
            profileImagePath = userInfo.getProfileImg();
            circlePanel.setImagePath(profileImagePath);
        }
    }

    private void loadLocationData() {
        // locations 배열에서 직접 데이터를 가져오도록 수정
        for (String district : locations) {
            locationComboBox.addItem(district);
        }
        
        // 현재 사용자의 지역 선택
        String userLocation = userInfo.getLocation();
        if (userLocation != null) {
            for (int i = 0; i < locations.length; i++) {
                if (locations[i].equals(userLocation)) {
                    locationComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    // Create a gender selection panel
    private JPanel createStyledGenderPanel() {
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.X_AXIS));
        genderPanel.setMaximumSize(new Dimension(250, 30));

        JLabel genderLabel = new JLabel("성별");
        genderLabel.setPreferredSize(new Dimension(70, 30));
        genderLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 11));

        // ToggleButton 생성
        maleToggleButton = new JToggleButton("남성");
        femaleToggleButton = new JToggleButton("여성");

        // 스타일 설정
        styleToggleButton(maleToggleButton);
        styleToggleButton(femaleToggleButton);

        // 현재 성별에 따른 버튼 선택
        if ("M".equals(currentGender)) {
            maleToggleButton.setSelected(true);
        } else if ("F".equals(currentGender)) {
            femaleToggleButton.setSelected(true);
        }

        // ButtonGroup 설정
        genderGroup = new ButtonGroup();
        genderGroup.add(maleToggleButton);
        genderGroup.add(femaleToggleButton);

        genderPanel.add(genderLabel);
        genderPanel.add(Box.createHorizontalStrut(10));
        genderPanel.add(maleToggleButton);
        genderPanel.add(Box.createHorizontalStrut(50));
        genderPanel.add(femaleToggleButton);
        genderPanel.add(Box.createHorizontalGlue());

        return genderPanel;
    }

    // 토글버튼 스타일링
    private void styleToggleButton(JToggleButton button) {
        button.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
        button.setFocusPainted(false);

        // 선택되지 않은 상태의 스타일
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);

        // 버튼의 상태에 따른 스타일 변경
        button.addChangeListener(e -> {
            if (button.isSelected()) {
                button.setBackground(new Color(65, 105, 225));
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
        });
    }

    // Helper function to add component with spacing
    private void addComponentWithSpacing(JPanel panel, JComponent component, int spacing) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(component);
        panel.add(Box.createVerticalStrut(spacing));
    }

    private JTextField createStyledTextField(int width) {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(width, 30));
        field.setMaximumSize(new Dimension(width, 30));
        field.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(3, 8, 3, 8)));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        return field;
    }

    private JComboBox<String> createStyledComboBox(int width) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(width, 30));
        comboBox.setMaximumSize(new Dimension(width, 30));
        comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
        return comboBox;
    }

    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(BUTTON_COLOR.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(BUTTON_COLOR.brighter());
                } else {
                    g2.setColor(BUTTON_COLOR);
                }

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                FontMetrics metrics = g2.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

                g2.setColor(getModel().isRollover() ? Color.BLACK : BUTTON_TEXT_COLOR);
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };

        button.setPreferredSize(new Dimension(250, 40));  // 너비만 다르게 설정
        button.setFont(BUTTON_FONT);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.addActionListener(actionListener);

        // 마우스 호버 효과
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        return button;
    }

    private JPanel createLabeledField(String labelText, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setMaximumSize(new Dimension(250, 30));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(70, 30));
        label.setFont(new Font("맑은 고딕", Font.PLAIN, 11));

        panel.add(label);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(field);

        return panel;
    }

    private ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            try (Connection conn = MariaDBConnection.getInstance().getConnection()) {
                // 먼저 birth_date 컬럼이 있는지 확인
                DatabaseMetaData md = conn.getMetaData();
                ResultSet rs = md.getColumns(null, null, "user", "birth_date");
                boolean hasBirthDate = rs.next();
                rs.close();

                String sql;
                if (hasBirthDate) {
                    sql = "UPDATE user SET nickname = ?, email = ?, location_id = ?, sex = ?, profile_img = ?, birth_date = ? WHERE id = ?";
                } else {
                    // birth_date 컬럼이 없으면 자동으로 추가
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute("ALTER TABLE user ADD COLUMN birth_date DATE");
                    }
                    sql = "UPDATE user SET nickname = ?, email = ?, location_id = ?, sex = ?, profile_img = ?, birth_date = ? WHERE id = ?";
                }

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, nicknameField.getText());
                    pstmt.setString(2, emailField.getText());
                    pstmt.setInt(3, locationComboBox.getSelectedIndex() + 1);
                    pstmt.setString(4, maleToggleButton.isSelected() ? "M" : "F");
                    
                    String imgPath = profileImagePath;
                    if (imgPath == null || imgPath.isEmpty()) {
                        imgPath = userInfo.getProfileImg();
                    }
                    pstmt.setString(5, imgPath);
                    
                    if (birthDateChooser.getDate() != null) {
                        pstmt.setDate(6, new java.sql.Date(birthDateChooser.getDate().getTime()));
                    } else {
                        pstmt.setNull(6, Types.DATE);
                    }
                    
                    pstmt.setString(7, userId);

                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        // UserInformation 업데이트
                        userInfo.setNickname(nicknameField.getText());
                        userInfo.setLocation(locations[locationComboBox.getSelectedIndex()]);
                        userInfo.setGender(maleToggleButton.isSelected() ? "M" : "F");
                        userInfo.setEmail(emailField.getText());
                        userInfo.setProfileImg(imgPath);
                        
                        if (birthDateChooser.getDate() != null) {
                            userInfo.setBirthDate(birthDateChooser.getDate().toInstant()
                                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
                        }
                        
                        JOptionPane.showMessageDialog(this, "프로필이 성공적으로 업데이트되었습니다.");
                        MainFrame.getInstance().showPanel("MyPage", new MyPageView());
                    }
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "프로필 업데이트 중 오류가 발생했습니다: " + ex.getMessage(), 
                                            "에러", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == imageChangeButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("이미지 선택");
            fileChooser.setFileFilter(
                    new javax.swing.filechooser.FileNameExtensionFilter("이미지 파일", "jpg", "png", "jpeg", "gif"));

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String newImagePath = fileChooser.getSelectedFile().getAbsolutePath();
                profileImagePath = newImagePath;

                // CirclePanel 업데이트
                circlePanel.setImagePath(profileImagePath);
                circlePanel.repaint();

                // 이미지 변경 사실을 콘솔에 출력
                System.out.println("이미지 변경됨: " + newImagePath);
            }
        }
    }
}