$(function(){
	var URL = window.location.href;
	var userID = document.getElementById("User_ID").value;
	var jokeID = document.getElementById("Joke_ID").value;

	
	$.post("CollectionAction_JokeHistory", {
		"messageURl": URL,
		"messageID": jokeID,
		"userID": userID,
		
	}, function(data) {
		 var like = JSON.parse(data);
		$('#JokeCollection').val(like.msg);
	});
	
	$("#likes").click(function(){
		 var dataid = $(this).children().children().next().attr("data-id");
		 var url = 'JokeAjaxAction_addClicks?jokeID=' + dataid;
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


function JokeCollection(){
	var isNull = $("#user_noshow").html();
	if(isNull != '' && isNull != null) {
		var jokeID = document.getElementById("Joke_ID").value;
		var userID = document.getElementById("User_ID").value;
		 var url = 'CollectionAction_JokeCollection.action?messageID=' + jokeID + '&userID=' + userID;
		 $.ajax({
	         url: url,
	         type: 'get',
		     success: function (data) {
		    	 var like = JSON.parse(data);
		 		$('#JokeCollection').val(like.msg);
		     }
		 });
	}
	else{
		window.location.href="/EmotionWebsite/foreground_login.jsp";
	}	
}

