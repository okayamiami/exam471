<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>得点管理システム</title>
</head>
<body>

<form action = "LoginExecute.action" method="post">

<!--
	autocomplete
	on/off: 自動保管の制御

	ime-mode
	active:漢字（全角）モードにします
	disabled:日本語入力機能

	required:input入力を必須にする

 -->
	<div>${errors.get("null")}</div>
	<!-- ID -->
	<label>ID</label>
	<input type="text" name="id" maxlength="20" placeholder="半角でご入力下さい"
	required
	>

	<!-- パスワード -->
	<label>パスワード</label>
	<input type="password" id="password" name="password" maxlength="20" placeholder="20文字以内の半角英数字でご入力下さい" required/><br>
	<input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" /><br>
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

	<input type="submit" name="login" value="ログイン"/>

</form>


</body>
</html>