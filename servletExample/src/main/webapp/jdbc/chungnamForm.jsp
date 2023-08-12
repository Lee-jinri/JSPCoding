<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		
		<title>chungnamForm</title>
		
		<link rel="shortcut icon" href="../image/icon.png" />
		<link rel="apple-touch-icon" href="../image/icon.png" />
		
		<!--[if lt IE 9]>
		<script src="../js/html5shiv.js"></script>
		<![endif]-->
		
		<!-- jQuery 파일을 다운받아 로드하는 방법 -->
		<script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="../js/common.js"></script>
		
		<style type="text/css">
			#main{text-align: center;}
			table{width: 600px; border:1px solid black;}
			th{text-align:left;}
			tr{border-bottom: 1px solid black;}
			#submit, #reset, #list {text-align:center;}
		</style>
		
		<script type="text/javascript">
			$(function(){
				// 입력완료 버튼 제어
				$("#submit").click(function(){	
					if(!chkSubmit($('#mng_no'),"구분 번호를 ")) return;
					else if(!chkSubmit($('#local_nm'),"지역명을 ")) return;
					else if(!chkSubmit($('#type'),"타입을 ")) return;
					else if(!chkSubmit($('#nm'),"명소명을 ")) return;
					else if(!chkSubmit($('#nm_sub'),"명소 부제목을 ")) return;
					else if(!chkSubmit($('#addr'),"주소를 ")) return;
					else if(!chkSubmit($('#lat'),"lat 좌표(경도) ")) return;
					else if(!chkSubmit($('#lng'),"lng 좌표(위도) ")) return;
					else if(!chkSubmit($('#description'),"상세설명을 ")) return;
					else if(!chkSubmit($('#list_img'),"이미지을 ")) return;
					else{
						$("#chungnam").attr({
							"method" : "post",
							"action" : "/servletExmple/insert"
						});
						$("#chungnam").submit(); 
					}
					
				});
				
				// 다시쓰기 버튼 제어
				$("#reset").click(function(){
					$("#chungnam").each(function(){ // form의 하위 원소수만큼 반복
						this.reset();
					})
				});
				
				// 목록 버튼 제어
				$("#listBtn").click(function(){
					location.href = "/sevletExample/select";
				});
			})	
		</script>
	</head>
	
	<body>
		<h3>충남관광 - 관광명소 등록 화면</h3>
		<form name="chungnam" id="chungnam">
			<table summary="충남 정보 등록을 위한 입력 페이지">
				<colgroup>
					<col width="100"></col>
					<col width="500"></col>
				</colgroup>
				<thead>
					<tr><th colspan="2">충남 정보 등록</th>	</tr>
				</thead>
				<tbody>
					<tr>
						<th class="ls3"> 구분 번호</th>
						<th> <input type="text" name="mng_no" id="mng_no" placeholder="번호 입력"></th>
					</tr>		
					<tr>
						<th class="ls5">지 역 명</th>
						<td><input type="text" name="local_nm" id="local_nm" placeholder="지역명 입력"></td>
					</tr>
					<tr>
						<th class="ls2">타입(분류)</th>
						<td><input type="text" name="type" id="type" placeholder="타입 입력"></td>
					</tr>
					<tr>
						<th class="ls5">명 소 명</th>
						<td><input type="text" name="nm" id="nm" placeholder="제목 입력"></td>
					</tr>
					<tr>
						<th class="ls2">명소명(부)</th>
						<td><input type="text" name="nm_sub" id="nm_sub" placeholder="부제목 입력"></td>
					</tr>
					<tr>
						<th class="ls3">명소 주소</th>
						<td><input type="text" name="addr" id="addr" placeholder="주소 입력"></td>
					</tr>
					<tr>
						<th class="ls3">위치 정보</th>
						<td><input type="text" name="lat" id="lat" placeholder="lat 좌표(경도)"><br />
							<input type="text" name="lng" id="lng" placeholder="lng 좌표(위도)"></td>
					</tr>	
					<tr>
						<th class="ls3">명소 설명</th>
						<td><textarea rows="7" cols="30" name="description"></textarea></td>
					</tr>
					<tr>
						<th class="ls3">이 미 지</th>
						<td><input type="text" name="list_img" id="list_img"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="button" id="submit">입력완료</button>
							<button type="button" id="reset">다시쓰기</button>
							<button type="button" id="listBtn">목록</button>
						</td>
					</tr>
				</table>
		</form>
	</body>
</html>