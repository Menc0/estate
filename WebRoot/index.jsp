<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- <script type="text/javascript" src="./js/jquery-1.7.1.js"></script> -->
	
	<script type="text/javascript">
	function search(){
	  var form = document.forms["userform1"];
		form.action = "/estate/houseMag/getHouseByHosNo?hosNo="+$("#hosNo").val();
		form.method="post";
		form.submit();
	  
	}
	function search1(){
		 $.ajax({
             type: "GET",
             url: "/estate/houseMag/queryHousemagByHosNoAjax.do",
             data: {hosNo:$("#hosNo").val()},
             dataType: "json",
             cache:false,
             success:function(data){
             alert("描述"+data[0].hosIntro);
             						}
             });
            }
       
      function menu(){
      $.ajax({
         type:"GET",
         url: "/estate/menu/queryMenuAjax.do",
         dataType:"json",
         cache:false,
         success:function(data){
         alert("菜单"+data[0].menuNm);
         } 
      
      });
      }
      
	</script>
	
  </head>
  
  <body>
  <%-- <button  onclick="menu()">测试菜单</button>
     <form action="" name="userform1"> 
		房号：<input type="text" id="hosNo" >
		<input type="button" value="查找" onclick="search1()">
	</form>
	<div id="div"></div>
	<table border="1">
		<tbody>
			<tr>
				<th>房间号</th>
				<th>描述</th>
			</tr>
			<c:if test="${!empty houseList}">
				<c:forEach items="${houseList}" var="house">
					<tr>
						<td>${house.hosNo}</td>
						<td>${house.hosIntro}</td>
					</tr>				
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div class="adjoined-bottom">
		<div class="grid-container">
			<div class="grid-width-100">
				<div id="editor">
					<h1>Hello world!</h1>
					
				</div>
			</div>
		</div>
	</div>
<script>
	initSample();
</script> --%>

${hosIntro}
</body>
</html>
