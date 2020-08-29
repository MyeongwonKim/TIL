<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
$(document).ready(function(){
	$("#btn").click(Func1);
});
function Func1(){
	$.ajax({
		url:"t3.jsp",
		dataType:"json",
		success:function(data){
			var htmlStr="<ol>";
			for(var index in data.list){
			htmlStr+="<li><a href=javasciprt:void(0); onclick=Func2("+index+");>"+data.list[index].id+"</a></li>"
			}
			htmlStr+="</ol>";
			$("#container1").html(htmlStr);
		},
		error:function(a){
			console.log(a);
		}
	});
}
function Func2(index){
	$.ajax({
		url:"t3.jsp",
		dataType:"json",
		success:function(data){
			var str = "<p>";
			str = "pw: " + data.list[index].pw +"<br>" 
			      + "addr: " + data.list[index].addr + "<br>"
			      + "tel: " + data.list[index].tel + "</p>";
			$("#container2").html(str);
		},
		error:function(a){
			console.log(a);
		}
	});
}
</script>
</head>
<body>
<input type="button" value="회원목록보기" id="btn">
<div id="container1">
</div>
<div id="container2">
</div>
</body>
</html>