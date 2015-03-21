package com.luxunsoft.action;

import java.util.List;

import com.luxunsoft.dao.AccountDao;
import com.luxunsoft.model.Account;
import com.luxunsoft.util.Constant;
import com.opensymphony.xwork2.ActionSupport;

public class AccountAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 897710336701153630L;

	private List<Account> accounts;
	private int pageNow = 1; // 初始化为1,默认从第一页开始显示
	private int pageTotal = 1; // 总页数
	private int pageInput = 1; // 用户输入的页数

	private AccountDao accountDao = new AccountDao();

	// 查询类别
	private String accountQuery;

	// 输入的值
	private String accountQueryInput;

	// 账户类别
	private String accountTypeQuery;

	public String execute() throws Exception {
		// String accountQuery,String accountQueryInput,String accountTypeQuery
		if (null == accountQueryInput || "".equals(accountQueryInput.trim())) {
			pageTotal = accountDao.getTotalPageSize(Constant.PAGE_SIZE);

			// 处理用户点击【上一页】和【下一页】边界情况
			if (pageNow > Constant.PAGE_SIZE) {
				pageNow = Constant.PAGE_SIZE;
			} else if (pageNow < 1) {
				pageNow = 1;
			}

			accounts = accountDao.queryByPage(Constant.PAGE_SIZE, pageInput);
			pageNow = pageInput;
		} else {
			pageTotal = accountDao.getTotalPageSize(Constant.PAGE_SIZE, accountQuery, accountQueryInput, accountTypeQuery);

			// 处理用户点击【上一页】和【下一页】边界情况
			if (pageNow > Constant.PAGE_SIZE) {
				pageNow = Constant.PAGE_SIZE;
			} else if (pageNow < 1) {
				pageNow = 1;
			}

			accounts = accountDao.queryByPage(Constant.PAGE_SIZE, pageInput, accountQuery, accountQueryInput,
					accountTypeQuery);
			pageNow = pageInput;
		}

		return SUCCESS;
	}

	public String newPage() throws Exception {
		// String accountQuery,String accountQueryInput,String accountTypeQuery
		if (null == accountQueryInput || "".equals(accountQueryInput.trim())) {
			pageTotal = accountDao.getTotalPageSize(Constant.PAGE_SIZE);

			// 处理用户点击【上一页】和【下一页】边界情况
			if (pageNow > Constant.PAGE_SIZE) {
				pageNow = Constant.PAGE_SIZE;
			} else if (pageNow < 1) {
				pageNow = 1;
			}

			accounts = accountDao.queryByPage(Constant.PAGE_SIZE, pageNow);
			pageInput = pageNow;
		} else {
			pageTotal = accountDao.getTotalPageSize(Constant.PAGE_SIZE, accountQuery, accountQueryInput, accountTypeQuery);

			// 处理用户点击【上一页】和【下一页】边界情况
			if (pageNow > Constant.PAGE_SIZE) {
				pageNow = Constant.PAGE_SIZE;
			} else if (pageNow < 1) {
				pageNow = 1;
			}

			accounts = accountDao.queryByPage(Constant.PAGE_SIZE, pageNow, accountQuery, accountQueryInput, accountTypeQuery);
			pageInput = pageNow;
		}

		return SUCCESS;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageInput() {
		return pageInput;
	}

	public void setPageInput(int pageInput) {
		this.pageInput = pageInput;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public String getAccountQuery() {
		return accountQuery;
	}

	public void setAccountQuery(String accountQuery) {
		this.accountQuery = accountQuery;
	}

	public String getAccountQueryInput() {
		return accountQueryInput;
	}

	public void setAccountQueryInput(String accountQueryInput) {
		this.accountQueryInput = accountQueryInput;
	}

	public String getAccountTypeQuery() {
		return accountTypeQuery;
	}

	public void setAccountTypeQuery(String accountTypeQuery) {
		this.accountTypeQuery = accountTypeQuery;
	}

}