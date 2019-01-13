package com.ew.dao;

import com.ew.core.dao.BaseDao;
import com.ew.entity.Admin;

public interface AdminDao extends BaseDao<Admin>{

	Admin findByAdminUsername(String username);
	
}