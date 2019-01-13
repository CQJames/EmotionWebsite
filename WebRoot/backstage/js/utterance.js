$(function() {
	$('#dg').datagrid({
		url: 'UtteranceBackstageAction_findAllExcludeComment',
		columns: [[  
			  {field: 'publisher',title: '发布者',width: '10%',align:'center'},
			  {field: 'visitors',title: '浏览人数',width: '5%',align:'center'},
			  {field: 'isSelected',title: '是否精选',width: '10%',align:'center',formatter:formatSfxs},
			  {field: 'isDisplay',title: '是否显示',width: '10%',align:'center',formatter:formatSfxs},
			  {field: 'message',title: '问题内容',width: '30%',align:'center'},
			  {field: 'saveTime',title: '保存时间',width: '10%',align:'center', formatter:formatDate},
		      {field: 'changeTime',title: '更改时间',width: '10%',align:'center', formatter:formatDate},
			  {field: 'xxx',title: '操作',width: '20%',align:'center',formatter: function(value, row, index) {
					return "<a href='javascript:void(0)' class='loan_update_button' onclick=\"edit('" + row.utteranceID + "')\">" + "修改</a>" +
							"| <a href='javascript:void(0)' class='loan_delete_button' onclick=\"del('" + row.utteranceID + "')\">" + "删除</a>"+
							"| <a href='javascript:void(0)' class='loan_redo_button' onclick=\"viewComment('" + row.utteranceID + "')\">" + "查看回复</a>";
					}
				}
			]],
		onLoadSuccess: function(data) {
			$(".loan_update_button").linkbutton({
				text: '修改',
				plain: true,
				iconCls: 'icon-edit'
			});
			$(".loan_delete_button").linkbutton({
				text: '删除',
				plain: true,
				iconCls: 'icon-remove'
			});
			$(".loan_redo_button").linkbutton({
				text: '查看回复',
				plain: true,
				iconCls: 'icon-redo'
			});
		},
		rownumbers: true,  
	    border: 0,
	    fit: true,
		animate: true,
		singleSelect: true,
		scrollbarSize: 0,
		loadMsg: '正在刷新，请稍候...',
		//斑马线
		striped: true,
		// 显示分页工具条
		pagination: true,
		// 分页条位置
		pagePosition: "bottom",
		// 初始化页数
		pageNumber: 1,
		// 每页显示多少条记录
		pageSize: 3,
		pageList: [3, 5, 10],
		toolbar: '#utterance-toolbar',
	});
	
	$('#dgComment').datagrid({
		url: 'ReplyBackstageAction_findComment',
		columns: [[  
		      {field: 'message',title: '内容',width: '30%',align:'center'},
		      {field: 'utterance',title: '评论的问题',width: '10%',align:'center', formatter:function(val){if(val){return  val.message}},},
		      {field: 'user',title: '回复的用户',width: '10%',align:'center', formatter:function(val){if(val){return  val.username}},},
			  {field: 'saveTime',title: '保存时间',width: '10%',align:'center', formatter:formatDate},
		      {field: 'changeTime',title: '更改时间',width: '10%',align:'center', formatter:formatDate},
			  {field: 'xxx',title: '操作',width: '10%',align:'center',formatter: function(value, row, index) {
					return "<a href='javascript:void(0)' class='loan_delete_button' onclick=\"Replydel('" + row.replyID + "')\">" + "删除</a>";
					}
				}
			]],
		onLoadSuccess: function(data) {
			$(".loan_delete_button").linkbutton({
				text: '删除',
				plain: true,
				iconCls: 'icon-remove'
			});
		},
		rownumbers: true,  
	    border: 0,
	    fit: true,
		animate: true,
		singleSelect: true,
		scrollbarSize: 0,
		loadMsg: '正在刷新，请稍候...',
		//斑马线
	});
	
	
	$('#update_isDisplay').combobox({
		onChange: function(){	
			var isDisplay = $('#update_isDisplay').combobox('getValue');
			if(isDisplay === "true" || isDisplay === "是") {
				$('#isDisplay').val("true");
			} else {
				$('#isDisplay').val("false");
			}

		}
	});
	
	$('#update_isSelected').combobox({
		onChange: function(){	
			
			var isSelected = $('#update_isSelected').combobox('getValue');
			if(isSelected === "true" || isSelected === "是") {
				$('#isSelected').val("true");
			} else {
				$('#isSelected').val("false");
			}
		}
	});

});

