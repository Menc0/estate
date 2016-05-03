<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'payCrcs.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
<table id="payCrcs"></table>

<div id="payCrcs_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-email" plain="true" onclick="payCrcs_tool.smsRemind();">短信提醒</a> -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="payCrcs_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="payCrcs_tool.redo();">取消选择</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		<form name="payCrcs_search" method="post" action="" id ="payCrcs_search">
		房　号：<input id="hosNo_search" name="hosNo_search" class="textbox" >
		身份证：<input id="cusIcd_search" name="cusIcd_search" class="textbox" >
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="payCrcs_tool.search();">　查询</a>
	</form>
	</div>
</div>


<form id="payCrcs_sms" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>号　码：<input id="cusTel" name="cusTel" class="textbox" style="width:180px;"></p>
	<p>内　容：<textarea id=smsContent name="smsContent" style="width:180px;height:200px"/></p>
</form>
<form id="payCrcs_email" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>Email：<input id="emailAddress" name="emailAddress" class="textbox" style="width:380px;"></p>
	<p>主  题：<input id="emailTitle" name="emailTitle" class="textbox" style="width:380px;"></p>
	<p>内  容：<textarea id="emailContent" name="emailContent" class="textbox" style="width:380px;height:300px"/></p>
</form>
<form id="payCrcs_paying" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<input type="hidden" id="selNo_paying" name="selNo_paying"/>
	<p>房　号：<input id="hosNo_paying" name="hosNo_paying" class="textbox" style="width:180px;" disabled="disabled"></p>
	<p>身份证：<input id="cusIcd_paying" name="cusIcd_paying" class="textbox" style="width:180px;"disabled="disabled"></p>
	<p>姓　名：<input id="cusNm_paying" name="cusNm_paying" class="textbox" style="width:180px;"disabled="disabled"></p>
	<p>金　额：<input id="Mon_paying" name="Mon_paying" class="textbox" style="width:180px;"disabled="disabled"></p>
</form>
<!-- <form id="payCrcs_paydtl" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<input type="hidden" id="selNo_paying" name="selNo_paying"/>
	<p>已还期数：<input id="payedSum_paydtl" name="payedSum_paydtl" class="textbox" style="width:180px;" disabled="disabled"></p>
	<p>剩余期数：<input id="unPaySum_paydtl" name="unPaySum_paydtl" class="textbox" style="width:180px;" disabled="disabled"></p>
	<p>逾期未还：<input id="overdue_paydtl" name="overdue_paydtl" class="textbox" style="width:180px;" disabled="disabled"></p>
</form> -->

	<script type="text/javascript" src="js/manager_payCrcs.js"></script>
  </body>
</html>
