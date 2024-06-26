<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<c:import url="/common/header.jsp" />

<div class ="main">

<c:import url="/common/navi.jsp" />
<div class ="con">
	<h2>科目情報 新規登録</h2>

	<!-- 完了したらExeに行ってくれる -->
	<form action = "SubjectCreateExecute.action" method="post">


		<!-- 科目コードを入力するとこ -->
		<label>科目コード</label>
		<input type="text" name="subject_cd"
			placeholder="科目コードを入力してください" maxlength="3" value="${subject_cd}" required />
		<div>${errors.get("list")}</div>

		<!-- 科目名を入力するとこ -->
		<label>科目名</label>
		<input type="text" name="subject_name"
			placeholder="科目名を入力してください" maxlength="10" value="${subject_name}" required/>


		<input type="submit" value="登録">
	</form>

	<!-- 科目全表示画面に戻る -->
	<a href="SubjectList.action">戻る</a>
</div>
</div>
</body>
</html>