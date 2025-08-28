<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f9f9f9; }
	h1 { color: #333; }
	table { border-collapse: collapse; width: 80%; margin-top: 20px; }
	th, td { padding: 10px; text-align: center; border-bottom: 1px solid #ddd; }
	th { background-color: #4CAF50; color: white; }
	tr:hover { background-color: #f1f1f1; }
	a { text-decoration: none; color: #4CAF50; font-weight: bold; margin: 10px; }
	a:hover { color: #2e7d32; }
	center { padding-top: 30px; }
</style>

<title>qna 게시판</title>
</head>
<body>
	<h1>qna 게시판</h1>
	<thead>
	
	<a href="<%=request.getContextPath()%>">홈으로</a>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>이메일</th>
			<th>글제목</th>
			<th>날짜</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>글번호</th>
			<td>작성자</th>
			<td>이메일</th>
			<td>글제목</th>
			<td>날짜</th>
		</tr>
	</tbody>	
</body>
</html>