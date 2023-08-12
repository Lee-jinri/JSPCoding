<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false" %>

<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>표현 언어</title>
		
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
		<h1>표현 언어로 여러가지 데이터 출력하기</h1>
		<h1>
			\${100}: ${100} <br>
			\${"안녕하세요"}: ${"안녕하세요" }<br>
			\${10+1 }:${10+1 }<br>
			\${"10"+1 }:${"10"+1 }<br> <!-- 문자열을 숫자로 자동 변환 -->
			\${null+10}:${null+10}<br> <!-- null+10 = 10 -->

			<%-- 문자열과 숫자를 더할 수 없습니다.
			\${"안녕" + 11 }:${"안녕" + 11 }<br>
			\${"hello" +"world" } : ${"hello" + "world" }<br> --%>
		</h1>	
		
		<h1>산술 연산자
		\${10+10 }:${10+10 }<br>
		\${20-10 }:${20-10 }<br>
		\${10*10 }:${10*10 }<br>
		\${100/9 }:${100/9 }<br>
		\${100 div 9} : ${100 div 9}<br>
		\${100%9 } : ${100%9 }<br>
		\${100 mod 9 }:${100 mod 9 }<br>
		</h1>
		
		<h1>비교 연산자
		\${10==10 }:${10==10 }<br>
		\${10 eq 10 }:${10 eq 10 }<br>
		
		\${"hello"=="hello" }:${"hello"=="hello" }<br>
		\${"hello" eq "hello" }:${"hello"eq"hello" }<br>
		
		\${20 != 10 } : ${20!=10 }<br>
		\${20 ne 10 } : ${20 ne 10 }<br>
		
		\${"hello"!="apple" } : ${"hello"!="apple" }<br>
		\${"hello" ne"apple" }:${"hello" ne "apple" }<br>
		
		\${10 < 10 } : ${10 < 10 }<br>
		\${10 lt 10 } : ${10 lt 10 }<br>
		
		\${100 > 10 } : ${100 > 10 }<br>
		\${100 gt 10 } : ${100 gt 10 }<br>
		
		\${100 <= 10 } : ${100 <= 10 }<br>
		\${100 le 10 } : ${100 le 10 }<br>
		
		\${100 >= 10 } : ${100 >= 10 }<br>
		\${100 ge 10 } : ${100 >= 10 }<br>
		</h1>
		
		<h1>
		논리 연산자 <br>
		\${(10==10) && (20==20) } : ${(10==10) && (20==20) }<br>
		\${(10==10) and (10==10) } : ${(10==10) and (10==10) }<br>
		
		\${(10==10) || (20 != 30) } : ${(10==10) || (20 != 30) }<br>
		\${(10==10) or (20 != 30) } : ${(10==10) or (20 != 30) }<br>
		
		\${!(20==10) }:${!(20==10) }<br>
		\${not (20==10) }:${!(20==10) }<br>
		
		\${!(20!=10) }:${!(20!=10) }<br>
		\${not(20!=10) }:${not(20!=10) }<br>
		</h1>
	</body>
</html>