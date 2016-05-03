$(function () {
	
	
	$('#wrkrInfo').datagrid({
		url : 'wrkrInfo/queryAllWrkrInfo.do',
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
		sortName : 'wrkrNo',
		
		sortOrder : 'asc',
		toolbar : '#wrkrInfo_tool',
		columns : [[
			{
				field : 'wrkrNo1',
				//title : '员工号',
				width : 100,
				checkbox : true,
			},
			{
				field : 'wrkrNo',
				title : '员工号',
				width : 100,
				sortable :true,
			},
			{
				field : 'wrkrIcd',
				title : '身份证',
				width : 100,
			},
			{
				field : 'wrkrNm',
				title : '名字',
				width : 100,
			},
			{
				field : 'wrkrTell',
				title : '电话',
				width : 100,
			},
			{
				field : 'wrkrPst',
				title : '职位',
				width : 100,
			},
			{
				field : 'roleNm',
				title : '拥有角色',
				width : 100,
			},
			{
				field : 'loginPsw',
				title : '登录密码',
				width : 100,
			},
			{
				field : 'sellScr',
				title : '销售成绩',
				width : 100,
				sortable :true,
			},
		]],
	});
	
	
	
	
	$('#wrkrInfo_add').dialog({
		width : 350,
		title : '新增员工',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#wrkrInfo_add').form('validate')) {
					$.ajax({
						url : 'wrkrInfo/saveWrkrInfo.do',
						type : 'post',
						data : {
							wrkrNo : $('#wrkrInfo_add #wrkrNo').val(),
							wrkrNm : $('#wrkrInfo_add #wrkrNm').val(),
							wrkrIcd : $('#wrkrInfo_add #wrkrIcd').val(),
							wrkrTell : $('#wrkrInfo_add #wrkrTell').val(),
							wrkrPst : $('#wrkrInfo_add #wrkrPst').val(),
							loginPsw : $('#wrkrInfo_add #loginPsw').val(),
							roleId : $('#wrkrInfo_add #roleId').combobox('getValue'),
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
								$('#wrkrInfo_add').dialog('close').form('reset');
								$('#wrkrInfo').datagrid('reload');
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
				$('#wrkrInfo_add').dialog('close').form('reset');
			},
		}],
	});
	
	//验证
	$('#wrkrInfo_add input[name="wrkrIcd"]').validatebox({
		required : true,
		validType : 'length[18,18]',
		missingMessage : '请输入身份证',
		invalidMessage : '请输入合法的身份证',
	});
	
	$('#wrkrInfo_add input[name="loginPsw"]').validatebox({
		required : true,
		validType : 'length[3,18]',
		missingMessage : '请输入密码',
		invalidMessage : '密码3-18位',
	});
	
	$('#wrkrInfo_edit').dialog({
		width : 350,
		title : '修改管理',
		modal : true,
		closed : true,
		iconCls : 'icon-user',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {
				if ($('#wrkrInfo_edit').form('validate')) {
					$.ajax({
						url : 'wrkrInfo/saveWrkrInfo.do',
						type : 'post',
						data : {
							wrkrNo : $('#wrkrInfo_edit #wrkrNo').val(),
							wrkrNm : $('#wrkrInfo_edit #wrkrNm').val(),
							wrkrIcd : $('#wrkrInfo_edit #wrkrIcd').val(),
							wrkrTell : $('#wrkrInfo_edit #wrkrTell').val(),
							wrkrPst : $('#wrkrInfo_edit #wrkrPst').val(),
							loginPsw : $('#wrkrInfo_edit #loginPsw').val(),
							roleId : $('#wrkrInfo_edit #roleId').combobox('getValue'),
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
								$('#wrkrInfo_edit').dialog('close').form('reset');
								$('#wrkrInfo').datagrid('reload');
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
				$('#wrkrInfo_edit').dialog('close').form('reset');
			},
		}],
	});
	
	//验证
	$('#wrkrInfo_edit input[name="wrkrIcd"]').validatebox({
		required : true,
		validType : 'length[18,18]',
		missingMessage : '请输入身份证',
		invalidMessage : '请输入合法的身份证',
	});
	
	$('#wrkrInfo_edit input[name="loginPsw"]').validatebox({
		required : true,
		validType : 'length[3,18]',
		missingMessage : '请输入密码',
		invalidMessage : '密码3-18位',
	});
	
	//选择角色
	$('#wrkrInfo_add #roleId').combobox({
		url:'wrkrInfo/queryAllRole.do',    
	    valueField:'roleId',    
	    textField:'roleNm',
	    required : true,
	});
	$('#wrkrInfo_tool #roleId').combobox({
		url:'wrkrInfo/queryAllRole.do',    
		valueField:'roleId',    
		textField:'roleNm',
	});
	
	//查看拥有权限弹出框
	$('#towrkrInfo').dialog({
		width : 350,
		title : '拥有权限',
		modal : true,
		closed : true,
		iconCls : 'icon-zoom',
		
	});
	
	
	
	wrkrInfo_tool = {
		reload : function () {
			$('#wrkrInfo').datagrid('reload');
		},
		redo : function () {
			$('#wrkrInfo').datagrid('unselectAll');
		},
		exportExcel : function () {
			$.ajax({
				url : 'wrkrInfo/exportExcel.do',
				type : 'post',
				beforeSend : function () {
					$.messager.progress({
						text : '正在获取中...',
					});
				},
				success : function (data, response, status) {
					$.messager.progress('close');
					
					if (data.success=='true') {
						$.messager.alert('导出成功！','导出成功！');
					} else {
						$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
					}
				}
			});
		},
		add : function () {
			//$('#wrkrInfo_add').dialog('open');
			$.ajax({
				url : 'wrkrInfo/getWrkrNo.do',
				type : 'post',
				beforeSend : function () {
					$.messager.progress({
						text : '正在获取中...',
					});
				},
				success : function (data, response, status) {
					$.messager.progress('close');
					
					if (data) {
						
						$('#wrkrInfo_add').form('load', {
							wrkrNo : data.wrkrNo,
						}).dialog('open');
						
						
						
					} else {
						$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
					}
				}
			});
		},
		remove : function () {
			var rows = $('#wrkrInfo').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i ++) {
							ids.push(rows[i].wrkrNo1);
						}
						//console.log(ids.join(','));
						$.ajax({
							type : 'POST',
							url : 'wrkrInfo/deleteWrkrInfo.do',
							data : {
								wrkrNo : ids.join(','),
							},
							beforeSend : function () {
								$('#wrkrInfo').datagrid('loading');
							},
							success : function (data) {
								if (data) {
									$('#wrkrInfo').datagrid('loaded');
									$('#wrkrInfo').datagrid('load');
									$('#wrkrInfo').datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : rows.length + '条数据被删除成功！',
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
			var rows = $('#wrkrInfo').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
			} else if (rows.length == 1) {
				$.ajax({
					url : 'wrkrInfo/queryWrkrInfoByWrkrNo.do',
					type : 'post',
					data : {
						wrkrNo : rows[0].wrkrNo1,
					},
					beforeSend : function () {
						$.messager.progress({
							text : '正在获取中...',
						});
					},
					success : function (data, response, status) {
						$.messager.progress('close');
						
						if (data) {
							
							$('#wrkrInfo_edit').form('load', {
								wrkrNo :data[0].wrkrNo,
								wrkrNm : data[0].wrkrNm,
								wrkrIcd :data[0].wrkrIcd,
								wrkrTell : data[0].wrkrTell,
								wrkrPst : data[0].wrkrPst,
								loginPsw : data[0].loginPsw,
								//roleId :  data[0].roleId,
							}).dialog('open');
							
							$('#wrkrInfo_edit #roleId').combobox({
								url:'wrkrInfo/queryAllRole.do',    
							    valueField:'roleId',    
							    textField:'roleNm',
							    required : true,
							    value: data[0].roleId,
							    /*onLoadSuccess : function (node, data) {
							    	data.
							    }*/
							});
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
			$('#wrkrInfo').datagrid('load', {
				url : 'wrkrInfo/queryAllWrkrInfo.do',
					wrkrNo : $('#searchform #wrkrNo').val(),
					wrkrNm : $('#searchform #wrkrNm').val(),
					wrkrPst : $('#searchform #wrkrPst').val(),
					roleId : $('#searchform #roleId').combobox('getValue'),
				
			});
			
		},
	};
	

});