package com.ew.core.dao.impl;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.ew.core.dao.BaseDao;

@Component("baseDao")
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	// 注入sessionFactory
	@Resource(name="sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);	
	}
	
	Class<T> clazz;
	/**获取当前类*/
	@SuppressWarnings("unchecked")
	public BaseDaoImpl(){
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	/**获取session*/
	public Session getCurrentSession(){
		Session session = null;
		try {
			session = getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			session = getSessionFactory().openSession();
		}
		return session;
	}
	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
		
	}
	
	@Override
	public void save(T entity) { 
		this.getHibernateTemplate().save(entity);		
	}

	@Override
	public void delete(Serializable id) {
		this.getHibernateTemplate().delete(findById(id));
		
	}

	@Override
	public T findById(Serializable id) {		
		return this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findObjects() {
		Session session = getCurrentSession();
		Query query = session.createQuery("FROM " + clazz.getSimpleName());
		return query.list();
	}

}