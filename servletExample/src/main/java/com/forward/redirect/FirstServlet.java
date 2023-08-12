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
		
		// ������ secondRedirec�� �Ұ���
		// response.sendRedirect("/servletExample/secondRedirect?name=Younghee"); ���� ��Ű���� ������ ���� ��� �Ƚᵵ �� 
		response.sendRedirect("SecondRedirect?name=Younghee"); // �������� �������� �̸��� name, �������� ���� Younghee 
	}*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		// ���ε� �޼��� : �����͸� �� ��ü�� ���ε��Ѵ�. �����ʹ� ������ ������Ʈ�� ���
		request.setAttribute("address", "����� �������� �ǻ���� 1");
		
		// �Ӽ��� ���� �� request�� ����������ؼ� Redirect�� ����� �� ����. dispatcher����ؾ���.
		
		// response.sendRedirect("SecondRedirect");  -> Redirect ��� �Ұ���
		RequestDispatcher dispatch = request.getRequestDispatcher("SecondRedirect");
		dispatch.forward(request, response); // SecondDispatcher�� request���� response���� ����
		
	}


}
