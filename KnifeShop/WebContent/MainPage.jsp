<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="FooterFirst.jsp"%>
<%@ include file="WelcomeHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- take care of all our styling needs and all device and browser differences: -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">

<!-- The fa fa classes are Font Awesome Icon classes.
To use these classes you must link to a Font Awesome library: -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

<title>Insert title here</title>

<style>
 		
	#picturesId{
		position: absolute;
    	width: 50%;
    	top: 260px;
    	left: 350px;
    	/* margin: 200px 350px 0px 350px; */
	}
		
</style>

</head>
<body>
	
	
		
		<section class="w3-container w3-center w3-content w3-responsive" style="max-width:600px; position: absolute; top: 200px; left: 480px;">
 			 <h2 class="w3-wide w3-opacity">Because we love knives</h2>
 		</section>
		
		<!-- Slide Show -->
		<section id = "picturesId">
		  <img class="mySlides" src="https://static.knifecenter.com/graphics/slider/large/kershaw-oso-sweet-2017-slider.jpg"
		  style="width:100%">
		  <img class="mySlides" src="https://static.knifecenter.com/graphics/slider/large/FoxKnives417Slider.jpg"
		  style="width:100%">
		  <img class="mySlides" src="https://static.knifecenter.com/graphics/slider/large/Olamic_Slider.jpg"
		  style="width:100%">
		  <img class="mySlides" src="https://static.knifecenter.com/graphics/slider/large/brs-rep-cf-slider-17.jpg"
		  style="width:100%">
		  <img class="mySlides" src="https://static.knifecenter.com/graphics/slider/large/spyderco-manix-2-exclusive-preorder-slider_UPDATE.jpg"
		  style="width:100%">
		</section>

		<script>
			// Automatic Slideshow - change image every 3 seconds
			var myIndex = 0;
			carousel();
			
			function carousel() {
			    var i;
			    var x = document.getElementsByClassName("mySlides");
			    for (i = 0; i < x.length; i++) {
			       x[i].style.display = "none";
			    }
			    myIndex++;
			    if (myIndex > x.length) {myIndex = 1}
			    x[myIndex-1].style.display = "block";
			    setTimeout(carousel, 3000);
			}
		</script>
	

</body>
</html>