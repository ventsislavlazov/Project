<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="User.jsp"%>
<%@ include file="Footer.jsp"%>
<%@ include file="ColorAllPages.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
	
		#success {
		    position: absolute;
		    top: 100px;
		    left: 665px;
		    color: green;
		    font-size: 23px;
		    text-align: center;
		}
		
		#errorNotEnoghtQuantity{
			position: absolute;
		    top: 100px;
		    left: 469px;
		    color: red;
		    font-size: 23px;
		    text-align: center;
		}
		
		#tableId{
			overflow: scroll;
			width: 1170px;
/*     		height: 600px;
 */    		margin:10px 8px 60px 130px;
		}
		
		h2{
			margin-left:140px;
			margin-top:82px;
		}
		
		/* #firstRow{
			background-color:lightgrey;
			height: 40px;
		} */
		
		#tableId>thead>tr>th{
			background-color:lightgrey;
			height: 40px;
		}
		
		#buyButton{
			margin-right:140px;
			margin-top:100px;
		}
		
		#titleAndBuyButton h2{
			float:left;
		}
		
		#titleAndBuyButton #buyButton{
			float:right;
		}
		
	</style>
</head>
<body>

	<div id = "errorNotEnoghtQuantity">		
		<c:if test="${not empty errorNotEnoghtQuantity}">
			<c:out value="${errorNotEnoghtQuantity}"></c:out>
		</c:if>
	</div>

	<div id = "success">		
		<c:if test="${not empty success}">
			<c:out value="${success}"></c:out>
		</c:if>
	</div>

	<div id = "titleAndBuyButton">
		<h2>All knifes in the basket</h2>
		<form action = "BuyEverythingFromTheBasketServlet" id = "forma" method = "POST"  name = "form" onsubmit="return validateForm()" enctype="multipart/form-data">
			<c:forEach items="${knifesInTheBasket}" var="record">
				<input type="hidden" name="knifeManufactor" value="${record.manufactor}">
			</c:forEach>
			<input type = "submit" value = "Buy the knives from the basket"  id = "buyButton">
		</form>
	</div>
		<table name = "table" border="1" id = "tableId" class = "w3-responsive w3-small">
			<thead>
				<tr id = "firstRow"> 
				 <th align="center">manufactor</th> 
				 <th align="center">model name</th>
				 <th align="center">steel</th>
				 <th align="center">lock</th>
				 <th align="center">folder</th>
				 <th align="center">length</th>
				 <th align="center">price</th>
				 <th align="center">quantity</th>
				 <th align="center">image</th>
				<th align="center">remove from basket</th>
			 	</tr>
		 	</thead>
		 	<tbody>
		 	<c:forEach items="${knifesInTheBasket}" var="record">
			 		<tr>
			 		 	<td align="center"><c:out value="${record.manufactor}"/></td>
			 		 	<td align="center"><c:out value="${record.model}"/></td>
			 		 	<td align="center"><c:out value="${record.steel}"/></td>
			 		 	<td align="center"><c:out value="${record.lock}"/></td>
			 		 	<td align="center"><c:out value="${record.folder}"/></td>
			 		 	<td align="center"><c:out value="${record.length}"/></td>
			 		 	<td align="center"><c:out value="${record.price}"/></td>
			 		 	<td align="center"><c:out value="${record.quantityBasket}"/></td>
			 		 	<td align="center"><img src="./GetImageServlet?image=${record.imageName}" alt="knife image" style="width:200px;height:150px;"></td>
			 			<td align="center">
				 			<form action = "RemoveFromBasketServlet" method = "POST">
				 				<input type = "number" name = "quantityToRemove" min="1" placeholder = "Quantity to remove" required>
				 				<input type="hidden" name="knifeQuantityInTheBasket" value="${record.quantityBasket}">
				 				<input type="hidden" name="knifeId" value="${record.id}">
				 				<input type="hidden" name="knifeModel" value="${record.model}">
				 				<input type = "submit" value = "Remove from basket">
				 			</form>
			 			</td>
			 		</tr>
		 	</c:forEach>
		 	</tbody>
		</table>
	
		<script>
		/* document.getElementById("forma").addEventListener("submit", function(event){
		    event.preventDefault()
		}); */
		function validateForm(){
			var a=document.forms["form"]["knifeManufactor"];
			if(a == null || a==""){
				 alert("The basket is empty");
				 return false;
			}
		}
	</script>

</body>
</html>