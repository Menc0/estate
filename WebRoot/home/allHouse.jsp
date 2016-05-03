<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>所有房间</title>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="../css/style.css" rel='stylesheet' type='text/css' />
<script src="../js/jquery-1.7.1.js"></script>

<!---- start-smoth-scrolling---->
<script type="text/javascript" src="../js/move-top.js"></script>
<script type="text/javascript" src="../js/easing.js"></script>
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
			<%-- <div class="header-logo">
				<a href="${pageContext.request.contextPath}/homeAction/home.do"><img src="../images/logo.png" alt=""/></a>
			</div> --%>
			<div class="top-nav">
				<ul class="nav1">
					<li><a href="${pageContext.request.contextPath}/homeAction/home.do">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/homeAction/getAllBuild.do">楼房</a></li>
					<li><a href="#">房间</a></li>
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
			<!-- <div class="social-icons">
				<div class="bshare-custom icon-medium"><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到新浪Qing" class="bshare-sinaqing"></a><a title="分享到新浪Vivi" class="bshare-sinavivi"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
			</div> -->
			<div class="clearfix"> </div>
		</div>
		<div class="search-box">
			<div id="sb-search" class="sb-search">
				<form action="${pageContext.request.contextPath}/homeAction/searchHouseByHosTp.do">
					<input class="sb-search-input" placeholder="输入户型查找..." type="search" name="hosTp" id="hosTp">
					<input class="sb-search-submit" type="submit" value="">
					<span class="sb-icon-search"> </span>
				</form>
			</div>
		</div>
		
	</div>
	<!--//header-->
	<!--search-scripts-->
					<script src="../js/classie.js"></script>
					<script src="../js/uisearch.js"></script>
						<script>
							new UISearch( document.getElementById( 'sb-search' ) );
						</script>
	<!--//search-scripts-->
	<!--buildings-->
	<div class="buildings">
		<div class="container">
			<div class="buildings-top">
			<c:forEach items="${houseList}" var="sum"  varStatus="j" >
				<div class="building-one">
				<c:forEach items="${houseList}" var="house"  begin="${(j.count-1)*4}" end="${(j.count)*4-1}">
					<div class="col-md-3 building-left">
						<a href="${pageContext.request.contextPath}/homeAction/getHouseIntro.do?hosNo=${house.HOS_NO}&bldNo=${house.BLD_NO}">
							<div style="height:240px;width:240px">
								<img src="../upload/${house.HOS_IMG}" alt="无图片" height="230" width="240"/>
							</div>
						</a>
						<a href="${pageContext.request.contextPath}/homeAction/getHouseIntro.do?hosNo=${house.HOS_NO}&bldNo=${house.BLD_NO}">
							<h4>${house.HOS_NO}</h4>
						</a>
						<p>${house.HOS_TP}面积${house.HOS_AREA}总价￥${house.SELL_PCE}</p>
						<div class="build-btn">
							<a href="${pageContext.request.contextPath}/homeAction/getHouseIntro.do?hosNo=${house.HOS_NO}&bldNo=${house.BLD_NO}" class="hvr-shutter-out-horizontal">
								Read more
							</a>
						</div>
					</div>
				</c:forEach>
 			<div class="clearfix"></div>
				</div>
			</c:forEach>
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
					<p>Copyright &copy; 2015.menco-蔡文浩.</p> 
				</div>
				
				<%-- <div class="col-md-6 footer-right">
					<a href="${pageContext.request.contextPath}/homeAction/home.do"><img src="../images/lg.png" alt="" /></a>
				</div> --%>
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