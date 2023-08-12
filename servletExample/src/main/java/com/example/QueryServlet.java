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
		// ������
		// ������ ���� Ÿ���� text/html;charset=utf-8�Դϴ�
		response.setContentType("text/html;charset=utf-8");
		
		// ��� ��Ʈ�� : ���䰴ü�� ���ؼ� ������ �� �ִ� getWriter()�� ��½�Ʈ�� ����
		PrintWriter out = response.getWriter();
		
		// ����ڿ��� ���޹��� �� ������ ����
		String userName = request.getParameter("name");
		int number = Integer.parseInt(request.getParameter("number"));
		
		out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
		out.println("<title>GET ��İ� POST ���</title>");
		out.println("<body>");
		
		out.print("<h2>��û ��� : " + request.getMethod() + "</h2>");
		out.print("<h2>��û URI : " + request.getRequestURI() + "</h2>");
		
		out.print("<h2>����� �̸��� " + userName + "�̱���!</h2>");
		out.print("<h2>����� �����ϴ� ���ڴ� " + number + "�̱���!</h2>");
		out.print("<a href='" + request.getHeader("referer")+"'>�Է�ȭ������ ����</a>");
	//  out.print("<a href='#' onclick='history.back();'>�Է�ȭ������ ����</a>");
		
		out.println("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ������ 
		// ��û���� �� �ѱ۵����� ���ڵ� ��� utf-8�� �ҰԿ�~~
		request.setCharacterEncoding("utf-8");
		// ���޹��� ���� doGet�� �����Ѵ�.
		doGet(request, response);
	}

}
