package com.luxunsoft.action;

import java.util.List;

import com.luxunsoft.dao.PageDao;
import com.luxunsoft.model.Student;
import com.opensymphony.xwork2.ActionSupport;

public class ShowAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 897710336701153630L;

	private List<Student> students;
	private int pageNow = 1; // 初始化为1,默认从第一页开始显示
	private int pageSize = 5; // 每页显示5条记录
	private int pageTotal = 1; // 总页数
	private int pageInput; // 用户输入的页数

	private PageDao pageDao = new PageDao();

	public String execute() throws Exception {
		pageTotal = pageDao.getTotalPageSize(pageSize);
		if (1 != pageInput && 0 != pageInput && pageInput <= pageTotal) {
			students = pageDao.queryByPage(pageSize, pageInput);
		} else {
			students = pageDao.queryByPage(pageSize, pageNow);
			pageInput = pageNow;
		}
		return SUCCESS;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageInput() {
		return pageInput;
	}

	public void setPageInput(int pageInput) {
		this.pageInput = pageInput;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

}