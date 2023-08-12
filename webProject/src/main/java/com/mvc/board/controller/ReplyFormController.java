package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class ReplyFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");  // 답변을 작성할 글 번호
		String code = request.getParameter("code"); // 답변 처리 실패시 내부적으로 사용할 변수
		
		BoardService service = BoardService.getInstance();
		BoardVO vo = service.replyForm(num); // 기존글 정보를 받아옴
		
		request.setAttribute("reply", vo); // 기존글 정보를 보여주기 위한 속성 설정
		
		if(code != null && code.equals("1")) { // 답변처리 실패 시 속성 값 설정 -> 답변 입력 화면으로 포워드
			request.setAttribute("errorMsg", "답변 처리에 문제가 있습니다. 잠시 후 다시 시도해주세요.");
		}		
		
		return "/board/replyForm"; // replyForm.jsp를 보여주도록 리턴3
	}

}
