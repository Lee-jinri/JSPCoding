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

// 매핑 정보 
@WebServlet("/connect")
public class DBConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// run on server 자체가 Get 방식임 (브라우저에 직접 URL을 요청하는 게 Get 방식) -> 그래서 doGet을 오버라이딩 함
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 오라클 서버와 연동해서 결과를 처리하는 작업
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>데이터베이스 드라이버와 Oracle 서버 연결 점검 프로그램 </h3><hr />");
		Connection con=null;
		try {
			
			// 드라이버 이름
			String driverName = "oracle.jdbc.OracleDriver";
			// 오라클 서버 이름, 포트번호, 서비스 이름
			String dbURL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			
			// 드라이버 클래스를 메모리에 적재 -> 드라이버 매니저가 생성됨.
			Class.forName(driverName); 
			
			// 사용자 정보를 직접 입력, 커넥션 객체를 사용.
			con = DriverManager.getConnection(dbURL, "javauser","java1234" );
			
			out.println("Oracle 서버에 성공적으로 접속했습니다");
			con.close();
					
		}
		catch (Exception e) {
			out.println("Oracle 서버 접속에 문제가 있습니다. <hr />");
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
