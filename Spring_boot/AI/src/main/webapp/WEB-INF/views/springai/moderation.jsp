<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Spring AI Moderation</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Roboto', 'Segoe UI', sans-serif;
            background-color: #f6f8fa;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 720px;
            margin: 0 auto;
            background: #ffffff;
            padding: 30px 25px;
            border-radius: 16px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 1.8em;
            color: #333;
        }

        textarea {
            width: 100%;
            padding: 12px;
            font-size: 1rem;
            border: 1px solid #ccc;
            border-radius: 8px;
            resize: vertical;
            min-height: 100px;
            box-sizing: border-box;
        }

        button {
            margin-top: 15px;
            padding: 12px 20px;
            font-size: 1rem;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.2s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .result {
            margin-top: 30px;
            background-color: #f9f9f9;
            border: 1px solid #e2e2e2;
            border-radius: 12px;
            padding: 20px;
        }

        .flagged {
            font-weight: bold;
            font-size: 1.1em;
            color: #d9534f;
        }

        ul {
            padding-left: 20px;
        }

        li {
            margin-bottom: 6px;
            line-height: 1.4;
        }

        @media (max-width: 600px) {
            .container {
                padding: 20px 15px;
            }

            h1 {
                font-size: 1.4em;
            }

            button {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>OpenAI Moderation</h1>

    <textarea id="inputText" placeholder="검사할 텍스트를 입력하세요..."></textarea>
    <button id="checkBtn">Moderate</button>

    <div class="result" id="resultBox" style="display:none;">
        <p class="flagged">⚠️ Flagged: <span id="flagged"></span></p>

        <h3>카테고리</h3>
        <ul id="categoriesList"></ul>

        <h3>카테고리 스코어</h3>
        <ul id="scoresList"></ul>
    </div>
</div>

<script>
    $("#checkBtn").click(function () {
        const inputVal = $("#inputText").val().trim();

        if (!inputVal) {
            alert("텍스트를 입력해주세요.");
            return;
        }

        $.post("moderation.do", { input: inputVal }, function (data) {
            $("#flagged").text(data.flagged);

            $("#categoriesList").empty();
            $.each(data.categories, function (key, value) {
                $("#categoriesList").append("<li>" + key + ": " + value + "</li>");
            });

            $("#scoresList").empty();
            $.each(data.categoryScores, function (key, value) {
                $("#scoresList").append("<li>" + key + ": " + value.toFixed(3) + "</li>");
            });

            $("#resultBox").slideDown();
        });
    });
</script>
</body>
</html>
