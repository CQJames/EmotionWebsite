package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.SelfEnhancementComment;

public interface SelfEnhancementCommentDao extends BaseDao<SelfEnhancementComment> {

	Integer findCount(DetachedCriteria detachedCriteria);

	SelfEnhancementComment findById(DetachedCriteria detachedCriteria);

	List<SelfEnhancementComment> findByPageBackstage(DetachedCriteria detachedCriteria, Integer begin, Integer rows);

	List<SelfEnhancementComment> findByArticlePaging(DetachedCriteria detachedCriteria, Integer begin, Integer rows);

}
