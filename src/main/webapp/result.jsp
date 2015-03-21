<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
	您上传的文件名为：
	<s:property value="uploadFileFileName" />
	，存放目录：
	<s:property value="#request.storeDir" />
	<br />
	<br />
	<!--下载上下文路径-->
	<s:a href="%{#request.get('javax.servlet.forward.context_path')}/upload/%{uploadFileFileName}">点击这里下载</s:a>
	<br />
</body>
</html>