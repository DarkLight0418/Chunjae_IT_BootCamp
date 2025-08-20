<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>인덱스</title>
		<style>
			a {text-decoration: none}
		</style>
	</head>
	<body style="text-align:center">
	<h1>JspP MVC Index</h1>
    <br/>
	    <a href="addr_dbcp/list.jsp">DBCP주소록</a><br/>
	    <a href="addr_mv/list.jsp">주소록(Model1)</a><br/>
	    <a href="address/addr.do">주소록(Model2)</a><br/>
	    <a href="mvcboard/board.do">게시판</a><br/>
	        
	   	<a href="file/file.do?m=form">파일 업로드 폼</a></br>
	   	<a href="ajax/ajax.do">비동기 통신</a></br>
	   	<a href="ajax/ajax.do?m=auto_form">자동 완성</a></br>
	   	
	   	<c:choose>
			<c:when test="${empty loginOkUser}">
				<a href="login/login.do?m=form">로그인</a>
			</c:when>
			<c:otherwise>
				<font style="color:green">${loginOkUser.name}</font>님 환영합니다 ^^ 
				<br/>
				<a href="login/login.do?m=logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
	   	
	</body>
</html>