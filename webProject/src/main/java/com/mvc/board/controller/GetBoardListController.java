package com.mvc.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class GetBoardListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String search = request.getParameter("search");
		// 최초 요청시 null 값 처리
		if(search == null) {
			search="all";
		}
		
		BoardVO vo = new BoardVO();
		vo.setSearch(search);
		vo.setKeyword(request.getParameter("keyword"));
		
		// 서비스 호출하려면 getInstance() 해야 주소값 얻을 수 있음.
		BoardService service = BoardService.getInstance();
		// 서비스의 list를 반환받음
		// ArrayList<BoardVO> list = service.boardList();  검색이 아니면 매개변수 필요없음
		
		// 게시판 레코드 검색 시 검색할 대상과 단어를 매개변수로 전달
		List<BoardVO> list = service.boardList(vo); 
		
		// 속성을 list라는 이름으로 list를 설정
		request.setAttribute("list", list);
		
		// 보여주고 싶은 jsp문서는 WEBINF/board/getBoardList.jsp인데 webINF에 있으니까 공통경로와 확장자 제외함.
		// viewResolver에서 공통경로랑 확장자 추가할거임
		return "/board/getBoardList";
	}

}
