package com.ew.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.CommunicationSkillsArticle;
import com.ew.entity.CommunicationSkillsArticlePage;
import com.ew.entity.CommunicationSkillsCommentPage;

public interface CommunicationSkillsArticleService {

	CommunicationSkillsArticlePage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	List<CommunicationSkillsArticle> findTenByCulling(DetachedCriteria detachedCriteria1);

	List<CommunicationSkillsArticle> findTenByNewest(DetachedCriteria detachedCriteria2);

	List<CommunicationSkillsArticle> findTenByPopular(DetachedCriteria detachedCriteria3);
	
	CommunicationSkillsArticle findArticleById(String communicationSkillsArticleID);

	List<CommunicationSkillsArticle> findAllArticle();

	void delete(CommunicationSkillsArticle communicationSkillsArticle);

	void save(CommunicationSkillsArticle communicationSkillsArticle);

	List<CommunicationSkillsArticle> findByIdExcludeComment(DetachedCriteria detachedCriteria);

	void update(CommunicationSkillsArticle communicationSkillsArticle);

	List<String> findIdByTitle(String title);

	CommunicationSkillsArticlePage findByPageBackstage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	CommunicationSkillsArticle updateAddClicks(String communicationSkillsArticleID);

	CommunicationSkillsCommentPage findCommentByArticlePaging(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	CommunicationSkillsArticle updateAndFindById(String communicationSkillsArticleID);

	CommunicationSkillsArticle findById(String communicationSkillsArticleID);

}
