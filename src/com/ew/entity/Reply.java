package com.ew.entity;

import javax.persistence.Column;

import org.springframework.stereotype.Controller;

@Controller("reply")
public class Reply extends BaseEntity{

	/**
	 * 问题回复实体
	 */
	private static final long serialVersionUID = 189487123694530936L;

	// 实体id
	private String replyID;
	// 信息
	private String message;
	//多对一用户
	private User user;
	//多对一问题
	private Utterance utterance;

	public String getReplyID() {
		return replyID;
	}
	public void setReplyID(String replyID) {
		this.replyID = replyID;
	}
	@Column(columnDefinition = "text")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Utterance getUtterance() {
		return utterance;
	}
	public void setUtterance(Utterance utterance) {
		this.utterance = utterance;
	}
}
