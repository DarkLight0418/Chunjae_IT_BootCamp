package board;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;


// 얘는 화면에서 받은 데이터 받아서 데이터베이스 테이블에 전달하는 역할

public class InsertList extends HttpServlet {
	String maria = "org.mariadb.jdbc.Driver";
	String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
	Connection con;
	PreparedStatement pstmt;
	String sql = "insert into Board(WRITER, EMAIL, SUBJECT, CONTENT, RDATE) values(?, ?, ?, ?, now())";  // executeUpdate() 써야 함
	public void init(){  
		try{
			Class.forName(maria);
			pln("(1) 드라이버 로딩");
			con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
			pln("(2) DB연결 성공");
			pstmt = con.prepareStatement(sql);
			pln("(3) stmt객체생성 성공");
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){}
	}

	// input.html에서 받아온 DB에 넣어주는 역할

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{ 
		//1. Take 

		String writer = req.getParameter("writer");
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");

		//2. DB에 insert 
		try{	
			pstmt.setString(1, writer);
			pstmt.setString(2, email);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.executeUpdate();
		}catch(SQLException se){}

		//3. list.do로 이동
		res.sendRedirect("list.do");
	}
	public void destroy(){ 
		try{
			pstmt.close();
			con.close();
		}
		catch (SQLException se ){}
	}
	void pln(String str){
		System.out.println(str);
	}	
}
