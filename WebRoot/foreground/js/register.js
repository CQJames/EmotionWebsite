$(function() {
	var regs = {
		usernameReg: /^(([\u4e00-\u9fa5])|[a-zA-Z0-9]){3,20}$/,
		pwdReg: /^.{6,20}$/,
		//以下三个用于判断密码强度
		numReg: /\d/,
		strReg: /[a-zA-Z]/,
		tsReg: /[^\u4e00-\u9fa5a-zA-Z0-9]/
	}
	
	//用户名输入框事件
	var username = $("#username");
	var correct = username.next();
	var box = username.parent();
	var warning = box.next();
	username.focus(function(){
		var value = $(this).val();
		// 输入框效果
		box.removeClass("error_border").addClass("box_shadow input_border");
		//获得焦点且输入框为空
		if(value == "") {
			//显示提示
			warning.removeClass().addClass("echeck_warning_show_tip");
			warning.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持汉字、字母及数字的组合，长度3-20个字符");
		}
		else if(!regs.usernameReg.test(value) || warning.html() == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名已存在")
		{
			//保留正则错误警告
			box.addClass("error_border");
		}
	});
	username.blur(function(){
		// 清除输入框效果
		box.addClass("normal_border");
		// 使警告不会因失去焦点消去
		var war = warning.html();
		if(war == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名不能为空" || war == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格式错误，仅支持汉字、字母及数字，长度3-20"
			|| war == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名已存在"){
			return false;
		}
		warning.removeClass().addClass("echeck_warning_hidden");
	});
	username.bind("input propertychange",function(){
		var value = $(this).val(); 
		if(value == ""){
			box.addClass("error_border");
			warning.removeClass().addClass("echeck_warning_show_error");
			warning.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名不能为空");
			correct.removeClass();
		}else if(regs.usernameReg.test(value)){
			// ajax检查账号是否已存在
			$.ajax({
	            url:"UserAjaxAction_findByUserUsername",
			    type:"post",
			    data:{"username":value},
			    async:false,
			    success:function(result){
	                var user = JSON.parse(result);
			       	if(user.username == "用户名已存在"){
		        		box.addClass("error_border");
			       		warning.removeClass().addClass("echeck_warning_show_error");
			           	warning.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + user.username);
			           	correct.removeClass();
			       	}else if(user.username == "用户名不存在"){
			         	// 满足reg且用户名不存在
						box.removeClass("error_border").addClass("input_border");
						warning.removeClass().addClass("echeck_warning_hidden");
						correct.addClass("echeck_warning_show_correct");
			       	}  
	            }
	        });		
		}else{
			box.addClass("error_border");
			warning.removeClass().addClass("echeck_warning_show_error");
			warning.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格式错误，仅支持汉字、字母及数字，长度3-20");
			correct.removeClass();
		}
	});
	
	//密码输入框事件
	var psw1 = $("#password");
	var correct1 = psw1.next();
	var box1 = psw1.parent();
	var warning1 = box1.next();
	psw1.focus(function(){
		var value = $(this).val();
		// 输入框效果
		box1.removeClass("error_border").addClass("box_shadow input_border");
		//获得焦点且输入框为空
		if(value == "") {
			//显示提示
			warning1.removeClass().addClass("echeck_warning_show_tip");
			warning1.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持字母、数字及符号的组合,长度6-20个字符");
		}
		else if(!regs.pwdReg.test(value))
		{
			//保留正则错误警告
			box1.addClass("error_border");
		}
	});
	psw1.blur(function(){
		// 清除输入框效果
		box1.addClass("normal_border");
		// 使警告不会因失去焦点消去
		var war = warning1.html();
		if(war == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码不能为空" || war == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格式错误，仅支持字母、数字及字符，长度6-20"){
			return false;
		}
		warning1.removeClass().addClass("echeck_warning_hidden");
	});
	psw1.bind("input propertychange",function(){
		var value = $(this).val(); 
		if(value == ""){
			box1.addClass("error_border");
			warning1.removeClass().addClass("echeck_warning_show_error");
			warning1.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码不能为空");
			correct1.removeClass();
		}else if(regs.pwdReg.test(value)){
			// 满足reg
			box1.removeClass("error_border").addClass("input_border");
			correct1.addClass("echeck_warning_show_correct");
			var level = getPwdLevel(value);
			switch(level) {
				case 1:
					warning1.removeClass().addClass("echeck_warning_show_weak");
					warning1.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码强度为弱，建议使用多种字符组合");
					break;
				case 2:
					warning1.removeClass().addClass("echeck_warning_show_middle");
					warning1.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码强度为中，建议使用更多种字符组合");
					break;
				case 3:
					warning1.removeClass().addClass("echeck_warning_show_strong");
					warning1.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码强度为强，谨记此密码哦");
					break;
			}
		}else{
			box1.addClass("error_border");
			warning1.removeClass().addClass("echeck_warning_show_error");
			warning1.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格式错误，仅支持字母、数字及字符，长度6-20");
			correct1.removeClass();
		}
	});
	
	//重复密码输入框事件
	var psw2 = $("#repassword");
	var correct2 = psw2.next();
	var box2 = psw2.parent();
	var warning2 = box2.next();
	psw2.focus(function(){
		var value = $(this).val();
		// 输入框效果
		box2.removeClass("error_border").addClass("box_shadow input_border");
		//获得焦点且输入框为空
		if(value == "") {
			//显示提示
			warning2.removeClass().addClass("echeck_warning_show_tip");
			warning2.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;两次输入的密码必须一致");
		}
		else if(!regs.pwdReg.test(value))
		{
			//保留正则错误
			box2.addClass("error_border");
		}
	});
	psw2.blur(function(){
		// 清除输入框效果
		box2.addClass("normal_border");
		// 使警告不会因失去焦点消去
		var war = warning2.html();
		if(war == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重复密码不能为空" || 
    		war == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;两次输入的密码不一致"){
			return false;
		}
		warning2.removeClass().addClass("echeck_warning_hidden");
	});
	psw2.bind("input propertychange",function(){
		var value = $(this).val(); 
		var value1 = $("#password").val();
		if(value == ""){
			box2.addClass("error_border");
			warning2.removeClass().addClass("echeck_warning_show_error");
			warning2.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重复密码不能为空");
			correct2.removeClass();
		}else if(value == value1){
			// 满足reg
			box2.removeClass("error_border").addClass("input_border");
			warning2.removeClass().addClass("echeck_warning_hidden");
			correct2.addClass("echeck_warning_show_correct");
		}else{
			box2.addClass("error_border");
			warning2.removeClass().addClass("echeck_warning_show_error");
			warning2.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;两次输入的密码不一致");
			correct2.removeClass();
		}
	});
	
	// 验证码输入框事件
	var checkCode = $("#checkCode");
    var correct3 = checkCode.next();
    var minBox = checkCode.parent();
	var box3 = checkCode.parent().parent();
	var warning3 = box3.next();
	checkCode.focus(function(){
		var value = $(this).val();
		// 输入框效果
		minBox.removeClass("error_border").addClass("box_shadow input_border");
		//获得焦点且输入框为空
		if(value == "") {
			//显示提示
			warning3.removeClass().addClass("echeck_warning_show_tip");
			warning3.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请输入四位验证码");
		}
		else if(value.length != 4)
		{
			//保留长度错误
			minBox.addClass("error_border");
		}
	});
	checkCode.blur(function(){
		// 清除输入框效果
		minBox.addClass("normal_border");
		// 使警告不会因失去焦点消去
		var war = warning3.html();
		if(war == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验证码不能为空" || 
    		war == "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验证码长度为四"){
			return false;
		}
		warning3.removeClass().addClass("echeck_warning_hidden");
	});
	checkCode.bind("input propertychange",function(){
		var value = $(this).val(); 
		if(value == ""){
			minBox.addClass("error_border");
			warning3.removeClass().addClass("echeck_warning_show_error");
			warning3.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验证码不能为空");
			correct3.removeClass();
		}else if(value.length == 4){
			// 满足长度
			minBox.removeClass("error_border").addClass("input_border");
			warning3.removeClass().addClass("echeck_warning_hidden");
			correct3.addClass("echeck_warning_show_correct");
		}else{
			minBox.addClass("error_border");
			warning3.removeClass().addClass("echeck_warning_show_error");
			warning3.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验证码长度为四");
			correct3.removeClass();
		}
	});
	
	//获得异常错误信息
	var isNull = $("#errorRegisterMessage").html();
	if(isNull != null && isNull.length != 0) {
		if(isNull == "用户已存在") {
			warning.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户已存在").removeClass().addClass("echeck_warning_show_error");
		}
		if(isNull == "验证码错误") {
			warning3.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验证码错误").removeClass().addClass("echeck_warning_show_error");
	    }
	}
    
    function getPwdLevel(pwd) {
    	var level = 0;
    	var numReg = true,strReg = true,tsReg = true;
    	for(var i = 0; i < pwd.length; i++) {
    		if(numReg && regs.numReg.test(pwd[i])) {
    			level++;
    			numReg = false;
    			continue;
    		}
    		if(strReg && regs.strReg.test(pwd[i])) {
    			level++;
    			strReg = false;
    			continue;
    		}
    		if(tsReg && regs.tsReg.test(pwd[i])) {
    			level++;
    			tsReg = false;
    		}
    	}
    	return level;
    }
});

