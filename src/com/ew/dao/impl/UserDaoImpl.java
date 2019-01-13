package com.ew.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ew.core.dao.impl.BaseDaoImpl;
import com.ew.dao.UserDao;
import com.ew.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

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
	public List<User> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows) {
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.userID").as("userID"));
		pList.add(Projections.property("sub.name").as("name"));
		pList.add(Projections.property("sub.sex").as("sex"));
		pList.add(Projections.property("sub.phoneNumber").as("phoneNumber"));
		pList.add(Projections.property("sub.email").as("email"));
		pList.add(Projections.property("sub.icon").as("icon"));
		pList.add(Projections.property("sub.remark").as("remark"));
		pList.add(Projections.property("sub.username").as("username"));
		pList.add(Projections.property("sub.password").as("password"));
		pList.add(Projections.property("sub.isDelete").as("isDelete"));
		pList.add(Projections.property("sub.saveTime").as("saveTime"));
		pList.add(Projections.property("sub.changeTime").as("changeTime"));
		detachedCriteria.setProjection(pList);
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(User.class));
		return (List<User>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, rows);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return list;
	}

	@Override
	public User findByUserUsername(String username) {
		
		// 获得当前session对象
		Session session = getCurrentSession();
		// 书写HQL语句
		String hql = "from User where username = :major";
		// 创建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter("major", username);
		// 执行查询
		User user = (User) query.uniqueResult();
		
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByIdExcludeComment(DetachedCriteria detachedCriteria) {
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("sub.userID").as("userID"));
		pList.add(Projections.property("sub.name").as("name"));
		pList.add(Projections.property("sub.sex").as("sex"));
		pList.add(Projections.property("sub.phoneNumber").as("phoneNumber"));
		pList.add(Projections.property("sub.email").as("email"));
		pList.add(Projections.property("sub.icon").as("icon"));
		pList.add(Projections.property("sub.remark").as("remark"));
		pList.add(Projections.property("sub.username").as("username"));
		pList.add(Projections.property("sub.password").as("password"));
		pList.add(Projections.property("sub.isDelete").as("isDelete"));
		pList.add(Projections.property("sub.saveTime").as("saveTime"));
		pList.add(Projections.property("sub.changeTime").as("changeTime"));
		detachedCriteria.setProjection(pList);
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(User.class));
		return (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public List<String> findAllUserUsername() {
		// 获得当前session对象
		Session session = getCurrentSession();
		// 书写HQL语句
		String hql = "select username from User";
		// 创建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		List<String> list = (List<String>) query.list();    
		
		return list;
	}
	
}
