package com.ew.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import org.springframework.stereotype.Controller;

@Controller("utterance")
public class Utterance extends BaseEntity{
	
	/**
	 * 问题实体
	 * 
	 */
	private static final long serialVersionUID = 8088617648955937535L;
	

	private String utteranceID;
	//问题的发布者
	private String publisher; 
	//问题的信息
	private String message;
	//问题是否显示
	private Boolean isDisplay;
	// 游览人数
	private Integer visitors;
	// 是否推荐为精选 
	private Boolean isSelected;
	
	private Set<Reply> reply = new HashSet<Reply>();

	public String getUtteranceID() {
		return utteranceID;
	}

	public void setUtteranceID(String utteranceID) {
		this.utteranceID = utteranceID;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(columnDefinition = "text")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}
	public Integer getVisitors() {
		return visitors;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Set<Reply> getReply() {
		return reply;
	}

	public void setReply(Set<Reply> reply) {
		this.reply = reply;
	}

	
	
}