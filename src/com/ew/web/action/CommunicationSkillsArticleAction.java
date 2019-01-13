package com.ew.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.CommunicationSkillsArticle;
import com.ew.entity.CommunicationSkillsArticlePage;
import com.ew.entity.CommunicationSkillsComment;
import com.ew.entity.CommunicationSkillsCommentPage;
import com.ew.service.CommunicationSkillsArticleService;
import com.ew.utils.ListFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("communicationSkillsArticleAction")
@Scope("prototype")
public class CommunicationSkillsArticleAction extends ActionSupport implements ModelDriven<CommunicationSkillsArticle>{

	
	/**
	 * 交往技巧Action
	 */
	private static final long serialVersionUID = -4876677557734672653L;
	
	private CommunicationSkillsArticle communicationSkillsArticle;
	private CommunicationSkillsArticleService communicationSkillsArticleService;
	//接收当前页数和每页显示的记录数
	private Integer page;
	private Integer rows;
	
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
	
	public String findCommunicationSkillsArticlePaging() throws Exception {
		
		//加载分页内容
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CommunicationSkillsArticle.class,"sub");
		//取显示的文章
		detachedCriteria.add(Restrictions.eq("sub.isDisplay", true));
		//调用业务层
		CommunicationSkillsArticlePage communicationSkillsArticlePage = communicationSkillsArticleService.findByPage(detachedCriteria,page,rows);
		//放入request中
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("communicationSkillsArticlePage", communicationSkillsArticlePage);
		
		DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(CommunicationSkillsArticle.class,"sub");
    	//加载侧面内容,加载精选文章10条,并拆分成两个5条
		List<CommunicationSkillsArticle> communicationSkillsArticleCList = communicationSkillsArticleService.findTenByCulling(detachedCriteria1);
		List<List<CommunicationSkillsArticle>> averageAssignList = ListFactory.averageAssign(communicationSkillsArticleCList, 2);
		List<CommunicationSkillsArticle> communicationSkillsArticleCList1 = averageAssignList.get(0);
		List<CommunicationSkillsArticle> communicationSkillsArticleCList2 = averageAssignList.get(1);
		request.put("communicationSkillsArticleCList1", communicationSkillsArticleCList1);
		request.put("communicationSkillsArticleCList2", communicationSkillsArticleCList2);
		
		DetachedCriteria detachedCriteria2 = DetachedCriteria.forClass(CommunicationSkillsArticle.class,"sub");
		//加载侧面内容,加载最新文章10条,并拆分成两个5条
		List<CommunicationSkillsArticle> communicationSkillsArticleNList = communicationSkillsArticleService.findTenByNewest(detachedCriteria2);
		List<List<CommunicationSkillsArticle>> averageAssignList1 = ListFactory.averageAssign(communicationSkillsArticleNList, 2);
		List<CommunicationSkillsArticle> communicationSkillsArticleNList1 = averageAssignList1.get(0);
		List<CommunicationSkillsArticle> communicationSkillsArticleNList2 = averageAssignList1.get(1);
		request.put("communicationSkillsArticleNList1", communicationSkillsArticleNList1);
		request.put("communicationSkillsArticleNList2", communicationSkillsArticleNList2);
		
		DetachedCriteria detachedCriteria3 = DetachedCriteria.forClass(CommunicationSkillsArticle.class,"sub");
		//加载侧面内容,加载热门文章10条,并拆分成两个5条
		List<CommunicationSkillsArticle> communicationSkillsArticlePList = communicationSkillsArticleService.findTenByPopular(detachedCriteria3);
		List<List<CommunicationSkillsArticle>> averageAssignList2 = ListFactory.averageAssign(communicationSkillsArticlePList, 2);
		List<CommunicationSkillsArticle> communicationSkillsArticlePList1 = averageAssignList2.get(0);
		List<CommunicationSkillsArticle> communicationSkillsArticlePList2 = averageAssignList2.get(1);
		request.put("communicationSkillsArticlePList1", communicationSkillsArticlePList1);
		request.put("communicationSkillsArticlePList2", communicationSkillsArticlePList2);
		
		return "toForegroundCommunicationSkills";
	}
	
	public String findById() throws Exception {
		
		String communicationSkillsArticleID = communicationSkillsArticle.getCommunicationSkillsArticleID();
		//根据交往技巧id找文章
		communicationSkillsArticle = communicationSkillsArticleService.updateAndFindById(communicationSkillsArticleID);
    	//通过这篇文章id找评论
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CommunicationSkillsComment.class,"sub");
		detachedCriteria.createCriteria("communicationSkillsArticle","csa");
		detachedCriteria.createCriteria("user","u");
		detachedCriteria.add(Restrictions.eq("csa.communicationSkillsArticleID", communicationSkillsArticleID));
		CommunicationSkillsCommentPage communicationSkillsCommentPage = communicationSkillsArticleService.findCommentByArticlePaging(detachedCriteria,page,rows);
    	Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("communicationSkillsCommentPage", communicationSkillsCommentPage);
		
		return "toForegroundShowCSArticleComment";
	}
}

