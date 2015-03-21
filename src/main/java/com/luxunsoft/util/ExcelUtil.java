package com.luxunsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.luxunsoft.model.DataSet;

public class ExcelUtil {
	private static String EXCEL_2003 = ".xls";
	private static String EXCEL_2007 = ".xlsx";

	public static void readExcelJXL() {

	}

	/**
	 * 通过POI方式读取Excel
	 * 
	 * @param excelFile
	 */
	public static DataSet readExcelPOI(String filePath, Integer cons) throws Exception {
		File excelFile = new File(filePath);
		if (excelFile != null) {
			String fileName = excelFile.getName();
			fileName = fileName.toLowerCase();
			if (fileName.toLowerCase().endsWith(EXCEL_2003)) {
				DataSet dataSet = readExcelPOI2003(excelFile, cons);
				return dataSet;
			}
			if (fileName.toLowerCase().endsWith(EXCEL_2007)) {
				DataSet dataSet = readExcelPOI2007(excelFile, cons);
				return dataSet;
			}
		}
		return null;
	}

	/**
	 * 通过POI方式读取Excel
	 * 
	 * @param excelFile
	 */
	public static String readExcelPOI(String filePath, Integer rowNumber, Integer cellNumber) throws Exception {
		File excelFile = new File(filePath);
		String cellValue = "";
		if (excelFile != null) {
			String fileName = excelFile.getName();
			fileName = fileName.toLowerCase();
			if (fileName.toLowerCase().endsWith(EXCEL_2003)) {
				cellValue = readExcelPOI2003(excelFile, rowNumber, cellNumber);
				return cellValue;
			}
			if (fileName.toLowerCase().endsWith(EXCEL_2007)) {
				cellValue = readExcelPOI2007(excelFile, rowNumber, cellNumber);
				return cellValue;
			}
		}
		return null;
	}

	/**
	 * 读取Excel2003的表单
	 * 
	 * @param excelFile
	 * @return
	 * @throws Exception
	 */
	private static DataSet readExcelPOI2003(File excelFile, Integer rCons) throws Exception {
		List<String[]> datasList = new ArrayList<String[]>();
		Set<String> colsSet = new HashSet<String>();
		InputStream input = new FileInputStream(excelFile);
		HSSFWorkbook workBook = new HSSFWorkbook(input);
		// 获取Excel的sheet数量
		Integer sheetNum = workBook.getNumberOfSheets();
		// 循环Sheet表单
		for (int i = 0; i < sheetNum; i++) {
			HSSFSheet sheet = workBook.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 获取Sheet里面的Row数量
			Integer rowNum = sheet.getLastRowNum() + 1;
			for (int j = 0; j < rowNum; j++) {
				if (j > rCons) {
					System.out.println("===========");
					HSSFRow row = sheet.getRow(j);
					if (row == null) {
						continue;
					}

					Integer cellNum = row.getLastCellNum() + 1;
					String[] datas = new String[cellNum];
					for (int k = 0; k < cellNum; k++) {
						HSSFCell cell = row.getCell(k);
						if (cell == null) {
							continue;
						}
						if (cell != null) {
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							String cellValue = "";
							int cellValueType = cell.getCellType();
							if (cellValueType == HSSFCell.CELL_TYPE_STRING) {
								cellValue = cell.getStringCellValue();
							}
							if (cellValueType == HSSFCell.CELL_TYPE_NUMERIC) {
								Double number = cell.getNumericCellValue();

								System.out.println("字符串+++==========" + number.intValue());
								cellValue = cell.getNumericCellValue() + "";
							}

							if (rCons == k) {
								colsSet.add(cellValue);
							}

							System.out.println(cellValue);
							datas[k] = cellValue;
						}
					}
					datasList.add(datas);
				}
			}
		}
		DataSet dataSet = new DataSet(null, null, datasList, colsSet);
		return dataSet;
	}

	/**
	 * 读取Excel2003的表单
	 * 
	 * @param excelFile
	 * @return
	 * @throws Exception
	 */
	private static String readExcelPOI2003(File excelFile, Integer rowNumber, Integer cellNumber) throws Exception {
		InputStream input = new FileInputStream(excelFile);
		HSSFWorkbook workBook = new HSSFWorkbook(input);
		String cellValue = "";
		// 循环Sheet表单
		HSSFSheet sheet = workBook.getSheetAt(0);
		if (sheet == null) {
			return "";
		}
		HSSFRow rowR = sheet.getRow(rowNumber);
		HSSFCell cellC = rowR.getCell(cellNumber);
		if (cellC == null) {
			return "";
		}
		if (cellC != null) {
			cellC.setCellType(HSSFCell.CELL_TYPE_STRING);

			int cellValueType = cellC.getCellType();
			if (cellValueType == HSSFCell.CELL_TYPE_STRING) {
				cellValue = cellC.getStringCellValue();
			}
			if (cellValueType == HSSFCell.CELL_TYPE_NUMERIC) {
				Double number = cellC.getNumericCellValue();

				System.out.println("字符串+++==========" + number.intValue());
				cellValue = cellC.getNumericCellValue() + "";
			}

			System.out.println(cellValue);
		}

		return cellValue;
	}

