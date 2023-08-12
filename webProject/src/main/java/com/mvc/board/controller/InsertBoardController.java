package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class InsertBoardController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String path = null;
		
		// 1. 사용자에게 입력받은 제목, 작성자, 글내용, 비밀번호를 vo에 설정
		BoardVO vo = new BoardVO();
		vo.setTitle(request.getParameter("title"));
		vo.setAuthor(request.getParameter("author"));
		vo.setContent(request.getParameter("content"));
		vo.setPasswd(request.getParameter("passwd"));
		
		// 2. 서비스 객체의 메서드 호출
		BoardService service = BoardService.getInstance();
		boolean result = service.boardInsert(vo);
		
		if(result == true) {  // 입력 성공
			path = "/board/getBoardList.do";  // 게시판 리스트 요청
		} else { // 입력 실패
			path = "/board/insertForm";  // 입력화면으로 이동
			request.setAttribute("errorMsg", "등록 완료에 문제가 있습니다. 잠시 후 다시 시도해주세요.");
		}
		
		// 3. dispatcher에 전달
		return path;
	}

}
