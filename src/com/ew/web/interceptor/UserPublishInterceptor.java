package com.ew.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class UserPublishInterceptor extends MethodFilterInterceptor{

	/**
	 * user发布东西拦截器
	 */
	private static final long serialVersionUID = -4311214546939443031L;

	// 指定发布方法拦截，其他方法不拦截
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
