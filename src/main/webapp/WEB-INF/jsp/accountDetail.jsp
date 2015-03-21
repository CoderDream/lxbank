<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国建设银行 企业网上银行</title>
<link type="text/css" rel="stylesheet" href="css/account_detail.css" />
<script type="text/javascript">
	function prePage() {
		//alert('prePage');

		alert("pageNow value: " + document.getElementById("pageNow").value);
		var pageNow = document.getElementById("pageNow");
		pageNow.value = parseInt(pageNow.value) - 1;

		alert("pageNow value: " + document.getElementById("pageNow").value);
		//alert(pageNow.value);
		//获取该页面中的第一个表单元素
		targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "newPageDetail.action";
		//提交表单
		targetForm.submit();
	}

	function nextPage() {
		//alert('nextPage');
		alert("pageNow value: " + document.getElementById("pageNow").value);
		var pageNow = document.getElementById("pageNow");
		pageNow.value = parseInt(pageNow.value) + 1;
		alert("pageNow value: " + document.getElementById("pageNow").value);
		//alert(pageNow.value);
		//获取该页面中的第一个表单元素
		targetForm = document.forms[0];
		//动态修改目标表单的action属性
		//alert('###')
		targetForm.action = "newPageDetail.action";
		//提交表单
		targetForm.submit();
	}

	function show(divName) {
		//alert('show');
		var checkbox_list = document.getElementById(divName);
		checkbox_list.style.display = "block";
	}
	function hidden(divName) {
		//alert('hidden');
		var checkbox_list = document.getElementById(divName);
		checkbox_list.style.display = "none";
	}

	function exportFile(type) {
		var checkboxs = document.getElementsByName("ad_check_box");
		var adIdStr = "";
		//alert("check point #1: ");
		var adIdArray = document.getElementsByName("adId");
		//alert("adIdList: "+adIdList);
		//alert("adIdList length: "+adIdList.length);
		for (var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if (e.checked) {
				adIdStr = adIdStr + adIdArray[i].value + ":";
			}
		}
		var adIdListStr = document.getElementById("adIdListStr");
		adIdListStr.value = adIdStr;

		if (1 == type) {
			//alert('Download Text!');
		} else if (2 == type) {
			//alert('Download Csv!');
		} else {
			//alert('Download Excel!');
		}

		var fileType = document.getElementById("fileType");
		fileType.value = type;
		//alert("fileType value: " + document.getElementById("fileType").value);

		hidden('btn_export');
		//获取该页面中的第一个表单元素
		targetForm = document.forms[0];
		//动态修改目标表单的action属性
		//alert('###')
		targetForm.action = "exportExcelFile.action";
		//提交表单
		targetForm.submit();
	}

	// 全选与反选，可以全选或全部取消
	function checkAll() {
		//alert("checkAll()");
		var checkboxs = document.getElementsByName("ad_check_box");

		var check_all = document.getElementById("check_all");
		//alert("check_all.chekcked: " + check_all.checked);

		for (var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			e.checked = check_all.checked;
		}
	}
