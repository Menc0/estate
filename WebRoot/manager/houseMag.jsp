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
    <table id="houseMag"></table>

 <form id="houseMag_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<table style="width:710px;text-align:center">
		<tr>
			<td>所属楼房</td>
			<td><input id="bldNo" name="bldNo" class="textbox" style="width:200px;"></td>
			<td>房屋编号</td>
			<td><input id="hosNo" name="hosNo" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>房屋户型</td>
			<td><input id="hosTp" name="hosTp" class="textbox" style="width:200px;"></td>
			<td>占地面积</td>
			<td><input id="hosArea" name="hosArea" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>房屋成本</td>
			<td><input id="costPce" name="costPce" class="textbox" style="width:200px;"></td>
			<td>销售价格</td>
			<td><input id="sellPce" name="sellPce" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>折扣率</td>
			<td><input id="dscntRate" name="dscntRate" class="textbox" style="width:200px;"></td>
			<td>是否推荐</td>
			<td><input id="isRecmd" name="isRecmd" class="textbox" style="width:200px;">
			</td>
		</tr>
		<tr>
			<td>销售状态</td>
			<td><input id="sellSt" name="sellSt" class="textbox" style="width:200px;">
			</td>
		</tr>
		<tr>
			<td>房屋简介</td>
			<td colspan="3"><textarea id='hosIntro' style='border:1px solid #cccccc;'></textarea>
			</td>
		</tr>
		<tr>
			<td>房屋图片</td>
			<td>
				<input id="hosImg" name="file" style="width:200px;" type="file"><div id="hosImgName" name="hosImgName"></div>
			</td>
			<td>
			<input id="upload" type="button" value="上传" onclick="HosImgUpload()">
			</td>
		</tr>
		<tr>
			
		</tr>
	</table>
</form> 
<form id="houseMag_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<table style="width:710px;text-align:center">
		<tr>
			<td>楼房编号</td>
			<td><input id="bldNo" name="bldNo" class="textbox" style="width:200px;" disabled="disabled"></td>
			<td>房屋编号</td>
			<td><input id="hosNo" name="hosNo" class="textbox" style="width:200px;" disabled="disabled"></td>
		</tr>
		<tr>
			<td>房屋户型</td>
			<td><input id="hosTp" name="hosTp" class="textbox" style="width:200px;"></td>
			<td>占地面积</td>
			<td><input id="hosArea" name="hosArea" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>房屋成本</td>
			<td><input id="costPce" name="costPce" class="textbox" style="width:200px;"></td>
			<td>销售价格</td>
			<td><input id="sellPce" name="sellPce" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>折扣率</td>
			<td><input id="dscntRate" name="dscntRate" class="textbox" style="width:200px;"></td>
			<td>是否推荐</td>
			<td><input id="isRecmd" name="isRecmd" class="textbox" style="width:200px;">
			</td>
		</tr>
		<tr>
			<td>销售状态</td>
			<td><input id="sellSt" name="sellSt" class="textbox" style="width:200px;">
			</td>
		</tr>
		<tr>
			<td>房屋简介</td>
			<td colspan="3"><textarea id='hosIntro_edit' style='border:1px solid #cccccc;'></textarea>
			</td>
		</tr>
		<tr>
			<td>房屋图片</td>
			<td>
				<input id="hosImg_edit" name="file" style="width:200px;" type="file"><div id="hosImgName_edit" name="hosImgName_edit"></div>
			</td>
			<td>
			<input id="upload" type="button" value="上传" onclick="HosImgUploadEdit()">
			</td>
		</tr>
		<tr>
			
		</tr>
	</table>
</form> 
<div id="houseMag_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add-new" plain="true" onclick="houseMag_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="houseMag_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="houseMag_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="houseMag_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="houseMag_tool.redo();">取消选择</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
	<form name="searchform" method="post" action="" id ="searchform">
		<table>
			<tr>
				<td>房屋编号：</td>
				<td><input id="hosNo" name="hosNo" class="textbox" style="width:120px;"></td>
				<td>楼房编号：</td>
				<td><input id="bldNo" name="bldNo" class="textbox" style="width:120px;"></td>
				<td>房屋户型：</td>
				<td><input id="hosTp" name="hosTp" class="textbox" style="width:120px;"></td>
				<td rowspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="houseMag_tool.search();">查询</a></td>
			</tr>
			<tr>
				<td>房屋面积：</td>
				<td colspan="2">
					<input id="hosAreaMin" name="hosAreaMin" class="textbox" style="width:87px;">
					- <input id="hosAreaMax" name="hosAreaMax" class="textbox" style="width:87px;">
				</td>
				
				<td style="text-align:right">房屋价格：</td>
				<td colspan="2">
					<input id="sellPceMin" name="sellPceMin" class="textbox" style="width:87px;">
					- <input id="sellPceMax" name="sellPceMax" class="textbox" style="width:87px;">
				</td>
				
			</tr>
		
		</table>
	</form>
	</div>
</div>
<script type="text/javascript" src="js/manager_houseMag.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src='wangEditor/js/wangEditor-1.3.12.min.js'></script>
<script>
function HosImgUpload(){
$.ajaxFileUpload({
                url:'/estate/upload/ImagesUpload.do?random='+Math.random(),//用于文件上传的服务器端请求地址
                secureuri:true,//是否启用安全提交，一般设置为false
                fileElementId:'hosImg',//文件上传控件的id
                dataType:'text',//服务器返回的数据类型
                success: function (data,status){
                data = data.replace("<PRE>", '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
	            data = data.replace("</PRE>", '');
	            data = data.replace("<pre>", '');
	            data = data.replace("</pre>", ''); //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
	            //将String字符串转换成json
	            var dataset = $.parseJSON(data);
                    if(dataset.success == "true"){
                        alert("上传成功!");
                        $("#hosImgName").empty();
                        $("#hosImgName").append(dataset.fileName);
                        
                    }else {
                    	if(dataset.fileName=="false"){
                    		alert("图片类型必须为png,jpg,gif");
                    	}
                        alert("上传失败!");
                    }
                },
            });
           
        
}
function HosImgUploadEdit(){
$.ajaxFileUpload({
                url:'/estate/upload/ImagesUpload.do?random='+Math.random(),//用于文件上传的服务器端请求地址
                secureuri:true,//是否启用安全提交，一般设置为false
                fileElementId:'hosImg_edit',//文件上传控件的id
                dataType:'text',//服务器返回的数据类型
                success: function (data,status){
                data = data.replace("<PRE>", '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
	            data = data.replace("</PRE>", '');
	            data = data.replace("<pre>", '');
	            data = data.replace("</pre>", ''); //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
	            //将String字符串转换成json
	            var dataset = $.parseJSON(data);
                    if(dataset.success == "true"){
                        alert("上传成功!");
                        $("#houseMag_edit #hosImgName_edit").empty();
                        $("#houseMag_edit #hosImgName_edit").append(dataset.fileName);
                        
                    }else {
                    	if(dataset.fileName=="false"){
                    		alert("图片类型必须为png,jpg,gif");
                    	}
                        alert("上传失败!");
                    }
                },
            });
           
        
}
</script> 
  </body>
</html>
