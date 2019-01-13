package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import com.ew.dao.SelfEnhancementArticleDao;
import com.ew.dao.SelfEnhancementCommentDao;
import com.ew.entity.SelfEnhancementArticle;
import com.ew.entity.SelfEnhancementArticlePage;
import com.ew.entity.SelfEnhancementComment;
import com.ew.entity.SelfEnhancementCommentPage;
import com.ew.service.SelfEnhancementArticleService;

@Service("selfEnhancementArticleService")
public class SelfEnhancementArticleServiceImpl implements SelfEnhancementArticleService {

	private SelfEnhancementArticleDao selfEnhancementArticleDao;
	private SelfEnhancementArticlePage selfEnhancementArticlePage;
	private SelfEnhancementCommentPage selfEnhancementCommentPage;
	private SelfEnhancementCommentDao selfEnhancementCommentDao;

	public SelfEnhancementArticleDao getSelfEnhancementArticleDao() {
		return selfEnhancementArticleDao;
	}
	@Resource(name="selfEnhancementArticleDao")
	public void setSelfEnhancementArticleDao(SelfEnhancementArticleDao selfEnhancementArticleDao) {
		this.selfEnhancementArticleDao = selfEnhancementArticleDao;
	}
	public SelfEnhancementArticlePage getSelfEnhancementArticlePage() {
		return selfEnhancementArticlePage;
	}
	@Resource(name="selfEnhancementArticlePage")
	public void setSelfEnhancementArticlePage(SelfEnhancementArticlePage selfEnhancementArticlePage) {
		this.selfEnhancementArticlePage = selfEnhancementArticlePage;
	}
	public SelfEnhancementCommentPage getSelfEnhancementCommentPage() {
		return selfEnhancementCommentPage;
	}
	@Resource(name="selfEnhancementCommentPage")
	public void setSelfEnhancementCommentPage(SelfEnhancementCommentPage selfEnhancementCommentPage) {
		this.selfEnhancementCommentPage = selfEnhancementCommentPage;
	}
	public SelfEnhancementCommentDao getSelfEnhancementCommentDao() {
		return selfEnhancementCommentDao;
	}
	@Resource(name="selfEnhancementCommentDao")
	public void setSelfEnhancementCommentDao(SelfEnhancementCommentDao selfEnhancementCommentDao) {
		this.selfEnhancementCommentDao = selfEnhancementCommentDao;
	}
	
	@Override
	public SelfEnhancementArticlePage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		selfEnhancementArticlePage.setCurrPage(page);
		//设置每页显示记录数
		selfEnhancementArticlePage.setPageSize(rows);
		//查询总的记录数
		Integer totalCount = selfEnhancementArticleDao.findCount(detachedCriteria);
		//设置总记录数
		selfEnhancementArticlePage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		selfEnhancementArticlePage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<SelfEnhancementArticle> list = selfEnhancementArticleDao.findByPage(detachedCriteria,begin,rows);
		selfEnhancementArticlePage.setList(list);
		return selfEnhancementArticlePage;
	}
	@Override
	public List<SelfEnhancementArticle> findTenByKnowYourself(DetachedCriteria detachedCriteria1) {
		List<SelfEnhancementArticle> list = selfEnhancementArticleDao.findTenByKnowYourself(detachedCriteria1);
		return list;
	}
	@Override
	public List<SelfEnhancementArticle> findTenBySelfConstruction(DetachedCriteria detachedCriteria2) {
		List<SelfEnhancementArticle> list = selfEnhancementArticleDao.findTenBySelfConstruction(detachedCriteria2);
		return list;
	}
	@Override
	public SelfEnhancementArticle findById(String selfEnhancementArticleID) {
		SelfEnhancementArticle selfEnhancementArticle = selfEnhancementArticleDao.findById(selfEnhancementArticleID);
		
		return selfEnhancementArticle;
	}
	
	
	@Override
	public void save(SelfEnhancementArticle selfEnhancementArticle) {
		selfEnhancementArticleDao.save(selfEnhancementArticle);
		
	}

	@Override
	public void delete(SelfEnhancementArticle selfEnhancementArticle) {
		selfEnhancementArticleDao.delete(selfEnhancementArticle.getSelfEnhancementArticleID());			
	}

	@Override
	public void update(SelfEnhancementArticle selfEnhancementArticle) {
		selfEnhancementArticleDao.update(selfEnhancementArticle);
		
	}

	@Override
	public List<SelfEnhancementArticle> findByIdExcludeComment(DetachedCriteria detachedCriteria) {
		return selfEnhancementArticleDao.findByIdExcludeComment(detachedCriteria);
	}
	@Override
	public List<SelfEnhancementArticle> findAllArticle() {
		
		List<SelfEnhancementArticle> selfEnhancementArticleList = selfEnhancementArticleDao.findAllArticle();
		
		return selfEnhancementArticleList;
	}
	
	@Override
	public SelfEnhancementArticle findArticleById(String selfEnhancementArticleID) {
		
		SelfEnhancementArticle selfEnhancementArticleTemp = selfEnhancementArticleDao.findArticleById(selfEnhancementArticleID);
		
		return selfEnhancementArticleTemp;
	}
	@Override
	public SelfEnhancementArticle updateAddClicks(String selfEnhancementArticleID) {
		
		SelfEnhancementArticle selfEnhancementArticleTemp = selfEnhancementArticleDao.findById(selfEnhancementArticleID);
		//加一个赞
		selfEnhancementArticleTemp.setClicks(selfEnhancementArticleTemp.getClicks() + 1);
		//更新数据库
		selfEnhancementArticleDao.update(selfEnhancementArticleTemp);
			
		return selfEnhancementArticleTemp;
	}
	
	public SelfEnhancementArticlePage findByPageBackstage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		selfEnhancementArticlePage.setCurrPage(page);
		//设置每页显示记录数
		selfEnhancementArticlePage.setPageSize(rows);
		//查询总的记录数
		Integer totalCount = selfEnhancementArticleDao.findCount(detachedCriteria);
		//设置总记录数
		selfEnhancementArticlePage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		selfEnhancementArticlePage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<SelfEnhancementArticle> list = selfEnhancementArticleDao.findByPageBackstage(detachedCriteria,begin,rows);
		selfEnhancementArticlePage.setList(list);
		return selfEnhancementArticlePage;
	}
	
	@Override
	public SelfEnhancementCommentPage findCommentByArticlePaging(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		//设置当前页数
		selfEnhancementCommentPage.setCurrPage(page);
		//设置每页显示记录数
		selfEnhancementCommentPage.setPageSize(rows);
		//查询总的记录数
		Integer totalCount = selfEnhancementCommentDao.findCount(detachedCriteria);
		//设置总记录数
		selfEnhancementCommentPage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		selfEnhancementCommentPage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<SelfEnhancementComment> list = selfEnhancementCommentDao.findByArticlePaging(detachedCriteria,begin,rows);
		selfEnhancementCommentPage.setList(list);
		return selfEnhancementCommentPage;
	}
	
}
