<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>

	<head>
		<title>心动-匿名树洞</title>
		<link href="${pageContext.request.contextPath}/foreground/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href='http://fonts.googleapis.com/css?family=Karla' rel='stylesheet' type='text/css'>
		<!-- Add fancyBox main JS and CSS files -->
		<script src="${pageContext.request.contextPath}/foreground/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/foreground/js/jquery.magnific-popup.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/foreground/css/magnific-popup.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath}/foreground/js/person.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/foreground/css/paging.css" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/foreground/js/paging.js"></script>
		<script src="${pageContext.request.contextPath}/foreground/js/anonymous-tree-hole-page-init.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/foreground/js/anonymous-tree-hole.js" type="text/javascript"></script>
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
						<li>
							<a href="${pageContext.request.contextPath}/SelfEnhancementArticleAction_findSelfEnhancementArticlePaging">自我提升</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/CommunicationSkillsArticleAction_findCommunicationSkillsArticlePaging">交往技巧</a>
						</li>
						<li class="active">
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
			<div class="single-wrapper">
				<div class="single-blog">
					<div class="related-post">
						<h4>你我的匿名树洞</h4>
						<div class="clear"></div>
						<div class="gallery">
							<!-- start magnific -->
							<ul>
								<li>
									<a class="popup-with-zoom-anim" href="#small-dialog2"><img src="${pageContext.request.contextPath}/foreground/images/tree-hole1.jpg" alt=""></a>
									<p>树洞啊</p>
								</li>
								<li>
									<a class="popup-with-zoom-anim" href="#small-dialog3"><img src="${pageContext.request.contextPath}/foreground/images/tree-hole2.jpg" alt=""></a>
									<p>我一直有个心里话</p>
								</li>
								<li>
									<a class="popup-with-zoom-anim" href="#small-dialog1"><img src="${pageContext.request.contextPath}/foreground/images/tree-hole3.jpg" alt=""></a>
									<p>想告诉您</p>
								</li>
							</ul>
							<div id="small-dialog1" class="mfp-hide">
								<div class="pop_up">
									<h2>关于爱情</h2>
									<img src="${pageContext.request.contextPath}/foreground/images/g1_big.jpg" alt="爱情" />
								</div>
							</div>
                                  <div id="small-dialog2" class="mfp-hide">
								<div class="pop_up">
									<h2>关于亲情</h2>
									<img src="${pageContext.request.contextPath}/foreground/images/g2_big.jpg" alt="亲情" />
								</div>
							</div>
							<div id="small-dialog3" class="mfp-hide">
								<div class="pop_up">
									<h2>关于友情</h2>
									<img src="${pageContext.request.contextPath}/foreground/images/g3_big.jpg" alt="友情" />
								</div>
							</div>
							<div class="clear"></div>
							<!-- end  magnific -->
						</div>
					</div>
					<div class="blog-comment">
						<h5>话语展示</h5>
						<div class="clear"></div>
						<div id="commentSum" class="comment_title" style="margin-bottom: 6%;">话语(<s:property value="#request.utterancePage.totalCount"/>)</div>
						<div class="container_left" style="padding-left:  14%;">
						  <!-- 当前页码 -->
						  <div id="currPage" class="noshow"><s:property value="#request.utterancePage.currPage"/></div>
						  <!-- 每页显示记录数 -->
						  <div id="pageSize" class="noshow"><s:property value="#request.utterancePage.pageSize"/></div>
					      <!-- 总记录数 -->
					      <div id="totalCount" class="noshow"><s:property value="#request.utterancePage.totalCount"/></div>
					      <!-- 总页数 -->
					      <div id="totalPage" class="noshow"><s:property value="#request.utterancePage.totalPage"/></div>
						  <div id="pageData0" class="clearfix position commentBigBox pagingHidden">
						  	<div class="default-icon-box">
								<img class="commentIcon" alt="头像" src="${pageContext.request.contextPath}/images/default-icon.jpg">
							</div>
							<div>
								<span class="commentUser">***</span>
								<span class="commentTime" style='margin-top:0px;'></span>
							</div>
						    <div>
								<span class="commentMessage"></span>
							</div>
							<div class="tree-hole-replay" style="float: right;cursor: pointer;color: blue;"><a>回复</a></div>		
						  </div>
						  <div id="pageData1" class="clearfix position commentBigBox pagingHidden">
						  	<div class="default-icon-box">
								<img class="commentIcon" alt="头像" src="${pageContext.request.contextPath}/images/default-icon.jpg">
							</div>
							<div>
								<span class="commentUser">***</span>
								<span class="commentTime" style='margin-top:0px;'></span>
							</div>
						    <div>
								<span class="commentMessage"></span>
							</div>
							<div class="tree-hole-replay" style="float: right;cursor: pointer;color: blue;"><a>回复</a></div>		
						  </div>
						  <div id="pageData2" class="clearfix position commentBigBox pagingHidden">
						  	<div class="default-icon-box">
								<img class="commentIcon" alt="头像" src="${pageContext.request.contextPath}/images/default-icon.jpg">
							</div>
							<div>
								<span class="commentUser">***</span>
								<span class="commentTime" style='margin-top:0px;'></span>
							</div>
						    <div>
								<span class="commentMessage"></span>
							</div>
							<div class="tree-hole-replay" style="float: right;cursor: pointer;color: blue;"><a>回复</a></div>		
						  </div>
						  <div id="pageData3" class="clearfix position commentBigBox pagingHidden">
						  	<div class="default-icon-box">
								<img class="commentIcon" alt="头像" src="${pageContext.request.contextPath}/images/default-icon.jpg">
							</div>
							<div>
								<span class="commentUser">***</span>
								<span class="commentTime" style='margin-top:0px;'></span>
							</div>
						    <div>
								<span class="commentMessage"></span>
							</div>
							<div class="tree-hole-replay" style="float: right;cursor: pointer;color: blue;"><a>回复</a></div>		
						  </div>
						  <div id="pageData4" class="clearfix position commentBigBox pagingHidden">
						  	<div class="default-icon-box">
								<img class="commentIcon" alt="头像" src="${pageContext.request.contextPath}/images/default-icon.jpg">
							</div>
							<div>
								<span class="commentUser">***</span>
								<span class="commentTime" style='margin-top:0px;'></span>
							</div>
						    <div>
								<span class="commentMessage"></span>
							</div>
							<div class="tree-hole-replay"><a>回复</a></div>		
						  </div>
						  <div id="pageData5" class="clearfix position commentBigBox pagingHidden">
						  	<div class="default-icon-box">
								<img class="commentIcon" alt="头像" src="${pageContext.request.contextPath}/images/default-icon.jpg">
							</div>
							<div>
								<span class="commentUser">***</span>
								<span class="commentTime" style='margin-top:0px;'></span>
							</div>
						    <div>
								<span class="commentMessage"></span>
							</div>
							<div class="tree-hole-replay" style="float: right;cursor: pointer;color: blue;"><a>回复</a></div>		
						  </div>
						  <div style="margin-left: 15%;width: 90%;" class="pagger-box pagger" id="box"></div>
						</div>
					</div>
					<a id="afterLogin" href="${pageContext.request.contextPath}/foreground_login.jsp" style="margin-left: -24%;"><button class="button11">登录/注册后发表</button></a>
					<div id="leaveComment" class="leaveComment noshow" style="width: 65%;margin-left: 18%;">
			    		<h5 class="comment" style="margin-left: 27%;">欢迎留下您的匿名话语哦</h5>
						<div class="clear"></div>
						<div class="text2">
							<textarea id="commentMessage" name="message" placeholder="评论与看法..."></textarea>
						</div>
						<div class="btnSubmit">
							<input id="btnSubmit" class="submit" type="button" value="发送">
						</div>
					</div>
				</div>
				<div class="project-sidebar">
					<!-- <div class="project-list">
						<div class="search_box">
							<form>
								<input type="text" value="查找..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '查找...';}">
								<input type="submit" value="">
							</form>
						</div>
					</div> -->
					<div class="project-list">
						<h4>精选话语</h4>
						<ul class="blog-list">
					    	<s:iterator value="#request.utteranceSList1">
							<li><img src="${pageContext.request.contextPath}/foreground/images/arrow.png" alt="">
								<p>
									<a href="${pageContext.request.contextPath}/UtteranceAction_findById?utteranceID=<s:property value="utteranceID"/>" target="_blank"><s:property value="message"/></a>
								</p>
								<div class="clear"></div>
							</li>
							</s:iterator>
						</ul>
						<ul class="blog-list">
							<s:iterator value="#request.utteranceSList2">
							<li><img src="${pageContext.request.contextPath}/foreground/images/arrow.png" alt="">
								<p>
									<a href="${pageContext.request.contextPath}/UtteranceAction_findById?utteranceID=<s:property value="utteranceID"/>" target="_blank"><s:property value="message"/></a>
								</p>
								<div class="clear"></div>
							</li>
							</s:iterator>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="project-list">
						<h4>热门话语</h4>
						<ul class="blog-list">
							<s:iterator value="#request.utterancePList1">
							<li><img src="${pageContext.request.contextPath}/foreground/images/arrow.png" alt="">
								<p>
									<a href="${pageContext.request.contextPath}/UtteranceAction_findById?utteranceID=<s:property value="utteranceID"/>" target="_blank"><s:property value="message"/></a>
								</p>
								<div class="clear"></div>
							</li>
							</s:iterator>
						</ul>
						<ul class="blog-list">
							<s:iterator value="#request.utterancePList2">
							<li><img src="${pageContext.request.contextPath}/foreground/images/arrow.png" alt="">
								<p>
									<a href="${pageContext.request.contextPath}/UtteranceAction_findById?utteranceID=<s:property value="utteranceID"/>" target="_blank"><s:property value="message"/></a>
								</p>
								<div class="clear"></div>
							</li>
							</s:iterator>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="project-list2">
						<h4>标签</h4>
						<ul>
							<li>
								精选话语
							</li>
							<li>
								热门话语
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
