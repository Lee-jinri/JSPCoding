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
			let buttonCheck = 0; // 수정버튼과 삭제버튼을 구별하기 위한 변수, 수정이면 1 삭제면 2
			
			$(function(){
				
				// 비밀번호 확인 화면 숨김
				$("#pwdChk").css("visibility","hidden");
				
				/* 수정 버튼 클릭 시 처리 이벤트 */
				$("#updateForm").click(function(){
					$("#pwdChk").css("visibility","visible");
					$("#msg").text("작성 시 입력한 비밀번호를 입력해주세요.").css("color","#000099");
					buttonCheck = 1;
				});
				
				/* 삭제 버튼 클릭 시 처리 이벤트 */
				$("#boardDelete").click(function(){
					$("#pwdChk").css("visibility","visible");
					$("#msg").text("작성 시 입력한 비밀번호를 입력해주세요.").css("color","#000099");
					buttonCheck = 2;
				});
				
				/* 비밀번호 확인 버튼 클릭 시 처리 이벤트 */
				$("#pwdBut").click(function(){
					boardPwdConfirm(); // 길어서 함수 사용, common.js에 있음
				});
				
				/* 취소 버튼 클릭 시 처리 이벤트 */
				$("#pwdButCancel").click(function(){
					$("#pwdChk").css("visibility","hidden");
					buttonCheck = 0;
				});
				
				/* 답변 버튼 클릭 시 처리 이벤트 */
				$("#boardReply").click(function(){
					$("#f_data").attr({
						"method" : "post",
						"action" : "/board/replyForm.do"
					});
					$("#f_data").submit();
				});
				
				/* 목록 버튼 클릭 시 처리 이벤트 */
				$("#boardListBtn").click(function(){
					location.href = "/board/getBoardList.do";
				});
			});
			
			/* 비밀번호 클릭시 실질적인 처리를 하는 함수 */
			function boardPwdConfirm(){
				if (!chkData('#passwd',"비밀번호를")) return;
				else{
					$.ajax({
						url : "/board/passwdCheck.do",  // 전송 url, 요청 url dispatcherServlet이 받음
						type : "post",					// 전송 시 메소드 방식
						data : $("#f_pwd").serialize(),  // 전달할 파라미터 값, 폼 전체의 데이터 전송 "num="+$("#num").val() +"&paswd="+$("#passwd").val()
						dataType : "text",				// 응답받을 문서 타입
						error : function(){ 			// 실행 시 오류 발생
							alert('시스템 오류 입니다. 관리자에게 문의하세요');
						},
						success : function(resultData){ // 정상 실행
							let goUrl = "";				// 이동경로를 저장할 변수
							if (resultData==0){			// 비밀번호 일치하지 않음
								$("#msg").text("비밀번호가 일치하지 않습니다.").css("color","red");
								$("#passwd").select();	// #passwd에 focus
							}else if (resultData == 1){	// 비밀번호 일치
								$("#msg").text("");
								console.log("비밀번호 일치");
								
								if(buttonCheck==1){ // 수정
									goUrl = "/board/updateForm.do";
									$("#f_pwd").attr("action",goUrl); // form에 속성 설정
									$("#f_pwd").submit();
								}else if(buttonCheck==2){ // 삭제버튼 클릭
									if(confirm("정말 삭제하시겠습니까?")){
										goUrl = "/board/deleteBoard.do";
										$("#f_data").attr("action",goUrl);
										$("#f_data").submit();
									}
								}	
							}
						}
					});
				}
			}
			
		</script>
	</head>
	<body>
		<div class="container">
			<div class="text-center"><h3>게시판 상세화면</h3></div>
			
			<form name="f_data" id="f_data" method="post">
				<input type="hidden" name="num" value="${detail.num }">
			</form>
		
		<%--=============== 비밀번호 확인 버튼 및 버튼 추가 시작 =============== --%>
		<div id="boardPwdBut" class="row text-center">
			<div id="pwdChk" class="col-md-9 text-left">
				<form name="f_pwd" id="f_pwd" class="form-inline">
					<!-- hidden에 사용자의 비밀번호를 미리 저장해서 숨겨놓음 -->
					<input type="hidden" name="num" id="num" value="${detail.num }"/>
					<label for="passwd" id="1_pwd">비밀번호 : </label>
					<input type="password" name="passwd" id="passwd" class="form-control"/>
					
					<button type="button" id="pwdBut" class="btn btn-default btn-sm" >확인</button>
					<button type="button" id="pwdButCancel" class="btn btn-default btn-sm">취소</button>
					<span id="msg"></span>
				</form>
			</div>
			<div class="col-md-3 text-right">
				<button type="button" id="updateForm" class="btn btn-primary btn-sm">수정</button>
				<button type="button" id="boardDelete" class="btn btn-primary btn-sm">삭제</button>
				<button type="button" id="boardReply" class="btn btn-primary btn-sm">답변</button>
				<button type="button" id="boardListBtn" class="btn btn-primary btn-sm">목록</button>
			</div>
		</div>
		<%--=============== 비밀번호 확인 버튼 및 버튼 추가 종료 =============== --%>
		
		<%--===================== 상세 정보 보여주기 ========================--%>
		<div class="text-center">
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td class="col-md-3">글번호</td>
						<!-- detail은 DetailBoardController의 속성 -->
						<td class="col-md-3 text-left">${detail.num }<label>(조회수: ${detail.readcnt})</label></td>
						<td class="col-md-3">작성일</td>
						<td class="col-md-3 text-left">${detail.writeday }</td>
					</tr>
					<tr>
						<td class="col-md-3">작성자</td>
						<td colspan="3" class="col-md-9 text-left">${detail.author }</td>
					</tr>	
					<tr>
						<td class="col-md-3">글제목</td>
						<td colspan="3" class="col-md-9 text-left">${detail.title }</td>
					</tr>
					<tr class="table-height">
						<td class="text-valign">글내용</td>
						<td colspan="3" class="text-left">${detail.content }</td>
					</tr>
				</tbody>
			</table>
			
		</div>
		</div>
	</body>
</html>