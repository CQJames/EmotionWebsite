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
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/common/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/common/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/common/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/backstage/js/user.js"></script>
	</head>

	<body>
		
		<table id="dg"></table>
		<div id="user-toolbar" style="padding:5px;height:30px">
			<div style="margin-bottom:5px; float: left;">
				<a href="javascript:void(0)" onClick="$('#dg').datagrid('reload');" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</a>
				<a href="javascript:void(0)" onClick="$('#winAdd').window('open');" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加用户</a>
			</div>
			<div style=" text-align: right; float: right;">
				<input id="user-username" class="easyui-textbox" data-options="prompt: '输入用户名'" style="width: 150px"></input>
				<a href="javascript:void(0)" onclick="searchGrid()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
				<a href="javascript:void(0)" onClick="$('#dg').datagrid('reload',{searchName:null});" class="easyui-linkbutton" data-options="iconCls:'icon-filter'">重置</a>
			</div>
		</div>
		<!-- 添加用户的表单，默认是隐藏的 -->
		<!-- modal定义是否将窗体显示为模式化窗口。 -->
		<div id="winAdd" class="easyui-window" title="添加用户" style="width:1000px;height:500px" data-options="iconCls:'icon-save',modal:true,closed:true,draggable:false,resizable:false">
			<div data-options="region:'north',border:0" style="padding: 10px;height:0px;border-top:1px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px" onclick="save();">保存</a>　
		    	<a href="javascript:void(0)" onClick="reset_add()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>　
		    	<a href="javascript:void(0)" onClick="$('#winAdd').window('close')" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
		    </div>
		    <div data-options="region:'center',border:0" style="padding:3% 0 0 6%">
			<form id="formAdd" method="post">
				<table>
					<tr>
		    			<td>用户名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="username"  data-options="required:true,prompt: '请输入用户名'" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>登陆密码:</td>
		    			<td>
		    				<input class="easyui-textbox" type="password" name="password"  data-options="required:true" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>头像:</td>
		    			<td>
		  					<img id="add-iconimg" class="textbox" src="./images/nothing.png" style="width:150px;height:150px; vertical-align: baseline;">
		  					<input id="add-iconimg-input" type="hidden" name="icon"></input>
		  					<input type="button" value="上传头像" class="easyui-linkbutton" style="padding:10px;margin:-30% 3% 0 3%;" onclick="iconimg_add();" />
	
		  				</td>
		    		</tr>
		    		<tr>
		    			<td>真实姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="name" data-options="prompt: '请输入真实姓名'" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>用户性别 ：</td>
						<td>
							<INPUT type="radio" name="sex" value="true" checked />男
							<INPUT type="radio" name="sex" value="false" />女
						</td>
		    		</tr>
		    		<tr>
		    			<td>手机号码:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="phoneNumber" data-options="prompt: '请输入手机号码'" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>电子邮箱:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="email" data-options="prompt: '请输入电子邮箱'" style="width: 500px"></input>
		    			</td>
		    		</tr>	 
		    		<tr>
		    			<td>是否显示:</td>
		    			<td>
		    				<select class="easyui-combobox" name="isDelete" data-options="editable:false,panelHeight:'50'" style="width: 500px">
		    					<option value="true">是</option>
		    					<option value="false">否</option>
		    				</select>
		    			</td>
		    		</tr>   		
		    		<tr>
		    			<td>备注:</td>
		    			<td>
		    				<textarea class="easyui-kindeditor" name="remark" data-options="multiline:true,required:false,prompt: '请简单描述该新闻'" style="width:600px;height:200px;padding:10px"></textarea>
		    			</td>
		    		</tr>
				</table>
			</form>
			
			
			</div>
		</div>

		<!-- 修改用户的表单，默认是隐藏的 -->
		
		<div id="winUpdate" class="easyui-window" title="修改用户" style="width:1000px;height:500px" data-options="iconCls:'icon-save',modal:true,closed:true,draggable:false,resizable:false">
			<div data-options="region:'north',border:0" style="padding: 10px;height:0px;border-top:1px">
			    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="width:80px" onclick="update();">保存</a>　
			    	<a href="javascript:void(0)" onClick="reset_update()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px">重置</a>　
			    	<a href="javascript:void(0)" onClick="$('#winUpdate').window('close')" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:80px">返回</a>
		    </div>
			 <div data-options="region:'center',border:0" style="padding:3% 0 0 6%">
			<form id="formUpdate" method="post">
				<input type="hidden" name="userID" maxLength=50/>
				<input type="hidden" name="sex" id="sex" maxLength=50/>
				<input type="hidden" name="saveTime" id="saveTime" maxLength=50/>
				<input type="hidden" name="isDelete" id="isDelete" maxLength=50/>
				<table>
					<tr>
		    			<td>用户名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="username"  data-options="required:true,prompt: '请输入用户名'" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>重置密码:</td>
		    			<td>
		    				<input class="easyui-textbox" type="password" name="password"  data-options="required:true" style="width: 500px"></input>
		    			</td>
		    		</tr>	
		    		<tr>
		    			<td>头像:</td>
		    			<td>
		  					<img id="update-iconimg" class="textbox" src="" style="width:150px;height:150px; vertical-align: baseline;">
		  					<input id="update-iconimg-input" type="hidden" name="icon"></input>
		  					<input type="button" value="上传头像" class="easyui-linkbutton" style="padding:10px;margin:-30% 3% 0 3%;" onclick="iconimg_update();" />
	
		  				</td>
		    		</tr>
		    		<tr>
		    			<td>真实姓名:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="name" data-options="prompt: '请输入真实姓名'" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>用户性别 ：</td>
						<td>
							<input type="radio" id="men" name="sex_composite_radio" value="true" />男
							<input type="radio" id="women" name="sex_composite_radio" value="false" />女
						</td>
		    		</tr>
		    		<tr>
		    			<td>手机号码:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="phoneNumber" data-options="prompt: '请输入手机号码'" style="width: 500px"></input>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>电子邮箱:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="email" data-options="prompt: '请输入电子邮箱'" style="width: 500px"></input>
		    			</td>
		    		</tr>	 
		    		<tr>
		    			<td>是否显示:</td>
		    			<td>
		    				<select id="update_isDelete" class="easyui-combobox"  data-options="editable:false,panelHeight:'50'" style="width: 500px">
		    					<option value="true">是</option>
		    					<option value="false">否</option>
		    				</select>
		    			</td>
		    		</tr>   		
		    		<tr>
		    			<td>备注:</td>
		    			<td>
		    				<textarea class="easyui-kindeditor" name="remark" data-options="multiline:true,required:false,prompt: '请简单描述该新闻'" style="width:600px;height:200px;padding:10px"></textarea>
		    			</td>
		    		</tr>
				</table>
			</form>
			</div>
		</div>
			<form  method="post" enctype="multipart/form-data" id="iconImage-upload-add">
				<input id="iconimg_add" type="file" name="upload" style="display:none;" onchange="icon_imgurl('add')"/>
			</form>
			<form  method="post" enctype="multipart/form-data" id="iconImage-upload-update">
				<input id="iconimg_update" type="file" name="upload" style="display:none;" onchange="icon_imgurl('update')"/>
			</form>
			
			<div id="dlg">
				<img id="simg" src="" style="width:100%;max-height:300px;">
			</div>

	</body>

</html>
