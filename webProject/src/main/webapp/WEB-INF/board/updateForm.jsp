<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		
		<!-- 부트스트랩 사용 -->
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap-theme.css" />
		
		<script type="text/javascript" src="/include/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		<!-- 이 부트스트랩은 script 파일이 먼저 준비되어 있어야하기 때문에 js 다음에 작성해야함 -->
		<script type="text/javascript" src="/include/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function(){
				// 에러났을 때 보여줄 문구, 원래는 이렇게 하지않고 에러페이지를 따로 만들기 때문에 작성하지 않아도 됨.
				let msg = "${errorMsg}";;
				if(msg != ""){
					alert(msg);
				}
				
				
				$("#boardUpdate").click(function(){
					// console.log("수정 버튼 클릭");
					// 유효성 체크
					if (!chkData("#title","제목을")) return;
					else if (!chkData("#content","작성할 내용을")) return;
					else {
						$("#f_updateForm").attr({
							"method" : "post",
							"action" : "/board/updateBoard.do"
						});
						$("#f_updateForm").submit();
					}
				});
				
				// 목록 버튼 클릭
				$("#boardListBtn").click(function(){
					location.href = "/board/getBoardList.do";
				});
			});
		</script>
	</head>
	<body>
		<div class="container">
			<div class="text-center"><h3>글 수정</h3></div>
			
			<div class="text-center">
				<form id="f_updateForm" name="f_updateForm">
					<input type="hidden" name="num" id="num" value="${updateData.num }">
					
					<table class="table table-bordered">
						<tr>
							<td class="text-center">글번호</td>
							<td class="text-left">${updateData.num } <span>(조회수 : ${updateData.readcnt })</span></td>
							<td class="text-center">작성일</td>
							<td class="text-left">${updateData.writeday }</td>
						</tr>
						<tr>
							<td class="text-center">작성자</td>
							<td colspan="3" class="text-left">${updateData.author }</td>
						</tr>
						<tr>
							<td class="text-center">글제목</td>
							<td colspan="3"><input type="text" name="title" id="title" value="${updateData.title }" class="form-control"></td>
						</tr>
						<tr>
							<td class="text-center">글내용</td>
							<td colspan="3"><textarea name="content" id="content" rows="8"
							class="form-control">${updateData.content }</textarea></td>
						</tr>
						<tr>
							<td class="text-center">비밀번호</td>
							<td colspan="3"><input type="password" name="passwd" id="passwd"
							class="form-control" placeholder="기존 비밀번호가 아니라 수정할 비밀번호를 입력해주세요."></td>
						</tr>
					</table>
				</form>
			</div>
		
			<div class="text-right">
				<button type="button" id="boardUpdate" class="btn btn-primary btn-sm">수정</button>
				<button type="button" id="boardListBtn" class="btn btn-primary btn-sm">목록</button>
			</div>		
		</div>
	</body>
</html>