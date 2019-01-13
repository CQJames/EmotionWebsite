$(function(){
	var flag = $('#success').html();
	if(flag == 'true'){
		$('#flag').addClass('success');
	}
	else{
		$('#flag').remove('success')
	}
	
	var regs = {
			pwdReg: /^.{6,20}$/,
	}
	$("#user-new-password").bind("input propertychange",function(){
		var value = $(this).val();
		if(!regs.pwdReg.test(value)){
			$("#errorMessageShow").html("格式错误，不支持汉字，长度6-20").removeClass("errorMessageHidden").addClass("errorMessageShow");
		}
		else{
			$("#errorMessageShow").removeClass("errorMessageShow").addClass("errorMessageHidden");
		}	
	});
	$("#user-confirm-password").bind("input propertychange",function(){
		var value = $(this).val(); 
		var value1 = $("#user-new-password").val();
		if(value != value1){
			$("#errorMessageShow").html("两次输入密码不一致").removeClass("errorMessageHidden").addClass("errorMessageShow");
		}
		else{
			$("#errorMessageShow").removeClass("errorMessageShow").addClass("errorMessageHidden");
		}
	});
	var message = $('#errorMessage').html();
	if(message == '原密码错误'){
		$("#errorMessageShow").html("原密码错误").removeClass("errorMessageHidden").addClass("errorMessageShow");
	}
});
function myCheck() {
    //检查表单信息是否为空
	var form = $("#form");
	for(var i = 0; i < form[0].elements.length - 1; i++) {
		if(form[0].elements[i].value.length == 0 || form[0].elements[i].value == "") {
			form[0].elements[i].focus();
			return false;
		}
	}
	return true;
}