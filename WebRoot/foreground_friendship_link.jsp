<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>

		<title>心动-友情链接</title>

		<style type="text/css">
			.piclist {
				margin: 0;
				padding: 0;
				list-style-type: none;
				width: 100%;
				margin-left: 410px;
				margin-top: 110px;
			}
			
			.piclist li {
				float: left;
				width: 200px;
				height: 200px;
			}
			
			.piclist img {
				display: block;
				margin: 0 auto;
				width: 150px;
				height: 150px;
				border: 1px solid #ccc;
			}
			
			.piclist p {
				text-align: center;
			}
		</style>
	</head>

	<body>
		<div align="center">
			<h2>友情链接</h2>
		</div>
		<ul class="piclist">
			<li>
				<a href="https://www.ixiaolu.com" target="_blank"><img src="${pageContext.request.contextPath}/foreground/images/link1.jpg" alt="小鹿情感" />
					<p>小鹿情感</p>
			</li>
			</a>
			<li>
				<a href="https://www.ixiaolu.com" target="_blank"><img src="${pageContext.request.contextPath}/foreground/images/link2.jpg" alt="爱情巴士" />
					<p>爱情巴士</p>
			</li>
			</a>
			<li>
				<a href="http://xiaolugongyi.org/" target="_blank"><img src="${pageContext.request.contextPath}/foreground/images/link3.jpg" alt="爱公益" />
					<p>小鹿公益</p>
			</li>
			</a>
		</ul>
	</body>

</html>