function save() {
	// 提交数据到Action
	$('#formAdd').form('submit', {
		url: "UtteranceBackstageAction_save",
		success: function(data) {
			// data是字符串类型
			// 在JS中将JSON的字符串解析成JSON数据格式，有两种方式：eval（）和 Function对象完成
			var jsonData = eval("(" + data + ")");
			$.messager.show({
				title: '提示消息',
				msg: jsonData.msg,
				timeout: 3000,
				showType: 'slide'
			});
			if(jsonData.msg == "保存成功!"){
				// 关闭窗口
				$("#winAdd").window("close");
				// 表格重新加载
				$("#dg").datagrid("reload");
				
				$("#formAdd").form('reset');
				
				$("#add_message").val('');
				
				KindEditor.instances[0].html('');
			}
			
		}
	});
}

function selectradio() {
	$("#saveTime").val(new Date($("#saveTime").val()).Format("yyyy-MM-dd hh:mm:ss"));
	var inputs = $("#isDisplay").val();
	if(inputs === "true"){
		$('#update_isDisplay').combobox('select', '是');
	}else{
		$('#update_isDisplay').combobox('select', '否');
	}
	
	if($("#isSelected").val() === "true"){
		$('#update_isSelected').combobox('select', '是');
	}else{
		$('#update_isSelected').combobox('select', '否');
	}
		
	
	KindEditor.instances[1].html($('#update_message').val());
	
	
		
}

function edit(id) {
	
	$('#winUpdate').window('open');
	//设置加载完成后回调  
	$('#formUpdate').form({
		onLoadSuccess: selectradio
		
	});
	$('#formUpdate').form('load', "UtteranceBackstageAction_findByIdExcludeReply.action?utteranceID=" + id + "");
	


}

function update() {
	$('#formUpdate').form('submit', {
		url: "UtteranceBackstageAction_update",
		success: function(data) {
			var jsonData = eval("(" + data + ")");
			$.messager.show({
				title: '提示消息',
				msg: jsonData.msg,
				timeout: 3000,
				showType: 'slide'
			});
			// 关闭窗口:
			$("#winUpdate").window("close");
			// 重新加载数据:
			$("#dg").datagrid("reload");
			
		}
	}); 
	
}

function del(id) {
	$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
		if(r) {
			$.post("UtteranceBackstageAction_delete", {
				"utteranceID": id
			}, function(data) {
				$.messager.show({
					title: '提示消息',
					msg: data.msg,
					timeout: 3000,
					showType: 'slide'
				});
				$("#dg").datagrid("reload");
			}, "json");
		}
	});
}

function Replydel(id) {
	
	$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
		if(r) {
			$.post("ReplyBackstageAction_delete", {
				"replyID": id
			}, function(data) {
				$.messager.show({
					title: '提示消息',
					msg: data.msg,
					timeout: 3000,
					showType: 'slide'
				});
				$("#dgComment").datagrid("reload");
			}, "json");
		}
	});
}

  

function searchGrid(){
	$('#dg').datagrid('reload', {
		searchName: $('#utterance-title').textbox('getValue'),
	});
}

function formatDate(value, row, index){
			return new Date(value.time).Format("yyyy-MM-dd hh:mm:ss");

}

Date.prototype.Format = function (fmt){
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function reset_add(){
	$('#formAdd').form('reset');
	
	KindEditor.instances[0].html('');
	
}

function reset_update(){
	$('#formUpdate').form('reset');

	
	KindEditor.instances[1].html('');
}

function formatSfxs(value){
	if (value === true) return "<font color='green'>是</font>";
	if (value === false) return "<font color='red'>否</font>";
}

function viewComment(id) {
	$.messager.confirm('确认', '您确认想要跳转对应回复吗？', function(r) {
		if(r) {
			$('#dgComment').datagrid('reload', {
				"utteranceID" : id,
			});
			$("#winComment").window("open");
		}
	});
}





