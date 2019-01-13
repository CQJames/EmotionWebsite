<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>心动-信息管理系统</title>
		<link href="${pageContext.request.contextPath}/backstage/css/login_base.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/backstage/css/login.css" rel="stylesheet">
	</head>

	<body class="default">
		<div class="login-hd">
			<div class="left-bg"></div>
			<div class="right-bg"></div>
			<div class="hd-inner">
				<span class="logo"></span>
				<span class="split"></span>
				<span class="sys-name">心动信息库</span>
			</div>
		</div>
		<div class="login-bd">
			<div class="bd-inner">
				<div class="inner-wrap">
					<div class="lg-zone">
						<div class="lg-box">
							<div class="lg-label">
								<h4>管理员登录</h4>
							</div>
							<div class="alert alert-error">
								<i class="iconfont">&nbsp;&#xe62e;</i>
								<span>&nbsp;请在下方输入账号</span>
							</div>
							<!--提交到管理员Action检查是否有该管理员-->
							<form id="form" onsubmit="return myCheck()" action="${pageContext.request.contextPath}/AdminAction_login" method="post">
								<!-- 存储异常错误信息 -->
								<div id="errorLoginMessage"><s:property value="exception.message"/></div>
								<!-- 输入账号信息 -->
								<div class="lg-username input-item clearfix">
									<i class="iconfont">&#xe60d;</i>
									<input id="usernameInput" type="text" name="username" placeholder="请输入账号" value="${username}"/>
								</div>
								<!-- 显示账号错误信息 -->
								<div id="errorLoginMessageAccount" class="errorLoginMessageBase">账号错误信息</div>
								<!-- 输入密码信息 -->
								<div class="lg-password input-item clearfix">
									<i class="iconfont">&#xe634;</i>
									<input id="passwordInput" type="password" name="password" placeholder="请输入密码" value="${password}"/>
								</div>
								<!-- 显示密码错误信息 -->
								<div id="errorLoginMessagePassword" class="errorLoginMessageBase">密码错误信息</div>
								<!-- 输入验证码信息 -->
								<div class="lg-check clearfix">
									<div class="input-item">
										<i class="iconfont">&#xe633;</i>
										<input id="codeInput" type="text" name="checkCode" placeholder="验证码"/>
									</div>
									<span class="check-code">
									    <img id="checkCodeImg" src="${pageContext.request.contextPath}/CheckCodeAction" alt="验证码" title="看不清，换一张"
									    onClick="document.getElementById('checkCodeImg').src='${pageContext.request.contextPath}/CheckCodeAction?timestamp='+ (new Date().getTime().toString(36)); return false" />
									</span>
								</div>
								<!-- 显示验证码错误信息 -->
								<div id="errorLoginMessageCode" class="errorLoginMessageBase">验证码错误信息</div>
								<div class="enter">
									<input type="submit" value="登录" class="admin"/>
								</div>
							</form>
						</div>
					</div>
					<div class="lg-poster"></div>
				</div>
			</div>
		</div>
		<div class="login-ft">
			<div class="ft-inner">
				<div class="address">&nbsp;&nbsp;地址：广东省肇庆市端州区肇庆学院&nbsp;邮编：526060&nbsp;&nbsp;Copyright&nbsp;©&nbsp;2018&nbsp;-&nbsp;2019&nbsp;心动股份有限公司&nbsp;版权所有</div>
				<div class="other-info">建议使用IE8及以上版本浏览器&nbsp;粤ICP备&nbsp;33333333号&nbsp;E-mail：1625186043@qq.com</div>
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/utils/jquery.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/admin-login-message.js"></script>
	</body>
</html>
