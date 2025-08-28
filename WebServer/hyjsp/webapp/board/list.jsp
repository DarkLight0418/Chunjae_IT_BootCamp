<%@ page contentType="text/html;charset=utf-8" 
     import="java.util.ArrayList, young.mvc.domain.Board"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<meta http-equiv="Content-Language" content="ko">
<meta charset='utf-8'>
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
<center>
<h1>
블로그 게시판 (EL)
</h1>
<a href='input.jsp'>글쓰기</a>
<a href='../'>인덱스로 돌아가기</a>
<table border='1' cellpadding='7' cellspacing='2' width='50%'>
<tr>
<th>글번호</th>
<th>작성자</th>
<th>이메일</th>
<th>글제목</th>
<th>날짜</th>
</tr>

<c:if test="${empty list}">
    <tr>
		<td align='center' colspan="5">데이터가 하나도 없음</td>
	</tr>
</c:if>
<c:forEach items="${list}" var="dto">
			<tr>
			<td align='center'>${dto.seq}</td>
			<td>${dto.writer}</td>
			<td>${dto.email}</td>
			<td align='center'><a href="board.do?m=show&seq=${dto.seq}">${dto.subject}</a></td>
			<td>${dto.rdate}</td>
			</tr>
</c:forEach>
</table>
</center>
