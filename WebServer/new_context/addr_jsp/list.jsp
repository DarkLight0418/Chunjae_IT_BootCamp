<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%!
    String maria = "org.mariadb.jdbc.Driver";
	String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
	Connection con;
	Statement stmt;
	public void jspInit(){ //DB연결 
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
	public void jspDestroy(){ //DB해제 
		try{
			stmt.close();
			con.close();
		}
		catch (SQLException se ){}
	}
	void pln(String str){
		System.out.println(str);
	}
%>

<meta charset='utf-8'>
<style>
table, th, td {
border: 1px solid black;
border-collapse: collapse;
}
th, td {
padding: 5px;
}
a { text-decoration:none }
</style>
<center>
	<h1>
	Address List JSP 
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
		String sql = "select * from ADDRESS order by SEQ desc";
		ResultSet rs = null;
		try{
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
