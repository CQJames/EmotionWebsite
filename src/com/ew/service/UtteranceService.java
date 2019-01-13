package com.ew.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.ReplyPage;
import com.ew.entity.Utterance;
import com.ew.entity.UtterancePage;

public interface UtteranceService {

	UtterancePage findByPageBackstage(DetachedCriteria detachedCriteria,
			Integer page, Integer rows);

	List<Utterance> findByIdExcludeReply(DetachedCriteria detachedCriteria);

	void save(Utterance utterance);

	Utterance findById(String utteranceID);

	void delete(Utterance utterance);

	void update(Utterance utterance);

	UtterancePage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	List<Utterance> findTenBySelected(DetachedCriteria detachedCriteria1);

	List<Utterance> findTenByPopular(DetachedCriteria detachedCriteria2);

	ReplyPage findReplyByUtterancePaging(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	Utterance updateAndFindById(String utteranceID);
}
