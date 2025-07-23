package jdbc.day01;

import JAE.P;
import java.sql.*;

public class F {
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    Connection con;
    Statement stmt;
    String sql = "select * from EMP";
    F(){
        try{
            Class.forName(maria);
            con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
            stmt = con.createStatement();
        } catch (ClassNotFoundException ce) {
            P.pln("드라이버로딩 실패");
        } catch (SQLException se) {
            P.pln("연결 실패");
        }

        createRs();
    }
    void createRs(){
        ResultSet rs = null;
        try{
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            for(int i=1; i<=cc; i++) {
                String cn = rsmd.getColumnName(i);
                P.p(cn+"\t");
            }
            P.pln("");

            for(int i=1; i<=cc; i++) {
                P.p("----------");
            }
            P.pln("");

            int j = 0;
            while(rs.next()){
                for(int i=1; i<=cc; i++){
                    String data = rs.getString(i);
                    P.p(data + "  \t");
                }
                j++;
                P.pln("");
            }

            for(int i=1; i<=cc; i++) {
                P.p("----------");
            }
            P.pln("");

            P.pln("총 "+j+"개의 row검색 됨");

        }catch(SQLException se){
        }finally{
            try{
                rs.close();
            }catch(SQLException se){}
        }
    }
    public static void main(String args[]){
        new F();
    }
}
