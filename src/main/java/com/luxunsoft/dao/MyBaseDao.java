package com.luxunsoft.dao;

import java.util.List;

public class MyBaseDao<T> implements IMyBaseDao<T> {
	/**
	 * 得到总页数
	 * 
	 * @param pageSize
	 * @return
	 */
	public Integer getTotalPageSize(int pageSize) {
		return null;
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
	public Integer getTotalPageSize(int pageSize, T t) {
		return null;
	}

	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public List<T> queryByPage(int pageSize, int pageNow) {
		return null;
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
	public List<T> queryByPage(int pageSize, int pageNow, T t) {
		return null;
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
	public List<T> queryBySerialNumber(String serialNumber) {
		return null;
	}

	/**
	 * 得到当前页的记录
	 * 
	 * @param adId
	 * @return
	 */
	public T queryByAdId(Integer adId) {
		return null;
	}

	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public Integer addBaseModel(T t) {
		return null;
	}

	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public Integer updateBaseModel(T t) {
		return null;
	}

	public Integer makePersistent(T t) {
		return null;
	}
}