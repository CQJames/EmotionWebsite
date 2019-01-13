$(function(){
	var URL = window.location.href;
	var userID = document.getElementById("User_ID").value;
	var SeID = document.getElementById("Se_ID").value;

	
	$.post("CollectionAction_SelfEnhancementArticleHistory", {
		"messageURl": URL,
		"messageID": SeID,
		"userID": userID,
		
	}, function(data) {
		 var like = JSON.parse(data);
		$('#SeCollection').val(like.msg);
	});
	
	
	$("#likes").click(function(){
		 var dataid = $(this).children().children().next().attr("data-id");
		 var url = 'SelfEnhancementArticleAjaxAction_addClicks?selfEnhancementArticleID=' + dataid;
		 $.ajax({
             url: url,
	         type: 'get',
		     success: function (data) {
		    	 var like = JSON.parse(data);
		         $("#like").html(like.like);
		     }
		 });
	});
});

function SeCollection(){
	var isNull = $("#user_noshow").html();
	if(isNull != '' && isNull != null) {
		var SeID = document.getElementById("Se_ID").value;
		var userID = document.getElementById("User_ID").value;
		 var url = 'CollectionAction_SelfEnhancementArticleCollection.action?messageID=' + SeID + '&userID=' + userID;
		 $.ajax({
	       url: url,
	       type: 'get',
		     success: function (data) {
		    	 var like = JSON.parse(data);
		 		$('#SeCollection').val(like.msg);
		     }
		 });
	}
	else{
		window.location.href="/EmotionWebsite/foreground_login.jsp";
	}
}