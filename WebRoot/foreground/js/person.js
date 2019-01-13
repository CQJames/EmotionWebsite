$(function() {
	var isNull = $("#user_noshow").html();
	if(isNull != '' && isNull != null) {
		//显示个人信息进入栏
		$("#cssloginregister").removeClass("cssloginregister").addClass("cssloginregister_noshow");
		$("#cssmine_noshow").removeClass("cssmine_noshow").addClass("cssmine");
		//显示个人信息面板
		$('#cssmine_noshow').hover(function(){
			$(this).children().eq(1).removeClass("noshow");
		},function(){
			$(this).children().eq(1).addClass("noshow");
		});
		//显示评论区，去掉登录注册按钮
		$("#afterLogin").addClass("noshow");
		$("#leaveComment").removeClass("noshow");
	}
	else{
		//隐藏个人信息进入栏
		$("#cssmine_noshow").removeClass("cssmine").addClass("cssmine_noshow");
		$("#cssloginregister").removeClass("cssloginregister_noshow").addClass("cssloginregister");
		//隐藏评论区，出现登录注册按钮
		$("#leaveComment").addClass("noshow");
		$("#afterLogin").removeClass("noshow");
	}
	
});