package com.mvc.common.controller;

import java.util.HashMap;
import java.util.Map;

import com.mvc.board.controller.DeleteBoardController;
import com.mvc.board.controller.DetailBoardController;
import com.mvc.board.controller.GetBoardListController;
import com.mvc.board.controller.InsertBoardController;
import com.mvc.board.controller.InsertFormController;
import com.mvc.board.controller.InsertReplyController;
import com.mvc.board.controller.PasswdCheckController;
import com.mvc.board.controller.ReplyFormController;
import com.mvc.board.controller.UpdateBoardController;
import com.mvc.board.controller.UpdateFormController;

public class HandlerMapping {
	 
	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		
		// 답변형 게시판 처리
		// 키-요청(String 타입), 밸류-실제 로직을 처리할 클래스(Controller)  
		// /board/getBoardList.do*"를 요청하면 GetBoardListController가 실행됨.
		// 컨트롤러 -> 서비스 -> dao(결과반환) -> dao에게 반환받음 -> 컨트롤러
		
		mappings.put("/board/getBoardList.do", new GetBoardListController()); // 글 목록
		mappings.put("/board/insertForm.do", new InsertFormController()); // 글 입력
		mappings.put("/board/insertBoard.do", new InsertBoardController()); // 입력된 글 db에 입력
		mappings.put("/board/detailBoard.do", new DetailBoardController()); // 상세 페이지
		mappings.put("/board/passwdCheck.do", new PasswdCheckController()); // 비밀번호 확인
		mappings.put("/board/updateForm.do", new UpdateFormController());  // 수정 화면
		mappings.put("/board/updateBoard.do", new UpdateBoardController()); // 수정 처리
		mappings.put("/board/deleteBoard.do", new DeleteBoardController()); // 삭제 처리
		
		mappings.put("/board/replyForm.do", new ReplyFormController()); // 답변글 입력
		mappings.put("/board/insertReply.do", new InsertReplyController()); // 답변글 처리
	}
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
