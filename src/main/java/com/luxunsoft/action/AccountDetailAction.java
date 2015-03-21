package com.luxunsoft.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.luxunsoft.dao.LoggingDao;
import com.luxunsoft.model.AccountDetail;
import com.luxunsoft.model.QueryModel;
import com.luxunsoft.service.AccountDetailService;
import com.luxunsoft.util.Constant;
import com.opensymphony.xwork2.ActionSupport;

public class AccountDetailAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 897710336701153630L;

	private static String TAG = "AccountDetailAction";

	private LoggingDao loggingDao = new LoggingDao(this.getClass().getName());

	private List<AccountDetail> accountDetails;
	private int pageNow = 1; // 初始化为1,默认从第一页开始显示
	private int pageTotal = 1; // 总页数
	private int pageInput = 1; // 用户输入的页数

	// 本方账号
	private String myAccountNo;
	// 本方账号
	private String myAccountNo2;
	// 记账日期开始
	private String fromAccountDateStr;
	// 记账日期截止
	private String toAccountDateStr;
	// 最小金额
	private double minAmount;
	// 最大金额
	private double maxAmount;
	// 交易方向
	private int debitAndCrebitFlag;
	// 对方户名
	private String sendName;
	// 对方账号
	private String sendNo;
	// 摘要
	private String summary;
	// 前台选择的adId
	private String adIdListStr;
	// 前台选择的导出文件类型
	private String fileType;

	private AccountDetailService accountDetailService = new AccountDetailService();

	// 该属性是依赖注入的属性，可以在配置文件中动态指定该属性值
	private String fileName;

	private InputStream excelFile;

	// @SuppressWarnings("unchecked")
	public String execute() throws Exception {
		QueryModel queryModel = new QueryModel(myAccountNo2, fromAccountDateStr, toAccountDateStr, minAmount, maxAmount,
				debitAndCrebitFlag, sendName, sendNo, summary);
		loggingDao.debug("myAccountNo: " + queryModel);

		// String accountQuery,String accountQueryInput,String accountTypeQuery
		if (queryModel.isEmpty()) {
			pageTotal = accountDetailService.getTotalPageSize(Constant.PAGE_SIZE);

			// 处理用户点击【上一页】和【下一页】边界情况
			if (pageNow > Constant.PAGE_SIZE) {
				pageNow = Constant.PAGE_SIZE;
			} else if (pageNow < 1) {
				pageNow = 1;
			}

			accountDetails = accountDetailService.queryByPage(Constant.PAGE_SIZE, pageInput);
			pageNow = pageInput;
		} else {
			pageTotal = accountDetailService.getTotalPageSize(Constant.PAGE_SIZE, queryModel);

			// 处理用户点击【上一页】和【下一页】边界情况
			if (pageNow > Constant.PAGE_SIZE) {
				pageNow = Constant.PAGE_SIZE;
			} else if (pageNow < 1) {
				pageNow = 1;
			}

			accountDetails = accountDetailService.queryByPage(Constant.PAGE_SIZE, pageInput, queryModel);
			pageNow = pageInput;
		}

		return SUCCESS;
	}

	public String newPageDetail() throws Exception {
		QueryModel queryModel = new QueryModel(myAccountNo2, fromAccountDateStr, toAccountDateStr, minAmount, maxAmount,
				debitAndCrebitFlag, sendName, sendNo, summary);
		loggingDao.debug("myAccountNo: " + queryModel);

		// String accountQuery,String accountQueryInput,String accountTypeQuery
		if (queryModel.isEmpty()) {
			pageTotal = accountDetailService.getTotalPageSize(Constant.PAGE_SIZE);

			// 处理用户点击【上一页】和【下一页】边界情况
			if (pageNow > Constant.PAGE_SIZE) {
				pageNow = Constant.PAGE_SIZE;
			} else if (pageNow < 1) {
				pageNow = 1;
			}

			accountDetails = accountDetailService.queryByPage(Constant.PAGE_SIZE, pageNow);
			pageInput = pageNow;
		} else {
			pageTotal = accountDetailService.getTotalPageSize(Constant.PAGE_SIZE, queryModel);

			// 处理用户点击【上一页】和【下一页】边界情况
			if (pageNow > Constant.PAGE_SIZE) {
				pageNow = Constant.PAGE_SIZE;
			} else if (pageNow < 1) {
				pageNow = 1;
			}

			accountDetails = accountDetailService.queryByPage(Constant.PAGE_SIZE, pageNow, queryModel);
			pageInput = pageNow;
		}

		return SUCCESS;
	}

	public String exportExcelFile() {
		accountDetailService = new AccountDetailService();

		List<Integer> adIdList = new ArrayList<Integer>();

		String[] adIdArray = this.getAdIdListStr().split(":");
		for (int i = 0; i < adIdArray.length; i++) {
			if (null != adIdArray[i] && !"".equals(adIdArray[i].trim())) {
				adIdList.add(Integer.parseInt(adIdArray[i]));
			}
		}

		List<AccountDetail> list = accountDetailService.queryByAdIdList(adIdList);
		String myAccountNo = "学生信息";
		if (null != list && 1 < list.size()) {
			AccountDetail accountDetail = list.get(0);
			myAccountNo = accountDetail.getMyAccountNo();

			// 设置导出文件的名称
			this.setFileName(myAccountNo + ".xls");
		}
		Workbook workbook = null;

		workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet(myAccountNo);
		loggingDao.debug(TAG, "myAccountNo: " + myAccountNo);
		Row row = sheet.createRow(0);
		//

		row.createCell(0).setCellValue("记账日期");
		row.createCell(1).setCellValue("交易时间");
		row.createCell(2).setCellValue("凭证种类");
		row.createCell(3).setCellValue("凭证号");
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

		for (int i = 1; i <= list.size(); i++) {
			AccountDetail accountDetail = list.get(i - 1);
			row = sheet.createRow(i);
			row.createCell(0).setCellValue(accountDetail.getAccountDateStr());
			row.createCell(1).setCellValue(accountDetail.getExchangeDateStr());
			row.createCell(2).setCellValue(accountDetail.getVoucherType());
			row.createCell(3).setCellValue(accountDetail.getVoucher());

			loggingDao.debug(TAG, "accountDetail: " + accountDetail);
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			workbook.write(baos);

			byte[] aa = baos.toByteArray();
			excelFile = new ByteArrayInputStream(aa, 0, aa.length);

			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
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

	public AccountDetailService getAccountDetailService() {
		return accountDetailService;
	}

	public void setAccountDetailService(AccountDetailService accountDetailService) {
		this.accountDetailService = accountDetailService;
	}

	public List<AccountDetail> getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(List<AccountDetail> accountDetails) {
		this.accountDetails = accountDetails;
	}

	public String getMyAccountNo() {
		return myAccountNo;
	}

	public void setMyAccountNo(String myAccountNo) {
		this.myAccountNo = myAccountNo;
	}

	public String getFromAccountDateStr() {
		return fromAccountDateStr;
	}

	public void setFromAccountDateStr(String fromAccountDateStr) {
		this.fromAccountDateStr = fromAccountDateStr;
	}

	public String getToAccountDateStr() {
		return toAccountDateStr;
	}

	public void setToAccountDateStr(String toAccountDateStr) {
		this.toAccountDateStr = toAccountDateStr;
	}

	public double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public int getDebitAndCrebitFlag() {
		return debitAndCrebitFlag;
	}

	public void setDebitAndCrebitFlag(int debitAndCrebitFlag) {
		this.debitAndCrebitFlag = debitAndCrebitFlag;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendNo() {
		return sendNo;
	}

	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getMyAccountNo2() {
		return myAccountNo2;
	}

	public void setMyAccountNo2(String myAccountNo2) {
		this.myAccountNo2 = myAccountNo2;
	}

	public String getAdIdListStr() {
		return adIdListStr;
	}

	public void setAdIdListStr(String adIdListStr) {
		this.adIdListStr = adIdListStr;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

}