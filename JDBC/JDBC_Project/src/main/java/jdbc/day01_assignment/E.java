package jdbc.day01_assignment;

import java.sql.*;

class E {
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    Connection con;
    Statement stmt;
    String sql = "select * from JDBCT order by NO desc";

    E() {
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
            stmt = con.createStatement();
        } catch (ClassNotFoundException ce) {
            System.out.println("로딩 실패 " + ce);
        } catch (SQLException se) {
            System.out.println("연결 실패 " + se);
        }

        createRs();
    }

    void createRs() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);

            forward(rs);
            rs.beforeFirst();
            forward(rs);
        } catch (SQLException se) {
        }
    }

    void forward(ResultSet rs) {
        System.out.println("<순방향>");
        try {
            while(rs.next()) {
                int no = rs.getInt(1);
                String name = rs.getString(2);
                Date rdate = rs.getDate(3);
                Time rtime = rs.getTime(3);
                System.out.println(no + "\t" + name + "\t" + rdate + "\t" + rtime + "\t");
            }
        } catch (SQLException se) {}
    }

    void backward(ResultSet rs) {
        System.out.println("<역방향>");
        try {
            while(rs.previous()) {
                int no = rs.getInt(1);
                String name = rs.getString(2);
                Date rdate = rs.getDate(3);
                Time rtime = rs.getTime(3);
                System.out.println(no + "\t" + name +"\t" + rdate + "\t" + rtime + "\t");
            }
        } catch (SQLException se) {}
    }

    public static void main(String[] args) {
        new E();
    }
}
