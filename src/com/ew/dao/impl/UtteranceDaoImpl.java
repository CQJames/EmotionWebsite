package com.ew.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ew.core.dao.impl.BaseDaoImpl;
import com.ew.dao.UtteranceDao;
import com.ew.entity.Utterance;

@Repository("utteranceDao")
public class UtteranceDaoImpl extends BaseDaoImpl<Utterance> implements UtteranceDao{

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
	public List<Utterance> findByPageBackstage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		List<Utterance> list = (List<Utterance>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utterance> findByIdExcludeReply(DetachedCriteria detachedCriteria) {
		List<Utterance> list = (List<Utterance>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		for(int i=0;i<list.size();i++){
			list.get(i).setReply(null);
		}
		return list;
	}

	@Override
	public List<Utterance> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		//时间按最新排序
		detachedCriteria.addOrder(Order.desc("sub.saveTime"));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.utteranceID").as("utteranceID"));
		pList.add(Projections.property("sub.publisher").as("publisher"));
		pList.add(Projections.property("sub.message").as("message"));
		pList.add(Projections.property("sub.isDisplay").as("isDisplay"));
		pList.add(Projections.property("sub.visitors").as("visitors"));
		pList.add(Projections.property("sub.isSelected").as("isSelected"));
		pList.add(Projections.property("sub.saveTime").as("saveTime"));
		pList.add(Projections.property("sub.changeTime").as("changeTime"));
		detachedCriteria.setProjection(pList);
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(Utterance.class));
		return (List<Utterance>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);
	}

	@Override
	public List<Utterance> findTenBySelected(DetachedCriteria detachedCriteria1) {
		//取显示的文章
		detachedCriteria1.add(Restrictions.eq("sub.isDisplay", true));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.utteranceID").as("utteranceID"));
		pList.add(Projections.property("sub.message").as("message"));
		pList.add(Projections.property("sub.saveTime").as("saveTime"));
		detachedCriteria1.setProjection(pList);
		detachedCriteria1.setResultTransformer(Transformers.aliasToBean(Utterance.class));
		Criteria cri =  detachedCriteria1.getExecutableCriteria(this.getCurrentSession());
		//设置最多10条
		cri.setMaxResults(10);
		//选择已准备好的10条，我们精选推送的
		cri.add(Restrictions.eq("isSelected", true));

		return (List<Utterance>) cri.list();
	}

	@Override
	public List<Utterance> findTenByPopular(DetachedCriteria detachedCriteria2) {
		//取显示的文章
		detachedCriteria2.add(Restrictions.eq("sub.isDisplay", true));
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.utteranceID").as("utteranceID"));
		pList.add(Projections.property("sub.message").as("message"));
		pList.add(Projections.property("sub.saveTime").as("saveTime"));
		detachedCriteria2.setProjection(pList);
		detachedCriteria2.setResultTransformer(Transformers.aliasToBean(Utterance.class));
		Criteria cri =  detachedCriteria2.getExecutableCriteria(this.getCurrentSession());
		//设置最多10条
		cri.setMaxResults(10);
		//选择10条游览次数最多的
		cri.addOrder(Order.desc("visitors"));

		return (List<Utterance>) cri.list();
	}

}