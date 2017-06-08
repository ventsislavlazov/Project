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

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<style>	
	
		#text{
			max-width: 401px;
		}
	
		#tableIdPrim{
			position: absolute;
			top: 250px;
			left:488px;
		}
		
		#success{
			position: absolute;
			top: 470px;
			left: 920px;
			color: green;
			font-size:45px;
			text-align: center;
		}
		
		#errorNotEnaughtMoneyInTheShop{
			position: absolute;
			top: 470px;
			left: 920px;
			color: red;
			font-size:45px;
			text-align: center;
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
	
	<div id = "errorNotEnaughtMoneyInTheShop">		
		<c:if test="${not empty errorNotEnaughtMoneyInTheShop}">
			<c:out value="${errorNotEnaughtMoneyInTheShop}"></c:out>
		</c:if>
	</div>	

	<div  id = "tableIdPrim">
		<h2>Take money from the shop</h2>
			<table name = "table" border="1" id = "tableId">
				<thead>
					<tr id = "firstRow"> 
					 <th align="center">all money</th> 
					 <th align="center">how much do you want to take</th>
				 	</tr>
				 </thead>
				<tr>
				 <td align="center"><c:out value="${allMoney}"/></td>
				 <td align="center">
					 <form action = "GetMoneyFromTheShopServlet" method = "POST" id = "formId">
					 	<input type = "number" name = "moneyToTake" min="1">
					 	<input type = "submit" value = "take money">
					 </form>
				 </td>
				</tr>
			</table>
			<textarea rows="6" cols="50" name="commentName" id = "text" form="formId" placeholder = "Enter comment here..."></textarea>
	</div>

</body>
</html>