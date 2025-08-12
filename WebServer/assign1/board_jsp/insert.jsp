<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>

<%!  // %! - 정의문, 선언만 해줄 것
	String maria = "org.mariadb.jdbc.Driver";
	String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
	Connection con;
	PreparedStatement pstmt;
	String sql = "insert into BOARD(writer, email, subject, content, rdate) values(?,?,?,?,now())";
	public void jspInit(){  
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
	public void jspDestroy(){ 
		try{
			pstmt.close();
			con.close();
		}
		catch (SQLException se ){}
	}
	void pln(String str){
		System.out.println(str);
	}	
%>
<%
	//1. Take 
	String writer = request.getParameter("writer");
	String email = request.getParameter("email");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	
	//2. DB에 insert 
	try{	
		pstmt.setString(1, writer);
		pstmt.setString(2, email);
		pstmt.setString(3, subject);
		pstmt.setString(4, content);
		pstmt.executeUpdate();
	}catch(SQLException se){
		System.out.println("sql 셋팅 예외 : " + se);
	}

	//3. list.do로 이동
	response.sendRedirect("list.jsp");
%>