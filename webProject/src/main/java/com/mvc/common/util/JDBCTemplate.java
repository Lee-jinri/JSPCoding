package com.mvc.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {
	
	// �̱��� ���� : ��ü�� �ν��Ͻ��� 1���� �����Ǵ� ����
	private static JDBCTemplate instance = null;
	
	public static JDBCTemplate getInstance() {
		if(instance==null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	private JDBCTemplate() {}
	
	// Ŀ�ؼ� Ǯ�� Ŀ�ؼ� ����
	public static Connection getConnection() throws Exception{
		// DataSource ����, Ŀ�ؼ� Ǯ ���
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle18c");
		return ds.getConnection();
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
