package com.ew.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.SelfEnhancementComment;
import com.ew.entity.SelfEnhancementCommentPage;

public interface SelfEnhancementCommentService {

	SelfEnhancementCommentPage findAllByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	void delete(SelfEnhancementComment selfEnhancementComment);

	SelfEnhancementComment findById(String selfEnhancementCommentID);

	SelfEnhancementComment findById(DetachedCriteria detachedCriteria);

	SelfEnhancementComment updateAddApplaud(String selfEnhancementCommentID);

	SelfEnhancementComment updateAddDisapproval(String selfEnhancementCommentID);

	void save(SelfEnhancementComment selfEnhancementComment);
}
