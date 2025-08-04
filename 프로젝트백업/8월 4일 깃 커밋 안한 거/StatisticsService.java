package org.example.service;

import org.example.db.DatabaseUtil;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;


public class StatisticsService {

    public Connection conn;

    // 일요일
    public StatisticsService() {
        try {
            conn = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            System.out.println("DB 연결 실패: " + e.getMessage());
        }
    }

    public String getUserKey_id() {
        String data = "";

        String query = "SELECT user_key, date FROM statistics";

        try (   Connection conn = DatabaseUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query); // try-catch-resource -> 자원 자동 닫기
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                data = rs.getString("user_key");
            }
        } catch (SQLException se2) {
            System.out.println("유저 키 sql 예외 발생 : " + se2);
        }

        return data;
    }

    // 날짜별로 신체 데이터 받아오기

    public Map<String, String> fetchBodyInfo(String userKey, String dateStr) {
        Map<String, String> info = new LinkedHashMap<>();

        String queryStats = """
                SELECT date, weight, muscle_mass, body_fat
                FROM statistics
                WHERE user_key = ?
                  AND date <= ?
                ORDER BY date DESC
                LIMIT 1;
            """;

        String queryUserWeight = """
                SELECT weight_kg
                FROM user
                WHERE user_key = ?;
            """;
        try (
                Connection conn = DatabaseUtil.getConnection();
                PreparedStatement pstmtStats = conn.prepareStatement(queryStats);
                PreparedStatement pstmtUser = conn.prepareStatement(queryUserWeight)
        ) {
            pstmtStats.setString(1, userKey);
            pstmtStats.setString(2, dateStr);
            ResultSet rsStats = pstmtStats.executeQuery();

            if (rsStats.next()) {
                info.put("date", rsStats.getString("date"));
                info.put("weight", rsStats.getString("weight"));
                info.put("body_fat", rsStats.getString("body_fat"));
                info.put("muscle_mass", rsStats.getString("muscle_mass"));
            } else {  // 기록 없을 경우 user 테이블에서 받아올 수 있도록 처리
                pstmtUser.setString(1, userKey);
                ResultSet rsUser = pstmtUser.executeQuery();
                if (rsUser.next()) {
                    info.put("date", "-");
                    info.put("weight", String.valueOf(rsUser.getFloat("weight_kg")));
                    info.put("body_fat", "-");
                    info.put("muscle_mass", "-");
                }
                rsUser.close();
            }

            rsStats.close();

        } catch (SQLException se) {
            System.out.println("신체 정보 SQL 예외 발생: " + se);
        }

        return info;
    }


    public Map<String, Integer> fetchWorkoutCountByDay(String dateStr) {
        Map<String, Integer> mapDay = new LinkedHashMap<>();  // LinkedHashMap<> : 입력한 순서대로 Key 보장
        // 요일까지 나오게 포맷 수정
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd(E)", Locale.KOREAN);

        // 텍스트 블록 """ """
        String query = """
            SELECT DATE(date) AS date_str, SUM(activity_minutes) AS activity_minutes
            FROM statistics
            WHERE date BETWEEN DATE_SUB(STR_TO_DATE(?, '%Y-%m-%d'), INTERVAL DAYOFWEEK(STR_TO_DATE(?, '%Y-%m-%d')) + 1 DAY)
                          AND DATE_ADD(STR_TO_DATE(?, '%Y-%m-%d'), INTERVAL 7 - DAYOFWEEK(STR_TO_DATE(?, '%Y-%m-%d')) DAY)
            GROUP BY date   /* 같은 날짜끼리 묶어서 집계 */
            ORDER BY date ASC
        """;
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            
            for(int i = 1; i <= 4; i++) {  // 일단 여기랑 바로 밑에 for 반복문으로 바꿨는데 i <= 4 조건 검토 필요
                pstmt.setString(i, dateStr);
            }

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Date sqlDate = rs.getDate("date_str");
                    LocalDate localDate = sqlDate.toLocalDate();

                    String day = localDate.format(fullFormatter);
                    int activityMinutes = rs.getInt("activity_minutes");
                    mapDay.put(day, activityMinutes);
                }
            }
        } catch (SQLException se) {
            System.out.println("운동 시간 SQL 예외 : " + se);
        }

        return mapDay;
    }

    public String getLastRecordedDateBefore(String userKey, String dateStr) {
        String query = """
                SELECT MAX(date)
                FROM statistics
                WHERE user_key = ? AND date < ?
                """;
        String lastRecordedDate = null;

        try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userKey);
            pstmt.setString(2, dateStr);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    lastRecordedDate = rs.getString(1); // 컬럼명이 아닌 인덱스명으로도 불러올 수 있네
                }
            }
        } catch (SQLException se) {
            System.out.println("이전 기록 날짜 SQL 예외 : " + se);
        }


        return lastRecordedDate;
    }

    public int totalCalories(String userKey, String dateStr) {
        String query = """
                    SELECT IFNULL(SUM(intaked_calories), 0) AS total_calories
                    FROM user_eaten_food
                    WHERE user_key = ?
                    AND date = STR_TO_DATE(?, '%Y-%m-%d');
                """;  /* sql 쿼리 : 지정일 기준으로 일요일부터 토요일까지의 일주일 데이터 가져와줘
        
        IFNULL 함수 의미 -> NULL값일 경우 0으로 처리해줘

        기준일(?, yyyy-MM-dd)을 날짜로 변환한 뒤, WEEKDAY()로 요일 인덱스를 구함
        WEEKDAY()는 월:0, 화:1, ..., 토:5, 일:6
        따라서 +1을 해서 "일요일" 기준으로 보정
        예: 기준일이 목요일(3) → 3+1 = 4일 전 = 해당 주 일요일
        주간 시작일: (기준일 - n일) → 그 주의 일요일
        
        중간 AND는 BETWEEN ~ AND문
         
        기준일 기준으로 이번 주 토요일 계산
        WEEKDAY()는 월:0, ..., 토:5, 일:6
        예: 기준일이 화요일(1)이라면 → 6 - 1 = 5일 뒤 = 해당 주 토요일


      + 그냥 ? 넣으면 문자열로 들어감   */
        int calories = 0;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userKey);
            pstmt.setString(2, dateStr);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    calories = rs.getInt("total_calories");
                }
            }
        } catch (SQLException se) {
            System.out.println("칼로리 계산 SQL 예외 : " + se.getMessage());  // getMessage() 주석 필요
        }

        return calories;
    }

    public double changeFatMass(String userKey, String dateStr) {
        String query = """
                    SELECT ROUND(ABS(recent.body_fat - past.body_fat), 2) AS difference_fat
                    FROM
                      -- 기준일 이전 최근 측정값
                      (SELECT body_fat
                       FROM statistics
                       WHERE user_key = ?
                         AND date <= STR_TO_DATE(?, '%Y-%m-%d')
                         AND body_fat IS NOT NULL
                       ORDER BY date DESC
                       LIMIT 1) AS recent,
                
                      -- 기준일 -14일 이전 최근 측정값
                      (SELECT body_fat
                       FROM statistics
                       WHERE user_key = ?
                         AND date <= DATE_SUB(STR_TO_DATE(?, '%Y-%m-%d'), INTERVAL 14 DAY)
                         AND body_fat IS NOT NULL
                       ORDER BY date DESC
                       LIMIT 1) AS past;
                
                """;
        // 지난 주 대비 -> 매일 변화하는 양 평균 구해서 저번 주 대비 비교
        // + sql은 두 개의 그룹 함수를 중첩해서 사용할 수 없음!!
        double differenceFat = 0.0;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userKey);
            pstmt.setString(2, dateStr);
            pstmt.setString(3, userKey);
            pstmt.setString(4, dateStr);

            // ? 갯수만큼 넣어줄 것

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    differenceFat = rs.getDouble("difference_fat");
                }
            }
        } catch (SQLException se) {
            System.out.println("체지방 SQL 예외 : " + se);
        }

        return differenceFat;
    }

    public double changeMuscleMass(String userKey, String dateStr) {
        String query = """
                    SELECT ROUND(ABS(recent.muscle_mass - past.muscle_mass), 2) AS difference_mass
                    FROM
                      -- 기준일 이전 최근 측정값
                      (SELECT muscle_mass
                       FROM statistics
                       WHERE user_key = ?
                         AND date <= STR_TO_DATE(?, '%Y-%m-%d')
                         AND muscle_mass IS NOT NULL
                       ORDER BY date DESC
                       LIMIT 1) AS recent,
                
                      -- 기준일 -14일 이전 최근 측정값
                      (SELECT muscle_mass
                       FROM statistics
                       WHERE user_key = ?
                         AND date <= DATE_SUB(STR_TO_DATE(?, '%Y-%m-%d'), INTERVAL 14 DAY)
                         AND muscle_mass IS NOT NULL
                       ORDER BY date DESC
                       LIMIT 1) AS past;
                
                """;
        double differenceMuscle = 0.0;

        // try-catch-resource -> 자원 자동 닫기

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userKey);
            pstmt.setString(2, dateStr);
            pstmt.setString(3, userKey);
            pstmt.setString(4, dateStr);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    differenceMuscle = rs.getDouble("difference_mass");
                }
            }
        } catch (SQLException se) {
            System.out.println("근육량 SQL 예외 : " + se);
        }

        return differenceMuscle;
    }

    public int percentageAccomplishmentCaloriesWeekly(String userKey, String dateStr) {
        dateStr = dateStr.split("\\(")[0].trim();
        LocalDate selectedDate = LocalDate.parse(dateStr);
        Locale locale = Locale.KOREA;
        LocalDate startDate = selectedDate.with(WeekFields.of(locale).dayOfWeek(), 1);
        LocalDate endDate = selectedDate.with(WeekFields.of(locale).dayOfWeek(), 7);

        String query = """
        SELECT 
            ROUND(SUM(COALESCE(uef.intaked_calories, f.food_calories * uef.quantity)) / (us.target_calories * 7) * 100, 0) AS goal_achievement_rate
        FROM user_eaten_food uef
        JOIN food f ON uef.food_id = f.food_id
        JOIN user_survey us ON uef.user_key = us.user_key
        WHERE uef.user_key = ?
          AND uef.date BETWEEN ? AND ?
    """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userKey);
            pstmt.setDate(2, java.sql.Date.valueOf(startDate));
            pstmt.setDate(3, java.sql.Date.valueOf(endDate));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("goal_achievement_rate");
            }
        } catch (SQLException se) {
            System.out.println("주간 총섭취 기준 SQL 예외 : " + se);
        }
        return 0;
    }

    public int percentageAccomplishmentCaloriesDailyAvg(String userKey, String dateStr) {
        dateStr = dateStr.split("\\(")[0].trim();
        LocalDate selectedDate = LocalDate.parse(dateStr);
        Locale locale = Locale.KOREA;
        LocalDate startDate = selectedDate.with(WeekFields.of(locale).dayOfWeek(), 1);
        LocalDate endDate = selectedDate.with(WeekFields.of(locale).dayOfWeek(), 7);

        String query = """
        SELECT 
            ROUND(AVG(COALESCE(uef.intaked_calories, f.food_calories * uef.quantity) / us.target_calories) * 100, 0) AS avg_rate
        FROM user_eaten_food uef
        JOIN food f ON uef.food_id = f.food_id
        JOIN user_survey us ON uef.user_key = us.user_key
        WHERE uef.user_key = ?
          AND uef.date BETWEEN ? AND ?
        GROUP BY uef.date
    """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userKey);
            pstmt.setDate(2, java.sql.Date.valueOf(startDate));
            pstmt.setDate(3, java.sql.Date.valueOf(endDate));
            ResultSet rs = pstmt.executeQuery();

            int sum = 0;
            int count = 0;
            while (rs.next()) {
                sum += rs.getInt("avg_rate");
                count++;
            }

            return count > 0 ? sum / count : 0;

        } catch (SQLException se) {
            System.out.println("일일 평균 방식 SQL 예외 : " + se);
        }
        return 0;
    }


    public int percentageAccomplishmentCalories(String userKey, String dateStr) {
        dateStr = dateStr.split("\\(")[0].trim();
        LocalDate selectedDate = LocalDate.parse(dateStr); // 예: "2025-08-02";  // 또는 사용자가 선택한 날짜
        Locale locale = Locale.KOREA;       // 주 시작 요일: 월요일

        // 주간 시작 (월요일), 종료 (일요일)
        LocalDate startDate = selectedDate.with(WeekFields.of(locale).dayOfWeek(), 1);  // 월요일
        LocalDate endDate = selectedDate.with(WeekFields.of(locale).dayOfWeek(), 7);

        String query = """
                SELECT 
                    ROUND(SUM(COALESCE(uef.intaked_calories, f.food_calories * uef.quantity)) / (us.target_calories * 7) * 100, 0) AS goal_achievement_rate
                FROM user_eaten_food uef
                JOIN food f ON uef.food_id = f.food_id
                JOIN user_survey us ON uef.user_key = us.user_key
                WHERE uef.user_key = ?
                  AND uef.date BETWEEN ? AND ?
            """;
        // sql 쿼리 : 날짜 던져주면 그 날의 달성률 데이터 가져옴.
        int percentage = 0;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userKey);
            pstmt.setDate(2, java.sql.Date.valueOf(startDate));
            pstmt.setDate(3, java.sql.Date.valueOf(endDate));


            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    percentage = rs.getInt("goal_achievement_rate");
                }
            }
        } catch (SQLException se) {
            System.out.println("칼로리 목표 달성률 SQL 예외 : " + se);
        }
        return percentage;
    }

    public Map<String, ArrayList<Object[]>> fetchWeeklyWorkoutGrouped(String userKey, String selectedDate) {
        Map<String, ArrayList<Object[]>> groupedMap = new LinkedHashMap<>();

        String query = """
            SELECT wr.date, e.exercise_name, wr.sets, wr.reps, wr.weight
            FROM workout_record wr
            JOIN exercise e ON wr.exercise_id = e.exercise_id
            WHERE wr.user_key = ?
              AND wr.date BETWEEN DATE_SUB(STR_TO_DATE(?, '%Y-%m-%d'), INTERVAL WEEKDAY(STR_TO_DATE(?, '%Y-%m-%d')) DAY)
                              AND DATE_ADD(STR_TO_DATE(?, '%Y-%m-%d'), INTERVAL (6 - WEEKDAY(STR_TO_DATE(?, '%Y-%m-%d'))) DAY)
            ORDER BY wr.date ASC
        """;

        try (Connection conn =  DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userKey);
            pstmt.setString(2, selectedDate);
            pstmt.setString(3, selectedDate);
            pstmt.setString(4, selectedDate);
            pstmt.setString(5, selectedDate);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String date = rs.getString("date");
                String[] row = new String[]{
                        date,
                        rs.getString("exercise_name"),
                        String.valueOf(rs.getInt("sets")),
                        String.valueOf(rs.getInt("reps")),
                        String.valueOf(rs.getInt("weight"))
                };

                groupedMap.computeIfAbsent(date, k -> new ArrayList<>()).add(row);
            }
        } catch (SQLException se) {
            System.out.println("이번 주 운동 기록 SQL 예외 : " + se);
        }

        System.out.println("[쿼리 요청 날짜] " + selectedDate);
        System.out.println("[userKey] " + userKey);
        System.out.println("[groupedMap size] " + groupedMap.size());

        return groupedMap;
    }

    public int fetchWeeklyGoalAchievement(String userKey, LocalDate startDate, LocalDate endDate) {
        String query = """
            SELECT
                ROUND(SUM(f.intaked_calories) / (s.target_calories * 7) * 100, 0) AS rate
            FROM user_eaten_food f
            JOIN user_survey s ON f.user_key = s.user_key
            WHERE f.user_key = ?
            AND f.date BETWEEN ? AND ?;
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userKey);
            pstmt.setDate(2, java.sql.Date.valueOf(startDate));
            pstmt.setDate(3, java.sql.Date.valueOf(endDate));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("rate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}

