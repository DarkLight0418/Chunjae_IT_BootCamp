package jdbc.day01_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

class C {
    Scanner sc = new Scanner(System.in);
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    Connection con;
    PreparedStatement pstmt;
    String sql = sc.nextLine();


    C() {
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
            pstmt = con.prepareStatement(sql);
        } catch (ClassNotFoundException ce) {
            System.out.println("오류 내용 ce : " + ce);
        } catch (SQLException se) {
            System.out.println("오류 내용 se : " + se);
        }
    }

    public static void main(String[] args) {
        C c = new C();
    }
}
