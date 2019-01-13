$(function(){
	var regs = {
			pwdReg: /^.{6,20}$/,
	}
	$("#password").bind("input propertychange",function(){
		var value = $(this).val();
		if(!regs.pwdReg.test(value)){
			$("#errorMessageShow").html("格式错误，不支持汉字，长度6-20").removeClass("errorMessageHidden").addClass("errorMessageShow");
		}
		else{
			$("#errorMessageShow").removeClass("errorMessageShow").addClass("errorMessageHidden");
		}	
	});
	$("#repassword").bind("input propertychange",function(){
		var value = $(this).val(); 
		var value1 = $("#password").val();
		if(value != value1){
			$("#errorMessageShow").html("两次输入密码不一致").removeClass("errorMessageHidden").addClass("errorMessageShow");
		}
		else{
			$("#errorMessageShow").removeClass("errorMessageShow").addClass("errorMessageHidden");
		}
	});
});
//检查表单信息是否为空
function myCheck() {
	var form = $("#form");
	for(var i = 0; i < form[0].elements.length - 1; i++) {
		if(form[0].elements[i].value.length == 0 || form[0].elements[i].value == "") {
			if(i == 0) {
				$("#errorMessageShow").html("新密码不能为空").removeClass("errorMessageHidden").addClass("errorMessageShow");
			} else if(i == 1) {
				$("#errorMessageShow").html("确认密码不能为空").removeClass("errorMessageHidden").addClass("errorMessageShow");
			}
			form[0].elements[i].focus();
			return false;
		}
	}
	return true;
}