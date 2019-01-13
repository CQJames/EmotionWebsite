$(function(){
	var URL = window.location.href;
	var userID = document.getElementById("User_ID").value;
	var CsID = document.getElementById("Cs_ID").value;

	
	$.post("CollectionAction_CommunicationSkillsArticleHistory", {
		"messageURl": URL,
		"messageID": CsID,
		"userID": userID,
		
	}, function(data) {
		 var like = JSON.parse(data);
		$('#CsCollection').val(like.msg);
	});
	
	
	$("#likes").click(function(){
		 var dataid = $(this).children().children().next().attr("data-id");
		 var url = 'CommunicationSkillsArticleAjaxAction_addClicks?communicationSkillsArticleID=' + dataid;
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


function CsCollection(){
	var isNull = $("#user_noshow").html();
	if(isNull != '' && isNull != null) {
		var CsID = document.getElementById("Cs_ID").value;
		var userID = document.getElementById("User_ID").value;
		 var url = 'CollectionAction_CommunicationSkillsArticleCollection.action?messageID=' + CsID + '&userID=' + userID;
		 $.ajax({
	        url: url,
	        type: 'get',
		     success: function (data) {
		    	 var like = JSON.parse(data);
		 		$('#CsCollection').val(like.msg);
		     }
		 });
	}
	else{
		window.location.href="/EmotionWebsite/foreground_login.jsp";
	}
	
}