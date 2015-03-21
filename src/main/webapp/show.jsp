<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>单位网上银行</title>
</head>
<body>
	账户查询&gt;账户信息查询
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


		<s:url id="url_next" value="show.action">
			<s:param name="pageNow" value="pageNow+1"></s:param>
		</s:url>


		<form action="show.action" method="post">
			第
			<s:property value="pageNow" />
			页/共
			<s:property value="pageTotal" />
			页[
			<s:a href="%{url_pre}">上一页</s:a>
			<s:property value="pageNow" />
			<s:a href="%{url_next}">下一页</s:a>
			]
			<s:textfield id="pageInput" name="pageInput" size="1" />
			<input type="submit" value="确定">
		</form>
	</div>
</body>
</html>