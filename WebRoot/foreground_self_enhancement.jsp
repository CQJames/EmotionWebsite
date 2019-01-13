<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>心动-自我提升</title>
		<link href="${pageContext.request.contextPath}/foreground/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href='http://fonts.googleapis.com/css?family=Karla' rel='stylesheet' type='text/css'>
		<link href="${pageContext.request.contextPath}/foreground/css/elastislide.css" rel="stylesheet" type="text/css" media="all" />
		<!-- jQuery -->
		<script src="${pageContext.request.contextPath}/foreground/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/jquery.tmpl.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/jquery.elastislide.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/gallery.js"></script>
		<script src="${pageContext.request.contextPath}/foreground/js/person.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/foreground/css/paging.css" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/foreground/js/paging.js"></script>
        <script src="${pageContext.request.contextPath}/foreground/js/self-enhancement-page-init.js"></script>
		<script id="img-wrapper-tmpl" type="text/x-jquery-tmpl">
			<div class="rg-image-wrapper">
				{{if itemsCount > 1}}
				<div class="rg-image-nav">
					<a href="#" class="rg-image-nav-prev">Previous Image</a>
					<a href="#" class="rg-image-nav-next">Next Image</a>
				</div>
				{{/if}}
				<div class="rg-image"></div>
				<div class="rg-loading"></div>
				<div class="rg-caption-wrapper">
					<div class="rg-caption" style="display:none;">
						<p></p>
					</div>
				</div>
			</div>
		</script>
		<!-- Add fancyBox main JS and CSS files -->
		<script src="${pageContext.request.contextPath}/foreground/js/jquery.magnific-popup.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/foreground/css/magnific-popup.css" rel="stylesheet" type="text/css">
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
						<li class="active">
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
		<div class="banner">
			<div class="wrap">
				<div class="clear"></div>
			</div>
		</div>
		<div class="main">
			<div class="project-wrapper">
				<div class="project">
					<div class="project-slider">
						<div id="rg-gallery" class="rg-gallery">
							<div class="rg-thumbs">
								<!-- Elastislide Carousel Thumbnail Viewer -->
								<div class="es-carousel-wrapper">
									<div class="es-nav">
										<span class="es-nav-prev">Previous</span>
										<span class="es-nav-next">Next</span>
									</div>
									<div class="es-carousel">
										<ul>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent1.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent1.jpg" alt="优秀1" /></a>
											</li>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent2.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent2.jpg" alt="优秀2" /></a>
											</li>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent3.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent3.jpg" alt="优秀3" /></a>
											</li>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent4.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent4.jpg" alt="优秀4" /></a>
											</li>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent5.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent5.jpg" alt="优秀5" /></a>
											</li>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent6.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent6.jpg" alt="优秀6" /></a>
											</li>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent7.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent7.jpg" alt="优秀7" /></a>
											</li>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent8.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent8.jpg" alt="优秀8" /></a>
											</li>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent9.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent9.jpg" alt="优秀9" /></a>
											</li>
											<li>
												<a href="#"><img src="${pageContext.request.contextPath}/foreground/images/excellent10.jpg" data-large="${pageContext.request.contextPath}/foreground/images/excellent10.jpg" alt="优秀10" /></a>
											</li>
										</ul>
									</div>
								</div>
								<!-- End Elastislide Carousel Thumbnail Viewer -->
							</div>
							<!-- rg-thumbs -->
						</div>
						<!-- rg-gallery -->
					</div>
					<div class="project-bottom">
						<div class="proj-desc">
							<h4>优秀的人物照</h4>
							<div class="clear"></div>
							<p>情感处理得好的人不一定会成功，但情感处理得差的人就没有成功一说。</p>
							<p>如果您感觉自己还缺点什么。没关系，把您没有的优点，在这里可以从无到有。</p>
						</div>
						<div class="related-desc">
							<h4>努力方向文章</h4>
							<div class="clear"></div>
						</div>
						<div id="self_enhancement_list">
							<div class="wrapper">
							    <!-- 当前页码 -->
							    <div id="currPage" class="noshow"><s:property value="#request.selfEnhancementArticlePage.currPage"/></div>
							    <!-- 每页显示记录数 -->
							    <div id="pageSize" class="noshow"><s:property value="#request.selfEnhancementArticlePage.pageSize"/></div>
							    <!-- 总记录数 -->
							    <div id="totalCount" class="noshow"><s:property value="#request.selfEnhancementArticlePage.totalCount"/></div>
							    <!-- 总页数 -->
							    <div id="totalPage" class="noshow"><s:property value="#request.selfEnhancementArticlePage.totalPage"/></div>
								<div class="self_enhancement_list">
									<div id="pageData0" class="portfolio-wrapper pagingHidden">
										<div class="selfenhancementimgbox">
											<a href="" target="_blank"> 
												<img src="" alt="">
											</a>
										</div>	
										<div class="links">
											<h4>
												<a href="" target="_blank"><span></span></a>
											</h4>
											<p class="img">
												<img src="${pageContext.request.contextPath}/foreground/images/likes.png" title="点赞" alt="点赞" /><span></span>
											</p>
											<div class="clear"></div>
											<ul>
												<li><a href="" target="_blank"></a></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="self_enhancement_list">
									<div id="pageData1" class="portfolio-wrapper pagingHidden">
										<div class="selfenhancementimgbox">
											<a href="" target="_blank"> 
												<img src="" alt="">
											</a>
										</div>
										<div class="links">
											<h4>
												<a href="" target="_blank"><span></span></a>
											</h4>
											<p class="img">
												<img src="${pageContext.request.contextPath}/foreground/images/likes.png" title="点赞" alt="点赞" /><span></span>
											</p>
											<div class="clear"></div>
											<ul>
												<li><a href="" target="_blank"></a></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div class="pagger-box pagger" id="box"></div>
				</div>
				<div class="project-sidebar">
					<div class="project-list">
						<h4>认识自己</h4>
						<ul class="blog-list">
						    <s:iterator value="#request.selfEnhancementArticleKYList1">
							<li><img src="${pageContext.request.contextPath}/foreground/images/arrow.png" alt="">
								<p>
									<a href="${pageContext.request.contextPath}/SelfEnhancementArticleAction_findById?selfEnhancementArticleID=<s:property value="selfEnhancementArticleID"/>" target="_blank"><s:property value="title"/></a>
								</p>
								<div class="clear"></div>
							</li>
							</s:iterator>
						</ul>
						<ul class="blog-list">
						    <s:iterator value="#request.selfEnhancementArticleKYList2">
							<li><img src="${pageContext.request.contextPath}/foreground/images/arrow.png" alt="">
								<p>
									<a href="${pageContext.request.contextPath}/SelfEnhancementArticleAction_findById?selfEnhancementArticleID=<s:property value="selfEnhancementArticleID"/>" target="_blank"><s:property value="title"/></a>
								</p>
								<div class="clear"></div>
							</li>
							</s:iterator>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="project-list self_enhancement_interval">
						<h4>自身建设</h4>
						<ul class="blog-list">
							<s:iterator value="#request.selfEnhancementArticleSCList1">
							<li><img src="${pageContext.request.contextPath}/foreground/images/arrow.png" alt="">
								<p>
									<a href="${pageContext.request.contextPath}/SelfEnhancementArticleAction_findById?selfEnhancementArticleID=<s:property value="selfEnhancementArticleID"/>" target="_blank"><s:property value="title"/></a>
								</p>
								<div class="clear"></div>
							</li>
							</s:iterator>
						</ul>
						<ul class="blog-list">
							<s:iterator value="#request.selfEnhancementArticleSCList2">
							<li><img src="${pageContext.request.contextPath}/foreground/images/arrow.png" alt="">
								<p>
									<a href="${pageContext.request.contextPath}/SelfEnhancementArticleAction_findById?selfEnhancementArticleID=<s:property value="selfEnhancementArticleID"/>"  target="_blank"><s:property value="title"/></a>
								</p>
								<div class="clear"></div>
							</li>
							</s:iterator>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="project-list2 self_enhancement_interval">
						<h4>标签</h4>
						<ul>
							<li>
								认识自己
							</li>
							<li>
								自身建设
							</li>
						</ul>
						<div class="clear"></div>
					</div>
				</div>
				<div class="clear"></div>
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