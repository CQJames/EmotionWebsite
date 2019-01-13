package com.ew.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.Collection;
import com.ew.entity.CollectionPage;

public interface CollectionService {

	CollectionPage findByPage(DetachedCriteria detachedCriteria, Integer page,
			Integer rows);

	Collection findById(String collectionID);

	void delete(Collection collection);

	List<Collection> findAllCollection(DetachedCriteria detachedCriteria);

	void save(Collection collection);

	void update(Collection collection);

	boolean validation(String messageID, DetachedCriteria detachedCriteria);



}
