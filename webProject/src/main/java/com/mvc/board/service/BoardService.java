package com.mvc.board.service;

import java.util.List;

import com.mvc.board.dao.BoardDAO;
import com.mvc.board.vo.BoardVO;

public class BoardService {
	private static BoardService service = null;
	
	private BoardDAO dao = BoardDAO.getInstance();
	
	private BoardService() {}
	
	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
		}
		return service;
	}
	
	/* �� ��� ��ȸ, �˻� ó�� ���� 
	public ArrayList<BoardVO> boardList(){
		// DAO�� boardList �޼ҵ带 ȣ��
		ArrayList<BoardVO> list = dao.boardList();
		return list;
	}*/
	
	/* �� ��� ��ȸ, �˻� ó�� */
	public List<BoardVO> boardList(BoardVO vo){
		// DAO�� boardList �޼ҵ带 ȣ��
		List<BoardVO> list = dao.boardList(vo);
		return list;
	}

	/* �� �Է� */
	public boolean boardInsert(BoardVO vo) {
		boolean result = dao.boardInsert(vo);
		return result;
	}
	
	/* ��ȸ�� ���� */
	public void readCount(String num) {
		dao.readCount(num);
	}

	/* �� �� ������ */
	public BoardVO boardDetail(String num) {
		BoardVO vo = dao.boardDetail(num);
		vo.setContent(vo.getContent().toString().replaceAll("\n", "<br />"));
		return vo;
	}
	
	/* ��й�ȣ Ȯ�� */
	public int boardPasswdChk(String num, String passwd) {
		int result = dao.boardPasswdChk(num, passwd);
		return result;
	}

	public BoardVO updateForm(String num) {
		BoardVO vo = dao.boardDetail(num);
		return vo;
	}

	public boolean boardUpdate(BoardVO vo) {
		boolean result = dao.boardUpdate(vo);
		return result;
	}

	public void boardDelete(String num) {
		dao.boardDelete(num);
		
	}

	public BoardVO replyForm(String num) {
		BoardVO vo = dao.boardDetail(num);
		return vo;
	}
	
	public boolean replyInsert(BoardVO vo) {
		boolean result = dao.replyInsert(vo);
		return result;
	}

	
}
