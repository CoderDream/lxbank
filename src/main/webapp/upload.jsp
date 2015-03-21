<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>上传页面</title>
<script type="text/javascript">
	function readSaeStorage() {
		//获取该页面中的第一个表单元素
		targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "readSaeStorage.action";
		//提交表单
		targetForm.submit();
	}

	function writeSaeStorage() {
		//获取该页面中的第一个表单元素
		targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "writeSaeStorage.action";
		//提交表单
		targetForm.submit();
	}

	function uploadExcelToSAE() {
		alert('uploadExcelToSAE');
		//获取该页面中的第一个表单元素
		targetForm = document.forms[1];
		//动态修改目标表单的action属性
		targetForm.action = "uploadExcelToSAE.action";
		//提交表单
		targetForm.submit();
	}
</script>
</head>
<body>
	<s:form action="uploadFile" method="post" enctype="multipart/form-data">
		<s:file name="uploadFile"></s:file>
		<br />
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>