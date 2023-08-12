<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

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
		안녕하세요. 부모 JSP 입니다
		<br>
		<!-- 인클루드 액션 태그 : duke_image.jsp를 동적으로 포워딩 -->
		<jsp:include page="duke_image.jsp" flush="true">
			<!-- param 액션 태그 : duke_image.jsp로 이름과 파일 이름을 전달 -->
			<jsp:param name="name" value="듀크" />
			<jsp:param name="imgName" value="duke.png"/>
		</jsp:include>
		<br>
		안녕하세요. 부모 JSP 끝 입니다.
	</body>
</html>