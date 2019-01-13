package com.ew.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.ew.dao.ContactDao;
import com.ew.entity.Contact;
import com.ew.entity.ContactPage;
import com.ew.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	private ContactDao contactDao;
	
	private ContactPage contactPage;
	
	public ContactDao getContactDao() {
		return contactDao;
	}
	@Resource(name="contactDao")
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	@Resource(name="contactPage")
	public void setContactPage(ContactPage contactPage) {
		this.contactPage = contactPage;
	}
	@Override
	public void saveOpinion(Contact contact) {
		contactDao.save(contact);
	}
	
	
	@Override
	public ContactPage findByPage(DetachedCriteria detachedCriteria,Integer page, Integer rows) {
		//设置当前页数
		contactPage.setCurrPage(page);
		//设置每页显示记录数
		contactPage.setPageSize(rows);
		//查询总的客户数
		Integer totalCount = contactDao.findCount(detachedCriteria);
		//设置总记录数
		contactPage.setTotalCount(totalCount);
		double tc = totalCount;
		//Math.ceil(double x)--返回大于或者等于指定表达式的最小整数
		Double num = Math.ceil(tc / rows);
		//设置 总页数
		contactPage.setTotalPage(num.intValue());
		//分页查询
		Integer begin = (page - 1 )* rows;
		List<Contact> list = contactDao.findByPage(detachedCriteria,begin,rows);
		contactPage.setList(list);
		return contactPage;
	}
	@Override
	public Contact findById(String contactID) {
		return contactDao.findById(contactID);
	}
	@Override
	public void delete(Contact contact) {
		contactDao.delete(contact.getContactID());	
		
	}

}
