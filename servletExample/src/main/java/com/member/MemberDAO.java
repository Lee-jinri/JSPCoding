package com.member;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	// 데이터베이스 연결 관련 상수 선언
	private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
	private static final String USER = "javauser";
	private static final String PASSWD = "java1234";
	
	// 하나의 인스턴스만 생성할 수 있는 싱글톤 패턴 정의, 1.memberDAO의 생성자가 private이어야함 2.인스턴스값을 외부로 반환할 수 있는 메소드 
	private static MemberDAO instance = null;
	
	public static MemberDAO getInstance() {
		if(instance == null) 
			instance = new MemberDAO();
		return instance;
	}
	
	private MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException{
		Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWD);
		return con;
	}
	
	/********************************************
	 * memberList() 메서드 : 관리자 페이지 회원 리스트
	 * @return ArrayList<MemberVO> 리턴
	 ********************************************/
	
	public ArrayList<MemberVO> memberList(){ // memberList메소드 만듦, 반환 타입이 ArrayList 안에 저장될 데이터 타입은 MemberVO
		ArrayList<MemberVO> list = new ArrayList<MemberVO>(); // ArrayList 인스턴스 생성
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null; // vo값 담기위한 vo의 인스턴스 참조변수 ..??
		
		try {
			con = getConnection(); // 커넥션 객체 얻음
			StringBuffer query = new StringBuffer();
			
			query.append("SELECT REGEXP_REPLACE(m_id, '.', '*', 4) AS m_id "); // 마지막에 띄어쓰기 없으면 오류남
			query.append(",m_id as m_passwd"); // 비밀번호 필드는 비어있으니까 m_id를 비밀번호에 넣어버려~~
			query.append(",REGEXP_REPLACE(m_name, '.', '*', 2, 1) AS m_name ");
			query.append(",REGEXP_REPLACE(m_tel,'(\\d{3})\\-(\\d{3,4})\\-(\\d{4})','\\1-****-\\3') as m_tel ");
			query.append(",CONCAT('***',SUBSTR(m_email, 4, LENGTH(m_email))) as m_email ");
			query.append(",to_char(reg_date,'yyyy-mm-dd hh24:mi:ss') reg_date ");
			query.append("FROM t_member");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				vo = new MemberVO();
				vo.setM_id(rs.getString("m_id")); //resultSet에서 m_id라는 이름의 memberVO 반환받아옴
				vo.setM_passwd(rs.getString("m_passwd")); // 비밀번호가 아니라 마스킹처리 되지 않은 아이디임.
				vo.setM_name(rs.getString("m_name"));
				vo.setM_email(rs.getString("m_email"));
				vo.setM_tel(rs.getString("m_tel"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				
				list.add( vo );
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( rs != null) rs.close();
				if( pstmt != null) pstmt.close();
				if( con != null) con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	/***********************************************
	 * memberDelete() 메서드 : 회원 삭제 메서드
	 * @param (String m_id)
	 * @return int 자료형 리턴
	 ***********************************************/
	
	public int memberDelete(String m_id) { // m_id 자체를 매개변수로 가져옴
 		Connection con = null;
 		PreparedStatement pstmt = null; // 쿼리 실행할 수 있게 해주는 것
 		int result = 0;
 		
 		try { // 성공하면
 			con = getConnection(); // getConnection()은 커넥션을 얻기위한 별도의 메서드 
 			StringBuffer query = new StringBuffer();
 			query.append("DELETE FROM t_member WHERE m_id = ?"); // ?는 삭제할 m_id 변수 (무엇이 올지 알 수 없음)
 			
 			pstmt = con.prepareStatement(query.toString()); // 쿼리문을 pstmt에 담음
 			pstmt.setString(1, m_id); // 1번째 ?를 m_id 문자로 설정
 			result = pstmt.executeUpdate(); // 쿼리실행문
 		}catch(Exception e) { // 실패하면 실패한 이유를 알려죵, 결과도 X
 			e.printStackTrace();
 			result = 0; 
 		}finally { 
 			try { // 성공하면 pstmt, con close
 				if(pstmt!=null) pstmt.close();
 				if(con!= null) con.close();
 			}catch(SQLException e) { // 실패하면 실패한 이유를 알려죵
 				e.printStackTrace();
 			}
 		}
 		return result;
	}
	
	/******************************************
	 * login() 메서드 : 로그인 처리
	 * @param (MemberVO vo)
	 * @return MemberVO 자료형 리턴.
	 *****************************************/
	public MemberVO login(MemberVO vo) {
		// 사용자가 로그인하기위해 입력한 아이디와 비밀번호
		System.out.println(vo); // 정확히 입력됐는지 확인, MemberVo의 toString() 나옴
		
		Connection con = null; // DB 연결 
		PreparedStatement pstmt = null; // 쿼리문을 전달합니당
		ResultSet rs = null; // 결과 값이 들어갈 것, DBMS의 값을 담는 클래스
		MemberVO memVO = null; // memberVO 객체 생성
		
		try {
			con = getConnection(); // DB 연결할 객체 생성
			StringBuffer query = new StringBuffer(); // 쿼리문을 버퍼로 전달(한줄씩)
			// 사용자가 입력한 아이디와 비밀번호로 조회를 해서 맞는 컬럼이 있는지 확인하는 것
			query.append("select m_id, m_name, m_email from t_member "); // 끝에 공백 필수
			query.append("where m_id = ? and m_passwd = ?");
			
			// prepareStatement() 메서드에 SQL문을 전달해서 pstmt 객체 생성
			pstmt = con.prepareStatement(query.toString()); // connection으로 연결
			pstmt.setString(1, vo.getM_id()); // 1번째 ?에는 사용자가 입력한 id를
			pstmt.setString(2, vo.getM_passwd()); // 2번째 ?에는 사용자가 입력한 passwd를 
			// executeQuery() 메서드 호출 , 설정한 SQL 실행 -> resultSet에 들어감
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 커서로 이동
				memVO = new MemberVO(); // ResultSet의 값이 memVO에 setter로 설정됨 
				memVO.setM_id(rs.getString("m_id"));
				memVO.setM_name(rs.getString("m_name"));
				memVO.setM_email(rs.getString("m_email"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( rs!=null) rs.close(); // null이 아닌게 정상
				if( pstmt!=null) pstmt.close();
				if( con!=null) con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return memVO;
	}
}
