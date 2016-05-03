function toViewHosSellCrcs(value){
	$.ajax({
		url : 'houseMag/toViewHosSellCrcs.do',
		type : 'post',
		data : {
			hosNo : value,
		},
		beforeSend : function () {
			$.messager.progress({
				text : '正在获取中...',
			});
		},
		success : function (data, response, status) {
			$.messager.progress('close');
			
			if (data) {
				$('#toViewHosSellCrcs').form('load', {
					hosNo : data.HOS_NO,
					txnDt : data.TXN_DT,
					hosPft : data.HOS_PFT,
					wrkrNo : data.WRKR_NO,
					wrkrNm : data.WRKR_NM,
					cusIcd : data.CUS_ICD,
					cusNm : data.CUS_NM
				}).dialog('open');
				
			} else {
				$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
			}
		}
	});
	
}
$(function () {
	
	$('#houseInfo').datagrid({
		url : 'houseMag/queryHouseInfoByBig.do',
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
		toolbar : '#houseInfo_tool',
		columns : [[
			{
				field : 'hosNo',
				title : '房屋编号',
				width : 100,
				sortable :true,
			},
			{
				field : 'bldNo',
				title : '楼房编号',
				width : 100,
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
				field : 'costPce',
				title : '成本价',
				width : 100,
				sortable :true,
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
			{
				field : 'sellSt',
				title : '销售状态',
				width : 100,
				sortable :true,
				formatter: function(value,row,index){
					if (row.sellSt==0){
						return '未销售';
					} else {
						return '<a href="javascript:void(0);" title="点击可查看交易详情" class="easyui-tooltip" style="color:red" onclick="toViewHosSellCrcs(\''+row.hosNo+'\')"> 已销售 </a>';
					}
				}
			},
			{
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

			},
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
	$('#bigSearch #dscntRate').combobox({
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
	
	//查看销售详情
	$('#toViewHosSellCrcs').dialog({
		width : 350,
		title : '交易详情',
		modal : true,
		closed : true,
		iconCls : 'icon-vcard',
		
	});
	
	
	houseInfo_tool = {
		search :function(){
			$('#houseInfo').datagrid('load', {
				url : 'houseMag/queryHouseInfoByBig.do',
					bldNo : $('#bigSearch #bldNo').val(),
					hosNo : $('#bigSearch #hosNo').val(),
					hosAreaMin : $('#bigSearch #hosAreaMin').val(),
					hosAreaMax : $('#bigSearch #hosAreaMax').val(),
					hosTp : $('#bigSearch #hosTp').val(),
					dscntRate : $('#bigSearch #dscntRate').combobox('getValue'),
					sellPceMin : $('#bigSearch #sellPceMin').val(),
					sellPceMax : $('#bigSearch #sellPceMax').val(),
					costPceMin : $('#bigSearch #costPceMin').val(),
					costPceMax : $('#bigSearch #costPceMax').val(),
				
			});
			
		},
	};
	

});