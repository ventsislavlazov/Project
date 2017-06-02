<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="User.jsp"%>
<%@ include file="ColorAllPages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<style>
	
		#tableId>thead>tr>th{
				background-color:lightgrey;
				height: 40px;
			}
		
		h2 span{
			color: red;
		}
			
	</style>

</head>
<body>

<div class = w3-left>
	<h2><b><span>Special offer</span></b> to you! Our <b><span>CHEAPEST</span></b> offers!</h2>
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
			 	</tr>
		 	</thead>
		 	<c:forEach items="${cheapest}" var="record">
			 		<tr>
			 		 	<td align="center"><c:out value="${record.manufactor}"/></td>
			 		 	<td align="center"><c:out value="${record.model}"/></td>
			 		 	<td align="center"><c:out value="${record.steel}"/></td>
			 		 	<td align="center"><c:out value="${record.lock}"/></td>
			 		 	<td align="center"><c:out value="${record.folder}"/></td>
			 		 	<td align="center"><c:out value="${record.length}"/></td>
			 		 	<td align="center"><c:out value="${record.price}"/></td>
			 		 	<td align="center"><img src="./GetImageServlet?image=${record.imageName}" alt="knife image" style="width:200px;height:150px;"></td>
			 		</tr>
		 	</c:forEach>
		</table>
</div>
		
<div class = w3-right>
		<h2><b><span>LAST</span></b> knifes! <b><span>DONT MISS</span></b> them!</h2>
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
				 <th align="center">quantity</th>
			 	</tr>
		 	</thead>
		 	<c:forEach items="${lowestQuantity}" var="record">
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
			 		</tr>
		 	</c:forEach>
		</table>
</div>

</body>
</html>