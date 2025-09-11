package MySqlClient;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientMain {
//    Scanner sc = new Scanner(System.in);
    InputStream is = System.in;
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);

    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    Connection con;
    PreparedStatement pstmt;
    String tname = "JDBCT";
    String id, pw;

    ClientMain() {
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaUrl, id, pw);
        }catch(ClassNotFoundException ce) {
            System.out.println(ce);
        }catch (SQLException se) {
            System.out.println(se);
        }
    }

    public static void main(String[] args) {
        new ClientMain();
    }
}
