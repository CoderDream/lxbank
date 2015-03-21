package com.luxunsoft.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.luxunsoft.dao.LoggingDao;
import com.luxunsoft.service.UploadExcelService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sina.sae.storage.SaeStorage;
import com.sina.sae.util.SaeUserInfo;

public class UploadExcelAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoggingDao loggingDao = new LoggingDao(this.getClass().getName());

	private static String TAG = "UploadExcelAction";

	private UploadExcelService uploadExcelService = new UploadExcelService();

	/**
	 * 这个名字与页面的file控件的name一致 <s:file name="uploadFile">
	 * 
	 */
	private File uploadFile;

	/**
	 * 这个属性名为文件的name+FileName，即uploadFile+FileName=uploadFileFileName
	 * 
	 */
	private String uploadFileFileName;

	private String message;

	@Override
	public String execute() throws Exception {

		loggingDao.debug(TAG, "execute begin");
		// 上传物理路径
		// String realPath = ServletActionContext.getServletContext().getRealPath("/upload");

		loggingDao.debug(TAG, "execute begin");
		// 使用SaeUserInfo拿到改请求可写的路径
		String realPath = SaeUserInfo.getSaeTmpPath() + "/";
		loggingDao.debug(TAG, "realPath: " + realPath);

		// 生成上传的File对象
		File target = new File(realPath, uploadFileFileName);

		loggingDao.debug(TAG, "Target: " + target.getName());
		loggingDao.debug(TAG, "UploadFile: " + uploadFile.getName());
		// 复制File对象，从而实现上传文件
		FileUtils.copyFile(uploadFile, target);

		SaeStorage ss = new SaeStorage();
		// 使用upload方法上传到域为image下
		ss.upload("lxbank", realPath + target.getName(), target.getName());
		loggingDao.debug(TAG, "upload realPath + target.getName(): " + realPath + target.getName());

		// http://lxbank-lxbank.stor.sinaapp.com/redirect_tmp.dat
		ServletActionContext.getRequest().setAttribute("storeDir",
				"http://lxbank-lxbank.stor.sinaapp.com/" + target.getName());
		int count = 0;
		String messageStr = "一共有" + count + "条数据上传成功!";

		this.setMessage(messageStr);
		// System.out.println("================================");
		// Set<String> datas = dataSet.getConStrctSet();
		// String[] datastr = new String[datas.size()];
		// datastr = datas.toArray(datastr);
		// for (int i = 0; i < datastr.length; i++) {
		// System.out.println(datastr[i]);
		// loggingDao.debug("UploadExcelAction", datastr[i]);
		// }

		return SUCCESS;
	}

	public String uploadExcelToSAE() throws Exception {
		loggingDao.debug("UploadExcelAction", "uploadExcelToSAE beging #");
		PrintWriter out = null;
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
			loggingDao.debug("UploadExcelAction", "request: " + request.toString());
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
			loggingDao.debug("UploadExcelAction", "response: " + response.toString());

			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			loggingDao.debug("UploadExcelAction", "PrintWriter: " + out.toString());

			// 使用SaeUserInfo拿到改请求可写的路径
			String realPath = SaeUserInfo.getSaeTmpPath() + "/";
			loggingDao.debug("UploadExcelAction", "realPath: " + realPath);

			// 使用common-upload上传文件至这个路径中
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			loggingDao.debug("UploadExcelAction", "isMultipart: " + isMultipart);
			if (!isMultipart) {
				return INPUT;
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(1024 * 1024);
			List<FileItem> items = null;
			items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					File fullFile = new File(item.getName());
					File uploadFile = new File(realPath, fullFile.getName());
					item.write(uploadFile);
					loggingDao.debug("UploadExcelAction", "uploadFile Complete: " + fullFile.getName());

					// 上传完毕后 使用SaeStorage往storage里面写

					SaeStorage ss = new SaeStorage();
					// 使用upload方法上传到域为image下
					loggingDao.debug("" + realPath + fullFile.getName() + "; fullFile.getName(): " + fullFile.getName());
					ss.upload("lxbank", realPath + fullFile.getName(), fullFile.getName());

					out.print("upload file:" + realPath + fullFile.getName() + "</br>");
					this.setMessage("upload file:" + realPath + fullFile.getName() + "</br>");
				}
			}
			out.print("upload end...");
		} catch (Exception e) {
			loggingDao.error(e.getMessage());
			out.print("ERROR:" + e.getMessage() + "</br>");
		} finally {
			out.flush();
			out.close();
		}

		return SUCCESS;
	}

	public String readSaeStorage() throws Exception {
		// 读storage中域名为domain，文件名为test.txt的文件
		FileInputStream inputStream = new FileInputStream("saestor://lxbank/test.txt");
		Reader reader = new InputStreamReader(inputStream);
		StringBuilder filetext = new StringBuilder();
		int tempchar;
		while ((tempchar = reader.read()) != -1) {
			filetext.append((char) tempchar);
			loggingDao.debug("test.txt content: " + filetext.toString());
		}
		reader.close();
		String messageStr = "读数据成功!";
		this.setMessage(messageStr);
		loggingDao.debug(messageStr);

		return SUCCESS;
	}

	public String writeSaeStorage() throws Exception {

		// 向域名为domain写入一个test.txt文件，文件内容为“测试内容”
		FileOutputStream outputStream = new FileOutputStream("saestor://lxbank/test.txt");
		Writer writer = new OutputStreamWriter(outputStream);
		writer.write("测试内容" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		writer.close();

		String messageStr = "写数据成功!";
		this.setMessage(messageStr);
		loggingDao.debug(messageStr);

		return SUCCESS;
	}

	/**
	 * 把指定的文件复制到指定的位置
	 * 
	 * @param src
	 * @param dest
	 */
	public void copyFile(File src, File dest) {
		try {
			InputStream fis = new FileInputStream(src);
			OutputStream fos = new FileOutputStream(dest);

			byte[] buf = new byte[4 * 1024];

			while (fis.read(buf) != -1) {
				fos.write(buf);
			}

			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LoggingDao getLoggingDao() {
		return loggingDao;
	}

	public void setLoggingDao(LoggingDao loggingDao) {
		this.loggingDao = loggingDao;
	}

	public UploadExcelService getUploadExcelService() {
		return uploadExcelService;
	}

	public void setUploadExcelService(UploadExcelService uploadExcelService) {
		this.uploadExcelService = uploadExcelService;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
}