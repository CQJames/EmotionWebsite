$(function(){
	var URL = window.location.href;
	var userID = document.getElementById("User_ID").value;
	var UtteranceID = document.getElementById("Utterance_ID").value;

	
	$.post("CollectionAction_UtteranceHistory", {
		"messageURl": URL,
		"messageID": UtteranceID,
		"userID": userID,
		
	}, function(data) {
		 var like = JSON.parse(data);
		$('#UtteranceCollection').val(like.msg);
	});
	
	//点击回复，没有登录，到登录页
	$('#utteranceReplyA').click(function(){
		var isNull = $("#user_noshow").html();
		if(isNull != '' && isNull != null) {
		}
		else{
			window.location.href="/EmotionWebsite/foreground_login.jsp";
		}
	});
	//回复谁
	var replyWho;
	$('#utteranceReply').click(function(){
		replyWho = '@楼主';
	});
	$('#replyBox0').click(function(){
		var name = $(this).prev().prev().children().eq(0).html();
		replyWho = '@' + name;
	});
	$('#replyBox1').click(function(){
		var name = $(this).prev().prev().children().eq(0).html();
		replyWho = '@' + name;
	});
	$('#replyBox2').click(function(){
		var name = $(this).prev().prev().children().eq(0).html();
		replyWho = '@' + name;
	});
	$('#replyBox3').click(function(){
		var name = $(this).prev().prev().children().eq(0).html();
		replyWho = '@' + name;
	});
	$('#replyBox4').click(function(){
		var name = $(this).prev().prev().children().eq(0).html();
		replyWho = '@' + name;
	});
	$('#replyBox5').click(function(){
		var name = $(this).prev().prev().children().eq(0).html();
		replyWho = '@' + name;
	});
	//判断是楼主还是其他用户
	var username = $("#user_noshow").html();
	var publisher = $("#utterancePublisher").html();
	//异步回复
	$('#btnSubmit').click(function(){
		var utteranceID = $('#utteranceID').html();
		var message = $(this).parent().prev().children().val();
		if(replyWho == undefined){
			replyWho = '@楼主';
		}
		message = replyWho + '      ' + message;
		$.ajax({
            url:"ReplyAjaxAction_publishReply",
		    type:"post",
		    data:{"utteranceID":utteranceID,"message":message},
		    success:function(){
		    	//成功后刷新评论,回到第一页,且数据加1
				var pageSize = $('#pageSize').html();
				var totalCount = $('#totalCount').html();
				var totalPage = $('#totalPage').html();
				var pageSizeNum = Number.parseInt(pageSize);
			    var totalCountNum = Number.parseInt(totalCount) + 1;
			    var totalPageNum = Math.ceil(totalCountNum / pageSizeNum);
			    //同步数据
			    $('#pageSize').html(pageSizeNum.toString());
			    $('#totalCount').html(totalCountNum.toString());
			    $('#totalPage').html(totalPageNum.toString());
				$('#commentSum').html("相互的回复(" + totalCountNum.toString() + ")");
			    $('#box').paging({
			        initPageNo: 1, // 初始页码
			        totalPages: totalPageNum, //总页数
			        totalCount: '合计' + totalCountNum + '条数据', // 条目总数
			        slideSpeed: 600, // 缓动速度。单位毫秒
			        jump: true, //是否支持跳转
			        callback: function(currPage) { // 回调函数
			        	$.ajax({
			        		url:"UtteranceAjaxAction_findReplyByUtterancePaging",
						    type:"post",
						    data:{"page":currPage,"rows":pageSizeNum},
						    success:function(result){
						    	var page = JSON.parse(result);
						    	var list = page.list;
						    	var index1 = 0;
						    	$.each(list,function(index,item){
						    		var pageDataIndex = $('#pageData'+index).removeClass('pagingHidden');
							    	var div1 = pageDataIndex.children('div').eq(0);
							    	var img = div1.children('img');
							    	var div2 = pageDataIndex.children('div').eq(1);
							    	var span1 = div2.children().eq(0);
							    	//如果是楼主，且不是楼主回复他人的回复
							    	if(username == publisher && username!=(item.user.username)){
							    	    $("#replyBox"+index).removeClass('noshow');
							    	}
							    	else{
							    		$("#replyBox"+index).addClass('noshow');
							    	}
							    	//如果是楼主回复
							    	if(publisher == item.user.username){
							    		img.attr('src','/EmotionWebsite/images/default-icon.jpg');
							    	    span1.html('***');
							    	}
							    	else{
							    		img.attr('src','/EmotionWebsite'+item.user.icon);
							    		span1.html(item.user.username);
							    	}
							    	var span2 = div2.children().eq(1);
							    	span2.html(new Date(item.saveTime).Format("yyyy-MM-dd HH:mm:ss"));
							    	var div3 = pageDataIndex.children('div').eq(2);
							    	var span3 = div3.children().eq(0);
							    	span3.html(item.message);
						    		index1++;
						    	});
						    	for(i=index1;i<6;i++){
						    		var pageDataIndex = $('#pageData'+i).addClass('pagingHidden');
						    	}
				            }
				        });	
			         }
			    });
            }
        });
		
	});
});

function UtteranceCollection(){
	var isNull = $("#user_noshow").html();
	if(isNull != '' && isNull != null) {
		var UtteranceID = document.getElementById("Utterance_ID").value;
		var userID = document.getElementById("User_ID").value;
		 var url = 'CollectionAction_UtteranceCollection.action?messageID=' + UtteranceID + '&userID=' + userID;
		 $.ajax({
	      url: url,
	      type: 'get',
		     success: function (data) {
		    	 var like = JSON.parse(data);
		 		$('#UtteranceCollection').val(like.msg);
		     }
		 });
		
	}
	else{
		window.location.href="/EmotionWebsite/foreground_login.jsp";
	}
}


Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}