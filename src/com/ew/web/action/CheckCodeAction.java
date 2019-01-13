package com.ew.web.action;

import java.io.ByteArrayInputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ew.utils.SecurityCodeFactory;
import com.ew.utils.SecurityImageFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("checkCodeAction")
@Scope("prototype")
public class CheckCodeAction extends ActionSupport {
	 
	/**
	 * 生成验证码action
	 */
	private static final long serialVersionUID = -987066983331401827L;
	// 图片流
	private ByteArrayInputStream imageStream;
	private String timestamp;
 
	@Override
	public String execute() throws Exception {
		try {
			// 生成验证码
			String securityCode = SecurityCodeFactory.getSecurityCode();
			// 生成验证码图
			imageStream = SecurityImageFactory.getImageAsInputStream(securityCode);
			// 放入session中
			ActionContext.getContext().getSession().put("securityCode", securityCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
 
	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}
 
	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}
    
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}

