<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="m" class="sec01.ex02.MemberBean" />
<jsp:setProperty name="m" property="*" />
<jsp:useBean id="addr" class="sec01.ex02.Address" />
<jsp:setProperty name="addr" property="city" value="서울" />
<jsp:setProperty name="addr" property="zipcode" value="07654" />

<%
	m.setAddr(addr); // MemberBean의 addr 설정자에 addr 속성 설정
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
		<table border=1 align = "center" >
			<tr align="center" bgcolor="#99ccff">
				<td width="7%"><b>아이디</b></td>
				<td width="7%"><b>비밀번호</b></td>
				<td width="5%"><b>이름</b></td>
				<td width="5%"><b>이메일</b></td>
				<td width="5%"><b>도시</b></td>
				<td width="5%"><b>우편번호</b></td>
			</tr>
			<tr align="center">
				<td>${m.id }</td>
				<td>${m.pwd }</td>
				<td>${m.name }</td>
				<td>${m.email }</td>
				<!-- 속성들의 getter 두 번 호출 -->
				<td><%=m.getAddr().getCity() %></td>
				<td><%=m.getAddr().getZipcode() %></td>
			</tr>
			<tr align="center">
				<td>${m.id }</td>
				<td>${m.pwd }</td>
				<td>${m.name }</td>
				<td>${m.email }</td>
				<!-- 자바빈의 속성 이름과 마침표 연산자를 이용해 주소 출력 -->
				<td>${m.addr.city }</td>
				<td>${m.addr.zipcode }</td>
			</tr>
		</table>
	</body>
</html>