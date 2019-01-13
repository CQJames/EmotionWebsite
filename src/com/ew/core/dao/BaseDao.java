package com.ew.core.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	//新增
	public void save(T entity);
	//更新
	public void update(T entity);
	//删除
	public void delete(Serializable id);
	//根据id查询
	public T findById(Serializable id);
	//查询列表
	public List<T> findObjects();
}	