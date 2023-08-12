package com.forward.dispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecondServlet3
 */
@WebServlet("/secondDispatcher")
public class SecondServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// dispatch는 클라이언트의 브라우저를 거치지 않고 서버에서 포워딩 진행, 
		// 클라이언트 웹브라우저에서 첫 번째 서블릿에 요청, 첫 번째 서블릿이 직접 두 번째 서블릿 포워드
		// 화면에 두 번째 서블릿이 나오지만 주소값은 첫 번째 서블릿의 주소값이 나옴
		// 다른 포워드는 클라이언트 웹브라우저에서 첫 번째 서블릿에 요청, 첫 번째 서블릿에서 브라우저에게 두 번째 서블릿 요청,
		// 클라이언트 웹브라우저에서 두 번째 서블릿포워드
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 전달받은 값을 변수에 대입, 파라미터로 받기
		/* String language = request.getParameter("language"); */
		
		// request의 속성 설정한 값 받기, getAttribute : Object타입으로 전달됨, String으로 강제 형변환~~
		String language = (String)request.getAttribute("language");
		String name = (String)request.getAttribute("name");
		String address = (String)request.getAttribute("address");
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>Despatcher 예제</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body><div>Despatcher을 이용한 redirect 실습입니다.</div>");
		out.println("<div>언어 : " + language + "</div>");
		out.println("<div>이름 : " + name + "</div>");
		out.println("<div>주소 : " + address + "</div>");
		out.println("</body></html>");
	}

}
