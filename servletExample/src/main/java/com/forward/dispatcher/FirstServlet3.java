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
		// despatcher�� ù��° ������ �ι�° ������ �����ų �� URL ���� �ٲ��� ����.
		// redirect, refresh, location�� �ι�° ������ ������ �� �ι�° �������� URL�� �ٲ�.
		
		response.setContentType("text/html; charset=utf-8");
		
		// request�� getRequestDispatcher �޼ҵ� ("������ ����?������ ���� �̸� : ��"); �� �̰� GET ���
		/* RequestDispatcher dispatch = request.getRequestDispatcher("secondDispatcher?language=java");
		dispatch.forward(request, response); */
		
		// request�� �Ӽ� ����, get�Ķ���ͷ� ������ get��Ʈ����Ʈ�� �޾ƾߴ�
		request.setAttribute("language", "java"); // ��û ��ü�� �Ӽ� ���� 
		request.setAttribute("name", "������");
		request.setAttribute("address", "����Ư���� ������ ��ȭ��� 23�� 49-8 402ȣ");
		RequestDispatcher dispatch = request.getRequestDispatcher("secondDispatcher");
		dispatch.forward(request, response);
		
	}

}
