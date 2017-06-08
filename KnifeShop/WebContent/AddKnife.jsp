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

	#success {
	    position: absolute;
	    top: 165px;
	    left: 946px;
	    color: green;
	    font-size: 23px;
	    text-align: center;
	}
	
	#errorDublicate{
		position: absolute;
		top: 270px;
		left: 250px;
		color: red;
		font-size:45px;
		text-align: center;
	}
		
	#errorNumbers{
		position: absolute;
		top: 270px;
		left: 250px;
		color: red;
		font-size:45px;
		text-align: center;
	}
	
	#errorFields{
		position: absolute;
		top: 270px;
		left: 450px;
		color: red;
		font-size:45px;
		text-align: center;
	}
	
	#errorImage{
		position: absolute;
		top: 270px;
		left: 250px;
		color: red;
		font-size:45px;
		text-align: center;
	}
	
	#submitAddKnife{
			position: absolute;
		    top: 80px;
		    left: 1182px;
		}

	#idAddKnife{
		position: absolute;
	    top: 200px;
	    left: 148px;
	    font-size: 12px;
	}
	
	
	h2{
		position: absolute;
		top: -50px;
		left:5px;
	}
	
	#tableId>thead>tr>th{
			background-color:lightgrey;
			height: 40px;
		}
	
</style>

</head>
<body>

	<div id = "errorDublicate">		
		<c:if test="${not empty errorDublicate}">
			<c:out value="${errorDublicate}"></c:out>
		</c:if>
	</div>	
	
	<div id = "success">		
		<c:if test="${not empty success}">
			<c:out value="${success}"></c:out>
		</c:if>
	</div>	
	
	<div id = "errorFields">		
		<c:if test="${not empty errorFields}">
			<c:out value="${errorFields}"></c:out>
		</c:if>
	</div>	
	
	<div id = "errorNumbers">		
		<c:if test="${not empty errorNumbers}">
			<c:out value="${errorNumbers}"></c:out>
		</c:if>
	</div>	
	
	<div id = "errorImage">		
		<c:if test="${not empty errorImage}">
			<c:out value="${errorImage}"></c:out>
		</c:if>
	</div>	

	<form action="./AddKnifeServlet" method = "POST" id = "idAddKnife" name = "form" onsubmit="return validateForm()" enctype="multipart/form-data">
	<h2>Add knife</h2>
	<table name = "table" border="1" class = "w3-responsive" id = "tableId">
		<thead>
			 <tr> 
				 <th align="center">Select manufactor</th> 
				 <th align="center">Add model name</th>
				 <th align="center">Select steel</th>
				 <th align="center">Select lock</th>
				 <th align="center">Is it folder</th>
				 <th align="center">Add blade length</th>
				 <th align="center">Add price</th>
				 <th align="center">Add image</th>
			 </tr>
		</thead>
		 
		 <tr> 
			 <td>
				  <select name="manufactor">
				  <c:forEach items="${manufactor}" var="record">
				  	<option value="${record}">"${record}"</option>
				  </c:forEach>
			  </td>
			  <td>
			  	<input type = "text" name = "model">
			  </td>
			  <td>
				  <select name="steel">
					  <c:forEach items="${steel}" var="record">
					  	<option value="${record}">"${record}"</option>
					  </c:forEach>
			  </td>
			  <td>
			  	<select name="lock">
				  <c:forEach items="${lock}" var="record">
				  	<option value="${record}">"${record}"</option>
				  </c:forEach>
			  </td>
			  <td>
			  	<select name="folder" cam-variable-type="Boolean">
				<option value="true">Yes</option>
				<option value="false">No</option>
			  </td>
			  <td>
			  	<input type = "number" name = "length" min="1">
			  </td>
			  <td>
			  	<input type = "number" name = "price" min="1">
			  </td>
			  <td>
			  	 <!-- сега потребителят не може да избере друг формат освен jpg, png и gif -->
			  	 <input type = "file" name = "image" id = "imageID" accept="image/x-png,image/gif,image/jpeg">
			  	 <!-- <input type = "file" name = "image" id = "imageID" onchange="validateImage()"> -->
			  	<!-- <button onclick="validateImage()">Add image</button> -->
			  </td>
		  </tr>
		  <!-- </select> -->
		  </table>
		  <br><br>
		  <input type="submit" id = "submitAddKnife" >
	  
	</form>
	
	<script>
		function validateForm(){
			var a=document.forms["form"]["model"].value;
			var b=document.forms["form"]["length"].value;
			var c=document.forms["form"]["price"].value;
			if(a==null || a==""){
				 alert("You should fill the \"model\" field");
				 return false;
			}
			if(b<=0){
				console.log("in alert")
				alert("The length should be > 0");
				return false;
			}
			if(c<=0){
				alert("The price should be > 0");
				return false;
			}
			
			if( document.getElementById("imageID").files.length == 0 ){
			    alert("You should upload an image");
				return false;
			}
		}
		
		 /* function validateImage(){
			var x = document.createElement("INPUT");
		    x.setAttribute("type", "file");
		    document.body.appendChild(x);
			//var fileName = $('#imageID').val();
		    var fileName = document.forms['form']['image'].files[0];
			var extension = (fileName.slice('.')[fileName.slice('.').length - 1])/* .toLowerCase() */;
			//if(extension != 'jpeg' || extension != 'gif' || extension != 'png')
			  // alert('Choose a supported image format');
		//}  
	</script>

</body>
</html>