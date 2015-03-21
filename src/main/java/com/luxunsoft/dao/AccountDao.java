package com.luxunsoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.luxunsoft.db.DBUtil;
import com.luxunsoft.model.Account;
import com.luxunsoft.util.Constant;

public class AccountDao extends MyBaseDao<Account> implements IAccountDao {
	/**
	 * 得到总页数
	 * 
	 * @param pageSize
	 * @return
	 */
	public Integer getTotalPageSize(int pageSize) {
		int rowCount = 0;
		int pageCount = 1;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();

			if (con != null && pageSize > 0) {
				String sql = "select count(*) from account;";
				// 获取prepareStatement对象
				pre = con.prepareStatement(sql);
				rs = pre.executeQuery();

				while (rs.next()) {
					rowCount = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}

		if (rowCount % pageSize == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}

		return pageCount;
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
	public Integer getTotalPageSize(int pageSize, String accountQuery, String accountQueryInput, String accountTypeQuery) {
		int rowCount = 0;
		int pageCount = 1;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "";
		try {
			con = DBUtil.getConnection();

			if (con != null && pageSize > 0) {
				// 账户名称
				if (null != accountQuery && !"".equals(accountQuery.trim())
						&& Constant.TYPE_OF_ACCOUNT_NAME.equals(accountQuery.trim())) {
					sql = "select count(*) from account a where a.myAccountName like '%" + accountQueryInput + "%';";
				}
				// 账号
				else {
					sql = "select count(*) from account a where a.myAccountNo like '%" + accountQueryInput + "%';";
				}

				// 获取prepareStatement对象
				pre = con.prepareStatement(sql);
				rs = pre.executeQuery();

				while (rs.next()) {
					rowCount = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}

		if (rowCount % pageSize == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}

		return pageCount;
	}

	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public List<Account> queryByPage(int pageSize, int pageNow) {
		List<Account> list = new ArrayList<Account>();

		PreparedStatement pre = null;
		ResultSet rs = null;
		// 获取prepareStatement对象
		Connection con = null;
		Account account = null;
		// 账户明细编号
		Integer accountId = null;
		// 本方账户名称
		String myAccountName = null;
		// 本方账号
		String myAccountNo = null;
		// 币种
		String cashType = null;
		// 本方账户开户机构
		String myAccountBank = null;
		// 本方账户别名
		String myAccountAlias = null;
		// 本方账户类型
		String myAccountType = null;
		try {
			con = DBUtil.getConnection();

			if (con != null && pageSize > 0 && pageNow > 0) {
				String sql = "select * from account order by accountId limit " + (pageNow * pageSize - pageSize) + ","
						+ pageSize;

				pre = con.prepareStatement(sql);
				rs = pre.executeQuery();

				while (rs.next()) {
					account = new Account();
					accountId = rs.getInt(1);
					account.setAccountId(accountId);
					myAccountName = rs.getString(2);
					account.setMyAccountName(myAccountName);
					myAccountNo = rs.getString(3);
					account.setMyAccountNo(myAccountNo);
					cashType = rs.getString(4);
					account.setCashType(cashType);
					myAccountBank = rs.getString(5);
					account.setMyAccountBank(myAccountBank);
					myAccountAlias = rs.getString(6);
					if (null == myAccountAlias || "".equals(myAccountAlias.trim())) {
						myAccountAlias = "&nbsp;&nbsp";
					}
					account.setMyAccountAlias(myAccountAlias);
					myAccountType = rs.getString(7);
					account.setMyAccountType(myAccountType);
					list.add(account);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return list;
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
	public List<Account> queryByPage(int pageSize, int pageNow, String accountQuery, String accountQueryInput,
			String accountTypeQuery) {
		List<Account> list = new ArrayList<Account>();

		PreparedStatement pre = null;
		ResultSet rs = null;
		// 获取prepareStatement对象
		Connection con = null;
		Account account = null;
		// 账户明细编号
		Integer accountId = null;
		// 本方账户名称
		String myAccountName = null;
		// 本方账号
		String myAccountNo = null;
		// 币种
		String cashType = null;
		// 本方账户开户机构
		String myAccountBank = null;
		// 本方账户别名
		String myAccountAlias = null;
		// 本方账户类型
		String myAccountType = null;

		String sql = "";
		try {
			con = DBUtil.getConnection();

			if (con != null && pageSize > 0 && pageNow > 0) {
				// 账户名称
				if (null != accountQuery && !"".equals(accountQuery.trim())
						&& Constant.TYPE_OF_ACCOUNT_NAME.equals(accountQuery.trim())) {
					sql = "select * from account a where a.myAccountName like '%" + accountQueryInput
							+ "%' order by accountId limit " + (pageNow * pageSize - pageSize) + "," + pageSize;
				}
				// 账号
				else {
					sql = "select * from account a where a.myAccountNo like '%" + accountQueryInput
							+ "%' order by accountId limit " + (pageNow * pageSize - pageSize) + "," + pageSize;
				}

				pre = con.prepareStatement(sql);
				rs = pre.executeQuery();

				while (rs.next()) {
					account = new Account();
					accountId = rs.getInt(1);
					account.setAccountId(accountId);
					myAccountName = rs.getString(2);
					account.setMyAccountName(myAccountName);
					myAccountNo = rs.getString(3);
					account.setMyAccountNo(myAccountNo);
					cashType = rs.getString(4);
					account.setCashType(cashType);
					myAccountBank = rs.getString(5);
					account.setMyAccountBank(myAccountBank);
					myAccountAlias = rs.getString(6);
					if (null == myAccountAlias || "".equals(myAccountAlias.trim())) {
						myAccountAlias = "&nbsp;&nbsp";
					}
					account.setMyAccountAlias(myAccountAlias);
					myAccountType = rs.getString(7);
					account.setMyAccountType(myAccountType);
					list.add(account);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return list;
	}
}