package com.luxunsoft.service;

import java.util.List;

import com.luxunsoft.model.AccountDetail;
import com.luxunsoft.model.QueryModel;

public interface IAccountDetailService extends IBaseService<AccountDetail> {

	/**
	 * 得到总页数
	 * 
	 * @param pageSize
	 * @return
	 */
	public Integer getTotalPageSize(int pageSize);

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
	public Integer getTotalPageSize(int pageSize, QueryModel queryModel);

	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public List<AccountDetail> queryByPage(int pageSize, int pageNow);

	/**
	 * 根据adId列表得到对应的AccountDetail的记录
	 * 
	 * @param adIdList
	 *          adId列表
	 * @return
	 */
	public List<AccountDetail> queryByAdIdList(List<Integer> adIdList);

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
	public List<AccountDetail> queryByPage(int pageSize, int pageNow, QueryModel queryModel);

	public List<AccountDetail> getAccountDetailList();

}