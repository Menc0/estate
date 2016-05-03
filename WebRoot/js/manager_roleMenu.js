

//显示拥有权限弹出框
function toRoleMenu(value){
	$.ajax({
		url : 'roleMenu/queryRoleMenuByRoleId.do',
		type : 'post',
		data : {
			roleId : value,
		},
		beforeSend : function () {
			$.messager.progress({
				text : '正在获取中...',
			});
		},
		success : function (data, response, status) {
			$.messager.progress('close');
			
			if (data) {
				var menu = data[0].menuCode;
				$('#toRoleMenu').form('load', {
					roleId_to : data[0].roleId,
					roleNm_to : data[0].roleNm,
				}).dialog('open');
				
				//拥有权限
				$('#auth_to').combotree({
					url : '/estate/roleMenu/queryAllMenu.do',
					required : true,
					lines : true,
					multiple : true,
					checkbox : true,
					onlyLeafCheck : false,
					cascadeCheck : false,
					onLoadSuccess : function (node, data) {
						var _this = this;
						if (data) {
							$(data).each(function (index, value) {
								if ($.inArray(value.id, menu) != -1) {
									var node = $(_this).tree('find',value.id);
									$(_this).tree('check', node.target);
								}
								if (this.state == 'closed') {
									$(_this).tree('expandAll');
								}
							});
							//设置选项不可点击
							$(this).find('span.tree-checkbox').unbind().click(function(){
								return false;
								});
							$(this).find('span.tree-title').unbind().click(function(){
								return false;
							});
						}
					},
				});
				
			} else {
				$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
			}
		}
	});
	}
