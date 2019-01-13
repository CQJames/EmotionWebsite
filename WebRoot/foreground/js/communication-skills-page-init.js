$(function(){
	var currPage = $('#currPage').html();
	var pageSize = $('#pageSize').html();
	var totalCount = $('#totalCount').html();
	var totalPage = $('#totalPage').html();
    $('#box').paging({
        initPageNo: currPage, // 初始页码
        totalPages: totalPage, //总页数
        totalCount: '合计' + totalCount + '条数据', // 条目总数
        slideSpeed: 600, // 缓动速度。单位毫秒
        jump: true, //是否支持跳转
        callback: function(currPage) { // 回调函数
        	$.ajax({
	            url:"CommunicationSkillsArticleAjaxAction_findCommunicationSkillsArticlePaging",
			    type:"post",
			    data:{"page":currPage,"rows":pageSize},
			    success:function(result){
			    	var page = JSON.parse(result);
			    	var list = page.list;
			    	var index1 = 0;
			    	$.each(list,function(index,item){
			    		var pageDataIndex = $('#pageData'+index).removeClass('pagingHidden');
			    		var img = pageDataIndex.children('img');
			    		img.attr('src','/EmotionWebsite'+item.image);
			    		img.attr('alt',item.title);
			    		var div = img.next();
			    		var h5 = div.children('h5');
			    		var h5_a = h5.children('a');
			    		h5_a.attr('href','CommunicationSkillsArticleAction_findById?communicationSkillsArticleID=' + item.communicationSkillsArticleID);
			    		h5_a.html('题目：'+item.title);
			    		var p = h5.next();
			    		var p_a = p.children('a');
			    		p_a.attr('href','CommunicationSkillsArticleAction_findById?communicationSkillsArticleID=' + item.communicationSkillsArticleID);
			    		p_a.html('摘要：'+item.remark);   					    		
			    		var div1 = p.next();
			    		var span = div1.children('span');
			    		var span1 = span.children('span');
			    		span1.html(item.clicks);
			    		var div2 = span.next();	    		
			    		var a1 = div2.children('a').eq(0);
			    		a1.attr('href','CommunicationSkillsArticleAction_findById?communicationSkillsArticleID=' + item.communicationSkillsArticleID);
			    		a1.html('作者：'+item.publisher);
			    		var a2 = a1.next();
			    		a2.attr('href','CommunicationSkillsArticleAction_findById?communicationSkillsArticleID=' + item.communicationSkillsArticleID);
			    		a2.html(new Date(item.changeTime).Format("yyyy-MM-dd HH:mm:ss"));
			    		index1++;
			    	});
			    	//判断置空之前的分页
			    	for(i=index1;i<6;i++){
			    		var pageDataIndex = $('#pageData'+i).addClass('pagingHidden');
			    	}
	            }
	        });		
        	/*非ajax方法
        	if(clickPage != currPage){
			window.location.href="CommunicationSkillsArticleAction_findSelfEnhancementArticlePaging?page="+clickPage+"&rows="+pageSize;
			}*/
        }
    });
});
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