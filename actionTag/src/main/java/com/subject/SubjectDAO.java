package com.subject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SubjectDAO {
	
	private static SubjectDAO instance = null;
	
	public static SubjectDAO getInstance() {
		if(instance == null) {
			instance = new SubjectDAO();
		}
		return instance;
	}
	
	// �����ڿ� �ƹ��͵� ����� �ʿ� ����
	private SubjectDAO() {}
	
	// Ŀ�ؼ� Ǯ�� Connection ��ü�� �� : DB ���� ���� �������� �����ϴ� �޼ҵ忡�� ���
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle18c");
		return ds.getConnection();
	}
	
	// �а� ���̺��� ��� ���ڵ带 ��ȯ�ϴ� �޼ҵ�
	public ArrayList<SubjectVO> getSubjectList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SubjectVO> list = new ArrayList<SubjectVO>();
		SubjectVO vo = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select no, s_num, s_name from subject order by no");
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			
			// ResultSet�� ������� ��� ���� ������ SubjectVO ��ü�� ����
			while (rs.next()) {
				vo = new SubjectVO();
				vo.setNo(rs.getInt("no"));
				vo.setS_num(rs.getString("s_num"));
				vo.setS_name(rs.getString("s_name"));
				
				list.add(vo);
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
