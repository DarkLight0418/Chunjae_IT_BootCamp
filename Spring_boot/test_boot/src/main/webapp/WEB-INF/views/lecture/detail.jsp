<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>테스트 페이지1</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f9f9f9;
        }
        a {
            text-decoration: none;
            color: #333;
        }
    </style>
</head>
<body>
<header style="text-align:center;">
    <h1>강의 목록</h1>
    <a href="writeLecture.do">강의 등록</a>
</header>

<main>
    <table>
        <thead>
        <tr>
            <th>번호</th>
            <th>강의 제목</th>
            <th>강사명</th>
            <th>가격</th>
            <th>난이도</th>
            <th>평점?</th>
            <th>상세보기</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="lecture" items="${lecture}">
            <tr>
                <td>${lecture.lectureId}</td>
                <td>${lecture.title}</td>
                <td>${lecture.teacher.name}</td>
                <td><fmt:formatNumber value="${lecture.price}" type="number"/>원</td>
                <td>${lecture.difficulty}</td>
                <td>${lecture.averageRate}</td>
                <td><a href="lectureDetail.do?id=${lecture.lectureId}">보기</a></td>
            </tr>
        </c:forEach>

        <c:if test="${empty lecture}">
            <tr>
                <td colspan="7">등록된 강의가 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</main>
</body>
</html>