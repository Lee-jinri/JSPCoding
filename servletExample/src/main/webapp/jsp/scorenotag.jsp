<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javabean.ScoreBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>JSP 예제 : scorenotag.jsp </title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->

	</head>
	<body>
		<h3>태그를 사용하지 않는 방법으로 JavaBeans를 이용하는 예제</h3>
		
		<!-- ScoreBean 객체 생성 -->
		<% ScoreBean score = new ScoreBean(); %>
		<hr />
		
		<h4>이름과 성적을 JavaBeans ScoreBean에 저장</h4>
		<p>
			이름 : 홍길동, 성적 : 85
		</p>
		
		<!-- 값 설정 -->
		<% score.setName("홍길동"); %>
		<% score.setPoint(85); %>
		<hr />
		
		<h4>JavaBeans ScoreBean에 저장된 정보를 조회 출력</h4>
		
		<!-- 값 가져옴 -->
		<p>
			이름 : <% out.println(score.getName()); %><br>
			성적 : <% out.println(score.getPoint()); %>
		</p>
	</body>
</html>