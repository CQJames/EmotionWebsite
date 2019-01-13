package com.ew.entity;

import org.springframework.stereotype.Controller;

@Controller("collection")
public class Collection extends BaseEntity {

	/**
	 * 收藏实体
	 * 
	 */
	private static final long serialVersionUID = 1771796900385655088L;


	private String collectionID;
	// 收藏的导航图片
	private String image;
	// 收藏的标题
	private String title;
	// 收藏的内容类型
	private String type;
	// 收藏的内容ID
	private String messageID;
	// 收藏的内容链接
	private String messageURl;
	// 是否收藏
	private Boolean isCollect;
	//多对一用户
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCollectionID() {
		return collectionID;
	}

	public void setCollectionID(String collectionID) {
		this.collectionID = collectionID;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getMessageURl() {
		return messageURl;
	}

	public void setMessageURl(String messageURl) {
		this.messageURl = messageURl;
	}

	public Boolean getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Boolean isCollect) {
		this.isCollect = isCollect;
	}

}