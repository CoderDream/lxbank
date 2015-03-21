package com.luxunsoft.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class LoggerInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8739387798735611075L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		invocation.addPreResultListener(new PreResultListener() {
			@Override
			public void beforeResult(ActionInvocation invocation, String resultCode) {
				System.out.println("(3) beforeResult()方法调用，Action已经执行完毕，Result还未执行");
			}
		});
		System.out.println("(1) Action和Result还没有开始执行");
		String resultCode = invocation.invoke();
		System.out.println("(4) Action和Result已经执行完毕，控制权重新交给LoggerInterceptor");
		return resultCode;
	}
}