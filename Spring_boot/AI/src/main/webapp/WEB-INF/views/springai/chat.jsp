<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SpringAI Chat</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f5f7fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
        }

        .container {
            background-color: #fff;
            margin-top: 50px;
            padding: 40px 30px;
            border-radius: 12px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 600px;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: 700;
            color: #34495e;
            text-align: left;
        }

        input[type="text"] {
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 16px;
            width: 100%;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus {
            outline: none;
            border-color: #007bff;
        }

        button {
            padding: 12px;
            background-color: #007bff;
            color: #fff;
            font-size: 16px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        .response-block {
            margin-top: 30px;
            background-color: #f0f4f8;
            padding: 20px;
            border-radius: 10px;
            border-left: 5px solid #007bff;
        }

        .response-block h2 {
            margin-top: 0;
            color: #2c3e50;
        }

        .response-block p {
            color: #34495e;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>SpringAI Chat</h1>

    <form action="chat.do" method="post">
        <label for="message">Your Message:</label>
        <input type="text" id="message" name="message" placeholder="Type your message..." required>
        <button type="submit">Send</button>
    </form>

    <c:if test="${not empty message}">
        <div class="response-block">
            <h2>Your Input:</h2>
            <p>${message}</p>
        </div>
    </c:if>

    <c:if test="${not empty response}">
        <div class="response-block">
            <h2>OpenAI's Response:</h2>
            <p>${response}</p>
        </div>
    </c:if>
</div>
</body>
</html>
