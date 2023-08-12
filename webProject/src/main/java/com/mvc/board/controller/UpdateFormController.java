package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class UpdateFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num"); // 수정할 글 번호
		String code = request.getParameter("code");  // 수정처리에 문제가 생기면 코드를 받아옴, 무슨 문제가 생겼는지 코드로 확인
		
		BoardService service = BoardService.getInstance();
		BoardVO data = service.updateForm(num);  // service의 updateForm메소드에 num을 매개변수로 전달
		
		request.setAttribute("updateData", data); // service 실행 후 VO에 저장된 값 data를 속성에 설정 (이게 결과값)
		
		if(code != null && code.equals("1")) {
			request.setAttribute("errorMsg", "수정완료에 문제가 있습니다. 잠시 후 다시 시도해주세요.");
		}
		
		return "/board/updateForm";  // 수정페이지를 보여주는 jsp 
	}

}
