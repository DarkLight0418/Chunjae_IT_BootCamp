package jdbc.day01_assignment;

import JAE.P;
import java.sql.*;

class power {
    String dbms = "마리아";
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/power_schema";
    Connection con;
    Statement stmt;
    power() {
        try {
            Class.forName(maria);
            P.pln("(1) "+dbms+" 드라이버 로딩 성공");
            con = DriverManager.getConnection(mariaUrl, "power", "6172");
            P.pln("(2) "+dbms+" 연결 성공 con: " + con);
        }catch (ClassNotFoundException ce) {
            P.pln("(1) "+dbms+" 드라이버 로딩 실패 : " + ce);
        }catch (SQLException se) {
            P.pln("(2) "+dbms+" 연결 실패");
        }
    }

    String tname = "유저 정보";
    void createT() {
        String sql = "create table if not exists "+tname+"(No int primary key, NAME varchar(15), RDATE datetime)";
        try {
            stmt.execute(sql);
            P.pln("테이블 생성 성공");
        } catch (SQLException se) {
            P.pln("테이블 생성 실패: " + se);
        }
    }
    public static void main(String args[]) {
        new power().createT();
    }
}