<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>得点管理システム</title>
</head>
<c:import url="/common/header.jsp" />
<body>

<c:import url="/common/navi.jsp" />

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

		<label>科目</label>
		<select name="f3">
			<option value="0">--------</option>
			<c:forEach var="sub" items="${subject_set}">
				<%-- 現在のsubと選択されていたf3が一致していた場合selectedを追記 --%>
				<option value="${sub.subject_cd}" <c:if test="${sub.subject_cd==f3}">selected</c:if>>${sub.name}</option>
			</c:forEach>
		</select>

		<label>回数</label>
		<select name="f4">
			<option value="0">--------</option>
			<option value="1" <c:if test="${1==f4}">selected</c:if>>1</option>
			<option value="2" <c:if test="${2==f4}">selected</c:if>>2</option>
		</select>


		<button>検索</button>

		<div>${errors.get("f1")}</div>
	</form>

	<c:choose>
		<c:when test="${tests.size()>0}">
			<div>検索結果：${tests.size()}件</div>

			<table class="table table-hover">
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>点数 </th>
					<th></th>

				</tr>
				<form action = "TestRegistExecute.action" method="post">
				<c:forEach var="test" items="${tests}">
					<tr>
						<td>${ent_year}</td>
						<td>${test.classNum}</td>
						<td>${test.student.student_no}</td>
						<td>${test.student.name}</td>
						<td>
						<input type="text"
							name="point_${test.student.student_no}" placeholder="点数を変更" maxlength="3"
							value="${test.point}" />
						<div>${errors.get("point")}</div>

						</td>
					</tr>

				</c:forEach>
			</table>

			<input type="hidden" name="f1" value ="${f1}">
			<input type="hidden" name="f2" value ="${f2}">
			<input type="hidden" name="f3" value ="${f3}">
			<input type="hidden" name="f4" value ="${f4}">
			<input type="submit" value="登録して終了">
		</form>
		</c:when>

		<c:when test="${tests.size()==0 }">
		<div>学生情報が存在しませんでした</div>
		</c:when>
	</c:choose>

</body>
<c:import url="/common/footer.jsp" />
</html>