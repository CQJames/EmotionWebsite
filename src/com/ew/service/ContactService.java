package com.ew.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ew.entity.Contact;
import com.ew.entity.ContactPage;

public interface ContactService {

	void saveOpinion(Contact contact);

	ContactPage findByPage(DetachedCriteria detachedCriteria, Integer page, Integer rows);

	Contact findById(String contactID);

	void delete(Contact contact);
}
