package com.ew.dao.impl;

import com.ew.dao.AdminDao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ew.core.dao.impl.BaseDaoImpl;
import com.ew.entity.Admin;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{

	@Override
	public Admin findByAdminUsername(String username) {
		
		// 获得当前session对象
		Session session = getCurrentSession();
		// 书写HQL语句
		String hql = "from Admin where username = :major";
		// 创建查询对象
		Query query = session.createQuery(hql);
		// 设置参数
		query.setParameter("major", username);
		// 执行查询
		Admin admin = (Admin) query.uniqueResult();
		
		return admin;
	}
	
}
