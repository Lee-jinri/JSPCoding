<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
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
		<form action="result.jsp">
			아이디 : <input type="text" size=20 /><br>
			비밀번호 : <input type="password" size=20 /><br>
			<input type="submit" value="로그인" /><input type="reset" value="다시 입력">
		</form>
	
		<br><br>
		<!-- 
		직접 컨텍스트 이름 입력
		<a href="http://localhost:8080/pro13/test01/memberForm.jsp">회원가입하기</a>
		request의 getContextPath() 메서드 사용
		<a href="<%=request.getContextPath() %>/test01/memberForm.jsp">회원가입하기</a>
		
		pageContext의 속성인 request하위의 contextPath 속성으로 컨텍스트 이름을 가져옴-->
		<a href="${pageContext.request.contextPath }/test01/memberForm.jsp">회원가입하기</a>
		
	</body>
</html>