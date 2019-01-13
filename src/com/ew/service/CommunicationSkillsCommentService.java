package com.ew.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.CommunicationSkillsComment;
import com.ew.entity.CommunicationSkillsCommentPage;

public interface CommunicationSkillsCommentService {

	CommunicationSkillsCommentPage findAllByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	CommunicationSkillsComment findById(String communicationSkillsCommentID);

	void update(CommunicationSkillsComment communicationSkillsComment);

	void delete(CommunicationSkillsComment communicationSkillsComment);

	void save(CommunicationSkillsComment communicationSkillsComment);

	CommunicationSkillsComment updateAddApplaud(String communicationSkillsCommentID);

	CommunicationSkillsComment updateAddDisapproval(String communicationSkillsCommentID);

}
