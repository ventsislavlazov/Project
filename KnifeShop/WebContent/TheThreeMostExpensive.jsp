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
		
			#tableId{
				overflow: scroll;
				position: absolute;
			    top: -35px;
   				left: 17px;
			    margin: 165px 63px -165px 133px;
			    width: 1170px;
			}
			
			h2{
   				margin-left: 154px;
			}
			
			.w3-small {
			    font-size: 18px!important;
			}
			
			#tableId>thead>tr>th{
				background-color:lightgrey;
				height: 40px;
			}
			
	</style>

</head>
<body>

	<h2>The three most expensive knifes</h2>
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
		 	<c:forEach items="${mostExpenisve}" var="record">
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

</body>
</html>