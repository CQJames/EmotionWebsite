package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.ew.dao.CommunicationSkillsArticleDao;
import com.ew.dao.CommunicationSkillsCommentDao;
import com.ew.entity.CommunicationSkillsArticle;
import com.ew.entity.CommunicationSkillsArticlePage;
import com.ew.entity.CommunicationSkillsComment;
import com.ew.entity.CommunicationSkillsCommentPage;
import com.ew.service.CommunicationSkillsArticleService;

@Service("communicationSkillsArticleService")
public class CommunicationSkillsArticleServiceImpl implements CommunicationSkillsArticleService {

	private CommunicationSkillsArticleDao communicationSkillsArticleDao;
	private CommunicationSkillsArticlePage communicationSkillsArticlePage;
	private CommunicationSkillsCommentPage communicationSkillsCommentPage;
	private CommunicationSkillsCommentDao communicationSkillsCommentDao;

	public CommunicationSkillsArticleDao getCommunicationSkillsArticleDao() {
		return communicationSkillsArticleDao;
	}
	@Resource(name="communicationSkillsArticleDao")
	public void setCommunicationSkillsArticleDao(CommunicationSkillsArticleDao communicationSkillsArticleDao) {
		this.communicationSkillsArticleDao = communicationSkillsArticleDao;
	}
	public CommunicationSkillsArticlePage getCommunicationSkillsArticlePage() {
		return communicationSkillsArticlePage;
	}
	@Resource(name="communicationSkillsArticlePage")
	public void setCommunicationSkillsArticlePage(CommunicationSkillsArticlePage communicationSkillsArticlePage) {
		this.communicationSkillsArticlePage = communicationSkillsArticlePage;
	}
	public CommunicationSkillsCommentPage getCommunicationSkillsCommentPage() {
		return communicationSkillsCommentPage;
	}
	@Resource(name="communicationSkillsCommentPage")
	public void setCommunicationSkillsCommentPage(CommunicationSkillsCommentPage communicationSkillsCommentPage) {
		this.communicationSkillsCommentPage = communicationSkillsCommentPage;
	}
	public CommunicationSkillsCommentDao getCommunicationSkillsCommentDao() {
		return communicationSkillsCommentDao;
	}
	@Resource(name="communicationSkillsCommentDao")
	public void setCommunicationSkillsCommentDao(CommunicationSkillsCommentDao communicationSkillsCommentDao) {
		this.communicationSkillsCommentDao = communicationSkillsCommentDao;
	}
	
