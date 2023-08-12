package com.chungnam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/* DAO (Data Access Object) 클래스 데이터 처리의 궁극적인 단계이다.
 * 
 * CRUD 프로그램 구현
 * 기본적인 데이터 처리 기능인 입력(Create, insert), 조회(Read 또는 Retrieve, Select), 수정(Update), 
 * 삭제(Delete) 기능을 구현한 데이터베이스 프로그램 
 * */

// 충남 테이블의 입력, 수정, 삭제에 대한 내용을 다룬다. 멤버의 입,수,삭이 필요하면 따로 멤버의 dao를 만들어야함.
public class ChungnamDAO {
	
	// 데이터베이스 연결 관련 상수 선언 (정적, 상수)
	private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
	private static final String USER = "javauser";
	private static final String PASSWD = "java1234";
	
	// 클래스 자신의 타입으로 정적 필드 선언
	private static ChungnamDAO instance = null;
	// 외부에서 호츨할 수 있는 정적 메소드인 getInstance() 선언하여 인스턴스(충남dao의 참조값)를 반환. 
	public static ChungnamDAO getInstance() {
		if(instance == null) { 				// 인스턴스 객체가 생성되지 않았다면
			instance = new ChungnamDAO();   // 생성자 호출하여 인스턴스 생성
		}
		return instance;  					// 생성된 인스턴스 반환
	}
	
	// 외부에서 new 연산자로 생성자를 호출할 수 없도록 막기위해 접근 제한자 사용 (private 생성자(){})
	private ChungnamDAO() {
		try {
			// 인스턴스 만들어 메모리에 적재
			Class.forName("oracle.jdbc.driver.OracleDriver");  // oracle.jdbc.OracleDriver로 변경 가능
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	// getConnection 메소드 구현
	private Connection getConnection() throws SQLException{ // getConnection을 호출하는 상위메서드에서 예외처리하게 함.
		// 데이터 베이스 연동할 때 어느 위치에 어떤 사용자의 데이터베이스를 연동할건지
		Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWD);
		return con;
	}
	
	/******************************************************
	 * getChungnamList() 메서드 : 충남 데이터 조회
	 * @return ArrayList<ChungnamVO> 리턴.
	 ******************************************************/
	
	// 메서드 만듦 : VO를 여러개 담기위한 ArrayList 컬렉션 사용
	public ArrayList<ChungnamVO> getChungnamList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ChungnamVO> list = new ArrayList<ChungnamVO>();
		ChungnamVO vo = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select mng_no, local_nm, type, nm, nm_sub, description, list_img from chungnam");
		
		// 예외처리
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 한 행의 정보를 저장할 VO 객체 생성.
				vo = new ChungnamVO();
				vo.setMng_no(rs.getInt("mng_no"));
				vo.setLocal_nm(rs.getString("local_nm"));
				vo.setType(rs.getString("type"));
				vo.setNm(rs.getString("nm"));
				vo.setNm_sub(rs.getString("nm_sub"));
				vo.setDescription(rs.getString("description"));
				vo.setList_img(rs.getString("list_img"));
				
				// 리스트 리턴
				list.add(vo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// rs, pstmt, con 연결 해제
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/***********************************************************
	 * chungnamInsert() 메서드 : chungnam 테이블의 레코드 입력 메서드
	 * @param ChungnamVO.
	 * @return int 자료형 리턴
	 ************************************************************/
	public int chungnamInsert(ChungnamVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INRO chungnam (mng_no, local_nm, type, nm, nm_sub, addr, lat, lng, description, list_img)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); // 사용자가 값을 입력할거임
		int rowCount = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setInt(1, vo.getMng_no());
			pstmt.setString(2, vo.getLocal_nm());
			pstmt.setString(3, vo.getType());
			pstmt.setString(4, vo.getNm());
			pstmt.setString(5, vo.getNm_sub());
			pstmt.setString(6, vo.getAddr());
			pstmt.setDouble(7, vo.getLat());
			pstmt.setDouble(8, vo.getLng());
			pstmt.setString(9, vo.getDescription());
			pstmt.setString(10, vo.getList_img());
			
			rowCount = pstmt.executeUpdate(); // 쿼리문이 정상적으로 수행되면 적용된 행의 수를 반환. (현재 쿼리문에서는 0 또는 1)
		} catch (SQLException se) {
			System.out.println("쿼리 chungnamInsert error = [ " + se + " ]");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("error chungnamInsert = [ " + e+ " ]");
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	/***************************************************************
	 * chungnamDelete() 메서드 : chungnam 테이블의 레코드 삭제 메서드
	 * @param ChungnamVO.
	 * @return int 자료형 리턴.
	 ***************************************************************/
	
	public int chungnamDelete(ChungnamVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		// sql에 쿼리문 추가 ?는 mng_no(primary key)를 변수로 줄거임(바인딩 변수)
		sql.append("DELETE FROM chungnam WHERE mng_no = ?"); 
		int rowCount = 0;
		try {
			con = getConnection(); 
			pstmt = con.prepareStatement(sql.toString());
			
			// 1번(?가 하나니까 1번)에 vo에서 mng_no값을 꺼내옴
			pstmt.setInt(1, vo.getMng_no());
			
			rowCount = pstmt.executeUpdate();
		
			// 에러 메세지 
		} catch(SQLException se) {
			System.out.println("쿼리 chungnamDelete error = [ " + se + " ]");
			se.printStackTrace();
		} catch(Exception e) {
			System.out.println("error chungnamDelete = [ " + e + " ]");
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}
}
