<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>中国建设银行 企业网上银行</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
</head>
<body>
	<div id="top">
		<s:include value="header.jsp" />
		<div id="mainbox">
			<span id="span_main_top"><img src="images/main_top.png" /></span> <span id="span_main_body"><img
				src="images/main_body.png" /></span>
			<s:a href="account.action">
				<span id="span_btn_enter"><img src="images/btn_enter.png" /></span>
			</s:a>
		</div>

		<s:include value="footer.jsp" />

	</div>
</body>
</html>