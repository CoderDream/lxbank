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
import com.luxunsoft.service.AccountDetailService;
import com.opensymphony.xwork2.ActionSupport;

public class ExportFileAction extends ActionSupport {

	private LoggingDao loggingDao = new LoggingDao(this.getClass().getName());

	private AccountDetailService accountDetailService;

	private static String TAG = "ExportFileAction";
	/**
	 * 
	 */
	private static final long serialVersionUID = 2854861486245034134L;

	// 该属性是依赖注入的属性，可以在配置文件中动态指定该属性值
	private String fileName;

	private InputStream excelFile;

	// ++++++++++++++++++++++++++
	/*
	 * 数据库只有3列分别是id,name,sex 查询后绑定到对象后 将对象赋值给list 然后转成对象进行列操作
	 */
	public String exportExcelFile() {
		accountDetailService = new AccountDetailService();

		List<Integer> adIdList = new ArrayList<Integer>();
		adIdList.add(6);
		adIdList.add(7);
		adIdList.add(8);
		adIdList.add(9);
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

}