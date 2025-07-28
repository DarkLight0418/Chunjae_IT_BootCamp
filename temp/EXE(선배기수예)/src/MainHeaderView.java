package main.java.views;

import main.java.models.MariaDBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainHeaderView extends JPanel implements ActionListener {
    // UI Constants
    private static final int HEADER_WIDTH = 360;
    private static final int HEADER_HEIGHT = 50;
    private static final int ICON_SIZE = 20;
    private static final int COMBOBOX_WIDTH = 300;
    private static final int COMBOBOX_HEIGHT = 15;
    private static final Color HEADER_BACKGROUND = new Color(144, 203, 251);
    private static final Insets LOCATION_INSETS = new Insets(0, 30, 0, 5);
    private static final Insets SEARCH_INSETS = new Insets(0, 5, 0, 5);

    // Database Constants
    private static final String LOCATION_QUERY = "SELECT district FROM location";
    private static final String DEFAULT_DISTRICT = "마포구";

    protected final JPanel header;
    protected final JButton searchButton;
    protected final JLabel locationIcon;
    protected final JComboBox<String> locationComboBox;
    private static String currentUserDistrict = DEFAULT_DISTRICT;

    public MainHeaderView(JFrame parentFrame) {
        header = createHeaderPanel();
        locationIcon = createLocationIcon();
        locationComboBox = createLocationComboBox();
        searchButton = createSearchButton();

        layoutComponents();
        add(header, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(HEADER_WIDTH, HEADER_HEIGHT));
        panel.setBackground(HEADER_BACKGROUND);
        return panel;
    }

    private JLabel createLocationIcon() {
        ImageIcon icon = loadAndScaleImage("/images/map.png", ICON_SIZE, ICON_SIZE);
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
        return label;
    }

    private JComboBox<String> createLocationComboBox() {
        List<String> locations = getLocations();
        JComboBox<String> comboBox = new JComboBox<>(locations.toArray(new String[0]));
        comboBox.setPreferredSize(new Dimension(COMBOBOX_WIDTH, COMBOBOX_HEIGHT));
        comboBox.setEnabled(true);
        comboBox.setSelectedItem(currentUserDistrict);

        comboBox.addActionListener(e -> {
            String selectedDistrict = (String) comboBox.getSelectedItem();
            currentUserDistrict = selectedDistrict;

            if (MainFrame.getInstance().getCurrentPanelName().equals("MainPage")) {
                MainPageView newMainPage = new MainPageView();
                MainFrame.getInstance().showPanel("MainPage", newMainPage);
            }
        });

        return comboBox;
    }

    private JButton createSearchButton() {
        ImageIcon icon = loadAndScaleImage("/images/search.png", ICON_SIZE, ICON_SIZE);
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        return button;
    }

    private ImageIcon loadAndScaleImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void layoutComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        // Location Icon
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.insets = LOCATION_INSETS;
        gbc.anchor = GridBagConstraints.WEST;
        header.add(locationIcon, gbc);

        // Location ComboBox
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        header.add(locationComboBox, gbc);

        // Search Button
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.insets = SEARCH_INSETS;
        gbc.anchor = GridBagConstraints.EAST;
        header.add(searchButton, gbc);
    }

    private List<String> getLocations() {
        List<String> locations = new ArrayList<>();

        try (Connection conn = MariaDBConnection.getInstance().getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(LOCATION_QUERY)) {

            while (rs.next()) {
                locations.add(rs.getString("district"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error fetching locations: " + e.getMessage());
            e.printStackTrace();
        }

        return locations;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            SearchView searchView = new SearchView("전체", "restaurant");
            MainFrame.getInstance().showPanel("Search", searchView);
        }
    }

    public static String getCurrentUserDistrict() {
        return currentUserDistrict;
    }

    public static void setCurrentUserDistrict(String district) {
        currentUserDistrict = district;
    }
}