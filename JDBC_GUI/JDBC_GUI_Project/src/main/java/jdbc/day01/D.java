package jdbc.day01;

import JAE.P;
import java.sql.*;

//CallableStatement
public class D {
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    Connection con;
    CallableStatement cstmt;
    String sql = "call INCRE2(?, ?)";
    D(){
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
            cstmt = con.prepareCall(sql);
        } catch (ClassNotFoundException ce) {
            P.pln("드라이버로딩 실패");
        } catch (SQLException se) {
            P.pln("연결 실패");
        }
    }
    void call(int empno, int rate) {
        try{
            cstmt.setInt(1, empno);
            cstmt.setInt(2, rate);
            cstmt.execute();
            P.pln("호출성공("+empno+"번 사원급여 인상완료)");
        }catch(SQLException se){
        }finally{
            try{
                cstmt.close();
                con.close();
            }catch(SQLException se){}
        }
    }
    public static void main(String args[]){
        new D().call(7902, 10);
    }
}
