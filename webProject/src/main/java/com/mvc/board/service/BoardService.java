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
	
	/* 글 목록 조회, 검색 처리 제외 
	public ArrayList<BoardVO> boardList(){
		// DAO의 boardList 메소드를 호출
		ArrayList<BoardVO> list = dao.boardList();
		return list;
	}*/
	
	/* 글 목록 조회, 검색 처리 */
	public List<BoardVO> boardList(BoardVO vo){
		// DAO의 boardList 메소드를 호출
		List<BoardVO> list = dao.boardList(vo);
		return list;
	}

	/* 글 입력 */
	public boolean boardInsert(BoardVO vo) {
		boolean result = dao.boardInsert(vo);
		return result;
	}
	
	/* 조회수 증가 */
	public void readCount(String num) {
		dao.readCount(num);
	}

	/* 글 상세 페이지 */
	public BoardVO boardDetail(String num) {
		BoardVO vo = dao.boardDetail(num);
		vo.setContent(vo.getContent().toString().replaceAll("\n", "<br />"));
		return vo;
	}
	
	/* 비밀번호 확인 */
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
