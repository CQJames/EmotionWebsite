package com.ew.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ew.core.dao.impl.BaseDaoImpl;
import com.ew.dao.CommunicationSkillsArticleDao;
import com.ew.entity.CommunicationSkillsArticle;

@Repository("communicationSkillsArticleDao")
public class CommunicationSkillsArticleDaoImpl extends BaseDaoImpl<CommunicationSkillsArticle> implements CommunicationSkillsArticleDao{


	@Override
	public List<CommunicationSkillsArticle> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.communicationSkillsArticleID").as("communicationSkillsArticleID"));
		pList.add(Projections.property("sub.visitors").as("visitors"));
		pList.add(Projections.property("sub.image").as("image"));
		pList.add(Projections.property("sub.title").as("title"));
		pList.add(Projections.property("sub.publisher").as("publisher"));
		pList.add(Projections.property("sub.remark").as("remark"));
		pList.add(Projections.property("sub.message").as("message"));
		pList.add(Projections.property("sub.isDisplay").as("isDisplay"));
		pList.add(Projections.property("sub.clicks").as("clicks"));
		pList.add(Projections.property("sub.saveTime").as("saveTime"));
		pList.add(Projections.property("sub.changeTime").as("changeTime"));
		detachedCriteria.setProjection(pList);
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(CommunicationSkillsArticle.class));
		return (List<CommunicationSkillsArticle>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);
	}

	@Override
	public List<CommunicationSkillsArticle> findTenByCulling(DetachedCriteria detachedCriteria1) {
		//取显示的文章
		detachedCriteria1.add(Restrictions.eq("sub.isDisplay", true));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.communicationSkillsArticleID").as("communicationSkillsArticleID"));
		pList.add(Projections.property("sub.title").as("title"));
		detachedCriteria1.setProjection(pList);
		detachedCriteria1.setResultTransformer(Transformers.aliasToBean(CommunicationSkillsArticle.class));
		Criteria cri =  detachedCriteria1.getExecutableCriteria(this.getCurrentSession());
		//设置最多10条
		cri.setMaxResults(10);
		//选择10条点赞量最多的
		cri.addOrder(Order.desc("clicks"));

		return (List<CommunicationSkillsArticle>) cri.list();
	}

	@Override
	public List<CommunicationSkillsArticle> findTenByNewest(DetachedCriteria detachedCriteria2) {
		//取显示的文章
		detachedCriteria2.add(Restrictions.eq("sub.isDisplay", true));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.communicationSkillsArticleID").as("communicationSkillsArticleID"));
		pList.add(Projections.property("sub.title").as("title"));
		detachedCriteria2.setProjection(pList);
		detachedCriteria2.setResultTransformer(Transformers.aliasToBean(CommunicationSkillsArticle.class));
		Criteria cri =  detachedCriteria2.getExecutableCriteria(this.getCurrentSession());
		//设置最多10条
		cri.setMaxResults(10);
		//选择10条最新的
		cri.addOrder(Order.desc("changeTime"));

		return (List<CommunicationSkillsArticle>) cri.list();
	}

	@Override
	public List<CommunicationSkillsArticle> findTenByPopular(DetachedCriteria detachedCriteria3) {
		//取显示的文章
		detachedCriteria3.add(Restrictions.eq("sub.isDisplay", true));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.communicationSkillsArticleID").as("communicationSkillsArticleID"));
		pList.add(Projections.property("sub.title").as("title"));
		detachedCriteria3.setProjection(pList);
		detachedCriteria3.setResultTransformer(Transformers.aliasToBean(CommunicationSkillsArticle.class));
		Criteria cri =  detachedCriteria3.getExecutableCriteria(this.getCurrentSession());
		//设置最多10条
		cri.setMaxResults(10);
		//选择10条游览人数最多的
		cri.addOrder(Order.desc("visitors"));

		return (List<CommunicationSkillsArticle>) cri.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		//利用Projections工具类的方法进行统计和分组
		//Projections.rowCount()--查询结果集中的记录条数
		detachedCriteria.setProjection(Projections.rowCount());//条件添加
		//Spring的框架提供了getHibernateTemplate ().findByCriteria(detachedCriteria)方法
		//可以很方便地根据DetachedCriteria来返回查询结果
		List<Long> list  = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		//取消查询总条数 以便等下进行正常分页查询
		detachedCriteria.setProjection(null);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CommunicationSkillsArticle> findByPageBackstage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		List<CommunicationSkillsArticle> list = (List<CommunicationSkillsArticle>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);

		return list;
	}

	@Override
	public CommunicationSkillsArticle findArticleById(String communicationSkillsArticleID) {
		// 获得当前session对象
		Session session = getCurrentSession();
		// 书写HQL语句
		String hql = "from CommunicationSkillsArticle where communicationSkillsArticleID = :major";
		// 创建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter("major", communicationSkillsArticleID);
		// 执行查询
		CommunicationSkillsArticle communicationSkillsArticleTemp = (CommunicationSkillsArticle) query.uniqueResult();
		
		return communicationSkillsArticleTemp;
	}

	@Override
	public List<CommunicationSkillsArticle> findAllArticle() {
		List<CommunicationSkillsArticle> communicationSkillsArticleList = (List<CommunicationSkillsArticle>) super.findObjects();
		
	    return communicationSkillsArticleList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommunicationSkillsArticle> findByIdExcludeComment(DetachedCriteria detachedCriteria) {
		List<CommunicationSkillsArticle> list = (List<CommunicationSkillsArticle>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		for(int i=0;i<list.size();i++){
			list.get(i).setCommunicationSkillsComment(null);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findIdByTitle(String title) {
		// 获得当前session对象
		Session session = getCurrentSession();
		// 书写HQL语句
		String hql = "select communicationSkillsArticleID,title from CommunicationSkillsArticle where title = :major";
		// 创建查询对象

		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter("major", title);
		
	
		List<String> list = (List<String>) query.list();    
			
		return list;
	}


}