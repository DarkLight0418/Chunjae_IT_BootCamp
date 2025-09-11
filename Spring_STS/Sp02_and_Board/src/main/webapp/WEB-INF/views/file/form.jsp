<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Fileupload Form</title>
		<style>
			a{text-decoration:none}
		</style>
	</head>
	<body style="text-align:center">
		<!-- 파일 업로드는 무조건 post -->

		<h1>Fileupload Form</h1>
		<form action="upload.do"  method="post" enctype="multipart/form-data">
					이름: <input name="name"><br/>
				파일: <input type='file' name='file'><br/>
			<input type='submit' value="전송">
		</form> 
		
	</body>
</html>