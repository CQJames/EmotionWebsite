package com.ew.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class UserLoginInterceptor extends MethodFilterInterceptor{

	/**
	 * user登录拦截器
	 */
	private static final long serialVersionUID = 6159267585389252620L;

	// 指定登录方法不拦截，其他方法拦截
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获得session
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 获得登录标识
		Object object = session.get("user");
		// 判断登录标识是否存在
		if(object == null)
		{
			// 不存在，没登录，重定向登录页面
			return "toUserLogin";
		}
	    // 存在，已登录，放行
	    return invocation.invoke();
	}	
}
