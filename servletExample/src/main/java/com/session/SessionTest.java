package com.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// ���� ����
		HttpSession session = request.getSession();
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>Session ��ü�� �޼��� ����</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body>");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		out.println("���� ���̵� : " + session.getId() + "<br />");
		// ���� ���ϴ� �������� ��¥�� ǥ����
		out.println("�ֱ� ���� ���� �ð� : " + sdf.format(new Date(session.getCreationTime())) + "<br />");
		out.println("�ֱ� ���� ���� �ð� : " + sdf.format(new Date(session.getLastAccessedTime())) + "<br/>");
		out.println("���� ��ȿ �ð� : " + session.getMaxInactiveInterval() + "<br />");
		// ���� ������ ���� ��������ٸ�
		if (session.isNew()) {
			out.println("������ ����������ϴ�.");
		}
		out.println("</body></html>");
		out.close();
		
	
	}

}
