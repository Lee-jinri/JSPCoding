<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>회원가입 창</title>
		
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
		<form method="post" action="member3.jsp">
			<h1 style="text-align:center">회원 가입 창</h1>
			
			<table align="center">
				<tr>
					<td width="200">
						<p align="right">아이디</p>
					</td>
					<td width="400">
						<input type="text" name="id">
					</td>
				</tr>
				<tr>
					<td width="200">
						<p align="right">비밀번호</p>
					</td>
					<td width="400">
						<input type="password" name="pwd">
					</td>
				</tr>
				<tr>
					<td width="200">
						<p align="right">이름	</p>
					</td>
					<td width="400">
						<input type="text" name="name">
					</td>
				</tr>
				<tr>
					<td width="200">
						<p align="right">이메일</p>
					</td>
					<td width="400">
						<input type="text" name="email">
					</td>
				</tr>
				<tr>
					<td width="200">
						<p>&nbsp;</p>
					</td>
					<td width="400">
						<input type="submit" value="가입하기">
						<input type="reset" value="다시 입력">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>