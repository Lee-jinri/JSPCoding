package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet({ "/queryget", "/querypost" })
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 재정의
		// 응답할 문서 타입은 text/html;charset=utf-8입니다
		response.setContentType("text/html;charset=utf-8");
		
		// 출력 스트림 : 응답객체를 통해서 연동할 수 있는 getWriter()로 출력스트림 형성
		PrintWriter out = response.getWriter();
		
		// 사용자에게 전달받은 값 변수에 담음
		String userName = request.getParameter("name");
		int number = Integer.parseInt(request.getParameter("number"));
		
		out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
		out.println("<title>GET 방식과 POST 방식</title>");
		out.println("<body>");
		
		out.print("<h2>요청 방식 : " + request.getMethod() + "</h2>");
		out.print("<h2>요청 URI : " + request.getRequestURI() + "</h2>");
		
		out.print("<h2>당신의 이름은 " + userName + "이군요!</h2>");
		out.print("<h2>당신이 좋아하는 숫자는 " + number + "이군요!</h2>");
		out.print("<a href='" + request.getHeader("referer")+"'>입력화면으로 가기</a>");
	//  out.print("<a href='#' onclick='history.back();'>입력화면으로 가기</a>");
		
		out.println("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 재정의 
		// 요청받을 때 한글데이터 인코딩 방식 utf-8로 할게요~~
		request.setCharacterEncoding("utf-8");
		// 전달받은 값을 doGet에 전달한다.
		doGet(request, response);
	}

}
