package jdbc.day01_assignment;

import java.sql.*;

class F {
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    Connection con;
    Statement stmt;
    String sql = "select * from EMP";
    F() {
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
            stmt = con.createStatement();
        } catch (ClassNotFoundException ce) {
            System.out.println("로딩 실패 : " + ce);
        } catch (SQLException se) {
            System.out.println("연결 실패 : " + se);
        }

        createRs();
    }

    void createRs() {
        ResultSet rs = null;
        try{
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            for(int i=1; i<=cc; i++) {
                String cn = rsmd.getColumnName(i);
                System.out.println(cn + "\t");
            }
            System.out.println("");

            for(int i=1; i<=cc; i++) {
                System.out.print("---------");
            }
            System.out.println("");

            int j = 0;
            while(rs.next()) {
                for(int i=1; i<=cc; i++) {
                    String data = rs.getString(i);
                    System.out.print(data + " \t");
                }
                j++;
                System.out.println("");
            }
            for(int i=1; i<=cc; i++) {
                System.out.print("----------");
            }
            System.out.println("");

            System.out.println("총 "+j+"개의 row검색 됨");

        } catch (SQLException se) {
            System.out.println("예외 발생 " + se);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void main(String[] args) {
        new F();
    }
}

class F2 {
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    Connection con;
    Statement stmt;
    String sql = "select * from EMP";
    F2() {
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
            stmt = con.createStatement();
        } catch (ClassNotFoundException ce) {
            System.out.println("드라이버 로딩 실패 " + ce);
        } catch (SQLException se) {
            System.out.println("연결 실패 : " + se);
        }

        createRs();
    }

    void createRs() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            for(int i=1; i<=cc; i++) {
                String cn = rsmd.getColumnName(i);
                System.out.print
                        (cn+"\n");
            }
            System.out.println("");

            for(int i = 1; i<=cc; i++) {
                System.out.print("==========");
            }
            System.out.println("");

            int j = 0;
            while(rs.next()) {
                for(int i=1; i<=cc; i++) {
                    String data = rs.getString(i);
                    System.out.print(data + " \t");
                }
                j++;
                System.out.println("");
            }
        } catch (SQLException se) {
        } finally {
            try {
                rs.close();
            } catch (SQLException se) {}
        }
    }
    public static void main(String[] args) {
        new F2();
    }
}