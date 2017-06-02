<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var = "colorVar" scope = "session" value="${sessionScope.color}" />

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