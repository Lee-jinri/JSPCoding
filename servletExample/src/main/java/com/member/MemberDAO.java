package com.member;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	// �����ͺ��̽� ���� ���� ��� ����
	private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
	private static final String USER = "javauser";
	private static final String PASSWD = "java1234";
	
	// �ϳ��� �ν��Ͻ��� ������ �� �ִ� �̱��� ���� ����, 1.memberDAO�� �����ڰ� private�̾���� 2.�ν��Ͻ����� �ܺη� ��ȯ�� �� �ִ� �޼ҵ� 
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
	 * memberList() �޼��� : ������ ������ ȸ�� ����Ʈ
	 * @return ArrayList<MemberVO> ����
	 ********************************************/
	
	public ArrayList<MemberVO> memberList(){ // memberList�޼ҵ� ����, ��ȯ Ÿ���� ArrayList �ȿ� ����� ������ Ÿ���� MemberVO
		ArrayList<MemberVO> list = new ArrayList<MemberVO>(); // ArrayList �ν��Ͻ� ����
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null; // vo�� ������� vo�� �ν��Ͻ� �������� ..??
		
		try {
			con = getConnection(); // Ŀ�ؼ� ��ü ����
			StringBuffer query = new StringBuffer();
			
			query.append("SELECT REGEXP_REPLACE(m_id, '.', '*', 4) AS m_id "); // �������� ���� ������ ������
			query.append(",m_id as m_passwd"); // ��й�ȣ �ʵ�� ��������ϱ� m_id�� ��й�ȣ�� �־����~~
			query.append(",REGEXP_REPLACE(m_name, '.', '*', 2, 1) AS m_name ");
			query.append(",REGEXP_REPLACE(m_tel,'(\\d{3})\\-(\\d{3,4})\\-(\\d{4})','\\1-****-\\3') as m_tel ");
			query.append(",CONCAT('***',SUBSTR(m_email, 4, LENGTH(m_email))) as m_email ");
			query.append(",to_char(reg_date,'yyyy-mm-dd hh24:mi:ss') reg_date ");
			query.append("FROM t_member");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				vo = new MemberVO();
				vo.setM_id(rs.getString("m_id")); //resultSet���� m_id��� �̸��� memberVO ��ȯ�޾ƿ�
				vo.setM_passwd(rs.getString("m_passwd")); // ��й�ȣ�� �ƴ϶� ����ŷó�� ���� ���� ���̵���.
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
	 * memberDelete() �޼��� : ȸ�� ���� �޼���
	 * @param (String m_id)
	 * @return int �ڷ��� ����
	 ***********************************************/
	
	public int memberDelete(String m_id) { // m_id ��ü�� �Ű������� ������
 		Connection con = null;
 		PreparedStatement pstmt = null; // ���� ������ �� �ְ� ���ִ� ��
 		int result = 0;
 		
 		try { // �����ϸ�
 			con = getConnection(); // getConnection()�� Ŀ�ؼ��� ������� ������ �޼��� 
 			StringBuffer query = new StringBuffer();
 			query.append("DELETE FROM t_member WHERE m_id = ?"); // ?�� ������ m_id ���� (������ ���� �� �� ����)
 			
 			pstmt = con.prepareStatement(query.toString()); // �������� pstmt�� ����
 			pstmt.setString(1, m_id); // 1��° ?�� m_id ���ڷ� ����
 			result = pstmt.executeUpdate(); // �������๮
 		}catch(Exception e) { // �����ϸ� ������ ������ �˷���, ����� X
 			e.printStackTrace();
 			result = 0; 
 		}finally { 
 			try { // �����ϸ� pstmt, con close
 				if(pstmt!=null) pstmt.close();
 				if(con!= null) con.close();
 			}catch(SQLException e) { // �����ϸ� ������ ������ �˷���
 				e.printStackTrace();
 			}
 		}
 		return result;
	}
	
	/******************************************
	 * login() �޼��� : �α��� ó��
	 * @param (MemberVO vo)
	 * @return MemberVO �ڷ��� ����.
	 *****************************************/
	public MemberVO login(MemberVO vo) {
		// ����ڰ� �α����ϱ����� �Է��� ���̵�� ��й�ȣ
		System.out.println(vo); // ��Ȯ�� �Էµƴ��� Ȯ��, MemberVo�� toString() ����
		
		Connection con = null; // DB ���� 
		PreparedStatement pstmt = null; // �������� �����մϴ�
		ResultSet rs = null; // ��� ���� �� ��, DBMS�� ���� ��� Ŭ����
		MemberVO memVO = null; // memberVO ��ü ����
		
		try {
			con = getConnection(); // DB ������ ��ü ����
			StringBuffer query = new StringBuffer(); // �������� ���۷� ����(���پ�)
			// ����ڰ� �Է��� ���̵�� ��й�ȣ�� ��ȸ�� �ؼ� �´� �÷��� �ִ��� Ȯ���ϴ� ��
			query.append("select m_id, m_name, m_email from t_member "); // ���� ���� �ʼ�
			query.append("where m_id = ? and m_passwd = ?");
			
			// prepareStatement() �޼��忡 SQL���� �����ؼ� pstmt ��ü ����
			pstmt = con.prepareStatement(query.toString()); // connection���� ����
			pstmt.setString(1, vo.getM_id()); // 1��° ?���� ����ڰ� �Է��� id��
			pstmt.setString(2, vo.getM_passwd()); // 2��° ?���� ����ڰ� �Է��� passwd�� 
			// executeQuery() �޼��� ȣ�� , ������ SQL ���� -> resultSet�� ��
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // Ŀ���� �̵�
				memVO = new MemberVO(); // ResultSet�� ���� memVO�� setter�� ������ 
				memVO.setM_id(rs.getString("m_id"));
				memVO.setM_name(rs.getString("m_name"));
				memVO.setM_email(rs.getString("m_email"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( rs!=null) rs.close(); // null�� �ƴѰ� ����
				if( pstmt!=null) pstmt.close();
				if( con!=null) con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return memVO;
	}
}
