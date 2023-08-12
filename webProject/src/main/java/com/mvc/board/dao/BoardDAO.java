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
	 * boardList() �޼��� : �Խ��� ��� ��ȸ(�˻� ó�� ����)
	 * @return ArrayList<BoardVO> ����.
	 ****************************************************
	
	public ArrayList<BoardVO> boardList(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection(); // JDBC getConnection() ���
			
			//�������� ��� ������ StringBuffer ���
			StringBuffer query = new StringBuffer();
			query.append("SELECT num, author, title, ");
			query.append("to_char(writeday, 'YYYY/MM/DD') writeday, ");
			query.append("readcnt, repRoot, repStep, repIndent From board "); // ���� �ؾߵ�
			query.append(" order by repRoot desc, repStep asc");
			
			// �������� ����
			pstmt = conn.prepareStatement(query.toString());
			// ����� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// VO�� �� ����
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
	}// ��ȸ�� ��
	*/
	
	
	/****************************************************
	 * boardList() �޼��� : �Խ��� ��� ��ȸ(�˻� ó��)
	 * @return List<BoardVO> ����.
	 ****************************************************/
	
	public List<BoardVO> boardList(BoardVO vo){
		// �������̽�<Ÿ��> �������� = new ����Ŭ����<>(); 
		List<BoardVO> list = new ArrayList<BoardVO>();
		// List<BoardVO> list = new List<>(); ����Ʈ�� �������̽�, �߻�޼ҵ尡 �ֱ⶧���� ����Ŭ������ ���Ұ� �ν��Ͻ� ����X
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;  // �Է� ���� �������� ���ʿ�, ��ȸ������ ���
		
		try {
			conn = getConnection(); // JDBC getConnection() ���
			
			/*�������� ��� ������ StringBuffer ���*/
			StringBuffer query = new StringBuffer();
			query.append("SELECT num, author, title, ");
			query.append("to_char(writeday, 'YYYY/MM/DD') writeday, ");
			query.append("readcnt, repRoot, repStep, repIndent From board "); // ���� �ؾߵ�
			
			if("title".equals(vo.getSearch())) {   				// �˻� ����� ������ ���
				query.append(" WHERE title LIKE ? ");
			} else if ("author".equals(vo.getSearch())) {		// �˻� ����� �ۼ����� ���
				query.append(" WHERE author LIKE ? ");
			} else if ("content".equals(vo.getSearch())) {		// �˻� ����� ������ ���
				query.append(" WHERE content LIKE ? ");
			}
			
			query.append(" order by repRoot desc, repStep asc");
			
			// �������� ����
			pstmt = conn.prepareStatement(query.toString());
			
			if(!"all".equals(vo.getSearch())) { // all�� �ƴϸ�
				pstmt.setString(1, "%"+vo.getKeyword()+"%" ); // ���ε� ������ �˻�� �߰���. %�� ���ϵ�ī��(0 �Ǵ� �ϳ��̻��� ���ڰ� ���Ե�)
			}
			// ����� ResultSet�� ����
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// VO�� �� ����
				BoardVO data = new BoardVO();  // �ݺ����ȿ� ����ؾ� �ݺ��ؼ� vo�� �����ϰ� ���� ������ �� ����
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
	}// ��ȸ�� ��
	
	
	/***********************************************
	 * boardInsert() �޼��� : �Խù� ���
	 * @return boolean ����
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
			pstmt.setString(1, vo.getAuthor());   // 1��° ?�� ����ڰ� �Է���(vo�� ������) author �Է� 
			pstmt.setString(2, vo.getTitle());    // 2��° ?�� ����ڰ� �Է��� title �Է�
			pstmt.setString(3, vo.getContent());  // 3��° ?�� ����ڰ� �Է��� content �Է�
			pstmt.setString(4, vo.getPasswd());   // 4��° ?�� ����ڰ� �Է��� passwd �Է�
			
			int value = pstmt.executeUpdate(); // ���� �Է�, �����ϸ� 1 �����ϸ� 0
			
			if (value == 1) result = true;		
		}catch (Exception e) {
			e.printStackTrace();
			result = false;
		}finally {
			close(pstmt);
			close(conn);
		}
		return result; // BoardService�� ���ϵ�. 
	}

	/***************************************************
	 * readCount() �޼��� : ��ȸ�� ���� ó�� �޼���.
	 * @param �Խù� ��ȣ.
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
	 * boardDetail() �޼��� : �� ������ ó�� �޼���
	 * @param �Խù� ��ȣ
	 * @return BoardVO ����
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
	 * boardPasswdChk() �޼��� : ��й�ȣ ��ȸ �޼���.
	 * @param �Խù� ��ȣ, ��й�ȣ.
	 * @return int ����
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
			pstmt.setInt(1, Integer.parseInt(num)); //  num�� �۹�ȣ
			pstmt.setString(2, passwd); // ����ڰ� �Է��� ��й�ȣ
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("result"); // ��й�ȣ ��ġ�ϸ� 1, ����ġ 0 ��ȯ
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
	 * boardUpdate() �޼��� : �Խù� ���� ó�� �޼���.
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
			
			if(vo.getPasswd() != "") { // ���ο� ��й�ȣ�� ����
				pstmt.setString(3, vo.getPasswd());
				pstmt.setInt(4, vo.getNum());
			}else { // ���ο� ��й�ȣ �������� ����, ���� ��й�ȣ ���
				pstmt.setInt(3, vo.getNum());
			}
			
			int count = pstmt.executeUpdate(); // update�� ���� �� = 1 , num�� �ߺ��� �� ���⶧���� 1��
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
	 * boardDelete() �޼��� : �Խù� ���� ó�� �޼���
	 * @param �Խù� ��ȣ, ��й�ȣ
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
			pstmt.executeUpdate();  // ���� ����
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
	} // end delete 

	/**************************************
	 * makeReply() �޼��� : �亯���� ���� repStep 1�� ����
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
	 * replyInsert() �޼��� : �亯 �Է� ó��
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
			
			/* �ֱٿ� �ۼ��� ���� ���� ���� order by repRoot desc
			 * <�亯��>
			 * repRoot �׷� : ���� �۹�ȣ�� ���� �ۿ� �亯���� �׷�ȭ�ϵ��� ���� ��ȣ�� �����Ѵ�
			 * repIndent ���� : [�亯]�� 1, [�亯][�亯]�� 2
			 * repStep ��ġ : ���� �ۿ� �亯���� ������ �޷��� �� 1,2,3���� ������ ����, ���� �� �ۿ� 1�� �߰���, 
			 * 				  �ֱٿ� �ۼ��� �亯���� ���� ���� order by repStep asc 
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
	
	

} // Ŭ���� ��
