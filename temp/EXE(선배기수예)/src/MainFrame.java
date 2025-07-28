package main.java.views;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MainFrame extends JFrame {
    // 상수 정의
    private static final int WINDOW_WIDTH = 360;
    private static final int WINDOW_HEIGHT = 800;
    private static final String WINDOW_TITLE = "밥메이트";
    private static final String INITIAL_PANEL = "MainPage";

    // 싱글톤 구현 개선 (Holder 패턴 사용)
    private static class MainFrameHolder {
        private static final MainFrame INSTANCE = new MainFrame();
    }

    private final JPanel contentPanel;
    private final CardLayout cardLayout;
    private final Stack<String> navigationHistory;
    private String currentPanelName;
    private int currentRestaurantNumber;

    // Panel types enum
    private enum PanelType {
        MAIN_PAGE("MainPage"),
        BOOKMARK("Bookmark"),
        MY_MEETING("MyMeeting"),
        MY_PAGE("MyPage"),
        LOGIN("Login"),
        RESTAURANT_DETAIL("RestaurantDetail"),
        SEARCH("Search"),
        PROFILE("Profile"),
        SELECTED_BOOKMARK("SelectedBookmark"),
        SEARCH_SELECT("SearchSelect"),
        MEETING_MAKE_SELECT("MeetingMakeSelect"),
        MEETING_MAKE_VIEW("MeetingMakeView");

        private final String name;

        PanelType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static PanelType fromString(String name) {
            for (PanelType type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown panel name: " + name);
        }
    }

    private MainFrame() {
        setTitle(WINDOW_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // 화면 중앙에 위치하도록 설정
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        add(contentPanel);

        navigationHistory = new Stack<>();
        currentPanelName = INITIAL_PANEL;
        navigationHistory.push(currentPanelName);

        setVisible(true);
    }

    public static MainFrame getInstance() {
        return MainFrameHolder.INSTANCE;
    }

    public void showPanel(String panelName, JPanel panel) {
        if (panelName == null || panel == null) {
            System.err.println("Error: Invalid panel parameters");
            return;
        }

        if (!panelName.equals(currentPanelName)) {
            navigationHistory.push(panelName);
            logNavigation("Navigation pushed: " + panelName);
        }
        this.currentPanelName = panelName;

        SwingUtilities.invokeLater(() -> {
            try {
                contentPanel.removeAll();
                contentPanel.add(panel, panelName);
                cardLayout.show(contentPanel, panelName);
                Footer.getInstance().updateCurrentButton();
                revalidate();
                repaint();
            } catch (Exception e) {
                System.err.println("Error showing panel: " + e.getMessage());
            }
        });
        logNavigation("Current panel: " + currentPanelName);
    }

    public void goBack() {
        if (navigationHistory.size() <= 1) {
            logNavigation("Cannot go back: History is empty");
            return;
        }

        String currentPanel = navigationHistory.pop();
        String previousPanel = navigationHistory.peek();
        currentPanelName = previousPanel;
        logNavigation("Going back from " + currentPanel + " to " + previousPanel);

        SwingUtilities.invokeLater(() -> {
            try {
                contentPanel.removeAll();
                JPanel newPanel = createPanel(previousPanel);
                if (newPanel != null) {
                    contentPanel.add(newPanel, previousPanel);
                    cardLayout.show(contentPanel, previousPanel);
                    Footer.getInstance().updateCurrentButton();
                    revalidate();
                    repaint();
                } else {
                    System.err.println("Failed to create panel: " + previousPanel);
                }
            } catch (Exception e) {
                System.err.println("Error during navigation: " + e.getMessage());
            }
        });
    }

    public void setCurrentRestaurantNumber(int restaurantNumber) {
        this.currentRestaurantNumber = restaurantNumber;
    }

    public int getCurrentRestaurantNumber() {
        return this.currentRestaurantNumber;
    }

    private JPanel createPanel(String panelName) {
        try {
            PanelType type = PanelType.fromString(panelName);
            return createPanelByType(type);
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating panel: " + e.getMessage());
            return null;
        }
    }

    private JPanel createPanelByType(PanelType type) {
        switch (type) {
            case MAIN_PAGE: return new MainPageView();
            case BOOKMARK: return new BookmarkView();
            case MY_MEETING: return new MyMeetingView();
            case MY_PAGE: return new MyPageView();
            case LOGIN: return new LoginView();
            case SEARCH: return new SearchView("전체", "restaurant");
            case PROFILE: return new ProfileManagementView();
            case SELECTED_BOOKMARK: return new SelectedBookmarkView();
            case SEARCH_SELECT: return new SearchSelectView("전체", "restaurant");
            case MEETING_MAKE_SELECT: return new MeetingMakeSelect();
            case MEETING_MAKE_VIEW: return new MeetingMakeView(currentRestaurantNumber, "");
            case RESTAURANT_DETAIL:
                RestaurantDetailView restaurantDetailView = new RestaurantDetailView();
                restaurantDetailView.getRestaurant(currentRestaurantNumber);
                return restaurantDetailView;
            default:
                System.err.println("Unhandled panel type: " + type);
                return null;
        }
    }

    public void clearHistory() {
        while (navigationHistory.size() > 1) {
            navigationHistory.pop();
        }
        logNavigation("History cleared");
    }

    public String getCurrentPanelName() {
        return currentPanelName;
    }

    private void logNavigation(String message) {
        System.out.println("Navigation: " + message);
        System.out.println("History stack: " + navigationHistory);
    }
}