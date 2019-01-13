$(function() {
	$('#dg').datagrid({
		url: 'UtteranceBackstageAction_findTenIsSelected',
		columns: [[  
			  {field: 'publisher',title: '发布者',width: '10%',align:'center'},
			  {field: 'visitors',title: '浏览人数',width: '5%',align:'center'},
			  {field: 'isSelected',title: '是否精选',width: '10%',align:'center',formatter:formatSfxs},
			  {field: 'isDisplay',title: '是否显示',width: '10%',align:'center',formatter:formatSfxs},
			  {field: 'message',title: '问题内容',width: '30%',align:'center'},
			  {field: 'saveTime',title: '保存时间',width: '10%',align:'center', formatter:formatDate},
		      {field: 'changeTime',title: '更改时间',width: '10%',align:'center', formatter:formatDate},
			  {field: 'xxx',title: '操作',width: '20%',align:'center',formatter: function(value, row, index) {
					return "<a href='javascript:void(0)' class='loan_update_button' onclick=\"cancel('" + row.utteranceID + "')\">" + "取消精选</a>";
					}
				}
			]],
		onLoadSuccess: function(data) {
			$(".loan_update_button").linkbutton({
				text: '取消精选',
				plain: true,
				iconCls: 'icon-edit'
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
	
});




function cancel(id) {
	$.messager.confirm('确认', '您确认想要取消精选吗？', function(r) {
		if(r) {
			$.post("UtteranceBackstageAction_cancelIsSelected", {
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


function formatSfxs(value){
	if (value === true) return "<font color='green'>是</font>";
	if (value === false) return "<font color='red'>否</font>";
}







