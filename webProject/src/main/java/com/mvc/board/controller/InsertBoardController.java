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
		
		// 1. ����ڿ��� �Է¹��� ����, �ۼ���, �۳���, ��й�ȣ�� vo�� ����
		BoardVO vo = new BoardVO();
		vo.setTitle(request.getParameter("title"));
		vo.setAuthor(request.getParameter("author"));
		vo.setContent(request.getParameter("content"));
		vo.setPasswd(request.getParameter("passwd"));
		
		// 2. ���� ��ü�� �޼��� ȣ��
		BoardService service = BoardService.getInstance();
		boolean result = service.boardInsert(vo);
		
		if(result == true) {  // �Է� ����
			path = "/board/getBoardList.do";  // �Խ��� ����Ʈ ��û
		} else { // �Է� ����
			path = "/board/insertForm";  // �Է�ȭ������ �̵�
			request.setAttribute("errorMsg", "��� �Ϸῡ ������ �ֽ��ϴ�. ��� �� �ٽ� �õ����ּ���.");
		}
		
		// 3. dispatcher�� ����
		return path;
	}

}
