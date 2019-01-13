package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.ew.dao.ReplyDao;
import com.ew.entity.Reply;
import com.ew.entity.ReplyPage;
import com.ew.service.ReplyService;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	private ReplyDao replyDao;
	private ReplyPage replyPage;
	
	@Resource(name="replyDao")
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	@Resource(name="replyPage")
	public void setReplyPage(ReplyPage replyPage) {
		this.replyPage = replyPage;
	}

	@Override
	public ReplyPage findAllByPage(DetachedCriteria detachedCriteria,Integer page, Integer rows) {
		replyPage.setCurrPage(page);
		
		replyPage.setPageSize(rows);
		
		Integer totalCount = replyDao.findCount(detachedCriteria);
		replyPage.setTotalCount(totalCount);
		
		double tc = totalCount;
		Double num = Math.ceil(tc / rows);
		replyPage.setTotalPage(num.intValue());
		
		Integer begin = (page - 1) * rows;
		List<Reply> list = replyDao.findByPage(detachedCriteria,begin,rows);
		
		replyPage.setList(list);
	
		return replyPage; 
	}

	@Override
	public Reply findById(String replyID) {
		return replyDao.findById(replyID);
	}

	@Override
	public void delete(Reply reply) {
		replyDao.delete(reply.getReplyID());			
		
	}

	@Override
	public void save(Reply reply) {
		replyDao.save(reply);
	}

	


}
