<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>KHJ's OpenAI Chat</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* 전체 레이아웃 스타일 */
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 20px;
            box-sizing: border-box;
            background: #f9f9f9;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
        }

        /* 폼 스타일 */
        form {
            display: flex;
            flex-direction: column;
            max-width: 600px;
            margin: 0 auto;
        }

        textarea {
            width: 96%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 8px;
            resize: none;
            font-size: 16px;
        }

        button {
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #2980b9;
        }

        /* 메시지 및 응답 출력 스타일 */
        .chat-container {
            max-width: 600px;
            margin: 20px auto;
        }

        .message-box {
            margin: 10px 0;
            padding: 15px;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .user-message {
            background: #dff9fb;
            text-align: left;
        }

        .ai-response {
            background: #f7d794;
            text-align: left;
        }

        /* 반응형 디자인 */
        @media (max-width: 768px) {
            body {
                padding: 10px;
            }

            form, .chat-container {
                width: 100%;
            }

            textarea {
                font-size: 14px;
            }

            button {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

<h1>KHJ's OpenAI Chat</h1>

<!-- 채팅 입력 폼 -->
<form action="chat.do" method="post">
    <textarea name="message" rows="4" placeholder="메시지를 입력하세요..." required></textarea>
    <button type="submit">Send</button>
</form>

<!-- 메시지 및 응답 출력 -->
<div class="chat-container">
    <c:if test="${not empty message}">
        <div class="message-box user-message">
            <h3>Your Message:</h3>
            <p><c:out value="${message}" /></p>
        </div>
    </c:if>

    <c:if test="${not empty response}">
        <div class="message-box ai-response">
            <h3>OpenAI Response:</h3>
            <p><c:out value="${response}" /></p>
        </div>
    </c:if>
</div>

</body>
</html>