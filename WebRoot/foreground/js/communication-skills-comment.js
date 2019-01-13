$(function(){
	for(i=0;i<6;i++){
		$("#commentApplaud"+i).click(function(){
			 var span = $(this).children().eq(1);
			 var dataid = span.attr("data-id");
			 var url = 'CommunicationSkillsCommentAjaxAction_addApplaud?communicationSkillsCommentID=' + dataid;
			 $.ajax({
	             url: url,
		         type: 'get',
			     success: function (data) {
			    	 var applaud = JSON.parse(data);
			         span.html(applaud.applaud);
			     }
			 });
		});
		
		$("#commentDisapproval"+i).click(function(){
			 var span = $(this).children().eq(1);
			 var dataid = span.attr("data-id");
			 var url = 'CommunicationSkillsCommentAjaxAction_addDisapproval?communicationSkillsCommentID=' + dataid;
			 $.ajax({
	             url: url,
		         type: 'get',
			     success: function (data) {
			    	 var disapproval = JSON.parse(data);
			         span.html(disapproval.disapproval);
			     }
			 });
		});
	}
	
	//异步评论
	$('#btnSubmit').click(function(){
		var communicationSkillsArticleID = $('#communicationSkillsArticleID').html();
		var message = $(this).parent().prev().children().val();
		$.ajax({
            url:"CommunicationSkillsCommentAjaxAction_publishComment",
		    type:"post",
		    data:{"communicationSkillsArticleID":communicationSkillsArticleID,"message":message},
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
				$('#commentSum').html("留言(" + totalCountNum.toString() + ")");
			    $('#box').paging({
			        initPageNo: 1, // 初始页码
			        totalPages: totalPageNum, //总页数
			        totalCount: '合计' + totalCountNum + '条数据', // 条目总数
			        slideSpeed: 600, // 缓动速度。单位毫秒
			        jump: true, //是否支持跳转
			        callback: function(currPage) { // 回调函数
			        	$.ajax({
				            url:"CommunicationSkillsArticleAjaxAction_findCommentByArticlePaging",
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
							    	img.attr('src','/EmotionWebsite'+item.user.icon);
							    	var div2 = pageDataIndex.children('div').eq(1);
							    	var span1 = div2.children().eq(0);
							    	span1.html(item.user.username);
							    	var span2 = div2.children().eq(1);
							    	span2.html(new Date(item.saveTime).Format("yyyy-MM-dd HH:mm:ss"));
							    	var div3 = pageDataIndex.children('div').eq(2);
							    	var span3 = div3.children().eq(0);
							    	span3.html(item.message);
							    	var div4 = pageDataIndex.children('div').eq(3);
							    	var div5 = div4.children('div').eq(0);
							    	var span4 = div5.children('span');
							    	span4.attr("data-id",item.communicationSkillsCommentID);
							    	span4.html(item.applaud);
							    	var div6 = div4.children('div').eq(1);
							    	var span5 = div6.children('span');
							    	span5.attr("data-id",item.communicationSkillsCommentID);
							    	span5.html(item.disapproval);
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