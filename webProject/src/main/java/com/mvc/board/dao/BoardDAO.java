package com.mvc.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.mvc.common.util.JDBCTemplate.*;
import com.mvc.board.vo.BoardVO;

public class BoardDAO {
	private static BoardDAO instance = null;
	
	public static BoardDAO getInstance() {
		if(instance==null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	private BoardDAO() {}
	
	/****************************************************
	 * boardList() 메서드 : 게시판 목록 조회(검색 처리 제외)
	 * @return ArrayList<BoardVO> 리턴.
	 ****************************************************
	
	public ArrayList<BoardVO> boardList(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection(); // JDBC getConnection() 사용
			
			//쿼리문이 길기 때문에 StringBuffer 사용
			StringBuffer query = new StringBuffer();
			query.append("SELECT num, author, title, ");
			query.append("to_char(writeday, 'YYYY/MM/DD') writeday, ");
			query.append("readcnt, repRoot, repStep, repIndent From board "); // 띄어쓰기 해야됨
			query.append(" order by repRoot desc, repStep asc");
			
			// 쿼리문을 실행
			pstmt = conn.prepareStatement(query.toString());
			// 결과를 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// VO에 값 설정
				BoardVO data = new BoardVO();
				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setWriteday(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setRepRoot(rs.getInt("repRoot"));
				data.setRepStep(rs.getInt("repStep"));
				data.setRepIndent(rs.getInt("repIndent"));
				
				list.add(data);
			} // end while
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return list;
	}// 조회문 끝
	*/
	
	
	/****************************************************
	 * boardList() 메서드 : 게시판 목록 조회(검색 처리)
	 * @return List<BoardVO> 리턴.
	 ****************************************************/
	
	public List<BoardVO> boardList(BoardVO vo){
		// 인터페이스<타입> 참조변수 = new 구현클래스<>(); 
		List<BoardVO> list = new ArrayList<BoardVO>();
		// List<BoardVO> list = new List<>(); 리스트는 인터페이스, 추상메소드가 있기때문에 구현클래스로 사용불가 인스턴스 생성X
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;  // 입력 수정 삭제에는 불필요, 조회문에만 사용
		
		try {
			conn = getConnection(); // JDBC getConnection() 사용
			
			/*쿼리문이 길기 때문에 StringBuffer 사용*/
			StringBuffer query = new StringBuffer();
			query.append("SELECT num, author, title, ");
			query.append("to_char(writeday, 'YYYY/MM/DD') writeday, ");
			query.append("readcnt, repRoot, repStep, repIndent From board "); // 띄어쓰기 해야됨
			
			if("title".equals(vo.getSearch())) {   				// 검색 대상이 제목일 경우
				query.append(" WHERE title LIKE ? ");
			} else if ("author".equals(vo.getSearch())) {		// 검색 대상이 작성자일 경우
				query.append(" WHERE author LIKE ? ");
			} else if ("content".equals(vo.getSearch())) {		// 검색 대상이 내용일 경우
				query.append(" WHERE content LIKE ? ");
			}
			
			query.append(" order by repRoot desc, repStep asc");
			
			// 쿼리문을 실행
			pstmt = conn.prepareStatement(query.toString());
			
			if(!"all".equals(vo.getSearch())) { // all이 아니면
				pstmt.setString(1, "%"+vo.getKeyword()+"%" ); // 바인딩 변수에 검색어를 추가함. %는 와일드카드(0 또는 하나이상의 글자가 포함됨)
			}
			// 결과를 ResultSet에 담음
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// VO에 값 설정
				BoardVO data = new BoardVO();  // 반복문안에 명시해야 반복해서 vo를 생성하고 값을 설정할 수 있음
				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setWriteday(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setRepRoot(rs.getInt("repRoot"));
				data.setRepStep(rs.getInt("repStep"));
				data.setRepIndent(rs.getInt("repIndent"));
				
				list.add(data);
			} // end while
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return list;
	}// 조회문 끝
	
	
	/***********************************************
	 * boardInsert() 메서드 : 게시물 등록
	 * @return boolean 리턴
	 **********************************************/
	
	public boolean boardInsert(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("insert into board( num, author, title, content, reproot, repstep, repindent, passwd ) ");
			query.append("values ( board_seq.nextval , ?, ?, ?, board_seq.currval, 0, 0, ? )");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getAuthor());   // 1번째 ?에 사용자가 입력한(vo에 설정된) author 입력 
			pstmt.setString(2, vo.getTitle());    // 2번째 ?에 사용자가 입력한 title 입력
			pstmt.setString(3, vo.getContent());  // 3번째 ?에 사용자가 입력한 content 입력
			pstmt.setString(4, vo.getPasswd());   // 4번째 ?에 사용자가 입력한 passwd 입력
			
			int value = pstmt.executeUpdate(); // 쿼리 입력, 성공하면 1 실패하면 0
			
			if (value == 1) result = true;		
		}catch (Exception e) {
			e.printStackTrace();
			result = false;
		}finally {
			close(pstmt);
			close(conn);
		}
		return result; // BoardService에 리턴됨. 
	}

	/***************************************************
	 * readCount() 메서드 : 조회수 증가 처리 메서드.
	 * @param 게시물 번호.
	 **************************************************/
	public void readCount(String num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("UPDATE board SET readcnt = readcnt + 1 ");
			query.append("WHERE num = ?");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
			close(pstmt);
		}
		
	}

