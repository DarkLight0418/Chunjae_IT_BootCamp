<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%!
	String maria = "org.mariadb.jdbc.Driver";
	String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
	Connection con;
	PreparedStatement pstmt;
	String sql = "delete from ADDRESS where SEQ=?";
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
	String strSeq = request.getParameter("seq");
	int seq = Integer.parseInt(strSeq);
	pln("AddrServletDel seq: " + seq);
	
	//2. DB에 delete  
	try{	
		pstmt.setInt(1, seq);
		pstmt.executeUpdate();
	}catch(SQLException se){}

	//3. list.do로 이동
	response.sendRedirect("list.jsp");
%>