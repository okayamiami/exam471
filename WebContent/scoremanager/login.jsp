<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<body>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='../css/style22.css'>

<title>得点管理システム</title>
</head>

<div class="header">
<div>
	<h1>得点管理システム</h1>

	<h2>ログイン</h2>

	<div>${errors.get("null")}</div>
</div>
</div>



<div class="main">
<form action = "LoginExecute.action" method="post">

	<div class="log">
		<!-- ID -->
		<label>ID</label>
		<input type="text" name="id" maxlength="20" placeholder="半角でご入力下さい"
		required
		><br>
	</div>


	<div class="pas">
		<!-- パスワード -->
		<label>パスワード</label>
		<input type="password" id="password" name="password" maxlength="20" placeholder="20文字以内の半角英数字でご入力下さい" required/><br>
		<input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
		<label for="showPassword">パスワードを表示</label>

		<script>
			function togglePasswordVisibility() {
				let passwordInput = document.getElementById("password");
				let showPasswordCheckbox = document.getElementById("showPassword");

				if (showPasswordCheckbox.checked) {
					passwordInput.type = "text";
				} else {
					passwordInput.type = "password";
				}
			}
		</script><br>
	</div>
		<div class="kitazima">
		<input type="submit" name="login" value="ログイン"/>
		</div>
</form>
</div>


</body>
<c:import url="/common/footer.jsp" />
</html>