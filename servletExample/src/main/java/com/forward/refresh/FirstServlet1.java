package com.forward.refresh;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet1
 */
@WebServlet("/FirstRefresh")
public class FirstServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		// Refresh�� �ð��� �󸶵��� ������ �ٸ� ������ �����Ѵ�. 10���Ŀ� secondRefresh ����
		response.addHeader("Refresh", "10;url=SecondRefresh");
	}

}