$(function () {
	
	
	$('#roleMenu').datagrid({
		url : 'roleMenu/queryRoleMenu.do',
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
		sortName : 'date',
		
		sortOrder : 'desc',
		toolbar : '#roleMenu_tool',
		columns : [[
			{
				field : 'roleId',
				title : '角色ID',
				width : 100,
				checkbox : true,
			},
			{
				field : 'roleNm',
				title : '角色',
				width : 100,
			},
			{
				field : 'menuUrl',
				title : '拥有权限',
				width : 200,
				formatter: function(value,row,index){
				return '<a href="javascript:void(0);" style="color:red" onclick="toRoleMenu(\''+row.roleId+'\')"> 点击查看 </a>';
				

				}

			},
			
		]],
	});
	
	
	
	
	$('#roleMenu_add').dialog({
		width : 350,
		title : '新增权限',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#roleMenu_add').form('validate')) {
					var menuCode = new Array();
					menuCode=$('#auth').combotree('getValues');
					//获取被选择的父节点
					var parentnode = $('#auth').combotree('tree').tree('getChecked','indeterminate');
					var menu = "";
					for(var i=0;i<menuCode.length;i++){
						menu =menuCode[i]+","+menu;
					}
					for(var i=0;i<parentnode.length;i++){
						menu = parentnode[i].id+","+menu;
					}
					$.ajax({
						url : 'roleMenu/saveRoleMenu.do',
						type : 'post',
						data : {
							roleId : $('#roleNm').combobox('getValue'),
							menuCode : menu,
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function (data) {
							$.messager.progress('close');
							if (data[0].success=='true') {
								$.messager.show({
									title : '提示',
									msg : '新增成功',
								});
								$('#roleMenu_add').dialog('close').form('reset');
								$('#roleMenu').datagrid('reload');
								updateTab();
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
				$('#roleMenu_add').dialog('close').form('reset');
			},
		}],
	});
	
	$('#roleMenu_edit').dialog({
		width : 350,
		title : '修改管理',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {
				//if ($('#roleMenu_add').form('validate')) {
					var menuCode = new Array();
					menuCode=$('#auth_edit').combotree('getValues');
					var menu = "";
					var parentnode = "";
					var parentmenu = "";
					for(var i=0;i<menuCode.length;i++){
						var node = $('#auth_edit').combotree('tree').tree('find',menuCode[i]);
						//获取选中的父ID
						parentnode = $('#auth_edit').combotree('tree').tree('getParent',node.target);
						parentmenu = parentnode.id+","+parentmenu;
						menu =menuCode[i]+","+menu;
					}
					menu = menu+parentmenu;
					$.ajax({
						url : 'roleMenu/updateRoleMenu.do',
						type : 'post',
						data : {
							roleId : $('input[name="roleId_edit"]').val(),
							menuCode : menu,
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data[0].success=='true') {
								$.messager.show({
									title : '提示',
									msg : '修改管理成功',
								});
								$('#roleMenu_edit').dialog('close').form('reset');
								$('#roleMenu').datagrid('reload');
							} else {
								$.messager.alert('修改失败！', '未知错误或没有任何修改，请重试！', 'warning');
							}
						}
					});
				//}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#roleMenu_edit').dialog('close').form('reset');
			},
		}],
	});
	//分配权限
	$('#roleMenu_add #auth').combotree({
		method:"GET",
		url : 'roleMenu/queryAllMenu.do',
		required : true,
		lines : true,
		multiple : true,
		checkbox : true,
		onlyLeafCheck : false,
		onLoadSuccess : function (node, data) {
			//var _this = this;
			if (data) {
				$(data).each(function (index, value) {
					if (this.state == 'closed') {
						$('#auth').tree('expandAll');
					}
				});
			}
		},
	});
	
	//选择角色
	$('#roleMenu_add #roleNm').combobox({
		url:'roleMenu/queryRole.do',    
	    valueField:'id',    
	    textField:'text',
	    required : true,
	});
	
	//查看拥有权限弹出框
	$('#toRoleMenu').dialog({
		width : 350,
		title : '拥有权限',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		
	});
	
	
	
	
	//刷新tab
	function updateTab(){
        var currTab = self.parent.$('#tabs').tabs('getSelected');    //获取选中的标签项
        var url = $(currTab.panel('options').content).attr('src');    //获取该选项卡中内容标签（iframe）的 src 属性
        /* 重新设置该标签 */
        self.parent.$('#tabs').tabs('update',{
            tab:currTab,
            options:{
                //content: createTabContent(url)
            	href:url
            }
        });
	}
	
	roleMenu_tool = {
		reload : function () {
			$('#roleMenu').datagrid('reload');
		},
		redo : function () {
			$('#roleMenu').datagrid('unselectAll');
		},
		add : function () {
			$('#roleMenu_add').dialog('open');
			//$('input[name="roleMenu"]').focus();
		},
		remove : function () {
			var rows = $('#roleMenu').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i ++) {
							ids.push(rows[i].roleId);
						}
						//console.log(ids.join(','));
						$.ajax({
							type : 'POST',
							url : 'roleMenu/deleteRoleMenu.do',
							data : {
								roleId : ids.join(','),
							},
							beforeSend : function () {
								$('#roleMenu').datagrid('loading');
							},
							success : function (data) {
								if (data) {
									$('#roleMenu').datagrid('loaded');
									$('#roleMenu').datagrid('load');
									$('#roleMenu').datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : rows.length + '个管理被删除成功！',
									});
									updateTab();
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
			var rows = $('#roleMenu').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
			} else if (rows.length == 1) {
				$.ajax({
					url : 'roleMenu/queryRoleMenuByRoleId.do',
					type : 'post',
					data : {
						roleId : rows[0].roleId,
					},
					beforeSend : function () {
						$.messager.progress({
							text : '正在获取中...',
						});
					},
					success : function (data, response, status) {
						$.messager.progress('close');
						
						if (data) {
							
							//var obj = $.parseJSON(data);
							var menu = data[0].menuCode;
							$('#roleMenu_edit').form('load', {
								roleId_edit : data[0].roleId,
								roleNm_edit : data[0].roleNm,
								//auth_edit : obj[0].auth,
							}).dialog('open');
							
							//分配权限
							$('#auth_edit').combotree({
								url : 'roleMenu/queryAllMenu.do',
								required : true,
								lines : true,
								multiple : true,
								checkbox : true,
								onlyLeafCheck : true,
								cascadeCheck:false,
								onLoadSuccess : function (node, data) {
									var _this = this;
									if (data) {
										$(data).each(function (index, value) {
											if ($.inArray(value.id, menu) != -1) {
												var node = $(_this).tree('find',value.id);
												$(_this).tree('check', node.target);
											}
											if (this.state == 'closed') {
												$(_this).tree('expandAll');
											}
										});
									}
								},
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
	};
	

});