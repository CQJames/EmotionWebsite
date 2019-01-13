package com.ew.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.Reply;
import com.ew.entity.ReplyPage;

public interface ReplyService {

	ReplyPage findAllByPage(DetachedCriteria detachedCriteria, Integer page,
			Integer rows);

	Reply findById(String replyID);

	void delete(Reply reply);

	void save(Reply reply);

}
