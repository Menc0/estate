<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'houseMag.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  <body >
 <table id="housePricing"></table>
 
 <form id="to_housePricing" style="margin:0;padding:5px 0 0 25px;color:#333;">
 	<input type="hidden" id="bldNo" name="bldNo">
	<p>房&nbsp&nbsp&nbsp号：<input id="hosNo" name="hosNo" class="textbox" style="width:200px;" disabled="disabled"></p>
	<p>销售价：<input id="sellPce" name="sellPce" class="textbox" style="width:200px;"></p>
	<p>折扣率：<input id="dscntRate" name="dscntRate" class="textbox" style="width:200px;"></p>
 </form>
 
<div id="housePricing_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-cart_edit" plain="true" onclick="housePricing_tool.pricing();">定价</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="housePricing_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="housePricing_tool.redo();">取消选择</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
	<fieldset>
	<legend>高级查询</legend>
	
	<form name="bigSearch_Pricing" method="post" action="" id ="bigSearch_Pricing">
		<table>
			<tr>
				<td>房屋编号：</td>
				<td style="width:200px"><input id="hosNo" name="hosNo" class="textbox" style="width:120px;"></td>
				<td style="text-align:right">房屋价格：</td>
				<td colspan="2">
					<input id="sellPceMin" name="sellPceMin" class="textbox" style="width:87px;">
					- <input id="sellPceMax" name="sellPceMax" class="textbox" style="width:87px;">
				</td>
				<td></td>
			</tr>
			<tr>
				<td>房屋户型：</td>
				<td><input id="hosTp" name="hosTp" class="textbox" style="width:120px;"></td>
				<td>房屋面积：</td>
				<td colspan="2">
					<input id="hosAreaMin" name="hosAreaMin" class="textbox" style="width:87px;">
					- <input id="hosAreaMax" name="hosAreaMax" class="textbox" style="width:87px;">
				</td>
				
				
			</tr>
			<tr>
				<td>是否折扣：</td>
				<td><input id="dscntRate" name="dscntRate" class="textbox" style="width:120px;"></td>
				<td style="text-align:right">房屋成本：</td>
				<td colspan="2">
					<input id="costPceMin" name="costPceMin" class="textbox" style="width:87px;">
					- <input id="costPceMax" name="costPceMax" class="textbox" style="width:87px;">
				</td>
			</tr>
			<tr>
				
				<td>楼房编号：</td>
				<td><input id="bldNo" name="bldNo" class="textbox" style="width:120px;"></td>
				<td colspan="3" style="text-align:right"><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="housePricing_tool.search();">查询</a></td>
			</tr>
		
		</table>
	</form>
	</fieldset>
	</div>
</div>
<script type="text/javascript" src="js/manager_housePricing.js"></script>
  </body>
</html>
