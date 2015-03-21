<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国建设银行 企业网上银行</title>
<link type="text/css" rel="stylesheet" href="css/account_query.css" />
<!--   引入jQuery -->
<script src="js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	function btnQuery() {
		//alert("begin btnQuery");
		var myAccountNo = document.getElementById("myAccountNo").innerHTML;
		//alert("myAccountNo: " + myAccountNo);
		var myAccountNo2 = document.getElementById("myAccountNo2");
		//alert("myAccountNo2_1: " + myAccountNo2.value);
		myAccountNo2.value = myAccountNo;
		//alert("myAccountNo2_2: " + myAccountNo2.value);
		//alert('submit #1 myAccountNo: ' + myAccountNo);
		// 记账日期开始
		var fromAccountDateStr = document.getElementById("fromAccountDateStr").value;
		//var flag = isValidDate(fromAccountDateStr);
		var flag = checkDate(fromAccountDateStr);
		if (!flag) {
			return;
		}
		//alert(fromAccountDateStr);
		// 记账日期截止
		var toAccountDateStr = document.getElementById("toAccountDateStr").value;
		flag = checkDate(toAccountDateStr);
		if (!flag) {
			return;
		}
		//alert('submit #2');
		flag = compareTwoDate(fromAccountDateStr, toAccountDateStr);
		//alert("flag: " + flag);
		//alert('submit #3');
		// 最小金额
		var minAmount = document.getElementById("minAmount").value;
		//alert('submit #3.1');
		// 最大金额
		var maxAmount = document.getElementById("maxAmount").value;
		//alert('submit #3.2');
		if (minAmount > maxAmount) {
			alert("金额的最大值应该大于或等于最小值！");
			return;
		}
		//alert('submit #3.3');
		// 交易方向
		//var debitAndCrebitFlagId = document.getElementById("debitAndCrebitFlag");
		//alert("debitAndCrebitFlagId: " + debitAndCrebitFlagId);
		//var debitAndCrebitFlagName = document.getElementByName("debitAndCrebitFlag");
		//alert("debitAndCrebitFlagId: " + debitAndCrebitFlagId.length);

		//var debitAndCrebitFlag = "";
		//for (var i = 0; i < debitAndCrebitFlagId.length; i++) {
		//	if (debitAndCrebitFlagId.item(i).checked) {
		//		debitAndCrebitFlag = debitAndCrebitFlagId.item(i).getAttribute("value");
		//		break;
		//	} else {
		//		continue;
		//	}
		//}
		//alert("debitAndCrebitFlag: " + debitAndCrebitFlag);

		//alert('submit #3.4');
		// 对方户名
		//var sendName = document.getElementById("sendName").value;
		//alert('submit #3.5');
		// 对方账号
		//var sendNo = document.getElementById("sendNo").value;
		//alert('submit #3.6');
		// 摘要
		//var summary = document.getElementById("summary").value;
		//alert('submit #4');
		//获取该页面中的第一个表单元素
		targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "accountDetail.action";
		//if (null != myAccountNo2 && "" != myAccountNo2.value) {
			
			//alert("myAccountNo2_3: " + myAccountNo2.value);
			//targetForm.myAccountNo2 = myAccountNo2.value;
			//alert("myAccountNo2_4: " + myAccountNo2.value);
		//}

		//targetForm.action.fromAccountDate = fromAccountDateStr;
		//targetForm.action.toAccountDate = toAccountDateStr;
		//targetForm.action.minAmount = minAmount;
		//targetForm.action.maxAmount = maxAmount;
		//targetForm.action.debitAndCrebitFlag = debitAndCrebitFlag;
		//targetForm.action.sendName = sendName;
		//targetForm.action.sendNo = sendNo;
		//targetForm.action.summary = summary;
		//targetForm.action = "accountDetail.action?fromAccountDateStr=" + fromAccountDateStr + "&toAccountDateStr="
		//		+ toAccountDateStr + "&minAmount=" + minAmount + "&maxAmount=" + maxAmount + "&debitAndCrebitFlag="
		//		+ debitAndCrebitFlag + "&sendName=" + sendName + "&sendNo=" + sendNo + "&summary=" + summary;
		//提交表单
		//alert('submit #5');
		targetForm.submit();
	}

	function btnReturn() {
		//获取该页面中的第一个表单元素
		targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "account.action";
		//提交表单
		targetForm.submit();
	}

	function checkDate(dateStr) {
		//alert(dateStr);
		var length = dateStr.length;
		//alert("check length: " + length);
		if (0 == length) {
			return true;
		}
		if (8 != length) {
			alert('错误的日期格式！格式为：YYYYMMDD！');
			return false;
		}
		var year = dateStr.substring(0, 4);
		var month = dateStr.substring(4, 6);
		var day = dateStr.substring(6, 8);
		var newDateStr = year + "/" + month + "/" + day;
		//alert("newDateStr: " + newDateStr);

		var ds = newDateStr.match(/\d+/g), ts = [ 'getFullYear', 'getMonth', 'getDate' ];
		var d = new Date(newDateStr.replace(/-/g, '/')), i = 3;
		ds[1]--;
		while (i--) {
			if (ds[i] * 1 != d[ts[i]]()) {
				//alert('false');
				return false;
			}

		}
		//alert('true');
		return true;
	}

	function compareTwoDate(date1, date2) {
		//alert(date1 + " : " + date2);
		var length1 = date1.length;
		var length2 = date2.length;
		if (0 == length1 || 0 == length2) {
			return true;
		}

		if (8 != length1 || 8 != length2) {
			alert('错误的日期格式！格式为：YYYYMMDD！');
			return false;
		}
		var year1 = date1.substring(0, 4);
		var month1 = date1.substring(4, 6);
		var day1 = date1.substring(6, 8);
		var newDateStr1 = year1 + "/" + month1 + "/" + day1;

		var year2 = date2.substring(0, 4);
		var month2 = date2.substring(4, 6);
		var day2 = date2.substring(6, 8);
		var newDateStr2 = year2 + "/" + month2 + "/" + day2;

		var date01 = new Date(Date.parse(newDateStr1)).getTime();
		var date02 = new Date(Date.parse(newDateStr2)).getTime();
		//alert(date01 + " : " + date02);
		return date02 - date01 < 90 * 24 * 60 * 60 * 1000;
	}
