$(function(){
	$.ajax({
	       url: "UserBackstageAction_findSexRatio",
	       type: 'get',
	       success: function (data) {
	    	 var sexRatio = JSON.parse(data);
	    	 var men = sexRatio.men;
	    	 var women = sexRatio.women;
	    	 var unknow = sexRatio.unknow;
	    	 var myChart = echarts.init(document.getElementById('sex-ratio'));
	    	 var option = {
	    			    title : {
	    			        text: '用户男女比例',
	    			        subtext: '包括没设置男女',
	    			        x:'center'
	    			    },
	    			    tooltip : {
	    			        trigger: 'item',
	    			        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    			    },
	    			    legend: {
	    			        orient: 'vertical',
	    			        left: 'left',
	    			        data: ['男','女','未知']
	    			    },
	    			    series : [
	    			        {
	    			            name: '性别',
	    			            type: 'pie',
	    			            radius : '55%',
	    			            center: ['50%', '50%'],
	    			            data:[
	    			                {value:men, name:'男'},
	    			                {value:women, name:'女'},
	    			                {value:unknow, name:'未知'}
	    			            ],
	    			            itemStyle: {
	    			                emphasis: {
	    			                    shadowBlur: 10,
	    			                    shadowOffsetX: 0,
	    			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	    			                }
	    			            }
	    			        }
	    			    ]
	    			};
	    	 	myChart.setOption(option);
	       }
	});
	
});