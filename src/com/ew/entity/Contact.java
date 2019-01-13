package com.ew.entity;

import javax.persistence.Column;

import org.springframework.stereotype.Controller;

@Controller("contact")
public class Contact extends BaseEntity {

	/**
	 * 联系实体
	 */
	private static final long serialVersionUID = -5678890076717920174L;
	//自身表id
	private String contactID;
	//姓名
	private String name;
	//邮箱
	private String email;
	//主题
	private String subject;
	//信息
	private String message;
	
	public String getContactID() {
		return contactID;
	}
	public void setContactID(String contactID) {
		this.contactID = contactID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Column(columnDefinition = "text")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
