<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>心动-关于小动</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href='http://fonts.googleapis.com/css?family=Karla' rel='stylesheet' type='text/css'>
		<link href="${pageContext.request.contextPath}/foreground/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<!-- Add fancyBox main JS and CSS files -->
		<script src="${pageContext.request.contextPath}/foreground/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/foreground/js/jquery.magnific-popup.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/foreground/css/magnific-popup.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath}/foreground/js/person.js" type="text/javascript"></script>
		<script src="http://api.map.baidu.com/api?v=2.0&ak=PeBUf5626HGDsEWw2Hk8e6sw6TrsRv32"></script>
		<script>
			$(document).ready(function() {
				$('.popup-with-zoom-anim').magnificPopup({
					type: 'inline',
					fixedContentPos: false,
					fixedBgPos: true,
					overflowY: 'auto',
					closeBtnInside: true,
					preloader: false,
					midClick: true,
					removalDelay: 300,
					mainClass: 'my-mfp-zoom-in'
				});
			});
		</script>
		<script>
			$(function(){
				var map = new BMap.Map("xindongmap");// 创建地图实例  
				var point = new BMap.Point(23.1161270096,112.5021076909);// 创建点坐标  
				map.centerAndZoom(point, 13);// 初始化地图，设置中心点坐标和地图级别  
				/* map.enableScrollWheelZoom(); */ 
				map.enableKeyboard();
      			map.enableDragging();
      			map.enableDoubleClickZoom();
				var myGeo = new BMap.Geocoder();
				// 将地址解析结果显示在地图上,并调整地图视野
				myGeo.getPoint("端州区黄岗街道肇庆学院田径场", function(point){
				    if (point) {
				        map.centerAndZoom(point, 18);
				        map.addOverlay(new BMap.Marker(point));
				    }else{
				        alert("您选择地址没有解析到结果!");
				    }
				}, "肇庆市"); 
			}); 
		</script>
	</head>

	<body>
		<div class="header">
			<div class="wrap">
				<div class="logo">
					<a href="${pageContext.request.contextPath}/foreground_index.jsp"><img src="${pageContext.request.contextPath}/foreground/images/logo.png" alt="心动标志" /></a>
				</div>
				<!-- 用户是否已登录 -->
				<div id="user_noshow" class="user_noshow"><s:property value="#session.user.username"/></div>
				<!-- 没有显示登录注册 -->
				<div id="cssloginregister" class="cssloginregister">
					<ul>
						<li>
							<a class="login" href="${pageContext.request.contextPath}/foreground_login.jsp">您好，请登录</a>
						</li>
						<li>
							<a class="register" href="${pageContext.request.contextPath}/foreground_register.jsp">免费注册</a>
						</li>
					</ul>
				</div>
				<!-- 有显示我的 -->
				<div id="cssmine_noshow" class="cssmine_noshow">
					<ul>
						<li>
							<a class="mine" href="${pageContext.request.contextPath}/person_index.jsp"><s:property value="#session.user.username"/></a>
						</li>
					</ul>
					<div id="user-panel" class="user-panel noshow">
					    <div class="panel-information"><a href="${pageContext.request.contextPath}/person_information.jsp">个人信息</a></div>
					    <div class="panel-exit"><a href="${pageContext.request.contextPath}/UserForegroundAction_loginOut">退出</a></div>
					</div>
				</div>
				<div class="cssmenu">
					<ul>
						<li>
							<a href="${pageContext.request.contextPath}/foreground_index.jsp">首页</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/JokeAction_findAllShowJoke">轻松一刻</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/SelfEnhancementArticleAction_findSelfEnhancementArticlePaging">自我提升</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/CommunicationSkillsArticleAction_findCommunicationSkillsArticlePaging">交往技巧</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/UtteranceAction_findUtterancePaging">匿名树洞</a>
						</li>
						<li class="active">
							<a href="${pageContext.request.contextPath}/foreground_about.jsp">关于小动</a>
						</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="banner">
			<div class="wrap">
				<div class="clear"></div>
			</div>
		</div>
		<div class="main">
			<div class="project-wrapper">
				<div class="wrap">
					<div class="section group">
						<div class="lsidebar span_1_of_about">
							<img src="${pageContext.request.contextPath}/foreground/images/about-img.jpg" alt="关于小动" />
						</div>
						<div class="cont span_2_of_about">
							<h2 class="welcome_to_team">欢迎加入到我们的团队</h2>
							<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们团队来自于五湖四海，行知湖，仙女湖，鼎湖，心形湖，星湖，东海，南海，西海，北海<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎下一个来自于五湖四海的你</h3>
							<div class="section group example">
								<div class="col_1_of_2 span_1_of_2">
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;心动公司是在情感领域中的一个蓬勃发展的公司，致力于解决人们的情感问题。我们在解决情感问题方面拥有足够的权威性，坚持围绕客户需求提供理想的解决方案。心动成立于2018年，是一家由员工持有全部股份的民营企业，目前有4位员工，业务遍布肇庆学院5区13栋111。</p>
								</div>
								<div class="col_1_of_2 span_1_of_2">
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们公司的理念是：心动不如行动。在本网站，你可以得到许多的服务。其中包括，在开心一刻里体会到两性思维之间的差异，在自我提升里学习到提升自己魅力的方法，在交往技巧里感受到人与人交往是一门学问，在匿名树洞里倾诉自己遇到的情感问题，在关于小动里了解我们和联系我们。希望小动的每一天都能为您带来快乐与知识，感谢您的浏览。</p>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="about-middle">
						<h4><span>公司地址</span></h4>
						<p>心动股份有限公司坐落于优美的北岭山下肇庆学院中紫薇苑内13栋111</p>
						<div id="xindongmap" style="width:760px;height:500px;padding-left: 33%;"></div>
					</div>
					<div class="section group example">
						<div class="clear"></div>
					</div>
					<div class="about-middle">
						<h4><span>团队成员</span></h4>
						<p></p>
					</div>
					<div class="team1">
						<div class="col_1_of_about-grids span_1_of_about-grids">
							<a class="popup-with-zoom-anim" href="#small-dialog1">
								<img src="${pageContext.request.contextPath}/foreground/images/team1.jpg" alt="首席架构师" />
								<span>首席架构师</span>
							</a>
						</div>
						<div class="col_1_of_about-grids span_1_of_about-grids">
							<a class="popup-with-zoom-anim" href="#small-dialog2">
								<img src="${pageContext.request.contextPath}/foreground/images/team2.jpg" alt="后端终结者" />
								<span>后端终结者</span>
							</a>
						</div>
						<div class="col_1_of_about-grids span_1_of_about-grids">
							<a class="popup-with-zoom-anim" href="#small-dialog3">
								<img src="${pageContext.request.contextPath}/foreground/images/team3.jpg" alt="心灵探测者" />
								<span>前端小大佬</span>
							</a>
						</div>
						<div class="col_1_of_about-grids span_1_of_about-grids">
							<a class="popup-with-zoom-anim" href="#small-dialog4">
								<img src="${pageContext.request.contextPath}/foreground/images/team4.jpg" alt="前端小菜鸡" />
								<span>网页小菜鸡</span>
							</a>
						</div>
						<div class="clear"></div>
					</div>
					<div class="team1">
					    <div class="col_1_of_about-grids span_1_of_about-grids">
							<div id="small-dialog1" class="mfp-hide">
								<div class="pop_up">
									<h2>关于设计者</h2>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大家好，我叫蔡耀毅，我来自肇庆学院。负责解决各种奇奇怪怪的东西。</p>
									<div class="social-icons">
									</div>
								</div>
							</div>
						</div>
						<div class="col_1_of_about-grids span_1_of_about-grids">
							<div id="small-dialog2" class="mfp-hide">
								<div class="pop_up">
									<h2>关于设计者</h2>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大家好，我叫薛淦文，我来自肇庆学院。负责解决后端出现的各种问题。</p>
									<div class="social-icons">
									</div>
								</div>
							</div>
						</div>
						<div class="col_1_of_about-grids span_1_of_about-grids">
							<div id="small-dialog3" class="mfp-hide">
								<div class="pop_up">
									<h2>关于设计者</h2>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大家好，我叫黄俊健，我来自肇庆学院。负责解决前端出现的各种问题。</p>
									<div class="social-icons">
									</div>
								</div>
							</div>
						</div>
						<div class="col_1_of_about-grids span_1_of_about-grids">
							<div id="small-dialog4" class="mfp-hide">
								<div class="pop_up">
									<h2>关于设计者</h2>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大家好，我叫古立滨，我来自肇庆学院。负责解决网页设计的各种问题。</p>
									<div class="social-icons">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="footer-top">
				<div class="wrap">
					<div class="section group">
						<div class="col_1_of_3 span_1_of_3">
							<h3>关于小动</h3>
							<p>正如我们的宗旨：心动不如行动，我们一直都在行动。我们最初的团队组建于宿舍，但我们的心不止于宿舍。而是想把我们的服务给到喜欢我们网站的朋友。在这过程中，虽然经历了不少的困难，但想到了千千万万的你们，我们又再次充满了动力。想了解我们更多，请点击下方链接</p>
							<a href="${pageContext.request.contextPath}/foreground_about.jsp"><button class="btn1 btn-8 btn-8b">了解更多</button></a>
							<h4>照片</h4>
							<div class="gallery">
								<ul>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog5"><img src="${pageContext.request.contextPath}/foreground/images/g1.jpg" alt="爱情" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog6"><img src="${pageContext.request.contextPath}/foreground/images/g2.jpg" alt="亲情" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog7"><img src="${pageContext.request.contextPath}/foreground/images/g3.jpg" alt="友情" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog8"><img src="${pageContext.request.contextPath}/foreground/images/g4.jpg" alt="我们" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog9"><img src="${pageContext.request.contextPath}/foreground/images/g5.jpg" alt="地点" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog10"><img src="${pageContext.request.contextPath}/foreground/images/g6.jpg" alt="工作室" /></a>
									</li>
								</ul>
								<div id="small-dialog5" class="mfp-hide">
										<div class="pop_up">
											<h2>爱情</h2>
											<img src="${pageContext.request.contextPath}/foreground/images/g1_big.jpg" alt="爱情" />
										</div>
									</div>
                                    <div id="small-dialog6" class="mfp-hide">
										<div class="pop_up">
											<h2>亲情</h2>
											<img src="${pageContext.request.contextPath}/foreground/images/g2_big.jpg" alt="亲情" />
										</div>
									</div>
									<div id="small-dialog7" class="mfp-hide">
										<div class="pop_up">
											<h2>友情</h2>
											<img src="${pageContext.request.contextPath}/foreground/images/g3_big.jpg" alt="友情" />
										</div>
									</div>
									<div id="small-dialog8" class="mfp-hide">
										<div class="pop_up">
											<h2>我们</h2>
											<img src="${pageContext.request.contextPath}/foreground/images/g4_big.jpg" alt="我们" />
										</div>
									</div>
									<div id="small-dialog9" class="mfp-hide">
										<div class="pop_up">
											<h2>地点</h2>
											<img src="${pageContext.request.contextPath}/foreground/images/g5_big.jpg" alt="地点" />
										</div>
									</div>
									<div id="small-dialog10" class="mfp-hide">
										<div class="pop_up">
											<h2>工作室</h2>
											<img src="${pageContext.request.contextPath}/foreground/images/g6_big.jpg" alt="工作室" />
										</div>
									</div>
							</div>
						</div>
						<div class="col_1_of_3 span_1_of_3">
							<h3>最新微博</h3>
							<div class="footer-list">
								<ul>
									<li><img src="${pageContext.request.contextPath}/foreground/images/wb.jpg" alt="" />
										<p>2018年10月08日心动工作室正式成立</p>
										<div class="clear"></div>
									</li>
									<li><img src="${pageContext.request.contextPath}/foreground/images/wb.jpg" alt="" />
										<p>2018年10月09日招聘蔡耀毅为首席架构师</p>
										<div class="clear"></div>
									</li>
									<li><img src="${pageContext.request.contextPath}/foreground/images/wb.jpg" alt="" />
										<p>2018年10月10日招聘薛淦文为后端开发工程师</p>
										<div class="clear"></div>
									</li>
									<li><img src="${pageContext.request.contextPath}/foreground/images/wb.jpg" alt="" />
										<p>2018年10月11日招聘黄俊健为前端开发工程师</p>
										<div class="clear"></div>
									</li>
									<li><img src="${pageContext.request.contextPath}/foreground/images/wb.jpg" alt="" />
										<p>2018年10月12日招聘古立滨为网页设计工程师</p>
										<div class="clear"></div>
									</li>
								</ul>
							</div>
							<div class="social-icons">
								<h4>合作伙伴</h4>
								<ul>
									<li class="baidu">
									</li>	
									<li class="taobao">
									</li>
									<li class="tengxun">
									</li>
								</ul>
								<div class="partner"><a href="https://www.baidu.com/" target="_blank">百度</a></div>
								&nbsp;&nbsp;
								<div class="partner1"><a href="https://www.taobao.com/" target="_blank">淘宝</a></div>
								&nbsp;&nbsp;
								<div class="partner1"><a href="http://www.qq.com/" target="_blank">腾讯</a></div>
							</div>
						</div>
						<div class="col_1_of_3 span_1_of_3">
							<h3>联系信息</h3>
							<div class="footer-list">
								<ul>
									<li><img src="${pageContext.request.contextPath}/foreground/images/address.png" alt="" />
										<p>地址：广东省肇庆市端州区黄岗街道肇庆学院<br>工作室：紫薇苑13栋111宿舍</p>
										<div class="clear"></div>
									</li>
									<li><img src="${pageContext.request.contextPath}/foreground/images/phone.png" alt="" />
										<p>电话: 13621446560<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;13202783297</p>
										<div class="clear"></div>
									</li>
									<li><img src="${pageContext.request.contextPath}/foreground/images/msg.png" alt="" />
										<p>邮箱: <span class="yellow"><a href="https://mail.qq.com/" target="_blank">1625186043@qq.com</a></span></p>
										<div class="clear"></div>
									</li>
								</ul>
							</div>
							<div class="follow">
								<h4>反馈方式</h4>
								<p>如有意见</p>
								<p>请将您的意见发送至我们的邮箱</p>
								<p>欢迎您的来件</p>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="footer-bottom">
				<div class="wrap">
					<div class="copy">
                        <p class="copy">
                        	<a href="${pageContext.request.contextPath}/foreground_about.jsp" target="_blank">关于小动</a>
		             		&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
	            			<a href="${pageContext.request.contextPath}/foreground_contact.jsp" target="_blank">联系我们</a>
            				&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
	            			<a href="${pageContext.request.contextPath}/foreground_advertisement.jsp" target="_blank">广告服务</a>
            				&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
	 	            		<a href="${pageContext.request.contextPath}/foreground_friendship_link.jsp" target="_blank">友情链接</a>
	             			&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
		            		<a href="${pageContext.request.contextPath}/foreground_legal.jsp" target="_blank">法律声明</a>
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </p>
					</div>
					<div class="footer-nav">
						<ul>
							<li>
								<a href="${pageContext.request.contextPath}/foreground_index.jsp">首页</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/JokeAction_findAllShowJoke">轻松一刻</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/SelfEnhancementArticleAction_findSelfEnhancementArticlePaging">自我提升</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/CommunicationSkillsArticleAction_findCommunicationSkillsArticlePaging">交往技巧</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/UtteranceAction_findUtterancePaging">匿名树洞</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground_about.jsp">关于小动</a>
							</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</body>

</html>