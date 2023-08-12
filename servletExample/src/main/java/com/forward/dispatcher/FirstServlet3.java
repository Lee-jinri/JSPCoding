package com.forward.dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet3
 */
@WebServlet("/FirstDispatcher")
public class FirstServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// despatcher는 첫번째 서블릿이 두번째 서블릿을 실행시킬 때 URL 값이 바뀌지 않음.
		// redirect, refresh, location는 두번째 서블릿을 실행할 때 두번째 서블릿으로 URL이 바뀜.
		
		response.setContentType("text/html; charset=utf-8");
		
		// request의 getRequestDispatcher 메소드 ("실행할 서블릿?전달할 값의 이름 : 값"); ↓ 이건 GET 방식
		/* RequestDispatcher dispatch = request.getRequestDispatcher("secondDispatcher?language=java");
		dispatch.forward(request, response); */
		
		// request의 속성 설정, get파라미터로 못받음 get어트리뷰트로 받아야댐
		request.setAttribute("language", "java"); // 요청 객체에 속성 설정 
		request.setAttribute("name", "이진리");
		request.setAttribute("address", "서울특별시 강서구 방화대로 23길 49-8 402호");
		RequestDispatcher dispatch = request.getRequestDispatcher("secondDispatcher");
		dispatch.forward(request, response);
		
	}

}
