<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<c:import url="/common/header.jsp" />
<div class="main">

<c:import url="/common/navi.jsp" />

<div class ="con">
<h2>成績参照</h2>
	<!-- 科目別 -->
	<form action="TestListSubjectExecute.action" method="post">
	<div>科目情報
		<label>入学年度 </label>
		<select name="f1" required>
			<option value="">--------</option>
			<c:forEach var="year" items="${ent_year_set}">
				<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
				<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
			</c:forEach>
		</select>

		<label>クラス</label>
		<select name="f2" required>
			<option value="">--------</option>
			<c:forEach var="num" items="${class_num_set}">
				<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
				<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
			</c:forEach>
		</select>

		<label>科目</label>
		<select name="f3" required>
			<option value="">--------</option>
			<c:forEach var="sub" items="${subject_set}">
				<%-- 現在のsubと選択されていたf3が一致していた場合selectedを追記 --%>
				<option value="${sub.subject_cd}" <c:if test="${sub.name==f3}">selected</c:if>>${sub.name}</option>
			</c:forEach>
		</select>
		<button>検索</button>
		</div>
	</form>

	<!-- 学生別 -->
	<form action="TestListStudentExecute.action" method="post">
	<div>学生情報
		<label>学生番号</label>
			<input type="text" name="student_no" placeholder="学生番号を入力してください" maxlength="10" required  />
		<button>検索</button>
		</div>
	</form>

	<!-- 結果部分 -->
	<c:choose>
		<%--学生別 --%>
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

		<%--科目別 --%>
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
						<td>${tlsub_set.entYear}</td>
						<td>${tlsub_set.class_num}</td>
						<td>${tlsub_set.student_no}</td>
						<td>${tlsub_set.student_name}</td>
						<td>${tlsub_set.getPoint(1)}</td>
						<td>${tlsub_set.getPoint(2)}</td>

					</tr>
				</c:forEach>
			</table>
		</c:when>

		<%--共用で存在しない場合 --%>
		<c:otherwise>
			<c:choose>
				<%--学生別で生徒は存在するが成績が１つもついていない --%>
				<c:when test="${student != null}">
					<div>氏名：${student.name}(${student.student_no})</div>
					<div>成績情報が存在しませんでした</div>
				</c:when>
				<%--その他 --%>
				<c:otherwise>
					<c:choose>
					<%--studentの名前でエラーが飛んできてる場合 --%>
						<c:when test="${errors.get(\"student\")!= null}">
						<div>${errors.get("student")}</div>
						</c:when>
						<%--共用で成績がない場合 --%>
						<c:otherwise>
						<div>成績情報が存在しませんでした</div>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>


</div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>