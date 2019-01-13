package com.ew.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.ew.core.dao.impl.BaseDaoImpl;
import com.ew.dao.CollectionDao;
import com.ew.entity.Collection;

@Repository("collectionDao")
public class CollectionDaoImpl extends BaseDaoImpl<Collection> implements CollectionDao {

	
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
	public List<Collection> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		List<Collection> list = (List<Collection>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Collection> findAllCollection(DetachedCriteria detachedCriteria) {
		List<Collection> collectionList = (List<Collection>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		
	    return collectionList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllCollectionMessageID(DetachedCriteria detachedCriteria) {
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("messageID").as("messageID"));
		detachedCriteria.setProjection(pList);
		List<String> list = (List<String>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		detachedCriteria.setProjection(null);
		return list;
//		// 获得当前session对象
//		Session session = getCurrentSession();
//		// 书写HQL语句
//		String hql = "select messageID from Collection";
//		// 创建查询对象
//		Query query = session.createQuery(hql);
//		// 设置参数
//		
//		List<String> list = (List<String>) query.list();    
//		
//		return list;
	}

	
	
}
