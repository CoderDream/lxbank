package com.luxunsoft.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.luxunsoft.model.Student;

public class PageDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/page";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";

	private Student student;

	// 数据库连接
	public synchronized Connection getConnection() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	/**
	 * 得到总页数
	 * 
	 * @param pageSize
	 * @return
	 */
	public Integer getTotalPageSize(int pageSize) {
		int size = 0;
		try {
			if (this.getConnection() != null && pageSize > 0) {
				pstmt = this.getConnection().prepareStatement("select count(*) from student;");
				rs = pstmt.executeQuery();

				while (rs.next()) {
					size = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return size / pageSize + 1;
	}

	// 分页查询
	public List<Student> queryByPage(int pageSize, int pageNow) {
		List<Student> list = new ArrayList<Student>();
		try {
			if (this.getConnection() != null && pageSize > 0 && pageNow > 0) {
				pstmt = this.getConnection().prepareStatement(
						"select * from student order by id limit " + (pageNow * pageSize - pageSize) + "," + pageSize);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					student = new Student();
					student.setId(rs.getInt(1));
					student.setName(rs.getString(2));
					student.setAddress(rs.getString(3));
					student.setPhone(rs.getString(4));
					list.add(student);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}