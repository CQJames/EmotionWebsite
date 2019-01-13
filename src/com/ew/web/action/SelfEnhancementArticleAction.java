package com.ew.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.SelfEnhancementArticle;
import com.ew.entity.SelfEnhancementArticlePage;
import com.ew.entity.SelfEnhancementComment;
import com.ew.entity.SelfEnhancementCommentPage;
import com.ew.service.SelfEnhancementArticleService;
import com.ew.utils.ListFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("selfEnhancementArticleAction")
@Scope("prototype")
public class SelfEnhancementArticleAction extends ActionSupport implements ModelDriven<SelfEnhancementArticle>{

	
	/**
	 * 自我提升Action
	 */
	private static final long serialVersionUID = -5288918987705664200L;
	
	private SelfEnhancementArticle selfEnhancementArticle;
	private SelfEnhancementArticleService selfEnhancementArticleService;
	//接收当前页数和每页显示的记录数
	private Integer page;
	private Integer rows;
	
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
	@Value("1")
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	@Value("2")
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public String findSelfEnhancementArticlePaging() throws Exception {
		
		//加载分页内容
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SelfEnhancementArticle.class,"sub");
		//取显示的文章
		detachedCriteria.add(Restrictions.eq("sub.isDisplay", true));
		//调用业务层
		SelfEnhancementArticlePage selfEnhancementArticlePage = selfEnhancementArticleService.findByPage(detachedCriteria,page,rows);
		//放入request中
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("selfEnhancementArticlePage", selfEnhancementArticlePage);
		
		DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(SelfEnhancementArticle.class,"sub");
    	//加载侧面内容,加载认识自己最新10条,并拆分成两个5条
		List<SelfEnhancementArticle> selfEnhancementArticleKYList = selfEnhancementArticleService.findTenByKnowYourself(detachedCriteria1);
		List<List<SelfEnhancementArticle>> averageAssignList = ListFactory.averageAssign(selfEnhancementArticleKYList, 2);
		List<SelfEnhancementArticle> selfEnhancementArticleKYList1 = averageAssignList.get(0);
		List<SelfEnhancementArticle> selfEnhancementArticleKYList2 = averageAssignList.get(1);
		request.put("selfEnhancementArticleKYList1", selfEnhancementArticleKYList1);
		request.put("selfEnhancementArticleKYList2", selfEnhancementArticleKYList2);
		
		DetachedCriteria detachedCriteria2 = DetachedCriteria.forClass(SelfEnhancementArticle.class,"sub");
		//加载侧面内容,加载自身建设最新10条,并拆分成两个5条
		List<SelfEnhancementArticle> selfEnhancementArticleSCList = selfEnhancementArticleService.findTenBySelfConstruction(detachedCriteria2);
		List<List<SelfEnhancementArticle>> averageAssignList1 = ListFactory.averageAssign(selfEnhancementArticleSCList, 2);
		List<SelfEnhancementArticle> selfEnhancementArticleSCList1 = averageAssignList1.get(0);
		List<SelfEnhancementArticle> selfEnhancementArticleSCList2 = averageAssignList1.get(1);
		request.put("selfEnhancementArticleSCList1", selfEnhancementArticleSCList1);
		request.put("selfEnhancementArticleSCList2", selfEnhancementArticleSCList2);
		
		return "toForegroundSelfEnhancement";
	}
	
	public String findById() throws Exception {
		
    	String selfEnhancementArticleID = selfEnhancementArticle.getSelfEnhancementArticleID();
    	//根据自我提升id找文章
    	selfEnhancementArticle = selfEnhancementArticleService.findById(selfEnhancementArticleID);
    	//通过这篇文章id找评论
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SelfEnhancementComment.class,"sub");
		detachedCriteria.createCriteria("selfEnhancementArticle","sea");
		detachedCriteria.createCriteria("user","u");
		detachedCriteria.add(Restrictions.eq("sea.selfEnhancementArticleID", selfEnhancementArticleID));
		SelfEnhancementCommentPage selfEnhancementCommentPage = selfEnhancementArticleService.findCommentByArticlePaging(detachedCriteria,page,rows);
    	Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("selfEnhancementCommentPage", selfEnhancementCommentPage);
		
		return "toForegroundShowSEArticleComment";
	}
}
