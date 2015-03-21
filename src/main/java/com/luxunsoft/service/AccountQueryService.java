package com.luxunsoft.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.luxunsoft.dao.AccountDetailDao;
import com.luxunsoft.model.AccountDetail;
import com.luxunsoft.model.DataSet;
import com.luxunsoft.util.Constant;
import com.luxunsoft.util.ExcelUtil;

public class AccountQueryService {

	private AccountDetailDao accountDetailDao = new AccountDetailDao();

	/**
	 * 得到总页数
	 * 
	 * @param pageSize
	 * @return
	 */
	public Integer getTotalPageSize(int pageSize) {
		return accountDetailDao.getTotalPageSize(pageSize);
	}


	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public List<AccountDetail> queryByPage(int pageSize, int pageNow) {
		return accountDetailDao.queryByPage(pageSize, pageNow);
	}

	public List<AccountDetail> getAccountDetailList() {
		List<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
		AccountDetail accountDetail = null;

		try {
			// DataSet dataSet = readExcelPOI("D:\\部门员工资料.xls", 0);
			// 得到classes的路径
			String url = ExcelUtil.class.getClassLoader().getResource("").getPath();
			System.out.println(url);
			// 然后再加上文件的路径
			String fileName = url + "/data/42001237008050007480.xls";

			String accountDetailsId = ExcelUtil.readExcelPOI(fileName, 4, 1);

			DataSet dataSet = ExcelUtil.readExcelPOI(fileName, Constant.DATA_OF_BEGIN_LINE);

			List<String[]> datasList = dataSet.getDatasList();
			for (Iterator<String[]> iterator = datasList.iterator(); iterator.hasNext();) {
				String[] strings = iterator.next();

				accountDetail = new AccountDetail();

				// 0.记账日期
				Date accountDate = null;
				String accountDateStr = strings[0];
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (null != accountDateStr && !"".equals(accountDateStr.trim())) {
					accountDate = dateFormat.parse(accountDateStr);
				} else {
					break;
				}
				accountDetail.setAccountDate(accountDate);

				// 1.交易时间
				Date exchangeDate = null;
				String exchangeDateStr = strings[1];
				SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (null != exchangeDateStr && !"".equals(exchangeDateStr.trim())) {
					exchangeDate = dateTimeFormat.parse(exchangeDateStr);
				}
				accountDetail.setExchangeDate(exchangeDate);

				// 2.凭证种类
				String voucherType = strings[2];
				if (null == voucherType || "".equals(voucherType.trim())) {
					voucherType = "";
				}
				accountDetail.setVoucherType(voucherType);

				// 3.凭证号
				String voucher = strings[3];
				if (null == voucher || "".equals(voucher.trim())) {
					voucher = "";
				}
				accountDetail.setVoucher(voucher);

				// 4.发生额/元 借方
				String debitAmountStr = strings[4];
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
				String crebitAmountStr = strings[5];
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
				String balanceStr = strings[6];
				double balance = 0.0;
				if (null != balanceStr && !"".equals(balanceStr.trim())) {
					balance = Double.parseDouble(balanceStr);
				}
				accountDetail.setBalance(balance);

				// 7.钞汇标志
				String accountCaseFlag = strings[7];
				if (null == accountCaseFlag || "".equals(accountCaseFlag.trim())) {
					accountCaseFlag = "";
				}
				accountDetail.setAccountCaseFlag(accountCaseFlag);

				// 8.对方户名
				String sendName = strings[8];
				if (null == sendName || "".equals(sendName.trim())) {
					sendName = "";
				}
				accountDetail.setSendName(sendName);

				// 9.对方账号
				String sendNo = strings[9];
				if (null == sendNo || "".equals(sendNo.trim())) {
					sendNo = "";
				}
				accountDetail.setSendNo(sendNo);

				// 10.摘要
				String summary = strings[10];
				if (null == summary || "".equals(summary.trim())) {
					summary = "";
				}
				accountDetail.setSummary(summary);

				// 11.备注
				String comment = strings[11];
				if (null == comment || "".equals(comment.trim())) {
					comment = "";
				}
				accountDetail.setComment(comment);

				// 12.账户明细编号-交易流水号
				String serialNumber = strings[12];
				if (null == serialNumber || "".equals(serialNumber.trim())) {
					serialNumber = "";
				}
				accountDetail.setSerialNumber(serialNumber);

				// 13.企业流水号
				String enterpriseSerialNumber = strings[13];
				if (null == enterpriseSerialNumber || "".equals(enterpriseSerialNumber.trim())) {
					enterpriseSerialNumber = "";
				}
				accountDetail.setEnterpriseSerialNumber(enterpriseSerialNumber);

				// 14.本方账号
				String myAccountNo = strings[14];
				if (null == myAccountNo || "".equals(myAccountNo.trim())) {
					myAccountNo = "";
				}
				accountDetail.setMyAccountNo(myAccountNo);

				// 15.本方账户名称
				String myAccountName = strings[15];
				if (null == myAccountName || "".equals(myAccountName.trim())) {
					myAccountName = "";
				}
				accountDetail.setMyAccountName(myAccountName);

				// 16.本方账户开户机构
				String myAccountBank = strings[16];
				if (null == myAccountBank || "".equals(myAccountBank.trim())) {
					myAccountBank = "";
				}
				accountDetail.setMyAccountBank(myAccountBank);

				// 17.本方账户状态
				String myAccountStatus = strings[17];
				if (null == myAccountStatus || "".equals(myAccountStatus.trim())) {
					myAccountStatus = "";
				}
				accountDetail.setMyAccountStatus(myAccountStatus);

				// 账户明细编号
				accountDetail.setAccountDetailsId(accountDetailsId);

				// 借贷方标记 TODO
				// int debitAndCrebitFlag = 0;

				System.out.println(accountDetail);
				accountDetailList.add(accountDetail);
			}
			System.out.println("================================");
			Set<String> datas = dataSet.getConStrctSet();
			String[] datastr = new String[datas.size()];
			datastr = datas.toArray(datastr);
			for (int i = 0; i < datastr.length; i++) {
				System.out.println(datastr[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountDetailList;
	}

	public int initData() {
		int count = 0;
		List<AccountDetail> accountDetailList = getAccountDetailList();
		for (Iterator<AccountDetail> iterator = accountDetailList.iterator(); iterator.hasNext();) {
			AccountDetail accountDetail = (AccountDetail) iterator.next();
			count = accountDetailDao.addAccountDetail(accountDetail);
		}

		return count;
	}

	public AccountDetailDao getAccountDetailDao() {
		return accountDetailDao;
	}

	public void setAccountDetailDao(AccountDetailDao accountDetailDao) {
		this.accountDetailDao = accountDetailDao;
	}

}