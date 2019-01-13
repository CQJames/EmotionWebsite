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

import com.ew.entity.CommunicationSkillsComment;
import com.ew.entity.CommunicationSkillsCommentPage;
import com.ew.service.CommunicationSkillsCommentService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("communicationSkillsCommentBackstageAction")
@Scope("prototype")
public class CommunicationSkillsCommentBackstageAction extends ActionSupport implements ModelDriven<CommunicationSkillsComment> {

	/**
	 * 评论后台action
	 */
	private static final long serialVersionUID = 6380726262862457119L;

	private CommunicationSkillsComment communicationSkillsComment;
	private CommunicationSkillsCommentService communicationSkillsCommentService;
	private Integer page = 1;
	private Integer rows = 3;
	private String searchName;
	private String communicationSkillsArticleID;
	
	
	
	
	
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	public void setCommunicationSkillsArticleID(String communicationSkillsArticleID) {
		this.communicationSkillsArticleID = communicationSkillsArticleID;
	}


	@Resource(name="communicationSkillsComment")
	public void setCommunicationSkillsComment(CommunicationSkillsComment communicationSkillsComment) {
		this.communicationSkillsComment = communicationSkillsComment;
	}

	@Resource(name="communicationSkillsCommentService")
	public void setCommunicationSkillsCommentService(CommunicationSkillsCommentService communicationSkillsCommentService) {
		this.communicationSkillsCommentService = communicationSkillsCommentService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	
	@Override
	public CommunicationSkillsComment getModel() {
		return communicationSkillsComment;
	}
	/**
	 * 分页查询的方法:findAllPage
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findAllPage() throws IOException {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CommunicationSkillsComment.class);
		detachedCriteria.createCriteria("communicationSkillsArticle");
		detachedCriteria.createCriteria("user");
		
		if(searchName != null){
			detachedCriteria.add(Restrictions.like("message","%"+searchName+"%"));
		}
		//detachedCriteria.add(Restrictions.eq("user.userID", "xx"));
		CommunicationSkillsCommentPage communicationSkillsCommentPage = communicationSkillsCommentService.findAllByPage(detachedCriteria, page, rows);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", communicationSkillsCommentPage.getTotalCount());
		map.put("rows", communicationSkillsCommentPage.getList());

		
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
			communicationSkillsComment = communicationSkillsCommentService.findById(communicationSkillsComment.getCommunicationSkillsCommentID());
			communicationSkillsCommentService.delete(communicationSkillsComment);
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
		communicationSkillsComment = communicationSkillsCommentService.findById(communicationSkillsComment.getCommunicationSkillsCommentID());
		String json = new Gson().toJson(communicationSkillsComment);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	public String findComment() throws IOException{
		System.out.println(communicationSkillsArticleID);
		
		if(communicationSkillsArticleID != null){
		
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CommunicationSkillsComment.class);
			detachedCriteria.createCriteria("communicationSkillsArticle");
			detachedCriteria.createCriteria("user");
			detachedCriteria.add(Restrictions.like("communicationSkillsArticle.communicationSkillsArticleID","%"+communicationSkillsArticleID+"%"));
	
			CommunicationSkillsCommentPage communicationSkillsCommentPage = communicationSkillsCommentService.findAllByPage(detachedCriteria, page, rows);
	
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", communicationSkillsCommentPage.getTotalCount());
			map.put("rows", communicationSkillsCommentPage.getList());
	
			
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[]{"communicationSkillsComment","selfEnhancementComment","reply","collection"});
			JSONObject json = JSONObject.fromObject(map,jsonConfig);
		//	String json = new Gson().toJson(map);
			System.out.println(json);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().println(json);
		}
		return NONE;
		
	}
	
}
