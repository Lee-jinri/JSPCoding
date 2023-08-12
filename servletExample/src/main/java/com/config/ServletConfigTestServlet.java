package com.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletConfigTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 객체 인코딩 방식 , Post 방식을 사용할 때는 반드시 요청 객체의 인코딩 방식을 설정해주어야함!
		// web.xml에 charset의 기본 파라미터 값을 설정해두었음!
		String charset = getInitParameter("charset");
		request.setCharacterEncoding(charset); // request.setCharacterEncoding("UTF-8");
				
		// 응답 객체 타입과 인코딩 방식
		response.setContentType("text/html;charset=UTF-8");
				
		// 출력 객체
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
		out.println("<title>서블릿 초기값 설정(web.xml)으로 인코딩 설정 </title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body><h3>서블릿 초기값으로 인코딩 설정한 후 결과 출력</h3>");
		out.print("<h4>이름 : " +request.getParameter("name")+"</h4>");
		out.println("</body></html>");
			
		out.close();
	}

}
