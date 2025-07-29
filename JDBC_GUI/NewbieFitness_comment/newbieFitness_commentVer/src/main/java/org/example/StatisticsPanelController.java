package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * 통계 화면(Statistics) 패널을 구성/표시하는 컨트롤러 클래스.
 * - 외부에서 전달받은 JPanel 위에 제목, 정보 영역, 원형 도형(운동 목표 달성률 표시용)을 배치합니다.
 * - Swing 레이아웃과 페인팅 흐름을 학습하기 좋은 간단한 예제 구조입니다.
 *
 * ⚠️ Swing은 이벤트 디스패치 스레드(EDT)에서 UI 변경을 수행해야 안전합니다.
 *    statistics_show(...)를 호출할 때 가능하면 SwingUtilities.invokeLater(...) 안에서 호출하세요.
 */
public class StatisticsPanelController {

    /**
     * 전달받은 panel의 내용을 지우고, 통계 화면을 새로 구성하여 보여줍니다.
     * @param panel 화면을 구성할 타깃 컨테이너(JPanel)
     */
    public static void statistics_show(JPanel panel) {
        // 기존에 panel에 붙어 있던 모든 컴포넌트를 제거합니다.
        // (탭 전환, 화면 갱신 등에서 이전 UI를 깨끗하게 치우기 위해 사용)
        panel.removeAll();

        // 최상위 컨테이너(panel)의 레이아웃을 BorderLayout으로 설정합니다.
        // BorderLayout은 NORTH(위), SOUTH(아래), WEST(왼), EAST(오), CENTER(가운데) 5영역 배치가 가능합니다.
        // (기존 flowLayout에서 수정)
        panel.setLayout(new BorderLayout());

        // 화면 상단 제목 라벨 생성
        JLabel label = new JLabel("Statistics");
        label.setForeground(Color.WHITE);                         // 글자색: 흰색
        label.setFont(new Font("Malgun Gothic", Font.BOLD, 24));  // 폰트: 맑은 고딕, 굵게, 24pt
        label.setAlignmentX(Component.CENTER_ALIGNMENT);          // ※ BoxLayout에서 수평 정렬에 영향. (BorderLayout에선 의미 없음)
        label.setHorizontalAlignment(SwingConstants.CENTER);      // 라벨 내부 텍스트 가로 정렬: 가운데

        // BorderLayout의 NORTH 영역(상단)에 제목 라벨 추가
        panel.add(label, BorderLayout.NORTH);

        // 가운데 영역에 들어갈 컨테이너(세로로 요소를 쌓기 위해 BoxLayout 사용)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));  // Y축(세로) 방향으로 컴포넌트 배치
        centerPanel.setBackground(Color.BLACK);                                // 배경색: 검정

        // ──────────────────────────────────────────────────────────────
        // 1) 신체/근력 정보 패널
        //    - 실제로는 왼쪽에 신체 정보, 오른쪽에 근력 정보 등을 배치하려는 의도로 보입니다.
        //    - GridLayout(1, 2)은 1행 2열 레이아웃이므로 컴포넌트를 2개 채워 넣는 것이 일반적입니다.
        //      현재는 JLabel 하나만 추가되어 오른쪽 칸은 비게 됩니다(추후 확장 예정 지점).
        // ──────────────────────────────────────────────────────────────
        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        // BoxLayout(Y_AXIS) 하에서 최대 크기를 제한하면, 레이아웃 계산 시 이 폭/높이를 넘지 않도록 노력합니다.
        // 화면이 넓더라도 infoPanel이 300x300을 넘지 않을 수 있음에 유의하세요(디자인 의도에 맞게 값 조정).
        infoPanel.setMaximumSize(new Dimension(300, 300));
        infoPanel.setBackground(Color.DARK_GRAY);  // 배경색: 어두운 회색

        // 신체 정보 라벨(실제 앱에선 내용 채워 넣기)
        JLabel bodyInfo = new JLabel("신체 정보: ");
        bodyInfo.setForeground(Color.WHITE);
        bodyInfo.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));

        // 첫 번째 칸에 라벨 추가(두 번째 칸은 비어 있음 → 추후 근력 정보 등 추가)
        infoPanel.add(bodyInfo);

        // ──────────────────────────────────────────────────────────────
        // 2) 원형 도형을 그리는 커스텀 패널
        //    - 운동 목표 달성률을 시각적으로 보여줄 자리(지금은 원과 텍스트만 표시).
        // ──────────────────────────────────────────────────────────────
        DrawCircle dc = new DrawCircle();
        // BoxLayout(Y_AXIS)에서 수평 정렬을 중앙으로 맞춥니다.
        // (BoxLayout에서만 의미 있음)
        dc.setAlignmentX(Component.CENTER_ALIGNMENT);

        // centerPanel에 간격(세로 여백)과 구성 요소들을 차례대로 추가
        centerPanel.add(Box.createVerticalStrut(10));  // 위쪽 여백 10px
        centerPanel.add(infoPanel);                    // 신체/근력 정보 영역
        centerPanel.add(Box.createVerticalStrut(15));  // 정보와 원형 사이 여백 15px
        centerPanel.add(dc);                           // 원형 커스텀 패널

        // 완성된 centerPanel을 BorderLayout의 CENTER에 배치
        panel.add(centerPanel, BorderLayout.CENTER);

        // 이 패널을 보이도록 설정(일반적으로 상위 프레임/탭이 관리하므로 없어도 되는 경우가 많음)
        panel.setVisible(true);

        // 레이아웃 재계산(컴포넌트 추가/제거 후 필수) 및 다시 그리기 요청
        panel.revalidate();  // 레이아웃 매니저에게 "배치 다시 계산해!"라고 알림
        panel.repaint();     // 화면을 다시 그려달라고 요청
    }
}

