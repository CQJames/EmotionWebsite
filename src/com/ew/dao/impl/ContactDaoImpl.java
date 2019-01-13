package com.ew.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.ew.core.dao.impl.BaseDaoImpl;
import com.ew.dao.ContactDao;
import com.ew.entity.Contact;

@Repository("contactDao")
public class ContactDaoImpl extends BaseDaoImpl<Contact> implements ContactDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
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
	public List<Contact> findByPage(DetachedCriteria detachedCriteria,Integer begin, Integer rows) {
		List<Contact> list = (List<Contact>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);

		return list;
	
	}

}
