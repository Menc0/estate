$(function () {
	
	$('#housePricing').datagrid({
		url : 'houseMag/queryHouseInfoByBig.do?sell=0',
		cache: false,
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		checkOnSelect:false,
		pageSize : 20,
		pageList : [10, 20, 30, 40, 50],
		pageNumber : 1,
		sortName : 'CREATE_DATE',
		sortOrder : 'desc',
		toolbar : '#housePricing_tool',
		columns : [[
			{
				field : 'hosNo',
				title : '房屋编号',
				width : 100,
				sortable :true,
			},
			{
				field : 'hosArea',
				title : '房屋面积',
				width : 100,
				sortable :true,
			},
			{
				field : 'hosTp',
				title : '户型',
				width : 100,
			},
			{
				field : 'sellPce',
				title : '销售价',
				width : 100,
				sortable :true,
			},
			{
				field : 'dscntRate',
				title : '折扣率',
				width : 100,
				sortable :true,
			},
		]],
	});
	
	
	$('#bigSearch_Pricing #dscntRate').combobox({
		valueField:'value',    
		textField:'text',  
		panelHeight:'auto',
		data:[{
			value:'0',
			text:'无',
		},{
			value:'1',
			text:'有',
		},{
			value:null,
			text:'清空',
		}],
	});
	
	$('#to_housePricing').dialog({
		width : 380,
		title : '房间定价',
		modal : true,
		closed : true,
		iconCls : 'icon-cart_edit',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#to_housePricing').form('validate')) {
					$.ajax({
						url : 'housePricing/saveHousePricing.do',
						type : 'post',
						data : {
							bldNo : $('#to_housePricing #bldNo').val(),
							hosNo : $('#to_housePricing #hosNo').val(),
							sellPce : $('#to_housePricing #sellPce').val(),
							dscntRate : $('#to_housePricing #dscntRate').val(),
							
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在定价中...',
							});
						},
						success : function (data) {
							$.messager.progress('close');
							if (data.success=='true') {
								$.messager.show({
									title : '提示',
									msg : '定价成功',
								});
								$('#to_housePricing').dialog('close').form('reset');
								$('#housePricing').datagrid('reload');
							} else if(data.success=='false'){
								$.messager.alert('操作失败！', '未知错误导致失败，请重试！', 'warning');
							} 
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#to_housePricing').dialog('close').form('reset');
			},
		}],
	});
	
	
	
	housePricing_tool = {
			reload : function () {
				$('#housePricing').datagrid('reload');
			},
			redo : function () {
				$('#housePricing').datagrid('unselectAll');
			},
			pricing : function () {
				var rows = $('#housePricing').datagrid('getSelections');
				if (rows.length > 1) {
					$.messager.alert('警告操作！', '只能选定一条数据！', 'warning');
				} else if (rows.length == 1) {
					$('#to_housePricing').form('load', {
						bldNo : rows[0].bldNo,
						hosNo : rows[0].hosNo,
						sellPce : rows[0].sellPce,
						dscntRate : rows[0].dscntRate,
					}).dialog('open');
				} else if (rows.length == 0) {
					$.messager.alert('警告操作！', '定价前必须选定一条数据！', 'warning');
				}
			},
		search :function(){
			$('#housePricing').datagrid('load', {
				url : 'houseMag/queryHouseInfoByBig.do',
					bldNo : $('#bigSearch_Pricing #bldNo').val(),
					hosNo : $('#bigSearch_Pricing #hosNo').val(),
					hosAreaMin : $('#bigSearch_Pricing #hosAreaMin').val(),
					hosAreaMax : $('#bigSearch_Pricing #hosAreaMax').val(),
					hosTp : $('#bigSearch_Pricing #hosTp').val(),
					dscntRate : $('#bigSearch_Pricing #dscntRate').combobox('getValue'),
					sellPceMin : $('#bigSearch_Pricing #sellPceMin').val(),
					sellPceMax : $('#bigSearch_Pricing #sellPceMax').val(),
					costPceMin : $('#bigSearch_Pricing #costPceMin').val(),
					costPceMax : $('#bigSearch_Pricing #costPceMax').val(),
				
			});
			
		},
	};
	

});