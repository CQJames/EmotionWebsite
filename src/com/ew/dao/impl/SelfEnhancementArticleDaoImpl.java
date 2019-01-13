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
import com.ew.dao.SelfEnhancementArticleDao;
import com.ew.entity.SelfEnhancementArticle;

@Repository("selfEnhancementArticleDao")
public class SelfEnhancementArticleDaoImpl extends BaseDaoImpl<SelfEnhancementArticle> implements SelfEnhancementArticleDao{


	@Override
	public List<SelfEnhancementArticle> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.selfEnhancementArticleID").as("selfEnhancementArticleID"));
		pList.add(Projections.property("sub.category").as("category"));
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
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(SelfEnhancementArticle.class));
		return (List<SelfEnhancementArticle>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);
	}

	@Override
	public List<SelfEnhancementArticle> findTenByKnowYourself(DetachedCriteria detachedCriteria) {
		//取显示的文章
		detachedCriteria.add(Restrictions.eq("sub.isDisplay", true));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.selfEnhancementArticleID").as("selfEnhancementArticleID"));
		pList.add(Projections.property("sub.title").as("title"));
		detachedCriteria.setProjection(pList);
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(SelfEnhancementArticle.class));
		Criteria cri =  detachedCriteria.getExecutableCriteria(this.getCurrentSession());
		//设置最多10条
		cri.setMaxResults(10);
		//选择category为认识自己
		cri.add(Restrictions.eq("category", "认识自我"));
		//选择10条最新的
		cri.addOrder(Order.desc("changeTime"));

		return (List<SelfEnhancementArticle>) cri.list();
	}

	@Override
	public List<SelfEnhancementArticle> findTenBySelfConstruction(DetachedCriteria detachedCriteria) {
		//取显示的文章
		detachedCriteria.add(Restrictions.eq("sub.isDisplay", true));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.selfEnhancementArticleID").as("selfEnhancementArticleID"));
		pList.add(Projections.property("sub.title").as("title"));
		detachedCriteria.setProjection(pList);
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(SelfEnhancementArticle.class));
		Criteria cri =  detachedCriteria.getExecutableCriteria(this.getCurrentSession());
		//设置最多10条
		cri.setMaxResults(10);
		//选择category为认识自己
		cri.add(Restrictions.eq("category", "自身建设"));
		//选择10条最新的
		cri.addOrder(Order.desc("changeTime"));

		return (List<SelfEnhancementArticle>) cri.list();
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
	public List<SelfEnhancementArticle> findByPageBackstage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		List<SelfEnhancementArticle> list = (List<SelfEnhancementArticle>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);

		return list;
	}

	@Override
	public  SelfEnhancementArticle findArticleById(String selfEnhancementArticleID) {
		// 获得当前session对象
		Session session = getCurrentSession();
		// 书写HQL语句
		String hql = "from SelfEnhancementArticle where selfEnhancementArticleID = :major";
		// 创建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter("major", selfEnhancementArticleID);
		// 执行查询
		 SelfEnhancementArticle  selfEnhancementArticleTemp = (SelfEnhancementArticle) query.uniqueResult();
		
		return selfEnhancementArticleTemp;
	}

	@Override
	public List<SelfEnhancementArticle> findAllArticle() {
		List<SelfEnhancementArticle> selfEnhancementArticleList = (List<SelfEnhancementArticle>) super.findObjects();
		
	    return selfEnhancementArticleList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelfEnhancementArticle> findByIdExcludeComment(DetachedCriteria detachedCriteria) {
		List<SelfEnhancementArticle> list = (List<SelfEnhancementArticle>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		for(int i=0;i<list.size();i++){
			list.get(i).setSelfEnhancementComment(null);
		}
		return list;
	}

}
