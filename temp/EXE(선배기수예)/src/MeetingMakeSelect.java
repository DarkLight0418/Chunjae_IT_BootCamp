package main.java.views;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeetingMakeSelect extends JPanel implements ActionListener {
    // UI 크기 상수
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 60;
    private static final int GRID_GAP = 10;
    private static final int PANEL_PADDING = 10;

    // UI 폰트 상수
    private static final Font BUTTON_FONT = new Font("맑은 고딕", Font.BOLD, 16);

    // UI 색상 상수
    private static final Color MAIN_COLOR = new Color(144, 203, 251);

    // UI 컴포넌트
    private final JButton bookmarkedR = createStyledButton("내가 찜한 장소");
    private final JButton searchR = createStyledButton("검색해서 고르기");
    private final JPanel center = new JPanel(new BorderLayout());

    public MeetingMakeSelect() {
        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }

        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        // Header 패널 추가
        add(new HeaderView(MainFrame.getInstance(), "모임 장소 선택하기", true), BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel();
        center.add(buttonPanel, BorderLayout.NORTH);
        add(center);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, GRID_GAP, GRID_GAP));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(
                PANEL_PADDING, PANEL_PADDING, PANEL_PADDING, PANEL_PADDING));

        buttonPanel.add(bookmarkedR);
        buttonPanel.add(searchR);

        return buttonPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setBackground(MAIN_COLOR);
        button.setBorder(new LineBorder(Color.GRAY, 0, true));
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == bookmarkedR) {
            openSelectedBookmarkView();
        } else if (obj == searchR) {
            openSearchSelectView("전체", "레스토랑");
        }
    }

    private void openSelectedBookmarkView() {
        SelectedBookmarkView selectedBM = new SelectedBookmarkView();
        MainFrame.getInstance().showPanel("SelectedBookmark", selectedBM);
    }

    private void openSearchSelectView(String initialDistrict, String initialSearchType) {
        SearchSelectView searchSelect = new SearchSelectView("전체", "restaurant");
        MainFrame.getInstance().showPanel("SearchSelect", searchSelect);
    }
}
