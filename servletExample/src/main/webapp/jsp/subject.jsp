<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="subject" class="com.javabean.SubjectBean" scope="page" />

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
		<h3>JavaBean SubjectBean을 이용한 예제</h3>
		<% request.setCharacterEncoding("utf-8"); %>
		
		<h4>폼에서 전달받은 학과정보를 그대로 자바빈 SubjectBean에 저장</h4>
		<jsp:setProperty name="subject" property="*"/>
		<hr />
		
		<h4>JavaBean SubjectBena에 저장된 정보를 조회 출력</h4>
		<table>
			<thead>
				<tr><th colspan="2">학과 정보</th></tr>
			</thead>
			<tbody>
				<tr>
					<td id="sNu">학과 코드</td>
					<td><jsp:getProperty property="s_num" name="subject"/></td>
				</tr>
				<tr>
					<td id="sNm">학 과 명</td>
					<td><jsp:getProperty property="s_name" name="subject"/></td>
				</tr>
			</tbody>
		</table>
		
	</body>
</html>