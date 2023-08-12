package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendRedirectTestServlet
 */

// 이 매핑 정보로 서블릿을 찾음
@WebServlet("/portalSite")
public class SendRedirectTestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String param = request.getParameter("site");
		
		if(param.equals("naver")) {
			response.sendRedirect("http://www.naver.com");
		}else if(param.equals("daum")) {
			response.sendRedirect("http://www.daum.net");
		}else if(param.equals("google")) {
			response.sendRedirect("http://www.google.com");
		}else if(param.equals("w3chools")) {
			response.sendRedirect("http://w3schools.com/");
		}
	}

	

}
