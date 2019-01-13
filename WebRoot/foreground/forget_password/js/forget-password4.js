$(function(){
	//设定倒数秒数 
	var count = 5;
	//写一个方法，显示倒数秒数  数到0后跳转页面  
	function countDown(){
		//将count显示在jump中
		document.getElementById("jump").innerHTML= count;
		//每执行一次，count减1
		count -= 1;
		//count=0时，跳转页面
		if(count==0){
			window.location.href="foreground_login.jsp";     
		}
		//每秒执行一次,showTime()
		setTimeout(countDown,1000);
	}
	//执行countDown方法
	countDown();
});