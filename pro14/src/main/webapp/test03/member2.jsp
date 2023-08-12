<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*, sec01.ex01.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="membersList" class="java.util.ArrayList" />
<jsp:useBean id="membersMap" class="java.util.HashMap" />
<%
	membersMap.put("id","park");
	membersMap.put("pwd","4321");
	membersMap.put("name","박지성");
	membersMap.put("email", "park2@test.com");
	MemberBean m1 = new MemberBean("son","1234","손흥민","son@test.com");
	MemberBean m2 = new MemberBean("ki","4231","기성용","ki@test.com");
	membersList.add(m1);
	membersList.add(m2);
	membersMap.put("membersList",membersList);
%>
<c:set var="membersList" value="${membersMap.membersList }"/>
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
		${membersMap.id }
		${membersMap.pwd }
		${membersMap.name }
		${membersMap.email }
		
		<!-- ArrayList + HashMap 에 저장한거 출력, 변수사용 -->
		${membersList[0].id }
	</body>
</html>