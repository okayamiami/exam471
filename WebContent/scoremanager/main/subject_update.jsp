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
	<h2>科目情報変更</h2>
	<form action = "SubjectUpdateExecute.action" method="post">

		<label>科目コード</label><br>
		<p type="text" name="subject_cd" value="${subject.subject_cd}"> ${subject.subject_cd}</p>

		<label>科目名</label>
		<input type="text" name="subject_name"
		placeholder="科目名を入力してください"	maxlength="10" required />
		<div>${errors.get("subject_name")}</div>

		<input type="hidden" name="subject_name" value="${subject.name}">
		<input type="hidden" name="subject_cd" value="${subject.subject_cd}">
		<input type="hidden" name="school_cd" value="${school.cd}">

		<input type="submit" value="変更">

	</form>

	<a href="SubjectList.action">戻る</a>

</body>
</html>