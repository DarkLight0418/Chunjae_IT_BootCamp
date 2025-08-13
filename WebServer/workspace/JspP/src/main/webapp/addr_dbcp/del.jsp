<%@ page contentType="text/html;charset=utf-8" 
     import="javax.sql.DataSource, java.sql.*"%>
<jsp:useBean id="dbcp" class="soo.dbcp.DbcpBean" scope="application"/>

<% 
    DataSource ds = dbcp.getDs();
    Connection con = null;
	PreparedStatement pstmt = null;
	String sql = "delete from ADDRESS where SEQ=?";
	//1. Take 
	String strSeq = request.getParameter("seq");
	int seq = Integer.parseInt(strSeq);
	
	//2. DB에 delete  
	try{	
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, seq);
		pstmt.executeUpdate();
	}catch(SQLException se){
	}finally{
		try{
			pstmt.close();
			con.close();
		}catch(SQLException se){}
	}

	//3. list.jsp로 이동
	//response.sendRedirect("list.jsp");
%>
<script>
	alert("삭제성공(with DBCP)");
    location.href="list.jsp";
</script>