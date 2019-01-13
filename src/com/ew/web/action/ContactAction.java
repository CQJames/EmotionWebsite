package com.ew.web.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Contact;
import com.ew.service.ContactService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("contactAction")
@Scope("prototype")
public class ContactAction extends ActionSupport implements ModelDriven<Contact>
{
	/**
	 * 联系action
	 */
	private static final long serialVersionUID = -4624822013713531265L;

	private Contact contact;
	private ContactService contactService;
	
	@Override
	public Contact getModel() {
		return contact;
	}
	public Contact getContact() {
		return contact;
	}
	@Resource(name="contact")
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public ContactService getContactService() {
		return contactService;
	}
	@Resource(name="contactService")
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	
	public String commitOpinion(){
		//回显页面信息
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        request.put("name", contact.getName());
        request.put("email", contact.getEmail());
        request.put("subject", contact.getSubject());
        request.put("message", contact.getMessage());
		contactService.saveOpinion(contact);
		return "toForegroundContact";
	}
}