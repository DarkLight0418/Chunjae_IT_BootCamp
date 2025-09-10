<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
	<meta charset="utf-8">
	<title>Sp01 INDEX</title>
</head>
<body style="text-align: center">
	<h2>
		Sp01 INDEX 
	</h2>
	
	<p>
		<a href="test">m01</a>
		<a href="test/base1">m02</a>
		<a href="test/base2">m03</a>
		<a href="test/form">form</a>
	</p>
	<p>
		<a href="test/param1?name=홍길동">m04</a>
		<a href="test/param2?na=홍길동&age=20">m05</a>
		<a href="test/param3?name=이순신&age=40">m06</a>
	</p>
	<p>
		<a href="test/param4?names=홍길동&names=이순신&names=강감찬">m07</a>
		<a href="test/param5?names=홍길동&names=이순신&names=강감찬">m08</a>
		<a href="test/param6?ns=홍길동&ns=이순신&ns=강감찬">m09</a>
	</p>
	<p>
		<!--   
		<a href="test/param7?list[0].name=이순신&list[0].age=40&list[1].name=강감찬&list[1].age=50">m10</a>
		[ -> %5B
		] -> %5D
		--> 
		
		<a href="test/param7?list%5B0%5D.name=이순신&list%5B0%5D.age=40&list%5B1%5D.name=강감찬&list%5B1%5D.age=50">m10</a>
		<a href="test/param8?name=이순신&age=40&dp=추가데이터&grade=3">m11</a>
		<a href="test/param9?subject=가을&cdate=2025/09/10 10:40:00">m12</a>
	</p>
</body>
</html>