<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>成績参照</h2>

	<form action="TestListSubject.action" method="post">
	<div>科目情報
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
				<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
				<option value="${sub.name}" <c:if test="${sub.name==f3}">selected</c:if>>${sub.name}</option>
			</c:forEach>
		</select>


		<button>検索</button>

		<div>${errors.get("f1")}</div>
		</div>
	</form>

	<form action="TestListStudentExecute.action" method="post">
	<div>学生情報
		<label>学生番号</label>
			<%-- パラメーターf3が存在している場合checkedを追記 --%>
			<input type="text" name="student_no" placeholder="学生番号を入力してください" maxlength="10" required  />

		<button>検索</button>

		<div>${errors.get("f1")}</div>
		</div>
	</form>

	<c:choose>
		<c:when test="${tls_set.size()>0}">
			<div>氏名：${student.name}(${student.student_no})</div>

			<table class="table table-hover">
				<tr>
					<th>科目名</th>
					<th>科目コード</th>
					<th>回数</th>
					<th>点数</th>

				</tr>
				<c:forEach var="tls_set" items="${tls_set}">
					<tr>
						<td>${tls_set.name}</td>
						<td>${tls_set.subject_cd}</td>
						<td>${tls_set.no}</td>
						<td>${tls_set.point}</td>

					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:when test="${tlsub_set.size()>0}">
			<div>科目名：${f3}</div>

			<table class="table table-hover">
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>1回</th>
					<th>2回</th>


				</tr>
				<c:forEach var="tlsub_set" items="${tlsub_set}">
					<tr>
						<td>${f1}</td>
						<td>${tlsub_set.class_num}</td>
						<td>${tlsub_set.student_no}</td>
						<td>${tlsub_set.student_name}</td>
						<td>${tlsub_set.getPoint(1)}</td>
						<td>${tlsub_set.getPoint(2)}</td>

					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<div>学生情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>



</body>
</html>