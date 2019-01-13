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
	            url:"UtteranceAjaxAction_findUtterancePaging",
			    type:"post",
			    data:{"page":currPage,"rows":pageSize},
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