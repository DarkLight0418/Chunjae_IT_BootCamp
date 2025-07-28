package main.java.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Footer extends JPanel implements ActionListener {
    // UI Constants
    private static final int BUTTON_SIZE = 50;
    private static final int ICON_SIZE = 20;
    private static final int GRID_HGAP = 10;
    private static final Color FOOTER_BACKGROUND = new Color(144, 203, 251);

    // Navigation enum
    public enum FooterNavigation {
        HOME(0, "MainPage", "/images/home1.png", "/images/home2.png"),
        BOOKMARK(1, "Bookmark", "/images/bookmark1.png", "/images/bookmark2.png"),
        MEETING(2, "MyMeeting", "/images/meet1.png", "/images/meet2.png"),
        MYPAGE(3, "MyPage", "/images/my1.png", "/images/my2.png");

        private final int index;
        private final String panelName;
        private final String defaultImagePath;
        private final String selectedImagePath;

        FooterNavigation(int index, String panelName, String defaultImagePath, String selectedImagePath) {
            this.index = index;
            this.panelName = panelName;
            this.defaultImagePath = defaultImagePath;
            this.selectedImagePath = selectedImagePath;
        }

        public static FooterNavigation fromPanelName(String panelName) {
            for (FooterNavigation nav : values()) {
                if (nav.panelName.equals(panelName)) {
                    return nav;
                }
            }
            return HOME; // 기본값
        }
    }

    // Singleton implementation
    private static class FooterHolder {
        private static final Footer INSTANCE = new Footer();
    }

    private final JPanel footer;
    private final ImageIcon[] defaultIcons;
    private final ImageIcon[] selectedIcons;
    private final JButton[] navigationButtons;
    private int currentSelectedIndex;

    private Footer() {
        footer = new JPanel(new GridLayout(1, FooterNavigation.values().length, GRID_HGAP, 0));
        footer.setBackground(FOOTER_BACKGROUND);

        defaultIcons = new ImageIcon[FooterNavigation.values().length];
        selectedIcons = new ImageIcon[FooterNavigation.values().length];
        navigationButtons = new JButton[FooterNavigation.values().length];

        initializeImages();
        initializeButtons();
        updateCurrentButton();

        setLayout(new BorderLayout());
        add(footer, BorderLayout.CENTER);
    }

    public static Footer getInstance() {
        return FooterHolder.INSTANCE;
    }

    private void initializeImages() {
        FooterNavigation[] navigations = FooterNavigation.values();
        for (FooterNavigation nav : navigations) {
            defaultIcons[nav.index] = loadAndScaleImage(nav.defaultImagePath);
            selectedIcons[nav.index] = loadAndScaleImage(nav.selectedImagePath);
        }
    }

    private ImageIcon loadAndScaleImage(String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image scaledImage = icon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void initializeButtons() {
        FooterNavigation[] navigations = FooterNavigation.values();
        for (FooterNavigation nav : navigations) {
            JButton button = createNavigationButton(nav);
            navigationButtons[nav.index] = button;
            footer.add(button);
        }
    }

    private JButton createNavigationButton(FooterNavigation nav) {
        JButton button = new JButton(defaultIcons[nav.index]);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        button.addActionListener(this);
        return button;
    }

    public void updateCurrentButton() {
        String currentPanel = MainFrame.getInstance().getCurrentPanelName();
        if (currentPanel == null)
            return;

        FooterNavigation navigation = FooterNavigation.fromPanelName(currentPanel);
        currentSelectedIndex = navigation.index;

        // Reset all buttons to default state
        for (FooterNavigation nav : FooterNavigation.values()) {
            navigationButtons[nav.index].setIcon(defaultIcons[nav.index]);
        }
        // Set selected button
        navigationButtons[currentSelectedIndex].setIcon(selectedIcons[currentSelectedIndex]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (FooterNavigation nav : FooterNavigation.values()) {
            if (e.getSource() == navigationButtons[nav.index]) {
                if (currentSelectedIndex == nav.index)
                    return;
                navigateToPage(nav);
                break;
            }
        }
    }

    private void navigateToPage(FooterNavigation navigation) {
        JPanel targetPanel = createTargetPanel(navigation);
        MainFrame.getInstance().showPanel(navigation.panelName, targetPanel);
    }

    private JPanel createTargetPanel(FooterNavigation navigation) {
        switch (navigation) {
            case HOME:
                return new MainPageView();
            case BOOKMARK:
                return new BookmarkView();
            case MEETING:
                return new MyMeetingView();
            case MYPAGE:
                return new MyPageView();
            default:
                throw new IllegalStateException("Unexpected navigation: " + navigation);
        }
    }
}