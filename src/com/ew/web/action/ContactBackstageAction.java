package com.ew.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Contact;
import com.ew.entity.ContactPage;
import com.ew.service.ContactService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("contactBackstageAction")
@Scope("prototype")
public class ContactBackstageAction extends ActionSupport implements ModelDriven<Contact> {

	/**
	 * 交往技巧文章action
	 */
	private static final long serialVersionUID = -4257242217011379268L;

	private Contact contact;
	private ContactService contactService;
	
	@Resource(name = "contact")
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	@Resource(name = "contactService")
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	@Override
	public Contact getModel() {
		// TODO Auto-generated method stub
		return contact;
	}
	


	
	//接收分页的数据
	//接收当前页数和每页显示的记录数，这两个值在easyUI中已经有规定。page当前页数，rows每页显示记录数
	private Integer page = 1;
	private Integer rows = 3;
	private String searchName;
	

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String findAll() throws IOException {
		// 创建DetachedCriteria实例
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Contact.class);
		
		if(searchName != null){
			detachedCriteria.add(Restrictions.like("name","%"+searchName+"%"));
		}
		// 调用业务层
		ContactPage contactPage = contactService.findByPage(detachedCriteria, page, rows);
		// 使用jsonlib或者fastjson
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", contactPage.getTotalCount());
		map.put("rows", contactPage.getList());
		
		
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json);
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;

	}
	

	
	public String delete() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			contact = contactService.findById(contact.getContactID());
			contactService.delete(contact);
			map.put("msg", "用户信息删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "用户信息删除异常!");
		}	
		String json = new Gson().toJson(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}	
	

	
	public String findById() throws IOException {
		contact = contactService.findById(contact.getContactID());
		String json = new Gson().toJson(contact);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return NONE;
	}
	
	

}
