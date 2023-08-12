<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
		
	</head>
	<body>
		<h2>저장된 영역 객체 속성 추출</h2>
		<%
			pageContext.setAttribute("message1","PageContext 객체에 저장된 문자열 객체");
			request.setAttribute("message","HttpServletRequest 객체에 저장된 문자열 객체");
			session.setAttribute("massage3","HttpSession 객체에 저장된 문자열 객체");
			application.setAttribute("massage","ServletContext 객체에 저장된 문자열 객체");
		
		// out.println(pageContext.getAttribute.("message1") + "<br />")과 같음
		%>
		pageScope 객체에서 추출 : ${ pageScope.message1 }<br>
		requestScope 객체에서 추출 : ${ requestScope.message }<br>
		sessionScope 객체에서 추출 : ${ sessionScope.massage3 }<br>
		applicationScope 객체에서 추출 : ${ applicationScope.massage }<br>
		<hr />
		<!-- 속성명만 쓰면 page ~ application 순서대로!! 속성이 있는지 확인함 -->
		message 추출 : ${message }
	</body>
	
</html>