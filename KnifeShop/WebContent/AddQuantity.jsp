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
	
		#errorQuantity{
			position: absolute;
			top: 470px;
			left: 920px;
			color: red;
			font-size:45px;
			text-align: center;
		}
		
		#success {
		    position: absolute;
		    top: 83px;
		    left: 817px;
		    color: green;
		    font-size: 23px;
		    text-align: center;
		}
		
		#tableId{
			overflow: scroll;
			width: 1170px;
    		height: 600px;
    		margin:10px 8px 60px 130px;
		}
		
		h2{
			margin-left:130px;
		}
		
		#firstRow{
			background-color:lightgrey;
		}
		
		#tableId>thead>tr>th{
				background-color:lightgrey;
				height: 40px;
			}
			
			.w3-small {
			    font-size: 17px!important;
			}
		
	</style>

</head>
<body>

<div id = "errorQuantity">		
	<c:if test="${not empty errorQuantity}">
		<c:out value="${errorQuantity}"></c:out>
	</c:if>
</div>	

<div id = "success">		
	<c:if test="${not empty success}">
		<c:out value="${success}"></c:out>
	</c:if>
</div>	

		<h2>All knifes</h2>
		<table name = "table" border="1" class = "w3-responsive w3-small" id = "tableId">
			<thead>
				<tr id = "firstRow"> 
				 <th align="center">manufactor</th> 
				 <th align="center">model name</th>
				 <th align="center">steel</th>
				 <th align="center">lock</th>
				 <th align="center">folder</th>
				 <th align="center">length</th>
				 <th align="center">price</th>
				 <th align="center">image</th>
				 <th align="center">current quantity</th>
				 <th align="center">add quantity</th>
			 	</tr>
		 	</thead>
		 	<c:forEach items="${allKnifes}" var="record">
			 		<tr>
			 		 	<td align="center"><c:out value="${record.manufactor}"/></td>
			 		 	<td align="center"><c:out value="${record.model}"/></td>
			 		 	<td align="center"><c:out value="${record.steel}"/></td>
			 		 	<td align="center"><c:out value="${record.lock}"/></td>
			 		 	<td align="center"><c:out value="${record.folder}"/></td>
			 		 	<td align="center"><c:out value="${record.length}"/></td>
			 		 	<td align="center"><c:out value="${record.price}"/></td>
			 		 	<td align="center"><img src="./GetImageServlet?image=${record.imageName}" alt="knife image" style="width:200px;height:150px;"></td>
			 			<td align="center"><c:out value="${record.quantity}"/></td>
			 			<td align="right">
				 			<form action = "AddQuantityServlet" method = "POST"  name = "form${record.id}">
				 				<input type = "number" name = "quantity" min="1">
				 				<input type="hidden" name="knifeId" value="${record.id}">
				 				<input type = "submit" onclick = "validateForm(${record.id})">
				 			</form>
			 			</td>
			 		</tr>
		 	</c:forEach>
		</table>
	
	<script>
		function validateForm(e){
			var a=document.forms["form"+e]["quantity"].value;
			if(a<=0){
				alert("The quantity should be > 0");
				event.preventDefault(); 
				return false;
			}
		}
	</script>

</body>
</html>