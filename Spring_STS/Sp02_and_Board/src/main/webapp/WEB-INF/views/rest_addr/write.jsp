<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>Restful Form</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script>
			$(function(){
				$("#na").focus();
				$("#btn").on("click", function(){
					/*
					$.ajax({
						url: "create1.json", 
						type: "post",
						data: {name: $("#na").val(), addr:$("#ad").val()}, //jsObj
						success: function(data){
							alert(data);
						}, 
						error: function(error){
							alert(error);
						}
					});
					*/
					
					let jsObj = {name: $("#na").val(), addr:$("#ad").val()};
					let jsonData = JSON.stringify(jsObj);
					
					$.ajax({ //쉬는 시간 갖고.. 아래를 완성해보세요!
						url: "create2.json", 
						type: "post",
						contentType: "application/json;charset=utf-8", 
						data: jsonData, //JSON
						success: function(){
							alert("성공");
						}, 
						error: function(){
							alert("실패");
						}
					});
				});
			});
		</script>
	</head>
	<body style="text-align:center">
		<h1>
			Rest Address Create 
		</h1>
			 이름 : <input id="na"><br/>
			 주소 : <input id="ad"><br/>
		   <input type="button" value="주소록 추가" id="btn"><br>
	</body>
</html>