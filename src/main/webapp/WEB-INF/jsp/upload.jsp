<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'upload.jsp' starting page</title>
</head>
<body>
	<s:form action="uploadFile" method="post" enctype="multipart/form-data">
		<s:file name="upload"></s:file>
		<s:submit value="上传"></s:submit>
	</s:form>
</body>
</html>