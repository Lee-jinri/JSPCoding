<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%
	request.setCharacterEncoding("utf-8");
%>

<%--회원 정보를 저장할 빈 생성, 전송된 회원 정보를 빈의 속성에 설정 --%>
<jsp:useBean id="m" class="sec01.ex01.MemberBean"/>
<jsp:setProperty name="m" property="*"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>Insert title here</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		<!-- jQuery 파일을 다운받아 로드하는 방법 -->
		<!-- <script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script> -->
		<!-- <script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script> -->
		
	</head>
	
	<body>
		
		<table align="center" border="1">
			<tr align="center" bgcolor="#99ccff">
				<td width="20%"><b>아이디</b></td>
				<td width="20%"><b>비밀번호</b></td>
				<td width="20%"><b>이름</b></td>
				<td width="20%"><b>이메일</b></td>
			</tr>
			
			
			<tr align="center">
				<td><%=m.getId() %></td>
				<td><%=m.getPwd() %></td>
				<td><%=m.getName() %></td>
				<td><%=m.getEmail() %></td>
			</tr>
			
			<tr align="center">
				<td>${m.id }</td>
				<td>${m.pwd }</td>
				<td>${m.name }</td>
				<td>${m.email }</td>
			</tr>
		</table>
	</body>
</html>