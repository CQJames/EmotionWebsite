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
	            url:"SelfEnhancementArticleAjaxAction_findSelfEnhancementArticlePaging",
			    type:"post",
			    data:{"page":currPage,"rows":pageSize},
			    success:function(result){
			    	var page = JSON.parse(result);
			    	var list = page.list;
			    	var index1 = 0;
			    	$.each(list,function(index,item){
			    		var pageDataIndex = $('#pageData'+index).removeClass('pagingHidden');
			    		var div1 = pageDataIndex.children().eq(0);
				    	var a = div1.children();
			    		a.attr('href','SelfEnhancementArticleAction_findById?selfEnhancementArticleID=' + item.selfEnhancementArticleID);
			    		var img = a.children();
			    		img.attr('src','/EmotionWebsite'+item.image);
			    		img.attr('alt',item.title);
			    		var likes = div1.next();
			    		var h4 = likes.children();
			    		var a1 = h4.children();
			    		a1.attr('href','SelfEnhancementArticleAction_findById?selfEnhancementArticleID=' + item.selfEnhancementArticleID);
			    		var span = a1.children();
			    		span.html(item.title);
			    		var p = h4.next();
			    		var span1 = p.children().next();
			    		span1.html(item.clicks);
			    		var ul = p.next().next();
			    		var li = ul.children();
			    		var a2 = li.children();
			    		a2.attr('href','SelfEnhancementArticleAction_findById?selfEnhancementArticleID=' + item.selfEnhancementArticleID);
			    		a2.html(new Date(item.changeTime).Format("yyyy-MM-dd HH:mm:ss"));
			    		index1++;
			    	});
			    	for(i=index1;i<2;i++){
			    		var pageDataIndex = $('#pageData'+i).addClass('pagingHidden');
			    	}
	            }
	        });		
        	/*非ajax方法
        	if(clickPage != currPage){
			window.location.href="SelfEnhancementArticleAction_findSelfEnhancementArticlePaging?page="+clickPage+"&rows="+pageSize;
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