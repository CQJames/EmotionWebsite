package com.ew.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Reply;
import com.ew.entity.ReplyPage;
import com.ew.entity.Utterance;
import com.ew.entity.UtterancePage;
import com.ew.service.UtteranceService;
import com.ew.utils.ListFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("utteranceAction")
@Scope("prototype")
public class UtteranceAction extends ActionSupport implements ModelDriven<Utterance>{

	/**
	 * 话语Action
	 */
	private static final long serialVersionUID = -5387407492379442625L;
	
	private Utterance utterance;
	private UtteranceService utteranceService;
	//接收当前页数和每页显示的记录数
	private Integer page;
	private Integer rows;
	
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
	
	public String findUtterancePaging() throws Exception {
		
		//加载分页内容
		//创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Utterance.class,"sub");
		//取显示的文章
		detachedCriteria.add(Restrictions.eq("sub.isDisplay", true));
		//调用业务层
		UtterancePage utterancePage = utteranceService.findByPage(detachedCriteria,page,rows);
		//放入request中
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("utterancePage", utterancePage);
		
		DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(Utterance.class,"sub");
    	//加载侧面内容,加载精选话语10条,并拆分成两个5条
		List<Utterance> utteranceSList = utteranceService.findTenBySelected(detachedCriteria1);
		List<List<Utterance>> averageAssignList = ListFactory.averageAssign(utteranceSList, 2);
		List<Utterance> utteranceSList1 = averageAssignList.get(0);
		List<Utterance> utteranceSList2 = averageAssignList.get(1);
		request.put("utteranceSList1", utteranceSList1);
		request.put("utteranceSList2", utteranceSList2);
		
		DetachedCriteria detachedCriteria2 = DetachedCriteria.forClass(Utterance.class,"sub");
		//加载侧面内容,加载热门话语10条,并拆分成两个5条
		List<Utterance> utterancePList = utteranceService.findTenByPopular(detachedCriteria2);
		List<List<Utterance>> averageAssignList2 = ListFactory.averageAssign(utterancePList, 2);
		List<Utterance> utterancePList1 = averageAssignList2.get(0);
		List<Utterance> utterancePList2 = averageAssignList2.get(1);
		request.put("utterancePList1", utterancePList1);
		request.put("utterancePList2", utterancePList2);
		
		return "toForegroundAnonymousTreeHole";
	}
	
	public String findById() throws Exception {
		
		String utteranceID = utterance.getUtteranceID();
		//根据话语id找话语
		utterance = utteranceService.updateAndFindById(utteranceID);
		//根据话语找回复，并分页
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Reply.class,"sub");
		detachedCriteria.createCriteria("utterance","ue");
		detachedCriteria.createCriteria("user","u");
		detachedCriteria.add(Restrictions.eq("ue.utteranceID", utteranceID));
		
		ReplyPage replyPage = utteranceService.findReplyByUtterancePaging(detachedCriteria,page,rows);
    	Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("replyPage", replyPage);
		
		return "toForegroundShowUtteranceReply";
	}
}

