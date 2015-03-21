package com.luxunsoft.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthenticationInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2197575789906029515L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = ActionContext.getContext();
		Map<String, Object> session = ctx.getSession();
		Object user = session.get("user");

		// 如果session中没有user属性，说明用户还没有的登录
		if (user == null) {
			ActionSupport action = (ActionSupport) invocation.getAction();
			// 用户名没有登录，调用Action的addActionError()方法添加Action错误
			action.addActionError("你还没有登录，不能访问资源！请先登录");
			return Action.LOGIN;
		} else {
			// 如果用户已经登录，则继续执行余下的拦截器、Action和Result
			return invocation.invoke();
		}
	}
}
