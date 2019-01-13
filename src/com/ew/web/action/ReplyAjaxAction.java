package com.ew.web.action;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.CommunicationSkillsComment;
import com.ew.entity.Reply;
import com.ew.entity.User;
import com.ew.entity.Utterance;
import com.ew.service.ReplyService;
import com.ew.service.UtteranceService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("replyAjaxAction")
@Scope("prototype")
public class ReplyAjaxAction extends ActionSupport implements ModelDriven<Reply> {
	
	/**
	 * 回复AjaxAction
	 */
	private static final long serialVersionUID = 8470644217716282081L;
	
	private Reply reply;
	private ReplyService replyService;
	//存储将click转为json的字符串
	private String json;
	private String utteranceID;
	private Utterance utterance;
	private UtteranceService utteranceService;
	
	@Override
	public Reply getModel() {
		return reply;
	}
	public Reply getReply() {
		return reply;
	}
	@Resource(name="reply")
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	public ReplyService getReplyService() {
		return replyService;
	}
	@Resource(name="replyService")
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
    public String getUtteranceID() {
		return utteranceID;
	}
	public void setUtteranceID(String utteranceID) {
		this.utteranceID = utteranceID;
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
	public String publishReply() throws Exception {
		
    	//获得当前用户ID
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	User user = (User) session.get("user");
    	reply.setUser(user);
    	//设置ID获得当前评论文章
    	utterance.setUtteranceID(utteranceID);
    	reply.setUtterance(utterance);
    	//保存到数据库
    	replyService.save(reply);
  
		return SUCCESS;
	}
	
}



