<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	<style>
		a {text-decoration:none; }
		p {font-size: 24px;}
	</style>
</head>
<body style="text-align:center">
	<h1>NewContext Index</h1>
	<br/>
	<p>DBCP</p>
	<a href="dbcp_test.jsp">DBCP Test</a>
	<br/>
	<p>EL</p>
	<a href="address/addr.do">Address EL</a>,
	<a href="board/board.do">Board EL</a>
	<br/>
	<p>File</p>
	<a href="file/file.do?m=form">File Upload</a>
	<br>
	<p>JQuery</p>
	<a href="jquery/jq1.html">JQuery 1</a>,
	<a href="jquery/jq2.html">JQuery 2</a>,
	<a href="ajax/ajax.do">AJAX</a>,
	<a href="ajax/ajax.do?m=auto_form">AutoComplete</a>
	<br/>
	<p>FileUpload Board</p>
	<a href="file-board/board.do">File Board</a>
	<br/>
	<p>Session</p>
	<!-- 
	<%
		String sid = session.getId();
	%>
	세션ID: <%= sid%>
	 -->
	 	<br>
	 <c:choose>
	 <c:when test="${empty loginOkUser}">
	 	<a href="login/login.do?m=form">Login</a>
	 </c:when>
	 <c:otherwise>
	 	<font style='color:green'>${loginOkUser.name}</font> 왔구나ㅋ
	 	<br/>
	 	<a href="login/login.do?m=logout">Logout</a>
	 </c:otherwise>
	 </c:choose>


</body>
</html>