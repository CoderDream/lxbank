package com.luxunsoft.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.luxunsoft.model.AccountDetail;
import com.luxunsoft.util.Constant;

public class UploadExcelServiceTest {

	private UploadExcelService uploadExcelService;

	private static AccountDetail init() {
		AccountDetail accountDetail = new AccountDetail();

		// 0.记账日期
		Date accountDate = null;
		String accountDateStr = "2014-01-13";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (null != accountDateStr && !"".equals(accountDateStr.trim())) {
			try {
				accountDate = dateFormat.parse(accountDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		accountDetail.setAccountDate(accountDate);

		// 1.交易时间
		Date exchangeDate = null;
		String exchangeDateStr = "2014-01-13 17:15:14";
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null != exchangeDateStr && !"".equals(exchangeDateStr.trim())) {
			try {
				exchangeDate = dateTimeFormat.parse(exchangeDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		accountDetail.setExchangeDate(exchangeDate);

		// 2.凭证种类
		String voucherType = "";
		if (null == voucherType || "".equals(voucherType.trim())) {
			voucherType = "";
		}
		accountDetail.setVoucherType(voucherType);

		// 3.凭证号
		String voucher = "14598.08";
		if (null == voucher || "".equals(voucher.trim())) {
			voucher = "";
		}
		accountDetail.setVoucher(voucher);

		// 4.发生额/元 借方
		String debitAmountStr = "14598.08";
		double debitAmount = 0.0;
		if (null != debitAmountStr && !"".equals(debitAmountStr.trim())) {
			if (Constant.NULL_OF_ACCOUNT.equals(debitAmountStr)) {
				debitAmount = 0.0;
			} else {
				debitAmount = Double.parseDouble(debitAmountStr);
			}
		}
		accountDetail.setDebitAmount(debitAmount);
		accountDetail.setDebitAmountStr(debitAmountStr);

		// 5.发生额/元 贷方
		String crebitAmountStr = "0";
		double crebitAmount = 0.0;
		if (null != crebitAmountStr && !"".equals(crebitAmountStr.trim())) {
			if (Constant.NULL_OF_ACCOUNT.equals(crebitAmountStr)) {
				crebitAmount = 0.0;
			} else {
				crebitAmount = Double.parseDouble(crebitAmountStr);
			}
		}
		accountDetail.setCrebitAmount(crebitAmount);
		accountDetail.setCrebitAmountStr(crebitAmountStr);

		// 6.余额/元
		String balanceStr = "5163.49";
		double balance = 0.0;
		if (null != balanceStr && !"".equals(balanceStr.trim())) {
			balance = Double.parseDouble(balanceStr);
		}
		accountDetail.setBalance(balance);

		// 7.钞汇标志
		String accountCaseFlag = "钞户";
		if (null == accountCaseFlag || "".equals(accountCaseFlag.trim())) {
			accountCaseFlag = "";
		}
		accountDetail.setAccountCaseFlag(accountCaseFlag);

		// 8.对方户名
		String sendName = "财库联网集中户";
		if (null == sendName || "".equals(sendName.trim())) {
			sendName = "";
		}
		accountDetail.setSendName(sendName);

		// 9.对方账号
		String sendNo = "10142000001824103500000011";
		if (null == sendNo || "".equals(sendNo.trim())) {
			sendNo = "";
		}
		accountDetail.setSendNo(sendNo);

		// 10.摘要
		String summary = "财税库行扣款交易 财税库行扣款交易";
		if (null == summary || "".equals(summary.trim())) {
			summary = "";
		}
		accountDetail.setSummary(summary);

		// 11.备注
		String comment = "";
		if (null == comment || "".equals(comment.trim())) {
			comment = "";
		}
		accountDetail.setComment(comment);

		// 12.账户明细编号-交易流水号
		String serialNumber = "3528-42000001885NA3OT7H5";
		if (null == serialNumber || "".equals(serialNumber.trim())) {
			serialNumber = "";
		}
		accountDetail.setSerialNumber(serialNumber);

		// 13.企业流水号
		String enterpriseSerialNumber = "";
		if (null == enterpriseSerialNumber || "".equals(enterpriseSerialNumber.trim())) {
			enterpriseSerialNumber = "";
		}
		accountDetail.setEnterpriseSerialNumber(enterpriseSerialNumber);

		// 14.本方账号
		String myAccountNo = "42001237008050007480";
		if (null == myAccountNo || "".equals(myAccountNo.trim())) {
			myAccountNo = "";
		}
		accountDetail.setMyAccountNo(myAccountNo);

		// 15.本方账户名称
		String myAccountName = "武汉华顿科技有限公司";
		if (null == myAccountName || "".equals(myAccountName.trim())) {
			myAccountName = "";
		}
		accountDetail.setMyAccountName(myAccountName);

		// 16.本方账户开户机构
		String myAccountBank = "武汉市武昌支行营业室";
		if (null == myAccountBank || "".equals(myAccountBank.trim())) {
			myAccountBank = "";
		}
		accountDetail.setMyAccountBank(myAccountBank);

		// 17.本方账户状态
		String myAccountStatus = "正常";
		if (null == myAccountStatus || "".equals(myAccountStatus.trim())) {
			myAccountStatus = "";
		}
		accountDetail.setMyAccountStatus(myAccountStatus);

		return accountDetail;
	}

	@Test
	public void makePersistentAccountDetail() {
		uploadExcelService = new UploadExcelService();
		AccountDetail accountDetail = init();
		int actual = uploadExcelService.makePersistent(accountDetail);
		assertEquals(1, actual);
	}

	@Test
	public void uploadAccountDetailData() {
		uploadExcelService = new UploadExcelService();
		AccountDetail accountDetail1 = init();
		AccountDetail accountDetail2 = init();
		List<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
		accountDetailList.add(accountDetail1);
		accountDetailList.add(accountDetail2);
		int actual = uploadExcelService.uploadAccountDetailData(accountDetailList);
		assertEquals(2, actual);
	}

}