	/**
	 * 读取Excel2007的表单
	 * 
	 * @param excelFile
	 * @return
	 * @throws Exception
	 */
	private static DataSet readExcelPOI2007(File excelFile, Integer rCons) throws Exception {
		List<String[]> datasList = new ArrayList<String[]>();
		Set<String> cosSet = new HashSet<String>();
		InputStream input = new FileInputStream(excelFile);
		XSSFWorkbook workBook = new XSSFWorkbook(input);
		// 获取Sheet数量
		Integer sheetNum = workBook.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			XSSFSheet sheet = workBook.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 获取行值
			Integer rowNum = sheet.getLastRowNum() + 1;
			for (int j = 0; j < rowNum; j++) {
				if (j > rCons) {
					System.out.println("=============");
					XSSFRow row = sheet.getRow(j);
					if (row == null) {
						continue;
					}
					Integer cellNum = row.getLastCellNum() + 1;
					String[] datas = new String[cellNum];
					for (int k = 0; k < cellNum; k++) {
						XSSFCell cell = row.getCell(k);
						if (cell == null) {
							continue;
						}
						if (cell != null) {
							cell.setCellType(XSSFCell.CELL_TYPE_STRING);
							String cellValue = "";
							int cellValueType = cell.getCellType();
							if (cellValueType == HSSFCell.CELL_TYPE_STRING) {
								cellValue = cell.getStringCellValue();
							}
							if (cellValueType == HSSFCell.CELL_TYPE_NUMERIC) {
								Double number = cell.getNumericCellValue();
								System.out.println("字符串+++==========" + number.toString());
								cellValue = cell.getNumericCellValue() + "";
							}
							System.out.println(cellValue);
							if (rCons == k) {
								cosSet.add(cellValue);
							}
							datas[k] = cellValue;
						}
					}
					datasList.add(datas);
				}
			}
		}
		DataSet dataSet = new DataSet(null, null, datasList, cosSet);
		return dataSet;
	}

	/**
	 * 读取Excel2007的表单
	 * 
	 * @param excelFile
	 * @return
	 * @throws Exception
	 */
	private static String readExcelPOI2007(File excelFile, Integer rowNumber, Integer cellNumber) throws Exception {
		InputStream input = new FileInputStream(excelFile);
		XSSFWorkbook workBook = new XSSFWorkbook(input);
		String cellValue = "";
		XSSFSheet sheet = workBook.getSheetAt(0);
		if (sheet == null) {
			return cellValue;
		}
		XSSFRow row = sheet.getRow(rowNumber);
		XSSFCell cell = row.getCell(cellNumber);
		if (cell == null) {
			return cellValue;
		}
		if (cell != null) {
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);

			int cellValueType = cell.getCellType();
			if (cellValueType == HSSFCell.CELL_TYPE_STRING) {
				cellValue = cell.getStringCellValue();
			}
			if (cellValueType == HSSFCell.CELL_TYPE_NUMERIC) {
				Double number = cell.getNumericCellValue();
				System.out.println("字符串+++==========" + number.toString());
				cellValue = cell.getNumericCellValue() + "";
			}
			System.out.println(cellValue);

		}

		return cellValue;
	}

	public static void main(String[] args) {
		try {
			// DataSet dataSet = readExcelPOI("D:\\部门员工资料.xls", 0);
			// 得到classes的路径
			String url = ExcelUtil.class.getClassLoader().getResource("").getPath();
			System.out.println(url);
			// 然后再加上文件的路径
			String fileName = url + "/data/42001237008050007480.xls";

			// Test 01
			// DataSet dataSet = readExcelPOI(fileName, 0);
			// System.out.println("================================");
			// Set<String> datas = dataSet.getConStrctSet();
			// String[] datastr = new String[datas.size()];
			// datastr = datas.toArray(datastr);
			// for (int i = 0; i < datastr.length; i++) {
			// System.out.println(datastr[i]);
			// }

			String cellValue = readExcelPOI(fileName, 4, 1);
			System.out.println("cellValue: " + cellValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
