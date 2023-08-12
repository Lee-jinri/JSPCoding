package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookInputServlet
 */
@WebServlet("/bookReg")
public class BookInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청받을 문서 타입
		request.setCharacterEncoding("UTF-8");
		
		// 응답할 문서 타입
		response.setContentType("text/html; charset=UTF-8");
		// 출력 스트림
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title"); // name이 title인 것의 파라미터를 받아서 title 변수에 담음
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		
		// VO 인스턴스 생성
		BookVO book = new BookVO();
		book.setTitle(title); // bookVO의 필드 값을 설정
		book.setAuthor(author);
		book.setPublisher(publisher);
		
		// bookVO의 주소값을 요청 객체의 속성으로 설정
		request.setAttribute("book", book);
		
		// BookOutput에 응답, 요청 객체를 넘겨줌
		RequestDispatcher dispatcher = request.getRequestDispatcher("bookOutput"); 
		dispatcher.forward(request, response);
		
		out.close();
	
	}
	

}
