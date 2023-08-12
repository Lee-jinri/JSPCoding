<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		<c:set var="score" value="${param.score }" />
		<h1>시험점수
			<c:out value="${score }"/>
		</h1>
		<c:choose>
			<c:when test="${score>=90 && score <=100 }">
				<h1>A학점입니다.</h1>
			</c:when>
			<c:when test="${score>=80 && score <90 }">
				<h1>B학점입니다.</h1>
			</c:when>
			<c:when test="${score>=70 && score < 80 }">
				<h1>C학점입니다.</h1>
			</c:when>
			<c:when test="${score>=60 && score < 70 }">
				<h1>D학점입니다.</h1>
			</c:when>
			<c:when test="${score < 60 }">
				<h1>F학점입니다.</h1>
			</c:when>
			<c:otherwise>
				<h1>점수를 잘못 입력하셨습니다.</h1>
				<a href="scoreTest.jsp">점수 입력 창으로 이동</a>
			</c:otherwise>
		</c:choose>
	</body>
</html>