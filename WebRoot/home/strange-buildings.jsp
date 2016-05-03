<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Strange-buildings</title>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="../css/style.css" rel='stylesheet' type='text/css' />
<script src="../js/jquery-1.7.1.js"></script>

<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
		</script>
<!--start-smoth-scrolling-->
</head>
<body>
	<!--header-->
	<div class="header-top" id="home">
		<div class="container">
			<div class="header-logo">
				<a href="index.html"><img src="images/logo.png" alt=""/></a>
			</div>
			<div class="top-nav">
				<span class="menu"><img src="images/menu-icon.png" alt=""/></span>
				<ul class="nav1">
					<li><a href="index.html">é¦é¡µ </a></li>
					<li><a href="strange-buildings.html">æ¥¼æ¿</a></li>
					<li><a href="strange-buildings.html">æ¿å±</a></li>
					
				</ul>
				<!-- script-for-menu -->
				 <script>
				   $( "span.menu" ).click(function() {
					 $( "ul.nav1" ).slideToggle( 300, function() {
					 // Animation complete.
					  });
					 });
				</script>
				<!-- /script-for-menu -->
			</div>
			<div class="social-icons">
				<ul>
					<li><a href="#"><span class="twit"> </span></a></li>
					<li><a href="#"><span class="fb"> </span></a></li>
					<li><a href="#"><span class="g"> </span></a></li>
				</ul>
			</div>
			<div class="clearfix"> </div>
		</div>
		<div class="search-box">
			<div id="sb-search" class="sb-search">
				<form>
					<input class="sb-search-input" placeholder="Enter your search term..." type="search" name="search" id="search">
					<input class="sb-search-submit" type="submit" value="">
					<span class="sb-icon-search"> </span>
				</form>
			</div>
		</div>
		
	</div>
	<!--//header-->
	<!--search-scripts-->
					<script src="js/classie.js"></script>
					<script src="js/uisearch.js"></script>
						<script>
							new UISearch( document.getElementById( 'sb-search' ) );
						</script>
	<!--//search-scripts-->
	<!--buildings-->
	<div class="buildings">
		<div class="container">
			<div class="buildings-top">
				<div class="building-one">
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-1.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-2.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-3.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-5.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="building-one">
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-4.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-5.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-6.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-7.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="building-one">
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-8.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-2.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-5.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="col-md-3 building-left">
						<a href="single.html"><img src="images/b-4.jpg" alt="" /></a>
						<a href="single.html"><h4>Aliquam ac mattis</h4></a>
						<p>Integer hendrerit tortor nec enim finibus consequat. Aliquam eu quam quam. Aliquam posuere neque</p>
						<div class="build-btn">
							<a href="single.html" class="hvr-shutter-out-horizontal">Read more</a>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!--buildings-->
	<!--read-->
	
	<!--read-->
	<!--footer-->
	<div class="footer">
		<div class="container">
			<div class="footer-top">
				 <div class="col-md-5 footer-left">
					<p>Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="#"></a> - More Templates <a href="#" target="_blank" title=""></a></p> 
				</div>
				
				<div class="col-md-6 footer-right">
					<a href="index.html"><img src="images/lg.png" alt="" /></a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<script type="text/javascript">
									$(document).ready(function() {
										/*
										var defaults = {
								  			containerID: 'toTop', // fading element id
											containerHoverID: 'toTopHover', // fading element hover id
											scrollSpeed: 1200,
											easingType: 'linear' 
								 		};
										*/
										
										$().UItoTop({ easingType: 'easeOutQuart' });
										
									});
								</script>
		<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
	</div>
	<!--footer-->
</body>
</html>