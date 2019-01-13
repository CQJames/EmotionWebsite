$(function() {
	$('#dg').datagrid({
		url: 'CollectionBackstageAction_findAll',
		columns: [[  
		      {field: 'user',title: '收藏的用户',width: '10%',align:'center', formatter:function(val){if(val){return  val.username}},},
		      {field: 'title',title: '收藏文章标题',width: '10%',align:'center'},
		      {field: 'image',title: '导航图片',width: '15%',align:'center', formatter:formatIcon},
		      {field: 'type',title: '收藏文章类型',width: '10%',align:'center', formatter:formatWzlx},
			  {field: 'messageID',title: '收藏文章ID',width: '10%',align:'center'},
			  {field: 'messageURl',title: '收藏文章URL',width: '10%',align:'center'},
			  {field: 'isCollect',title: '是否被收藏',width: '5%',align:'center',formatter:formatSfxs},
			  {field: 'saveTime',title: '保存时间',width: '10%',align:'center', formatter:formatDate},
		      {field: 'changeTime',title: '更改时间',width: '10%',align:'center', formatter:formatDate},
			  {field: 'xxx',title: '操作',width: '10%',align:'center',formatter: function(value, row, index) {
					return "<a href='javascript:void(0)' class='loan_delete_button' onclick=\"del('" + row.collectionID + "')\">" + "删除</a>";
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
		toolbar: '#collection-toolbar',
	});
	
});

function del(id) {
	$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
		if(r) {
			$.post("CollectionBackstageAction_delete", {
				"collectionID": id
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


function searchGrid(){
	$('#dg').datagrid('reload', {
		searchName: $('#collection-title').textbox('getValue'),
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

function formatSfxs(value){
	if (value === true) return "<font color='red'>收藏</font>";
	if (value === false) return "<font color='green'>浏览</font>";
}

function formatWzlx(value){
	     if (value === "交往技巧") return "交往技巧";
	else if (value === "自我提升") return "自我提升";
	else if (value === "问题") return "问题";
	else if (value === "每日一笑") return "每日一笑";
}




