package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.User;


public interface UserDao extends BaseDao<User> {
	Integer findCount(DetachedCriteria detachedCriteria);
	
	List<User> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer rows);

	List<User> findAll();

	User findByUserUsername(String username);

	List<User> findByIdExcludeComment(DetachedCriteria detachedCriteria);

	List<String> findAllUserUsername();

}
