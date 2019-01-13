<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="Author" contect="http://www.webqin.net">
		<title>忘记密码</title>
		<link type="text/css" href="${pageContext.request.contextPath}/foreground/forget_password/css/css.css" rel="stylesheet" />
	    <script type="text/javascript" src="${pageContext.request.contextPath}/foreground/forget_password/js/jquery-1.8.3-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/foreground/forget_password/js/forget-password4.js"></script>
	</head>

	<body>

		<div class="content">
			<div class="for-liucheng">
				<div class="liulist for-cur"></div>
				<div class="liulist for-cur"></div>
				<div class="liulist for-cur"></div>
				<div class="liulist for-cur"></div>
				<div class="liutextbox">
					<div class="liutext for-cur"><em>1</em><br /><strong>填写账户名</strong></div>
					<div class="liutext for-cur"><em>2</em><br /><strong>验证身份</strong></div>
					<div class="liutext for-cur"><em>3</em><br /><strong>设置新密码</strong></div>
					<div class="liutext for-cur"><em>4</em><br /><strong>完成</strong></div>
				</div>
			</div>
			<!--for-liucheng/-->
			<div class="successs">
				<h3>恭喜您，修改成功！</h3>
			</div>
			<div id="jump" class="jump"></div>
		</div>
		<!--web-width/-->
		</div>
		<!--content/-->

	</body>

</html>