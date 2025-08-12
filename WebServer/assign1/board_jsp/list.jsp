<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"   %> 
<!-- //지시어 -->
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
		catch (SQLException se){}
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
	<hr width='950' size='2' noshade>
	<h2>게시판(JSP)</h2>
	&nbsp;&nbsp;&nbsp;
	<a href='input.jsp'>글쓰기</a>
	&nbsp;&nbsp;&nbsp;
	<a href='index.html'>인덱스</a>
	<hr width='950' size='2' noshade>
</center>
<table border='1' width='950' align='center' cellpadding='2'>
	<tr>
		<th align='center' width='10%'>글번호</th>
		<th align='center' width='15%'>작성자</th>
		<th align='center' width='25%'>이메일</th>
		<th align='center' width='20%'>글제목</th>
		<th align='center' width='10%'>내용 요약</th>
		<th align='center' width='15%'>날짜</th>
		<th align='center' width='5%'>삭제</th>
	</tr>
<% 
	String sql = "select * from BOARD order by SEQ desc";
	ResultSet rs = null;
	try {
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			int seq = rs.getInt(1);
			String writer = rs.getString(2);
			String email = rs.getString(3);
			String subject = rs.getString(4);
			String content = rs.getString(5);
			Date rdate = rs.getDate(6);
%>
	<tr>
		<td align='center'><%=seq%></td>
		<td align='center'><%=writer%></td>
		<td align='center'><%=email%></td>
		<td align='center'>
			<a href='content.jsp?seq=<%=seq%>'><%=subject%></a>
		</td>
		<td align='center'><%=content%></td>
		<td align='center'><%=rdate%></td>
		<td align='center'><a href='del.jsp?seq=<%=seq%>'>삭제</a></td>
	</tr>
<%
}
	} catch(SQLException se) {
		System.out.println("sql 예외 : " + se);
	} finally{
		try{
			rs.close();
		}catch(SQLException se){}
	}
%>

</table>
<hr width='950' size='2' noshade>
