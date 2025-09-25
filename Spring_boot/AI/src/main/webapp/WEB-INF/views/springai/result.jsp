<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>생성된 이미지</title>
</head>
<body style="text-align: center">
<h1>생성된 이미지</h1>
<c:if test="${not empty imageUrl}">
    <img src="${imageUrl}" alt="Generated Image">
</c:if>
<br>
<a href="image.do">다시 생성하기</a>
</body>
</html>