<%@ page contentType ="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>파일 리스팅</title>
</head>
<body>
<center>
    <h3>< 파일 리스트 ></h3>
    <a href="upload.do">파일폼</a><br/>

    <h4>이미지 출력</h4>
    <c:set var="allowedExtensions" value=".jpeg,.png,.gif,.jpg" />
    <c:forEach items="${fileUps}" var="fileUp">
        <c:if test="${fn:contains(allowedExtensions, fileUp.orgnm.substring(fileUp.orgnm.lastIndexOf('.')))}">
            <img src="images/${fileUp.id}" width="150" height="100">
            <p>${fileUp.orgnm}</p>
        </c:if>
    </c:forEach>
    <div>

    </div>

    <h4>파일 다운로드</h4>
    <c:forEach items="${fileUps}" var="fileUp">
        <a href ="attach/${fileUp.id}">${fileUp.orgnm}</a><br/>
    </c:forEach>
    <div>
        <a href="../">인덱스로</a>
    </div>
</center>
</body>
</html>

