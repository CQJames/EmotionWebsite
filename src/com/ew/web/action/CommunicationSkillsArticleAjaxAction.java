package com.ew.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.CommunicationSkillsArticle;
import com.ew.entity.CommunicationSkillsArticlePage;
import com.ew.entity.CommunicationSkillsComment;
import com.ew.entity.CommunicationSkillsCommentPage;
import com.ew.service.CommunicationSkillsArticleService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("communicationSkillsArticleAjaxAction")
@Scope("prototype")
public class CommunicationSkillsArticleAjaxAction extends ActionSupport implements ModelDriven<CommunicationSkillsArticle>{

	
	/**
	 * 自我提升Action
	 */
	private static final long serialVersionUID = -5288918987705664200L;
	
	private CommunicationSkillsArticle communicationSkillsArticle;
	private CommunicationSkillsArticleService communicationSkillsArticleService;
	//接收当前页数和每页显示的记录数
	private Integer page;
	private Integer rows;
	private String json;
	
	@Override
	public CommunicationSkillsArticle getModel() {
		return communicationSkillsArticle;
	}
	public CommunicationSkillsArticle getCommunicationSkillsArticle() {
		return communicationSkillsArticle;
	}
	@Resource(name="communicationSkillsArticle")
	public void setCommunicationSkillsArticle(CommunicationSkillsArticle communicationSkillsArticle) {
		this.communicationSkillsArticle = communicationSkillsArticle;
	}
	public CommunicationSkillsArticleService getCommunicationSkillsArticleService() {
		return communicationSkillsArticleService;
	}
	@Resource(name="communicationSkillsArticleService")
	public void setCommunicationSkillsArticleService(CommunicationSkillsArticleService communicationSkillsArticleService) {
		this.communicationSkillsArticleService = communicationSkillsArticleService;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	public String findCommunicationSkillsArticlePaging() throws Exception {
		
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CommunicationSkillsArticle.class,"sub");
		//取显示的文章
		detachedCriteria.add(Restrictions.eq("sub.isDisplay", true));
		//调用业务层
		CommunicationSkillsArticlePage communicationSkillsArticlePage = communicationSkillsArticleService.findByPage(detachedCriteria,page,rows);
    	//转换json 
    	json = new Gson().toJson(communicationSkillsArticlePage);
    	
		return SUCCESS;
	}
	
	public String addClicks() throws Exception {
		
    	String communicationSkillsArticleID = communicationSkillsArticle.getCommunicationSkillsArticleID();
    	//根据交往技巧id找文章并加一个赞
    	communicationSkillsArticle = communicationSkillsArticleService.updateAddClicks(communicationSkillsArticleID);
    	//将点赞放入map
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("like", communicationSkillsArticle.getClicks());
    	//转换json 
    	json = new Gson().toJson(map);
		
		return SUCCESS;
	}
	
	public String findCommentByArticlePaging() throws Exception {
		
    	String communicationSkillsArticleID = communicationSkillsArticle.getCommunicationSkillsArticleID();
    	//通过这篇文章id找评论
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CommunicationSkillsComment.class,"sub");
		detachedCriteria.createCriteria("communicationSkillsArticle","csa");
		detachedCriteria.createCriteria("user","u");
		detachedCriteria.add(Restrictions.eq("csa.communicationSkillsArticleID", communicationSkillsArticleID));
		CommunicationSkillsCommentPage communicationSkillsCommentPage = communicationSkillsArticleService.findCommentByArticlePaging(detachedCriteria,page,rows);
		json = new Gson().toJson(communicationSkillsCommentPage);
		
		return SUCCESS;
	}
}

