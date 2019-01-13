package com.ew.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;

@Controller("communicationSkillsArticle")
public class CommunicationSkillsArticle extends Article {

	/**
	 * 交往技巧文章实体
	 */
	private static final long serialVersionUID = -7246743704743713413L;
	
	//实体id
	private String communicationSkillsArticleID;
	//浏览人数
	private Integer visitors;
	//一对多自我提升评论
	private Set<CommunicationSkillsComment> communicationSkillsComment = new HashSet<CommunicationSkillsComment>();
	
	public String getCommunicationSkillsArticleID() {
		return communicationSkillsArticleID;
	}
	public void setCommunicationSkillsArticleID(String communicationSkillsArticleID) {
		this.communicationSkillsArticleID = communicationSkillsArticleID;
	}
	public Integer getVisitors() {
		return visitors;
	}
	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}
	public Set<CommunicationSkillsComment> getCommunicationSkillsComment() {
		return communicationSkillsComment;
	}
	public void setCommunicationSkillsComment(Set<CommunicationSkillsComment> communicationSkillsComment) {
		this.communicationSkillsComment = communicationSkillsComment;
	}
}
