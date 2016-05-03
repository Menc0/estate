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
 <table id="houseSell"></table>
 
 <form id="to_houseSell" style="margin:0;padding:5px 0 0 25px;color:#333;">
 		<input type="hidden" id="cusNo" name="cusNo"/>
 			
 			<div style="margin-bottom:10px">
	 			楼　号：<input id="bldNo" name="bldNo" class="textbox"  disabled="disabled">
	 			　　　房　号：<input id="hosNo" name="hosNo" class="textbox"  disabled="disabled">
	 			
 			</div>
 			<fieldset style="width:400px;margin-bottom:10px">
			<legend>客户信息</legend>
 			<table style="margin-left:50px">
 				<tr>
		 			<td style="text-align:right">身份证</td>
		 			<td><input id="cusIcd" name="cusIcd" class="textbox" style="width:200px;">
		 			</td>
 				</tr>
 				<tr>
 					<td colspan="2">
 					<input type="button" id="getCusInfo" value="获取客户" style="float:right">
 					</td>
 				</tr>
		 		<tr>
		 			<td style="text-align:right">名　字</td>
		 			<td><input id="cusNm" name="cusNm" class="textbox" style="width:200px;"></td>
		 		</tr>
		 		<tr>
		 			<td style="text-align:right">电　话</td>
		 			<td><input id="cusTel" name="cusTel" class="textbox" style="width:200px;"></td>
		 		</tr>
		 		<tr>
		 			<td style="text-align:right">email</td>
		 			<td><input id="cusEmail" name="cusEmail" class="textbox" style="width:200px;"></td>
		 		</tr>
 			
 			</table>
 			</fieldset>
 			<div>
 				<p>总　价：<input id="amount" name="amount" class="textbox"  disabled="disabled">
 				　　　期　数：<input id="bystgSum" name="bystgSum" class="textbox"  ></p>
 				<p>首　付：<input id="fsdpdPay" name="fsdpdPay" class="textbox"  >
 				　　　需　付：<input id="fsdpdPayMon" name="fsdpdPayMon" class="textbox"  ></p>
 			</div>
	 		<div>
	 			备　注：<textarea id="comment" name="comment" class="textbox"  style="width:375px;height:100px"/>
	 		</div>
 		
 			<input type="hidden" id="sell_wrkrNo">
 	
 </form>
 
<div id="houseSell_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-cart_add" plain="true" onclick="houseSell_tool.buy();">购买</a>
<!-- 		<a href="#" class="easyui-linkbutton" iconCls="icon-cart_edit" plain="true" onclick="houseSell_tool.book();">预订</a> -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="houseSell_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="houseSell_tool.redo();">取消选择</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
	<fieldset>
	<legend>高级查询</legend>
	
	<form name="bigSearch_Sell" method="post" action="" id ="bigSearch_Sell">
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
				<td colspan="3" style="text-align:right"><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="houseSell_tool.search();">查询</a></td>
			</tr>
		
		</table> 
	</form>
	</fieldset>
	</div>
</div>
<script type="text/javascript" src="js/manager_houseSell.js"></script>
<script type="text/javascript">
	$("#getCusInfo").click(function(){
		var cusIcd = $("#to_houseSell #cusIcd").val();
		$.ajax({
			url : 'cusInfo/getCusinfoByCusIcd.do',
			type : 'post',
			cache: false,
			data : {
					cusIcd : cusIcd,
					},
			success:function(data){
				if(data==''){
					alert("没有该客户，请在下面填写信息！");
					$("#to_houseSell #cusNm").focus();
				}
				$("#to_houseSell #cusNo").val(data.cusNo);
				$("#to_houseSell #cusNm").val(data.cusNm);
				$("#to_houseSell #cusTel").val(data.cusTel);
				$("#to_houseSell #cusEmail").val(data.cusEmail);
			},
		});
	});

	$("#sell_wrkrNo").val($("#wrkrNo").val());
	
</script>
  </body>
</html>
