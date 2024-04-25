<%-- 成績一覧JSP 4/25更新 --%>
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

	<h2>成績管理</h2>

	<form method="get">
		<label>入学年度 </label>
		<select name="f1">
			<option value="0">--------</option>
			<c:forEach var="year" items="${ent_year_set}">
				<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
				<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
			</c:forEach>
		</select>

		<label>クラス</label>
		<select name="f2">
			<option value="0">--------</option>
			<c:forEach var="num" items="${class_num_set}">
				<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
				<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
			</c:forEach>
		</select>

		<%--★以下更新箇所 --%>
		<label>科目</label>
		<select name="f3">
			<option value="0">--------</option>
			<c:forEach var="sub" items="${class_sub_set}">
				<%-- 現在のsubと選択されていたf3が一致していた場合selectedを追記 --%>
				<option value="${sub}" <c:if test="${sub==f3}">selected</c:if>>${sub}</option>
			</c:forEach>
		</select>

		<label>回数</label>
		<select name="f4">
			<option value="0">--------</option>
			<c:forEach var="trynum" items="${class_trynum_set}">
				<%-- 現在のsubと選択されていたf4が一致していた場合selectedを追記 --%>
				<option value="${trynum}" <c:if test="${trynum==f4}">selected</c:if>>${trynum}</option>
			</c:forEach>
		</select>


		<button>検索</button>

		<div>${errors.get("f1")}</div>
	</form>

	<c:choose>
		<c:when test="${students.size()>0}">
			<div>検索結果：${students.size()}件</div>

			<table class="table table-hover">
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>点数 </th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="student" items="${students}">
					<tr>
						<td>${student.entYear}</td>
						<td>${student.classNum}</td>
						<td>${student.student_no}</td>
						<td>${student.name}</td>
						<td>${student.tryNum}</td>
						
						<label>氏名</label>
						<input type="text"
							name="trynum" placeholder="点数を変更" maxlength="3"
							value="${trynum}" required />
						<div>${errors.get("trynum")}</div>
						
						<td><a href="StudentUpdate.action?student_no=${student.student_no}">変更</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<div>成績情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>

</body>
</html>