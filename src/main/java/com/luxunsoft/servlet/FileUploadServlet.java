package com.luxunsoft.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sina.sae.storage.SaeStorage;
import com.sina.sae.util.SaeUserInfo;

/**
 * 
 * @author Administrator 文件上传 具体步骤： 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 2） 利用 request 获取 真实路径 ，供临时文件存储，和 最终文件存储
 *         ，这两个存储位置可不同，也可相同 3）对 DiskFileItemFactory 对象设置一些 属性 4）高水平的API文件上传处理 ServletFileUpload upload = new
 *         ServletFileUpload(factory); 目的是调用 parseRequest（request）方法 获得 FileItem 集合list ，
 * 
 *         5）在 FileItem 对象中 获取信息， 遍历， 判断 表单提交过来的信息 是否是 普通文本信息 另做处理 6） 第一种. 用第三方 提供的 item.write( new File(path,filename)
 *         ); 直接写到磁盘上 第二种. 手动处理
 * 
 */
public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5315855346417238044L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 使用SaeUserInfo拿到改请求可写的路径
		String realPath = SaeUserInfo.getSaeTmpPath() + "/";
		try {
			// 使用common-upload上传文件至这个路径中
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart)
				return;
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
					// 上传完毕后 使用SaeStorage往storage里面写
					SaeStorage ss = new SaeStorage();
					// 使用upload方法上传到域为image下
					ss.upload("lxbank", realPath + fullFile.getName(), fullFile.getName());

					out.print("upload file:" + realPath + fullFile.getName() + "</br>");
				}
			}
			out.print("upload end...");
		} catch (Exception e) {
			out.print("ERROR:" + e.getMessage() + "</br>");
		} finally {
			out.flush();
			out.close();
		}
	}

}
