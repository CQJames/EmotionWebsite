package com.ew.web.action;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Reply;
import com.ew.entity.ReplyPage;
import com.ew.entity.User;
import com.ew.entity.Utterance;
import com.ew.entity.UtterancePage;
import com.ew.service.UtteranceService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("utteranceAjaxAction")
@Scope("prototype")
public class UtteranceAjaxAction extends ActionSupport implements ModelDriven<Utterance>{

	/**
	 * 话语AjaxAction
	 */
	private static final long serialVersionUID = -3855552319656159282L;
	
	private Utterance utterance;
	private UtteranceService utteranceService;
	//接收当前页数和每页显示的记录数
	private Integer page;
	private Integer rows;
	private String json;
	
	@Override
	public Utterance getModel() {
		return utterance;
	}
	public Utterance getUtterance() {
		return utterance;
	}
	@Resource(name="utterance")
	public void setUtterance(Utterance utterance) {
		this.utterance = utterance;
	}
	public UtteranceService getUtteranceService() {
		return utteranceService;
	}
	@Resource(name="utteranceService")
	public void setUtteranceService(UtteranceService utteranceService) {
		this.utteranceService = utteranceService;
	}
	public Integer getPage() {
		return page;
	}
	@Value("1")
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	@Value("6")
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	public String findUtterancePaging() throws Exception {
		
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Utterance.class,"sub");
		//取显示的文章
		detachedCriteria.add(Restrictions.eq("sub.isDisplay", true));
		//调用业务层
		UtterancePage utterancePage = utteranceService.findByPage(detachedCriteria,page,rows);
    	//转换json 
    	json = new Gson().toJson(utterancePage);
    	
		return SUCCESS;
	}
	
	public String publishUtterance() throws Exception {
		
    	//获得当前用户ID
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User) session.get("user");
    	utterance.setPublisher(user.getUsername());
    	//保存到数据库
    	utteranceService.save(utterance);
		return SUCCESS;
	}
	
	public String findReplyByUtterancePaging() throws Exception {
		
    	String utteranceID = utterance.getUtteranceID();
    	//通过这篇文章id找评论
    	//根据话语找回复，并分页
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Reply.class,"sub");
		detachedCriteria.createCriteria("utterance","ue");
		detachedCriteria.createCriteria("user","u");
		detachedCriteria.add(Restrictions.eq("ue.utteranceID", utteranceID));
		ReplyPage replyPage = utteranceService.findReplyByUtterancePaging(detachedCriteria,page,rows);
		json = new Gson().toJson(replyPage);
		
		return SUCCESS;
	}
}