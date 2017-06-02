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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style>
	
		#successAddMore{
			position: absolute;
			top: 270px;
			left: 250px;
			color: green;
			font-size:45px;
			text-align: center;
		}
		
		#successAdd{
			position: absolute;
			top: 270px;
			left: 250px;
			color: green;
			font-size:45px;
			text-align: center;
		}
		
		#notEnaughtQuantity{
			position: absolute;
			top: 270px;
			left: 250px;
			color: red;
			font-size:45px;
			text-align: center;
		}
		
		#noQuantity{
			position: absolute;
			top: 270px;
			left: 250px;
			color: red;
			font-size:45px;
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
		
		.dropbtnSort {
		    background-color: #4CAF50;
		    color: white;
		    padding: 16px;
		    font-size: 25px;
		    border: none;
		    cursor: pointer;
		    margin-right: 5px;
		}
		
		.dropdownSort {
		    position: relative;
		    display: inline-block;
		}
		
		.dropdown-contentSort {
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		    min-width: 160px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    z-index: 1;
		    min-width: 50px;
		    max-width: 123px;
		}
		
		.dropdown-contentSort a {
		    color: black;
		    padding: 12px 16px;
		    text-decoration: none;
		    display: block;
   			font-size: 13px;
		}
		
		.dropdown-contentSort a button{
			 background-color: inherit;
			 border: none;
		}
		
		.dropdown-contentSort a:hover {background-color: #f1f1f1}
		
		.dropdownSort:hover .dropdown-contentSort {
		    display: block;
		}
		
		.dropdownSort:hover .dropbtnSort {
		    background-color: #3e8e41;
		}
		
	</style>
	

</head>
<body>


	<div class="dropdownSort w3-right">
	  <button class="dropbtnSort">Sort by</button>
	  <div class="dropdown-contentSort">
		  <!-- <form id = "formHighestPrice">
		    <a><input type = "submit" value = "highest price"></a>
		  </form> -->
		   <a><button id = "highestPriceButton">highest price</button></a>
		   <a><button id = "lowestPriceButton">lowest price</button></a>
		   <a><button id = "highestLengthButton">highest length</button></a>
		   <a><button id = "lowestLengthButton">lowest length</button></a>
		  <!--  <a><button id = "highestlengthButton">highest length</button></a>
		   <a><button id = "lowest lengthButton">lowest length</button></a> -->
		  <!--  <form action = "SortByLowestPriceServlet" method = "GET">
		    <a><input type = "submit" value = "lowest price"></a>
		  </form> -->
	   	  <!-- <form action = "SortByHighestLengthServlet" method = "GET">
		    <a><input type = "submit" value = "highest lenght"></a>
		  </form> -->
		  <!-- <form action = "SortByLowestLengthServlet" method = "GET">
		    <a><input type = "submit" value = "lowest lenght"></a>
		  </form> -->
	  </div>
	</div>

	<div id = "noQuantity">		
		<c:if test="${not empty noQuantity}">
			<c:out value="${noQuantity}"></c:out>
		</c:if>
	</div>	
	
	<div id = "notEnaughtQuantity">		
		<c:if test="${not empty notEnaughtQuantity}">
			<c:out value="${notEnaughtQuantity}"></c:out>
		</c:if>
	</div>	

	<div id = "successAddMore">		
		<c:if test="${not empty successAddMore}">
			<c:out value="${successAddMore}"></c:out>
		</c:if>
	</div>	
	
	<div id = "successAdd">		
		<c:if test="${not empty successAdd}">
			<c:out value="${successAdd}"></c:out>
		</c:if>
	</div>	

	<h2>All knifes</h2>
		<table name = "table" border="1" id = "tableId">
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
			 <th align="center">add to basket</th>
		 	</tr>
		 </thead>
			 <tbody id = "tbodyId">
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
				 			<td align="center">
					 			<form action = "AddToBasketServlet" method = "POST">
					 				<input type = "number" name = "quantityToAdd" min="1" placeholder="Quantity">
					 				<input type="hidden" name="knifeToAddId" value="${record.id}">
					 				<input type="hidden" name="knifeToAddModel" value="${record.model}">
					 				<input type = "submit" value = "add">
					 			</form>
				 			</td>
				 		</tr>
			 	</c:forEach>
			 </tbody>
		</table>

</body>

	<script>
	
	$(document).ready(function() {
		console.log("in")
		 $("#lowestPriceButton").click(function() {
			    $.ajax({
			      url: "SortByLowestPriceServlet",
				  type: "GET",
			      dataType : 'json',
			      success: function(response) {
			    	  //delete tbody
			    	  $("#tbodyId").empty();
			    	  //fill tbody
			    	  for ( var i in response) {
			    		  
			    		  $('#tbodyId')
			    		  .append(
			    		  "<tr><td align='center'>"+response[i].manufactor+"</td>"
			    		  +"<td align='center'>"+response[i].model+"</td>"
			    		  +"<td align='center'>"+response[i].steel+"</td>"
			    		  +"<td align='center'>"+response[i].lock+"</td>"
			    		  +"<td align='center'>"+response[i].folder+"</td>"
			    		  +"<td align='center'>"+response[i].length+"</td>"
			    		  +"<td align='center'>"+response[i].price+"</td>"
			    		  +"<td align='center'><img src='./GetImageServlet?image=${'"+ response[i].imageName+"'}' alt='knife image' style='width:200px;height:150px;'></td>"
			    		  +"<td align='center'><form action = 'AddToBasketServlet' method = 'POST'>"
			    		  +"<input type = 'number' name = 'quantityToAdd' min='1' placeholder='Quantity'>"
			    		  +"<input type='hidden' name='knifeToAddId' value="+ response[i].id +">"
			    		  +"<input type='hidden' name='knifeToAddModel' value="+ response[i].model +">"
			    		  +"<input type = 'submit' value = 'add'>"
			    		  +"</form></td></tr>")
			    		 
			    		  document.getElementById('tableId').style.visibility = 'visible';
			    			}
			    	  }

			       })
			       
			       
		  });
		  
		 $("#highestPriceButton").click(function() {
			    $.ajax({
			      url: "SortByHighestPriceServlet",
				  type: "GET",
			      dataType : 'json',
			      success: function(response) {
			    	  //delete tbody
			    	  $("#tbodyId").empty();
			    	  //fill tbody
			    	  for ( var i in response) {
			    		  
			    		  $('#tbodyId')
			    		  .append(
			    		  "<tr><td align='center'>"+response[i].manufactor+"</td>"
			    		  +"<td align='center'>"+response[i].model+"</td>"
			    		  +"<td align='center'>"+response[i].steel+"</td>"
			    		  +"<td align='center'>"+response[i].lock+"</td>"
			    		  +"<td align='center'>"+response[i].folder+"</td>"
			    		  +"<td align='center'>"+response[i].length+"</td>"
			    		  +"<td align='center'>"+response[i].price+"</td>"
			    		  +"<td align='center'><img src='./GetImageServlet?image=${'"+ response[i].imageName+"'}' alt='knife image' style='width:200px;height:150px;'></td>"
			    		  +"<td align='center'><form action = 'AddToBasketServlet' method = 'POST'>"
			    		  +"<input type = 'number' name = 'quantityToAdd' min='1' placeholder='Quantity'>"
			    		  +"<input type='hidden' name='knifeToAddId' value="+ response[i].id +">"
			    		  +"<input type='hidden' name='knifeToAddModel' value="+ response[i].model +">"
			    		  +"<input type = 'submit' value = 'add'>"
			    		  +"</form></td></tr>")
			    		 
			    		  document.getElementById('tableId').style.visibility = 'visible';
			    			}
			    	  }

			       })
			    });
		 
		 $("#highestLengthButton").click(function() {
			    $.ajax({
			      url: "SortByHighestLengthServlet",
				  type: "GET",
			      dataType : 'json',
			      success: function(response) {
			    	  //delete tbody
			    	  $("#tbodyId").empty();
			    	  //fill tbody
			    	  for ( var i in response) {
			    		  
			    		  $('#tbodyId')
			    		  .append(
			    		  "<tr><td align='center'>"+response[i].manufactor+"</td>"
			    		  +"<td align='center'>"+response[i].model+"</td>"
			    		  +"<td align='center'>"+response[i].steel+"</td>"
			    		  +"<td align='center'>"+response[i].lock+"</td>"
			    		  +"<td align='center'>"+response[i].folder+"</td>"
			    		  +"<td align='center'>"+response[i].length+"</td>"
			    		  +"<td align='center'>"+response[i].price+"</td>"
			    		  +"<td align='center'><img src='./GetImageServlet?image=${'"+ response[i].imageName+"'}' alt='knife image' style='width:200px;height:150px;'></td>"
			    		  +"<td align='center'><form action = 'AddToBasketServlet' method = 'POST'>"
			    		  +"<input type = 'number' name = 'quantityToAdd' min='1' placeholder='Quantity'>"
			    		  +"<input type='hidden' name='knifeToAddId' value="+ response[i].id +">"
			    		  +"<input type='hidden' name='knifeToAddModel' value="+ response[i].model +">"
			    		  +"<input type = 'submit' value = 'add'>"
			    		  +"</form></td></tr>")
			    		 
			    		  document.getElementById('tableId').style.visibility = 'visible';
			    			}
			    	  }

			       })
			    });
		 
		 $("#lowestLengthButton").click(function() {
			    $.ajax({
			      url: "SortByLowestLengthServlet",
				  type: "GET",
			      dataType : 'json',
			      success: function(response) {
			    	  //delete tbody
			    	  $("#tbodyId").empty();
			    	  //fill tbody
			    	  for ( var i in response) {
			    		  
			    		  $('#tbodyId')
			    		  .append(
			    		  "<tr><td align='center'>"+response[i].manufactor+"</td>"
			    		  +"<td align='center'>"+response[i].model+"</td>"
			    		  +"<td align='center'>"+response[i].steel+"</td>"
			    		  +"<td align='center'>"+response[i].lock+"</td>"
			    		  +"<td align='center'>"+response[i].folder+"</td>"
			    		  +"<td align='center'>"+response[i].length+"</td>"
			    		  +"<td align='center'>"+response[i].price+"</td>"
			    		  +"<td align='center'><img src='./GetImageServlet?image=${'"+ response[i].imageName+"'}' alt='knife image' style='width:200px;height:150px;'></td>"
			    		  +"<td align='center'><form action = 'AddToBasketServlet' method = 'POST'>"
			    		  +"<input type = 'number' name = 'quantityToAdd' min='1' placeholder='Quantity'>"
			    		  +"<input type='hidden' name='knifeToAddId' value="+ response[i].id +">"
			    		  +"<input type='hidden' name='knifeToAddModel' value="+ response[i].model +">"
			    		  +"<input type = 'submit' value = 'add'>"
			    		  +"</form></td></tr>")
			    		 
			    		  document.getElementById('tableId').style.visibility = 'visible';
			    			}
			    	  }

			       })
			    });
		
		 
		 

		  })
		  
	</script>
</html>