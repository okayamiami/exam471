<!-- 科目削除JSP -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科目削除</title>
</head>
<body>

<h2>科目情報削除</h2>

	<form action = "StudentDeleteExecute.action" method="post">


					<tr>
						<td>${subject.name}</td>
						<p>を削除してもよろしいですか？</p>
					</tr>

		<input type="submit" value="削除">
	</form>

	<a href="SubjectList.action">戻る</a>

</body>
</html>