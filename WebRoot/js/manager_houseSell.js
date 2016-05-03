
$(function () {
	
	$('#houseSell').datagrid({
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
		toolbar : '#houseSell_tool',
		columns : [[
			{
				field : 'hosNo',
				title : '房屋编号',
				width : 100,
				sortable :true,
			},
			/*{
				field : 'bldNo',
				title : '楼房编号',
				width : 100,
			},*/
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
			/*{
				field : 'costPce',
				title : '成本价',
				width : 100,
				sortable :true,
			},*/
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
			/*{
				field : 'sellSt',
				title : '销售状态',
				width : 100,
				sortable :true,
				formatter: function(value,row,index){
					if (row.sellSt==0){
						return '未销售';
					} else {
						return '已销售';
					}
				}
			},*/
			/*{
				field : 'isRecmd',
				title : '是否推荐',
				width : 100,
				sortable :true,
				formatter: function(value,row,index){
					if (row.isRecmd==0){
						return '否';
					} else {
						return '是';
					}
				}

			},*/
			{
				field : 'intro',
				title : '简介',
				width : 100,
				sortable :true,
				formatter: function(value,row,index){
					return "<a href='houseMag/toHosIntro.do?hosNo="+row.hosNo+"' style='color:red' target='_Blank'> 点击查看 </a>";

				}
			
			},
		]],
	});
	
	
	$('#bigSearch_Sell #dscntRate').combobox({
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
	//首付下拉框
	$('#to_houseSell #fsdpdPay').combobox({
		valueField:'value',    
		textField:'text',  
		panelHeight:'auto',
		value:'100',
		data:[{
			value:'30',
			text:'30%',
		},{
			value:'20',
			text:'20%',
		},{
			value:'100',
			text:'全付',
		}],
		onChange:function(){
			$("#fsdpdPayMon").val($("#fsdpdPay").combobox('getValue')/100*$("#amount").val()); 
			},
	});
	
	$('#to_houseSell #bystgSum').combobox({
		url:'intrateMag/queryAllIntrateMag.do',    
	    valueField:'intrateId',    
	    textField:'bystgSum',
	    value:'1',
	    required : true,
	});
	
	
	$('#to_houseSell').dialog({
		width : 500,
		title : '购买房屋',
		modal : true,
		closed : true,
		iconCls : 'icon-cart_add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#to_houseSell').form('validate')) {
					$.ajax({
						url : 'houseSell/saveHouseSell.do',
						type : 'post',
						data : {
							bldNo : $('#to_houseSell #bldNo').val(),
							hosNo : $('#to_houseSell #hosNo').val(),
							cusNo : $('#to_houseSell #cusNo').val(),
							cusIcd : $('#to_houseSell #cusIcd').val(),
							cusNm : $('#to_houseSell #cusNm').val(),
							cusTel : $('#to_houseSell #cusTel').val(),
							cusEmail : $('#to_houseSell #cusEmail').val(),
							comment : $('#to_houseSell #comment').val(),
							wrkrNo : $('#to_houseSell #sell_wrkrNo').val(),
							prdSumId : $('#to_houseSell #bystgSum').combobox('getValue'),
							fsdpdPayMon : $('#to_houseSell #fsdpdPayMon').val(),
							
							
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在购买中...',
							});
						},
						success : function (data) {
							$.messager.progress('close');
							if (data.success=='true') {
								$.messager.show({
									title : '提示',
									msg : '购买成功',
								});
								$('#to_houseSell').dialog('close').form('reset');
								$('#houseSell').datagrid('reload');
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
				$('#to_houseSell').dialog('close').form('reset');
			},
		}],
	});
	
	
	//验证
	$('#to_houseSell input[name="cusIcd"]').validatebox({
		required : true,
		validType : 'length[18,18]',
		missingMessage : '请输入身份证',
		invalidMessage : '请输入合法的身份证',
	});
	
	houseSell_tool = {
			reload : function () {
				$('#houseSell').datagrid('reload');
			},
			redo : function () {
				$('#houseSell').datagrid('unselectAll');
			},
			buy : function () {
				var rows = $('#houseSell').datagrid('getSelections');
				if (rows.length > 1) {
					$.messager.alert('警告操作！', '只能选定一条数据！', 'warning');
				} else if (rows.length == 1) {
					var amount=rows[0].sellPce-(rows[0].sellPce*rows[0].dscntRate/100);
					$('#to_houseSell').form('load', {
						bldNo : rows[0].bldNo,
						hosNo : rows[0].hosNo,
						sellPce:rows[0].sellPce,
						dscntRate:rows[0].dscntRate,
						amount:amount,
						fsdpdPayMon : $('#to_houseSell #fsdpdPay').combobox('getValue')/100*amount,
					}).dialog('open');
				} else if (rows.length == 0) {
					$.messager.alert('警告操作！', '您还未选择房间！请选择一条后购买', 'warning');
				}
			},
		search :function(){
			$('#houseSell').datagrid('load', {
				url : 'houseMag/queryHouseInfoByBig.do',
					bldNo : $('#bigSearch_Sell #bldNo').val(),
					hosNo : $('#bigSearch_Sell #hosNo').val(),
					hosAreaMin : $('#bigSearch_Sell #hosAreaMin').val(),
					hosAreaMax : $('#bigSearch_Sell #hosAreaMax').val(),
					hosTp : $('#bigSearch_Sell #hosTp').val(),
					dscntRate : $('#bigSearch_Sell #dscntRate').combobox('getValue'),
					sellPceMin : $('#bigSearch_Sell #sellPceMin').val(),
					sellPceMax : $('#bigSearch_Sell #sellPceMax').val(),
					costPceMin : $('#bigSearch_Sell #costPceMin').val(),
					costPceMax : $('#bigSearch_Sell #costPceMax').val(),
				
			});
			
		},
	};
	

});