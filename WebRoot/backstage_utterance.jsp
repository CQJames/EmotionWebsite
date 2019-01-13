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
   	    <script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/utterance.js"></script>

	</head>
  <body>
	    <table id="dg"></table>
	    <div id="utterance-toolbar" style="padding:5px;height:30px">
			<div style="margin-bottom:5px; float: left;">
				<a href="javascript:void(0)" onClick="$('#dg').datagrid('reload');" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
				<a href="javascript:void(0)" onClick="$('#winAdd').window('open');" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加问题</a>
			</div>
			<div style=" text-align: right; float: right;">
				<input id="utterance-title" class="easyui-textbox" data-options="prompt: '输入问题内容'" style="width: 150px"></input>
				<a href="javascript:void(0)" onclick="searchGrid()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
				<a href="javascript:void(0)" onClick="$('#dg').datagrid('reload',{searchName:null});" class="easyui-linkbutton" data-options="iconCls:'icon-filter'">重置</a>
			</div>
		</div>
		<!-- 添加用户的表单，默认是隐藏的 -->
		<!-- modal定义是否将窗体显示为模式化窗口。 -->
		<div id="winAdd" class="easyui-window" title="添加问题" data-options="iconCls:'icon-save',modal:true,closed:true,draggable:false,resizable:false,fit:true">
			<div data-options="region:'north',border:0" style="padding: 10px;height:0px;border-top:1px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px" onclick="save();">保存</a>　
		    	<a href="javascript:void(0)" onClick="reset_add()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>　
		    	<a href="javascript:void(0)" onClick="$('#winAdd').window('close')" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
		    </div>
		    <div data-options="region:'center',border:0" style="padding:3% 0 0 6%">
			<form id="formAdd" method="post">
				<table >
		    		<tr>
		    			<td>问题发布者:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="publisher"  data-options="required:true" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>是否精选:</td>
		    			<td>
		    				<select class="easyui-combobox" name="isSelected" data-options="editable:false,panelHeight:'50'" style="width: 500px">
		    					<option value="true">是</option>
		    					<option value="false">否</option>
		    				</select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>是否显示:</td>
		    			<td>
		    				<select class="easyui-combobox" name="isDisplay" data-options="editable:false,panelHeight:'50'" style="width: 500px">
		    					<option value="true">是</option>
		    					<option value="false">否</option>
		    				</select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>浏览人数:</td>
		    			<td>
		    				<input class="easyui-numberspinner" name="visitors" data-options="" style="width: 100px"></input>
		    			</td>
		    		</tr>	
		    		<tr>
		    			<td>问题内容:</td> 
		    			<td>
		    				<textarea id="add_message" class="easyui-kindeditor" name="message"  style="width:100%;height:800%;padding:10px"></textarea>
		    			</td>
		    		</tr>
				</table>
			</form>
			
			
			</div>
		</div>
		
		<!-- 修改用户的表单，默认是隐藏的 -->
		
		<div id="winUpdate" class="easyui-window" title="修改问题" data-options="iconCls:'icon-save',modal:true,closed:true,draggable:false,resizable:false,fit:true">
			<div data-options="region:'north',border:0" style="padding: 10px;height:0px;border-top:1px">
			    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px" onclick="update();">保存</a>　
			    	<a href="javascript:void(0)" onClick="reset_update()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>　
			    	<a href="javascript:void(0)" onClick="$('#winUpdate').window('close')" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
		    </div>
			 <div data-options="region:'center',border:0" style="padding:3% 0 0 6%">
			<form id="formUpdate" method="post">
				<input type="hidden" name="utteranceID" maxLength=50/>
				<input type="hidden" name="isDisplay" id="isDisplay" maxLength=50/>
				<input type="hidden" name="isSelected" id="isSelected" maxLength=50/>
				<input type="hidden" name="saveTime" id="saveTime" maxLength=50/>
				<table >
		    		<tr>
		    			<td>问题发布者:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="publisher"  data-options="required:true" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>是否精选:</td>
		    			<td>
		    				<select id="update_isSelected" class="easyui-combobox"  data-options="editable:false,panelHeight:'50'" style="width: 500px">
		    					<option value="true">是</option>
		    					<option value="false">否</option>
		    				</select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>是否显示:</td>
		    			<td>
		    				<select id="update_isDisplay" class="easyui-combobox"  data-options="editable:false,panelHeight:'50'" style="width: 500px">
		    					<option value="true">是</option>
		    					<option value="false">否</option>
		    				</select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>浏览人数:</td>
		    			<td>
		    				<input class="easyui-numberspinner" name="visitors" data-options="" style="width: 100px"></input>
		    			</td>
		    		</tr>	
		    		</tr>	
		    			<td>文章内容:</td> 
		    			<td>
		    				<textarea id="update_message" class="easyui-kindeditor" name="message"  style="width:150%;height:800%;padding:10px"></textarea>
		    			</td>
		    		</tr>
		    		
				</table>
			</form>
			</div>
		</div>
		
		
		<div id="winComment" class="easyui-window" title="评论显示"  style="width:1000px;height:500px" data-options="iconCls:'icon-reload',modal:true,closed:true,draggable:false,resizable:false">
			 <table id="dgComment"></table>
		</div>
		
		
  </body>
</html>