	@Override
	public CommunicationSkillsArticlePage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		communicationSkillsArticlePage.setCurrPage(page);
		//设置每页显示记录数
		communicationSkillsArticlePage.setPageSize(rows);
		//查询总的记录数
		Integer totalCount = communicationSkillsArticleDao.findCount(detachedCriteria);
		//设置总记录数
		communicationSkillsArticlePage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		communicationSkillsArticlePage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<CommunicationSkillsArticle> list = communicationSkillsArticleDao.findByPage(detachedCriteria,begin,rows);
		communicationSkillsArticlePage.setList(list);
		return communicationSkillsArticlePage;
	}
	@Override
	public List<CommunicationSkillsArticle> findTenByCulling(DetachedCriteria detachedCriteria1) {
		List<CommunicationSkillsArticle> list = communicationSkillsArticleDao.findTenByCulling(detachedCriteria1);
		return list;
	}
	@Override
	public List<CommunicationSkillsArticle> findTenByNewest(DetachedCriteria detachedCriteria2) {
		List<CommunicationSkillsArticle> list = communicationSkillsArticleDao.findTenByNewest(detachedCriteria2);
		return list;
	}
	@Override
	public List<CommunicationSkillsArticle> findTenByPopular(DetachedCriteria detachedCriteria3) {
		List<CommunicationSkillsArticle> list = communicationSkillsArticleDao.findTenByPopular(detachedCriteria3);
		return list;	
	}
	
	
	@Override
	public List<CommunicationSkillsArticle> findAllArticle() {
		
		List<CommunicationSkillsArticle> communicationSkillsArticleList = communicationSkillsArticleDao.findAllArticle();
		
		return communicationSkillsArticleList;
	}
	
	@Override
	public CommunicationSkillsArticle findArticleById(String communicationSkillsArticleID) {
		
		CommunicationSkillsArticle communicationSkillsArticleTemp = communicationSkillsArticleDao.findArticleById(communicationSkillsArticleID);
		
		return communicationSkillsArticleTemp;
	}	

	@Override
	public void save(CommunicationSkillsArticle communicationSkillsArticle) {
		communicationSkillsArticleDao.save(communicationSkillsArticle);
		
	}

	@Override
	public void delete(CommunicationSkillsArticle communicationSkillsArticle) {
		communicationSkillsArticleDao.delete(communicationSkillsArticle.getCommunicationSkillsArticleID());			
	}

	@Override
	public CommunicationSkillsArticle findById(String communicationSkillsArticleID) {
		return communicationSkillsArticleDao.findById(communicationSkillsArticleID);
	}

	@Override
	public void update(CommunicationSkillsArticle communicationSkillsArticle) {
		communicationSkillsArticleDao.update(communicationSkillsArticle);
		
	}

	@Override
	public List<CommunicationSkillsArticle> findByIdExcludeComment(DetachedCriteria detachedCriteria) {
		return communicationSkillsArticleDao.findByIdExcludeComment(detachedCriteria);
	}

	@Override
	public List<String> findIdByTitle(String title) {
		List<String> list = communicationSkillsArticleDao.findIdByTitle(title);
		
		return list;
	}
	
	@Override
	public CommunicationSkillsArticlePage findByPageBackstage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		communicationSkillsArticlePage.setCurrPage(page);
		//设置每页显示记录数
		communicationSkillsArticlePage.setPageSize(rows);
		//查询总的记录数
		Integer totalCount = communicationSkillsArticleDao.findCount(detachedCriteria);
		//设置总记录数
		communicationSkillsArticlePage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		communicationSkillsArticlePage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<CommunicationSkillsArticle> list = communicationSkillsArticleDao.findByPageBackstage(detachedCriteria,begin,rows);
		communicationSkillsArticlePage.setList(list);
		return communicationSkillsArticlePage;
	}
	@Override
	public CommunicationSkillsArticle updateAddClicks(String communicationSkillsArticleID) {
		CommunicationSkillsArticle communicationSkillsArticleTemp = communicationSkillsArticleDao.findById(communicationSkillsArticleID);
		//加一个赞
		communicationSkillsArticleTemp.setClicks(communicationSkillsArticleTemp.getClicks() + 1);
		//更新数据库
		communicationSkillsArticleDao.update(communicationSkillsArticleTemp);
		
		return communicationSkillsArticleTemp;
	}
	
	@Override
	public CommunicationSkillsCommentPage findCommentByArticlePaging(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		communicationSkillsCommentPage.setCurrPage(page);
		//设置每页显示记录数
		communicationSkillsCommentPage.setPageSize(rows);
		//查询总的记录数
		Integer totalCount = communicationSkillsCommentDao.findCount(detachedCriteria);
		//设置总记录数
		communicationSkillsCommentPage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		communicationSkillsCommentPage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<CommunicationSkillsComment> list = communicationSkillsCommentDao.findByArticlePaging(detachedCriteria,begin,rows);
		communicationSkillsCommentPage.setList(list);				
		return communicationSkillsCommentPage;
	}
	
	@Override
	public CommunicationSkillsArticle updateAndFindById(String communicationSkillsArticleID) {
		CommunicationSkillsArticle communicationSkillsArticleTemp = communicationSkillsArticleDao.findById(communicationSkillsArticleID);
		//更新浏览人数+1
		communicationSkillsArticleTemp.setVisitors((communicationSkillsArticleTemp.getVisitors() + 1));
		communicationSkillsArticleDao.update(communicationSkillsArticleTemp);
		return communicationSkillsArticleTemp;
	}

}
