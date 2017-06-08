<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="Admin.jsp"%>
<%@ include file="ColorAllPages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<style>
	
		#success {
		    position: absolute;
		    top: 84px;
		    left: 832px;
		    color: green;
		    font-size: 23px;
		    text-align: center;
		}
		
		#tableId{
			overflow: scroll;
			width: 1170px;
    		height: 100px;
    		margin:10px 8px 60px 130px;
		}
		
		h2{
			margin-left:135px;
		}
		
		#firstRow{
				background-color:lightgrey;
			}
			
			#tableId>thead>tr>th{
				background-color:lightgrey;
				height: 40px;
			}
		
	</style>

</head>
<body>

<div id = "success">		
	<c:if test="${not empty success}">
		<c:out value="${success}"></c:out>
	</c:if>
</div>

	<h2>All users</h2>
	<table name = "table" border="1" id = "tableId">
		<thead>
			<tr id = "firstRow"> 
				 <th align="center">Username</th> 
				 <th align="center">Email</th>
				 <th align="center">Age</th>
				 <th align="center">Is admin</th>
				 <th align="center">Change status</th>
			</tr>
		</thead>
		<c:forEach items="${allUsers}" var="record">
			 		<tr>
			 		 	<td align="center"><c:out value="${record.username}"/></td>
			 		 	<td align="center"><c:out value="${record.email}"/></td>
			 		 	<td align="center"><c:out value="${record.age}"/></td>
			 		 	<td align="center"><c:out value="${record.admin}"/></td>
			 			<td align="center">
				 			<form action = "./DeleteUserFromDBServlet" method = "POST">
				 				<input type = "hidden" name = "user" value = "${record.id}">
				 				<input type = "submit" value = "delete user &quot;${record.username}&quot">
				 			</form>
			 			</td>
			 		</tr>
		 </c:forEach>
	</table>

</body>
</html>