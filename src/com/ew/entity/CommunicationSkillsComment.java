package com.ew.entity;

import org.springframework.stereotype.Controller;

@Controller("communicationSkillsComment")
public class CommunicationSkillsComment extends Comment {

	/**
	 * 交往技巧评论实体
	 */
	private static final long serialVersionUID = -1568759586354627484L;

	//实体id
	private String communicationSkillsCommentID;
	//多对一用户
	private User user;
	//多对一交往技巧文章
	private CommunicationSkillsArticle communicationSkillsArticle;
	
	public String getCommunicationSkillsCommentID() {
		return communicationSkillsCommentID;
	}
	public void setCommunicationSkillsCommentID(String communicationSkillsCommentID) {
		this.communicationSkillsCommentID = communicationSkillsCommentID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CommunicationSkillsArticle getCommunicationSkillsArticle() {
		return communicationSkillsArticle;
	}
	public void setCommunicationSkillsArticle(CommunicationSkillsArticle communicationSkillsArticle) {
		this.communicationSkillsArticle = communicationSkillsArticle;
	}
}
