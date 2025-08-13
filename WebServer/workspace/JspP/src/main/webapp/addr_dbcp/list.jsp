<%@ page contentType="text/html;charset=utf-8" 
     import="javax.sql.DataSource, java.sql.*"%>
     
<jsp:useBean id="dbcp" class="soo.dbcp.DbcpBean" scope="application"/>

<meta charset='utf-8'>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
th, td {
	padding: 5px;
}
a { 
	text-decoration:none 
}
</style>
<center>
<h1>
Address List DBCP 
</h1>
<a href='input.jsp'>입력</a> 
&nbsp;&nbsp;&nbsp;
<a href='../'>인덱스</a>
<table border='1' cellpadding='7' cellspacing='2' width='50%'>
<tr>
	<th>번호</th>
	<th>이름</th>
	<th>주소</th>
	<th>날짜</th>
	<th>삭제</th>
</tr>
<% 
	DataSource ds = dbcp.getDs(); 
	Connection con = null;
	Statement stmt = null;
    String sql = "select * from ADDRESS order by SEQ desc";
	ResultSet rs = null;
	try{
		con = ds.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			int seq = rs.getInt(1);
			String name = rs.getString(2);
			String addr = rs.getString(3);
			Date rdate = rs.getDate(4);
%>
			<tr>
			<td align='center'><%=seq%></td>
			<td><%=name%></td>
			<td><%=addr%></td>
			<td><%=rdate%></td>
			<td align='center'><a href='del.jsp?seq=<%=seq%>'>삭제</a></td>
			</tr>
<%
       }
	}catch(SQLException se){
	}finally{
		try{
			rs.close();
		}catch(SQLException se){}
	}
%>

</table>
</center>
