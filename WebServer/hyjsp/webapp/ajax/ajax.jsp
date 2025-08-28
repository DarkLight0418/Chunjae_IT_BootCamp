<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8"/>
        <title>AJAX Test</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script>
        	$(function(){
        		//$('#searchOk01').on('click', function(){
        		$('#seq').on('keyup', function(){
        			$.ajax({
        				url: 'ajax.do?m=search01', 
        				type: 'get', 
        				data: { seq: $('#seq').val() }, 
        				success: function(result){
        					console.log(result);
        					//result를 HTML형태로 만들어주면 됨
        					
        					//let jsObj = JSON.parse(result); //JSON -> jsObj ( jquery 낮은 버젼 )
        					//let json = JSON.stringify(jsObj); //jsObj -> JSON
        					
        					let html = "";
        					html += "<form id='ajax'>";
        					html += "번호: <input name='seq' value='"+result.seq+"'>";
        					html += "이름: <input name='name' value='"+result.name+"'>";
        					html += "주소: <input name='addr' value='"+result.addr+"'>";
        					html += "날짜: <input name='rdate' value='"+result.rdate+"'>";
        					html += "</form>";
        					
        					$('#name').val("");
        					$("#container").html(html);
        				}, 
        				error: function(xhr,status,error){
        					//alert('error: ' + error + ": 존재하지 않는 SEQ");
        				}
        			});
        		});
        		
        		//$('#searchOk02').on('click', function(){
        		$('#name').on('keyup', function(){
					$.ajax({
						url: "ajax.do?m=search02", 
						type: 'post',
						data: { na: $('#name').val()},
						success: function(result){
							console.log(result);
							
							let html = "";
							html += "<center>";
							html += "<table style='border:1px solid black' width='50%'>";
							html += "<tr>";
							html += "<th>번호</th>";
							html += "<th>이름</th>";
							html += "<th>주소</th>";
							html += "<th>날짜</th>";
							html += "</tr>";
							if(result.length == 0){
								html += "<tr>";
								html += "<td align='center' colspan='4'>검색 결과 없음</td>";
								html += "<tr>";
							}else{
								for(const address of result){
								    html += "<tr>";
								    html += "<td align='center'>"+address.seq+"</td>";
								    html += "<td align='center'>"+address.name+"</td>";
									html += "<td align='center'>"+address.addr+"</td>";
									html += "<td align='center'>"+address.rdate+"</td>";
								    html += "<tr>";
								}
							}
							html += "</table>"
							html += "</center>";
							
							$('#seq').val("");
							$('#container').html(html);
						}
					});
        		});
        	});
        </script>
    </head>
    <body>
        <center>
        <h2>비동기통신</h2>
        
        번호: <input type="text" name="seq" id="seq"/>
        <input type="button" value="번호 검색" id="searchOk01"/>
        <br/>
        
        이름: <input type="text" name="name" id="name"/>
        <input type="button" value="이름 검색" id="searchOk02"/>
        <br/>
        
        <br/>
        <div id="container"></div>
        <br/><br/>
        
        <a href="../">인덱스</a><br/><br/>
        </center>
    </body>
</html>
