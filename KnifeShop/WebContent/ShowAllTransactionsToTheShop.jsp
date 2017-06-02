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
		
	</style>

</head>
<body>

	<h2>All transactions</h2>
		<table name = "table" border="1" id = "tableId">
			<thead>
				<tr id = "firstRow"> 
				 <th align="center">transaction id</th> 
				 <th align="center">transacted money</th> 
				 <th align="center">current money in the <br>shop after last transaction</th>
				 <th align="center">are this money added</th>
				 <th align="center">comment</th>
			 	</tr>
		 	</thead>
		 	<c:forEach items="${allTransactions}" var="record">
			 		<tr>
			 		 	<td align="center"><c:out value="${record.transactionId}"/></td>
			 		 	 <c:if test = "${record.transactedMoney<0}">
					        <td align="center"><c:out value="${record.transactedMoney*(-1)}"/></td>
					      </c:if>
			 		 	<c:if test = "${record.transactedMoney>=0}">
					        <td align="center"><c:out value="${record.transactedMoney}"/></td>
					      </c:if>
			 		 	<td align="center"><c:out value="${record.allMoney}"/></td>
			 		 	<td align="center"><c:out value="${record.addedMoney}"/></td>
			 		 	<td align="center"><c:out value="${record.event}"/></td>
			 		</tr>
		 	</c:forEach>
		</table>
	

</body>
</html>