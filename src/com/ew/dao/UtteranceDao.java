package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.Utterance;

public interface UtteranceDao extends BaseDao<Utterance>{

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Utterance> findByPageBackstage(DetachedCriteria detachedCriteria,
			Integer begin, Integer rows);

	List<Utterance> findByIdExcludeReply(DetachedCriteria detachedCriteria);

	List<Utterance> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows);

	List<Utterance> findTenBySelected(DetachedCriteria detachedCriteria1);

	List<Utterance> findTenByPopular(DetachedCriteria detachedCriteria2);


}
