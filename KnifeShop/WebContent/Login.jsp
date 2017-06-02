<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="FooterFirst.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 <style>
 
 	#error{
		position: absolute;
	    top: 502px;
	    left: 450px;
	    color: red;
	    font-size: 26px;
	}
	
	#errorRegister{
		position: absolute;
		top: 502px;
	    left: 300px;
	    color:red;
	    font-size: 26px;
	    text-align: center;
	}
	
	#usernameAndPassword{
		position: absolute;
		top:350px;
		right:371px;
		font-size:15px;
	}
	/* 
			#loginHeadingId b{
				right: 353px;
			    top: -63px;
			    position: absolute;
			    font-size: 50px;
		} */
	
		
	#makeRegistrationButton{
		position: absolute;
		top: 545px;
	    left: 300px;
	    color:red;
	    font-size: 26px;
	}
	
	#username{
		font-size:26px;
	}
	
	#password{
		font-size:26px;
	}
	
	body{
		background-color:#65623c;
		background-size:cover;
	}
	
	
 		.container {
		    overflow: hidden;
		    background-color: /* #333 */ black;
		    font-family: Arial;
		    top: 0;
		    left:0;
		    width: 100%;
		    position: absolute;
		}
		
		.container a {
		    
		    float: left;
		    font-size: 43px;
		    width: 100%;
		    height: 100%;
		    color: white;
		    text-align: center;
		    text-decoration: none;
		}
 
	 
	 li {
   		 float: left;
   		 height:5.8em;
	}
	li:nth-child(1) {
 	   text-align:left;
 	    width: 10%;
 	    size:40px;
	}
	
	li:nth-child(2) {
 	   text-align:center;
 	    width: 70%;
 	    color:white
	}
	
	 .logo form>input {
	    width: 68px;
	    padding: 10px;
	    padding-top: 18px;
	      padding-bottom: 18px;
	}
	
	li .welcome {
		float:none;
		align: center;
	}
	
	body{
		background-color:#65623c;
		background-size:cover;
	}
	
	ul {
		list-style-type: none;
		margin: 0;
		padding: 0;
		overflow: hidden;
		background-color: #333;
	}
	
	.back{
		align: right;
	}
	
	li:nth-child(3) {
  	  float: left;
 	  width: 10%;
 	  
	}
	li:nth-child(3)>form{
		display: inline-block;
	    box-sizing: border-box;
	    width: 100%;
	    height: 100%;
	    padding: 5px 5px 10px 0px;
	}
	li:nth-child(3) form>a>input{
		position: relative;
		right: -80px;
	    width: 149%;
	    height: 100%;
	    padding: 0px;
	    font-size: 20px;
    
	}
	
	.loginClass{
		    position: absolute;
		    top: 380px;
		    left: 513px;
		    font-size: large;
		}
		
		.loginClass .submitButton{
			float:right;
		}
		
		.loginClass .text{
			font-size:23px;
		}
		
		.containerImage {
			     position: relative;
			    width: 27%;
			    top: 209px;
			    left: 514px;
			}
			
			.image {
			  display: block;
			  width: 100%;
			  height: auto;
			}
			
			.overlay {
			  position: absolute;
			  bottom: 0;
			  left: 0;
			  right: 0;
			  background-color: #008CBA;
			  overflow: hidden;
			  width: 0;
			  height: 100%;
			  transition: .5s ease;
			}
			
			.containerImage:hover .overlay {
			  width: 100%;
			}
			
			.textImage {
			  white-space: nowrap; 
			  color: white;
			  font-size: 20px;
			  position: absolute;
			  overflow: hidden;
			  top: 50%;
			  left: 50%;
			  transform: translate(-50%, -50%);
			  -ms-transform: translate(-50%, -50%);
			}
		
 </style>

</head>
<body>

	<ul class="container">
	
		  <li class = "logo">
			<form action = "MainPageServlet" method = "GET">
			   <input type="image" src="./GetStoreLogoImageServlet" alt="logo">
			</form>
		  </li>
		  
		  <li class = "welcome">
		 	<h1><span><b> Log in to the adventure! </b></span></h1>
		  </li>
		  
		  <li class = "back">
		  	<form action = "RegisterServlet" method = "GET">
			   <a><input type="submit" value = "Have not registration?" style = "background-color:#3366ff; color:#6600cc;
				 text-decoration: underline;"></a>
			</form>
		  </li>

		</ul>
		
		<div class="containerImage">
			<img src="./images/login.jpg" alt = "login"  class="image">
				<div class="overlay">
			   		 <div class="textImage">You are one of us</div>
			    </div>
		</div>
	
	<form action = "LoginServlet" method = "POST" class = "loginClass">
	
	<table name = "table">
		<!-- <div id = "usernameAndPassword"> -->
			<!-- <h1 id = "loginHeadingId"><b>Log in</b></h1> -->
			<tr>
				<td><font color="#0099ff" id = "username">Username : </font></td>
				<td><input type = "text" name = "username"></td>
			</tr>
			<tr>
				<td><font color="#0099ff" id = "password">Password : </font></td>
				<td><input type = "password" name = "password"></td>
			</tr>
		<!-- </div> -->
		</table>
		<input type = "submit" value = "Login" class = "submitButton">
	</form>
	
	
	
	<div id = "error">		
		<c:if test="${not empty error}">
			<c:out value="${error}"></c:out>
		</c:if>
	</div>	
	
	
			
		<c:if test="${not empty errorRegister}">
			<div id = "errorRegister"><c:out value="${errorRegister}"></c:out></div>	
			<form action ="RegisterServlet" method = "GET">
				<div id = "makeRegistrationButton"><input type = "submit" value = "Make a registration" 
					style ="width: 769px;height: 25px; font-size:15px;">
	    		</div>
			</form>
		</c:if>
	
	

</body>
</html>