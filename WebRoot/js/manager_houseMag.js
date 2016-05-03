$(function () {
	
	$('#houseMag').datagrid({
		url : 'houseMag/queryAllHouseMag.do',
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
		sortName : 'CREATE_DATE',
		
		sortOrder : 'desc',
		toolbar : '#houseMag_tool',
		columns : [[
			{
				field : 'hosNo1',
				width : 100,
				checkbox : true,
			},
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
		]],
	});
	
	
	
	
	$('#houseMag_add').dialog({
		width : 750,
		title : '新增房屋',
		modal : true,
		closed : true,
		iconCls : 'icon-houseing_add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#houseMag_add').form('validate')) {
					$.ajax({
						url : 'houseMag/saveHouseMag.do',
						type : 'post',
						data : {
							bldNo : $('#houseMag_add #bldNo').combobox('getValue'),
							hosNo : $('#houseMag_add #hosNo').val(),
							hosTp : $('#houseMag_add #hosTp').val(),
							hosArea : $('#houseMag_add #hosArea').val(),
							costPce : $('#houseMag_add #costPce').val(),
							sellPce : $('#houseMag_add #sellPce').val(),
							dscntRate : $('#houseMag_add #dscntRate').val(),
							sellSt : $('#houseMag_add #sellSt').val(),
							hosIntro : $('#hosIntro').val(),
							hosImg : $('#houseMag_add #hosImgName').val(),
							isRecmd : $('#houseMag_add #isRecmd').combobox('getValue'),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function (data) {
							$.messager.progress('close');
							if (data.success=='true') {
								$.messager.show({
									title : '提示',
									msg : '新增成功',
								});
								$('#houseMag_add').dialog('close').form('reset');
								$('#houseMag').datagrid('reload');
							} else if(data.success=='false'){
								$.messager.alert('新增失败！', '未知错误导致失败，请重试！', 'warning');
							} else if(data.success=='hasHosNo'){
								$.messager.alert('新增失败！', '楼房编号已经存在！', 'warning');
							}else if(data.success=='gtHosSum'){
								$.messager.alert('新增失败！', '房间数量超出楼房预设数量！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#houseMag_add').dialog('close').form('reset');
			},
		}],
	});
	$('#houseMag_add #bldNo').combobox({
		url:'houseMag/queryAllBuild.do',    
	    valueField:'bldNo',    
	    textField:'bldNm',
	    required : true,
	});
	
	$.extend($.fn.validatebox.defaults.rules, {  
	    //验证英文开头加数字  
	    EnAndNum: {  
	        validator: function (value) {  
	        	var reg = /^[A-Z][1-9]*$/;  
	            return reg.test(value);  
	        },  
	        message: '英文开头(加数字)'  
	    }, 
	  //验证汉字  
	    CHS: {  
	        validator: function (value) {  
	            return /^[\u0391-\uFFE5]+$/.test(value);  
	        },  
	        message: 'The input Chinese characters only.'  
	    },  
	});
	
	$('#houseMag_add input[name="hosNo"]').validatebox({
		required : true,
		validType : 'length[4,4]',
		missingMessage : '请输入房屋编号',
		invalidMessage : '房屋编号必须为3位',
	});
	
	
	$('#houseMag_edit').dialog({
		width : 750,
		title : '修改房屋',
		modal : true,
		closed : true,
		iconCls : 'icon-houseing_edit',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {
				if ($('#houseMag_edit').form('validate')) {
					$.ajax({
						url : 'houseMag/saveHouseMag.do',
						type : 'post',
						data : {
							method : 'edit',
							bldNo : $('#houseMag_edit #bldNo').val(),
							hosNo : $('#houseMag_edit #hosNo').val(),
							hosTp : $('#houseMag_edit #hosTp').val(),
							hosArea : $('#houseMag_edit #hosArea').val(),
							costPce : $('#houseMag_edit #costPce').val(),
							sellPce : $('#houseMag_edit #sellPce').val(),
							dscntRate : $('#houseMag_edit #dscntRate').val(),
							sellSt : $('#houseMag_edit #sellSt').combobox('getValue'),
							hosIntro : $('#houseMag_edit .wangEditor-textarea').html(),
							hosImg : $('#hosImgName_edit').text(),
							isRecmd : $('#houseMag_edit #isRecmd').combobox('getValue'),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data.success=='true') {
								$.messager.show({
									title : '提示',
									msg : '修改管理成功',
								});
								$('#houseMag_edit').dialog('close').form('reset');
								$('#houseMag').datagrid('reload');
							} else if(data.success=='false'){
								$.messager.alert('修改失败！', '未知错误导致失败，请重试！', 'warning');
							} 
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#houseMag_edit').dialog('close').form('reset');
			},
		}],
	});
	//验证
	$('#houseMag_edit input[name="bldNm"]').validatebox({
		required : true,
		validType : 'CHS',
		missingMessage : '请输入楼名',
		invalidMessage : '楼名必须为汉字',
	});
	
	$('#houseMag_add #isRecmd').combobox({
		valueField:'value',    
		textField:'text',  
		panelHeight:'auto',
		data:[{
			value:'0',
			text:'否',
		},{
			value:'1',
			text:'是',
		}],
	});
	$('#houseMag_add #sellSt').combobox({
		valueField:'value',    
		textField:'text',  
		panelHeight:'auto',
		data:[{
			value:'0',
			text:'未销售',
		},{
			value:'1',
			text:'已销售',
		}],
	});
	
	houseMag_tool = {
		reload : function () {
			$('#houseMag').datagrid('reload');
		},
		redo : function () {
			$('#houseMag').datagrid('unselectAll');
		},
		add : function () {
			//编辑器初始化
			$("#hosIntro").wangEditor();
			$(".wangEditor-container").css("width",560);
			$(".wangEditor-container").css("margin-left",40);
			$('#houseMag_add').dialog('open');
		},
		remove : function () {
			var rows = $('#houseMag').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i ++) {
							ids.push(rows[i].bldNo+";"+rows[i].hosNo1);
						}
						//console.log(ids.join(','));
						$.ajax({
							type : 'POST',
							url : 'houseMag/deleteHouseMag.do',
							data : {
								hosNo : ids.join(','),
							},
							beforeSend : function () {
								$('#houseMag').datagrid('loading');
							},
							success : function (data) {
								if (data.sucess=='true') {
									$('#houseMag').datagrid('loaded');
									$('#houseMag').datagrid('load');
									$('#houseMag').datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : rows.length + '条数据被删除成功！',
									});
									
								}else if(data.sucess=='false'){
									$.messager.alert('未知错误', '删除失败！', 'info');
									$('#houseMag').datagrid('loaded');
									$('#houseMag').datagrid('load');
									$('#houseMag').datagrid('unselectAll');
								}
							},
						});
					}
				});
			} else {
				$.messager.alert('提示', '请选择要删除的记录！', 'info');
			}
		},
		edit : function () {
			var rows = $('#houseMag').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
			} else if (rows.length == 1) {
				$.ajax({
					url : 'houseMag/queryHouseMagByhosNo.do',
					type : 'post',
					cache: false,
					data : {
						hosNo : rows[0].hosNo1,
						bldNo : rows[0].bldNo,
					},
					beforeSend : function () {
						$.messager.progress({
							text : '正在获取中...',
						});
					},
					success : function (data, response, status) {
						$.messager.progress('close');
						
						if (data) {
							
							$('#houseMag_edit').form('load', {
								bldNo : data[0].id.bldNo,
								hosNo : data[0].id.hosNo,
								hosTp : data[0].hosTp,
								hosArea : data[0].hosArea,
								costPce : data[0].costPce,
								sellPce : data[0].sellPce,
								dscntRate : data[0].dscntRate,
							}).dialog('open');
							$('#houseMag_edit #isRecmd').combobox({
								valueField:'value',    
								textField:'text',  
								panelHeight:'auto',
								data:[{
										value:'0',
										text:'否',
									},{
										value:'1',
										text:'是',
									}
								],
								value: data[0].isRecmd,
							});
							$('#houseMag_edit #sellSt').combobox({
								valueField:'value',    
								textField:'text',  
								panelHeight:'auto',
								data:[{
									value:'0',
									text:'未销售',
								},{
									value:'1',
									text:'已销售',
								}
								],
								value: data[0].sellSt,
							});
							
							$("#hosImgName_edit").empty();
							$("#hosImgName_edit").append(data[0].hosImg);
							if($("#hosIntro_edit").wangEditor()!=false){
							$(".wangEditor-container").css("width",560);
							$(".wangEditor-container").css("margin-left",40);
							}else{
							$(".wangEditor-textarea").empty();
							}
							$(".wangEditor-textarea").append(data[0].hosIntro);
							
							
							
						} else {
							$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
						}
					}
				});
			} else if (rows.length == 0) {
				$.messager.alert('警告操作！', '编辑记录必须选定一条数据！', 'warning');
			}
		},
		search :function(){
			$('#houseMag').datagrid('load', {
				url : 'houseMag/queryAllHouseMag.do',
					bldNo : $('#searchform #bldNo').val(),
					hosNo : $('#searchform #hosNo').val(),
					hosAreaMin : $('#searchform #hosAreaMin').val(),
					hosAreaMax : $('#searchform #hosAreaMax').val(),
					hosTp : $('#searchform #hosTp').val(),
					sellPceMin : $('#searchform #sellPceMin').val(),
					sellPceMax : $('#searchform #sellPceMax').val(),
				
			});
			
		},
	};
	

});