package com.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBConnectServlet
 */

// ���� ���� 
@WebServlet("/connect")
public class DBConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// run on server ��ü�� Get ����� (�������� ���� URL�� ��û�ϴ� �� Get ���) -> �׷��� doGet�� �������̵� ��
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ����Ŭ ������ �����ؼ� ����� ó���ϴ� �۾�
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>�����ͺ��̽� ����̹��� Oracle ���� ���� ���� ���α׷� </h3><hr />");
		Connection con=null;
		try {
			
			// ����̹� �̸�
			String driverName = "oracle.jdbc.OracleDriver";
			// ����Ŭ ���� �̸�, ��Ʈ��ȣ, ���� �̸�
			String dbURL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			
			// ����̹� Ŭ������ �޸𸮿� ���� -> ����̹� �Ŵ����� ������.
			Class.forName(driverName); 
			
			// ����� ������ ���� �Է�, Ŀ�ؼ� ��ü�� ���.
			con = DriverManager.getConnection(dbURL, "javauser","java1234" );
			
			out.println("Oracle ������ ���������� �����߽��ϴ�");
			con.close();
					
		}
		catch (Exception e) {
			out.println("Oracle ���� ���ӿ� ������ �ֽ��ϴ�. <hr />");
			out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch (Exception e2) {
				out.println(e2.getMessage());
			}
		}
		out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
