package com.luxunsoft.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getDownloadFile() {
		// 创建Excel
		createExcel();
		// 获取文件的输入流的时候不需要获取绝对路径，因为这个目录在当前的工程下面
		// 所以直接使用相对路径就可以了
		return ServletActionContext.getServletContext().getResourceAsStream("/upload/" + fileName);

	}

	/**
	 * 创建Excel文件
	 */
	public void createExcel() {
		// 创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle setBorder1 = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		setBorder1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		setBorder1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		setBorder1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		setBorder1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		setBorder1.setDataFormat(format.getFormat("@"));
		setBorder1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		setBorder1.setFillForegroundColor(HSSFColor.RED.index);
		HSSFCellStyle setBorder2 = wb.createCellStyle();
		setBorder2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		setBorder2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		setBorder2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		setBorder2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		setBorder2.setDataFormat(format.getFormat("@"));

		/*----------------------创建sheet1开始----------------------------*/
		HSSFSheet sheet1 = wb.createSheet("表1");
		HSSFRow row1 = null;
		row1 = sheet1.createRow(0);
		HSSFRow row2 = null;
		row2 = sheet1.createRow(1);

		HSSFCell cell1 = row1.createCell(1);
		cell1.setCellStyle(setBorder1);
		cell1.setCellValue("红色为必填内容！");
		HSSFCell cell2 = row2.createCell(1);
		cell2.setCellStyle(setBorder2);
		cell2.setCellValue("有内容了!");

		/*----------------------创建sheet1结束----------------------------*/

		/*----------------------创建sheet2开始----------------------------*/
		HSSFSheet sheet2 = wb.createSheet("表2");
		HSSFRow row21 = null;
		row21 = sheet2.createRow(0);
		HSSFRow row22 = null;
		row22 = sheet2.createRow(1);

		HSSFCell cell21 = row21.createCell(1);
		cell21.setCellStyle(setBorder1);
		cell21.setCellValue("红色为必填内容！");
		HSSFCell cell22 = row22.createCell(1);
		cell22.setCellStyle(setBorder2);
		cell22.setCellValue("有内容了!");

		/*----------------------创建sheet2结束----------------------------*/

		try {
			// 写入文件的时候需要获取目录的绝对路径
			// 但是在获取文件的时候就不需要获取它的绝对路径了
			String path = ServletActionContext.getServletContext().getRealPath("/");
			FileOutputStream os = new FileOutputStream(path + "/upload/test.xls");
			try {
				wb.write(os);
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
