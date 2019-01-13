package com.ew.entity;

import org.springframework.stereotype.Controller;

@Controller("selfEnhancementComment")
public class SelfEnhancementComment extends Comment {

	/**
	 * 自我提升评论实体
	 */
	private static final long serialVersionUID = 8713499867459455737L;
	
	//实体id
	private String selfEnhancementCommentID;
	//多对一用户
	private User user;
	//多对一自我提升文章
	private SelfEnhancementArticle selfEnhancementArticle;
	
	public String getSelfEnhancementCommentID() {
		return selfEnhancementCommentID;
	}
	public void setSelfEnhancementCommentID(String selfEnhancementCommentID) {
		this.selfEnhancementCommentID = selfEnhancementCommentID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SelfEnhancementArticle getSelfEnhancementArticle() {
		return selfEnhancementArticle;
	}
	public void setSelfEnhancementArticle(SelfEnhancementArticle selfEnhancementArticle) {
		this.selfEnhancementArticle = selfEnhancementArticle;
	}
}
