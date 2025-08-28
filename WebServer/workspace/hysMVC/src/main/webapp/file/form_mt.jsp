<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Fileupload Form Multi</title>
		<style>
			a{text-decoration:none}
		</style>
	</head>
	<body style="text-align:center">
		<h1>Fileupload Form Multi</h1>

		<form action="file.do?m=upload_mt"  method="post" enctype="multipart/form-data">
		 <div>
		   <input type='file' name='files'>
		 </div>
		 <div>
		   <input type='file' name='files'>
		 </div>
		 <div>
		   <input type='file' name='files'>
		 </div>
		 <div>
		   <input type='file' name='files'>
		 </div>
		 <div>
		   <input type='file' name='files'>
		 </div>
		 <div>
		   <input type='submit'>
		 </div>   
		</form>
		<a href="file/file.do?m=list">파일리스트</a><br/>
	</body>
</html>