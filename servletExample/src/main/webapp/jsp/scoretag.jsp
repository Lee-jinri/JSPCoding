<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- scoreBean score = new ScoreBean(); 과 같음 --%>
<jsp:useBean id="score" class="com.javabean.ScoreBean" scope="page" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>JSP 예제 : scoretag.jsp </title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
	</head>
	<body>
		<h3>JavaBeans를 이용한 학생의 이름과 성적의 저장과 조회 예제</h3>
		<% request.setCharacterEncoding("utf-8"); %>
		<hr />
		
		<%-- 
		<h4>이름과 성적을 JavaBeans ScoreBean에 저장</h4>
		<p>
			이름 : 홍길동, 성적 : 85
		</p>
		<jsp:setProperty name="score" property="name" value="홍길동" />
		<jsp:setProperty name="score" property="point" value="85"/>
		<hr />
		
		
		<h4>폼에서 전달받은 이름과 성적을 JavaBeans ScoreBean에 저장</h4>
		
		<jsp:setProperty name="score" property="name" value="<%=request.getParameter(\"name\") %>" />
		<jsp:setProperty name="score" property="point" value="<%=Integer.parseInt(request.getParameter(\"point\")) %>" /> 	
		
		
		<!-- param은 자동으로 형변환해서 parseInt할 필요 없음 -->
		<jsp:setProperty name="score" property="name" param="name" />
		<jsp:setProperty name="score" property="point" param="point" />
		--%>
		
		
		<%--property명과 파라미터의 이름이 같으면 param 생략 가능 
		<jsp:setProperty name="score" property="name" />
		<jsp:setProperty name="score" property="point" />
		--%>
		
		<%-- 모든 속성과 파라미터 이름이 같으면 property에 * 하면 됨 --%>
		<jsp:setProperty property="*" name="score"/>
		
		
		<h4>JavaBeans ScoreBean에 저장된 정보를 조회 출력</h4>
		<p>
			<%-- out.print(score.getName()); 과 같은 표현. --%>
			이름 : <jsp:getProperty property="name" name="score"/><br />
			성적 : <jsp:getProperty property="point" name="score"/><br />
			학점 : <jsp:getProperty property="grade" name="score" />	
		</p>
		 
		

	</body>
</html>