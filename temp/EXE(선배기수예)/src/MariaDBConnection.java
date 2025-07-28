package main.java.models;

import java.sql.*;

public class MariaDBConnection {
    // 데이터베이스 연결 상수
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/bobmate_db";
    private static final String USER = "bobmate";
    private static final String PASSWORD = "1234";

    private static MariaDBConnection instance;
    private Connection connection;

    private MariaDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static synchronized MariaDBConnection getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new MariaDBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.err.println("데이터베이스 연결 실패: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

}
