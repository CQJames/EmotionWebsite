<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML>
<html>

	<head>
		<title>心动-首页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href='http://fonts.googleapis.com/css?family=Karla' rel='stylesheet' type='text/css'>
		<link href="${pageContext.request.contextPath}/foreground/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${pageContext.request.contextPath}/foreground/css/magnific-popup.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/foreground/css/colorbox.css" rel="stylesheet" type="text/css">
		<!-- jQuery -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/jquery.min.js"></script>
		<!-- Add fancyBox main JS and CSS files -->
		<script src="${pageContext.request.contextPath}/foreground/js/jquery.magnific-popup.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/foreground/js/person.js" type="text/javascript"></script>
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
	</head>

	<body>
		<div class="header">
			<div class="wrap">
				<div class="logo">
					<a href="${pageContext.request.contextPath}/foreground_index.jsp"><img src="${pageContext.request.contextPath}/foreground/images/logo.png" alt="心动标志" /></a>
				</div>
				<!-- 用户是否已登录 -->
				<div id="user_noshow" class="user_noshow"><s:property value="#session.user.username"/></div>
				<!-- 没有,显示登录注册 -->
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
				<!-- 有,显示我的 -->
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
						<li class="active">
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
		<div class="index-banner">
			<div class="wmuSlider example1">
				<div class="wmuSliderWrapper">
					<article style="position: absolute; width: 100%; opacity: 0;">
						<div class="banner-wrap">
							<div class="slider-left">
								<h3>轻松一刻</h3>
								<h4>有的不只是笑话</h4>
								<p class="top">而是可以通过它们</p>
								<p class="middle">把我们的脚步慢下来</p>
								<p class="bottom">好好思考一下我们的情感</p>
								<a href="${pageContext.request.contextPath}/JokeAction_findAllShowJoke"><button class="btn btn-8 btn-8b" >马上访问轻松一刻</button></a>
							</div>
							<div class="slider-right">
								<img style="margin-top:38px;" src="${pageContext.request.contextPath}/foreground/images/fun-time.png" />
							</div>
							<div class="clear"></div>
						</div>
					</article>
					<article style="position: relative; width: 100%; opacity: 0;">
						<div class="banner-wrap">
							<div class="slider-right">
								<img style="margin-top:38px;" src="${pageContext.request.contextPath}/foreground/images/self-enhancement.png" />
							</div>
							<div class="slider-left">
								<h3>自我提升</h3>
								<h4>有的不只是自己</h4>
								<p class="top">而是可以通过它们</p>
								<p class="middle">学习到别人身上的优点</p>
								<p class="bottom">让自己成为一个更加优秀的人</p>
								<a href="${pageContext.request.contextPath}/SelfEnhancementArticleAction_findSelfEnhancementArticlePaging"><button class="btn btn-8 btn-8b" >马上访问自我提升</button></a>
							</div>
							<div class="clear"></div>
						</div>
					</article>
					<article style="position: absolute; width: 100%; opacity: 0;">
						<div class="banner-wrap">
							<div class="slider-left">
								<h3>交往技巧</h3>
								<h4>有的不只是技巧</h4>
								<p class="top">而是可以通过它们</p>
								<p class="middle">更好的把情感表达出来</p>
								<p class="bottom">让对方感受到你的真情实意</p>
								<a href="${pageContext.request.contextPath}/CommunicationSkillsArticleAction_findCommunicationSkillsArticlePaging"><button class="btn btn-8 btn-8b" >马上访问交往技巧</button></a>
							</div>
							<div class="slider-right">
								<img style="margin-top:38px;" src="${pageContext.request.contextPath}/foreground/images/communication-skills.png" />
							</div>
							<div class="clear"></div>
						</div>
					</article>
					<article style="position: absolute; width: 100%; opacity: 0;">
						<div class="banner-wrap">
							<div class="slider-right">
								<img style="margin-top:38px;" src="${pageContext.request.contextPath}/foreground/images/anonymous-tree-hole.png" />
							</div>
							<div class="slider-left">
								<h3>匿名树洞</h3>
								<h4>有的不只是密语</h4>
								<p class="top">而是可以通过它们</p>
								<p class="middle">找到懂得回答你的人</p>
								<p class="bottom">帮助你解决生活上的情感问题</p>
								<a href="${pageContext.request.contextPath}/UtteranceAction_findUtterancePaging"><button class="btn btn-8 btn-8b" >马上访问匿名树洞</button></a>
							</div>
							<div class="clear"></div>
						</div>
					</article>
					<article style="position: absolute; width: 100%; opacity: 0;">
						<div class="banner-wrap">
							<div class="slider-left">
								<h3>关于小动</h3>
								<h4>有的不只是我们</h4>
								<p class="top">而是因为有了你们</p>
								<p class="middle">我们更加充满了动力</p>
								<p class="bottom">努力的把内容做好做充实</p>
								<a href="${pageContext.request.contextPath}/foreground_about.jsp"><button class="btn btn-8 btn-8b" >马上访问关于小动</button></a>
							</div>
							<div class="slider-right">
								<img style="margin-top:38px;" src="${pageContext.request.contextPath}/foreground/images/about-xiao-dong.png" />
							</div>
							<div class="clear"></div>
						</div>
					</article>
				</div>
			</div>
			<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/modernizr.custom.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/jquery.wmuSlider.js"></script>
			<script>
				$('.example1').wmuSlider();
			</script>
		</div>
		<!---//End-da-slider----->
		<div class="main">
			<div class="content-top">
				<div class="wrap">
					<h3>我们以情感为中心设计本网站</h3>
					<h5></h5>
					<div class="section group">
						<div class="index_col_1_of_3 index_span_1_of_3">
							<div class="grid1">
								<img src="${pageContext.request.contextPath}/foreground/images/index1.png" alt="" />
								<h4>亲情</h4>
							</div>
							<div class="desc">
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲情，特指亲属之间的那种特殊的感情，不管对方怎样也会爱对方，无论贫穷或富有，无论健康或疾病，甚至无论善恶。有两个特点：一是互相的，不是专一的；二是立体的，不能是单方面的。</p>
							</div>
						</div>
						<div class="index_col_1_of_3 index_span_1_of_3">
							<div class="grid1">
								<img src="${pageContext.request.contextPath}/foreground/images/index2.jpg" alt="" />
								<h4>友情</h4>
							</div>
							<div class="desc">
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;友情，表示“无缘之慈悲”，无缘即无条件之意。无条件之友情，即是世间超越血缘、地缘，出离家园与国家，超越骨肉血亲之爱，超越国别地域之爱，对所有人皆可平等奉献、交流无条件之友情，这即是友情的奇特之处。</p>
							</div>
						</div>
						<div class="index_col_1_of_3 index_span_1_of_3">
							<div class="grid1">
								<img src="${pageContext.request.contextPath}/foreground/images/index3.jpg" alt="" />
								<h4>爱情</h4>
							</div>
							<div class="desc">
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;爱情，是指人与人之间强烈的依恋、亲近、向往，以及无私，并且无所不尽其心的情感。爱情由情爱和性爱两个部分组成，情爱是爱情的灵魂，性爱是爱情的附属品，并不是必要存在的，情爱才是爱情的根本与核心。</p>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="content-middle">
				<h2><span>我们的服务</span></h2>
				<p>在轻松一刻中，我们提供了一些关于交往处理不当所引起的笑话&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在自我提升中，我们提供了您如何把自己培养成优秀的人的导向</p>
				<p>在交往技巧中，我们提供了一些关于人与人之间交往技巧的文章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在匿名树洞中，我们提供了您抒发自己所遇到的情感问题的地方</p>
				<div id="container">
					<div id="main1">
						<ul id="tiles">
							<!-- These are our grid blocks -->
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work1-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work1.png" width="200" height="283">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work2-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work2.png" width="200" height="300">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work3-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work3.png" width="200" height="252">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work4-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work4.png" width="200" height="158">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work5-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work5.png" width="200" height="265">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work6-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work6.png" width="200" height="158">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work7-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work7.png" width="200" height="200">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work8-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work8.png" width="200" height="200">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work9-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work9.png" width="200" height="133">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work10-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work10.png" width="200" height="193">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work11-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work11.png" width="200" height="283">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work12-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work12.png" width="200" height="283">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work13-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work13.png" width="200" height="300">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work14-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work14.png" width="200" height="252">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work15-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work15.png" width="200" height="158">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work16-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work16.png" width="200" height="265">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work17-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work17.png" width="200" height="158">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work18-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work18.png" width="200" height="200">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work19-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work19.png" width="200" height="200">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work20-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work20.png" width="200" height="133">
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/foreground/images/work21-big.png" rel="lightbox" class="cboxElement">
									<img src="${pageContext.request.contextPath}/foreground/images/work21.png" width="200" height="193">
								</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- Include the imagesLoaded plug-in -->
				<script src="${pageContext.request.contextPath}/foreground/js/jquery.imagesloaded.js"></script>
				<!-- include colorbox -->
				<script src="${pageContext.request.contextPath}/foreground/js/jquery.colorbox-min.js"></script>
				<!-- Include the plug-in -->
				<script src="${pageContext.request.contextPath}/foreground/js/jquery.wookmark.js"></script>
				<!-- Once the page is loaded, initalize the plug-in. -->
				<script type="text/javascript">
					(function($) {
						$('#tiles').imagesLoaded(function() {
							// Prepare layout options.
							var options = {
								autoResize: true, // This will auto-update the layout when the browser window is resized.
								container: $('#main1'), // Optional, used for some extra CSS styling
								offset: 2, // Optional, the distance between grid items
								itemWidth: 200 // Optional, the width of a grid item
							};

							// Get a reference to your grid items.
							var handler = $('#tiles li');

							// Call the layout function.
							handler.wookmark(options);

							// Init lightbox
							$('a', handler).colorbox({
								rel: 'lightbox'
							});
						});
					})(jQuery);
				</script>
			</div>
			<div class="content-bottom">
				<h2><span>我们的客户</span></h2>
				<p>我们目前提供了广告服务给予以下的客户，如您也需要，请联系我们</p>
				<ul id="flexiselDemo3">
					<li><img src="${pageContext.request.contextPath}/foreground/images/client1.jpg" /></li>
					<li><img src="${pageContext.request.contextPath}/foreground/images/client2.jpg" /></li>
					<li><img src="${pageContext.request.contextPath}/foreground/images/client3.jpg" /></li>
					<li><img src="${pageContext.request.contextPath}/foreground/images/client4.jpg" /></li>
					<li><img src="${pageContext.request.contextPath}/foreground/images/client5.jpg" /></li>
					<li><img src="${pageContext.request.contextPath}/foreground/images/client6.jpg" /></li>
					<li><img src="${pageContext.request.contextPath}/foreground/images/client7.jpg" /></li>
					<li><img src="${pageContext.request.contextPath}/foreground/images/client8.jpg" /></li>
				</ul>
				<script type="text/javascript">
					$(window).load(function() {
						$("#flexiselDemo1").flexisel();
						$("#flexiselDemo2").flexisel({
							enableResponsiveBreakpoints: true,
							responsiveBreakpoints: {
								portrait: {
									changePoint: 480,
									visibleItems: 1
								},
								landscape: {
									changePoint: 640,
									visibleItems: 2
								},
								tablet: {
									changePoint: 768,
									visibleItems: 3
								}
							}
						});

						$("#flexiselDemo3").flexisel({
							visibleItems: 5,
							animationSpeed: 1000,
							autoPlay: true,
							autoPlaySpeed: 3000,
							pauseOnHover: true,
							enableResponsiveBreakpoints: true,
							responsiveBreakpoints: {
								portrait: {
									changePoint: 480,
									visibleItems: 1
								},
								landscape: {
									changePoint: 640,
									visibleItems: 2
								},
								tablet: {
									changePoint: 768,
									visibleItems: 3
								}
							}
						});

					});
				</script>
				<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/jquery.flexisel.js"></script>
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
										<a class="popup-with-zoom-anim" href="#small-dialog1"><img src="${pageContext.request.contextPath}/foreground/images/g1.jpg" alt="爱情" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog2"><img src="${pageContext.request.contextPath}/foreground/images/g2.jpg" alt="亲情" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog3"><img src="${pageContext.request.contextPath}/foreground/images/g3.jpg" alt="友情" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog4"><img src="${pageContext.request.contextPath}/foreground/images/g4.jpg" alt="我们" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog5"><img src="${pageContext.request.contextPath}/foreground/images/g5.jpg" alt="地点" /></a>
									</li>
									<li>
										<a class="popup-with-zoom-anim" href="#small-dialog6"><img src="${pageContext.request.contextPath}/foreground/images/g6.jpg" alt="工作室" /></a>
									</li>
								</ul>
								<div id="small-dialog1" class="mfp-hide">
									<div class="pop_up">
										<h2>爱情</h2>
										<img src="${pageContext.request.contextPath}/foreground/images/g1_big.jpg" alt="爱情" />
									</div>
								</div>
	                                  <div id="small-dialog2" class="mfp-hide">
									<div class="pop_up">
										<h2>亲情</h2>
										<img src="${pageContext.request.contextPath}/foreground/images/g2_big.jpg" alt="亲情" />
									</div>
								</div>
								<div id="small-dialog3" class="mfp-hide">
									<div class="pop_up">
										<h2>友情</h2>
										<img src="${pageContext.request.contextPath}/foreground/images/g3_big.jpg" alt="友情" />
									</div>
								</div>
								<div id="small-dialog4" class="mfp-hide">
									<div class="pop_up">
										<h2>我们</h2>
										<img src="${pageContext.request.contextPath}/foreground/images/g4_big.jpg" alt="我们" />
									</div>
								</div>
								<div id="small-dialog5" class="mfp-hide">
									<div class="pop_up">
										<h2>地点</h2>
										<img src="${pageContext.request.contextPath}/foreground/images/g5_big.jpg" alt="地点" />
									</div>
								</div>
								<div id="small-dialog6" class="mfp-hide">
									<div class="pop_up">
										<h2>工作室</h2>
										<img src="${pageContext.request.contextPath}/foreground/images/g6_big.jpg" alt="工作室" />
									</div>
								</div>
								<div class="clear"></div>
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
