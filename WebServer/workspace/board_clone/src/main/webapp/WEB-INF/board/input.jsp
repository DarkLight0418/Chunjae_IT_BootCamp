<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
        }
        a { text-decoration: none; }
    </style>
</head>

<body onload="document.f.writer.focus()">
<center>
    <h1>Simple Board Input</h1>
    <form name="f" action="board.do?m=insert" method="post">
        <table border="1" width="400" height="250">
            <tr>
                <td colspan="2" align="center"><h2>글쓰기 폼</h2></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><input type="text" name="writer" size="30" required></td>
            </tr>
            <tr>
			    <th>이메일</th>
			    <td><input type="text" name="email" size="30"></td>
			</tr>
            <tr>
                <th>제목</th>
                <td><input type="text" name="subject" size="30" required></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea name="content" rows="4" cols="28" required></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="등록">
                    <input type="reset" value="취소">
                </td>
            </tr>
        </table>
    </form>
    <p><a href="board.do">[목록으로]</a></p>
</center>
</body>
</html>