<%@ page contentType="text/html;charset=utf-8" 
     import="java.util.*, addr.mv.model.*"%>
<jsp:useBean id="addrDAO_board" class="addr.mv.model.AddrDAO_board" scope="application"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
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
</head>
<body>
<center>
	<h1>
	게시판 List (Model1) 
	</h1>
	<a href='input_board.jsp'>입력</a> 
	&nbsp;&nbsp;&nbsp;
	<a href='../'>인덱스</a>
<table border='1' cellpadding='7' cellspacing='2' width='50%'>
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>이메일</th>
		<th>제목</th>
		<th>내용</th>
		<th>날짜</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
<% 
	ArrayList<AddrDTO_board> list_board = addrDAO_board.list();
    if(list_board != null && list_board.size() != 0){
    	for(AddrDTO_board dto : list_board){
%>
		<tr>
			<td align='center'><%=dto.getSeq()%></td>
			<td><%=dto.getWriter()%></td>
			<td><%=dto.getEmail()%></td>
			<td><%=dto.getSubject()%></td>
			<td><%=dto.getContent()%></td>
			<td><%=dto.getRdate()%></td>
			<td align='center'><a href='update_board.jsp?seq=<%=dto.getSeq()%>'>수정</a></td>
			<td align='center'><a href='del_board.jsp?seq=<%=dto.getSeq()%>'>삭제</a></td>
		</tr>
<%
    	} 
    }else{
%>
	   <tr>
		<td align='center' colspan="5">데이터가 하나도 없음</td>
	   </tr>
<%    	
    }
%>

</table>
</center>
</body>
</html>