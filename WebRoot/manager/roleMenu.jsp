<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'roleMenu.jsp' starting page</title>
    
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
<table id="roleMenu"></table>

<div id="roleMenu_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add-new" plain="true" onclick="roleMenu_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="roleMenu_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="roleMenu_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="roleMenu_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="roleMenu_tool.redo();">取消选择</a>		
	</div>
</div>

<form id="roleMenu_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>选择角色：<input id="roleNm" name="roleNm" class="textbox" style="width:200px;"></p>
	<p>分配权限：<input id="auth" class="textbox" name="auth" style="width:205px;"></p>
</form>
<form id="roleMenu_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<input type="hidden" name="roleId_edit" class="textbox" style="width:200px;">
	<p>角　　色：<input id="roleNm_edit" name="roleNm_edit" class="textbox"  style="width:200px;" disabled="disabled"></p>
	<p>分配权限：<input id="auth_edit" class="textbox" name="auth_edit" style="width:205px;"></p>
</form>
<form id="toRoleMenu" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<input type="hidden" name="roleId_to" class="textbox" style="width:200px;">
	<p>角　　色：<input id="roleNm_to" name="roleNm_to" class="textbox" style="width:200px;" disabled="disabled"></p>
	<p>拥有权限：<input id="auth_to" class="textbox" name="auth_to" style="width:205px;"></p>
</form>
	<script type="text/javascript" src="js/manager_roleMenu.js"></script>
  </body>
</html>
