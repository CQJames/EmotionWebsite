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

import com.ew.entity.SelfEnhancementComment;
import com.ew.entity.SelfEnhancementCommentPage;
import com.ew.service.SelfEnhancementCommentService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("selfEnhancementCommentBackstageAction")
@Scope("prototype")
public class SelfEnhancementCommentBackstageAction extends ActionSupport implements ModelDriven<SelfEnhancementComment> {

	/**
	 * 评论后台action
	 */
	private static final long serialVersionUID = 6380726262862457119L;

	private SelfEnhancementComment selfEnhancementComment;
	private SelfEnhancementCommentService selfEnhancementCommentService;
	private Integer page = 1;
	private Integer rows = 3;
	private String searchName;
	private String selfEnhancementArticleID;
	
	
	
	public void setSelfEnhancementArticleID(String selfEnhancementArticleID) {
		this.selfEnhancementArticleID = selfEnhancementArticleID;
	}

	@Resource(name="selfEnhancementComment")
	public void setSelfEnhancementComment(SelfEnhancementComment selfEnhancementComment) {
		this.selfEnhancementComment = selfEnhancementComment;
	}

	@Resource(name="selfEnhancementCommentService")
	public void setSelfEnhancementCommentService(SelfEnhancementCommentService selfEnhancementCommentService) {
		this.selfEnhancementCommentService = selfEnhancementCommentService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	
	@Override
	public SelfEnhancementComment getModel() {
		return selfEnhancementComment;
	}
	/**
	 * 分页查询的方法:findAllPage
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findAllPage() throws IOException {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SelfEnhancementComment.class);
		detachedCriteria.createCriteria("selfEnhancementArticle");
		detachedCriteria.createCriteria("user");
		if(searchName != null){
			detachedCriteria.add(Restrictions.like("message","%"+searchName+"%"));
		}
		//detachedCriteria.add(Restrictions.eq("user.userID", "xx"));
		SelfEnhancementCommentPage selfEnhancementCommentPage = selfEnhancementCommentService.findAllByPage(detachedCriteria, page, rows);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", selfEnhancementCommentPage.getTotalCount());
		map.put("rows", selfEnhancementCommentPage.getList());

		

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
		System.out.println(selfEnhancementComment.getSelfEnhancementCommentID());
		Map<String, String> map = new HashMap<String, String>();
	
		try {
			selfEnhancementComment = selfEnhancementCommentService.findById(selfEnhancementComment.getSelfEnhancementCommentID());
			selfEnhancementCommentService.delete(selfEnhancementComment);
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
	
	
	public String findComment() throws IOException{
		System.out.println(selfEnhancementArticleID);
		
		if(selfEnhancementArticleID != null){
		
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SelfEnhancementComment.class);
			detachedCriteria.createCriteria("selfEnhancementArticle");
			detachedCriteria.createCriteria("user");
			detachedCriteria.add(Restrictions.like("selfEnhancementArticle.selfEnhancementArticleID","%"+selfEnhancementArticleID+"%"));
	
			SelfEnhancementCommentPage selfEnhancementCommentPage = selfEnhancementCommentService.findAllByPage(detachedCriteria, page, rows);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", selfEnhancementCommentPage.getTotalCount());
			map.put("rows", selfEnhancementCommentPage.getList());

			

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
