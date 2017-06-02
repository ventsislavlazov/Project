<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="ColorAllPages.jsp"%>
<c:choose>
    <c:when test="${which eq 'masterAdmin'}">
    	<%@ include file="Admin.jsp"%>
    </c:when>
     <c:when test="${which eq 'admin'}">
    	<%@ include file="AdminWithoutControlUsers.jsp"%>
    </c:when>
</c:choose>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<style>
		
		#success{
			position: absolute;
			top: 335px;
			left: 360px;
			color: green;
			font-size:45px;
			text-align: center;
		}
		
		#errorExists{
			position: absolute;
			top: 335px;
			left: 400px;
			color: red;
			font-size:45px;
			text-align: center;
		}
		
		#errorEmpty{
			position: absolute;
			top: 335px;
			left: 490px;
			color: red;
			font-size:45px;
			text-align: center;
		}
	
		#idAddLock{
			position: absolute;
			top: 250px;
			left:590px;
			font-size: smaller;
		}
		
		h2{
			font-size:25px;
		}
		
	</style>

</head>
<body>

	<div id = "success">		
		<c:if test="${not empty success}">
			<c:out value="${success}"></c:out>
		</c:if>
	</div>	
	
	<div id = "errorExists">		
		<c:if test="${not empty errorExists}">
			<c:out value="${errorExists}"></c:out>
		</c:if>
	</div>	
	
	<div id = "errorEmpty">		
		<c:if test="${not empty errorEmpty}">
			<c:out value="${errorEmpty}"></c:out>
		</c:if>
	</div>		

	<form action="./AddLockToDBServlet" method = "POST" id = "idAddLock" name = "form" onsubmit="return validateForm()">
		<h2>Add new lock</h2>
		<input type = "text" name = "addLockNameToDB" placeholder="Locks name">
		<input type = "submit">
	</form>
	
	<script>
		function validateForm(){
			var a=document.forms["form"]["addLockNameToDB"].value;
			if(a==null || a==""){
				 alert("You should fill the \"locks name\" field");
				 return false;
			}
		}
	</script>

</body>
</html>