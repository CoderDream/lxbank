<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Check All</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
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
	<form method="post" action="">
		<table>
			<tr>
				<td><input type="checkbox" name="ad_check_box" value="true" id="ad_check_box" /></td>
				<td>第一行</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="ad_check_box" value="true" id="ad_check_box" /></td>
				<td>第二行</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="ad_check_box" value="true" id="ad_check_box" /></td>
				<td>第三行</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="ad_check_box" value="true" id="ad_check_box" /></td>
				<td>第四行</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="ad_check_box" value="true" id="ad_check_box" /></td>
				<td>第五行</td>
			</tr>
			<tr>
				<td>全选</td>
				<td><input type="checkbox" name="check_all" value="true" id="check_all" onclick="checkAll();" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