/**
 * 원과 텍스트(“운동 목표 달성률”)를 그려주는 커스텀 패널.
 * - paintComponent(...)를 오버라이드하여 사용자 정의 그리기를 수행합니다.
 * - 현재는 단순한 윤곽 원과 중앙 텍스트만 표시합니다.
 */
class DrawCircle extends JPanel {

    public DrawCircle() {
        // 선호(권장) 크기 설정: 레이아웃 매니저가 이 크기를 참고하여 배치합니다.
        // 실제 표시 크기는 레이아웃/상황에 따라 달라질 수 있습니다.
        setPreferredSize(new Dimension(200, 200));

        // 배경색을 검정으로. 기본적으로 JPanel은 불투명(true)이며 setBackground로 색이 적용됩니다.
        setBackground(Color.BLACK);
    }

    /**
     * 컴포넌트가 화면에 그려질 때 자동 호출되는 메서드.
     * - 절대 직접 호출하지 말고, repaint()를 호출하여 간접적으로 요청하세요.
     * - 항상 super.paintComponent(g)를 먼저 호출해 이전 프레임의 잔상이 남지 않게 합니다.
     */
    @Override
    public void paintComponent(Graphics g) {
        // 부모 클래스의 그리기 로직을 먼저 수행(배경 지우기 등 필수)
        super.paintComponent(g);

        // ──────────────────────────────────────────────────────────────
        // (1) 원 그리기
        // ──────────────────────────────────────────────────────────────
        int diameter = 150;                       // 원 지름(고정값). 화면 크기에 맞추려면 동적으로 계산하는 방식도 있음(아래 보충 설명 참조).
        int x = (getWidth() - diameter) / 2;      // 패널 중앙 정렬을 위해 x 좌표를 계산
        int y = (getHeight() - diameter) / 2;     // 패널 중앙 정렬을 위해 y 좌표를 계산

        g.setColor(Color.white);                  // 선색: 흰색(대문자 Color.WHITE 권장)
        g.drawOval(x, y, diameter, diameter);     // (x, y)에서 시작하는 지름 크기의 원(실제로는 타원)을 그립니다.

        // ──────────────────────────────────────────────────────────────
        // (2) 중앙 텍스트 그리기: "운동 목표 달성률"
        // ──────────────────────────────────────────────────────────────
        String text = "운동 목표 달성률";
        Font font = new Font("Malgun Gothic", Font.BOLD, 18);  // 폰트 객체 생성
        g.setFont(font);

        // 현재 Graphics 컨텍스트에 설정된 폰트 정보로 텍스트 치수(폭/높이 등)를 얻습니다.
        FontMetrics fm = g.getFontMetrics(font);
        int textWidth = fm.stringWidth(text);     // 텍스트의 픽셀 단위 가로 길이
        int textHeight = fm.getAscent();          // 텍스트 높이(베이스라인 위쪽 높이)

        // 원의 중심 좌표 계산
        int centerX = x + diameter / 2;
        int centerY = y + diameter / 2;

        // 텍스트를 원의 중심에 맞추기 위한 좌표 계산
        int textX = centerX - (textWidth / 2);    // 텍스트 중앙이 원 중심에 오도록 X를 왼쪽으로 절반만큼 이동
        int textY = centerY - (textHeight / 4);   // Y는 베이스라인 기준이라 약간의 보정(- textHeight/4)을 적용

        // 계산된 위치에 텍스트 그리기
        g.drawString(text, textX, textY);
    }
}
