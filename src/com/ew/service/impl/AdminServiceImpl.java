package com.ew.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ew.dao.AdminDao;
import com.ew.entity.Admin;
import com.ew.exception.AdminLoginException;
import com.ew.service.AdminService;
import com.ew.utils.EncryptionFactory;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao;
	
	@Resource(name="adminDao")
	public void setAdminDao(AdminDao adminDao){
		this.adminDao = adminDao;
	}

	@Override
	public Admin login(Admin admin) {
		
		// 调用dao根据用户名查询是否有Admin对象
		Admin existAdmin = adminDao.findByAdminUsername(admin.getUsername());
		if(existAdmin == null)
		{
			// 用户名不存在
			throw new AdminLoginException("账号不存在!");
		}
		// 加密用户密码
		admin.setPassword(EncryptionFactory.md5(admin.getPassword()));
		// 对比密码是否一致
		if(!existAdmin.getPassword().equals(admin.getPassword()))
		{
			// 密码不一致
			throw new AdminLoginException("密码错误!");
		}
		// 将数据库Admin对象返回
		return existAdmin;
	}
		
}
