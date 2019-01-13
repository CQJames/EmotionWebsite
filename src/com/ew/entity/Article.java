package com.ew.entity;

import javax.persistence.Column;

public class Article extends BaseEntity{
	
	/**
	 * 文章实体
	 * 
	 */
	private static final long serialVersionUID = 8088617648955937535L;
	
	//文章的导航图片
    private String image;
	//文章的标题
	private String title; 
	//文章的发布者
	private String publisher; 
	//文章的摘要
	private String remark;
	//文章的描述
	private String message;
	//文章是否显示
	private Boolean isDisplay; 
	//文章的点赞量
	private Integer clicks;
	
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
	
}