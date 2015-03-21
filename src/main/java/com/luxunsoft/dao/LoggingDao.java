package com.luxunsoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.luxunsoft.db.DBUtil;
import com.luxunsoft.model.Logging;

public class LoggingDao {

	private String location;

	public static String TAG = "LoggingDao";

	private Logger logger = Logger.getLogger(LoggingDao.class);

	public LoggingDao() {
	}

	public LoggingDao(String location) {
		this.location = location;
	}

	// add
	public int add(Logging logging) {

		logger.debug(TAG + "###0###");
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			logger.debug(TAG + con);
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, logging.getLogDate());
			ps.setString(2, logging.getLogLevel());
			ps.setString(3, logging.getLocation());
			ps.setString(4, logging.getMessage());
			count = ps.executeUpdate();
			logger.debug(TAG + "count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return count;
	}

	public int debug(String message) {
		if (!logger.isDebugEnabled()) {
			return 0;
		}

		logger.debug(TAG + "###0###");
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement pre = null;
		try {
			con = DBUtil.getConnection();
			logger.debug(TAG + con);
			pre = con.prepareStatement(sql);
			pre.setTimestamp(1, new Timestamp(Calendar.getInstance().getTime().getTime()));
			pre.setString(2, "DEBUG");
			pre.setString(3, location);
			pre.setString(4, message);
			count = pre.executeUpdate();
			logger.debug(TAG + "count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return count;
	}

	public int debug(String location, String message) {
		if (!Logger.getLogger(LoggingDao.class).isDebugEnabled()) {
			return 0;
		}
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, new Timestamp(Calendar.getInstance().getTime().getTime()));
			ps.setString(2, "DEBUG");
			ps.setString(3, location);
			ps.setString(4, message);
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return count;
	}

	public int error(String message) {
		logger.debug(TAG + "###0###");
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement pre = null;
		try {
			con = DBUtil.getConnection();
			logger.debug(TAG + con);
			pre = con.prepareStatement(sql);
			pre.setTimestamp(1, new Timestamp(Calendar.getInstance().getTime().getTime()));
			pre.setString(2, "ERROR");
			pre.setString(3, location);
			pre.setString(4, message);
			count = pre.executeUpdate();
			logger.debug(TAG + "count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return count;
	}

	public int error(String location, String message) {
		String sql = "INSERT INTO logging (log_date, log_level, location, message) VALUES (?,?,?,?)";

		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, new Timestamp(Calendar.getInstance().getTime().getTime()));
			ps.setString(2, "ERROR");
			ps.setString(3, location);
			ps.setString(4, message);
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return count;
	}

	PreparedStatement pre;
	ResultSet rs;

	/**
	 * @author help
	 * 
	 *         显示所有记录
	 * @return
	 */
	public List<Logging> query() {

		String sql = "select * from logging order by id";
		List<Logging> list = new ArrayList<Logging>();

		// 获取prepareStatement对象
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();

			while (rs.next()) {
				Logging logging = new Logging();
				logging.setId(rs.getInt("id"));
				logging.setLogDate(rs.getTimestamp("log_date"));
				logging.setLogLevel(rs.getString("log_level"));
				logging.setLocation(rs.getString("location"));
				logging.setMessage(rs.getString("message"));
				list.add(logging);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pre);
			DBUtil.close(con);
		}
		return list;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}