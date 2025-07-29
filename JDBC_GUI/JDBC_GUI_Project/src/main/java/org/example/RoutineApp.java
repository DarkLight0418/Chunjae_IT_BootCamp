package org.example;

import gui.day01.D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RoutineApp extends JFrame {

    private DonutChart chart;
    private Timer timer;
    private int totalSeconds = 30; // 예: 30초 휴식 타이머
    private int elapsedMillis = 0;

    ArrayList<String> day = new ArrayList<>();

    public RoutineApp() {
        plusDay();

        setTitle("Routine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 780);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== 상단 헤더 =====
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(24, 24, 28));
        JLabel profile = new JLabel("👤");
        JLabel title = new JLabel("ROUTINE");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(profile);
        header.add(Box.createHorizontalStrut(8));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // ===== 중앙 메인 카드 =====
        JPanel card = new JPanel();
        card.setBackground(new Color(18, 18, 22));
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 타이틀 영역
        JPanel titleBox = new JPanel();
        titleBox.setOpaque(false);
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        JLabel today = new JLabel("TODAY'S ROUTINE");
        today.setForeground(Color.WHITE);
        today.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JLabel sub = new JLabel("5 X 5 SUPPER SET");
        sub.setForeground(new Color(255, 0, 127));
        sub.setFont(new Font("SansSerif", Font.BOLD, 22));

        JLabel date = null;

        for(int i = 0; i < 7; i++) {
            date = new JLabel("2025-07-27 (" + day.get(i) + "요일)");
        }

        date.setForeground(Color.GRAY);
        titleBox.add(today);
        titleBox.add(Box.createVerticalStrut(4));
        titleBox.add(sub);
        titleBox.add(Box.createVerticalStrut(4));
        titleBox.add(date);

        card.add(titleBox, BorderLayout.NORTH);

        // 도넛 + 좌/우 정보 패널
        JPanel centerBox = new JPanel(new BorderLayout());
        centerBox.setOpaque(false);

        // 좌측 정보
        JPanel leftInfo = new JPanel();
        leftInfo.setOpaque(false);
        leftInfo.setLayout(new BoxLayout(leftInfo, BoxLayout.Y_AXIS));
        addInfo(leftInfo, "NO", "4");
        addInfo(leftInfo, "SET", "3");

        // 도넛
        chart = new DonutChart(0);
        chart.setBackground(new Color(18, 18, 22));

        // 우측 정보
        JPanel rightInfo = new JPanel();
        rightInfo.setOpaque(false);
        rightInfo.setLayout(new BoxLayout(rightInfo, BoxLayout.Y_AXIS));
        addInfo(rightInfo, "REST TIME", String.valueOf(totalSeconds));
        addInfo(rightInfo, "SET", "4");

        centerBox.add(leftInfo, BorderLayout.WEST);
        centerBox.add(chart, BorderLayout.CENTER);
        centerBox.add(rightInfo, BorderLayout.EAST);

        card.add(centerBox, BorderLayout.CENTER);

        // 운동명/세트 정보 + 버튼
        JPanel bottomBox = new JPanel();
        bottomBox.setOpaque(false);
        bottomBox.setLayout(new BoxLayout(bottomBox, BoxLayout.Y_AXIS));

        JPanel benchRow = new JPanel(new GridLayout(1, 4, 8, 0));
        benchRow.setOpaque(false);
        JLabel name = new JLabel("Bench PRESS");
        name.setForeground(Color.WHITE);
        JLabel setLabel = new JLabel("SET");
        setLabel.setForeground(Color.WHITE);
        JLabel setNo = new JLabel("4");
        setNo.setForeground(Color.WHITE);
        JLabel weight = new JLabel("60");
        weight.setForeground(Color.WHITE);
        benchRow.add(name);
        benchRow.add(setLabel);
        benchRow.add(setNo);
        benchRow.add(weight);

        bottomBox.add(benchRow);
        bottomBox.add(Box.createVerticalStrut(20));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttons.setOpaque(false);
        JButton startBtn = new JButton("START");
        stylePinkButton(startBtn);

        JButton stopBtn = new JButton("STOP");
        styleDarkButton(stopBtn);

        buttons.add(startBtn);
        buttons.add(stopBtn);
        bottomBox.add(buttons);

        // 버튼 동작
        startBtn.addActionListener(e -> startTimer());
        stopBtn.addActionListener(e -> stopTimer());

        card.add(bottomBox, BorderLayout.SOUTH);

        add(card, BorderLayout.CENTER);

        // ===== 하단 네비게이션 =====
        JPanel nav = new JPanel(new GridLayout(1, 5));
        nav.setBackground(new Color(24, 24, 28));
        nav.setPreferredSize(new Dimension(getWidth(), 40));

        nav.add(navItem("🏠"));
        nav.add(navItem("📋"));
        nav.add(navItem("📊"));
        nav.add(navItem("🥗"));
        nav.add(navItem("💡"));

        add(nav, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void plusDay() {
        day.clear();
        String[] days = {"월", "화", "수", "목", "금", "토", "일"};
        for (String d : days) {
            day.add(d);
        }
    }
    // startTimer 메소드랑 stopTimer 메소드 기능으로 따로 뺄 것
    
    private void startTimer() {
        stopTimer();
        elapsedMillis = 0;
        timer = new Timer(100, e -> {
            elapsedMillis += 25;
            int progress = (int) (elapsedMillis / (totalSeconds * 1000.0) * 100);
            chart.setProgress(Math.min(progress, 100));
            if (elapsedMillis >= totalSeconds * 1000) {
                ((Timer) e.getSource()).stop();
            }
        });

        // 람다식 사용 -> timer
        timer.start();
    }

    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    // 확인(기능 여기까지0

    private JPanel navItem(String icon) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel i = new JLabel(icon, SwingConstants.CENTER);
        i.setFont(new Font("SansSerif", Font.PLAIN, 28));
        i.setAlignmentX(Component.CENTER_ALIGNMENT);

//        JLabel t = new JLabel(text, SwingConstants.CENTER);
//        t.setAlignmentX(Component.CENTER_ALIGNMENT);
//        t.setForeground(text.equals("ROUTINE") ? new Color(255, 0, 127) : Color.LIGHT_GRAY);
        p.add(i);
//        p.add(t);
        return p;
    }

    private void addInfo(JPanel parent, String title, String value) {
        JLabel t = new JLabel(title);
        t.setForeground(Color.LIGHT_GRAY);
        t.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JLabel v = new JLabel(value);
        v.setForeground(Color.WHITE);
        v.setFont(new Font("SansSerif", Font.BOLD, 18));
        parent.add(t);
        parent.add(v);
        parent.add(Box.createVerticalStrut(16));
    }

    private void stylePinkButton(JButton btn) {
        btn.setBackground(new Color(255, 0, 127));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
    }

    private void styleDarkButton(JButton btn) {
        btn.setBackground(new Color(60, 60, 65));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RoutineApp::new);
    }
}

/**
 * 도넛(원형 진행도) 컴포넌트
 */
class DonutChart extends JPanel {
    private int progress = 0; // 0~100

    public DonutChart(int progress) {
        this.progress = progress;
        setPreferredSize(new Dimension(220, 220));
        setOpaque(false);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // 안티앨리어싱
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight());
        int thickness = 28;

        int x = (getWidth() - size) / 2 + thickness / 2;
        int y = (getHeight() - size) / 2 + thickness / 2;
        int diameter = size - thickness;

        // 배경 원
        g2.setColor(new Color(60, 60, 65));
        g2.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawArc(x, y, diameter, diameter, 0, 360);

        // 진행 원
        g2.setColor(new Color(255, 0, 127));
        int angle = (int) (360 * (progress / 100.0));
        g2.drawArc(x, y, diameter, diameter, 90, -angle);

        // 가운데 숫자
        g2.setFont(new Font("SansSerif", Font.BOLD, 40));
        String text = String.valueOf(progress);
        FontMetrics fm = g2.getFontMetrics();
        int tx = (getWidth() - fm.stringWidth(text)) / 2;
        int ty = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
        g2.setColor(Color.WHITE);
        g2.drawString(text, tx, ty);
    }
}
