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
   	    <script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/collection.js"></script>

	</head>
  <body>
	    <table id="dg"></table>
	    <div id="collection-toolbar" style="padding:5px;height:30px">
			<div style="margin-bottom:5px; float: left;">
				<a href="javascript:void(0)" onClick="$('#dg').datagrid('reload');" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
			</div>
			<div style=" text-align: right; float: right;">
				<input id="collection-title" class="easyui-textbox" data-options="prompt: '输入收藏标题'" style="width: 150px"></input>
				<a href="javascript:void(0)" onclick="searchGrid()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
				<a href="javascript:void(0)" onClick="$('#dg').datagrid('reload',{searchName:null});" class="easyui-linkbutton" data-options="iconCls:'icon-filter'">重置</a>
			</div>
		</div>
			
		<div id="dlg">
			<img id="simg" src="" style="width:100%;max-height:300px;">
		</div>
		
  </body>
</html>
