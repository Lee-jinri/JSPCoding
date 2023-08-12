package com.chungnam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/* DAO (Data Access Object) Ŭ���� ������ ó���� �ñ����� �ܰ��̴�.
 * 
 * CRUD ���α׷� ����
 * �⺻���� ������ ó�� ����� �Է�(Create, insert), ��ȸ(Read �Ǵ� Retrieve, Select), ����(Update), 
 * ����(Delete) ����� ������ �����ͺ��̽� ���α׷� 
 * */

// �泲 ���̺��� �Է�, ����, ������ ���� ������ �ٷ��. ����� ��,��,���� �ʿ��ϸ� ���� ����� dao�� ��������.
public class ChungnamDAO {
	
	// �����ͺ��̽� ���� ���� ��� ���� (����, ���)
	private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
	private static final String USER = "javauser";
	private static final String PASSWD = "java1234";
	
	// Ŭ���� �ڽ��� Ÿ������ ���� �ʵ� ����
	private static ChungnamDAO instance = null;
	// �ܺο��� ȣ���� �� �ִ� ���� �޼ҵ��� getInstance() �����Ͽ� �ν��Ͻ�(�泲dao�� ������)�� ��ȯ. 
	public static ChungnamDAO getInstance() {
		if(instance == null) { 				// �ν��Ͻ� ��ü�� �������� �ʾҴٸ�
			instance = new ChungnamDAO();   // ������ ȣ���Ͽ� �ν��Ͻ� ����
		}
		return instance;  					// ������ �ν��Ͻ� ��ȯ
	}
	
	// �ܺο��� new �����ڷ� �����ڸ� ȣ���� �� ������ �������� ���� ������ ��� (private ������(){})
	private ChungnamDAO() {
		try {
			// �ν��Ͻ� ����� �޸𸮿� ����
			Class.forName("oracle.jdbc.driver.OracleDriver");  // oracle.jdbc.OracleDriver�� ���� ����
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	// getConnection �޼ҵ� ����
	private Connection getConnection() throws SQLException{ // getConnection�� ȣ���ϴ� �����޼��忡�� ����ó���ϰ� ��.
		// ������ ���̽� ������ �� ��� ��ġ�� � ������� �����ͺ��̽��� �����Ұ���
		Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWD);
		return con;
	}
	
	/******************************************************
	 * getChungnamList() �޼��� : �泲 ������ ��ȸ
	 * @return ArrayList<ChungnamVO> ����.
	 ******************************************************/
	
	// �޼��� ���� : VO�� ������ ������� ArrayList �÷��� ���
	public ArrayList<ChungnamVO> getChungnamList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ChungnamVO> list = new ArrayList<ChungnamVO>();
		ChungnamVO vo = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select mng_no, local_nm, type, nm, nm_sub, description, list_img from chungnam");
		
		// ����ó��
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// �� ���� ������ ������ VO ��ü ����.
				vo = new ChungnamVO();
				vo.setMng_no(rs.getInt("mng_no"));
				vo.setLocal_nm(rs.getString("local_nm"));
				vo.setType(rs.getString("type"));
				vo.setNm(rs.getString("nm"));
				vo.setNm_sub(rs.getString("nm_sub"));
				vo.setDescription(rs.getString("description"));
				vo.setList_img(rs.getString("list_img"));
				
				// ����Ʈ ����
				list.add(vo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// rs, pstmt, con ���� ����
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
	 * chungnamInsert() �޼��� : chungnam ���̺��� ���ڵ� �Է� �޼���
	 * @param ChungnamVO.
	 * @return int �ڷ��� ����
	 ************************************************************/
	public int chungnamInsert(ChungnamVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INRO chungnam (mng_no, local_nm, type, nm, nm_sub, addr, lat, lng, description, list_img)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); // ����ڰ� ���� �Է��Ұ���
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
			
			rowCount = pstmt.executeUpdate(); // �������� ���������� ����Ǹ� ����� ���� ���� ��ȯ. (���� ������������ 0 �Ǵ� 1)
		} catch (SQLException se) {
			System.out.println("���� chungnamInsert error = [ " + se + " ]");
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
	 * chungnamDelete() �޼��� : chungnam ���̺��� ���ڵ� ���� �޼���
	 * @param ChungnamVO.
	 * @return int �ڷ��� ����.
	 ***************************************************************/
	
	public int chungnamDelete(ChungnamVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		// sql�� ������ �߰� ?�� mng_no(primary key)�� ������ �ٰ���(���ε� ����)
		sql.append("DELETE FROM chungnam WHERE mng_no = ?"); 
		int rowCount = 0;
		try {
			con = getConnection(); 
			pstmt = con.prepareStatement(sql.toString());
			
			// 1��(?�� �ϳ��ϱ� 1��)�� vo���� mng_no���� ������
			pstmt.setInt(1, vo.getMng_no());
			
			rowCount = pstmt.executeUpdate();
		
			// ���� �޼��� 
		} catch(SQLException se) {
			System.out.println("���� chungnamDelete error = [ " + se + " ]");
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
