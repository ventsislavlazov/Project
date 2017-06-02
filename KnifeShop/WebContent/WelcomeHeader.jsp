<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<style>
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
 
	/*  .login_button a{
	 	position:absolute;
		right:57px;
		top:0px;
	 }
	 
	 .register_button a{
	 	position:absolute;
		right:0px;
		top:0px;
	 } */
	 
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
		
    width: 100%;
    height: 100%;
    padding: 0px;
    font-size: 20px;
    
	}
	
	li:nth-child(4) {
  	  float: left;
 	   width: 10%;
	}
	
	li:nth-child(4)>form{
	display: inline-block;
    box-sizing: border-box;
    width: 100%;
    height: 100%;
    padding: 5px 5px 10px 5px;
	}
	li:nth-child(4) form>a>input{
		
    width: 100%;
    height: 100%;
    padding: 0px;
     font-size: 20px;
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
		 	<h1><span><b> Welcome to the knife shop </b></span></h1>
		  </li>
		  	
		  <li class="login_button">
		    <form action="LoginServlet" method = "GET" id = "loginButton" style = "display:inline-block; width:100%;height:100%; ">
				<a><input style = "background-color:#3366ff; color:#6600cc;
				 text-decoration: underline;" type = "submit"  value = "Login"></a>
			</form>
		  </li> 
		  
		  <li class = "register_button">
		  	 <form action="RegisterServlet" method = "GET" id = "registerButton">
				<a><input style = "background-color:#3366ff; color:#6600cc;
				 text-decoration: underline;" type = "submit" value = "Register"></a>
			 </form>
		  </li>

		</ul>

</body>
</html>