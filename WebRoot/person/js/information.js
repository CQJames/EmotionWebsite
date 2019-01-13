$(function(){
	//判断男女按钮
	var sex = $('#sex').val();
	if(sex == 'true'){
		$('#male').prop("checked",true);
	}
	else if(sex == 'false'){
		$('#female').prop("checked",true);
	}
	
	
});
function myCheck() {
	//性别处理
    var val = $('#male').prop("checked");
    if(val == true){
 	   $('#sex').val('true');
    }
    var val1 = $('#female').prop("checked");
    if(val1 == true){
 	   $('#sex').val('false');
    }
//    //检查表单信息是否为空
//	var form = $("#form");
//	for(var i = 0; i < form[0].elements.length - 1; i++) {
//		if(form[0].elements[i].value.length == 0 || form[0].elements[i].value == "") {
//			form[0].elements[i].focus();
//			return false;
//		}
//	}
//	return true;
}


function icon_imgurl() {
	$("#iconImage-upload").submit();
		
}