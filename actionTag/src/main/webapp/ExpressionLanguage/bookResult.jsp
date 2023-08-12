<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>bookResult</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
	</head>
	<body>
	<!-- 자바빈 객체 생성 -->
	<jsp:useBean id="book" class="com.book.BookVO" />
	<jsp:setProperty name="book" property="*" />
	<%
		request.setAttribute("book", book); // 속성 설정
	%>
	<jsp:forward page="bookOutput.jsp" />

	</body>
</html>