package com.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletConfigTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��û ��ü ���ڵ� ��� , Post ����� ����� ���� �ݵ�� ��û ��ü�� ���ڵ� ����� �������־����!
		// web.xml�� charset�� �⺻ �Ķ���� ���� �����صξ���!
		String charset = getInitParameter("charset");
		request.setCharacterEncoding(charset); // request.setCharacterEncoding("UTF-8");
				
		// ���� ��ü Ÿ�԰� ���ڵ� ���
		response.setContentType("text/html;charset=UTF-8");
				
		// ��� ��ü
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
		out.println("<title>���� �ʱⰪ ����(web.xml)���� ���ڵ� ���� </title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body><h3>���� �ʱⰪ���� ���ڵ� ������ �� ��� ���</h3>");
		out.print("<h4>�̸� : " +request.getParameter("name")+"</h4>");
		out.println("</body></html>");
			
		out.close();
	}

}
