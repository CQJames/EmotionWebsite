/**
 * Easyui KindEditor的简单扩展.
 * 有了这个之后,你就可以像使用Easyui组件的方式使用KindEditor了
 * 前提是你需要导入KindEditor的核心js和相关样式. 本插件也需要在Easyui.min和KindEditor之后导入.
 **/
(function ($, K) {
	if (!K)
		throw "KindEditor未定义!";

	function create(target) {
		var opts = $.data(target, 'kindeditor').options;
		var editor = K.create(target, opts);	
		
		$.data(target, 'kindeditor').options.editor = editor;
	}

	$.fn.kindeditor = function (options, param) {
		if (typeof options == 'string') {
			var method = $.fn.kindeditor.methods[options];
			if (method) {
				return method(this, param);
			}
		}
		options = options || {};
		return this.each(function () {
			var state = $.data(this, 'kindeditor');
			if (state) {
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'kindeditor', {
						options : $.extend({}, $.fn.kindeditor.defaults, $.fn.kindeditor.parseOptions(this), options)
					});
			}
			create(this);
		});
	};

	$.fn.kindeditor.parseOptions = function (target) {
		return $.extend({}, $.parser.parseOptions(target, []));
	};

	$.fn.kindeditor.methods = {
		editor : function (jq) {
			return $.data(jq[0], 'kindeditor').options.editor;
		}
	};

	$.fn.kindeditor.defaults = {
		basePath: '/EmotionWebsite/backstage/common/kindeditor/',
		uploadJson: 'CommunicationSkillsArticleBackstageAction_kingeditor.action',
		//fileManagerJson: '/Content/Plugin/kindeditor-4.1.5/asp.net/file_manager_json.ashx',//路径自己改一下
	    resizeType: 1,
	    wellFormatMode: true,
	    newlineTag: 'br',
		//allowFileManager: true,
	    allowPreviewEmoticons: true,
	    allowImageUpload: true,
	   
	    items: ['source', '|', 'emoticons', 'undo', 'redo', 'cut', 'copy', 'paste',
	            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
              'justifyfull', 'insertorderedlist', 'insertunorderedlist', '|',
              'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
              'italic', 'underline', 'lineheight', '|', 'image', 'multiimage', "flash", "media", 'insertfile', 'baidumap',
              'table', 
              'link', 'unlink', 'fullscreen'],
        afterChange:function(){
			this.sync();//这个是必须的,如果你要覆盖afterChange事件的话,请记得最好把这句加上.
		},
		//afterBlur: function(){this.sync();}
	};
	$.parser.plugins.push("kindeditor");
})(jQuery, KindEditor);