<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>心动-后台管理主页</title>
		<!-- 引入EasyUI的CSS和JS -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/common/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/common/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/backstage/css/index.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/common/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/common/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/index.js"></script>
	</head>
	<body>
		<div id="cc" class="easyui-layout" data-options="fit:true"> 
			<div data-options="region:'north',title:'情感系统客户管理',split:true" style="height:15%;padding:20px;">
		    	欢迎你：<s:property value="#session.admin.username"/>
	    </div>       
	    <div data-options="region:'west',title:'系统菜单',split:true" style="width:10%;">
	    	<div id="aa" class="easyui-accordion" data-options="fit:true">   
	        	<div title="用户管理" data-options="iconCls:'icon-reload',selected:true" style="overflow:auto;padding:10px;">   
	            	<a href="backstage_user.jsp" class="menuA" >用户列表</a><Br>  
	            	<a href="backstage_collection.jsp" class="menuA" >收藏浏览管理</a><Br>  
	    		</div>   
		       <div title="交往技巧管理" data-options="iconCls:'icon-reload'" style="overflow:auto;padding:10px;"> 
			    	<a href="backstage_communicationSkillsArticle_article.jsp" class="menuA">交往技巧文章</a><Br>  
			    	<a href="backstage_communicationSkillsArticle_comment.jsp" class="menuA">交往技巧评论</a><Br> 	 
			    </div>   
			    <div title="自我提升管理" data-options="iconCls:'icon-reload'" style="overflow:auto;padding:10px;">   
		    	   	<a href="backstage_selfEnhancementArticle_article.jsp" class="menuA">自我提升文章</a><Br>  
		    	   	<a href="backstage_selfEnhancementArticle_comment.jsp" class="menuA">自我提升评论</a><Br>    
			    </div>
			     <div title="问题管理" data-options="iconCls:'icon-reload'" style="overflow:auto;padding:10px;">   
		    	   	<a href="backstage_utterance.jsp" class="menuA">问题</a><Br>  
		    	   	<a href="backstage_reply.jsp" class="menuA">回复</a><Br>
		    	   	<a href="backstage_utterance_tenIsSelected.jsp" class="menuA">精选十条</a><Br>  
			    </div>
			    <div title="每日一笑管理" data-options="iconCls:'icon-reload'" style="overflow:auto;padding:10px;">   
		    	   <a href="backstage_joke.jsp" class="menuA">每日一笑</a><Br> 
			    </div>  
			    <div title="联系我们管理" data-options="iconCls:'icon-reload'" style="overflow:auto;padding:10px;">   
		    	   	<a href="backstage_contact.jsp" class="menuA">联系我们</a><Br> 
			    </div>
			    <div title="网站数据统计图" data-options="iconCls:'icon-reload'" style="overflow:auto;padding:10px;">   
		    	   	<a href="backstage_user_sex_ratio.jsp" class="menuA">用户性别比例</a><Br>
			    </div>   
		    </div>  
	    </div>   
	    <div data-options="region:'center',title:''" style="height:85%;width:90%;">
	  		<div id="tt" class="easyui-tabs" data-options="fit:true">   
	    		<div title="数据区域" data-options="closable:true" style="padding:20px;display:none;">   
	        		<div>欢迎来到心动后台管理系统！</div>
	    		</div>   
		   </div>  
		</div>  
	    </div>  
	</body>
</html>
