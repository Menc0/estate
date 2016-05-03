


$(function () {
	
	
	$('#intrateMag').datagrid({
		url : 'intrateMag/queryAllIntrateMag.do',
		cache: false,
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		//pagination : true,
		checkOnSelect:true,
		//pageSize : 20,
		//pageList : [10, 20, 30, 40, 50],
		//pageNumber : 1,
		sortName : 'intrateId',
		
		sortOrder : 'desc',
		toolbar : '#intrateMag_tool',
		columns : [[
			{
				field : 'intrateId',
				title : '利率ID',
				width : 100,
				checkbox : true,
			},
			{
				field : 'bystgSum',
				title : '期数',
				width : 100,
			},
			{
				field : 'intrate',
				title : '利率',
				width : 200,

			},
			
		]],
	});
	
	
	
	
	$('#intrateMag_add').dialog({
		width : 350,
		title : '新增期数利率',
		modal : true,
		closed : true,
		iconCls : 'icon-add-new',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#intrateMag_add').form('validate')) {
					$.ajax({
						url : 'intrateMag/saveIntrateMag.do',
						type : 'post',
						data : {
							bystgSum : $('#bystgSum').val(),
							intrate : $('#intrate').val(),
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
								$('#intrateMag_add').dialog('close').form('reset');
								$('#intrateMag').datagrid('reload');
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
				$('#intrateMag_add').dialog('close').form('reset');
			},
		}],
	});
	
	$('#intrateMag_edit').dialog({
		width : 350,
		title : '修改管理',
		modal : true,
		closed : true,
		iconCls : 'icon-edit-new',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {
				if ($('#intrateMag_add').form('validate')) {
					$.ajax({
						url : 'intrateMag/saveIntrateMag.do',
						type : 'post',
						data : {
							intrateId : $('#intrateId_edit').val(),
							bystgSum : $('#bystgSum_edit').val(),
							intrate : $('#intrate_edit').val(),
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
									msg : '修改成功',
								});
								$('#intrateMag_edit').dialog('close').form('reset');
								$('#intrateMag').datagrid('reload');
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
				$('#intrateMag_edit').dialog('close').form('reset');
			},
		}],
	});
	
	
	
	
	intrateMag_tool = {
		reload : function () {
			$('#intrateMag').datagrid('reload');
		},
		redo : function () {
			$('#intrateMag').datagrid('unselectAll');
		},
		add : function () {
			$('#intrateMag_add').dialog('open');
		},
		remove : function () {
			var rows = $('#intrateMag').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i ++) {
							ids.push(rows[i].intrateId);
						}
						$.ajax({
							type : 'POST',
							url : 'intrateMag/deleteIntrateMag.do',
							data : {
								intrateId : ids.join(','),
							},
							beforeSend : function () {
								$('#intrateMag').datagrid('loading');
							},
							success : function (data) {
								if (data) {
									$('#intrateMag').datagrid('loaded');
									$('#intrateMag').datagrid('load');
									$('#intrateMag').datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : rows.length + '个被删除成功！',
									});
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
			var rows = $('#intrateMag').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
			} else if (rows.length == 1) {
				$('#intrateMag_edit').form('load', {
					intrateId_edit : rows[0].intrateId,
					bystgSum_edit : rows[0].bystgSum,
					intrate_edit : rows[0].intrate,
				}).dialog('open');
				
			} else if (rows.length == 0) {
				$.messager.alert('警告操作！', '编辑记录至少选定一条数据！', 'warning');
			}
		},
	};
	

});