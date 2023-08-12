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
		<script type="text/javascript">
			/*$(function(){
				$("#submitBtn").click(function(){
					if (!chkData('#title',"책제목을")) return false;
					else if(!chkData('#author',"책저자를")) return false;
					else if(!chkData('#publisher',"출판사를")) return false;
					else{
						$("#frm").attr({
							"method" : "post"
							"action" : "bookResult.jsp"
						});
						$("#frm").submit();
					}
					
				});
			});*/
		</script>
	</head>
	<body>
		<!-- <form id="frm"> -->
		<form action="bookResult.jsp" method="post">
			<label>책제목 : </label>
			<input type="text" name="title" id="title"><br>
			
			<label>책저자 : </label>
			<input type="text" name="author" id="author"><br>
			
			<label>출판사 : </label>
			<input type="text" name="publisher" id="publisher"><br>
			<button type="button" id="submitBtn">등록</button>
		</form>
		
	</body>
</html>