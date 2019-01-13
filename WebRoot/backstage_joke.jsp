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
   	    <script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/joke.js"></script>

	</head>
  <body>
	    <table id="dg"></table>
	    <div id="joke-toolbar" style="padding:5px;height:30px">
			<div style="margin-bottom:5px; float: left;">
				<a href="javascript:void(0)" onClick="$('#dg').datagrid('reload');" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
				<a href="javascript:void(0)" onClick="$('#winAdd').window('open');" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加笑话</a>
			</div>
			<div style=" text-align: right; float: right;">
				<input id="joke-title" class="easyui-textbox" data-options="prompt: '输入笑话标题'" style="width: 150px"></input>
				<a href="javascript:void(0)" onclick="searchGrid()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
				<a href="javascript:void(0)" onClick="$('#dg').datagrid('reload',{searchName:null});" class="easyui-linkbutton" data-options="iconCls:'icon-filter'">重置</a>
			</div>
		</div>
		<!-- 添加用户的表单，默认是隐藏的 -->
		<!-- modal定义是否将窗体显示为模式化窗口。 -->
		<div id="winAdd" class="easyui-window" title="添加用户" data-options="iconCls:'icon-save',modal:true,closed:true,draggable:false,resizable:false,fit:true">
			<div data-options="region:'north',border:0" style="padding: 10px;height:0px;border-top:1px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px" onclick="save();">保存</a>　
		    	<a href="javascript:void(0)" onClick="reset_add()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>　
		    	<a href="javascript:void(0)" onClick="$('#winAdd').window('close')" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
		    </div>
		    <div data-options="region:'center',border:0" style="padding:3% 0 0 6%">
			<form id="formAdd" method="post">
				<table >
					<tr>
		    			<td>笑话标题:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="title"  data-options="required:true,prompt: '请输入文章标题'" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>作者:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="publisher"  data-options="required:true" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>导航图片:</td>
		    			<td>
		  					<img class="textbox" id="add-imageimg" src="./images/nothing.png" style="width:150px;height:150px; vertical-align: baseline;">
		  					<input id="add-image-input" type="hidden" name="image"></input>
		  					<input type="button" value="上传导航图" class="easyui-linkbutton" style="padding:10px;margin:-15% 3% 0 3%;" onclick="iconimg_add();" />
	
		  				</td>
		    		</tr>
		    		<tr>
		    			<td>文章类型:</td>
		    			<td>
		    				<select class="easyui-combobox" name="category" data-options="editable:false,panelHeight:'100'" style="width: 500px">
		    					<option value="亲情">亲情</option>
		    					<option value="友情">友情</option>
		    					<option value="爱情">爱情</option>
		    				</select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>笑话摘要:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="remark" data-options="prompt: '请输入文章的摘要'" style="width: 500px"></input>
		    			</td>
		    		</tr>	
		    		<tr>
		    			<td>点击量:</td>
		    			<td>
		    				<input class="easyui-numberspinner" name="clicks"  style="width: 100px"></input>
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
		    			<td>内容:</td> 
		    			<td>
		    				<textarea id="add_message" class="easyui-kindeditor" name="message"  style="width:100%;height:800%;padding:10px"></textarea>
		    			</td>
		    		</tr>
				</table>
			</form>
			
			
			</div>
		</div>
		
		<!-- 修改用户的表单，默认是隐藏的 -->
		
		<div id="winUpdate" class="easyui-window" title="修改用户" data-options="iconCls:'icon-save',modal:true,closed:true,draggable:false,resizable:false,fit:true">
			<div data-options="region:'north',border:0" style="padding: 10px;height:0px;border-top:1px">
			    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px" onclick="update();">保存</a>　
			    	<a href="javascript:void(0)" onClick="reset_update()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>　
			    	<a href="javascript:void(0)" onClick="$('#winUpdate').window('close')" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
		    </div>
			 <div data-options="region:'center',border:0" style="padding:3% 0 0 6%">
			<form id="formUpdate" method="post">
				<input type="hidden" name="jokeID" maxLength=50/>
				<input type="hidden" name="isDisplay" id="isDisplay" maxLength=50/>
				<input type="hidden" name="category" id="category" maxLength=50/>
				<input type="hidden" name="saveTime" id="saveTime" maxLength=50/>
				<table >
					<tr>
		    			<td>笑话标题:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="title"  data-options="required:true,prompt: '请输入文章标题'" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>作者:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="publisher"  data-options="required:true" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>导航图片:</td>
		    			<td>
		  					<img class="textbox" id="update-imageimg" src="" style="width:150px;height:150px; vertical-align: baseline;">
		  					<input id="update-image-input" type="hidden" name="image"></input>
		  					<input type="button" value="上传导航图" class="easyui-linkbutton" style="padding:10px;margin:-15% 3% 0 3%;" onclick="iconimg_update();" />
	
		  				</td>
		    		</tr>
		    		<tr>
		    			<td>文章类型:</td>
		    			<td>
		    				<select  id="update_category" class="easyui-combobox" data-options="editable:false,panelHeight:'100'" style="width: 500px">
		    					<option value="亲情">亲情</option>
		    					<option value="友情">友情</option>
		    					<option value="爱情">爱情</option>
		    				</select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>笑话摘要:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="remark" data-options="prompt: '请输入文章的摘要'" style="width: 500px"></input>
		    		</td>
		    		<tr>
		    			<td>点击量:</td>
		    			<td>
		    				<input class="easyui-numberspinner" name="clicks"  style="width: 100px"></input>
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
		    		</tr>	
		    			<td>内容:</td> 
		    			<td>
		    				<textarea id="update_message" class="easyui-kindeditor" name="message"  style="width:150%;height:800%;padding:10px"></textarea>
		    			</td>
		    		</tr>
		    		
				</table>
			</form>
			</div>
		</div>
				
			<form  method="post" enctype="multipart/form-data" id="iconImage_upload_add">
				<input id="iconimg_add" type="file" name="upload" style="display:none;" onchange="icon_imgurl('add')"/>
			</form>
			<form  method="post" enctype="multipart/form-data" id="iconImage_upload_update">
				<input id="iconimg_update" type="file" name="upload" style="display:none;" onchange="icon_imgurl('update')"/>
			</form>
			
			<div id="dlg">
				<img id="simg" src="" style="width:100%;max-height:300px;">
			</div>
		
  </body>
</html>
