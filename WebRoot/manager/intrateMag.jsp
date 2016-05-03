<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'intrateMag.jsp' starting page</title>
    
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
<table id="intrateMag"></table>

<div id="intrateMag_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add-new" plain="true" onclick="intrateMag_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="intrateMag_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="intrateMag_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="intrateMag_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="intrateMag_tool.redo();">取消选择</a>		
	</div>
</div>

<form id="intrateMag_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>期　数：<input id="bystgSum" name="bystgSum" class="textbox" style="width:200px;"></p>
	<p>利　率：<input id=intrate name="intrate" class="textbox" style="width:200px;">%</p>
</form>
<form id="intrateMag_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<input type="hidden" id="intrateId_edit" name="intrateId_edit" class="textbox" style="width:200px;">
	<p>期　数：<input id="bystgSum_edit" name="bystgSum_edit" class="textbox" style="width:200px;"></p>
	<p>利　率：<input id="intrate_edit" name="intrate_edit"　class="textbox"  style="width:200px;">%</p>
</form>
	<script type="text/javascript" src="js/manager_intrateMag.js"></script>
  </body>
</html>
