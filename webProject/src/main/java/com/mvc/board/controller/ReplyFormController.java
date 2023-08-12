package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class ReplyFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");  // �亯�� �ۼ��� �� ��ȣ
		String code = request.getParameter("code"); // �亯 ó�� ���н� ���������� ����� ����
		
		BoardService service = BoardService.getInstance();
		BoardVO vo = service.replyForm(num); // ������ ������ �޾ƿ�
		
		request.setAttribute("reply", vo); // ������ ������ �����ֱ� ���� �Ӽ� ����
		
		if(code != null && code.equals("1")) { // �亯ó�� ���� �� �Ӽ� �� ���� -> �亯 �Է� ȭ������ ������
			request.setAttribute("errorMsg", "�亯 ó���� ������ �ֽ��ϴ�. ��� �� �ٽ� �õ����ּ���.");
		}		
		
		return "/board/replyForm"; // replyForm.jsp�� �����ֵ��� ����3
	}

}
