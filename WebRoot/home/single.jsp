<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Single</title>
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
				<a href="index.jsp"><img src="../images/logo.png" alt=""/></a>
			</div>
			<div class="top-nav">
				<span class="menu"><img src="../images/menu-icon.png" alt=""/></span>
				<ul class="nav1">
					<li><a href="index.jsp">首页</a></li>
					<li><a href="strange-buildings.jsp">楼房</a></li>
					<li><a href="strange-buildings.jsp">房间</a></li>
					
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
	<!--blog-->
	<div class="blog">
		<div class="container" style="margin-bottom:50px">
			<div class="blog-top">
				<div class="col-md-9 blog-left">
					<div class="blog-main">
						<a class="bg">DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE</a>
						<p>Post by <a href="#">Admin</a> on saturday, March 02, 2015  <a href="#">5 comments</a></p>
					</div>								
					<div class="blog-main-one">
						<div class="blog-one blog-sng">
							<img src="../images/blog-2.jpg" alt="" />
							<p class="sngl">Nunc quis turpis sed tortor viverra dictum. Etiam in cursus libero, ut cursus turpis. Nulla quis nulla pellentesque, commodo lorem sed, ultrices leo. Duis magna mauris, cursus vitae lacus ut, consequat malesuada magna. Duis bibendum pellentesque nisi eget volutpat.
							   Nunc rhoncus ultrices lectus.Aliquam eu dui quis orci ultrices eleifend ut non massa. Duis commodo, ante in vulputate iaculis, libero ex fringilla dolor, id laoreet augue lorem in velit. Aenean vitae risus tempor, suscipit turpis elementum, lacinia justo. Aenean tortor orci, tristique sed libero vel, vulputate elementum lectus.
							   Aliquam dapibus nisi et gravida accumsan. Nam aliquam blandit dapibus. Aliquam bibendum vestibulum neque, eu dapibus nunc congue vitae. Praesent mollis dolor eget elementum auctor.</p>
						</div>	
						
					</div>	
					
						<div class="related">
							<h3>推荐</h3>
							<div class="related-bottom">
								<div class="col-md-3 related-left">
									<img src="../images/r-1.jpg" alt="" />
									<h4>Cum sociis sere</h4>
								</div>
								<div class="col-md-3 related-left">
									<img src="../images/r-2.jpg" alt="" />
									<h4>Vestibulum est ex</h4>
								</div>
								<div class="col-md-3 related-left">
									<img src="../images/r-3.jpg" alt="" />
									<h4>Ut tincidunt</h4>
								</div>
								<div class="col-md-3 related-left">
									<img src="../images/r-4.jpg" alt="" />
									<h4> Aliquam eu quam</h4>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
						
				</div>
				
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--blog-->
	
	<!--footer-->
	<div class="footer">
		<div class="container">
			<div class="footer-top">
				 <div class="col-md-5 footer-left">
					<p>Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="#"></a> - More Templates <a href="#" target="_blank" title=""></a></p>
				</div>
				
				<div class="col-md-6 footer-right">
					<a href="index.jsp"><img src="../images/lg.png" alt="" /></a>
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