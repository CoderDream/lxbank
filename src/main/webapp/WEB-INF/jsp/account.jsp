<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国建设银行 企业网上银行</title>
<link type="text/css" rel="stylesheet" href="css/account.css" />
<!--   引入jQuery -->
<script type="text/javascript">
	function prePage() {
		var pageNow = document.getElementById("pageNow");
		pageNow.value = parseInt(pageNow.value) - 1;
		//获取该页面中的第一个表单元素
		targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "newPage.action";
		//提交表单
		targetForm.submit();
	}

	function nextPage() {
		var pageNow = document.getElementById("pageNow");
		pageNow.value = parseInt(pageNow.value) + 1;
		//alert(pageNow.value);
		//获取该页面中的第一个表单元素
		targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "newPage.action";
		//提交表单
		targetForm.submit();
	}
</script>
</head>
<body>
	<div id="top">
		<s:include value="header.jsp" />
		<s:form id="myForm" action="account.action" method="post" theme="simple">

			<s:hidden id="pageNow" name="pageNow" />
			<s:hidden id="accountQuery" value="accountQuery" />
			<s:hidden id="accountTypeQuery" value="accountTypeQuery" />
			<div id="mainbox">
				<div id="part">
					<span id="span_main_top"><img src="images/sub_menu.png" /></span>
				</div>
				<div id="part_left">
					<span id="span_label1">&nbsp;&nbsp;账户查询&nbsp;&gt;&nbsp;账户信息查询</span>
				</div>
				<div id="part">
					<span id="span_main_top"><img src="images/sub_menu_2.png" /></span>
				</div>
				<div id="part_left2">
					<span id="span_label2">&nbsp;&nbsp;&nbsp;账户查询：&nbsp;&nbsp;</span> <span id="span_label2"><s:select
							id="accountQuery" cssClass="selectStyle" cssStyle="selectStyle" label="账户查询：" name="accountQuery"
							list="#{1:'账户名称',2:'账号' }"></s:select> </span> <span id="span_label2"><s:textfield cssClass="selectStyle"
							id="accountQueryInput" name="accountQueryInput" value="%{accountQueryInput}" size="22"
							style="vertical-align: middle;" /></span> <span id="span_label2">&nbsp;账户类别：&nbsp;&nbsp; </span> <span id="span_label2"><s:select
							cssClass="selectStyle" cssStyle="selectStyle" label="账户类别：" id="accountTypeQuery" name="accountTypeQuery"
							list="#{1:'全部' }"></s:select> </span> <span id="span_submit"><input type="image" src="images/btn_submit_1.png"
						onclick="this.form.submit();" style="vertical-align: middle;" /></span>
				</div>
				<div id="span_main_body">
					<table class="table1">
						<tr>
							<th class="first_child_th"></th>
							<th class="th1">账户名称</th>
							<th class="th1">账号</th>
							<th class="th1">币种</th>
							<th class="th1">开户机构</th>
							<th class="th1">账户别名</th>
							<th class="last_child_th">账户类型</th>
						</tr>
						<s:iterator value="accounts">
							<tr>
								<td class="first_child_td"><s:checkbox name="myAccountNo" /></td>
								<td class="td1"><s:property value="myAccountName" escape="false" /></td>
								<td class="td1"><a href='accountQuery.action?myAccountNo=<s:property value="myAccountNo" />'> <s:property
											value="myAccountNo" escape="false" />
								</a></td>
								<td class="td1"><s:property value="cashType" escape="false" /></td>
								<td class="td1"><s:property value="myAccountBank" escape="false" /></td>
								<td class="td1"><s:property value="myAccountAlias" escape="false" /></td>
								<td class="last_child_td"><s:property value="myAccountType" escape="false" /></td>
							</tr>
						</s:iterator>

						<tr>
							<td class="last_second_line_td" colspan="7"><span>第 <s:property value="pageNow" /> 页/共 <s:property
										value="pageTotal" /> 页[ <img id="pre_page" src="images/pre.png" style="vertical-align: middle;"
									onclick="prePage();"> <s:property value="pageNow" /> <img id="next_page" src="images/next.png"
									style="vertical-align: middle;" onclick="nextPage();"> ]
							</span><span><s:textfield id="pageInput" name="pageInput" size="1" style="vertical-align: middle;" /></span> <span
								id="span_submit"><input type="image" src="images/btn_ok_1.png" onclick="this.form.submit();"
									style="vertical-align: middle;" /></span></td>
						</tr>

						<tr>
							<td class="last_line_td" colspan="7"></td>
						</tr>
					</table>
				</div>

				<div id="part_left"></div>
				<div id="part_left_btn">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="span_btn_0201"><img src="images/btn_0201.png" /></span>&nbsp;&nbsp;
					<span id="span_btn_0202"><img src="images/btn_0202.png" /></span>&nbsp;&nbsp; <span id="span_btn_0203"><img
						src="images/btn_0203.png" /></span>&nbsp;&nbsp; <span id="span_btn_0204"><img src="images/btn_0204.png" /></span>&nbsp;&nbsp;
					<span id="span_btn_0205"><img src="images/btn_0205.png" /></span>&nbsp;&nbsp;
				</div>
			</div>
		</s:form>
		<s:include value="footer.jsp" />
	</div>
</body>
</html>