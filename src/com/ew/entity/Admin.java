package com.ew.entity;

import org.springframework.stereotype.Controller;

@Controller("admin")
public class Admin extends BaseEntity {

	/**
	 * 管理员实体
	 */
	private static final long serialVersionUID = -1754749074217589495L;
    // 自身表id
	private String adminID;
	// 真实姓名
	private String name;
	// 性别
	private Boolean sex;
	// 手机号码
	private String phoneNumber;
	// 电子邮箱
	private String email;
	// 头像图标
	private String icon;
	// 备注
	private String remark;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 是否删除该用户
	private Boolean isDelete;
	
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
}
