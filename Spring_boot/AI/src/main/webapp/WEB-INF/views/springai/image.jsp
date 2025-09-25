<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>이미지 생성기</title>
</head>
<body style="text-align: center">
<h1>텍스트 기반 이미지 생성</h1>
<form action="image.do" method="post">
    <label for="prompt">프롬프트:</label>
    <input type="text" id="prompt" name="prompt" required>
    <button type="submit">생성</button>
</form>
</body>
</html>