package com.luxunsoft.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class TimerInterceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4685794564827181789L;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		long startTime = System.currentTimeMillis();
		String result = invocation.invoke();
		long executionTime = System.currentTimeMillis() - startTime;

		System.out.println("Action的执行花费的毫秒数是：" + executionTime);

		return result;
	}
}
