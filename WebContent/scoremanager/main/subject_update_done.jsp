<!-- 科目更新後JSP -->
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
	<h2>科目情報更新</h2>
		<p>更新が完了しました</p>
	<a href="SubjectUpdate.action">戻る</a>
	<a href="SubjectList.action">科目一覧</a>
</div>
</div>
</body>
</html>