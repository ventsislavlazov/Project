<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="Footer.jsp"%>
<%@ include file="ColorAllPages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<style>
	
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
		
		li:nth-child(4){
			float: right;
			right: 5px;
			top: 5px;
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
		
		.container span {
		    float: right;
		    padding: 14px 16px;
		    width:40px;
		    height:38px;
		}
		
		<!-- KNIFES	 -->
		.knifes_dropdown {
		    float: left;
		    overflow: hidden;
		}

		.knifes_dropdown .knifes_dropdown_button {
		    cursor: pointer;
		    font-size: 16px;    
		    border: none;
		    outline: none;
		    color: white;
		    padding: 14px 16px;
		    background-color: inherit;
		    width: 126px;
		}
		
		/* .container .dropdown .dropdown-content a:hover{
			background-color: green;
		} */ 
		
		/* .dropdown:hover .dropdown-content {
			display: block;
		} */
		
		.knifes_dropdown button:hover {
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
		
		.knifes_content {
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		    /* min-width: 160px; */
		     min-width: 50px;
		    max-width: 212px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    z-index: 1;
		}

		.knifes_content a {
		    color: black;
		    padding: 12px 16px;
		    text-decoration: none;
		    display: block;
		    text-align: left;
		    font-size: 14px;
		}
		
		.knifes_content a input{
			 background-color: inherit;
			 border: none;
		}
		
		.knifes_content form a:hover{
			background-color: #d9d9d9;
		}
		
		.knifes_content a:hover {
			background-color: #f1f1f1;
		}
		
		.knifes_dropdown:hover .knifes_content {
		    display: block;
		}

		<!-- INFORMATION -->
		.information{
			float: left;
		    overflow: hidden;
		}
		
		.information .information_button{
			cursor: pointer;
		    font-size: 16px;    
		    border: none;
		    outline: none;
		    color: white;
		    padding: 14px 16px;
		    background-color: inherit;
		    width: 126px;
		}
		
		.container a:hover, .information button:hover  {
		    background-color: red;
		}
		
		.information_content{
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		    width: 127px;
		    min-width: 127px;
		    max-width: 127px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    z-index: 1;
		}
		
		.information_content a {
		    color: black;
		    padding: 12px 16px;
		    text-decoration: none;
		    display: block;
		    text-align: left;
    		width: 127px;
		}
		
		.information_content a:hover {
			background-color: #f1f1f1
		}
		
		.information_content a input{
			 background-color: inherit;
			 border: none;
		}
		
		.information_content form a:hover{
			background-color: #d9d9d9;
		}
		
		.information:hover .information_content {
		    display: block;
		}
		
		<!-- BASKET -->
		
			/* .li:nth-child(4) #idBasket>#idBasketImage{
				float: right;
			    overflow: hidden;
			    max-width:40px;
			    max-heigh:38px;
			    padding: 9px, 8px, 7px, 8px;
			} */
			
		
		
		.show {
		    display: block;
		}
		
	</style>

</head>
<body>

	<ul class="container">
		  <li><a href="UserServlet" style = "width: 126px;">Home</a></li>
		  
		  <li class="knifes_dropdown">
		    <button class="knifes_dropdown_button" onclick="knifesFunction()">Knifes</button>
		    <div class="knifes_content" id="knfies_id">
		      <form action = "UserViewKnifesServlet" method = "GET">
		      	<a><input type='submit' value = "See all knifes"></a> 
		      </form>
		    </div>
		  </li> 
		  
		  <li class = "information">
		  	 <button class = "information_button" onclick="informationFunction()">Information</button>
		  	 <div class = "information_content" id = "information_id">
			  	 <form action = "AboutUsServlet" method = "GET">
			      	<a><input type='submit' value = "About us"></a> 
			     </form>
			     <form action = "ContactServlet" method = "GET">
			      	<a><input type='submit' value = "Contact us"></a> 
			     </form>
		  	 </div>
		  </li>
		  
		   <!-- <li class = "basket">
			   	<form action = "BasketServlet" method = "GET">
			   		<a href = "BasketServlet"><img  src="./GetShoppingBasketImageServlet" alt="shoppingBasket" width="38" height="38"></a>
			    </form>
		   </li> -->
		  
		   <li class = "basket">
			   	<form action = "BasketServlet" method = "GET" id = "idBasket" style = "width:35px; height:0px;">
			   		<input type="image" src="./GetShoppingBasketImageServlet" alt="shoppingBasket" id = "idBasketImage" style = "width: 34px; height: 36px; margin-top: 5px; margin-right: 5px; margin-bottom: -22px; padding-top: 5px; padding-right: 7px;">
			    </form>
		   </li>
		    <li class="logout">
		  		<a href="LogoutServlet">Log out</a>
		  	</li>
		</ul>
		
		
		<script>

	//KNIFES
	/* When the user clicks on the button, 
		toggle between hiding and showing the dropdown content */
		function knifesFunction() {
		    document.getElementById("knfies_id").classList;
		}
		
		// Close the dropdown if the user clicks outside of it
		window.onclick = function(e) {
		  if (!e.target.matches('.knifes_dropdown_button')) {
		    var myDropdown = document.getElementById("knfies_id");
		      if (myDropdown.classList.contains('show')) {
		        myDropdown.classList.remove('show');
		      }
		  }
		}
		
		$(document).click(function(){
			  $("#knfies_id").hide();
		});
		
		//INFORMATION
		function informationFunction() {
		   	document.getElementById("information_id").classList;
		}
		// Close the dropdown if the user clicks outside of it
		window.onclick = function(e) {
		  if (!e.target.matches('.information_button')) {
		    var myDropdown = document.getElementById("information_id");
		      if (myDropdown.classList.contains('show')) {
		        myDropdown.classList.remove('show');
		      }
		  }
		}
		
		$(document).click(function(){
			  $("#information_id").hide();
		});
		

	</script>
	

</body>
</html>