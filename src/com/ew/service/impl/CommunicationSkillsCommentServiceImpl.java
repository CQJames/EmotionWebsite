package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.ew.dao.CommunicationSkillsCommentDao;
import com.ew.entity.CommunicationSkillsComment;
import com.ew.entity.CommunicationSkillsCommentPage;
import com.ew.service.CommunicationSkillsCommentService;

@Service("communicationSkillsCommentService")
public class CommunicationSkillsCommentServiceImpl implements CommunicationSkillsCommentService {

	private CommunicationSkillsCommentDao communicationSkillsCommentDao;
	private CommunicationSkillsCommentPage communicationSkillsCommentPage;
	
	@Resource(name="communicationSkillsCommentDao")
	public void setCommunicationSkillsCommentDao(CommunicationSkillsCommentDao communicationSkillsCommentDao) {
		this.communicationSkillsCommentDao = communicationSkillsCommentDao;
	}

	@Resource(name="communicationSkillsCommentPage")
	public void setCommunicationSkillsCommentPage(CommunicationSkillsCommentPage communicationSkillsCommentPage) {
		this.communicationSkillsCommentPage = communicationSkillsCommentPage;
	}

	public CommunicationSkillsCommentPage findAllByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
		
		communicationSkillsCommentPage.setCurrPage(page);
		
		communicationSkillsCommentPage.setPageSize(rows);
		
		Integer totalCount = communicationSkillsCommentDao.findCount(detachedCriteria);
		communicationSkillsCommentPage.setTotalCount(totalCount);
		
		double tc = totalCount;
		Double num = Math.ceil(tc / rows);
		communicationSkillsCommentPage.setTotalPage(num.intValue());
		
		Integer begin = (page - 1) * rows;
		List<CommunicationSkillsComment> list = communicationSkillsCommentDao.findByPage(detachedCriteria,begin,rows);
		
		communicationSkillsCommentPage.setList(list);
	
		return communicationSkillsCommentPage; 
	}

	@Override
	public CommunicationSkillsComment findById(String communicationSkillsCommentID) {
		return communicationSkillsCommentDao.findById(communicationSkillsCommentID);
	}

	@Override
	public void update(CommunicationSkillsComment communicationSkillsComment) {
		communicationSkillsCommentDao.update(communicationSkillsComment);
		
	}

	@Override
	public void delete(CommunicationSkillsComment communicationSkillsComment) {
		communicationSkillsCommentDao.delete(communicationSkillsComment.getCommunicationSkillsCommentID());			
		
	}

	@Override
	public void save(CommunicationSkillsComment communicationSkillsComment) {
		communicationSkillsCommentDao.save(communicationSkillsComment);
		
	}

	@Override
	public CommunicationSkillsComment updateAddApplaud(String communicationSkillsCommentID) {
		CommunicationSkillsComment communicationSkillsCommentTemp = communicationSkillsCommentDao.findById(communicationSkillsCommentID);
		//加一个赞同
		communicationSkillsCommentTemp.setApplaud(communicationSkillsCommentTemp.getApplaud() + 1);
		//更新数据库
		communicationSkillsCommentDao.update(communicationSkillsCommentTemp);
			
		return communicationSkillsCommentTemp;
	}

	@Override
	public CommunicationSkillsComment updateAddDisapproval(String communicationSkillsCommentID) {
		CommunicationSkillsComment communicationSkillsCommentTemp = communicationSkillsCommentDao.findById(communicationSkillsCommentID);
		//加一个反对
		communicationSkillsCommentTemp.setDisapproval(communicationSkillsCommentTemp.getDisapproval() + 1);
		//更新数据库
		communicationSkillsCommentDao.update(communicationSkillsCommentTemp);
			
		return communicationSkillsCommentTemp;
	}
}
