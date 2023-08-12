<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
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
		<form action="result.jsp">
			<label>아이디 : </label> <input type="text" size=20 /><br />
			<label>비밀번호 : </label><input tpye="text" size=20 /><br>
			<input type="submit" value="로그인" /><input type="reset" value="다시입력" />
		</form>
		<br><br>
		<%--
		<a href="${pageContext.request.contextPath}/memberForm.jsp">회원가입하기</a>
		 --%>
		 <a href="${contextPath }.text03/memberForm.jsp">회원가입하기</a>
	</body>
</html>