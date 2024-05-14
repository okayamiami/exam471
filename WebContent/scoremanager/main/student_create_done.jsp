<%-- 学生登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class ="wrapper">
<c:import url="/common/header.jsp" />

</div>
<body>
<div class="side">
<c:import url="/common/navi.jsp" />
</div>
	<h2>学生情報登録</h2>
		<p>登録が完了しました</p>
	<a href="StudentCreate.action">戻る</a>
	<a href="StudentList.action">学生一覧</a>
</body>
<c:import url="/common/footer.jsp" />
</html>