package com.luxunsoft.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.luxunsoft.model.AccountDetail;
import com.luxunsoft.model.QueryModel;

public class AccountDetailServiceTest {

	private AccountDetailService accountDetailService;

	// @Test
	public void getAccountDetailList() {
		accountDetailService = new AccountDetailService();

		List<AccountDetail> accountDetailList = accountDetailService.getAccountDetailList();
		assertEquals(12, accountDetailList.size());
	}

	// @Test
	public void initData() {
		// accountDetailService = new AccountDetailService();

		// int actual = accountDetailService.initData();
		// assertEquals(1, actual);
	}

	/**
	 * 本方账号
	 */
	@Test
	public void queryByPage_0101() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(5, actual.size());
	}

	/**
	 * 本方账号
	 */
	@Test
	public void queryByPage_0102() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "7480";
		queryModel.setMyAccountNo(myAccountNo);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(0, actual.size());
	}

	/*
	 * 记账日期开始
	 */
	@Test
	public void queryByPage_0201() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		String fromAccountDateStr = "20140113";
		queryModel.setFromAccountDateStr(fromAccountDateStr);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(5, actual.size());
	}

	// private String ;
	/*
	 * 记账日期截止
	 */
	@Test
	public void queryByPage_0202() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		String toAccountDateStr = "20140109";
		queryModel.setToAccountDateStr(toAccountDateStr);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(5, actual.size());
	}

	/*
	 * 记账日期截止
	 */
	@Test
	public void queryByPage_0203() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		String fromAccountDateStr = "20140108";
		queryModel.setFromAccountDateStr(fromAccountDateStr);
		String toAccountDateStr = "20140109";
		queryModel.setToAccountDateStr(toAccountDateStr);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(3, actual.size());
	}

	/*
	 * 
	 * 最小金额
	 */
	@Test
	public void queryByPage_0301() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		double minAmount = 21000.0;
		queryModel.setMinAmount(minAmount);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(1, actual.size());
	}

	/*
	 * 
	 * 最大金额 maxAmount;
	 */
	@Test
	public void queryByPage_0302() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		double maxAmount = 11.0;
		queryModel.setMaxAmount(maxAmount);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(2, actual.size());
	}

	/*
	 * 
	 * 最大金额 maxAmount;
	 */
	@Test
	public void queryByPage_0303() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		double minAmount = 100.0;
		queryModel.setMinAmount(minAmount);
		double maxAmount = 5000.0;
		queryModel.setMaxAmount(maxAmount);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(5, actual.size());
	}

	/*
	 * 交易方向
	 */
	@Test
	public void queryByPage_0304() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		double minAmount = 100.0;
		queryModel.setMinAmount(minAmount);
		double maxAmount = 1000.0;
		queryModel.setMaxAmount(maxAmount);
		int debitAndCrebitFlag = 1;
		queryModel.setDebitAndCrebitFlag(debitAndCrebitFlag);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(2, actual.size());
	}

	/*
	 * 交易方向
	 */
	@Test
	public void queryByPage_0305() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		double minAmount = 1000.0;
		queryModel.setMinAmount(minAmount);
		double maxAmount = 10000.0;
		queryModel.setMaxAmount(maxAmount);
		int debitAndCrebitFlag = 2;
		queryModel.setDebitAndCrebitFlag(debitAndCrebitFlag);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(2, actual.size());
	}

	// //
	// private String ;
	// //
	// //
	// private String sendName;
	// //
	// private String ;
	// //

	/**
	 * 对方户名
	 */
	@Test
	public void queryByPage_0401() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		String sendName = "江海科技";
		queryModel.setSendName(sendName);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(3, actual.size());
	}

	/**
	 * 对方户名
	 */
	@Test
	public void queryByPage_0402() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		String sendName = "企业信用管理";
		queryModel.setSendName(sendName);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(2, actual.size());
	}

	/**
	 * 对方账号
	 */
	@Test
	public void queryByPage_0501() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		String sendNo = "1812020319200036738";
		queryModel.setSendNo(sendNo);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(3, actual.size());
	}

	/**
	 * 对方账号
	 */
	@Test
	public void queryByPage_0502() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		String sendNo = "1812";
		queryModel.setSendNo(sendNo);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(3, actual.size());
	}

	/**
	 * 摘要
	 */
	@Test
	public void queryByPage_0601() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		String summary = "手续费";
		queryModel.setSummary(summary);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(1, actual.size());
	}

	/**
	 * 摘要
	 */
	@Test
	public void queryByPage_0602() {
		accountDetailService = new AccountDetailService();
		int pageSize = 5;
		int pageNow = 1;
		QueryModel queryModel = new QueryModel();
		// 江海科技
		String myAccountNo = "42001237008050007480";
		queryModel.setMyAccountNo(myAccountNo);
		// 江海科技
		String summary = "扣款";
		queryModel.setSummary(summary);
		List<AccountDetail> actual = accountDetailService.queryByPage(pageSize, pageNow, queryModel);
		assertEquals(4, actual.size());
	}

	@Test
	public void queryByAdIdList_01() {
		accountDetailService = new AccountDetailService();
		List<Integer> adIdList = new ArrayList<Integer>();
		adIdList.add(6);
		List<AccountDetail> actual = accountDetailService.queryByAdIdList(adIdList);
		assertEquals(1, actual.size());
	}

	@Test
	public void queryByAdIdList_02() {
		accountDetailService = new AccountDetailService();
		List<Integer> adIdList = new ArrayList<Integer>();
		adIdList.add(6);
		adIdList.add(7);
		adIdList.add(8);
		adIdList.add(9);
		List<AccountDetail> actual = accountDetailService.queryByAdIdList(adIdList);
		assertEquals(4, actual.size());
	}

	@Test
	public void queryByAdIdList_03() {
		accountDetailService = new AccountDetailService();
		List<Integer> adIdList = new ArrayList<Integer>();
		adIdList.add(6);
		adIdList.add(7);
		adIdList.add(8);
		adIdList.add(9);
		adIdList.add(109);
		List<AccountDetail> actual = accountDetailService.queryByAdIdList(adIdList);
		assertEquals(4, actual.size());
	}

}
