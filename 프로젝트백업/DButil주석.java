package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtil 클래스는 데이터베이스 연결(Connection)을 관리하는 유틸리티 클래스입니다.
 *
 * 주요 역할:
 * - JDBC 드라이버 로딩
 * - 데이터베이스 연결 생성 및 반환
 * - 데이터베이스 연결 종료(닫기) 기능 제공
 *
 * 이 클래스를 통해 여러 곳에서 반복되는 DB 연결 코드를 줄이고,
 * DB 연결 관리의 일관성과 재사용성을 높일 수 있습니다.
 *
 * 사용 시 주의사항:
 * - DB 연결 정보를 정확히 설정해야 하며,
 * - 연결이 끝난 후 반드시 closeConnection() 메서드를 호출하여 연결을 닫아야 합니다.
 */
public class DBUtil {

    // JDBC 드라이버 클래스 이름 (MySQL 8.x 기준)
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // 데이터베이스 접속 URL (포트, DB명, 문자셋, 타임존 등 포함)
    private static final String URL = "jdbc:mysql://localhost:3306/newbiehealth?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";

    // DB 접속 사용자명
    private static final String USER = "root";

    // DB 접속 비밀번호
    private static final String PASSWORD = "root";

    // DB 연결 객체를 저장하는 변수
    private static Connection conn;

    /**
     * DB 연결을 생성하여 반환합니다.
     *
     * 1) JDBC 드라이버 클래스를 로딩합니다. (클래스가 메모리에 로딩되어야 DriverManager가 인식합니다.)
     * 2) DriverManager를 통해 URL, 사용자명, 비밀번호로 DB 연결 객체를 생성합니다.
     * 3) 연결 객체를 저장하고 반환합니다.
     *
     * 예외 발생 시 SQLException 또는 ClassNotFoundException이 던져질 수 있으므로 호출 시 예외 처리가 필요합니다.
     *
     * @return DB 연결 객체(Connection)
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn == null || conn.isClosed()) {
            // JDBC 드라이버 클래스 로딩
            Class.forName(DRIVER);

            // DB 연결 생성
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conn;
    }

    /**
     * DB 연결을 닫는 메서드입니다.
     *
     * 연결 객체가 null이 아니고 닫혀있지 않은 경우에만 close()를 호출합니다.
     * 연결 종료 후 null로 초기화하여 재사용 시 다시 연결을 생성하게 합니다.
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
            conn = null;
        }
    }
}
