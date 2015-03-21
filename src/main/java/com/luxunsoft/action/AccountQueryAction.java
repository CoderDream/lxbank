package com.luxunsoft.action;

import java.util.Map;

import com.luxunsoft.service.AccountQueryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AccountQueryAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 897710336701153630L;

	private String myAccountNo; // 用户输入的页数

	private AccountQueryService accountQueryService = new AccountQueryService();

	@Override
	public String input() throws Exception {
		System.out.println("");
		return super.input();
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		// String accountQuery,String accountQueryInput,String accountTypeQuery
		ActionContext context = ActionContext.getContext();
		Map<String, Object> request = (Map<String, Object>) context.get("request");
		// Map<String, Object> session = context.getSession();
		// Map<String, Object> application = context.getApplication();

		// 在请求中放置欢迎信息。
		String reqMyAccountNo = (String) request.get("myAccountNo");
		this.setMyAccountNo(reqMyAccountNo);

		return INPUT;
	}

	@SuppressWarnings("unchecked")
	public String queryDetail() throws Exception {
		// String accountQuery,String accountQueryInput,String accountTypeQuery
		ActionContext context = ActionContext.getContext();
		Map<String, Object> request = (Map<String, Object>) context.get("request");
		// Map<String, Object> session = context.getSession();
		// Map<String, Object> application = context.getApplication();

		// 在请求中放置欢迎信息。
		String reqMyAccountNo = (String) request.get("myAccountNo");
		this.setMyAccountNo(reqMyAccountNo);

		return SUCCESS;
	}

	public String getMyAccountNo() {
		return myAccountNo;
	}

	public void setMyAccountNo(String myAccountNo) {
		this.myAccountNo = myAccountNo;
	}

	public AccountQueryService getAccountQueryService() {
		return accountQueryService;
	}

	public void setAccountQueryService(AccountQueryService accountQueryService) {
		this.accountQueryService = accountQueryService;
	}

}