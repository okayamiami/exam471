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
<body>
<h2>科目管理</h2>
	<a href="SubjectCreate.action">新規登録</a>

	<c:choose>
		<c:when test="${list.size()>0}">
			<div>検索結果：${list.size()}件</div>

			<table class="table table-hover">
				<tr>
					<th>学校コード</th>
					<th>科目コード</th>
					<th>科目名</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="subject" items="${list}">
					<tr>
						<td>${subject.subject_cd}</td>
						<td>${subject.name}</td>
						<td>${subject.school_cd}</td>
						<td class="text-center">
						</td>

						<td><a href="SubjectUpdate.action?subject_cd=${subject.subject_cd}">変更/更新</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<div>科目の情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>



	<a href="SubjectUpdate.action">科目情報変更</a>

	<a href="SujectDelete.action">科目情報削除</a>




</body>
</html>