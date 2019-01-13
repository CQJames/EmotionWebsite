$(function(){
	//异步发表话语
	$('#btnSubmit').click(function(){
		var message = $(this).parent().prev().children().val();
		$.ajax({
            url:"UtteranceAjaxAction_publishUtterance",
		    type:"post",
		    data:{"message":message},
		    success:function(){
		    	//成功后刷新话语,回到第一页,且数据加1
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
				$('#commentSum').html("话语(" + totalCountNum.toString() + ")");
			    $('#box').paging({
			        initPageNo: 1, // 初始页码
			        totalPages: totalPageNum, //总页数
			        totalCount: '合计' + totalCountNum + '条数据', // 条目总数
			        slideSpeed: 600, // 缓动速度。单位毫秒
			        jump: true, //是否支持跳转
			        callback: function(currPage) { // 回调函数
			        	$.ajax({
				            url:"UtteranceAjaxAction_findUtterancePaging",
						    type:"post",
						    data:{"page":currPage,"rows":pageSizeNum},
						    success:function(result){
						    	var page = JSON.parse(result);
						    	var list = page.list;
						    	var index1 = 0;
						    	$.each(list,function(index,item){
						    		var pageDataIndex = $('#pageData'+index).removeClass('pagingHidden');
							    	var div2 = pageDataIndex.children('div').eq(1);
							    	var span2 = div2.children().eq(1);
							    	span2.html(new Date(item.saveTime).Format("yyyy-MM-dd HH:mm:ss"));
							    	var div3 = pageDataIndex.children('div').eq(2);
							    	var span3 = div3.children().eq(0);
							    	span3.html(item.message);
							    	var div4 = pageDataIndex.children('div').eq(3);
							    	var a = div4.children().eq(0);
							    	a.attr("href",'UtteranceAction_findById?utteranceID=' + item.utteranceID);
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

