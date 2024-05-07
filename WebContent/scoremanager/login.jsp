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

	<!-- ID -->
<label>ID</label>
<input type="text" name="id" maxlength="20" placeholder="20文字以内の半角英数字"
	value="admin"
>

	<!-- パスワード -->
<label>パスワード</label>
<input type="password" name="password" value="password">

	<!-- 名前 -->
<label>名前</label>
<input type="text" name="name" value="大原　門左衛門">

	<!-- 学校コード -->
<label>学校コード</label>
<input type="text" name="school_cd" value="knz">

	<input type="submit" name="login" value="ログイン"/>

</form>


</body>
</html>