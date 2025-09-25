<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>텍스트 기반 이미지 생성기</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f4f6f8;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 50px;
            color: #333;
        }

        h1 {
            margin-bottom: 30px;
        }

        form {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
        }

        label {
            font-size: 1.1em;
            display: block;
            margin-bottom: 10px;
            text-align: left;
        }

        input[type="text"] {
            width: 100%;
            padding: 12px;
            font-size: 1em;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        button {
            padding: 12px 20px;
            font-size: 1em;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .image-container {
            margin-top: 40px;
            text-align: center;
        }

        .image-container img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
        }

        .retry-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        .retry-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<h1>텍스트 기반 이미지 생성</h1>

<form action="image.do" method="post">
    <label for="prompt">프롬프트:</label>
    <input type="text" id="prompt" name="prompt" value="${param.prompt}" required>
    <button type="submit">이미지 생성</button>
</form>

<c:if test="${not empty imageUrl}">
    <div class="image-container">
        <h2>생성된 이미지</h2>
        <img src="${imageUrl}" alt="Generated Image">
        <br>
        <a href="image.do" class="retry-link">다시 생성하기</a>
    </div>
</c:if>

</body>
</html>
