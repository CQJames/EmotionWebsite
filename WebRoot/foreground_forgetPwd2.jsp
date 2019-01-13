<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>忘记密码</title>
		<link type="text/css" href="${pageContext.request.contextPath}/foreground/forget_password/css/css.css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/forget_password/js/jquery-1.8.3-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/forget_password/js/forget-password2.js"></script>
	</head>

	<body>

		<div class="content">
			<div class="web-width">
				<div class="for-liucheng">
					<div class="liulist for-cur"></div>
					<div class="liulist for-cur"></div>
					<div class="liulist"></div>
					<div class="liulist"></div>
					<div class="liutextbox">
						<div class="liutext for-cur"><em>1</em><br /><strong>填写账户名</strong></div>
						<div class="liutext for-cur"><em>2</em><br /><strong>验证身份</strong></div>
						<div class="liutext"><em>3</em><br /><strong>设置新密码</strong></div>
						<div class="liutext"><em>4</em><br /><strong>完成</strong></div>
					</div>
				</div>
				<!--for-liucheng/-->
				<form id="form" onsubmit="return myCheck()" action="${pageContext.request.contextPath}/ForgetPasswordAction_validationCheckCode" method="post" class="forget-pwd">
					<dl class="sel-yzyx">
					    <div id="sendSuccess"><s:property value="#request.sendSuccess"/></div>
						<dt id="message" class="messageHidden">验证码已发送至您的邮箱！</dt>
						<div class="clears"></div>
					</dl>
					<dl>
						<dt>请输入邮箱收到的四位验证码：</dt>
						<dd><input id="checkCode" name="checkCode" type="text"/></dd>
						<div class="clears"></div>
					</dl>
					<div id="errorMessage"><s:property value="exception.message"/></div>
					<div id="errorMessageShow" class="errorMessageHidden"></div>
					<div class="subtijiao"><input type="submit" value="提交" /></div>
				</form>
				<button id="getCheckCode" class="getButton" onClick="window.location.href='${pageContext.request.contextPath}/ForgetPasswordAction_sendEmailCheckCode'">获取邮箱验证码</button>
				<!--forget-pwd/-->
			</div>
			<!--web-width/-->
		</div>
		<!--content/-->

	</body>

</html>