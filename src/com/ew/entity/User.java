package com.ew.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("user")
@Scope("prototype")
public class User extends BaseEntity {

	/**
	 * 用户实体
	 */
	private static final long serialVersionUID = -5170142231358661154L;
    // 自身表id
	private String userID;
	// 真实姓名
	private String name;
	// 性别
	private Boolean sex;
	// 手机号码
	private String phoneNumber;
	// 电子邮箱
	private String email;
	// 头像图标路径
	private String icon;
	// 备注
	private String remark;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 是否删除该用户
	private Boolean isDelete;
	//一对多自我提升评论
	private Set<SelfEnhancementComment> selfEnhancementComment = new HashSet<SelfEnhancementComment>();
	//一对多交往技巧评论
	private Set<CommunicationSkillsComment> communicationSkillsComment = new HashSet<CommunicationSkillsComment>();
	
	private Set<Reply> reply = new HashSet<Reply>();
	
	private Set<Collection> collection = new HashSet<Collection>();
	
	public Set<Collection> getCollection() {
		return collection;
	}
	public void setCollection(Set<Collection> collection) {
		this.collection = collection;
	}
	
	public Set<Reply> getReply() {
		return reply;
	}
	public void setReply(Set<Reply> reply) {
		this.reply = reply;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
	public Set<SelfEnhancementComment> getSelfEnhancementComment() {
		return selfEnhancementComment;
	}
	public void setSelfEnhancementComment(Set<SelfEnhancementComment> selfEnhancementComment) {
		this.selfEnhancementComment = selfEnhancementComment;
	}
	public Set<CommunicationSkillsComment> getCommunicationSkillsComment() {
		return communicationSkillsComment;
	}
	public void setCommunicationSkillsComment(Set<CommunicationSkillsComment> communicationSkillsComment) {
		this.communicationSkillsComment = communicationSkillsComment;
	}
}
