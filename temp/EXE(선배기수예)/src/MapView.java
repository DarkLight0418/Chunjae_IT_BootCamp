package main.java.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Properties;

/**
 * 구글 맵 API를 사용하여 장소의 위치를 지도로 표시하는 클래스입니다.
 */
public class MapView extends JFrame {
    // 상수 정의
    private static final String PROPERTIES_FILE = "secrets.properties";
    private static final String API_KEY_PROPERTY = "api_key";
    private static final String PLACE_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json";
    private static final String STATIC_MAP_URL = "https://maps.googleapis.com/maps/api/staticmap";
    private static final String GOOGLE_MAPS_URL = "https://www.google.com/maps/place/?q=place_id:";

    private static final int MAP_WIDTH = 600;
    private static final int MAP_HEIGHT = 400;
    private static final int ZOOM_LEVEL = 15;

    private final String placeId;
    private String apiKey;

    /**
     * MapView 생성자
     * 
     * @param placeId 구글 맵 Place ID
     */
    public MapView(String placeId) {
        this.placeId = placeId;

        setTitle("지도 보기");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            loadApiKey();
            initializeMap();
        } catch (Exception e) {
            handleError("지도를 가져오는 데 실패했습니다.", e);
        }
    }

    private void loadApiKey() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new FileNotFoundException("secrets.properties 파일을 찾을 수 없습니다.");
            }
            properties.load(input);
            apiKey = properties.getProperty(API_KEY_PROPERTY);
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new IllegalStateException("API 키가 설정되지 않았습니다.");
            }
        }
    }

    private void initializeMap() throws Exception {
        String placeDetailsJson = getPlaceDetails();
        double[] coordinates = extractCoordinates(placeDetailsJson);
        BufferedImage mapImage = getMapImage(coordinates[0], coordinates[1]);
        String googleMapLink = GOOGLE_MAPS_URL + placeId;
        displayMap(mapImage, googleMapLink);
    }

    private String getPlaceDetails() throws Exception {
        String urlStr = String.format("%s?place_id=%s&key=%s", PLACE_DETAILS_URL, placeId, apiKey);
        return makeHttpRequest(urlStr);
    }

    private String makeHttpRequest(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }

    private double[] extractCoordinates(String jsonResponse) {
        try {
            String latStr = jsonResponse.split("\"lat\" : ")[1].split(",")[0].trim();
            String lngStr = jsonResponse.split("\"lng\" : ")[1].split("}")[0].trim();
            return new double[] {
                    Double.parseDouble(latStr),
                    Double.parseDouble(lngStr)
            };
        } catch (Exception e) {
            throw new IllegalStateException("좌표 추출에 실패했습니다.", e);
        }
    }

    private BufferedImage getMapImage(double lat, double lng) throws IOException {
        String urlStr = String.format("%s?center=%f,%f&zoom=%d&size=%dx%d&markers=%f,%f&key=%s",
                STATIC_MAP_URL, lat, lng, ZOOM_LEVEL, MAP_WIDTH, MAP_HEIGHT, lat, lng, apiKey);

        URL url = new URL(urlStr);
        return ImageIO.read(url);
    }

    private void displayMap(BufferedImage image, String googleMapLink) {
        JLabel mapLabel = new JLabel(new ImageIcon(image));
        mapLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mapLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openBrowser(googleMapLink);
            }
        });

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(mapLabel, BorderLayout.CENTER);

        // 도움말 텍스트 추가
        JLabel helpLabel = new JLabel("지도를 클릭하면 구글 맵이 열립니다", SwingConstants.CENTER);
        helpLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        helpLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        contentPanel.add(helpLabel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
        pack();

        // MainFrame을 기준으로 중앙에 위치하도록 설정
        setLocationRelativeTo(MainFrame.getInstance());
        setVisible(true);
    }

    private void openBrowser(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            handleError("브라우저를 열 수 없습니다.", e);
        }
    }

    private void handleError(String message, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, message, "오류", JOptionPane.ERROR_MESSAGE);
    }

    // 테스트용 메인 메서드
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MapView("YOUR_PLACE_ID_HERE");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "지도를 표시할 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
