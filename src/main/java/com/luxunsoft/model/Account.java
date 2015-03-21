package com.luxunsoft.model;

public class Account extends BaseModel {

	private static final long serialVersionUID = 1L;

	// 账户明细编号
	private Integer accountId;

	// 本方账户名称
	private String myAccountName;

	// 本方账号
	private String myAccountNo;

	// 币种
	private String cashType;
	// 本方账户开户机构
	private String myAccountBank;

	// 本方账户别名
	private String myAccountAlias;

	// 本方账户类型
	private String myAccountType;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getMyAccountNo() {
		return myAccountNo;
	}

	public void setMyAccountNo(String myAccountNo) {
		this.myAccountNo = myAccountNo;
	}

	public String getMyAccountName() {
		return myAccountName;
	}

	public void setMyAccountName(String myAccountName) {
		this.myAccountName = myAccountName;
	}

	public String getMyAccountBank() {
		return myAccountBank;
	}

	public void setMyAccountBank(String myAccountBank) {
		this.myAccountBank = myAccountBank;
	}

	public String getMyAccountType() {
		return myAccountType;
	}

	public void setMyAccountType(String myAccountType) {
		this.myAccountType = myAccountType;
	}

	public String getCashType() {
		return cashType;
	}

	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	public String getMyAccountAlias() {
		return myAccountAlias;
	}

	public void setMyAccountAlias(String myAccountAlias) {
		this.myAccountAlias = myAccountAlias;
	}

}