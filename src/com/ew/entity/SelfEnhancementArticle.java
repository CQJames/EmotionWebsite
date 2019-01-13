package com.ew.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;

@Controller("selfEnhancementArticle")
public class SelfEnhancementArticle extends Article {

	/**
	 * 自我提升文章实体
	 */
	private static final long serialVersionUID = -750181704743871464L;
	
	//实体id
	private String selfEnhancementArticleID;
	//类别
	private String category;
	//一对多自我提升评论
	private Set<SelfEnhancementComment> selfEnhancementComment = new HashSet<SelfEnhancementComment>();
	
	public String getSelfEnhancementArticleID() {
		return selfEnhancementArticleID;
	}
	public void setSelfEnhancementArticleID(String selfEnhancementArticleID) {
		this.selfEnhancementArticleID = selfEnhancementArticleID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Set<SelfEnhancementComment> getSelfEnhancementComment() {
		return selfEnhancementComment;
	}
	public void setSelfEnhancementComment(Set<SelfEnhancementComment> selfEnhancementComment) {
		this.selfEnhancementComment = selfEnhancementComment;
	}
}