	/*********************************************************
	 * boardDetail() 메서드 : 상세 페이지 처리 메서드
	 * @param 게시물 번호
	 * @return BoardVO 리턴
	 *********************************************************/
	
	public BoardVO boardDetail(String num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO data = new BoardVO();
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT num, author, title, content, ");
			query.append("TO_CHAR(writeday, 'YYYY-MM-DD HH24:MI:SS') writeday, ");
			query.append("readcnt, repRoot, repIndent, ");
			query.append("repStep FROM board WHERE num = ?");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setTitle(rs.getString("title"));
				data.setAuthor(rs.getString("author"));
				data.setContent(rs.getString("content"));
				data.setWriteday(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setRepRoot(rs.getInt("repRoot"));
				data.setRepStep(rs.getInt("repStep"));
				data.setRepIndent(rs.getInt("repIndent"));
			} // end if
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(conn);
			close(pstmt);
		}
		return data;
	}
	
	/****************************************************************
	 * boardPasswdChk() 메서드 : 비밀번호 조회 메서드.
	 * @param 게시물 번호, 비밀번호.
	 * @return int 리턴
	 ****************************************************************/
	public int boardPasswdChk(String num, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT NVL((SELECT 1 FROM board WHERE num = ? AND passwd = ?), 0) as result FROM dual");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num)); //  num은 글번호
			pstmt.setString(2, passwd); // 사용자가 입력한 비밀번호
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("result"); // 비밀번호 일치하면 1, 불일치 0 반환
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		return result;
	}
	
	/*******************************************************
	 * boardUpdate() 메서드 : 게시물 수정 처리 메서드.
	 * @param BoardVO
	 * @return 
	 *******************************************************/
	public boolean boardUpdate(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean success = false;
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("UPDATE board SET title = ?, content = ? ");
			if(vo.getPasswd()!="") query.append(", passwd = ? ");
			query.append("WHERE num = ? ");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			
			if(vo.getPasswd() != "") { // 새로운 비밀번호로 변경
				pstmt.setString(3, vo.getPasswd());
				pstmt.setInt(4, vo.getNum());
			}else { // 새로운 비밀번호 설정하지 않음, 기존 비밀번호 사용
				pstmt.setInt(3, vo.getNum());
			}
			
			int count = pstmt.executeUpdate(); // update된 행의 수 = 1 , num은 중복될 수 없기때문에 1임
			if(count == 1)success = true;
		}catch(Exception e) {
			e.printStackTrace();
			success = false; 
		}finally {
			close(pstmt);
			close(conn);
		}
		return success;
	}// end update

	/******************************************************
	 * boardDelete() 메서드 : 게시물 삭제 처리 메서드
	 * @param 게시물 번호, 비밀번호
	 *******************************************************/

	public void boardDelete(String num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			StringBuffer query = new StringBuffer();
			query.append("DELETE FROM board WHERE num = ? ");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.executeUpdate();  // 쿼리 수행
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
	} // end delete 

	/**************************************
	 * makeReply() 메서드 : 답변글의 기존 repStep 1씩 증가
	 **************************************/
	public void makeReply(int root, int step) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("UPDATE board SET repStep = repStep + 1 ");
			query.append("WHERE repRoot = ? AND repStep > ? ");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, root);
			pstmt.setInt(2, step);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
			close(pstmt);
		}
	}
	
	/**************************************
	 * replyInsert() 메서드 : 답변 입력 처리
	 **************************************/
	public boolean replyInsert(BoardVO vo) {
		makeReply(vo.getRepRoot(),vo.getRepStep());
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO board(num, title, author, ");
			query.append("content, repRoot, repStep, repIndent, passwd) ");
			query.append("values( board_seq.nextval, ?, ?, ?, ?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getAuthor());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getRepRoot());
			pstmt.setInt(5, vo.getRepStep() + 1);
			pstmt.setInt(6, vo.getRepIndent() + 1);
			pstmt.setString(7, vo.getPasswd());
			int count = pstmt.executeUpdate();
			
			/* 최근에 작성한 글이 먼저 나옴 order by repRoot desc
			 * <답변글>
			 * repRoot 그룹 : 기존 글번호와 기존 글에 답변글을 그룹화하도록 같은 번호로 설정한당
			 * repIndent 계층 : [답변]은 1, [답변][답변]은 2
			 * repStep 위치 : 기존 글에 답변글이 여러개 달렸을 때 1,2,3으로 순서를 지정, 먼저 쓴 글에 1씩 추가됨, 
			 * 				  최근에 작성한 답변글이 먼저 나옴 order by repStep asc 
			 * */
			
			if(count == 1) result = true;			
		}catch(Exception e) {
			e.printStackTrace();
			result = false;
		}finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}//end reply
	
	

} // 클래스 끝
