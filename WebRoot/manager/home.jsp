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
    
    <title>房地产销售管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="css/admin.css" />
	<link rel="stylesheet" type="text/css" href="wangEditor/css/wangEditor-1.3.12.css">
</head>
<body class="easyui-layout">
		<input type="hidden" value="${wrkrInfo.roleId}" id="roleId"/>
		<input type="hidden" value="${wrkrInfo.wrkrNo}" id="wrkrNo"/>
		<div data-options="region:'north',title:'header',split:false,noheader:true" style="height:60px;background:#666;">
			<div class="logo">房地产销售管理系统</div>
			<div class="logout">
				${wrkrInfo.roleNm}，
				<a href="javascript:void(0);" id="info" style="color:white">${wrkrNm}</a>　
			</div>
			<div id="infoMenu" style="width:150px;">   
			    <div data-options="iconCls:'icon-edit-new'" onClick="javascript:edit();">
						个人信息　
				</div>
				<div  onClick="location.href='<%=path%>/login/toLogout.do'">
					退出
				</div>   
			</div>  

		</div>   
		<div data-options="region:'south',title:'footer',split:false,noheader:true" style="height:35px;line-height:30px;text-align:center;background:#666;">
			&copy;2015-2016 menco-cwh. 
		</div>    
		<div data-options="region:'west',title:'导航',split:true,iconCls:'icon-world'" style="width:180px;padding:10px;">
			<ul id="nav"></ul>
		</div>   
		<div data-options="region:'center'" style="overflow:hidden;">
			<div id="tabs">
				<div title="起始页" iconCls="icon-house" style="padding:0 10px;display:block;">
					欢迎来到后台管理系统！
					<form id="myInfo_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
						<p>工　号：<input id="wrkrNo1" name="wrkrNo1" class="textbox" style="width:200px;" disabled="disabled"></p>
						<p>身份证：<input id="wrkrIcd" name="wrkrIcd" class="textbox" style="width:200px;"></p>
						<p>姓　名：<input id="wrkrNm" name="wrkrNm" class="textbox" style="width:200px;"></p>
						<p>电　话：<input id="wrkrTell" name="wrkrTell" class="textbox" style="width:200px;"></p>
						<p>职　位：<input id="wrkrPst" name="wrkrPst" class="textbox" style="width:200px;" disabled="disabled"></p>
						<p>角　色：<input id="roleId1" name="roleId1" class="textbox" style="width:200px;" disabled="disabled"></p>
						<p>密　码：<input id="loginPsw" name="loginPsw" class="textbox" style="width:200px;"></p>
					</form>
					
					
				</div>
			</div>
		</div> 

			<div id="rcmenu" class="easyui-menu" style="">
			    <div data-options="iconCls:'icon-cancel'" id="closecur">
			        关闭
			    </div>
			    <div id="closeall">
			        关闭全部
			    </div>
			    <div id="closeother">
			        关闭其他
			    </div>
			    <div class="menu-sep"></div>
			    <div id="closeright">
			        关闭右侧标签页
			    </div>
			    <div id="closeleft">
			        关闭左侧标签页
			    </div>
			</div>
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script src="./js/highcharts.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js" ></script>
<script type="text/javascript" src="./js/manager_home.js"></script>
</body>
</html>
