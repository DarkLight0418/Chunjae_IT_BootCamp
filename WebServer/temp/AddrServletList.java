package soo.sv.addr;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AddrServletList extends HttpServlet {
	String maria = "org.mariadb.jdbc.Driver";
	String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
	Connection con;
	Statement stmt;
	public void init(){ //DB연결 
		try{
			Class.forName(maria);
			pln("(1) 드라이버 로딩");
			con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
			pln("(2) DB연결 성공");
			stmt = con.createStatement();
			pln("(3) stmt객체생성 성공");
		}catch(ClassNotFoundException cnfe){
			pln("(1) 드라이버 실패");
		}catch(SQLException se){
			pln("(2) DB연결 or (3) stmt객체생성 실패: " + se);
		}
	}
	public void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{ //요청+응답 처리
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();

		pw.println("<meta charset='utf-8'>");
		
		pw.println("<style>");
			pw.println("table, th, td {");
			   pw.println("border: 1px solid black;");
			   pw.println("border-collapse: collapse;");
			pw.println("}");
			pw.println("th, td {");
			   pw.println("padding: 5px;");
			pw.println("}");
			pw.println("a { text-decoration:none }");
		pw.println("</style>");
		pw.println("<center>");
			pw.println("<h1>");
				pw.println("게시판 List");
			pw.println("</h1>");
			pw.println("<a href='input.html'>입력</a>");
			pw.println("&nbsp;&nbsp;&nbsp;");
			pw.println("<a href='../'>인덱스</a>");
			pw.println("<table border='1' cellpadding='7' cellspacing='2' width='50%'>");
				pw.println("<tr>");
					pw.println("<th>글번호</th>");
					pw.println("<th>작성자</th>");
					pw.println("<th>이메일</th>");
					pw.println("<th>글제목</th>");
					pw.println("<th>날짜</th>");
				pw.println("</tr>");

        String sql = "select * from BOARD order by SEQ desc";
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt(1);
				String name = rs.getString(2);
				String mail = rs.getString(3);
				String title = rs.getString(4);
				Date rdate = rs.getDate(5);
				pw.println("<tr>");
					pw.println("<td align='center'>"+seq+"</td>");
					pw.println("<td>"+name+"</td>");
					pw.println("<td>"+mail+"</td>");
					pw.println("<td>"+title+"</td>");
					pw.println("<td>"+rdate+"</td>");
					pw.println("<td align='center'><a href='del.do?seq="+seq+"'>삭제</a></td>");
				pw.println("</tr>");
			}
		}catch(SQLException se){
			System.out.println(se);
		}finally{
			try{
				rs.close();
			}catch(SQLException se){}
		}
			pw.println("</table>");
		pw.println("</center>");
	}
	public void destroy(){ //DB해제 
		try{
			stmt.close();
			con.close();
		}
		catch (SQLException se ){}
	}
	void pln(String str){
		System.out.println(str);
	}
}
