package jdbc.day01_assignment;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

class A {
    String maria = "org.mariadb.jdbc.Driver";
    String mariaURL = "jdbc:mariadb://127.0.0.1:3306/java_schema";

    Connection con;

    A() {
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaURL, "scott", "tiger");
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException ce) {
            System.out.println("실패 :" + ce);
        } catch (SQLException se) {
            System.out.println("연결 실패 :" + se);
        }
    }

    public static void main(String[] args) {
        new A();
    }
}

class A2 {
    String dbms = "마리아";
    String maria = "org.mariadb.jdbc.Driver";

    String mariaURL = "jdbc:mariadb://127.0.0.1:3306/java_schema";

    Connection con;

    A2() {
        try {
            Class.forName(maria);
            System.out.println(dbms + "드라이버 로딩 성공");
            con = DriverManager.getConnection(mariaURL, "scott", "tiger");
            System.out.println(dbms + "연결 성공 con : " + con);
        } catch (ClassNotFoundException ce) {
            System.out.println(dbms + "드라이버 로딩 실패 : " + ce);
        } catch (SQLException se) {
            System.out.println(dbms + "연결 실패");
        }
    }
    public static void main(String[] args) {
        new A2();
    }
}
