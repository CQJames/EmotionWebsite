package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import com.ew.dao.ReplyDao;
import com.ew.dao.UtteranceDao;
import com.ew.entity.Reply;
import com.ew.entity.ReplyPage;
import com.ew.entity.Utterance;
import com.ew.entity.UtterancePage;
import com.ew.service.UtteranceService;

@Service("utteranceService")
public class UtteranceServiceImpl implements UtteranceService {

	private UtteranceDao utteranceDao;
	private UtterancePage utterancePage;
	private ReplyPage replyPage;
	private ReplyDao replyDao;
	
	public UtteranceDao getUtteranceDao() {
		return utteranceDao;
	}
	
	@Resource(name="utteranceDao")
	public void setUtteranceDao(UtteranceDao utteranceDao) {
		this.utteranceDao = utteranceDao;
	}
	
	public UtterancePage getUtterancePage() {
		return utterancePage;
	}
	
	@Resource(name="utterancePage")
	public void setUtterancePage(UtterancePage utterancePage) {
		this.utterancePage = utterancePage;
	}
	public ReplyPage getReplyPage() {
		return replyPage;
	}
	@Resource(name="replyPage")
	public void setReplyPage(ReplyPage replyPage) {
		this.replyPage = replyPage;
	}
	public ReplyDao getReplyDao() {
		return replyDao;
	}
	@Resource(name="replyDao")
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Override
	public UtterancePage findByPageBackstage(DetachedCriteria detachedCriteria,Integer page, Integer rows) {
		//设置当前页数
		utterancePage.setCurrPage(page);
		//设置每页显示记录数
		utterancePage.setPageSize(rows);
		//查询总的记录数
		Integer totalCount = utteranceDao.findCount(detachedCriteria);
		//设置总记录数
		utterancePage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		utterancePage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<Utterance> list = utteranceDao.findByPageBackstage(detachedCriteria,begin,rows);
		utterancePage.setList(list);
		return utterancePage;
	}

	@Override
	public List<Utterance> findByIdExcludeReply(DetachedCriteria detachedCriteria) {
		return utteranceDao.findByIdExcludeReply(detachedCriteria);
	}

	@Override
	public void save(Utterance utterance) {
		//初始化访问人数
		utterance.setVisitors(0);
		//设置可以显示
		utterance.setIsDisplay(true);
		//设置不精选
		utterance.setIsSelected(false);
		utteranceDao.save(utterance);
	}

	@Override
	public Utterance findById(String utteranceID) {
		return utteranceDao.findById(utteranceID);
	}

	@Override
	public void delete(Utterance utterance) {
		utteranceDao.delete(utterance.getUtteranceID());			
		
	}

	@Override
	public void update(Utterance utterance) {
		utteranceDao.update(utterance);
		
	}

	@Override
	public UtterancePage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		utterancePage.setCurrPage(page);
		//设置每页显示记录数
		utterancePage.setPageSize(rows);
		//查询总的记录数
		Integer totalCount = utteranceDao.findCount(detachedCriteria);
		//设置总记录数
		utterancePage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		utterancePage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<Utterance> list = utteranceDao.findByPage(detachedCriteria,begin,rows);
		utterancePage.setList(list);
		return utterancePage;
	}

	@Override
	public List<Utterance> findTenBySelected(DetachedCriteria detachedCriteria1) {
		List<Utterance> list = utteranceDao.findTenBySelected(detachedCriteria1);
		return list;
	}

	@Override
	public List<Utterance> findTenByPopular(DetachedCriteria detachedCriteria2) {
		List<Utterance> list = utteranceDao.findTenByPopular(detachedCriteria2);
		return list;
	}

	@Override
	public ReplyPage findReplyByUtterancePaging(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		replyPage.setCurrPage(page);
		//设置每页显示记录数
		replyPage.setPageSize(rows);
		//查询总的记录数
		Integer totalCount = replyDao.findCount(detachedCriteria);
		//设置总记录数
		replyPage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		replyPage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<Reply> list = replyDao.findByUtterancePaging(detachedCriteria,begin,rows);
		replyPage.setList(list);				
		return replyPage;
	}

	@Override
	public Utterance updateAndFindById(String utteranceID) {
		Utterance utteranceTemp = utteranceDao.findById(utteranceID);
		//游览人数+1
		utteranceTemp.setVisitors(utteranceTemp.getVisitors() + 1);
		//更新数据库
		utteranceDao.update(utteranceTemp);
		return utteranceTemp;
	}
}
