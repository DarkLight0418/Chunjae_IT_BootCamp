<%@ page contentType="text/html;charset=utf-8" 
     import="javax.sql.DataSource, java.sql.*"%>
<jsp:useBean id="dbcp" class="soo.dbcp.DbcpBean" scope="application"/>

<%
	DataSource ds = dbcp.getDs(); 
	Connection con = null;
	PreparedStatement pstmt = null;
	String sql = "insert into ADDRESS(name, addr, rdate) values(?,?,now())";

	//1. Take 
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	
	//2. DB에 insert 
	try{	
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, addr);
		pstmt.executeUpdate();
	}catch(SQLException se){
	}finally{
		try{
			pstmt.close();
			con.close();
		}catch(SQLException se){}
	}

	//3. list.do로 이동
	//response.sendRedirect("list.jsp");
%>
<script>
	alert("입력성공(with DBCP)");
    location.href="list.jsp";
</script>