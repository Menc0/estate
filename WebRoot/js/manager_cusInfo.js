$(function () {
	
	
	$('#cusInfo').datagrid({
		url : 'cusInfo/queryAllCusInfo.do',
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
		total : 30,
		sortName : 'cusNo',
		
		sortOrder : 'desc',
		toolbar : '#cusInfo_tool',
		columns : [[
			{
				field : 'cusNo',
				//title : '员工号',
				width : 100,
				checkbox : true,
			},
			{
				field : 'cusNm',
				title : '名字',
				width : 100,
			},
			{
				field : 'cusIcd',
				title : '身份证',
				width : 100,
			},
			{
				field : 'cusTel',
				title : '电话',
				width : 100,
			},
			{
				field : 'cusEmail',
				title : 'Email',
				width : 100,
			},
		]],
	});
	
	
	
	
	$('#cusInfo_add').dialog({
		width : 350,
		title : '新增客户',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#cusInfo_add').form('validate')) {
					$.ajax({
						url : 'cusInfo/saveCusInfo.do',
						type : 'post',
						data : {
							cusNm : $('#cusInfo_add #cusNm').val(),
							cusIcd : $('#cusInfo_add #cusIcd').val(),
							cusTel : $('#cusInfo_add #cusTel').val(),
							cusEmail : $('#cusInfo_add #cusIncm').val(),
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
								$('#cusInfo_add').dialog('close').form('reset');
								$('#cusInfo').datagrid('reload');
							} else {
								$.messager.alert('新增失败！', '未知错误导致失败，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#cusInfo_add').dialog('close').form('reset');
			},
		}],
	});
	
	//验证
	$('#cusInfo_add input[name="cusIcd"]').validatebox({
		required : true,
		validType : 'length[18,18]',
		missingMessage : '请输入身份证',
		invalidMessage : '请输入合法的身份证',
	});
	
	$('#cusInfo_edit').dialog({
		width : 350,
		title : '修改客户',
		modal : true,
		closed : true,
		iconCls : 'icon-user',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {
				if ($('#cusInfo_edit').form('validate')) {
					$.ajax({
						url : 'cusInfo/saveCusInfo.do',
						type : 'post',
						data : {
							cusNo : $('#cusInfo_edit #cusNo').val(),
							cusNm : $('#cusInfo_edit #cusNm').val(),
							cusIcd : $('#cusInfo_edit #cusIcd').val(),
							cusTel : $('#cusInfo_edit #cusTel').val(),
							cusEmail : $('#cusInfo_edit #cusIncm').val(),
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
								$('#cusInfo_edit').dialog('close').form('reset');
								$('#cusInfo').datagrid('reload');
							} else {
								$.messager.alert('修改失败！', '未知错误或没有任何修改，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#cusInfo_edit').dialog('close').form('reset');
			},
		}],
	});
	
	//验证
	$('#cusInfo_edit input[name="cusIcd"]').validatebox({
		required : true,
		validType : 'length[18,18]',
		missingMessage : '请输入身份证',
		invalidMessage : '请输入合法的身份证',
	});
	
	
	
	
	
	cusInfo_tool = {
		reload : function () {
			$('#cusInfo').datagrid('reload');
		},
		redo : function () {
			$('#cusInfo').datagrid('unselectAll');
		},
		exportExcel : function () {
			$.ajax({
				url : 'cusInfo/exportExcel.do',
				type : 'post',
				data : {
					cusIcd : $('#cusInfo_searchform #cusIcd').val(),
					cusNm : $('#cusInfo_searchform #cusNm').val(),
					cusTel : $('#cusInfo_searchform #cusTel').val(),
				},
				beforeSend : function () {
					$.messager.progress({
						text : '正在获取中...',
					});
				},
				success : function (data, response, status) {
					$.messager.progress('close');
					
					if (data.success=='true') {
						$.messager.alert('导出成功！','导出成功！存在E：盘根目录');
					} else {
						$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
					}
				}
			});
		},
		add : function () {
			$('#cusInfo_add').dialog('open');
		},
		remove : function () {
			var rows = $('#cusInfo').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i ++) {
							ids.push(rows[i].cusNo);
						}
						//console.log(ids.join(','));
						$.ajax({
							type : 'POST',
							url : 'cusInfo/deleteCusInfo.do',
							data : {
								cusNo : ids.join(','),
							},
							beforeSend : function () {
								$('#cusInfo').datagrid('loading');
							},
							success : function (data) {
								if (data) {
									$('#cusInfo').datagrid('loaded');
									$('#cusInfo').datagrid('load');
									$('#cusInfo').datagrid('unselectAll');
									if(data.success=="true"){
									$.messager.show({
										title : '提示',
										msg : rows.length + '条数据被删除成功！',
									});
								}else if(data.success=="false"){
									$.messager.alert('提示', '删除失败！', 'info');
								}
									
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
			var rows = $('#cusInfo').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
			} else if (rows.length == 1) {
				$.ajax({
					url : 'cusInfo/queryCusInfoByCusNo.do',
					type : 'post',
					data : {
						cusNo : rows[0].cusNo,
					},
					beforeSend : function () {
						$.messager.progress({
							text : '正在获取中...',
						});
					},
					success : function (data, response, status) {
						$.messager.progress('close');
						
						if (data) {
							
							$('#cusInfo_edit').form('load', {
								cusNo :data[0].cusNo,
								cusNm : data[0].cusNm,
								cusIcd :data[0].cusIcd,
								cusTel : data[0].cusTel,
								cusIncm : data[0].cusEmail,
							}).dialog('open');
							
						} else {
							$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
						}
					}
				});
			} else if (rows.length == 0) {
				$.messager.alert('警告操作！', '编辑记录至少选定一条数据！', 'warning');
			}
		},
		search :function(){
			$('#cusInfo').datagrid('load', {
				url : 'cusInfo/queryAllCusInfo.do',
					cusIcd : $('#cusInfo_searchform #cusIcd').val(),
					cusNm : $('#cusInfo_searchform #cusNm').val(),
					cusTel : $('#cusInfo_searchform #cusTel').val(),
				
			});
			
		},
	};
	

});