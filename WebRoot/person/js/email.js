$(function(){
	var flag = $('#success').html();
	if(flag == 'true'){
		$('#flag').addClass('success');
	}
	else{
		$('#flag').remove('success')
	}
	
	var regs = {
			emailReg: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/,
	}
	$("#user-email").bind("input propertychange",function(){
		var value = $(this).val();
		if(!regs.emailReg.test(value)){
			$("#errorMessageShow").html("邮箱格式错误").removeClass("errorMessageHidden").addClass("errorMessageShow");
		}
		else{
			$("#errorMessageShow").removeClass("errorMessageShow").addClass("errorMessageHidden");
		}	
	});
	var message = $('#errorMessage').html();
	if(message == '验证码错误'){
		$("#errorMessageShow").html("验证码错误").removeClass("errorMessageHidden").addClass("errorMessageShow");
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