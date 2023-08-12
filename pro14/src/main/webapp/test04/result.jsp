<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>title</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		<script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="../js/common.js"></script>
	</head>
	<body>
		<c:if test="${empty param.userID }">
			아이디를 입력하세요.
			<a href="login.jsp">로그인 창</a>
		</c:if>
		<c:if test="${not empty param.userID }">
			<h1>환영합니다. <c:out value="${param.userID }"/>님</h1>
		</c:if>
	</body>
</html>