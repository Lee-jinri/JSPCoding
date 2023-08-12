<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
		<h2>표현언어 내장객체 cookie 이용</h2>
		<%
			// 쿠키 인스턴스 (키,값)
			Cookie c = new Cookie("userId","javajsp");
			// 클라이언트에게 응답할 때 쿠키도 같이 보낼게요~~
			response.addCookie(c);
		%>
			<hr />
			<h4>스크립트릿에서 직접 Cookie 이용</h4>
		<%
			// 쿠키를 쿠키배열에 담음
			Cookie[] cs = request.getCookies();
			
			if(!(cs == null)){
				for(Cookie ck : cs){
					if(ck.getName().equals("userId"))
						out.println("ck.getValue() 값 : " + ck.getValue());
				}
			}
		%>
		<hr />
		<h4>표현 언어 내장 객체 cookie 이용</h4>
		<%-- 쿠키객체의 userId라는 이름의 값 --%>
		\${cookie.userId.value} = ${cookie.userId.value}
		
		<h2>\${ header } : 결과</h2>
		<p> 요청정보 헤더의 정보들을 이름과 값으로 저장하고 있는 Map 객체.</p>
		<p>${ header }</p>
		<hr />
		\${ header["user-agent"] } = ${ header["user-agent"] }
		
		
	</body>
</html>