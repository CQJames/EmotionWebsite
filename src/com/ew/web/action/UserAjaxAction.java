package com.ew.web.action;


import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.User;
import com.ew.service.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userAjaxAction")
@Scope("prototype")
public class UserAjaxAction extends ActionSupport implements ModelDriven<User> {
	
	/**
	 * 用户AjaxAction
	 */
	private static final long serialVersionUID = 1875743746919885163L;

	//存储将user转为json的字符串
	private String message;
	//模型驱动使用的对象
	private User user;

	private UserService userService;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	@Resource(name="user")
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public User getModel() {
		return user;
	}
	

	public UserService getUserService() {
		return userService;
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//用于ajax检查用户是否已经存在
	public String findByUserUsername() throws Exception {
		
		User userTemp = userService.findByUserUsername(user);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(userTemp != null){
			map.put("username", "用户名已存在");
			Gson gs = new Gson();
			message = gs.toJson(map);
		}
		else
		{
			map.put("username", "用户名不存在");
			Gson gs = new Gson();
			message = gs.toJson(map);
		}
		return SUCCESS;	
	}
}
