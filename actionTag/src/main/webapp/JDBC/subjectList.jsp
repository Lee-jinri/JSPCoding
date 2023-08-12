<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList, com.subject.SubjectDAO, com.subject.SubjectVO" %>
<%
	// DAO 인스턴스 얻음
	SubjectDAO dao = SubjectDAO.getInstance();
	// dao의 메소드 getSubjectList를 ArrayList SubjectVO 타입으로 list란 이름에 담음
	ArrayList<SubjectVO> list = dao.getSubjectList();
	// list의 레코드 수가 count
	int counter = list.size();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>자바 빈즈를 이용한 테이블 조회 예제 : subjectList.jsp</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
	</head>
	<body>
		<h3>자바 빈즈 SubjectDAO를 통해 subject 테이블 조회 프로그램</h3>
		<hr />
		향상된 for문
		<div id="subjectList">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>학과번호</th>
						<th>학과명</th>
					</tr>
				</thead>
				<tbody>
				<% if(counter > 0) {// 조회된 레코드가 있다면 
					for(SubjectVO sub : list ){
					/* 향상된 for문 
					for(int i = 0; i<counter; i++){
						SubjectVO sub = list.get(i); */
				%>
						<tr class="tc" data-no="<%=sub.getNo() %>">
							<td><%= sub.getNo() %></td>
							<td><%= sub.getS_num() %></td>
							<td><%= sub.getS_name() %></td>
						</tr>
				<%
					 }
					}else { // 조회된 레코드 없음
				%>
						<tr>
							<td colspan="3" class="tc">조회된 학과 정보가 존재하지 않습니다.</td>
						</tr>
				<%	
					}
				%>
				</tbody>
			</table>
			<div class="tr">조회된 학과 수는 <span id="counter"><%=counter %></span>입니다.</div>
		</div>
	</body>
</html>