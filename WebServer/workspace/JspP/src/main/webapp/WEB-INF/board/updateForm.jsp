<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="board.mvc.model.BoardService" %>
<%@ page import="board.mvc.domain.Board" %>

<%
    request.setCharacterEncoding("utf-8");
    String seq = request.getParameter("seq");

    Board dto = new Board();
    dto.setSeq(Long.parseLong(seq));

    Board result = BoardService.getInstance().content(dto); // ✅ 여기서 데이터 조회
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>게시글 수정</title>
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
    <h2>게시글 수정</h2>
    <form method="post" action="board.do?m=doUpdate">
        <input type="hidden" name="seq" value="<%= result.getSeq() %>">
        <table border="1" width="600" cellpadding="5">
            <tr>
                <td width="30%" align="center">글쓴이</td>
                <td><input type="text" size="60" value="<%= result.getWriter() %>" disabled></td>
            </tr>
            <tr>
                <td align="center">이메일</td>
                <td><input type="text" name="email" size="60" value="<%= result.getEmail() %>"></td>
            </tr>
            <tr>
                <td align="center">글제목</td>
                <td><input type="text" name="subject" size="60" value="<%= result.getSubject() %>"></td>
            </tr>
            <tr>
                <td align="center">글내용</td>
                <td><textarea name="content" rows="5" cols="53"><%= result.getContent() %></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="수정">
                </td>
            </tr>
        </table>
    </form>
    <br/>
    <a href="board.do">목록으로 돌아가기</a>
</center>
</body>
</html>

