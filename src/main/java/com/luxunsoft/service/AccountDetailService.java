package com.luxunsoft.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.luxunsoft.dao.AccountDetailDao;
import com.luxunsoft.dao.LoggingDao;
import com.luxunsoft.model.AccountDetail;
import com.luxunsoft.model.DataSet;
import com.luxunsoft.model.QueryModel;
import com.luxunsoft.util.Constant;
import com.luxunsoft.util.ExcelUtil;
import com.luxunsoft.util.ModelUtil;

public class AccountDetailService extends BaseService<AccountDetail> implements IAccountDetailService {

	private AccountDetailDao accountDetailDao = new AccountDetailDao();

	private LoggingDao loggingDao = new LoggingDao(this.getClass().getName());

	/**
	 * 得到总页数
	 * 
	 * @param pageSize
	 * @return
	 */
	public Integer getTotalPageSize(int pageSize) {
		int rtn = accountDetailDao.getTotalPageSize(pageSize);
		loggingDao.debug("pageSize: " + rtn);
		return rtn;
	}

	/**
	 * 得到总页数
	 * 
	 * @param pageSize
	 *          页大小
	 * @param accountQuery
	 *          查询类别
	 * @param accountQueryInput
	 *          输入的值
	 * @param accountTypeQuery
	 *          账户类别
	 * @return
	 */
	public Integer getTotalPageSize(int pageSize, QueryModel queryModel) {
		return accountDetailDao.getTotalPageSize(pageSize, queryModel);
	}

	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public List<AccountDetail> queryByPage(int pageSize, int pageNow) {
		List<AccountDetail> list = accountDetailDao.queryByPage(pageSize, pageNow);
		loggingDao.debug("list.size: " + list.size());
		return list;
	}

	/**
	 * 根据adId列表得到对应的AccountDetail的记录
	 * 
	 * @param adIdList
	 *          adId列表
	 * @return
	 */
	public List<AccountDetail> queryByAdIdList(List<Integer> adIdList) {
		List<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
		AccountDetail accountDetail = null;
		for (Iterator<Integer> iterator = adIdList.iterator(); iterator.hasNext();) {
			Integer adId = iterator.next();
			accountDetail = accountDetailDao.queryByAdId(adId);
			if (null != accountDetail) {
				accountDetailList.add(accountDetail);
			}
		}

		loggingDao.debug("list.size: " + accountDetailList.size());
		return accountDetailList;
	}

	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 *          页大小
	 * @param pageNow
	 *          当前页
	 * @param accountQuery
	 *          查询类别
	 * @param accountQueryInput
	 *          输入的值
	 * @param accountTypeQuery
	 *          账户类别
	 * @return
	 */
	public List<AccountDetail> queryByPage(int pageSize, int pageNow, QueryModel queryModel) {
		return accountDetailDao.queryByPage(pageSize, pageNow, queryModel);
	}

	public List<AccountDetail> getAccountDetailList() {
		List<AccountDetail> accountDetailList = null;
		try {
			// DataSet dataSet = readExcelPOI("D:\\部门员工资料.xls", 0);
			// 得到classes的路径
			String url = ExcelUtil.class.getClassLoader().getResource("").getPath();
			System.out.println(url);
			// 然后再加上文件的路径
			String fileName = url + "/data/42001237008050007480.xls";

			DataSet dataSet = ExcelUtil.readExcelPOI(fileName, Constant.DATA_OF_BEGIN_LINE);
			accountDetailList = ModelUtil.transferDataSetToAccountDetailList(dataSet);

			List<String[]> datasList = dataSet.getDatasList();
			for (Iterator<String[]> iterator = datasList.iterator(); iterator.hasNext();) {
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