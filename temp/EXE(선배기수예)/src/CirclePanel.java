package main.java.views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.Ellipse2D;
import java.awt.RenderingHints;


public class CirclePanel extends JPanel {
    private BufferedImage image;
    private String imagePath;

    private static final String DEFAULT_IMAGE_PATH = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "images" + File.separator + "default.jpg";

    public CirclePanel(String imagePath) {
        this.imagePath = imagePath;
        setPreferredSize(new Dimension(150, 150));
        setMinimumSize(new Dimension(150, 150));
        setMaximumSize(new Dimension(150, 150));
        loadImage(imagePath);
        setOpaque(false);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        loadImage(imagePath);
    }

    private void loadImage(String imagePath) {
        try {
            if (imagePath == null || imagePath.isEmpty() || imagePath.equals("default.jpg")) {
                imagePath = DEFAULT_IMAGE_PATH;
            }

            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                imageFile = new File(DEFAULT_IMAGE_PATH);
                if (!imageFile.exists()) {
                    System.err.println("기본 이미지를 찾을 수 없습니다: " + DEFAULT_IMAGE_PATH);
                    return;
                }
            }

            // 원본 이미지 로드
            BufferedImage originalImage = ImageIO.read(imageFile);
            if (originalImage == null) {
                System.err.println("이미지를 로드할 수 없습니다: " + imagePath);
                return;
            }

            // 원 안에 맞도록 이미지 크기 조정
            int targetSize = 150;  // 패널 크기
            double originalWidth = originalImage.getWidth();
            double originalHeight = originalImage.getHeight();
            double scale;
            int newWidth, newHeight;

            if (originalWidth > originalHeight) {
                scale = targetSize / originalHeight;
                newWidth = (int)(originalWidth * scale);
                newHeight = targetSize;
            } else {
                scale = targetSize / originalWidth;
                newWidth = targetSize;
                newHeight = (int)(originalHeight * scale);
            }

            // 비율 유지하면서 크기 조정
            this.image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = this.image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2.dispose();

            repaint();
        } catch (IOException e) {
            System.err.println("이미지 로드 중 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight());
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        // 원형 클리핑 영역 생성
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, size, size);
        g2d.setClip(circle);

        // 배경 그리기
        g2d.setColor(Color.WHITE);
        g2d.fill(circle);

        // 이미지 그리기 - 중앙 정렬
        if (image != null) {
            int imageX = x + (size - image.getWidth()) / 2;
            int imageY = y + (size - image.getHeight()) / 2;
            g2d.drawImage(image, imageX, imageY, null);
        }

        // 테두리 그리기
        g2d.setClip(null);
        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(circle);

        g2d.dispose();
    }

    public String getImagePath() {
        return imagePath;
    }
}