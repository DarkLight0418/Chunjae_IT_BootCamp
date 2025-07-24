package jdbc.day01;

import JAE.P;
import java.sql.*;

public class E {
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    Connection con;
    Statement stmt;
    String sql = "select * from JDBCT order by NO desc";
    E(){
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

            forward(rs);//BOF -> EOF
            backward(rs); //EOF-> BOF
            rs.afterLast(); //EOF
            backward(rs); //EOF-> BOF

            forward(rs); //BOF -> EOF
            rs.beforeFirst(); //BOF
            forward(rs); //BOF -> EOF
        }catch(SQLException se){}
    }
    void forward(ResultSet rs){
        P.pln("< 순방향 > ");
        try{
            while(rs.next()){
                int no = rs.getInt(1);
                String name = rs.getString(2);
                Date rdate = rs.getDate(3);
                Time rtime = rs.getTime(3);
                P.pln(no+"\t"+name+"\t"+rdate+"\t"+rtime+"\t");
            }
        }catch(SQLException se){}
    }
    void backward(ResultSet rs){
        P.pln("< 역방향 > ");
        try {
            while(rs.previous()){
                int no = rs.getInt(1);
                String name = rs.getString(2);
                Date rdate = rs.getDate(3);
                Time rtime = rs.getTime(3);
                P.pln(no+"\t"+name+"\t"+rdate+"\t"+rtime+"\t");
            }
        }catch(SQLException se){}
    }
    public static void main(String args[]){
        new E();
    }
}
