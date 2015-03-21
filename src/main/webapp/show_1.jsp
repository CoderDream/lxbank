<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Struts2实现分页显示</title>
</head>
<body>
	<div align="center">
		<table border="1">
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>地址</th>
				<th>电话</th>
			</tr>
			<s:iterator value="students">
				<tr>
					<td><s:property value="id" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="address" /></td>
					<td><s:property value="phone" /></td>
				</tr>
			</s:iterator>
		</table>
		<s:url id="url_pre" value="show.action">
			<s:param name="pageNow" value="pageNow-1"></s:param>
		</s:url>
		<s:a href="%{url_pre}">上一页</s:a>

		<s:url id="url_next" value="show.action">
			<s:param name="pageNow" value="pageNow+1"></s:param>
		</s:url>
		<s:a href="%{url_next}">下一页</s:a>

		<s:iterator value="students" status="status">
			<s:url id="url" value="show.action">
				<s:param name="pageNow" value="pageNow" />
			</s:url>
		</s:iterator>
	</div>
</body>
</html>