package com.ew.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.core.dao.BaseDao;
import com.ew.entity.Collection;

public interface CollectionDao extends BaseDao<Collection> {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Collection> findByPage(DetachedCriteria detachedCriteria,
			Integer begin, Integer rows);

	List<Collection> findAllCollection(DetachedCriteria detachedCriteria);


	List<String> findAllCollectionMessageID(DetachedCriteria detachedCriteria);



}
