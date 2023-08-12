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
		// ���� ��û�� null �� ó��
		if(search == null) {
			search="all";
		}
		
		BoardVO vo = new BoardVO();
		vo.setSearch(search);
		vo.setKeyword(request.getParameter("keyword"));
		
		// ���� ȣ���Ϸ��� getInstance() �ؾ� �ּҰ� ���� �� ����.
		BoardService service = BoardService.getInstance();
		// ������ list�� ��ȯ����
		// ArrayList<BoardVO> list = service.boardList();  �˻��� �ƴϸ� �Ű����� �ʿ����
		
		// �Խ��� ���ڵ� �˻� �� �˻��� ���� �ܾ �Ű������� ����
		List<BoardVO> list = service.boardList(vo); 
		
		// �Ӽ��� list��� �̸����� list�� ����
		request.setAttribute("list", list);
		
		// �����ְ� ���� jsp������ WEBINF/board/getBoardList.jsp�ε� webINF�� �����ϱ� �����ο� Ȯ���� ������.
		// viewResolver���� �����ζ� Ȯ���� �߰��Ұ���
		return "/board/getBoardList";
	}

}
