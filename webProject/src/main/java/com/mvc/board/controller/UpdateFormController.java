package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.board.service.BoardService;
import com.mvc.board.vo.BoardVO;
import com.mvc.common.controller.Controller;

public class UpdateFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num"); // ������ �� ��ȣ
		String code = request.getParameter("code");  // ����ó���� ������ ����� �ڵ带 �޾ƿ�, ���� ������ ������� �ڵ�� Ȯ��
		
		BoardService service = BoardService.getInstance();
		BoardVO data = service.updateForm(num);  // service�� updateForm�޼ҵ忡 num�� �Ű������� ����
		
		request.setAttribute("updateData", data); // service ���� �� VO�� ����� �� data�� �Ӽ��� ���� (�̰� �����)
		
		if(code != null && code.equals("1")) {
			request.setAttribute("errorMsg", "�����Ϸῡ ������ �ֽ��ϴ�. ��� �� �ٽ� �õ����ּ���.");
		}
		
		return "/board/updateForm";  // ������������ �����ִ� jsp 
	}

}
