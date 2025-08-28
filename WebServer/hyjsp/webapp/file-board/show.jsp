<%@ page contentType="text/html;charset=utf-8" 
     import="java.util.ArrayList, young.mvc.domain.Board"%>

<meta http-equiv="Content-Language" content="ko">
<meta charset='utf-8'>
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

/* 👇 파일 업로드 전용 스타일 */
.file-upload-box {
  position: relative;
  display: inline-block;
  width: 100%;
  margin-bottom: 10px;
}

.uploaded-file {
  margin-top: 8px;
  background-color: #f1f1f1;
  padding: 8px 12px;
  border-radius: 4px;
  font-size: 14px;
}

.uploaded-link {
  color: #007BFF;
  text-decoration: none;
}

.uploaded-link:hover {
  text-decoration: underline;
}

.warnig {
  color: #9F9F9F;
    font-size: 12px;
}

.del {
	color: #FFA500;
}
</style>
<body onload="document.f.writer.focus()">
<center>
<h1>게시판 글쓰기</h1>
<form name="f" action="insert.jsp" method="post">
<table>
<tr>
<th>작성자</th>
<td>${dto.writer}</td>
</tr>
<tr>
<th>이메일</th>
<td>${dto.email}</td>
</tr>
<tr>
<th>제목</th>
<td>${dto.subject}</td>
</tr>
<tr>
<th>내용</th>
<td><div id='content'><pre>${dto.content}</pre><div></td>
</tr>
<th>첨부파일</th>
<td>
<div class="uploaded-file">

<a href="board.do?m=download&seq=${dto.seq}" class="uploaded-link">${dto.fname}</a>

</div>
</td>
</tr>
</table>
</form>
<div class="button-row">
<a href= "board.do?m=show&seq=${dto.seq}&edit=true">수정</a>
<a href= "board.do?m=del&seq=${dto.seq}">삭제</a>
<a href= "board.do?m=list">돌아가기</a>
</div>
</center>
</body>
