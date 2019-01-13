package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.CommunicationSkillsComment;

public interface CommunicationSkillsCommentDao extends BaseDao<CommunicationSkillsComment> {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<CommunicationSkillsComment> findByPage(DetachedCriteria detachedCriteria, Integer begin,
			Integer rows);

	List<CommunicationSkillsComment> findByArticlePaging(DetachedCriteria detachedCriteria, Integer begin,
			Integer rows);

}
