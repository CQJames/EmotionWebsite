<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<!-- 引入EasyUI的CSS和JS -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/common/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/common/easyui/themes/icon.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/backstage/common/kindeditor/themes/default/default.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/common/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/common/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/common/easyui/locale/easyui-lang-zh_CN.js"></script>	
   		<script charset="utf-8" src="${pageContext.request.contextPath}/backstage/common/kindeditor/kindeditor-min.js"></script>
	    <script charset="utf-8" src="${pageContext.request.contextPath}/backstage/common/kindeditor/lang/zh_CN.js"></script>
		<script charset="utf-8" src="${pageContext.request.contextPath}/backstage/common/kindeditor/config.js"></script>
   	    <script charset="utf-8" src="${pageContext.request.contextPath}/backstage/common/easyui/kindeditor.js"></script>
   	    <script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/utterance_tenIsSelected.js"></script>

	</head>
  <body>
	    <table id="dg"></table>
	    			
  </body>
</html>
