package com.ew.entity;

import javax.persistence.Column;

import org.springframework.stereotype.Controller;

@Controller("joke")
public class Joke extends BaseEntity{

	/**
	 * 笑话实体
	 */
	private static final long serialVersionUID = -3681215226780736409L;

	//笑话的ID
	private String jokeID;
	//笑话的导航图片
	private String image;
	//笑话的标题
	private String title; 
	//笑话的发布者
	private String publisher; 
	//笑话的摘要
	private String remark;
	//笑话的描述
	private String message;
	//笑话是否显示
	private Boolean isDisplay; 
	//笑话的点击量
	private Integer clicks;
	//类别
	private String category;
	
	public String getJokeID() {
		return jokeID;
	}
	public void setJokeID(String jokeID) {
		this.jokeID = jokeID;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Integer getClicks() {
		return clicks;
	}
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
