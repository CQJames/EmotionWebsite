package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.SelfEnhancementArticle;

public interface SelfEnhancementArticleDao extends BaseDao<SelfEnhancementArticle>{

	Integer findCount(DetachedCriteria detachedCriteria);

	List<SelfEnhancementArticle> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows);

	List<SelfEnhancementArticle> findTenByKnowYourself(DetachedCriteria detachedCriteria);

	List<SelfEnhancementArticle> findTenBySelfConstruction(DetachedCriteria detachedCriteria);
	
	
	
	
	SelfEnhancementArticle findArticleById(String selfEnhancementArticleID);

	List<SelfEnhancementArticle> findAllArticle();


	List<SelfEnhancementArticle> findByIdExcludeComment(DetachedCriteria detachedCriteria);

	List<SelfEnhancementArticle> findByPageBackstage(DetachedCriteria detachedCriteria, Integer begin, Integer rows);


}