</script>
</head>
<body>
	<div id="top">
		<s:include value="header.jsp" />
		<s:form id="myForm" action="accountDetail.action" method="post" theme="simple">
			<s:hidden id="pageNow" name="pageNow" />
			<s:hidden id="myAccountNo" name="myAccountNo" />
			<s:hidden id="myAccountNo2" name="myAccountNo2" />
			<s:hidden id="fromAccountDateStr" name="fromAccountDateStr" />
			<s:hidden id="toAccountDateStr" name="toAccountDateStr" />
			<s:hidden id="minAmount" name="minAmount" />
			<s:hidden id="maxAmount" name="maxAmount" />
			<s:hidden id="debitAndCrebitFlag" name="debitAndCrebitFlag" />
			<s:hidden id="sendName" name="sendName" />
			<s:hidden id="sendNo" name="sendNo" />
			<s:hidden id="summary" name="summary" />
			<s:hidden id="adIdListStr" name="adIdListStr" />
			<s:hidden id="fileType" name="fileType" />
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
				<div id="span_main_body">
					<table class="table1">
						<tr>
							<th class="th_01_03_header" colspan="4"></th>
						</tr>
						<tr>
							<th class="th_01_01" colspan="4">账户基本信息</th>
						</tr>
						<tr>
							<th class="th_01_02_title">&nbsp;&nbsp;&nbsp;&nbsp;开户机构：</th>
							<th class="th_01_02_content"></th>
							<th class="th_01_02_title">&nbsp;&nbsp;&nbsp;&nbsp;币&nbsp;&nbsp;&nbsp;&nbsp;种：</th>
							<th class="th_01_02_content"></th>
						</tr>
						<tr>

							<th class="th_01_02_title">&nbsp;&nbsp;&nbsp;&nbsp;账&nbsp;&nbsp;&nbsp;&nbsp;号：</th>
							<th class="th_01_02_content"></th>
							<th class="th_01_02_title">&nbsp;&nbsp;&nbsp;&nbsp;钞汇标志：</th>
							<th class="th_01_02_content"></th>
						</tr>
						<tr>
							<th class="th_01_02_title">&nbsp;&nbsp;&nbsp;&nbsp;账户名称：</th>
							<th class="th_01_02_content"></th>
							<th class="th_01_02_title">&nbsp;&nbsp;&nbsp;&nbsp;账户状态：</th>
							<th class="th_01_02_content"></th>
						</tr>
						<tr>
							<th class="th_01_03_header" colspan="4"></th>
						</tr>
					</table>
				</div>
				<div id="span_main_body">
					<table class="table1">
						<tr>
							<th class="first_child_th" rowspan="2">选择</th>
							<th class="th1" rowspan="2">记账日期</th>
							<th class="th1" rowspan="2">交易时间</th>
							<th class="th1" rowspan="2">凭证种类</th>
							<th class="th1" rowspan="2">凭证号</th>
							<th class="th1" colspan="2">发生额/元</th>
							<th class="th1" rowspan="2">余额/元</th>
							<th class="th1" rowspan="2">对方户名</th>
							<th class="th1" rowspan="2">对方账号</th>
							<th class="th1" rowspan="2">摘要</th>
							<th class="th1" rowspan="2">备注</th>
							<th class="th1" rowspan="2">账户明细编号-<br />交易流水号
							</th>
							<th class="last_child_th" rowspan="2">企业流水号</th>
						</tr>
						<tr>
							<th class="th1">借方</th>
							<th class="th1">贷方</th>
						</tr>
						<s:iterator value="accountDetails">
							<tr>
								<td class="first_child_td"><s:checkbox id="ad_check_box" name="ad_check_box" /> <s:hidden id="adId"
										name="adId" /></td>
								<td class="td_02"><s:property value="accountDateStr" escape="false" /></td>
								<td class="td_03"><s:property value="exchangeDateStr" escape="false" /></td>
								<td class="td_04"><s:property value="voucherType" escape="false" /></td>
								<td class="td_05"><s:property value="voucher" escape="false" /></td>
								<td class="td_06"><s:property value="debitAmountStr" escape="false" /></td>
								<td class="td_07"><s:property value="crebitAmountStr" escape="false" /></td>
								<td class="td_08"><s:property value="balance" escape="false" /></td>
								<td class="td_09"><s:property value="sendName" escape="false" /></td>
								<td class="td_10"><s:property value="sendNo" escape="false" /></td>
								<td class="td_11"><s:property value="summary" escape="false" /></td>
								<td class="td_12"><s:property value="comment" escape="false" /></td>
								<td class="td_13"><s:property value="serialNumber" escape="false" /></td>
								<td class="last_child_td"><s:property value="myAccountType" escape="false" /></td>
							</tr>
						</s:iterator>

						<tr>
							<td class="last_line_td" colspan="14"></td>
						</tr>

						<tr>
							<td class="td_empty" colspan="14"></td>
						</tr>

						<tr>
							<td class="last_line_td" colspan="14"></td>
						</tr>

						<tr>
							<td class="last_second_line_td" colspan="14"><s:checkbox id="check_all" name="check_all"
									onclick="checkAll();">全选</s:checkbox></td>
						</tr>

						<tr>
							<td class="last_second_line_td" colspan="14">尊敬的客户：<br> 在20140101至20140315期间，本页统计：本账户共发生转出交易 57 笔，金额
								567,611.20 元；转入交易 14 笔，金额 564,261.61 元。
							</td>
						</tr>

						<tr>
							<td class="last_second_line_td" colspan="14"><span>第 <s:property value="pageNow" /> 页/共 <s:property
										value="pageTotal" /> 页[ <img id="pre_page" src="images/pre.png" style="vertical-align: middle;"
									onclick="prePage();"> <s:property value="pageNow" /> <img id="next_page" src="images/next.png"
									style="vertical-align: middle;" onclick="nextPage();"> ]
							</span><span><s:textfield id="pageInput" name="pageInput" size="1" style="vertical-align: middle;" /></span> <span
								id="span_submit"><input type="image" src="images/btn_ok_1.png" onclick="this.form.submit();"
									style="vertical-align: middle;" /></span></td>
						</tr>

						<tr>
							<td class="last_line_td" colspan="14"></td>
						</tr>
					</table>
				</div>

				<div id="part_left"></div>
				<div id="part_left_btn">
					<table class="table">
						<tr>
							<td><span id="span_btn_0401"><img src="images/btn_0401.png" style="vertical-align: middle;" /></span></td>
							<td><span id="span_btn_0402"><img src="images/btn_0402.png" style="vertical-align: middle;" /></span></td>
							<td style="position: relative;"><img id="img_export" src="images/btn_0403.png"
								style="vertical-align: middle;" onclick="show('btn_export');" /> <!--div begin-->
								<div id="btn_export"
									style="display: none; z-index: 2000; position: absolute; left: 3px; top: 23px; border: 1px solid #06c; padding: 0px; background: #E7ECF1;">
									<div style="text-align: left; background: #E7ECF1; text-align: right; padding-right: 3px;">
										<span style="width: 12; border-width: 0px; color: white; font-family: webdings; cursor: hand; left: -200;"
											onclick="hidden('btn_export')">r</span><br> <span>&nbsp;请选择下载方式：&nbsp;</span><br> <span>Txt
											&nbsp;&nbsp;&nbsp; <a href="#" onclick="exportFile(1);">下载</a>
										</span><br> <span>Csv &nbsp;&nbsp;&nbsp; <a href="#" onclick="exportFile(2);">下载</a></span><br> <span>Excel
											&nbsp; <a href="#" onclick="exportFile(3);">下载</a>
										</span>
									</div>
								</div> <!--div end--></td>
							<td><span id="span_btn_0404"><img src="images/btn_0404.png" style="vertical-align: middle;" /></span></td>
							<td><span id="span_btn_0405"><img src="images/btn_0405.png" style="vertical-align: middle;" /></span></td>
						</tr>
					</table>
				</div>
			</div>
		</s:form>
		<s:include value="footer.jsp" />
	</div>
</body>
</html>