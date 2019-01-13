package com.ew.web.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.Admin;
import com.ew.exception.AdminLoginException;
import com.ew.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport implements ModelDriven<Admin>
{
	/**
	 * 管理员action
	 */
	private static final long serialVersionUID = -7157271792086123564L;
	private String checkCode;
	private Admin admin;
	private AdminService adminService;
	
    @Resource(name="admin")
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public Admin getModel() {
		return admin;
	}	

	@Resource(name="adminService")
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String login() throws Exception {
		
		//回显页面信息
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        request.put("username", admin.getUsername());
        request.put("password", admin.getPassword());
        //判断验证码是否正确
        String securityCode = (String) ActionContext.getContext().getSession().get("securityCode");
        if(!checkCode.equals(securityCode))
        {
        	throw new AdminLoginException("验证码错误!");
        }
        // 调用adminService执行登录操作
    	Admin adminTemp = adminService.login(admin);
   		// 将返回的admin对象放入session域作为登录标识
   		ActionContext.getContext().getSession().put("admin", adminTemp);
   		// 转发到项目的首页
   		return "toBackstageIndex";  
	}
		
}