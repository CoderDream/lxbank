package com.luxunsoft.model;

import java.util.Date;

public class AccountDetail extends BaseModel {

	private static final long serialVersionUID = 1L;

	// 账户明细编号
	private int adId;

	// 账户明细编号
	private String accountDetailsId;

	// 记账日期
	private Date accountDate;

	// 记账日期
	private String accountDateStr;

	// 交易时间
	private Date exchangeDate;

	// 交易时间
	private String exchangeDateStr;

	// 凭证种类
	private String voucherType;

	// 凭证号
	private String voucher;

	// 发生额/元 借方
	private double debitAmount;

	// 发生额/元 借方
	private String debitAmountStr;

	// 发生额/元 贷方
	private double crebitAmount;

	// 发生额/元 贷方
	private String crebitAmountStr;

	// 借贷方标记
	private int debitAndCrebitFlag;

	// 余额/元
	private double balance;

	// 钞汇标志
	private String accountCaseFlag;

	// 对方户名
	private String sendName;

	// 对方账号
	private String sendNo;

	// 摘要
	private String summary;

	// 备注
	private String comment;

	// 账户明细编号-交易流水号
	private String serialNumber;

	// 企业流水号
	private String enterpriseSerialNumber;

	// 本方账号
	private String myAccountNo;

	// 本方账户名称
	private String myAccountName;

	// 本方账户开户机构
	private String myAccountBank;

	// 本方账户状态
	private String myAccountStatus;

	public String getAccountDetailsId() {
		return accountDetailsId;
	}

	public void setAccountDetailsId(String accountDetailsId) {
		this.accountDetailsId = accountDetailsId;
	}

	public Date getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	public Date getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public double getCrebitAmount() {
		return crebitAmount;
	}

	public void setCrebitAmount(double crebitAmount) {
		this.crebitAmount = crebitAmount;
	}

	public int getDebitAndCrebitFlag() {
		return debitAndCrebitFlag;
	}

	public void setDebitAndCrebitFlag(int debitAndCrebitFlag) {
		this.debitAndCrebitFlag = debitAndCrebitFlag;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountCaseFlag() {
		return accountCaseFlag;
	}

	public void setAccountCaseFlag(String accountCaseFlag) {
		this.accountCaseFlag = accountCaseFlag;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendNo() {
		return sendNo;
	}

	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEnterpriseSerialNumber() {
		return enterpriseSerialNumber;
	}

	public void setEnterpriseSerialNumber(String enterpriseSerialNumber) {
		this.enterpriseSerialNumber = enterpriseSerialNumber;
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

	public String getMyAccountStatus() {
		return myAccountStatus;
	}

	public void setMyAccountStatus(String myAccountStatus) {
		this.myAccountStatus = myAccountStatus;
	}

	public String getDebitAmountStr() {
		return debitAmountStr;
	}

	public void setDebitAmountStr(String debitAmountStr) {
		this.debitAmountStr = debitAmountStr;
	}

	public String getCrebitAmountStr() {
		return crebitAmountStr;
	}

	public void setCrebitAmountStr(String crebitAmountStr) {
		this.crebitAmountStr = crebitAmountStr;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public String getAccountDateStr() {
		return accountDateStr;
	}

	public void setAccountDateStr(String accountDateStr) {
		this.accountDateStr = accountDateStr;
	}

	public String getExchangeDateStr() {
		return exchangeDateStr;
	}

	public void setExchangeDateStr(String exchangeDateStr) {
		this.exchangeDateStr = exchangeDateStr;
	}

	@Override
	public String toString() {
		return "AccountDetail [accountDetailsId=" + accountDetailsId + ", accountDate=" + accountDate + ", exchangeDate="
				+ exchangeDate + ", voucherType=" + voucherType + ", voucher=" + voucher + ", debitAmount=" + debitAmount
				+ ", debitAmountStr=" + debitAmountStr + ", crebitAmount=" + crebitAmount + ", crebitAmountStr="
				+ crebitAmountStr + ", debitAndCrebitFlag=" + debitAndCrebitFlag + ", balance=" + balance
				+ ", accountCaseFlag=" + accountCaseFlag + ", sendName=" + sendName + ", sendNo=" + sendNo + ", summary="
				+ summary + ", comment=" + comment + ", serialNumber=" + serialNumber + ", enterpriseSerialNumber="
				+ enterpriseSerialNumber + ", myAccountNo=" + myAccountNo + ", myAccountName=" + myAccountName
				+ ", myAccountBank=" + myAccountBank + ", myAccountStatus=" + myAccountStatus + "]";
	}

}