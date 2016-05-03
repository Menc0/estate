$(function () {
	
	$('#nav').tree({
		method:"GET",
		url : 'menu/queryMenuAjax.do?roleId='+$('#roleId').val(),
		//data:{roleId: $('#roleId').val(),},
		cache: false,
		lines : true,
		onLoadSuccess : function (node, data) {
			if (data) {
				$(data).each(function (index, value) {
					if (this.state == 'closed') {
						$('#nav').tree('expandAll');
					}
				});
			}
		},
		onClick : function (node) {
			if (node.url) {
				if ($('#tabs').tabs('exists', node.text)) {
					$('#tabs').tabs('select', node.text);
				} else {
					$('#tabs').tabs('add', {
						title : node.text,
						iconCls : node.iconCls,
						closable : true,
						href : node.url ,
					});
				}
			}
		}
	});
	$('#myInfo_edit').dialog({
		width : 350,
		title : '个人信息',
		modal : true,
		closed : true,
		iconCls : 'icon-user',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {
				if ($('#myInfo_edit').form('validate')) {
					$.ajax({
						url : 'wrkrInfo/saveWrkrInfo.do',
						type : 'post',
						data : {
							wrkrNo : $('#myInfo_edit #wrkrNo1').val(),
							wrkrNm : $('#myInfo_edit #wrkrNm').val(),
							wrkrIcd : $('#myInfo_edit #wrkrIcd').val(),
							wrkrTell : $('#myInfo_edit #wrkrTell').val(),
							wrkrPst : $('#myInfo_edit #wrkrPst').val(),
							loginPsw : $('#myInfo_edit #loginPsw').val(),
							roleId : $('#myInfo_edit #roleId1').combobox('getValue'),
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
								$('#myInfo_edit').dialog('close').form('reset');
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
				$('#myInfo_edit').dialog('close').form('reset');
			},
		}],
	});
	
	//验证
	$('#myInfo_edit input[name="wrkrIcd"]').validatebox({
		required : true,
		validType : 'length[18,18]',
		missingMessage : '请输入身份证',
		invalidMessage : '请输入合法的身份证',
	});
	
	$('#myInfo_edit input[name="loginPsw"]').validatebox({
		required : true,
		validType : 'length[3,18]',
		missingMessage : '请输入密码',
		invalidMessage : '密码3-18位',
	});
	
	 
	$('#tabs').tabs({
		fit : true,
		border : false,
	});

	$('#info').menubutton({    
	    menu: '#infoMenu'   
	});  



});
function edit() {
	{
		$.ajax({
			url : 'wrkrInfo/queryWrkrInfoByWrkrNo.do',
			type : 'post',
			data : {
				wrkrNo : $('#wrkrNo').val(),
			},
			beforeSend : function () {
				$.messager.progress({
					text : '正在获取中...',
				});
			},
			success : function (data, response, status) {
				$.messager.progress('close');
				
				if (data) {
					
					$('#myInfo_edit').form('load', {
						wrkrNo1 :data[0].wrkrNo,
						wrkrNm : data[0].wrkrNm,
						wrkrIcd :data[0].wrkrIcd,
						wrkrTell : data[0].wrkrTell,
						wrkrPst : data[0].wrkrPst,
						loginPsw : data[0].loginPsw,
						//roleId :  data[0].roleId,
					}).dialog('open');
					
					$('#myInfo_edit #roleId1').combobox({
						url:'wrkrInfo/queryAllRole.do',    
					    valueField:'roleId',    
					    textField:'roleNm',
					    required : true,
					    value: data[0].roleId,
					    
					});
				} else {
					$.messager.alert('获取失败！', '未知错误导致失败，请重试！', 'warning');
				}
			}
		});
	} 
}

	 