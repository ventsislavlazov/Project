<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="FooterAdmins.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style>

	li button, li a{
		 width: 200px;
	}
	
		ul {
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
		    overflow: hidden;
		    background-color: #333;
		}
	
		li {
		    float: left;
		}
		
		.container {
		    overflow: hidden;
		    background-color: #333;
		    font-family: Arial;
		}
		
		.container a {
		    float: left;
		    font-size: 16px;
		    color: white;
		    text-align: center;
		    padding: 14px 16px;
		    text-decoration: none;
		}
		
		<!-- ADD-REMOVE PROPERTIES	 -->
		.dropdown {
		    float: left;
		    overflow: hidden;
		}
		
		.dropdown .dropbtn {
		    cursor: pointer;
		    font-size: 16px;    
		    border: none;
		    outline: none;
		    color: white;
		    padding: 14px 16px;
		    background-color: inherit;
		}
		
		/* .container .dropdown .dropdown-content a:hover{
			background-color: green;
		} */ 
		
		/* .dropdown:hover .dropdown-content {
			display: block;
		} */
		
		.dropdown button:hover {
		    background-color: red;
		}
		
		/* .dropdown-content {
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		    min-width: 160px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    z-index: 1;
		}
		
		.dropdown-content a {
		    color: black;
		    text-decoration: none;
		    display: block;
		    text-align: left;
		}	 */
		
		/* .dropdown-content a:hover {
			background-color: #f1f1f1;
		} */
		
		.dropdown-content {
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		   	min-width: 50px;
		    max-width: 212px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    z-index: 1;
		}
		
		.dropdown-content a {
		    color: black;
		    padding: 12px 16px;
		    text-decoration: none;
		    display: block;
		    text-align: left;
		    font-size: 14px;
		}
		
		.dropdown-content a input{
			 background-color: inherit;
			 border: none;
		}
		
		.dropdown-content form a:hover{
			background-color: #d9d9d9;
		}
		
		.dropdown:hover .dropdown-content {
		    display: block;
		}
		
		<!-- STATISTICS	 -->
		 .statistics{
			float: left;
		    overflow: hidden;
		}
		
		.statistics .statist{
			cursor: pointer;
		    font-size: 16px;    
		    border: none;
		    outline: none;
		    color: white;
		    padding: 14px 16px;
		    background-color: inherit;
		}
		
		.container a:hover, .statistics button:hover  {
		    background-color: red;
		}
		
		.contentStatistics{
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		   /*  width: 127px; */
		    min-width: 50px;
		    max-width: 212px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    z-index: 1;
		}
		
		.contentStatistics a {
		    color: black;
		    padding: 12px 16px;
		    text-decoration: none;
		    display: block;
		    text-align: left;
		    font-size: 14px;
		}
		
		.contentStatistics a input{
			 background-color: inherit;
			 border: none;
		}
		
		.contentStatistics form a:hover{
			background-color: #d9d9d9;
		}
		
		.statistics:hover .contentStatistics {
		    display: block;
		}
		
		
		<!-- FINANCE -->
		.finance{
			float: left;
		    overflow: hidden;
		}
		
		.finance .financeButton{
			cursor: pointer;
		    font-size: 16px;    
		    border: none;
		    outline: none;
		    color: white;
		    padding: 14px 16px;
		    background-color: inherit;
		    width: 222px;
		}
		
		.container a:hover, .finance button:hover  {
		    background-color: red;
		}
		
		.finance-content{
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		    max-width: 222px;
		    min-width: 50px;
		    max-width: 222px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    z-index: 1;
		}
		
		.finance-content a {
		    color: black;
		    padding: 12px 16px;
		    text-decoration: none;
		    display: block;
		    text-align: left;
		    font-size: 14px;
		}
		
		.finance-content form a{
			width: 217px;
		}
		
		.finance-content a input{
			 background-color: inherit;
			 border: none;
		}
		
		.finance-content form a:hover{
			background-color: #d9d9d9;
		}
		
		.finance:hover .finance-content {
		    display: block;
		}
		
		.show {
		    display: block;
		}
		
	</style>
	

</head>
<body>
<div id = "success">		
	<c:if test="${not empty success}">
		<c:out value="${success}"></c:out>
	</c:if>
</div>	

<div id = "error">		
	<c:if test="${not empty error}">
		<c:out value="${error}"></c:out>
	</c:if>
