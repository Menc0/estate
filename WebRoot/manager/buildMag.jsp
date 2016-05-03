<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'buildMag.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body >
    <table id="buildMag"></table>

<form id="buildMag_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<table style="width:710px;text-align:center">
		<tr>
			<td>楼房编号</td>
			<td><input id="bldNo" name="bldNo" class="textbox" style="width:200px;"></td>
			<td>楼房名字</td>
			<td><input id="bldNm" name="bldNm" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>楼房位置</td>
			<td><input id="bldSite" name="bldSite" class="textbox" style="width:200px;"></td>
			<td>占地面积</td>
			<td><input id="bldArea" name="bldArea" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>楼层总数</td>
			<td><input id="floSum" name="floSum" class="textbox" style="width:200px;"></td>
			<td>房间总数</td>
			<td><input id="houseSum" name="houseSum" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>投入金额</td>
			<td><input id="bldInvestMon" name="bldInvestMon" class="textbox" style="width:200px;"></td>
			<td>是否推荐</td>
			<td><input id="isRecmd" name="isRecmd" class="textbox" style="width:200px;">
			<!-- <select id="isRecmd" name="isRecmd" class="easyui-combobox" style="width:200px;">
					<option value="1">是</option>   
		    		<option value="0">否</option>
		    		</select> -->
			</td>
		</tr>
		<tr>
			<td>楼房简介</td>
			<td colspan="3"><textarea id='bldIntro' style='border:1px solid #cccccc;'></textarea>
			</td>
		</tr>
		<tr>
			<td>楼房图片</td>
			<td>
				<input id="bldImg" name="file" style="width:200px;" type="file"><div id="bldImgName" name="bldImgName"></div>
			</td>
			<td>
			<input id="upload" type="button" value="上传" onclick="BldImgUpload()">
			</td>
		</tr>
		<tr>
			
		</tr>
	</table>
</form>
<form id="buildMag_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<table style="width:710px;text-align:center">
		<tr>
			<td>楼房编号</td>
			<td><input id="bldNo" name="bldNo" class="textbox" style="width:200px;" disabled="disabled"></td>
			<td>楼房名字</td>
			<td><input id="bldNm" name="bldNm" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>楼房位置</td>
			<td><input id="bldSite" name="bldSite" class="textbox" style="width:200px;"></td>
			<td>占地面积</td>
			<td><input id="bldArea" name="bldArea" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>楼层总数</td>
			<td><input id="floSum" name="floSum" class="textbox" style="width:200px;"></td>
			<td>房间总数</td>
			<td><input id="houseSum" name="houseSum" class="textbox" style="width:200px;"></td>
		</tr>
		<tr>
			<td>投入金额</td>
			<td><input id="bldInvestMon" name="bldInvestMon" class="textbox" style="width:200px;"></td>
			<td>是否推荐</td>
			<td><input id="isRecmd" name="isRecmd" class="textbox" style="width:200px;">
			<!-- <select id="isRecmd" name="isRecmd" class="easyui-combobox" style="width:200px;">
					<option value="1">是</option>   
		    		<option value="0">否</option>
		    		</select> -->
			</td>
		</tr>
		<tr>
			<td>楼房简介</td>
			<td colspan="3"><textarea id='bldIntro_edit' style='border:1px solid #cccccc;'></textarea>
			</td>
		</tr>
		<tr>
			<td>楼房图片</td>
			<td>
				<input id="bldImg_edit" name="file" style="width:200px;" type="file"><div id="bldImgName_edit" name="bldImgName_edit"></div>
			</td>
			<td>
			<input type="button" value="上传" onclick="BldImgUploadEdit()">
			</td>
		</tr>
		<tr>
			
		</tr>
	</table>
</form>
<div id="buildMag_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add-new" plain="true" onclick="buildMag_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="buildMag_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="buildMag_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="buildMag_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="buildMag_tool.redo();">取消选择</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
	<form name="searchform" method="post" action="" id ="searchform">
		楼房编号：<input id="bldNo" name="bldNo" class="textbox" style="width:120px;">
		楼房名字：<input id="bldNm" name="bldNm" class="textbox" style="width:120px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="buildMag_tool.search();">查询</a>
	</form>
	</div>
</div>
<script type="text/javascript" src="js/manager_buildMag.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src='wangEditor/js/wangEditor-1.3.12.min.js'></script>
<script>
function BldImgUpload(){
$.ajaxFileUpload({
                url:'/estate/upload/ImagesUpload.do?random='+Math.random(),//用于文件上传的服务器端请求地址
                secureuri:true,//是否启用安全提交，一般设置为false
                fileElementId:'bldImg',//文件上传控件的id
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
                        $("#bldImgName").empty();
                        $("#bldImgName").append(dataset.fileName);
                        
                    }else {
                    	if(dataset.fileName=="false"){
                    		alert("图片类型必须为png,jpg,gif");
                    	}
                        alert("上传失败!");
                    }
                },
            });
           
        
}
function BldImgUploadEdit(){
$.ajaxFileUpload({
                url:'/estate/upload/ImagesUpload.do?random='+Math.random(),//用于文件上传的服务器端请求地址
                secureuri:true,//是否启用安全提交，一般设置为false
                fileElementId:'bldImg_edit',//文件上传控件的id
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
                        $("#buildMag_edit #bldImgName_edit").empty();
                        $("#buildMag_edit #bldImgName_edit").append(dataset.fileName);
                        
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