</script>
</head>
<body>
	<div id="top">
		<s:include value="header.jsp" />
		<s:form id="myForm" action="accountDetail.action" method="get" theme="simple">
			<s:hidden id="myAccountNo2" name="myAccountNo2" />
			<div id="mainbox">
				<div id="part">
					<span id="span_main_top"><img src="images/sub_menu.png" /></span>
				</div>
				<div id="part_left">
					<span id="span_label1">&nbsp;&nbsp;账户查询&nbsp;&gt;&nbsp;账户信息查询</span>
				</div>

				<div id="span_main_body">
					<table class="table1">
						<tr>
							<td class="first_line_th" colspan="2"><span id="span_label_1"><s:label id="myAccountNo"
										name="myAccountNo">
									</s:label>明细查询</span></td>
						</tr>

						<tr>
							<td class="td_left"><span>*</span><span>按时间段查询：</span></td>
							<td class="td_right"><s:textfield id="fromAccountDateStr" name="fromAccountDateStr" /> - <s:textfield
									id="toAccountDateStr" name="toAccountDateStr" />（标准输入格式20070405，查询时间段不能超过3个月）</td>
						</tr>

						<tr>
							<td class="td_left"><span>金额：</span></td>
							<td class="td_right"><s:textfield id="minAmount" name="minAmount" /> - <s:textfield id="maxAmount"
									name="maxAmount" /></td>
						</tr>

						<tr>
							<td class="td_left"><span>交易方向：</span></td>
							<td class="td_right"><s:radio id="debitAndCrebitFlag" name="debitAndCrebitFlag" value="0"
									list="#{0:'全部',2:'转入',1:'转出' }" /></td>
						</tr>

						<tr>
							<td class="td_left"><span>交易对方户名：</span></td>
							<td class="td_right"><s:textfield id="sendName" name="sendName" /></td>
						</tr>

						<tr>
							<td class="td_left"><span>交易对方账号：</span></td>
							<td class="td_right"><s:textfield id="sendNo" name="sendNo" /></td>
						</tr>

						<tr>
							<td class="td_left"><span>交易摘要：</span></td>
							<td class="td_right"><s:textfield id="summary" name="summary" /></td>
						</tr>

						<tr>
							<td class="last_line_td" colspan="2"></td>
						</tr>
					</table>
				</div>

				<div id="part_left"></div>
				<div id="part_left_btn">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="span_btn_0301"><img src="images/btn_0301.png"
						onclick="btnQuery();" /></span>&nbsp;&nbsp; <span id="span_btn_0302"><img src="images/btn_0302.png"
						onclick="btnReturn();" /></span>&nbsp;&nbsp;
				</div>
			</div>
		</s:form>
		<s:include value="footer.jsp" />
	</div>
</body>
</html>