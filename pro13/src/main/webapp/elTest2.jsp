<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    isELIgnored="false" %>
    
<jsp:useBean id="m1" class="sec01.ex01.MemberBean" scope="page" />
<jsp:setProperty name="m1" property="name" value="이순신" />
<jsp:useBean id="m2" class="java.util.ArrayList" scope="page" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>표현 언어의 여러가지 연산자들</title>
		
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
		empty 연산자 : 비어있으면 true 비어있지 않으면 false
		<h2>
			\${empty m1 } : ${empty m1 } <br>
			\${not empty m1 } : ${not empty m1 }<br><br>
			
			\${empty m2 } : ${empty m2 } <br>
			\${not empty m2 } : ${not empty m2 }<br><br>
			
			\${empty "hello" } : ${empty "hello" }<br>
			\${empty null } : ${empty null }<br>
			\${empty "" } : ${empty "" }<br>
		</h2>
	</body>
</html>