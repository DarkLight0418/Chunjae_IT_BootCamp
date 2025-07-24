package jdbc.day01;

import JAE.P;
import java.sql.*;

// PreparedStatement
class C {
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
    Connection con;
    PreparedStatement pstmt1, pstmt2, pstmt3;
    String tname = "JDBCT";
    String sql1 = "insert into "+tname+" values(?, ?, now())"; //가독성
    String sql2 = "select * from "+tname+" order by NO desc";
    String sql3 = "select * from "+tname+" where NAME like ?"; //성능

    C() {
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
            pstmt1 = con.prepareStatement(sql1);
            pstmt2 = con.prepareStatement(sql2);
            pstmt3 = con.prepareStatement(sql3);
        } catch (ClassNotFoundException ce) {
            P.pln("드라이버로딩 실패");
        } catch (SQLException se) {
            P.pln("연결 실패");
        }
    }
    void insertD(int no, String name){
        try{
            pstmt1.setInt(1, no);
            pstmt1.setString(2, name);
            int i = pstmt1.executeUpdate();
            if(i>0){
                P.pln(i+"개의 row 입력 성공");
            }else{
                P.pln("입력 실패");
            }
        }catch(SQLException se){
            P.pln("입력 실패: " + se);
        }
    }
    void selectD(){
        ResultSet rs = null;
        try{
            rs = pstmt2.executeQuery();
            P.pln("번호\t이름\t\t시간");
            P.pln("----------------------------");
            int i =0;
            while(rs.next()) {
                int no = rs.getInt(1);
                String name = rs.getString(2);
                Time rtime = rs.getTime(3);
                P.pln(no + "\t" + name + "\t" + rtime);
                i++;
            }
            P.pln("----------------------------");
            P.pln("총 " +i+"개의 row가 검색됨");
        } catch (SQLException se) {
            P.pln("selectD() 예외: " + se);
        }finally{
            try{
                rs.close();
            }catch(SQLException se){}
        }
    }
    void selectD(String na){
        ResultSet rs = null;
        try{
            pstmt3.setString(1, "%" +na+"%");
            rs = pstmt3.executeQuery();
            P.pln("번호\t이름\t\t시간");
            P.pln("----------------------------");
            int i =0;
            while(rs.next()) {
                int no = rs.getInt(1);
                String name = rs.getString(2);
                Time rtime = rs.getTime(3);
                P.pln(no + "\t" + name + "\t" + rtime);
                i++;
            }
            P.pln("----------------------------");
            P.pln("총 " +i+"개의 row가 검색됨");
        } catch (SQLException se) {
            P.pln("selectD() 예외: " + se);
        }finally{
            try{
                rs.close();
            }catch(SQLException se){}
        }
    }
    public static void main(String args[]){
        C c = new C();

        //c.insertD(50, "오연희");
        //c.insertD(60, "박현지");
        //c.insertD(70, "최윤서");
        //c.insertD(80, "김승완");
        //c.insertD(90, "김효상");

        //c.selectD();
        c.selectD("김");
    }
}
