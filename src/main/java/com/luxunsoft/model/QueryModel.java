package com.luxunsoft.model;

public class QueryModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	// 本方账号
	private String myAccountNo;
	// 记账日期开始
	private String fromAccountDateStr;
	// 记账日期截止
	private String toAccountDateStr;
	// 最小金额
	private double minAmount;
	// 最大金额
	private double maxAmount;
	// 交易方向
	private int debitAndCrebitFlag;
	// 对方户名
	private String sendName;
	// 对方账号
	private String sendNo;
	// 摘要
	private String summary;

	public boolean isEmpty() {
		return (null == myAccountNo || this.myAccountNo.isEmpty())
				&& (null == fromAccountDateStr || this.fromAccountDateStr.isEmpty())
				&& (null == toAccountDateStr || this.toAccountDateStr.isEmpty()) && this.minAmount == 0.0
				&& this.maxAmount == 0.0 && this.debitAndCrebitFlag == 0 && (null == sendName || this.sendName.isEmpty())
				&& (null == sendNo || this.sendNo.isEmpty()) && (null == summary || this.summary.isEmpty());
	}

	public QueryModel() {

	}

	public QueryModel(String myAccountNo, String fromAccountDateStr, String toAccountDateStr, double minAmount,
			double maxAmount, int debitAndCrebitFlag, String sendName, String sendNo, String summary) {
		super();
		this.myAccountNo = myAccountNo;
		this.fromAccountDateStr = fromAccountDateStr;
		this.toAccountDateStr = toAccountDateStr;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.debitAndCrebitFlag = debitAndCrebitFlag;
		this.sendName = sendName;
		this.sendNo = sendNo;
		this.summary = summary;
	}

	public String getMyAccountNo() {
		return myAccountNo;
	}

	public void setMyAccountNo(String myAccountNo) {
		this.myAccountNo = myAccountNo;
	}

	public String getFromAccountDateStr() {
		return fromAccountDateStr;
	}

	public void setFromAccountDateStr(String fromAccountDateStr) {
		this.fromAccountDateStr = fromAccountDateStr;
	}

	public String getToAccountDateStr() {
		return toAccountDateStr;
	}

	public void setToAccountDateStr(String toAccountDateStr) {
		this.toAccountDateStr = toAccountDateStr;
	}

	public double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public int getDebitAndCrebitFlag() {
		return debitAndCrebitFlag;
	}

	public void setDebitAndCrebitFlag(int debitAndCrebitFlag) {
		this.debitAndCrebitFlag = debitAndCrebitFlag;
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

	public static void main(String[] args) {
		QueryModel queryModel = new QueryModel();
		boolean flag = queryModel.isEmpty();
		System.out.println(flag);
	}

}