
function smsRemindBtn(cusTel,smsContent,payed) {//短信提醒
				if(payed==null){
				$('#payCrcs_sms').form('load', {
						cusTel : cusTel,
						smsContent : smsContent,
					}).dialog('open');
				}else{
					alert("该客户本期已经付款");
				}
			}
function emailBtn(title,content,email,payed) {//邮件提醒
	if(payed==null){
		$('#payCrcs_email').form('load', {
			emailTitle : title,
			emailContent : content,
			emailAddress : email,
		}).dialog('open');
	}else{
		alert("该客户本期已经付款");
	}
}
function payBtn(cusNm,cusIcd,selNo,hosNo,payed,mon){
	if(payed==null){
		$('#payCrcs_paying').form('load', {
			cusNm_paying : cusNm,
			cusIcd_paying : cusIcd,
			selNo_paying : selNo,
			hosNo_paying : hosNo,
			Mon_paying : mon,
		}).dialog('open');
	}else{
		alert("该客户本期已经付款");
	}
	
}

$(function () {
	
	
	$('#payCrcs').datagrid({
		url : 'payCrcs/queryPayCrcs.do',
		cache: false,
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		checkOnSelect:true,
		pageSize : 20,
		pageList : [10, 20, 30, 40, 50],
		pageNumber : 1,
		sortName : 'selNo',
		
		sortOrder : 'desc',
		toolbar : '#payCrcs_tool',
		columns : [[
			{
				field : 'selNo',
				title : '销售ID',
				width : 100,
				checkbox : true,
			},
			{
				field : 'hosNo',
				title : '房号',
				width : 50,
			},
			{
				field : 'cusIcd',
				title : '客户身份证',
				width : 70,
			},
			{
				field : 'cusNm',
				title : '客户名',
				width : 50,
			},
			{
				field : 'cusTel',
				title : '联系电话',
				width : 60,
			},
			{
				field : 'cusEmail',
				title : 'email',
				width : 60,
				hidden:true,
			},
			{
				field : 'allPrdSum',
				title : '总共期数',
				width : 40,
			},
			{
				field : 'payedSum',
				title : '已付期数',
				width : 40,

			},
			{
				field : 'unPaySum',
				title : '未付期数',
				width : 40,
				
			},
			{
				field : 'perPrdMon',
				title : '每期金额',
				width : 80,
				
			},
			{
				field : 'payed',
				title : '本期是否已付',
				width : 50,
				sortable :true,
				formatter: function(value,row,index){
					if (row.payed!=null){
						return '已付款';
					} else {
						return '未付款';
					}
				}
				
			},
			{
				field : 'opt',
				title : '操作',
				width : 80,
				formatter: function(value,row,index){
					 ///var btnSms = '<a class="smscls" onclick="smsRemindBtn(\''+row.cusTel+'\',\'尊敬的'+row.cusNm+'您的'+row.hosNo+'本期房贷请于本月末前交齐　[大地房产]\','+row.payed+')" href="javascript:void(0)" >短信提醒</a>'; 
					 var btnPay = '<a class="paycls" onclick="payBtn(\''+row.cusNm+'\',\''+row.cusIcd+'\',\''+row.selNo+'\',\''+row.hosNo+'\','+row.payed+',\''+row.perPrdMon+'\')" href="javascript:void(0)">付款</a>';
		             var btnEmail = '<a class="emailcls" onclick="emailBtn(\'【大地房产】\',\'尊敬的'+row.cusNm+'您的'+row.hosNo+'本期房贷请于本月末前交齐　[大地房产]\',\''+row.cusEmail+'\','+row.payed+')" href="javascript:void(0)" >email提醒</a>'; 
					 //var btnPayDtl = '<a class="payDtlcls" onclick="payDtlBtn(\''+row.cusNm+'\',\''+row.cusIcd+'\')" href="javascript:void(0)">还款详情</a>';
					 var btn=  btnPay +btnEmail;
					 return btn;  
				}
			},
			
		]],
		 onLoadSuccess:function(data){  
		        $('.smscls').linkbutton({text:'短信提醒',plain:true,iconCls:'icon-email'});  
		        $('.emailcls').linkbutton({text:'email提醒',plain:true,iconCls:'icon-email'});  
		        $('.paycls').linkbutton({text:'付款',plain:true,iconCls:'icon-money'});  
		        $('.payDtlcls').linkbutton({text:'还款详情',plain:true,iconCls:'icon-coins'});  
		    }  
	});
	
	
	 
	
	
	$('#payCrcs_sms').dialog({
		width : 300,
		title : '短信编辑',
		modal : true,
		closed : true,
		iconCls : 'icon-email_edit',
		buttons : [{
			text : '发送',
			iconCls : 'icon-email_go',
			handler : function () {
				if ($('#payCrcs_sms').form('validate')) {
					$.ajax({
						url : 'payCrcs/sms.do',
						type : 'post',
						data : {
							smsContent:$("#smsContent").val(),
							cusTel:$("#cusTel").val(),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在发送中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data.success=='true') {
								$.messager.show({
									title : '提示',
									msg : '发送成功',
								});
								$('#payCrcs_email').dialog('close').form('reset');
								$('#payCrcs').datagrid('reload');
							} else {
								$.messager.alert('发送失败！', '未知错误，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#payCrcs_sms').dialog('close').form('reset');
			},
		}],
	});
	
	$('#payCrcs_email').dialog({
		width : 500,
		title : 'email编辑',
		modal : true,
		closed : true,
		iconCls : 'icon-email_edit',
		buttons : [{
			text : '发送',
			iconCls : 'icon-email_go',
			handler : function () {
				if ($('#payCrcs_email').form('validate')) {
					$.ajax({
						url : 'payCrcs/sendEmail.do',
						type : 'post',
						data : {
							emailTitle:$("#emailTitle").val(),
							emailContent:$("#emailContent").val(),
							toEmailAddress:$("#emailAddress").val(),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在发送中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data.success=='true') {
								$.messager.show({
									title : '提示',
									msg : '发送成功',
								});
								$('#payCrcs_email').dialog('close').form('reset');
								$('#payCrcs').datagrid('reload');
							} else if(data.success!='true'&&data.success!='false'){
								$.messager.alert('发送失败！', data.success, 'warning');
							}else{
								$.messager.alert('发送失败！', '未知错误，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#payCrcs_email').dialog('close').form('reset');
			},
		}],
	});
	
	$('#payCrcs_paying').dialog({
		width : 300,
		title : '本期付款账单',
		modal : true,
		closed : true,
		iconCls : 'icon-vcard',
		buttons : [{
			text : '付款',
			iconCls : 'icon-money',
			handler : function () {
				if ($('#payCrcs_paying').form('validate')) {
					$.ajax({
						url : 'payCrcs/paying.do',
						type : 'post',
						data : {
							selNo:$("#selNo_paying").val(),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在付款中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data.success=='true') {
								$.messager.show({
									title : '提示',
									msg : '付款成功',
								});
								$('#payCrcs_paying').dialog('close').form('reset');
								$('#payCrcs').datagrid('reload');
							} else {
								$.messager.alert('付款失败！', '未知错误，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#payCrcs_paying').dialog('close').form('reset');
			},
		}],
	});
	
	
	
	
	payCrcs_tool = {
		reload : function () {
			$('#payCrcs').datagrid('reload');
		},
		redo : function () {
			$('#payCrcs').datagrid('unselectAll');
		},
		smsRemind : function () {
			var rows = $('#payCrcs').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
			} else if (rows.length == 1) {
				$('#payCrcs_sms').form('load', {
					cusTel : rows[0].cusTel,
				}).dialog('open');
				
			} else if (rows.length == 0) {
				$.messager.alert('警告操作！', '编辑记录至少选定一条数据！', 'warning');
			}
		},
		search :function(){
			$('#payCrcs').datagrid('load', {
				url : 'payCrcs/queryPayCrcs.do',
					cusIcd : $('#cusIcd_search').val(),
					hosNo : $(' #hosNo_search').val(),
				
			});
			
		},
	};
	

});