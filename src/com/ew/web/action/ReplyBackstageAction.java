package com.ew.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Reply;
import com.ew.entity.ReplyPage;
import com.ew.service.ReplyService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("replyBackstageAction")
@Scope("prototype")
public class ReplyBackstageAction extends ActionSupport implements ModelDriven<Reply> {

	/**
	 * 回复后台action
	 */
	private static final long serialVersionUID = 6380726262862457119L;

	private Reply reply;
	private ReplyService replyService;
	private Integer page = 1;
	private Integer rows = 3;
	private String searchName;
	private String utteranceID;
	
	
	@Resource(name="reply")
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
	@Resource(name="replyService")
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public void setUtteranceID(String utteranceID) {
		this.utteranceID = utteranceID;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	
	@Override
	public Reply getModel() {
		return reply;
	}
	/**
	 * 分页查询的方法:findAllPage
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findAllPage() throws IOException {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Reply.class);
		detachedCriteria.createCriteria("utterance");
		detachedCriteria.createCriteria("user");
		
		if(searchName != null){
			detachedCriteria.add(Restrictions.like("message","%"+searchName+"%"));
		}
		ReplyPage replyPage = replyService.findAllByPage(detachedCriteria, page, rows);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", replyPage.getTotalCount());
		map.put("rows", replyPage.getList());

		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"communicationSkillsComment","selfEnhancementComment","reply","collection"});
		JSONObject json = JSONObject.fromObject(map,jsonConfig);
	//	String json = new Gson().toJson(map);
		System.out.println(json);
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String delete() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			reply = replyService.findById(reply.getReplyID());
			replyService.delete(reply);
			map.put("msg", "用户信息删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "用户信息删除异常!");
		}	
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}	
	
	public String findById() throws IOException {
		reply = replyService.findById(reply.getReplyID());
		String json = new Gson().toJson(reply);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String findComment() throws IOException{
		System.out.println(utteranceID);
		
		if(utteranceID != null){
		
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Reply.class);
			detachedCriteria.createCriteria("utterance");
			detachedCriteria.createCriteria("user");
			detachedCriteria.add(Restrictions.like("utterance.utteranceID","%"+utteranceID+"%"));
	
			ReplyPage replyPage = replyService.findAllByPage(detachedCriteria, page, rows);
	
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", replyPage.getTotalCount());
			map.put("rows", replyPage.getList());
	
			
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[]{"communicationSkillsComment","selfEnhancementComment","reply"});
			JSONObject json = JSONObject.fromObject(map,jsonConfig);
		//	String json = new Gson().toJson(map);
			System.out.println(json);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().println(json);
		}
		return NONE;
		
	}
	
}
