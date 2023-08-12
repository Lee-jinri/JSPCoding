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
		// ��û���� ���� Ÿ��
		request.setCharacterEncoding("UTF-8");
		
		// ������ ���� Ÿ��
		response.setContentType("text/html; charset=UTF-8");
		// ��� ��Ʈ��
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title"); // name�� title�� ���� �Ķ���͸� �޾Ƽ� title ������ ����
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		
		// VO �ν��Ͻ� ����
		BookVO book = new BookVO();
		book.setTitle(title); // bookVO�� �ʵ� ���� ����
		book.setAuthor(author);
		book.setPublisher(publisher);
		
		// bookVO�� �ּҰ��� ��û ��ü�� �Ӽ����� ����
		request.setAttribute("book", book);
		
		// BookOutput�� ����, ��û ��ü�� �Ѱ���
		RequestDispatcher dispatcher = request.getRequestDispatcher("bookOutput"); 
		dispatcher.forward(request, response);
		
		out.close();
	
	}
	

}
