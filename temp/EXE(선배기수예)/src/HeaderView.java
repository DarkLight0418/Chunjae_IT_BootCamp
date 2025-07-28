package main.java.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HeaderView extends JPanel implements ActionListener {
    // UI 크기 상수
    private static final int HEADER_WIDTH = 330;
    private static final int HEADER_HEIGHT = 50;
    private static final int ICON_SIZE = 20;
    private static final int BUTTON_SIZE = 30;
    private static final int TITLE_FONT_SIZE = 18;
    private static final int PADDING_SIZE = 6; // " " 공백 크기

    // UI 색상 상수
    private static final Color HEADER_BACKGROUND = new Color(144, 203, 251); // #90cbfb

    // UI 폰트 상수
    private static final Font HEADER_FONT = new Font("맑은 고딕", Font.BOLD, TITLE_FONT_SIZE);

    // 컴포넌트 필드
    private final JFrame parentFrame;
    private final JButton returnButton;
    private final JLabel headerText;

    /**
     * HeaderView 생성자
     * 
     * @param parentFrame     부모 프레임
     * @param text            헤더에 표시할 텍스트
     * @param hasReturnButton 뒤로가기 버튼 표시 여부
     */
    public HeaderView(JFrame parentFrame, String text, boolean hasReturnButton) {
        this.parentFrame = parentFrame;
        this.headerText = createHeaderLabel(text);
        this.returnButton = hasReturnButton ? createReturnButton() : null;

        initializeUI(hasReturnButton);
    }

    private void initializeUI(boolean hasReturnButton) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(HEADER_WIDTH, HEADER_HEIGHT));
        setBackground(HEADER_BACKGROUND);

        if (hasReturnButton && returnButton != null) {
            add(returnButton, BorderLayout.WEST);
        }

        add(headerText, BorderLayout.CENTER);
        add(createPaddingLabel(), BorderLayout.EAST);
    }

    private JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(HEADER_FONT);
        return label;
    }

    private JButton createReturnButton() {
        ImageIcon icon = loadAndScaleIcon("/images/return.png", ICON_SIZE, ICON_SIZE);
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        button.setFocusPainted(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(this);
        return button;
    }

    private ImageIcon loadAndScaleIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private JLabel createPaddingLabel() {
        // 헤더 제목 가운데 정렬을 위한 패딩
        return new JLabel(" ".repeat(PADDING_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton) {
            MainFrame.getInstance().goBack();
        }
    }
}