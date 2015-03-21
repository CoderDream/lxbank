<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>陆璇软件银行系统登录页面</title>
<link type="text/css" rel="stylesheet" href="css/login.css">
</head>

<body background="images/login_bg.jpg">
	<div id="header">
		<img src="images/logo1.jpg">
	</div>

	<div id="mainbox">
		<div id="left">
			<form action="login.action" method="post">
				<span id="span_username1"><input type="text" class="username1" id="username1" name="username1"></span> <br />
				<span id="span_username"><input type="text" class="username" id="user.username" name="user.username"
					value="admin"></span> <br /> <span id="span_password"><input type="password" class="password"
					id="user.password" name="user.password" value="1234"></span> <br /> <span id="span_submit"><input
					type="submit" id="submit" value=""></span>
			</form>
		</div>
		<div id="right"></div>
	</div>

	<div id="footer">
		<img alt="net_shield" src="images/net_shield.jpg">
	</div>
</body>
</html>