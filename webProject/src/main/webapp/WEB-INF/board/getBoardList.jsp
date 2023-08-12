<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>목록보기</title>
		
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
				/* 검색 후 검색 대상과 검색 단어 출력 */
				if('${param.keyword}'!=""){  // 검색을 했을 때만 실행, 처음에 사이트 들어가면 키워드 값이 없기때문에 실행 안됨
					$("#keyword").val('${param.keyword}');
					$("#search").val('${param.search}');
				}
				
				/* enter 눌렀을 때는 수행되지 않고 정확하게 버튼을 클릭했을 때 수행되게 함 (입력 양식 enter 제거) */
				$("#keyword").bind("keydown",function(event){
					if (event.keyCode == 13){  // 13번이 enter
						event.preventDefault();
						// $("#searchData").click();
					}
				});
				
				/* 검색 버튼 클릭 시 처리 이벤트 */
				$("#searchData").click(function(){
					if($("#search").val() != "all"){
						if(!chkData("#keyword","검색어를")) return;
					}else if($("#search").val() == "all"){ // all이면 검색어 필요없음, 검색어 초기화
						$("#keyword").val("");
					}
					$("#f_search").attr({
						"method" : "post",
						"action" : "/board/getBoardList.do"
					});
					$("#f_search").submit();
				});
				
				/* 제목 클릭 시 상세 페이지 이동을 위한 처리 이벤트 */
				$(".goDetail").click(function(){
					/* data-num="${vo.num}" 설정해놓았음.
					num이 기본키이기 때문에 조회할 때 사용해야하는데 사용자가 선택한 title의 num을 찾기 힘들기 때문에 설정한 거임. 
					설정 안하면 이렇게 해야됨. let num = $(this).parents("tr").children().eq(0).html();
					자기 자신의 부모 중 tr의 속성인 data-num을 선택 */
					let num = $(this).parents("tr").attr("data-num");
					// console.log("num = " + num); // 클릭했을 때 선택자가 정확한지 확인, 개발자 도구에 표시됨
					
					$("#num").val(num); // num의 값을 변수 num으로 설정, num은 detailForm에 있는 hidden, value를 전달함
					$("#detailForm").attr({
						"method" : "post",
						"action" : "/board/detailBoard.do"
					});
					$("#detailForm").submit();
				});
				
				// 글쓰기 버튼 클릭 시 처리 이벤트
				$("#writeForm").click(function(){
					location.href = "/board/insertForm.do";
					// 1 핸들러 매핑 2. 서브 컨트롤러 3. 컨트롤러 4. board/inserForm.jsp 실행
				});
				
				
			})
		</script>
	</head>
	<body>
		<div class="container">
			<div class="text-center"><h3>게시판 리스트</h3></div>
			<form name="detailForm" id="detailForm">
				<input type="hidden" name="num" id="num">
			</form>
			
			<%--============= 검색 기능 시작 ================ --%>
			<div id="boardSearch" class="text-right">
				<form id="f_search" name="f_search" class="form-inline">
					<div class="form-group">
						<label>검색 조건</label>
						<select id="search" name="search" class="form-control">
							<option value="all">전체</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="author">작성자</option>
						</select>
						<input type="text" name="keyword" id="keyword" placeholder="검색어를 입력하세요" class="form-control">
						<button type="button" id="searchData" class="btn btn-primary">검색</button>
					</div>
				</form>
			</div>
			<%--=============검색 기능 종료 ===============--%>
			
			
			
			<%--============= 리스트 시작 ================ --%>
			<div id="boardList">
				<table summary="게시판 리스트" class="table">
					<thead>
						<tr>
							<th class="col-md-1 text-center">번호</th>
							<th class="col-md-6 text-center">제목</th>
							<th class="col-md-2 text-center">작성자</th>
							<th class="col-md-2 text-center">날짜</th>
							<th class="col-md-1 text-center">조회수</th> 
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty list }">
								<c:forEach var="vo" items="${list }">
									<tr class="text-center" data-num="${vo.num }">
										<td>${vo.num }</td>
										<%-- <td class="text-left"><span class="goDetail">${vo.title }</span></td>--%>
										<td class="text-left">
											<c:if test="${vo.repStep>0}"><%-- 답변 글이면 --%>
												<c:forEach begin="1" end="${vo.repIndent }"><%--답변의 계층번호에 따라 공백설정(기본값 공백 3칸) --%>
													&nbsp;&nbsp;&nbsp;
												</c:forEach>
												<img src="/image/icon.png"><%-- 답변이미지 출력 --%>
											</c:if>
											<span class="goDetail">${vo.title }</span>
										</td>
										<td>${vo.author }</td>
										<td>${vo.writeday }</td>
										<td>${vo.readcnt }</td>
									</tr>
								</c:forEach>
							</c:when>
						<c:otherwise>
						<tr>
							<td colspan="5" class="text-center">등록된 게시물이 존재하지 않습니다.</td>
						</tr>
						</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			
			<%-- ===================== 글쓰기 버튼 출력 시작 ============================== --%>
			<div class="contentBtn text-right">
				<button type="button" id="writeForm" class="btn btn-primary btn-sm">글쓰기</button>
			</div>
		</div>
	</body>
</html>