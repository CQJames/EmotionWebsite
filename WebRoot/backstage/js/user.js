$(function() {
	$('#dg').datagrid({
		url: 'UserBackstageAction_findAllExcludeComment',
		columns: [[  
		      {field: 'username',title: '用户名',width: '10%',align:'center'},
		      {field: 'icon',title: '头像',width: '15%',align:'center', formatter:formatIcon},
			  {field: 'sex',title: '性别',width: '5%',align:'center', formatter:formatBoolean},
			  {field: 'phoneNumber',title: '手机号码',width: '10%',align:'center'},
			  {field: 'email',title: '电子邮箱',width: '10%',align:'center'},
			  {field: 'remark',title: '备注',width: '10%',align:'center'},
			  {field: 'name',title: '真实姓名',width: '10%',align:'center'},
			  {field: 'isDelete',title: '是否删除',width: '10%',align:'center',formatter:formatSfxs},
			  {field: 'saveTime',title: '保存时间',width: '10%',align:'center', formatter:formatDate},
		      {field: 'changeTime',title: '更改时间',width: '10%',align:'center', formatter:formatDate},
			  {field: 'xxx',title: '操作',width: '10%',align:'center',formatter: function(value, row, index) {
					return "<a href='javascript:void(0)' class='loan_update_button' onclick=\"edit('" + row.userID + "')\">" + "修改</a>" +
							"| <a href='javascript:void(0)' class='loan_delete_button' onclick=\"del('" + row.userID + "')\">" + "删除</a>";
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
		toolbar: '#user-toolbar',
	});
	
	$(":radio").click(function() {
		var sex = document.getElementById("sex");
		if($(this).val() == "true") {
			sex.value = "true";
		} else {
			sex.value = "false";
		}
	});
	
	
	$('#update_isDelete').combobox({
		onChange: function(){	
			var isDelete = $('#update_isDelete').combobox('getValue');
			if(isDelete === "true") {
				$('#isDelete').val("true");
			} else {
				$('#isDelete').val("false");
			}
		}
	});
});

function save() {
	// 提交数据到Action
	$('#formAdd').form('submit', {
		url: "UserBackstageAction_save",
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
				// 表格重新加载	
				$("#formAdd").form('reset');
				
				$("#add-iconimg").attr("src", "");
				 
				$("#add-iconimg-input").val('');
				
				$("#winAdd").window("close");
				
				$("#dg").datagrid("reload");
			}
		}
	});
}

function selectradio() {
	var inputs = document.getElementById("sex");
	//true需要加双引号，这里作为字符串处理
	if(inputs.value == "true") {
		//男的
		$("#women").prop("checked", false);
		$("#men").prop("checked", true);
	} else {
		//女的
		$("#men").prop("checked", false);
		$("#women").prop("checked", true);
	}
	

	if($("#isDelete").val() === "true"){
		$('#update_isDelete').combobox('select', '是');
	}else{
		$('#update_isDelete').combobox('select', '否');
	}
	//通过name找值
//	var a = $('input[name^="icon"]').map(function(){
//		    return {name:this.name,value:this.value};
//        }
//	).get();
	
	var a=$("#update-iconimg-input").val();
	if(a === "" || a === null){
		//图像没有时
		a = "/images/nothing.png";
	}
	$("#update-iconimg").attr("src", "."+a);
	
	   
	//保存时间json格式转Date并回显
	$("#saveTime").val(new Date($("#saveTime").val()).Format("yyyy-MM-dd hh:mm:ss"));

}

function edit(id) {
	$('#winUpdate').window('open');
	//设置加载完成后回调  
	$('#formUpdate').form({
		onLoadSuccess: selectradio
		
	});
	$('#formUpdate').form('load', "UserBackstageAction_findByIdExcludeComment?userID=" + id + "");
}

function update() {
	$('#formUpdate').form('submit', {
		url: "UserBackstageAction_update",
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
			$.post("UserBackstageAction_delete", {
				"userID": id
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

function formatIcon(value,row){
	
	var str = "";
	if(value === "" || value === null){
		//图像没有时
		value = "/images/nothing.png";
	}
	str = '<img style="height:60px; width:150px;" src=".'+value+'" onclick="viewImg(\''+value+'\');" class="easyui-tooltip=" id="'+row.userID+'" title="点击预览图片"/>';
    return str;
}

function viewImg(value){  
	
    var simg =  "." + value;  
    $("#simg").attr("src",simg); 
    $('#dlg').dialog({  
        title: '图片预览',  
        width: 600,  
        //height:$("#simg").height()+4, 
        maximizable: true, 
        resizable:true,  
        closed: false,  
        cache: false,  
        modal: true,
        onClose:function(){
	    	$('#dlg').dialog("center");
	    } 
    });  
    $('#dlg').dialog("center");	
}   

function iconimg_add(){
	$("#iconimg_add").click();
}

function iconimg_update(){
	$("#iconimg_update").click();
}

function icon_imgurl(operate) {
	$.messager.confirm("提示", "是否上传", function(confirm) {

		if(confirm) {
			if(operate == 'add') {
				$("#iconImage-upload-add").form('submit', {
					url: 'UserBackstageAction_upLoad',
					success: function(data) {
						var res = eval("(" + data + ")");
						$("#add-iconimg").attr("src", "." + res.url);
						$("#add-iconimg-input").val(res.url);
					}
				});
			} else if(operate == 'update') {
				$("#iconImage-upload-update").form('submit', {
					url: 'UserBackstageAction_upLoad',
					success: function(data) {
						var res = eval("(" + data + ")");
						$("#update-iconimg").attr("src", "." + res.url);
						$("#update-iconimg-input").val(res.url);
					}
				});
			}
		};
	});
}

function formatBoolean(value, row, index){
	if (value) return "<font color='green'>男</font>";
	else return "<font color='red'>女</font>";
}

function searchGrid(){
	$('#dg').datagrid('reload', {
		searchName: $('#user-username').textbox('getValue'),
	});
}

function formatDate(value, row, index){
	return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
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
}


function reset_add(){
	$('#formAdd').form('reset');
	
	$('#add-iconimg').attr('src', '');
	
	$('#add-iconimg-input').val('');
	
	
}

function reset_update(){
	$('#formUpdate').form('reset');
	
	$('#update-iconimg').attr('src', '');
	
	$('#update-iconimg-input').val('');
	
}

function formatSfxs(value){
	if (value === true) return "<font color='green'>是</font>";
	if (value === false) return "<font color='red'>否</font>";
}


