package main.java.views;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyPageView extends JPanel {
    private static final Font MENU_FONT = new Font("맑은 고딕", Font.PLAIN, 15);
    
    private CirclePanel profileImagePanel;
    private JLabel nicknameLabel;

    public MyPageView() {

        setLayout(new BorderLayout());

        Color backgroundColor = Color.WHITE;
        setBackground(backgroundColor);

        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }

        UserInformation userInfo = UserInformation.getInstance(); // UserInfo 데이터 가져오기
        String profileImagePath = userInfo.getProfileImg(); // 프로필 이미지 경로
        String nickname = userInfo.getNickname(); // 닉네임

        HeaderView header = new HeaderView(MainFrame.getInstance(), "My page", true);
        add(header, BorderLayout.NORTH);

        JPanel profilePanel = createProfilePanel(profileImagePath, nickname);
        profilePanel.setBackground(getBackground());
        add(profilePanel, BorderLayout.CENTER);

        Footer footer = Footer.getInstance();
        add(footer, BorderLayout.SOUTH);
    }

    private JPanel createProfilePanel(String profileImagePath, String nickname) {
        JPanel profilePanel = new JPanel(new MigLayout("center, flowy, insets 20", "[grow,center]", "[]20[]30[]"));
        profilePanel.setBackground(getBackground());

        // UserInformation에서 프로필 이미지 경로 가져오기
        String imagePath = UserInformation.getInstance().getProfileImg();
        if (imagePath == null || imagePath.isEmpty()) {
            imagePath = "default.jpg";
        }
        
        profileImagePanel = new CirclePanel(imagePath);
        profileImagePanel.setPreferredSize(new Dimension(150, 150));
        profilePanel.add(profileImagePanel, "align center");

        nicknameLabel = new JLabel(nickname);
        nicknameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        nicknameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilePanel.add(nicknameLabel, "align center");

        JPanel buttonPanel = createButtonSection();
        profilePanel.add(buttonPanel, "wrap, growx");

        return profilePanel;
    }

    private JPanel createButtonSection() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new MigLayout("fillx, insets 10, gap 0", "[grow]", "[]10[]10[]"));
        buttonPanel.setBackground(getBackground());

        // "프로필 관리" 버튼
        buttonPanel.add(createMenuItem("프로필 관리", e -> {
            MainFrame.getInstance().showPanel("Profile", new ProfileManagementView());
        }), "wrap, growx");

        // "계정 설정" 버튼
        buttonPanel.add(createMenuItem("계정 설정", e -> {
            MainFrame.getInstance().showPanel("Set Account", new SetAccountView());
        }), "wrap, growx");

        buttonPanel.add(createMenuItem("1:1 문의", e -> {
            JPanel parent = (JPanel) this.getParent();
            if(parent != null){
                parent.removeAll();
                parent.add(new CustomerServiceView());
                parent.revalidate();
                parent.repaint();
            }
        }), "wrap, growx");

        return buttonPanel;
    }

    private JPanel createMenuItem(String title, ActionListener listener) {
        JPanel menuItem = new JPanel(new BorderLayout());
        menuItem.setBackground(Color.WHITE);
        menuItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JButton button = new JButton(title);
        button.setFont(MENU_FONT);  // 폰트 설정
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        
        // 패딩 추가
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        // 화살표 아이콘 추가
        try {
            Image arrowImg = ImageIO.read(getClass().getResource("/images/arrow.png"));
            Image scaledArrow = arrowImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            JLabel arrowLabel = new JLabel(new ImageIcon(scaledArrow));
            menuItem.add(arrowLabel, BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        menuItem.add(button, BorderLayout.CENTER);
        return menuItem;
    }
}
