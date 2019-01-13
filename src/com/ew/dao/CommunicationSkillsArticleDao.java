package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.CommunicationSkillsArticle;

public interface CommunicationSkillsArticleDao extends BaseDao<CommunicationSkillsArticle>{

	Integer findCount(DetachedCriteria detachedCriteria);

	List<CommunicationSkillsArticle> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows);

	List<CommunicationSkillsArticle> findTenByCulling(DetachedCriteria detachedCriteria1);

	List<CommunicationSkillsArticle> findTenByNewest(DetachedCriteria detachedCriteria2);

	List<CommunicationSkillsArticle> findTenByPopular(DetachedCriteria detachedCriteria3);
	
	CommunicationSkillsArticle findArticleById(String communicationSkillsArticleID);

	List<CommunicationSkillsArticle> findAllArticle();

	List<CommunicationSkillsArticle> findByIdExcludeComment(DetachedCriteria detachedCriteria);

	List<String> findIdByTitle(String title);

	List<CommunicationSkillsArticle> findByPageBackstage(DetachedCriteria detachedCriteria, Integer begin,
			Integer rows);

}
