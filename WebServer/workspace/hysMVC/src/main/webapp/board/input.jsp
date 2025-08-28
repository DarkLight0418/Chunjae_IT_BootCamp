<%@ page contentType="text/html;charset=utf-8" 
     import="java.util.ArrayList, young.mvc.domain.Board"%>

<meta charset="utf-8">
<meta http-equiv="Content-Language" content="ko">
<style>
    body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f9f9f9;
    margin: 0;
    padding: 30px;
}

h1 {
    color: #333;
}

form {
    width: 600px;
    margin-top: 20px;
    background-color: #fff;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
    border-radius: 6px;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    padding: 10px;
    text-align: left;
}

th {
    width: 20%;
    color: #333;
    font-weight: bold;
}

td input[type="text"] {
    width: 95%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="submit"],
input[type="reset"] {
    padding: 8px 16px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin: 5px;
}

input[type="submit"]:hover,
input[type="reset"]:hover {
    background-color: #45a049;
}

a {
    text-decoration: none;
    color: #4CAF50;
    font-weight: bold;
    margin: 10px;
}

a:hover {
    color: #2e7d32;
}

center {
    padding-top: 30px;
}

#content {
    width: 95%;
    height: 200px;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
    vertical-align: top; 
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 14px;
    line-height: 1.5;
}

</style>

<body onload="document.f.writer.focus()">
<center>
   <h1>게시판 글쓰기</h1>
   <form name="f" action="board.do?m=insert" method="post">
       <table>
          <tr>
             <th>작성자</th> 
             <td><input type="text" name="writer" required></td>
          </tr>
          <tr>
             <th>이메일</th> 
             <td><input type="text" name="email" required></td>
          </tr>
          <tr>
             <th>제목</th> 
             <td><input type="text" name="subject" required></td>
          </tr>
          <tr>
             <th>내용</th> 
             <td><textarea name="content" id="content"></textarea></td>
          </tr>
          <tr>
             <td colspan="2" class="button-row">
                 <input type="submit" value="전송">
                 <input type="reset" value="다시 입력">
             </td> 
          </tr>
       </table>
   </form>
</center>
</body>
