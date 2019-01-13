package com.ew.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.SelfEnhancementArticle;
import com.ew.entity.SelfEnhancementComment;
import com.ew.entity.User;
import com.ew.service.SelfEnhancementCommentService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("selfEnhancementCommentAjaxAction")
@Scope("prototype")
public class SelfEnhancementCommentAjaxAction extends ActionSupport implements ModelDriven<SelfEnhancementComment> {
	
	/**
	 * 评论AjaxAction
	 */
	private static final long serialVersionUID = 3732019938107036199L;
	
	private SelfEnhancementComment selfEnhancementComment;
	private SelfEnhancementCommentService selfEnhancementCommentService;
	//存储将click转为json的字符串
	private String json;
	private String selfEnhancementArticleID;
	private SelfEnhancementArticle selfEnhancementArticle;
	
	@Override
	public SelfEnhancementComment getModel() {
		return selfEnhancementComment;
	}
	public SelfEnhancementComment getSelfEnhancementComment() {
		return selfEnhancementComment;
	}
	@Resource(name="selfEnhancementComment")
	public void setSelfEnhancementComment(SelfEnhancementComment selfEnhancementComment) {
		this.selfEnhancementComment = selfEnhancementComment;
	}
	public SelfEnhancementCommentService getSelfEnhancementCommentService() {
		return selfEnhancementCommentService;
	}
	@Resource(name="selfEnhancementCommentService")
	public void setSelfEnhancementCommentService(SelfEnhancementCommentService selfEnhancementCommentService) {
		this.selfEnhancementCommentService = selfEnhancementCommentService;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
    public String getSelfEnhancementArticleID() {
		return selfEnhancementArticleID;
	}
	public void setSelfEnhancementArticleID(String selfEnhancementArticleID) {
		this.selfEnhancementArticleID = selfEnhancementArticleID;
	}
	public SelfEnhancementArticle getSelfEnhancementArticle() {
		return selfEnhancementArticle;
	}
	@Resource(name="selfEnhancementArticle")
	public void setSelfEnhancementArticle(SelfEnhancementArticle selfEnhancementArticle) {
		this.selfEnhancementArticle = selfEnhancementArticle;
	}
	
	public String addApplaud() throws Exception {
		
    	String selfEnhancementCommentID = selfEnhancementComment.getSelfEnhancementCommentID();
    	//根据自我提升评论ID找评论并加一个赞同
    	selfEnhancementComment = selfEnhancementCommentService.updateAddApplaud(selfEnhancementCommentID);
    	//将点赞放入map
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("applaud", selfEnhancementComment.getApplaud());
    	//转换json 
    	json = new Gson().toJson(map);
		
		return SUCCESS;
	}
    
    public String addDisapproval() throws Exception {
		
    	String selfEnhancementCommentID = selfEnhancementComment.getSelfEnhancementCommentID();
    	//根据自我提升评论ID找评论并加一个反对
    	selfEnhancementComment = selfEnhancementCommentService.updateAddDisapproval(selfEnhancementCommentID);
    	//将反对放入map
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("disapproval", selfEnhancementComment.getDisapproval());
    	//转换json 
    	json = new Gson().toJson(map);
		
		return SUCCESS;
	}
    
    public String publishComment() throws Exception {
		
    	//获得当前用户ID
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User) session.get("user");
    	selfEnhancementComment.setUser(user);
    	//设置ID获得当前评论文章
    	selfEnhancementArticle.setSelfEnhancementArticleID(selfEnhancementArticleID);
    	selfEnhancementComment.setSelfEnhancementArticle(selfEnhancementArticle);
    	//初始化赞同与反对
    	selfEnhancementComment.setApplaud(0);
    	selfEnhancementComment.setDisapproval(0);
    	//保存到数据库
    	selfEnhancementCommentService.save(selfEnhancementComment);
		return SUCCESS;
	}
	
}

