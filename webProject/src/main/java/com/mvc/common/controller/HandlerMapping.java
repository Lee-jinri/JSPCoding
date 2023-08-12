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
		
		// �亯�� �Խ��� ó��
		// Ű-��û(String Ÿ��), ���-���� ������ ó���� Ŭ����(Controller)  
		// /board/getBoardList.do*"�� ��û�ϸ� GetBoardListController�� �����.
		// ��Ʈ�ѷ� -> ���� -> dao(�����ȯ) -> dao���� ��ȯ���� -> ��Ʈ�ѷ�
		
		mappings.put("/board/getBoardList.do", new GetBoardListController()); // �� ���
		mappings.put("/board/insertForm.do", new InsertFormController()); // �� �Է�
		mappings.put("/board/insertBoard.do", new InsertBoardController()); // �Էµ� �� db�� �Է�
		mappings.put("/board/detailBoard.do", new DetailBoardController()); // �� ������
		mappings.put("/board/passwdCheck.do", new PasswdCheckController()); // ��й�ȣ Ȯ��
		mappings.put("/board/updateForm.do", new UpdateFormController());  // ���� ȭ��
		mappings.put("/board/updateBoard.do", new UpdateBoardController()); // ���� ó��
		mappings.put("/board/deleteBoard.do", new DeleteBoardController()); // ���� ó��
		
		mappings.put("/board/replyForm.do", new ReplyFormController()); // �亯�� �Է�
		mappings.put("/board/insertReply.do", new InsertReplyController()); // �亯�� ó��
	}
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
