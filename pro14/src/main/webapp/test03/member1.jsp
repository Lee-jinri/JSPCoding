<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 코어 태그 라이브러리를 사용하기 위한 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="id" value="hong" scope="page" />
<c:set var="pwd" value="1234" scope="page" />
<c:set var="name" value="${'홍길동' }" scope="page" />
<c:set var="age" value="${22 }" scope="page" />
<c:set var="height" value="${177 }" scope="page" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>회원정보 출력창</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		<script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="../js/common.js"></script>
	</head>
	<body>
		<table align="center" border=1>
			<tr align="center" bgcolor="ligntgreen">
				<td width="7%" ><b>아이디</b></td>
				<td width="7%" ><b>비밀번호</b></td>
				<td width="7%" ><b>이름</b></td>
				<td width="7%" ><b>나이</b></td>
				<td width="7%" ><b>키</b></td>
			</tr>
			<tr align="center">
				<td>${id }</td>
				<td>${pwd }</td>
				<td>${name }</td>
				<td>${age }</td>
				<td>${height }</td>
			</tr>
		</table>
	</body>
</html>