$(function() {
	//获得异常错误信息
	var isNull = $("#errorLoginMessage").html();
	if(isNull != null && isNull.length != 0) {
		if(isNull == "账号不存在!") {
			$("#errorLoginMessageAccount").html("账号不存在!").removeClass("errorLoginMessageBase").addClass("errorLoginMessage");
		}
		if(isNull == "密码错误!") {
			$("#errorLoginMessagePassword").html("密码错误!").removeClass("errorLoginMessageBase").addClass("errorLoginMessage");
		}
		if(isNull == "验证码错误!") {
			$("#errorLoginMessageCode").html("验证码错误!").removeClass("errorLoginMessageBase").addClass("errorLoginMessage");
		}
	}
	//清除表单错误信息
	$("#usernameInput").bind('input propertychange',function(){
		$("#errorLoginMessageAccount").html("账号错误信息").removeClass("errorLoginMessage").addClass("errorLoginMessageBase");
	});
	$("#passwordInput").bind('input propertychange',function(){
		$("#errorLoginMessagePassword").html("密码错误信息").removeClass("errorLoginMessage").addClass("errorLoginMessageBase");
	});
	$("#codeInput").bind('input propertychange',function(){
		$("#errorLoginMessageCode").html("验证码错误信息").removeClass("errorLoginMessage").addClass("errorLoginMessageBase");
	});
});
//检查表单信息是否为空
function myCheck() {
	var form = $("#form");
	for(var i = 0; i < form[0].elements.length - 1; i++) {
		if(form[0].elements[i].value.length == 0 || form[0].elements[i].value == "") {
			if(i == 0) {
				$("#errorLoginMessageAccount").html("账号不能为空!").removeClass("errorLoginMessageBase").addClass("errorLoginMessage");
			} else if(i == 1) {
				$("#errorLoginMessagePassword").html("密码不能为空!").removeClass("errorLoginMessageBase").addClass("errorLoginMessage");
			} else {
				$("#errorLoginMessageCode").html("验证码不能为空!").removeClass("errorLoginMessageBase").addClass("errorLoginMessage");
			}
			form[0].elements[i].focus();
			return false;
		}
	}
	return true;
}