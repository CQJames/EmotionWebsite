package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.ew.dao.CollectionDao;
import com.ew.entity.Collection;
import com.ew.entity.CollectionPage;
import com.ew.service.CollectionService;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

	private CollectionDao collectionDao;
	
	private CollectionPage collectionPage;


	@Resource(name="collectionDao")
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
	}
	
	@Resource(name="collectionPage")
	public void setCollectionPage(CollectionPage collectionPage) {
		this.collectionPage = collectionPage;
	}

	@Override
	public CollectionPage findByPage(DetachedCriteria detachedCriteria,Integer page, Integer rows) {
		//设置当前页数
		collectionPage.setCurrPage(page);
		//设置每页显示记录数
		collectionPage.setPageSize(rows);
		//查询总的客户数
		Integer totalCount = collectionDao.findCount(detachedCriteria);
		//设置总记录数
		collectionPage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		collectionPage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<Collection> list = collectionDao.findByPage(detachedCriteria,begin,rows);
		collectionPage.setList(list);
		return collectionPage;
	}

	@Override
	public Collection findById(String collectionID) {
		return collectionDao.findById(collectionID);
	}

	@Override
	public void delete(Collection collection) {
		collectionDao.delete(collection.getCollectionID());	
		
	}

	@Override
	public List<Collection> findAllCollection(DetachedCriteria detachedCriteria) {
		List<Collection> collectionList = collectionDao.findAllCollection(detachedCriteria);
		
		return collectionList;
	}

	@Override
	public void save(Collection collection) {
		collectionDao.save(collection);	
		
	}

	@Override
	public void update(Collection collection) {
		collectionDao.update(collection);	
		
	}

	@Override
	public boolean validation(String messageID, DetachedCriteria detachedCriteria) {
		List<String> list = collectionDao.findAllCollectionMessageID(detachedCriteria);
		for(String value:list){
            if(value.equals(messageID)){
            	return true;
            }    	
        }
        return false;
	}
	
	





}