</div>	

	<ul class="container">
		  <li><a href="AdminOnlyServlet">Home</a></li>
		  
		  <!-- statistika za prodajbi -->
		   <li class="statistics">
		    <button class="statist" onclick="functionStatistics()">statistics</button>
		  	<div class="contentStatistics" id="dropdownStatisticsId">
		  		<!-- trite naj - prodawani noja -->
		  		 <form action = "TheThreeBestSellersServlet" method = "GET">
			      	<a><input type='submit' value = "Best sellers"></a> 
			      </form>
		    	<!-- trite naj - skapi noja -->
		    	<form action = "TheThreeMostExpensiveServlet" method = "GET">
			      	<a><input type='submit' value = "Highest price"></a> 
			    </form>
		    	<!-- trite naj - ewtini noja -->
		    	<form action = "TheThreeCheapestServlet" method = "GET">
			      	<a><input type='submit' value = "Lowest price"></a> 
			     </form>
		    	<!-- trite s naj - golqmo koli4estwo noja -->
		    	<form action = "TheThreeWithHighestQuantitySerlvet" method = "GET">
			      	<a><input type='submit' value = "Highest amount"></a> 
			     </form>
		    	<!-- trite s naj - malko koli4estwo noja -->
		    	<form action = "TheThreeWithLowestQuantitySerlvet" method = "GET">
			      	<a><input type='submit' value = "Lowest amount"></a> 
			     </form>
		    	<!-- wsi4ki prodadeni nojowe -->
		    	<form action = "AllSoldKnifesServlet" method = "GET">
			      	<a><input type='submit' value = "All sold knifes"></a> 
			     </form>
		    </div>
		  </li>
		  
		  <li class="dropdown">
		    <button class="dropbtn" onclick="myFunction()">add/remove properties</button>
		    <div class="dropdown-content" id="myDropdown">
		      <form action = "AddKnifeServlet" method = "GET">
		      	<a><input type='submit' value = "Add knife"></a> 
		      </form>
		       <form action = "AddManufactorToDBServlet" method = "GET">
		      		<a><input type='submit' value = "Add manufactor"></a>
		       </form>
		      <form action = "AddSteelToDBServlet" method = "GET">
		      		<a><input type='submit' value = "Add steel"></a>
		      </form>
		      <form action = "AddLockToDBServlet" method = "GET">
		     	 	<a><input type='submit' value = "Add lock"></a>
		     </form>
		     <form action = "AddQuantityServlet" method = "GET">
		     	 	<a><input type='submit' value = "Add quantity"></a>
		     </form>
		     <form action = "./RemoveKnifeServlet" method = "GET">
		     	 	<a><input type='submit' value = "Remove knife"></a>
		     </form>
		    </div>
		  </li> 
		  
		  <li class = "finance">
		  	 <button class = "financeButton" onclick="financeFunction()">finance</button>
		  	 <div class = "finance-content" id = "financeDropdownId">
		  	 	<form action = "AddMoneyToTheShopServlet" method = "GET">
			    	<a><input type='submit' value = "Add money to the shops safe"></a> 
			    </form>
			    <form action = "GetMoneyFromTheShopServlet" method = "GET">
			    	<a><input type='submit' value = "Get money from the shops safe"></a> 
			    </form>
			    <form action = "ShowAllTransactionsToTheShopServlet" method = "GET">
			    	<a><input type='submit' value = "Show all transactions"></a> 
			    </form>
		  	 </div>
		  </li>
		  <li class="logout">
		  	<a href="LogoutServlet">Log out</a>
		  </li>
		</ul>

	<script>
	
	//za6to ne moga da izpolzwam edni i sa6ti imena na klasowe ili na id-ta za nqkolko ne6ta, naprimer za dropdawn i manufactor
	
	//ADD-REMOVE PROPERTIES	
	/* When the user clicks on the button, 
		toggle between hiding and showing the dropdown content */
		function myFunction() {
		    document.getElementById("myDropdown").classList.toggle("show");
		}
		
		// Close the dropdown if the user clicks outside of it
		window.onclick = function(e) {
		  if (!e.target.matches('.dropbtn')) {
		    var myDropdown = document.getElementById("myDropdown");
		      if (myDropdown.classList.contains('show')) {
		        myDropdown.classList.remove('show');
		      }
		  }
		}
		
		$(document).click(function(){
			  $("#myDropdown").hide();
		});
		
		//STATISTICS
		function functionStatistics() {
		   	document.getElementById("dropdownStatisticsId").classList;
		}
		// Close the dropdown if the user clicks outside of it
		window.onclick = function(e) {
		  if (!e.target.matches('.statist')) {
		    var myDropdown = document.getElementById("dropdownStatisticsId");
		      if (myDropdown.classList.contains('show')) {
		        myDropdown.classList.remove('show');
		      }
		  }
		}
		
		$(document).click(function(){
			  $("#dropdownStatisticsId").hide();
		});
		
		//CONTROL
		function controlFunction() {
		   	document.getElementById("controlDropdownId").classList;
		}
		// Close the dropdown if the user clicks outside of it
		window.onclick = function(e) {
		  if (!e.target.matches('.controlButton')) {
		    var myDropdown = document.getElementById("controlDropdownId");
		      if (myDropdown.classList.contains('show')) {
		        myDropdown.classList.remove('show');
		      }
		  }
		}
		
		$(document).click(function(){
			  $("#controlDropdownId").hide();
		});
		
		//FINANCE
		function financeFunction() {
		   	document.getElementById("financeDropdownId").classList;
		}
		// Close the dropdown if the user clicks outside of it
		window.onclick = function(e) {
		  if (!e.target.matches('.financeButton')) {
		    var myDropdown = document.getElementById("financeDropdownId");
		      if (myDropdown.classList.contains('show')) {
		        myDropdown.classList.remove('show');
		      }
		  }
		}
		
		$(document).click(function(){
			  $("#financeDropdownId").hide();
		});
		
		/* var timeOut = 1000 * 30;
		var lastActivity = new Date().getTime();
		var checkTimeout;
		checkTimeOut = function(){
		    if(new Date().getTime() > lastActivity + timeOut){
		        // redirect to timeout page
		    }else{
		        window.setTimeout(checkTimeOut, 1000); // check once per second
		    }
		} */

		
	</script>
	

</body>
</html>