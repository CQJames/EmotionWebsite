package com.ew.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.SexRatio;
import com.ew.entity.User;
import com.ew.entity.UserPage;

public interface UserService {
	
	void save(User user);
	
    void update(User user);
	
	void delete(User user);
	
	User findById(String id);

	List<User> findAll();
	
	UserPage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	User saveRegister(User user);

	User findByUserUsername(User user);

	User login(User user);

	List<User> findByIdExcludeComment(DetachedCriteria detachedCriteria);

	Boolean validation(String username);

	User findUserEmail(User user);

	String sendEmailCheckCode(User user);

	User saveResetPassword(User user);

	void modifyUserMessage(User userTemp);

	SexRatio findSexRatio(DetachedCriteria detachedCriteria);
}