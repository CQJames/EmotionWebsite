package com.ew.entity;

import javax.persistence.Column;

public class Comment extends BaseEntity {

	/**
	 * 评论实体
	 */
	private static final long serialVersionUID = 8898352416521572857L;

	// 信息
	private String message;
    // 点赞
	private Integer applaud;
	// 不赞同
	private Integer disapproval;
	
	@Column(columnDefinition = "text")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getApplaud() {
		return applaud;
	}
	public void setApplaud(Integer applaud) {
		this.applaud = applaud;
	}
	public Integer getDisapproval() {
		return disapproval;
	}
	public void setDisapproval(Integer disapproval) {
		this.disapproval = disapproval;
	}
	
}
