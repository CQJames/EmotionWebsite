package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.Reply;

public interface ReplyDao extends BaseDao<Reply> {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Reply> findByPage(DetachedCriteria detachedCriteria, Integer begin,
			Integer rows);

	List<Reply> findByUtterancePaging(DetachedCriteria detachedCriteria, Integer begin, Integer rows);



}