//检查表单信息是否为空,数据格式是否正确
function myCheck() {
	var regs = {
		usernameReg: /^(([\u4e00-\u9fa5])|[a-zA-Z0-9]){3,20}$/,
		pwdReg: /^.{6,20}$/,
	}
	var form = $("#form");
	// 检查是否为空
	for(var i = 0; i < form[0].elements.length - 1; i++) {
		if(form[0].elements[i].value.length == 0 || form[0].elements[i].value == "") {
			form[0].elements[i].focus();
			return false;
		}
	}
	// 检查用户名数据格式是否正确
	if(!regs.usernameReg.test(form[0].elements[0].value)){
		$("#usernameError").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格式错误，仅支持汉字、字母及数字，长度3-20").removeClass().addClass("echeck_warning_show_error");
		form[0].elements[0].focus();
		return false;
	}
	// 检查密码数据格式是否正确
	if(!regs.pwdReg.test(form[0].elements[1].value)){
		$("#passwordError").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格式错误，仅支持字母、数字及字符，长度6-20").removeClass().addClass("echeck_warning_show_error");
		form[0].elements[1].focus();
		return false;
	}
	// 检查重复密码数据格式是否正确
	if(form[0].elements[1].value != form[0].elements[2].value){
		$("#repasswordError").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;两次输入的密码不一致").removeClass().addClass("echeck_warning_show_error");
		form[0].elements[2].focus();
		return false;
	}
	// 检查验证码数据格式是否正确
	if(form[0].elements[3].value.length != 4){
		$("#checkCodeError").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;验证码长度为四").removeClass().addClass("echeck_warning_show_error");
		form[0].elements[3].focus();
		return false;
	}
	return true;
}