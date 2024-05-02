<!-- 科目更新JSP -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>得点管理システム</title>
</head>
<body>
	<h2>学生情報変更</h2>
	<form action = "SubjectUpdateExecute.action" method="post">
		<label>科目コード</label><br>
		<input type="hidden" name="subject_cd" value="${subject_cd}">
		${subject_cd}<br>
		<label>科目名</label>
		<input type="text" name="subject_name" placeholder="科目名を入力してください"
			maxlength="10" value="${subject_name}" required />
		<div>${errors.get("subject_name")}</div>

		<input type="submit" value="変更">

	</form>

	<a href="StudentList.action">戻る</a>

</body>
</html>