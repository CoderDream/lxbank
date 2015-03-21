package com.luxunsoft.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.luxunsoft.db.DBUtil;
import com.luxunsoft.model.AccountDetail;
import com.luxunsoft.model.QueryModel;
import com.luxunsoft.util.Constant;

public class AccountDetailDao {

	private LoggingDao loggingDao = new LoggingDao(this.getClass().getName());

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
				String sql = "select count(*) from accountdetail;";
				// 获取prepareStatement对象
				pre = con.prepareStatement(sql);

				loggingDao.error("AccountDetailDao", "sql: " + sql);
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
	public Integer getTotalPageSize(int pageSize, QueryModel queryModel) {
		int rowCount = 0;
		int pageCount = 1;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "";

		try {
			con = DBUtil.getConnection();

			if (con != null && pageSize > 0) {
				sql = "select count(*) from accountdetail a where 1=1 ";

				sql = genSQL(sql, queryModel);

				// 获取prepareStatement对象
				pre = con.prepareStatement(sql);
				loggingDao.debug("AccountDetailDao", "sql: " + sql);
				rs = pre.executeQuery();

				while (rs.next()) {
					rowCount = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			loggingDao.error("AccountDetailDao", "Exception: " + e.getMessage());
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
	public List<AccountDetail> queryByPage(int pageSize, int pageNow) {
		List<AccountDetail> list = new ArrayList<AccountDetail>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		// 获取prepareStatement对象
		Connection con = null;

		try {
			con = DBUtil.getConnection();

			if (con != null && pageSize > 0 && pageNow > 0) {
				String sql = "select * from accountdetail order by exchangeDate limit " + (pageNow * pageSize - pageSize) + ","
						+ pageSize;

				loggingDao.debug("AccountDetailDao", "sql: " + sql);
				pre = con.prepareStatement(sql);
				rs = pre.executeQuery();

				list = transferResultSetToList(rs);
			}
		} catch (Exception e) {
			loggingDao.error("AccountDetailDao", "sql: " + e);
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return list;
	}

	/**
	 * 根据查询条件生成SQL语句
	 * 
	 * @param sql
	 * @param queryModel
	 * @return
	 */
	private String genSQL(String sql, QueryModel queryModel) {
		// 记账日期开始
		String fromAccountDateStr;
		// 记账日期截止
		String toAccountDateStr;
		// 最小金额
		double minAmount;
		// 最大金额
		double maxAmount;
		// 交易方向
		int debitAndCrebitFlag;
		// 对方户名
		String sendName;
		// 对方账号
		String sendNo;
		// 摘要
		String summary;
		// 本方账号
		String myAccountNo = queryModel.getMyAccountNo();
		if (null != myAccountNo && !"".equals(myAccountNo.trim())) {
			sql += "and myAccountNo = '" + myAccountNo + "' ";
		}

		// TODO
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date ud = null;
		Date sd = null;
		// 记账日期开始
		fromAccountDateStr = queryModel.getFromAccountDateStr();
		if (null != fromAccountDateStr && !"".equals(fromAccountDateStr.trim())) {

			try {
				ud = dateFormat.parse(fromAccountDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sd = new Date(ud.getTime());
			sql += "and accountDate >= '" + sd + "' ";
		}

		// 记账日期截止
		toAccountDateStr = queryModel.getToAccountDateStr();
		if (null != toAccountDateStr && !"".equals(toAccountDateStr.trim())) {
			try {
				ud = dateFormat.parse(toAccountDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sd = new Date(ud.getTime());
			sql += "and accountDate <= '" + sd + "' ";
		}

		// 最小金额
		minAmount = queryModel.getMinAmount();
		// 最大金额
		maxAmount = queryModel.getMaxAmount();
		// 发生额/元 借方 debitAmount;
		// 发生额/元 贷方 crebitAmount;

		// 交易方向
		debitAndCrebitFlag = queryModel.getDebitAndCrebitFlag();
		if (0 == debitAndCrebitFlag) {
			if (0.0 != minAmount) {
				sql += "and ( debitAmount >= " + minAmount + " or crebitAmount >=" + minAmount + ") ";
			}

			if (0.0 != maxAmount) {
				sql += "and ((debitAmount <= " + maxAmount + " and crebitAmount = 0 ) or (crebitAmount <=" + maxAmount
						+ " and debitAmount = 0 )) ";
			}
		}
		// 发生额/元 借方 转入
		else if (1 == debitAndCrebitFlag) {
			if (0.0 != minAmount) {
				sql += "and debitAmount >= " + minAmount + " ";
			}

			if (0.0 != maxAmount) {
				sql += "and debitAmount <= " + maxAmount + " ";
			}
		}
		// 发生额/元 贷方 转出
		else if (2 == debitAndCrebitFlag) {
			if (0.0 != minAmount) {
				sql += "and crebitAmount >=" + minAmount + " ";
			}

			if (0.0 != maxAmount) {
				sql += "and crebitAmount <=" + maxAmount + " ";
			}
		}

		// 对方户名
		sendName = queryModel.getSendName();
		if (null != sendName && !"".equals(sendName.trim())) {
			sql += "and sendName like '%" + sendName + "%' ";
		}

		// 对方账号
		sendNo = queryModel.getSendNo();
		if (null != sendNo && !"".equals(sendNo.trim())) {
			sql += "and sendNo like '%" + sendNo + "%' ";
		}

		// 摘要
		summary = queryModel.getSummary();
		if (null != summary && !"".equals(summary.trim())) {
			sql += "and summary like '%" + summary + "%' ";
		}

		return sql;
	}

	private List<AccountDetail> transferResultSetToList(ResultSet rs) {
		List<AccountDetail> list = new ArrayList<AccountDetail>();
		// 账户明细编号
		int adId = 0;

		// 记账日期
		Date accountDate = null;

		// 交易时间
		Timestamp exchangeDate = null;

		// 凭证种类
		String voucherType = null;

		// 凭证号
		String voucher = null;

		// 发生额/元 借方
		double debitAmount = 0.0;

		// 发生额/元 借方
		String debitAmountStr = null;

		// 发生额/元 贷方
		double crebitAmount = 0.0;

		// 发生额/元 贷方
		String crebitAmountStr = null;

		// 余额/元
		double balance = 0.0;

		// 钞汇标志
		String accountCaseFlag = null;

		// 对方户名
		String sendName = null;

		// 对方账号
		String sendNo = null;

		// 摘要
		String summary = null;

		// 备注
		String comment = null;

		// 账户明细编号-交易流水号
		String serialNumber = null;

		// 企业流水号
		String enterpriseSerialNumber = null;

		// 本方账号
		String myAccountNo = null;

		// 本方账户名称
		String myAccountName = null;

		// 本方账户开户机构
		String myAccountBank = null;

		// 本方账户状态
		String myAccountStatus = null;

		AccountDetail accountDetail = null;
		try {
			while (rs.next()) {
				accountDetail = new AccountDetail();

				// 1
				adId = rs.getInt(1);
				accountDetail.setAdId(adId);

				// 2
				accountDate = rs.getDate(2);
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

				if (null != accountDate) {
					String accountDateStr = dateFormat1.format(accountDate.getTime());
					accountDetail.setAccountDate(dateFormat1.parse(accountDateStr));
					accountDetail.setAccountDateStr(accountDateStr);
				}

				// 3.java.sql.Date的精确保存问题
				exchangeDate = rs.getTimestamp(3); // 指定时间
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (null != exchangeDate) {
					String exchangeDateStr = dateFormat2.format(exchangeDate.getTime());
					accountDetail.setExchangeDate(dateFormat2.parse(exchangeDateStr));
					accountDetail.setExchangeDateStr(exchangeDateStr);
				}

				// 4.
				voucherType = rs.getString(4);
				accountDetail.setVoucherType(voucherType);

				// 5.
				voucher = rs.getString(5);
				accountDetail.setVoucher(voucher);

				DecimalFormat decfmt = new DecimalFormat("##0.00");
				// 6.
				debitAmount = rs.getDouble(6);
				if (0.00 == debitAmount) {
					debitAmountStr = Constant.NULL_OF_ACCOUNT;
					accountDetail.setDebitAmountStr(debitAmountStr);
				} else {
					accountDetail.setDebitAmountStr(decfmt.format(debitAmount));
				}
				accountDetail.setDebitAmount(debitAmount);

				// 7.
				crebitAmount = rs.getDouble(7);
				if (0.0 == crebitAmount) {
					crebitAmountStr = Constant.NULL_OF_ACCOUNT;
					accountDetail.setCrebitAmountStr(crebitAmountStr);
				} else {
					accountDetail.setCrebitAmountStr(decfmt.format(crebitAmount));
				}
				accountDetail.setCrebitAmount(crebitAmount);

				// 8.
				balance = rs.getDouble(8);
				accountDetail.setBalance(balance);

				// 9.
				accountCaseFlag = rs.getString(9);
				accountDetail.setAccountCaseFlag(accountCaseFlag);

				// 10.
				sendName = rs.getString(10);
				accountDetail.setSendName(sendName);

				// 11.
				sendNo = rs.getString(11);
				accountDetail.setSendNo(sendNo);

				// 12.
				summary = rs.getString(12);
				accountDetail.setSummary(summary);

				comment = rs.getString(13);
				accountDetail.setComment(comment);

				serialNumber = rs.getString(14);
				accountDetail.setSerialNumber(serialNumber);

				enterpriseSerialNumber = rs.getString(15);
				accountDetail.setEnterpriseSerialNumber(enterpriseSerialNumber);

				myAccountNo = rs.getString(16);
				accountDetail.setMyAccountNo(myAccountNo);

				myAccountName = rs.getString(17);
				accountDetail.setMyAccountName(myAccountName);

				myAccountBank = rs.getString(18);
				accountDetail.setMyAccountBank(myAccountBank);

				myAccountStatus = rs.getString(19);
				accountDetail.setMyAccountStatus(myAccountStatus);

				list.add(accountDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
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
	public List<AccountDetail> queryByPage(int pageSize, int pageNow, QueryModel queryModel) {
		List<AccountDetail> list = new ArrayList<AccountDetail>();

		PreparedStatement pre = null;
		ResultSet rs = null;
		// 获取prepareStatement对象
		Connection con = null;

		String sql = "";
		try {
			con = DBUtil.getConnection();

			if (con != null && pageSize > 0 && pageNow > 0) {
				sql = "select * from accountdetail where 1=1 ";
				sql = genSQL(sql, queryModel);
				sql += " order by exchangedate limit " + (pageNow * pageSize - pageSize) + "," + pageSize;
				// System.out.println("sql: " + sql);
				pre = con.prepareStatement(sql);
				rs = pre.executeQuery();

				list = transferResultSetToList(rs);
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
	public List<AccountDetail> queryBySerialNumber(String serialNumber) {
		List<AccountDetail> list = new ArrayList<AccountDetail>();

		PreparedStatement pre = null;
		ResultSet rs = null;
		// 获取prepareStatement对象
		Connection con = null;

		String sql = "";
		try {
			con = DBUtil.getConnection();

			if (con != null) {
				sql = "select * from accountdetail where serialnumber=? ";
				pre = con.prepareStatement(sql);
				pre.setString(1, serialNumber);
				rs = pre.executeQuery();

				list = transferResultSetToList(rs);
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
	 * @param adId
	 * @return
	 */
	public AccountDetail queryByAdId(Integer adId) {
		AccountDetail accountDetail = new AccountDetail();

		PreparedStatement pre = null;
		ResultSet rs = null;
		// 获取prepareStatement对象
		Connection con = null;

		String sql = "";
		try {
			con = DBUtil.getConnection();

			if (con != null) {
				sql = "select * from accountdetail where adid=? ";
				pre = con.prepareStatement(sql);
				pre.setInt(1, adId);
				rs = pre.executeQuery();

				List<AccountDetail> list = transferResultSetToList(rs);
				int size = 0;
				if (null != list) {

					size = list.size();
					switch (size) {
					case 0:
						accountDetail = null;
						loggingDao.debug("queryByAdId Not Found: " + adId);
						System.out.println("queryByAdId Not Found: " + adId);
						break;

					case 1:
						accountDetail = list.get(0);
						break;

					default:
						accountDetail = null;
						loggingDao.error("queryByAdId ERROR: " + adId);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return accountDetail;
	}

	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public int addAccountDetail(AccountDetail accountDetail) {
		PreparedStatement pre = null;
		// 获取prepareStatement对象
		Connection con = null;
		String sql = "INSERT INTO accountdetail (" + "accountDate,exchangeDate,voucherType,voucher,"
				+ "debitAmount,crebitAmount,balance,accountCaseFlag," + "sendName,sendNo,summary,comment,serialNumber,"
				+ "enterpriseSerialNumber,myAccountNo,myAccountName,"
				+ "myAccountBank,myAccountStatus) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		int count = 0;
		try {
			con = DBUtil.getConnection();
			pre = con.prepareStatement(sql);

			pre.setDate(1, new Date(accountDetail.getAccountDate().getTime()));
			// java.sql.Date的精确保存问题
			pre.setTimestamp(2, new Timestamp(accountDetail.getExchangeDate().getTime())); // 指定时间
			pre.setString(3, accountDetail.getVoucherType());
			pre.setString(4, accountDetail.getVoucher());
			pre.setDouble(5, accountDetail.getDebitAmount());
			pre.setDouble(6, accountDetail.getCrebitAmount());
			pre.setDouble(7, accountDetail.getBalance());
			pre.setString(8, accountDetail.getAccountCaseFlag());
			pre.setString(9, accountDetail.getSendName());
			pre.setString(10, accountDetail.getSendNo());
			pre.setString(11, accountDetail.getSummary());
			pre.setString(12, accountDetail.getComment());
			pre.setString(13, accountDetail.getSerialNumber());
			pre.setString(14, accountDetail.getEnterpriseSerialNumber());
			pre.setString(15, accountDetail.getMyAccountNo());
			pre.setString(16, accountDetail.getMyAccountName());
			pre.setString(17, accountDetail.getMyAccountBank());
			pre.setString(18, accountDetail.getMyAccountStatus());
			count = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return count;
	}

	/**
	 * 得到当前页的记录
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public int updateAccountDetail(AccountDetail accountDetail) {
		PreparedStatement pre = null;
		// 获取prepareStatement对象
		Connection con = null;
		String sql = "UPDATE accountdetail " + "set accountDate = ? ,exchangeDate=? ,voucherType=? ,voucher=? ,"
				+ "debitAmount=? ,crebitAmount=? ,balance=? ,accountCaseFlag=? ,"
				+ "sendName=? ,sendNo=? ,summary=? ,comment=? ," + "enterpriseSerialNumber=? ,myAccountNo=? ,myAccountName=? ,"
				+ "myAccountBank=? ,myAccountStatus=? WHERE serialNumber=? ";

		int count = 0;
		try {
			con = DBUtil.getConnection();
			pre = con.prepareStatement(sql);

			pre.setDate(1, new Date(accountDetail.getAccountDate().getTime()));
			// java.sql.Date的精确保存问题
			pre.setTimestamp(2, new Timestamp(accountDetail.getExchangeDate().getTime())); // 指定时间
			pre.setString(3, accountDetail.getVoucherType());
			pre.setString(4, accountDetail.getVoucher());
			pre.setDouble(5, accountDetail.getDebitAmount());
			pre.setDouble(6, accountDetail.getCrebitAmount());
			pre.setDouble(7, accountDetail.getBalance());
			pre.setString(8, accountDetail.getAccountCaseFlag());
			pre.setString(9, accountDetail.getSendName());
			pre.setString(10, accountDetail.getSendNo());
			pre.setString(11, accountDetail.getSummary());
			pre.setString(12, accountDetail.getComment());
			pre.setString(13, accountDetail.getEnterpriseSerialNumber());
			pre.setString(14, accountDetail.getMyAccountNo());
			pre.setString(15, accountDetail.getMyAccountName());
			pre.setString(16, accountDetail.getMyAccountBank());
			pre.setString(17, accountDetail.getMyAccountStatus());
			pre.setString(18, accountDetail.getSerialNumber());
			loggingDao.debug(sql);
			count = pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return count;
	}

	public int makePersistent(AccountDetail accountDetail) {
		int result = 0;
		List<AccountDetail> list = queryBySerialNumber(accountDetail.getSerialNumber());

		if (null != list) {
			int size = list.size();
			switch (size) {
			case 0:
				result = addAccountDetail(accountDetail);
				loggingDao.debug("addAccountDetail");
				break;

			case 1:
				result = updateAccountDetail(accountDetail);
				loggingDao.debug("updateAccountDetail");
				break;

			default:
				result = -1;
				break;
			}
		}

		return result;
	}
}