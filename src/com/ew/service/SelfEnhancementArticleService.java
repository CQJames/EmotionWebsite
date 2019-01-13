package com.ew.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.SelfEnhancementArticle;
import com.ew.entity.SelfEnhancementArticlePage;
import com.ew.entity.SelfEnhancementCommentPage;

public interface SelfEnhancementArticleService {

	SelfEnhancementArticlePage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	List<SelfEnhancementArticle> findTenByKnowYourself(DetachedCriteria detachedCriteria);

	List<SelfEnhancementArticle> findTenBySelfConstruction(DetachedCriteria detachedCriteria);

	SelfEnhancementArticle findById(String selfEnhancementArticleID);
	
	SelfEnhancementArticle findArticleById(String selfEnhancementArticleID);

	List<SelfEnhancementArticle> findAllArticle();

	void delete(SelfEnhancementArticle selfEnhancementArticle);

	void save(SelfEnhancementArticle selfEnhancementArticle);

	List<SelfEnhancementArticle> findByIdExcludeComment(DetachedCriteria detachedCriteria);

	void update(SelfEnhancementArticle selfEnhancementArticle);

	SelfEnhancementArticle updateAddClicks(String selfEnhancementArticleID);
	
	public SelfEnhancementArticlePage findByPageBackstage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	SelfEnhancementCommentPage findCommentByArticlePaging(DetachedCriteria detachedCriteria, Integer page, Integer rows);

}
