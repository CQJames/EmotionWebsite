package com.ew.web.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.entity.User;
import com.ew.exception.ForgetPwd1Exception;
import com.ew.exception.ForgetPwd2Exception;
import com.ew.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("forgetPasswordAction")
@Scope("prototype")
public class ForgetPasswordAction extends ActionSupport implements ModelDriven<User> {
	
	/**
	 * 忘记密码action
	 */
	private static final long serialVersionUID = 5860709493024860875L;
	
	private String checkCode;
	private String repassword;

	//模型驱动使用的对象
	private User user;
	private UserService userService;
	
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public User getUser() {
		return user;
	}

	@Resource(name="user")
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public User getModel() {
		return user;
	}
	

	public UserService getUserService() {
		return userService;
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	// 忘记密码
	public String findUserEmail() throws Exception {
		
		//回显页面信息
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        request.put("username", user.getUsername());
        //判断验证码是否正确
        String securityCode = (String) ActionContext.getContext().getSession().get("securityCode");
        if(!checkCode.equals(securityCode))
        {
        	throw new ForgetPwd1Exception("验证码错误");
        }
		// 调用userService执行找用户邮箱操作
    	User userTemp = userService.findUserEmail(user);
    	// 将用户放进session
    	ActionContext.getContext().getSession().put("userForgetPassword", userTemp);
   		// 转发到项目的忘记密码页面2
   		return "toForegroundForgetPwd2";  
	}
	
	public String sendEmailCheckCode() throws Exception {
		// 获得忘记密码页面1的找到的user对象
		User user = (User) ActionContext.getContext().getSession().get("userForgetPassword");
		// 往service传user
		String securityCode = userService.sendEmailCheckCode(user);
		// 放入session中
	    ActionContext.getContext().getSession().put("securityCode", securityCode);
	    // 告知前端成功
	    Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
	    request.put("sendSuccess", "true");
	    // 转发到忘记密码页面2
		return "toForegroundForgetPwd2";
	}
	
	public String validationCheckCode() throws Exception {
	
		//判断验证码是否正确
        String securityCode = (String) ActionContext.getContext().getSession().get("securityCode");
        if(!checkCode.equals(securityCode))
        {
        	throw new ForgetPwd2Exception("验证码错误");
        }	
   		// 转发到项目的忘记密码页面3
   		return "toForegroundForgetPwd3";  
	}
	
	public String saveResetPassword() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 获得忘记密码页面1的找到的user对象
		User userTemp = (User) session.get("userForgetPassword");
		// 重置密码
		userTemp.setPassword(user.getPassword());
		// 往userSevice转user
		User updateUser = userService.saveResetPassword(userTemp);
		//重新放入session
		session.put("user", updateUser);
   		// 转发到项目的忘记密码页面4
   		return "toForegroundForgetPwd4";  
	}
	
}

