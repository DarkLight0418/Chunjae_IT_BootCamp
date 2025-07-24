package jdbc.day01_assignment;

import java.sql.*;

class B {
    String maria = "org.mariadb.jdbc.Driver";
    String mariaURL = "jdbc:mariadb://127.0.0.1:3306/java_schema";

    Connection con;
    Statement stmt;

    B() {
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaURL, "scott", "tiger");
            stmt = con.createStatement();

        } catch (ClassNotFoundException ce) {
            System.out.println("실패 :" + ce);
        } catch (SQLException se) {
            System.out.println("연결 실패 :" + se);
        }
        createT();
        closeAll();
    }
    String tname = "JDBCT";
    void createT() {
        String sql = "create table if not exists " + tname + "(NO int primary key, NAME varchar(10), RDATE datetime)";

        try {
            System.out.println("테이블 생성 성공");
            stmt.execute(sql);
        } catch (SQLException se) {
            System.out.println("연결 실패");
        }
    }
    void dropT() {
        String sql = "drop table if exists " + tname;
        try {
            stmt.execute(sql);
            System.out.println("테이블 삭제 성공");
        } catch (SQLException se) {
            System.out.println("테이블 삭제 실패 : " + se);
        }
    }

    void insertD(int no, String name) {
        String sql = "insert into " + tname + " values(" + no +",'"+name+"', now())";
        try {
            int i = stmt.executeUpdate(sql);
            if(i>0) {
                System.out.println(i + "개의 row 입력 성공");
            } else {
                System.out.println("입력 실패");
            }
        } catch (SQLException se) {
            System.out.println("입력 실패: " + se);
        }
    }

    void selectD() {
        String sql = "select * from " + tname + " order by NO desc";
        ResultSet rs = null;
    }

    void closeAll() {
        try {
            stmt.close();
            con.close();
            System.out.println("객체들 닫기 성공");
        } catch (SQLException se) {
        }
    }

    public static void main(String[] args) {
        new B();
    }
}
