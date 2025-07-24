package jdbc.day01;

import JAE.P;

import java.sql.*;

class A {
    String dbms = "오라클";
    String dbms2 = "마리아";
    String oracle = "oracle.jdbc.driver.OracleDriver";
    String maria = "org.mariadb.jdbc.Driver";

    String mariaURL = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    String oracleURL = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";

    Connection con;

    A() {
        try {
            Class.forName(oracle);
            Class.forName(maria);
            System.out.println(dbms + " 드라이버 로딩 성공");
            System.out.println(dbms2 + " 드라이버 로딩 성공");
//            con = DriverManager.getConnection(mariaURL, "scott", "tiger");
            con = DriverManager.getConnection(oracleURL, "scott", "tiger");
            P.pln(dbms + " 연결 성공 con : " + con);
        } catch (ClassNotFoundException ce) {
            System.out.println(dbms + " 드라이버 로딩 실패 : " + ce);
        } catch (SQLException se) {
            P.pln(dbms + " " +
                    "연결 실패");
        }
    }

    public static void main(String[] args) {
        new A();
    }
}
