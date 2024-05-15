<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='../../css/style.css'>

<title>得点管理システム</title>
</head>
<div class ="header">
<div>
	<h1>得点管理システム</h1>
</div>
<c:if test ="${user.isAuthenticated()}">
	<div>
		<span>${user.getName() }様</span>
		<a href ="Logout.action">ログアウト</a>
	</div>
</c:if>
</div>