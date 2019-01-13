$(function(){
	//获取错误异常信息
	var isNull = $("#errorMessage").html();
	if(isNull != null && isNull.length != 0) {
		if(isNull == "您没有绑定邮箱,联系我们或重新注册") {
			$("#errorMessageShow").html("您没有绑定邮箱,联系我们或重新注册").removeClass("errorMessageHidden").addClass("errorMessageShow");
		}
		if(isNull == "验证码错误") {
			$("#errorMessageShow").html("验证码错误").removeClass("errorMessageHidden").addClass("errorMessageShow");
		}
		if(isNull == "验证码发送至您的邮箱失败") {
			$("#message").html("验证码发送至您的邮箱失败").removeClass("messageHidden").addClass("messageShow");
		}
	}
	else{
		var isNull = $("#sendSuccess").html();
		if(isNull != null && isNull.length != 0){
            $("#message").removeClass("messageHidden").addClass("messageShow");
		}
	}
	//清除表单错误信息
	$("#checkCode").bind('input propertychange',function(){
		$("#errorMessageShow").removeClass("errorMessageShow").addClass("errorMessageHidden");
	});
});
//检查表单信息是否为空
function myCheck() {
	var form = $("#form");
	for(var i = 0; i < form[0].elements.length - 1; i++) {
		if(form[0].elements[i].value.length == 0 || form[0].elements[i].value == "") {
			if(i == 0) {
				$("#errorMessageShow").html("验证码不能为空").removeClass("errorMessageHidden").addClass("errorMessageShow");
			}
			form[0].elements[i].focus();
			return false;
		}
	}
	return true;
}