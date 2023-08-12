package com.forward.redirect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecondServlet
 */
@WebServlet("/SecondRedirect")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 /**
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// ���޹��� �� name(Younghee)�� ���� name�� ����
		String name = request.getParameter("name");
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>redirect ����</title>");
		out.println("<link rel='icon' href='data:,'</head>");
		out.println("<body><div>sendRedirect�� �̿��� redirect �ǽ��Դϴ�.</div>");
		out.println("<div>�̸� : " + name + "</div>");
		out.println("</body></html>");
	}
 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String address = (String)request.getAttribute("address"); // setAttribute�� ���� �����´�. String���� ����ȯ �ʼ�
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>redirect ����</title>");
		out.println("<link rel='icon' href='data:,'</head>");
		out.println("<body><div>sendRedirect�� �̿��� ���ε� �ǽ��Դϴ�.</div>");
		out.println("<div>�ּ� : " + address + "</div>");
		out.println("</body></html>");
	}
}
