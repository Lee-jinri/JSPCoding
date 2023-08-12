<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>글쓰기</title>
		
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
				// InsertBoardController에 request.setAttribute("errorMsg", "등록 완료에 문제가 있습니다. 잠시 후 다시 시도해주세요."); 
				// 처음에 실행됐을 때 메세지가 나타나는 게 아니라 실행 실패해서 다시 돌아왔을 때 errorMsg에 값이 설정되기 때문에 실패했을 때만 msg가 나옴
				let msg = "${errorMsg}";
				if(msg != ""){
					alert(msg);
				}
				
				/* 저장 버튼 클릭 시 처리 이벤트 */
				$("#boardInsert").click(function(){
					if(!chkData("#author","이름을")) return;
					else if (!chkData("#title","제목을"))return;
					else if (!chkData("#content","작성할 내용을"))return;
					else if (!chkData("#passwd","비밀번호를")) return;
					else {
						$("#f_writeForm").attr({
							"method" : "post",
							"action" : "/board/insertBoard.do"
						});
						$("#f_writeForm").submit();	
					}
				});
			
				/* 목록 버튼 클릭 시 처리 이벤트 */
				$("#boardListBtn").click(function(){
					location.href = "/board/getBoardList.do";
				});
			});
			
			
		</script>
	</head>
	<body>
		<div class="container">
			<div class="text-center"><h3>게시판 입력화면</h3></div>
			
			<form class="form-horizontal" id="f_writeForm">
				<div class="form-group">
					<label for="author" class="col-sm-2 control-label">작 성 자</label>
					<div class="col-sm-10">
					<input type="text" class="form-control" id="author" placeholder="작성자 입력" name="author" maxlength="6">
					</div>
				</div>
				
				<div class="form-group">
					<label for="title" class="col-sm-2 control-label">글 제 목</label>
					<div class="col-sm-10">
					<input type="text" class="form-control" id="title" placeholder="글제목 입력" name="title">
					</div>
				</div>
				
				<div class="form-group">
					<label for="content" class="col-sm-2 control-label">글 내 용</label>
					<div class="col-sm-10">
					<textarea class="form-control" id="content" placeholder="글내용 입력" name="content" rows="8"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label for="passwd" class="col-sm-2 control-label">비밀번호</label>
					<div class="col-sm-10">
					<input type="password" class="form-control" id="passwd" placeholder="비밀번호 입력" name="passwd" >
					</div>
				</div>
			</form>
			<div class="text-right">
				<button type="button" class="btn btn-primary" id="boardInsert">저장</button>
				<button type="button" class="btn btn-primary" id="boardListBtn">목록</button>
			</div>
		</div>
	</body>
</html>