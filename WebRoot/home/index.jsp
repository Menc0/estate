<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>主页</title>
<link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="../css/style.css" rel='stylesheet' type='text/css' />
<link href="../css/owl.carousel.css" rel="stylesheet">
<link href="../css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/swipebox.css">
<script src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../js/move-top.js"></script>
<script type="text/javascript" src="../js/easing.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('jsp,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
		</script>
</head>
<body>
	<div class="header-top" id="home">
		<div class="container">
			<%-- <div class="header-logo">
				<a href="${pageContext.request.contextPath}/homeAction/home.do"><img src="../images/logo.png" alt=""/></a>
			</div> --%>
			 <div class="top-nav">
				<ul class="nav1">
					<li><a href="#">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/homeAction/getAllBuild.do">楼房</a></li>
					<li>
						<a href="${pageContext.request.contextPath}/homeAction/getAllHouse.do">房间</a>
					</li>
				</ul>
				
				 <script>
				   $( "span.menu" ).click(function() {
					 $( "ul.nav1" ).slideToggle( 300, function() {
					 // Animation complete.
					  });
					 });
				</script>
				
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
					<script src="../js/classie.js"></script>
					<script src="../js/uisearch.js"></script>
						<script>
							new UISearch( document.getElementById( 'sb-search' ) );
						</script>
	<div class="banner">
		<div class="container">
			
		</div>
	</div>
	<div class="gallery-cursual">
		
			<script>
				$(document).ready(function() {
					$("#owl-demo").owlCarousel({
						items : 5,
						lazyLoad : true,
						autoPlay : true,
						pagination : false,
					});
				});
			</script>
		<div id="owl-demo" class="owl-carousel text-center">
		<c:forEach items="${home.recmdBuild}" var="build" varStatus="j">
			<div class="item g1 popup-with-zoom-anim" href="#small-dialog${j.count}">
				<img class="lazyOwl" data-src="../upload/${build.BLD_IMG }" alt="name" style="height:200px">
				<div class="caption">
					<h3>${build.BLD_NO}${build.BLD_NM}</h3>
					<div class="s-btn">
						<a href="#">READ MORE</a>
					</div>
				</div>
			</div>
		</c:forEach>
		</div>
		<c:forEach items="${home.recmdBuild}" var="build" varStatus="j">
		<div class="caption-popup">
				<div id="small-dialog${j.count}" class="mfp-hide innercontent">
					<h4>${build.BLD_NO}</h4>
					<img class="img-responsive cap" src="../upload/${build.BLD_IMG }" title="postname" style="height:200px"/>
					<p>位于${build.BLD_SITE},高：${build.FLO_SUM}层,总面积：${build.BLD_AREA}, 总投资：￥${build.BLD_INVEST_MON}</p>
					<a class="morebtn" href="${pageContext.request.contextPath}/homeAction/getBuildIntro.do?bldNo=${build.BLD_NO}">ReadMore</a>
				</div>						  
		</div>
		</c:forEach>
			<script>
						$(document).ready(function() {
						$('.popup-with-zoom-anim').magnificPopup({
							type: 'inline',
							fixedContentPos: false,
							fixedBgPos: true,
							overflowY: 'auto',
							closeBtnInside: true,
							preloader: false,
							midClick: true,
							removalDelay: 300,
							mainClass: 'my-mfp-zoom-in'
						});
																						
						});
				</script>	
		</div>
				<script src="../js/jquery.magnific-popup.js" type="text/javascript"></script>
				<script src="../js/owl.carousel.js"></script>
	<div class="address">
		<div class="container">
			
			<div style="border-bottom:3px #14B9D5 solid"><h3>推荐房间</h3></div>
		</div>
	</div>
	<div class="articles">
		<div class="container">
			<div class="articles-top">
			<c:forEach items="${home.recmdHouse}" var="sum"   end="2"  varStatus="j">
				<div class="col-md-4 articles-left">
					<c:forEach items="${home.recmdHouse}" var="recmdHouse" step="3" begin="${j.count-1}" varStatus="i">
					<div class="art-one">
						<a href="${pageContext.request.contextPath}/homeAction/getHouseIntro.do?hosNo=${recmdHouse.HOS_NO}&bldNo=${recmdHouse.BLD_NO}">
							<img src="../upload/${recmdHouse.HOS_IMG}" alt="" />
						</a>
						<div class="s-btn">
						<c:if test="${recmdHouse.liked ne null}">
							<a  id="likeHos${j.count}${i.count}" onclick="dislikeHos('likeHos${j.count}${i.count}','${recmdHouse.HOS_NO}')">
							点赞${recmdHouse.likeTotal}</a>
						</c:if>
						<c:if test="${recmdHouse.liked eq null}">
						<a  id="likeHos${j.count}${i.count}" onclick="likeHos('likeHos${j.count}${i.count}','${recmdHouse.HOS_NO}')">
						点赞${recmdHouse.likeTotal}</a>
						</c:if>
						</div>
						<div class="art-btm">
							<a href="${pageContext.request.contextPath}/homeAction/getHouseIntro.do?hosNo=${recmdHouse.HOS_NO}&bldNo=${recmdHouse.BLD_NO}"><h3>${recmdHouse.HOS_NO}</h3></a>
							<p>位于${recmdHouse.BLD_NO}楼,户型：${recmdHouse.HOS_TP},总面积：${recmdHouse.HOS_AREA}, 总价格：￥${recmdHouse.SELL_PCE}</p>
						</div>
					</div>
				</c:forEach>
					
				</div>
		</c:forEach>
				
				
			<div class="clearfix"></div>
			<div class="load" style="border-bottom:3px #14B9D5 solid">
				<!-- <a href="#" style="float:right">Load More</a> -->
				
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript">
	function dislikeHos(id,hosNo){
		alert("已点赞");
		 /* $.ajax({
				url : "/estate/homeAction/removeCusLikeHos.do",
				data : {
					ip:'${pageContext.request.remoteAddr}',
					hosNo:hosNo,
				},
				success:function(data){
					$("#"+id+"").text("点赞"+(parseInt($("#"+id+"").text().replace("取消点赞",''))-1));
					$("#"+id+"").removeAttr("onclick");
					$("#"+id+"").attr("onclick","likeHos('"+id+"','"+hosNo+"')");
				}
			});  */
	}
	function likeHos(id,hosNo){
		 $.ajax({
			url : "/estate/homeAction/saveCusLikeHos.do",
			data : {
				ip:'${pageContext.request.remoteAddr}',
				hosNo:hosNo,
			},
			success:function(data){
				$("#"+id+"").text("点赞"+(parseInt($("#"+id+"").text().replace("点赞",0))+1));
				$("#"+id+"").removeAttr("onclick");
				$("#"+id+"").attr("onclick","dislikeHos('"+id+"','"+hosNo+"')");
			}
		}); 
	}
	</script>
	<script src="../js/jquery.swipebox.min.js"></script> 
	    <script type="text/javascript">
			jQuery(function($) {
				$(".swipebox").swipebox();
			});
		</script>
	<script type="text/javascript" src="../js/jquery.mixitup.min.js"></script>
<script type="text/javascript">
$(function () {
	
	var filterList = {
	
		init: function () {
		
			// MixItUp plugin
		// http://mixitup.io
		$('#portfoliolist').mixitup({
			targetSelector: '.portfolio',
			filterSelector: '.filter',
			effects: ['fade'],
			easing: 'snap',
			// call the hover effect
			onMixEnd: filterList.hoverEffect()
		});				
	
	},
	
	hoverEffect: function () {
	
		// Simple parallax effect
		$('#portfoliolist .portfolio').hover(
			function () {
				$(this).find('.label').stop().animate({bottom: 0}, 200, 'easeOutQuad');
				$(this).find('img').stop().animate({top: -30}, 500, 'easeOutQuad');				
			},
			function () {
				$(this).find('.label').stop().animate({bottom: -40}, 200, 'easeInQuad');
				$(this).find('img').stop().animate({top: 0}, 300, 'easeOutQuad');								
			}		
		);				
	
	}

};

// Run the show!
	
});	
</script>
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
</body>
</jsp>