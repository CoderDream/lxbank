package com.luxunsoft.service;

import java.util.Iterator;
import java.util.List;

import com.luxunsoft.dao.AccountDetailDao;
import com.luxunsoft.dao.LoggingDao;
import com.luxunsoft.model.AccountDetail;

public class UploadExcelService {

	private AccountDetailDao accountDetailDao = new AccountDetailDao();

	private LoggingDao loggingDao = new LoggingDao(this.getClass().getName());

	public int uploadAccountDetailData(List<AccountDetail> accountDetailList) {
		int count = 0;
		// List<AccountDetail> accountDetailList = getAccountDetailList();
		AccountDetail accountDetail = null;
		for (Iterator<AccountDetail> iterator = accountDetailList.iterator(); iterator.hasNext();) {
			accountDetail = (AccountDetail) iterator.next();
			loggingDao.debug("UploadExcelService", accountDetail.toString());
			count += accountDetailDao.makePersistent(accountDetail);
		}

		return count;
	}

	public int makePersistent(AccountDetail accountDetail) {
		loggingDao.debug("UploadExcelService", accountDetail.toString());
		int count = accountDetailDao.makePersistent(accountDetail);
		loggingDao.debug("UploadExcelService", "uploadAccountDetail() count: " + count);
		return count;
	}
}