package com.forward.redirect;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FistServlet
 */
@WebServlet("/FirstRedirect")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		// 응답은 secondRedirec가 할거임
		// response.sendRedirect("/servletExample/secondRedirect?name=Younghee"); 같은 패키지에 있으면 굳이 경로 안써도 됨 
		response.sendRedirect("SecondRedirect?name=Younghee"); // 전달해줄 데이터의 이름은 name, 전달해줄 값은 Younghee 
	}*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		// 바인딩 메서드 : 데이터를 각 객체에 바인딩한다. 데이터는 무조건 오브젝트로 담김
		request.setAttribute("address", "서울시 영등포구 의사당대로 1");
		
		// 속성을 담은 후 request를 전달해줘야해서 Redirect를 사용할 수 없음. dispatcher사용해야함.
		
		// response.sendRedirect("SecondRedirect");  -> Redirect 사용 불가능
		RequestDispatcher dispatch = request.getRequestDispatcher("SecondRedirect");
		dispatch.forward(request, response); // SecondDispatcher에 request값과 response값을 전달
		
	}


}
