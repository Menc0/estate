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
    
    <title>My JSP 'cusInfo.jsp' starting page</title>
    
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
<table id="cusInfo"></table>

<form id="cusInfo_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>身份证：<input id="cusIcd" name="cusIcd" class="textbox" style="width:200px;"></p>
	<p>姓　名：<input id="cusNm" name="cusNm" class="textbox" style="width:200px;"></p>
	<p>电　话：<input id="cusTel" name="cusTel" class="textbox" style="width:200px;"></p>
	<p>Email：<input id="cusIncm" name="cusIncm" class="textbox" style="width:200px;"></p>
</form>
<form id="cusInfo_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<input type="hidden" id="cusNo" name="cusNo">
	<p>身份证：<input id="cusIcd" name="cusIcd" class="textbox" style="width:200px;"></p>
	<p>姓　名：<input id="cusNm" name="cusNm" class="textbox" style="width:200px;"></p>
	<p>电　话：<input id="cusTel" name="cusTel" class="textbox" style="width:200px;"></p>
	<p>Email：<input id="cusIncm" name="cusIncm" class="textbox" style="width:200px;"></p>
</form>

<div id="cusInfo_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add-new" plain="true" onclick="cusInfo_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="cusInfo_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="cusInfo_tool.remove();" id="role_remove">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="cusInfo_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="cusInfo_tool.redo();">取消选择</a>		
		<a href="#" class="easyui-linkbutton" iconCls="icon-page_excel" plain="true" onclick="cusInfo_tool.exportExcel();">导出所有</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
	<form name="cusInfo_searchform" method="post" action="" id ="cusInfo_searchform">
		身份证：<input type="text" class="textbox" name="cusIcd" id="cusIcd" style="width:150px">
		名字：<input type="text" class="textbox" name="cusNm" id="cusNm" style="width:110px">
		电话：<input type="text" class="textbox" name="cusTel" id="cusTel"style="width:110px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="cusInfo_tool.search();">查询</a>
	</form>
	</div>
</div>
<script type="text/javascript" src="js/manager_cusInfo.js"></script>
<script type="text/javascript">
$(function(){
	if($("#roleId").val()=='2001'){//销售员不予显示删除
		$("#role_remove").remove();
	}
})
</script>
  </body>
</html>
