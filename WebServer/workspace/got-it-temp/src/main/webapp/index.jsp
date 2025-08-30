<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 테스트 페이지</title>
	<style>
		a {text-decoration:none; }
		p {font-size: 24px;}
	</style>
</head>
<body style="text-align:center">
	
	<h1>게시판 테스트</h1>
	<a href="<%=request.getContextPath()%>/board.do?type=QnA">qna 게시판</a><br>
	<a href="<%=request.getContextPath()%>/board.do?type=knowledge">지식나눔 게시판</a>
</body>
</html>