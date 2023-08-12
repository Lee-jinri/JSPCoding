<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>답변글 작성</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		<!-- 부트스트랩 사용 -->
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/include/dist/css/bootstrap-theme.css" />
		<style type="text/css">
			#content{resize: none;} /* textarea 크기 변경 불가하게 설정함 */
		</style>
		<script type="text/javascript" src="/include/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		<!-- 이 부트스트랩은 script 파일이 먼저 준비되어 있어야하기 때문에 js 다음에 작성해야함 -->
		<script type="text/javascript" src="/include/dist/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
			$(function(){
				let conText = $("#content").val();
				$("#content").val("\n\n\n\n>>>>>>>>>>>>>>>>>>>>>기존글 내용>>>>>>>>>>>>>>>>\n\n"+conText);
				
				// 실패했을 때만 실행됨
				let msg = "${errorMsg}";
				if(msg!=""){
					alert(msg);
				}
				
				$("#boardInsert").click(function(){
					// 입력값 체크
					if(!chkData("#author","이름을")) return;
					else if(!chkData("#title","제목을")) return;
					else if(!chkData("#content","작성할 내용을")) return;
					else if(!chkData("#passwd","비밀번호를")) return;
					else {
						$("#f_replyForm").attr({
							"method" : "post",
							"action" : "/board/insertReply.do"
						});
						$("#f_replyForm").submit();
					}
				});
				
				$("#boardListBtn").click(function(){
					location.href="/board/getBoardList.do"; // 목록 페이지로 이동
				});
			});
		</script>
	</head>
	<body>
		<div class="container">
			<div class="text-center"><h3>답변글 작성</h3></div>
			
			<div class="text-center">
				<form id="f_replyForm" name="f_replyForm">
					<!-- 답변 글 필요 -->
					<input type="hidden" name="num" value="${reply.num}">
					<input type="hidden" name="repRoot" value="${reply.repRoot}">
					<input type="hidden" name="repStep" value="${reply.repStep }">
					<input type="hidden" name="repIndent" value="${reply.repIndent }">
					
					<table class="table table-bordered">
						<tr>
							<td colspan="2" class="text-left">게시물 글 번호 ${reply.num } &nbsp; (조회수 : ${reply.readcnt }))</td>
						</tr>
						<tr>
							<td class="text-center">작성자</td>
							<td><input type="text" name="author" id="author" class="form-control" /></td>
						</tr>
						<tr>
							<td class="text-center">글제목</td>
							<td><input type="text" name="title" id="title" value="[답변] ${reply.title }" class="form-control"/></td>
						</tr>
						<tr>
							<td class="text-center">글내용</td>
							<td><textarea name="content" id="content" maxlength="2000"
							rows="8" class="form-control">${reply.content }</textarea></td>
						</tr>
						<tr>
							<td class="text-center">비밀번호</td>
							<td><input type="password" name="passwd" id="passwd" class="form-control"/></td>
						</tr>
					</table>
				</form>
			</div>
			
			<div class="text-right">
				<button type="button" class="btn btn-primary btn-sm" id="boardInsert">저장</button>
				<button type="button" class="btn btn-primary btn-sm" id="boardListBtn">목록</button>
			</div>
		</div>
	</body>
</html>