public class WeeklyChartPanel extends JPanel {
    StatisticsService jdbcStatWeekDayCounts = new StatisticsService();

    public WeeklyChartPanel(String dateStr) {
        setLayout(new BorderLayout());


        // 데이터셋 생성(요일 및 운동 횟수)
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Integer> dataDayTimeCounts = jdbcStatWeekDayCounts.fetchWorkoutCountByDay(dateStr);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd(E)", Locale.KOREAN);
        DateTimeFormatter labelFormat = DateTimeFormatter.ofPattern("M/d");  // X축 레이블

        for (String d : getWeekDates(dateStr)) {
            LocalDate ld = LocalDate.parse(d, formatter);
            String label = ld.format(labelFormat);

            int count = dataDayTimeCounts.getOrDefault(d, 0);

            dataset.addValue(count, "운동 시간", label);
        }

        // 꺾은선 차트
        JFreeChart lineChart = ChartFactory.createLineChart(
                "날짜별 운동 시간",
                "날짜",  // X축 레이블
                "활동 시간(분)",  // Y축 레이블
                dataset,  // 그래프에 들어갈 데이터셋
                PlotOrientation.VERTICAL,  // 차트 방향 (세로 꺾은선)
                false, // 범례 표시 여부
                true,  // 툴팁 표시 여부(마우스 올릴 시 값 설명)
                false  // URL 링크 여부
        );

        lineChart.setBackgroundPaint(new Color(185, 228, 255));  // 차트 배경색

        ChartPanel chartPn = new ChartPanel(lineChart);
        chartPn.setPreferredSize(new Dimension(350, 200));
        chartPn.setBackground(Color.WHITE);  // 차트 패널 안쪽 색
        chartPn.setMaximumSize(new Dimension(350, 350));

        Font font = new Font("Malgun Gothic", Font.PLAIN, 15);
//        add(Box.createVerticalStrut(10));

        CategoryPlot plot = lineChart.getCategoryPlot();

        plot.setInsets(new RectangleInsets(10, 10, 10, 10));  // 안쪽 여백 확보

        plot.setBackgroundPaint(Color.WHITE);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        plot.getDomainAxis().setLabelFont(font);  // x축 레이블
        plot.getDomainAxis().setTickLabelFont(font);  // x축 눈금
        plot.getRangeAxis().setLabelFont(font);  // y축 레이블
        plot.getRangeAxis().setTickLabelFont(font);  // y축 눈금

        lineChart.getTitle().setFont(new Font("Malgun Gothic", Font.BOLD, 14));

        add(chartPn, BorderLayout.CENTER);
    }

    public static ArrayList<String> getWeekDates(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd(E)", Locale.KOREAN);
        LocalDate baseDate = LocalDate.parse(date, formatter);

        // 해당 주의 월요일 구하기
        LocalDate startOfWeek = baseDate.minusDays(baseDate.getDayOfWeek().getValue() % 7);

        ArrayList<String> weekDates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weekDates.add(startOfWeek.plusDays(i).format(formatter));
        }
        return weekDates;
    }
}