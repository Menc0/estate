<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'wrkrInfo.jsp' starting page</title>
    
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
<table id="wrkrInfo"></table>

<form id="wrkrInfo_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>员工工号：<input id="wrkrNo" name="wrkrNo" class="textbox" style="width:200px;" disabled="disabled"></p>
	<p>&nbsp&nbsp&nbsp身份证：<input id="wrkrIcd" name="wrkrIcd" class="textbox" style="width:200px;"></p>
	<p>员工姓名：<input id="wrkrNm" name="wrkrNm" class="textbox" style="width:200px;"></p>
	<p>员工电话：<input id="wrkrTell" name="wrkrTell" class="textbox" style="width:200px;"></p>
	<p>员工职位：<input id="wrkrPst" name="wrkrPst" class="textbox" style="width:200px;"></p>
	<p>选择角色：<input id="roleId" name="roleId" class="textbox" style="width:200px;"></p>
	<p>登录密码：<input id="loginPsw" name="loginPsw" class="textbox" style="width:200px;"></p>
</form>
<form id="wrkrInfo_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>员工工号：<input id="wrkrNo" name="wrkrNo" class="textbox" style="width:200px;" disabled="disabled"></p>
	<p>&nbsp&nbsp&nbsp身份证：<input id="wrkrIcd" name="wrkrIcd" class="textbox" style="width:200px;"></p>
	<p>员工姓名：<input id="wrkrNm" name="wrkrNm" class="textbox" style="width:200px;"></p>
	<p>员工电话：<input id="wrkrTell" name="wrkrTell" class="textbox" style="width:200px;"></p>
	<p>员工职位：<input id="wrkrPst" name="wrkrPst" class="textbox" style="width:200px;"></p>
	<p>选择角色：<input id="roleId" name="roleId" class="textbox" style="width:200px;"></p>
	<p>登录密码：<input id="loginPsw" name="loginPsw" class="textbox" style="width:200px;"></p>
</form>

<div id="wrkrInfo_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add-new" plain="true" onclick="wrkrInfo_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="wrkrInfo_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="wrkrInfo_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="wrkrInfo_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="wrkrInfo_tool.redo();">取消选择</a>		
		<a href="#" class="easyui-linkbutton" iconCls="icon-page_excel" plain="true" onclick="wrkrInfo_tool.exportExcel();">导出所有</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
	<form name="searchform" method="post" action="" id ="searchform">
		工号：<input type="text" class="textbox" name="wrkrNo" id="wrkrNo" style="width:110px">
		名字：<input type="text" class="textbox" name="wrkrNm" id="wrkrNm" style="width:110px">
		职位：<input type="text" class="textbox" name="wrkrPst" id="wrkrPst"style="width:110px">
		角色：<input  class="textbox" name="roleId" id="roleId" style="width:110px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="wrkrInfo_tool.search();">查询</a>
	</form>
	</div>
</div>
<script type="text/javascript" src="js/manager_wrkrInfo.js"></script>
  </body>
</html>
