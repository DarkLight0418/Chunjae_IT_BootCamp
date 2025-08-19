<%@ page contentType="text/html;charset=utf-8" import="board.mvc.domain.Board"%>
<%
	Board board = (Board)request.getAttribute("board");
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
<hr width='600' size='2' noshade>
<h2>Simple Board with MVC</h2>
&nbsp;&nbsp;&nbsp;
<a href='board.do?m=input'>글쓰기</a>
<hr width='600' size='2' noshade>
<table border='1' width='600' align='center' cellpadding='3'>
<tr>
<td width='100' align='center'>글번호</td>
<td><%=board.getSeq()%></td>
</tr>
<tr>
<td align='center'>글쓴이</td>
<td><%=board.getWriter()%></td>
</tr>
<tr>
<td align='center'>이메일</td>
<td><%=board.getEmail()%></td>
</tr>
<tr>
<td align='center'>글제목</td>
<td><%=board.getSubject()%></td>
</tr>
<tr>
<td align='center'>글내용</td>
<td><%=board.getContent()%></td>
</tr>
</table>
<hr width='600' size='2' noshade>
<b>
<a  href='board.do?m=update&seq=<%=board.getSeq()%>'>수정</a>
| 
<a href='board.do?m=delete&seq=<%=board.getSeq()%>'>삭제</a>
| 
<a href='board.do'>목록</a>
</b>
<hr width='600' size='2' noshade>
</center>
