/* 함수명 : chkSubmit(유효성 체크 대상, 메시지 내용) 
 * 출력영역 : alert로
 * 예시 : if(!chkSubmit($('#keyword'),"검색이름")) return; 
 */
 
function chkSubmit(item, msg){
	if(item.val().replace(/\s/g,"")==""){
		alert (msg+" 입력해주세요.");
		item.val("");
		item.focus();
		return false;
	} else{
		return true;
	}
}

/* 함수명: chkData(유효성 체크 대상, 메시지 내용)
 * 출력영역 : alert로
 * 예시 : if (!chkData("#keyword","검색어를")) return;
 */
 
function chkData(item, msg){
	if($(item).val().replace(/\s/g,"")==""){
		alert(msg+" 입력해주세요");
		$(item).val();
		$(item).focus();
		return false;
	}else{
		return true;
	}
}

/* dataCheck(유효성 체크 대상, 출력 영역, 메시지 내용) 대상만 받아옴 */ 
function dataCheck(item, out, msg){
	if($(item).val().replace(/\s/g,"")==""){
		$(out).html(msg + " 입력해주세요.");
		$(item).val("");
		return false;
	}else{
		return true;
	}
}

/* formCheck(유효성 체크 대상, 출력 영역, 메시지 내용) 객체로 받아옴($(item))이 아니라 item */
function formCheck(main, item, msg){
	if(main.val().replace(/\s/g,"")==""){
		item.html(msg +  " 입력해주세요");
		main.val("");
		return false;
	}else{
		return true;
	}
}


