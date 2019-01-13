$(function() {
	$(".menuA").click(function() {
		//获得超链接中的文本内容
		var textContext = this.innerHTML;
		//获得请求路径
		var url = this.href;
		//创建选项卡
		createTabs(textContext, url);
		//让超链接不跳转
		return false;
	});
});
//创建选项卡的函数
function createTabs(textContext, url) {
	//判断选项卡是否存在
	var flag = $("#tt").tabs("exists", textContext);
	if(flag) {
		//已经存在该选项卡
		$("#tt").tabs("select", textContext);
	} else {
		//不存在该选项卡
		$("#tt").tabs("add", {
			title: textContext,
			content: createUrl(url),
			closable: true
		});
	}
}

function createUrl(url) {
	return "<iframe src='" + url + "' style='border:0px;width:100%;height:100%;'></iframe>";
}