<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
		<meta charset="utf-8">
		<title>心动-欢迎登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/foreground/css/reset.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/foreground/css/common.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/foreground/fonts/iconfont.css" />
	</head>
	<body>
		<div class="wrap login_wrap">
			<div class="content">
				<div class="logo"></div>
				<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">登录</div>
						<form id="form" onsubmit="return myCheck()" class="login_form_height" action="${pageContext.request.contextPath}/UserForegroundAction_login" method="post">
							<!-- 存储异常错误信息 -->
							<div id="errorLoginMessage"><s:property value="exception.message"/></div>
							
							<div class="form_text_ipt">
						    	<i class="iconfont">&nbsp;&#xe60d;</i>
								<input id="username" name="username" type="text" placeholder="用户名" value="${username}">
								<!--放正确小图标-->
								<div></div>
							</div>
							<div id="usernameError" class="echeck_warning_hidden">用户名不能为空</div>
							
							<div class="form_text_ipt">
	    						<i class="iconfont">&nbsp;&#xe634;</i>
								<input id="password" name="password" type="password" placeholder="密码" value="${password}">
								<!--放正确小图标-->
								<div></div>
							</div>
							<div id="passwordError" class="echeck_warning_hidden">密码不能为空</div>
							
							<div class="form_check_code">
								<div class="form_check_code_div">
						    		<i class="iconfont">&#xe633;</i>
									<input id="checkCode" class="form_check_code_input" name="checkCode" type="text" placeholder="验证码">
									<!--放正确小图标-->
							    	<div></div>
								</div>
								<span class="form_check_code_span">
				     				<img id="checkCodeImg" src="${pageContext.request.contextPath}/CheckCodeAction" alt="验证码" title="看不清，换一张"
				     				onClick="document.getElementById('checkCodeImg').src='${pageContext.request.contextPath}/CheckCodeAction?timestamp='+ (new Date().getTime().toString(36)); return false" />
				     			</span>
							</div>
							<div id="checkCodeError" class="echeck_warning_hidden">验证码不能为空</div>
							
							<div class="form_check_ipt">
								<div id="remember" class="left check_left">
									<label for="autoLogin"><input type="checkbox" id="autoLogin">&nbsp;记住用户名及密码</label>
								</div>
								<div class="right check_right">
									<a href="${pageContext.request.contextPath}/foreground_forgetPwd1.jsp">忘记密码</a>
								</div>
							</div>
							
							<div class="form_btn">
								<input id="loginButton" type="submit" value="登录"/>
							</div>
							
							<div class="form_reg_btn">
								<span>还没有帐号？</span>
								<a href="${pageContext.request.contextPath}/foreground_register.jsp">马上注册</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="other_link">
            <p>
				<a href="${pageContext.request.contextPath}/foreground_about.jsp" target="_blank">关于小动</a>
				&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/foreground_contact.jsp" target="_blank">联系我们</a>
				&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/foreground_advertisement.jsp" target="_blank">广告服务</a>
				&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/foreground_friendship_link.jsp" target="_blank">友情链接</a>
				&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/foreground_legal.jsp" target="_blank">法律声明</a>
			</p>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/jquery.min.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/jquery.cookie.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/js/login.js" ></script>
	</body>
</html>
