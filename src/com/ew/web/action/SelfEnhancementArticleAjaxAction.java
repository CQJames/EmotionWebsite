package com.ew.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.SelfEnhancementArticle;
import com.ew.entity.SelfEnhancementArticlePage;
import com.ew.entity.SelfEnhancementComment;
import com.ew.entity.SelfEnhancementCommentPage;
import com.ew.service.SelfEnhancementArticleService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("selfEnhancementArticleAjaxAction")
@Scope("prototype")
public class SelfEnhancementArticleAjaxAction extends ActionSupport implements ModelDriven<SelfEnhancementArticle>{

	
	/**
	 * 自我提升Action
	 */
	private static final long serialVersionUID = -5288918987705664200L;
	
	private SelfEnhancementArticle selfEnhancementArticle;
	private SelfEnhancementArticleService selfEnhancementArticleService;
	//接收当前页数和每页显示的记录数
	private Integer page;
	private Integer rows;
	private String json;
	
	@Override
	public SelfEnhancementArticle getModel() {
		return selfEnhancementArticle;
	}
	public SelfEnhancementArticle getSelfEnhancementArticle() {
		return selfEnhancementArticle;
	}
	@Resource(name="selfEnhancementArticle")
	public void setSelfEnhancementArticle(SelfEnhancementArticle selfEnhancementArticle) {
		this.selfEnhancementArticle = selfEnhancementArticle;
	}
	public SelfEnhancementArticleService getSelfEnhancementArticleService() {
		return selfEnhancementArticleService;
	}
	@Resource(name="selfEnhancementArticleService")
	public void setSelfEnhancementArticleService(SelfEnhancementArticleService selfEnhancementArticleService) {
		this.selfEnhancementArticleService = selfEnhancementArticleService;
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
	
	public String findSelfEnhancementArticlePaging() throws Exception {
		
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SelfEnhancementArticle.class,"sub");
		//取显示的文章
		detachedCriteria.add(Restrictions.eq("sub.isDisplay", true));
		//调用业务层
		SelfEnhancementArticlePage selfEnhancementArticlePage = selfEnhancementArticleService.findByPage(detachedCriteria,page,rows);
    	//转换json 
    	json = new Gson().toJson(selfEnhancementArticlePage);
    	
		return SUCCESS;
	}
	
	public String addClicks() throws Exception {
		
    	String selfEnhancementArticleID = selfEnhancementArticle.getSelfEnhancementArticleID();
    	//根据交往技巧id找文章并加一个赞
    	selfEnhancementArticle = selfEnhancementArticleService.updateAddClicks(selfEnhancementArticleID);
    	//将点赞放入map
    	Map<String,Object> map = new HashMap<String,Object>();
		map.put("like", selfEnhancementArticle.getClicks());
    	//转换json 
    	json = new Gson().toJson(map);
		
		return SUCCESS;
	}
	
	public String findCommentByArticlePaging() throws Exception {
		
    	String selfEnhancementArticleID = selfEnhancementArticle.getSelfEnhancementArticleID();
    	//通过这篇文章id找评论
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SelfEnhancementComment.class,"sub");
		detachedCriteria.createCriteria("selfEnhancementArticle","sea");
		detachedCriteria.createCriteria("user","u");
		detachedCriteria.add(Restrictions.eq("sea.selfEnhancementArticleID", selfEnhancementArticleID));
		SelfEnhancementCommentPage selfEnhancementCommentPage = selfEnhancementArticleService.findCommentByArticlePaging(detachedCriteria,page,rows);
		json = new Gson().toJson(selfEnhancementCommentPage);
		
		return SUCCESS;
	}
}

