package com.luxunsoft.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.luxunsoft.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8047473058927169093L;

	private User user;
	private Map<String, Object> session;

	/**
	 * 通过调用login!input.action来访问login.jsp
	 */
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String execute() throws Exception {
		if ("admin".equals(user.getUsername()) && "1234".equals(user.getPassword())) {
			// 用户登录成功，将user对象保存到session中
			session.put("user", user);
			return SUCCESS;
		} else {
			addActionError("登录失败");
			return INPUT;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}