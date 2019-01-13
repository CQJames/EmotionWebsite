package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.ew.dao.SelfEnhancementCommentDao;
import com.ew.entity.SelfEnhancementComment;
import com.ew.entity.SelfEnhancementCommentPage;
import com.ew.service.SelfEnhancementCommentService;

@Service("selfEnhancementCommentService")
public class SelfEnhancementCommentServiceImpl implements SelfEnhancementCommentService {

	private SelfEnhancementCommentDao selfEnhancementCommentDao;
	private SelfEnhancementCommentPage selfEnhancementCommentPage;
	
	@Resource(name="selfEnhancementCommentDao")
	public void setSelfEnhancementCommentDao(SelfEnhancementCommentDao selfEnhancementCommentDao) {
		this.selfEnhancementCommentDao = selfEnhancementCommentDao;
	}

	@Resource(name="selfEnhancementCommentPage")
	public void setSelfEnhancementCommentPage(SelfEnhancementCommentPage selfEnhancementCommentPage) {
		this.selfEnhancementCommentPage = selfEnhancementCommentPage;
	}

	public SelfEnhancementCommentPage findAllByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		
		selfEnhancementCommentPage.setCurrPage(page);
		
		selfEnhancementCommentPage.setPageSize(rows);
		
		Integer totalCount = selfEnhancementCommentDao.findCount(detachedCriteria);
		selfEnhancementCommentPage.setTotalCount(totalCount);
		
		double tc = totalCount;
		Double num = Math.ceil(tc / rows);
		selfEnhancementCommentPage.setTotalPage(num.intValue());
		
		Integer begin = (page - 1) * rows;
		List<SelfEnhancementComment> list = selfEnhancementCommentDao.findByPageBackstage(detachedCriteria,begin,rows);
		
		selfEnhancementCommentPage.setList(list);
	
		return selfEnhancementCommentPage; 
	}


	@Override
	public void delete(SelfEnhancementComment selfEnhancementComment) {
		selfEnhancementCommentDao.delete(selfEnhancementComment.getSelfEnhancementCommentID());			
		
	}

	@Override
	public SelfEnhancementComment findById(String selfEnhancementCommentID) {
		return selfEnhancementCommentDao.findById(selfEnhancementCommentID);
	}

	@Override
	public SelfEnhancementComment findById(DetachedCriteria detachedCriteria) {
		SelfEnhancementComment selfEnhancementComment = selfEnhancementCommentDao.findById(detachedCriteria);
		return selfEnhancementComment;
	}

	@Override
	public SelfEnhancementComment updateAddApplaud(String selfEnhancementCommentID) {
		
		SelfEnhancementComment selfEnhancementCommentTemp = selfEnhancementCommentDao.findById(selfEnhancementCommentID);
		//加一个赞同
		selfEnhancementCommentTemp.setApplaud(selfEnhancementCommentTemp.getApplaud() + 1);
		//更新数据库
		selfEnhancementCommentDao.update(selfEnhancementCommentTemp);
			
		return selfEnhancementCommentTemp;
	}

	@Override
	public SelfEnhancementComment updateAddDisapproval(String selfEnhancementCommentID) {
		
		SelfEnhancementComment selfEnhancementCommentTemp = selfEnhancementCommentDao.findById(selfEnhancementCommentID);
		//加一个反对
		selfEnhancementCommentTemp.setDisapproval(selfEnhancementCommentTemp.getDisapproval() + 1);
		//更新数据库
		selfEnhancementCommentDao.update(selfEnhancementCommentTemp);
			
		return selfEnhancementCommentTemp;
	}

	@Override
	public void save(SelfEnhancementComment selfEnhancementComment) {
		selfEnhancementCommentDao.save(selfEnhancementComment);
	}


}
