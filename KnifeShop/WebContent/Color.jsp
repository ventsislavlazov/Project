<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<style>
	
		#color{
			position:absolute;
			top:100px;
			left:100px;
		}
		
	</style>

<title>Insert title here</title>
</head>
<body>

	<div id = "color">
		<form action = "ColorChooseServlet" method = "POST" id = "form">
			Select color:  <input type="color" name="colorName" value="#ffffff">
			<input type = "submit" value = "change" onclick = "setColor()">
		</form>
	</div>
	
	<c:set var = "colorVar" scope = "session" value = "${color}"/>
	
	<script>

		function setColor() {
			var message = "${colorVar}";
		    var x = document.getElementsByTagName("BODY")[0];
		    x.style.backgroundColor = message;
		}
		window.onload = setColor();
		
	</script>

</body>
</html>