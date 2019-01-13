package com.ew.web.action;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.CommunicationSkillsArticle;
import com.ew.entity.CommunicationSkillsComment;
import com.ew.entity.User;
import com.ew.service.CommunicationSkillsCommentService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("communicationSkillsCommentAjaxAction")
@Scope("prototype")
public class CommunicationSkillsCommentAjaxAction extends ActionSupport implements ModelDriven<CommunicationSkillsComment> {
	
	/**
	 * 评论AjaxAction
	 */
	private static final long serialVersionUID = 3732019938107036199L;
	
	private CommunicationSkillsComment communicationSkillsComment;
	private CommunicationSkillsCommentService communicationSkillsCommentService;
	//存储将click转为json的字符串
	private String json;
	private String communicationSkillsArticleID;
	private CommunicationSkillsArticle communicationSkillsArticle;
	
	@Override
	public CommunicationSkillsComment getModel() {
		return communicationSkillsComment;
	}
	public CommunicationSkillsComment getCommunicationSkillsComment() {
		return communicationSkillsComment;
	}
	@Resource(name="communicationSkillsComment")
	public void setCommunicationSkillsComment(CommunicationSkillsComment communicationSkillsComment) {
		this.communicationSkillsComment = communicationSkillsComment;
	}
	public CommunicationSkillsCommentService getCommunicationSkillsCommentService() {
		return communicationSkillsCommentService;
	}
	@Resource(name="communicationSkillsCommentService")
	public void setCommunicationSkillsCommentService(CommunicationSkillsCommentService communicationSkillsCommentService) {
		this.communicationSkillsCommentService = communicationSkillsCommentService;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
    public String getCommunicationSkillsArticleID() {
		return communicationSkillsArticleID;
	}
	public void setCommunicationSkillsArticleID(String communicationSkillsArticleID) {
		this.communicationSkillsArticleID = communicationSkillsArticleID;
	}
	public CommunicationSkillsArticle getCommunicationSkillsArticle() {
		return communicationSkillsArticle;
	}
	@Resource(name="communicationSkillsArticle")
	public void setCommunicationSkillsArticle(CommunicationSkillsArticle communicationSkillsArticle) {
		this.communicationSkillsArticle = communicationSkillsArticle;
	}
	
	public String addApplaud() throws Exception {
		
    	String communicationSkillsCommentID = communicationSkillsComment.getCommunicationSkillsCommentID();
    	//根据自我提升评论ID找评论并加一个赞同
    	communicationSkillsComment = communicationSkillsCommentService.updateAddApplaud(communicationSkillsCommentID);
    	//将点赞放入map
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("applaud", communicationSkillsComment.getApplaud());
    	//转换json 
    	json = new Gson().toJson(map);
		
		return SUCCESS;
	}
    
    public String addDisapproval() throws Exception {
		
    	String communicationSkillsCommentID = communicationSkillsComment.getCommunicationSkillsCommentID();
    	//根据自我提升评论ID找评论并加一个反对
    	communicationSkillsComment = communicationSkillsCommentService.updateAddDisapproval(communicationSkillsCommentID);
    	//将反对放入map
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("disapproval", communicationSkillsComment.getDisapproval());
    	//转换json 
    	json = new Gson().toJson(map);
		
		return SUCCESS;
	}
	
    public String publishComment() throws Exception {
		
    	//获得当前用户ID
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User) session.get("user");
    	communicationSkillsComment.setUser(user);
    	//设置ID获得当前评论文章
    	communicationSkillsArticle.setCommunicationSkillsArticleID(communicationSkillsArticleID);
    	communicationSkillsComment.setCommunicationSkillsArticle(communicationSkillsArticle);
    	//初始化赞同与反对
    	communicationSkillsComment.setApplaud(0);
    	communicationSkillsComment.setDisapproval(0);
    	//保存到数据库
    	communicationSkillsCommentService.save(communicationSkillsComment);
		return SUCCESS;
	}
}


