package com.luxunsoft.service;

import java.util.List;

public class BaseService<T> implements IBaseService<T> {

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
	 * 根据adId列表得到对应的BaseModel的记录
	 * 
	 * @param adIdList
	 *          adId列表
	 * @return TODO (为什么不能加？）
	 */
	// public List<T> queryByAdIdList(List<T> adIdList) {
	// return null;
	// }

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

}