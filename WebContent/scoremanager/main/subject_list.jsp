<%-- 科目一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科目一覧</title>
</head>

<c:import url="/common/header.jsp" />

<body>
<div class="main">
<c:import url="/common/navi.jsp" />
<div class ="con">
<h2>科目管理</h2>
	<!-- 新規登録ぺージ のリンク-->
	<a href="SubjectCreate.action">新規登録</a>

			<table class="table table-hover">
				<tr>
					<th>科目コード</th>
					<th>科目名</th>
					<th></th>
					<th></th>
				</tr>
				<!-- 科目名と科目コードをあるだけ(所属している学校の) -->
				<c:forEach var="subject" items="${sblist}">
					<tr>
					<!-- 表示 -->
						<td>${subject.subject_cd}</td>
						<td>${subject.name}</td>
						<td><a href="SubjectUpdate.action?subject_cd=${subject.subject_cd}">更新</a></td>
						<td><a href="SubjectDelete.action?subject_cd=${subject.subject_cd}">削除</a></td>
					</tr>
				</c:forEach>
			</table>

</div>
</div>
</body>
<c:import url="/common/footer.jsp" />
</html>