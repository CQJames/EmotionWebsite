package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.Contact;

public interface ContactDao extends BaseDao<Contact>{

	List<Contact> findByPage(DetachedCriteria detachedCriteria, Integer begin,
			Integer rows);

	Integer findCount(DetachedCriteria detachedCriteria);

}
