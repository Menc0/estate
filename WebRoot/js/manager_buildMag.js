$(function () {
	
	$('#buildMag').datagrid({
		url : 'buildMag/queryAllBuildMag.do',
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
		sortName : 'CREATE_DATE',
		
		sortOrder : 'desc',
		toolbar : '#buildMag_tool',
		columns : [[
			{
				field : 'bldNo1',
				width : 100,
				checkbox : true,
			},
			{
				field : 'bldNo',
				title : '楼房编号',
				width : 100,
				sortable :true,
			},
			{
				field : 'bldNm',
				title : '楼房名字',
				width : 100,
			},
			{
				field : 'bldSite',
				title : '楼房位置',
				width : 100,
			},
			{
				field : 'bldArea',
				title : '占地面积',
				width : 100,
				sortable :true,
			},
			{
				field : 'floSum',
				title : '楼层总数',
				width : 100,
				sortable :true,
			},
			{
				field : 'houseSum',
				title : '房间总数',
				width : 100,
				sortable :true,
			},
			{
				field : 'bldInvestMon',
				title : '投入金额',
				width : 100,
				sortable :true,
			},
			/*{
				field : 'bldIntro',
				title : '楼房简介',
				width : 100,
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
	
	
	
	
	$('#buildMag_add').dialog({
		width : 750,
		title : '新增楼房',
		modal : true,
		closed : true,
		iconCls : 'icon-building_add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#buildMag_add').form('validate')) {
					$.ajax({
						url : 'buildMag/saveBuildMag.do',
						type : 'post',
						data : {
							bldNo : $('#buildMag_add #bldNo').val(),
							bldNm : $('#buildMag_add #bldNm').val(),
							bldSite : $('#buildMag_add #bldSite').val(),
							bldArea : $('#buildMag_add #bldArea').val(),
							floSum : $('#buildMag_add #floSum').val(),
							houseSum : $('#buildMag_add #houseSum').val(),
							bldInvestMon : $('#buildMag_add #bldInvestMon').val(),
							bldIntro : $('#bldIntro').val(),
							bldImg : $('#buildMag_add #bldImgName').val(),
							isRecmd : $('#buildMag_add #isRecmd').combobox('getValue'),
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
								$('#buildMag_add').dialog('close').form('reset');
								$('#buildMag').datagrid('reload');
							} else if(data.success=='false'){
								$.messager.alert('新增失败！', '未知错误导致失败，请重试！', 'warning');
							} else if(data.success=='hasBldNo'){
								$.messager.alert('新增失败！', '楼房编号已经存在！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#buildMag_add').dialog('close').form('reset');
			},
		}],
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
	//验证
	$('#buildMag_add input[name="bldNo"]').validatebox({
		required : true,
		validType : 'EnAndNum',
		missingMessage : '请输入编号,楼房英文名首字母加数字',
		invalidMessage : '请输入合法的编号,楼房英文名首字母加数字',
	});
	
	$('#buildMag_add input[name="bldNm"]').validatebox({
		required : true,
		validType : 'CHS',
		missingMessage : '请输入楼名',
		invalidMessage : '楼名必须为汉字',
	});
	
	
	$('#buildMag_edit').dialog({
		width : 750,
		title : '修改楼房',
		modal : true,
		closed : true,
		iconCls : 'icon-building_edit',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {
				if ($('#buildMag_edit').form('validate')) {
					$.ajax({
						url : 'buildMag/saveBuildMag.do',
						type : 'post',
						data : {
							method : 'edit',
							bldNo : $('#buildMag_edit #bldNo').val(),
							bldNm : $('#buildMag_edit #bldNm').val(),
							bldSite : $('#buildMag_edit #bldSite').val(),
							bldArea : $('#buildMag_edit #bldArea').val(),
							floSum : $('#buildMag_edit #floSum').val(),
							houseSum : $('#buildMag_edit #houseSum').val(),
							bldInvestMon : $('#buildMag_edit #bldInvestMon').val(),
							bldIntro : $('#buildMag_edit .wangEditor-textarea').html(),
							bldImg : $('#bldImgName_edit').text(),
							isRecmd : $('#buildMag_edit #isRecmd').combobox('getValue'),
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
								$('#buildMag_edit').dialog('close').form('reset');
								$('#buildMag').datagrid('reload');
							} else if(data.success=='false'){
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
				$('#buildMag_edit').dialog('close').form('reset');
			},
		}],
	});
	//验证
	$('#buildMag_edit input[name="bldNm"]').validatebox({
		required : true,
		validType : 'CHS',
		missingMessage : '请输入楼名',
		invalidMessage : '楼名必须为汉字',
	});
	
	$('#buildMag_add #isRecmd').combobox({
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
	
	buildMag_tool = {
		reload : function () {
			$('#buildMag').datagrid('reload');
		},
		redo : function () {
			$('#buildMag').datagrid('unselectAll');
		},
		add : function () {
			//编辑器初始化
			$("#bldIntro").wangEditor();
			$(".wangEditor-container").css("width",560);
			$(".wangEditor-container").css("margin-left",40);
			$('#buildMag_add').dialog('open');
		},
		remove : function () {
			var rows = $('#buildMag').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i ++) {
							ids.push(rows[i].bldNo1);
						}
						//console.log(ids.join(','));
						$.ajax({
							type : 'POST',
							url : 'buildMag/deleteBuildMag.do',
							data : {
								bldNo : ids.join(','),
							},
							beforeSend : function () {
								$('#buildMag').datagrid('loading');
							},
							success : function (data) {
								if (data.sucess=='true') {
									$('#buildMag').datagrid('loaded');
									$('#buildMag').datagrid('load');
									$('#buildMag').datagrid('unselectAll');
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
			var rows = $('#buildMag').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
			} else if (rows.length == 1) {
				$.ajax({
					url : 'buildMag/queryBuildMagByBldNo.do',
					type : 'post',
					cache: false,
					data : {
						bldNo : rows[0].bldNo1,
					},
					beforeSend : function () {
						$.messager.progress({
							text : '正在获取中...',
						});
					},
					success : function (data, response, status) {
						$.messager.progress('close');
						
						if (data) {
							
							$('#buildMag_edit').form('load', {
								bldNo :data[0].bldNo,
								bldNm :data[0].bldNm,
								bldSite :data[0].bldSite ,
								bldArea :data[0].bldArea ,
								floSum : data[0].floSum,
								houseSum :data[0].houseSum ,
								bldInvestMon :data[0].bldInvestMon ,
								//bldIntro_edit :data[0].bldIntro,
								//isRecmd : $('#buildMag_add #isRecmd').combobox('getValue'),
								//roleId :  data[0].roleId,
							}).dialog('open');
							$('#buildMag_edit #isRecmd').combobox({
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
							
							$("#bldImgName_edit").empty();
							$("#bldImgName_edit").append(data[0].bldImg);
							if($("#bldIntro_edit").wangEditor()!=false){
							$(".wangEditor-container").css("width",560);
							$(".wangEditor-container").css("margin-left",40);
							}else{
							$(".wangEditor-textarea").empty();
							}
							//var $bldIntro = ("<p>"+data[0].bldIntro+"</p>");
							//bldIntro_edit.append($bldIntro);
							$(".wangEditor-textarea").append(data[0].bldIntro);
							
							
							
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
			$('#buildMag').datagrid('load', {
				url : 'buildMag/queryAllBuildMag.do',
					bldNo : $('#searchform #bldNo').val(),
					bldNm : $('#searchform #bldNm').val(),
				
			});
			
		},
	};
	

});