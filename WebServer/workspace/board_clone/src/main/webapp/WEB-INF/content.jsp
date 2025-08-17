<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="board.mvc.domain.Board" %>

<%
    Board result = (Board) request.getAttribute("board");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>게시글 상세 보기</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
        }
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<center>
    <hr width="600">
    <h2>Simple Board with Servlet</h2>
    &nbsp;&nbsp;&nbsp;<a href="board.do?m=input">글쓰기</a>
    <hr width="600">

    <table border="1" width="600" cellpadding="3">
        <tr>
            <td width="100" align="center">글번호</td>
            <td><%= result.getSeq() %></td>
        </tr>
        <tr>
            <td align="center">글쓴이</td>
            <td><%= result.getWriter() %></td>
        </tr>
        <tr>
            <td align="center">이메일</td>
            <td><%= result.getEmail() %></td>
        </tr>
        <tr>
            <td align="center">글제목</td>
            <td><%= result.getSubject() %></td>
        </tr>
        <tr>
            <td align="center">글내용</td>
            <td><%= result.getContent() %></td>
        </tr>
    </table>

    <hr width="600">
    <b>
        <a href="updateForm.jsp?seq=<%= result.getSeq() %>">수정</a> |
        <a href="board.do?m=del&seq=<%= result.getSeq() %>">삭제</a> |
        <a href="board.do">목록</a>
    </b>
    <hr width="600">
</center>
</body>
</html>

