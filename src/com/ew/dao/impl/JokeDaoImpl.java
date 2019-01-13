package com.ew.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.ew.core.dao.impl.BaseDaoImpl;
import com.ew.dao.JokeDao;
import com.ew.entity.Joke;

@Repository("jokeDao")
public class JokeDaoImpl extends BaseDaoImpl<Joke> implements JokeDao {

	@Override
	public List<Joke> findAllShowJoke() {
		
		// 获得当前session对象
		Session session = getCurrentSession();
		// 书写HQL语句
		String hql = "from Joke where isDisplay = :major";
		// 创建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter("major", true);
		// 执行查询
		List<Joke> jokeList = (List<Joke>) query.list();
				
		return jokeList;
	}

	@Override
	public Joke findJokeById(String jokeID) {
		
		// 获得当前session对象
		Session session = getCurrentSession();
		// 书写HQL语句
		String hql = "from Joke where jokeID = :major";
		// 创建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter("major", jokeID);
		// 执行查询
		Joke jokeTemp = (Joke) query.uniqueResult();
		
		return jokeTemp;
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
	public List<Joke> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		List<Joke> list = (List<Joke>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);

		return list;
	}
	
}
