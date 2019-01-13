$(function() {
	$('#dg').datagrid({
		url: 'JokeBackstageAction_findAll',
		columns: [[  
		      {field: 'title',title: '笑话标题',width: '10%',align:'center'},
		      {field: 'image',title: '导航图片',width: '15%',align:'center', formatter:formatIcon},
		      {field: 'category',title: '文章类型',width: '10%',align:'center', formatter:formatWzlx},
			  {field: 'publisher',title: '发布者',width: '10%',align:'center'},
			  {field: 'remark',title: '摘要',width: '10%',align:'center'},
			  {field: 'isDisplay',title: '是否显示',width: '10%',align:'center',formatter:formatSfxs},
			  {field: 'clicks',title: '点击量',width: '5%',align:'center'},
			  {field: 'saveTime',title: '保存时间',width: '10%',align:'center', formatter:formatDate},
		      {field: 'changeTime',title: '更改时间',width: '10%',align:'center', formatter:formatDate},
			  {field: 'xxx',title: '操作',width: '15%',align:'center',formatter: function(value, row, index) {
					return "<a href='javascript:void(0)' class='loan_update_button' onclick=\"edit('" + row.jokeID + "')\">" + "修改</a>" +
							"| <a href='javascript:void(0)' class='loan_delete_button' onclick=\"del('" + row.jokeID + "')\">" + "删除</a>";
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
		toolbar: '#joke-toolbar',
	});
	
	$('#update_isDisplay').combobox({
		onChange: function(){	
			var isDisplay = $('#update_isDisplay').combobox('getValue');
			if(isDisplay === "true") {
				$('#isDisplay').val("true");
			} else {
				$('#isDisplay').val("false");
			}
		}
	});
	
	$('#update_category').combobox({
		onChange: function(){	
			var category = $('#update_category').combobox('getValue');
				 if(category === "亲情") {$('#category').val("亲情");} 
			else if(category === "友情") {$('#category').val("友情");}
			else if(category === "爱情") {$('#category').val("爱情");}
		}
	});

});

function save() {
	// 提交数据到Action
	$('#formAdd').form('submit', {
		url: "JokeBackstageAction_save",
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
				
				$("#add-imageimg").attr("src", "");
				 
				$("#add-image-input").val('');
				
				$("#add_message").val('');
				
				KindEditor.instances[0].html('');
			}
			
		}
	});
}

function selectradio() {
	$("#saveTime").val(new Date($("#saveTime").val()).Format("yyyy-MM-dd hh:mm:ss"));
	
	if($("#category").val() === "亲情"){$('#update_category').combobox('select', '亲情');}
	else if($("#category").val() === "友情"){$('#update_category').combobox('select', '友情');}
	else if($("#category").val() === "爱情"){$('#update_category').combobox('select', '爱情');}
	
	var inputs = $("#isDisplay").val();
	if(inputs === "true"){
		$('#update_isDisplay').combobox('select', '是');
	}else{
		$('#update_isDisplay').combobox('select', '否');
	}
		
	var a=$("#update-image-input").val();
	
	if(a === "" || a === null){
		//图像没有时
		a = "/images/nothing.png";
	}
	
	$("#update-imageimg").attr("src", "."+a);
	
	
	//通过name的方式获得
	//var a=$('input[name^="image"]').map(function(){return {value:this.value,name:this.name};}).get();
	
	KindEditor.instances[1].html($('#update_message').val());
	
	
		
}

function edit(id) {
	
	$('#winUpdate').window('open');
	//设置加载完成后回调  
	$('#formUpdate').form({
		onLoadSuccess: selectradio
		
	});
	$('#formUpdate').form('load', "JokeBackstageAction_findById.action?jokeID=" + id + "");
	


}

function update() {
	$('#formUpdate').form('submit', {
		url: "JokeBackstageAction_update",
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
			$.post("JokeBackstageAction_delete", {
				"jokeID": id
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
	str = '<img style="height:60px; width:150px;" src=".'+value+'" onclick="viewImg(\''+value+'\');" class="easyui-tooltip=" id="'+row.communicationSkillsArticleID+'" title="点击预览图片"/>';
    return str;
}

function viewImg(value){  
	
    var simg =  '.'+value;  
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

function icon_imgurl(operate){
	$.messager.confirm("提示", "是否上传", function(confirm){
		if(confirm) {
			if(operate == 'add'){
				$("#iconImage_upload_add").form('submit',{
					url:'JokeBackstageAction_upLoad',
					success:function(data){
						 var res = eval ("(" + data + ")");
						 $("#add-imageimg").attr("src", "."+res.url);
						 $("#add-image-input").val(res.url);
					}
				});	
			}else if(operate == 'update'){
				$("#iconImage_upload_update").form('submit',{
					url:'JokeBackstageAction_upLoad',
					success:function(data){
						 var res = eval ("(" + data + ")");
						 $("#update-imageimg").attr("src", "."+res.url);
						 $("#update-image-input").val(res.url);
					}
				});
			}
		};
	});
}

function searchGrid(){
	$('#dg').datagrid('reload', {
		searchName: $('#joke-title').textbox('getValue'),
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
	
	$('#add-imageimg').attr('src', '');
	
	$('#add-image-input').val('');
	
	KindEditor.instances[0].html('');
	
}

function reset_update(){
	$('#formUpdate').form('reset');
	
	$('#update-imageimg').attr('src', '');
	
	$('#update-image-input').val('');
	
	KindEditor.instances[1].html('');
}

function formatSfxs(value){
	if (value === true) return "<font color='green'>是</font>";
	if (value === false) return "<font color='red'>否</font>";
}

function formatWzlx(value){
    if (value === "亲情") return "亲情";
else if (value === "友情") return "友情";
else if (value === "爱情") return "爱情";
}




