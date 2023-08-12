package com.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionTest")
public class SessionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// �Ķ���� ���� �����ɴϴ�.
		String param = request.getParameter("param");
		String msg = null;
		HttpSession session = null; // ���� ��ü �����ϰ� ������ ����
		
		// �Ķ���� ���� ������ 
		if(param == null) {
			out.print("���� create, delete, add, get, remove, replace �� �ϳ��� �����ϼ���. <br />");
			out.print("��û URL�� http://localhost:8080/servletExample/sessionTest?param=\"��\"�� ������ּ���.");
			return;
		}
		
		if (param.equals("create")) {
			session = request.getSession(); // ���� ����!!! ���� ����, ���� ���� ����� ��ȯ, ������ ��ü ����
			// ������ ���� �����������
			if(session.isNew()) {
				msg = "���ο� ���� ��ü�� �����Ǿ����ϴ�.";
			} else { // �̹� ������ ������ �ִٸ�
				msg = "������ ���� ��ü�� ���ϵǾ����ϴ�.";
			}
		} else if (param.equals("delete")) {
			session = request.getSession(false); // ���� ��ü�� �����ϸ� ��ȯ. ������ null
			if(session != null) { // ������ �����ϸ�
				session.invalidate(); // ���� ����, �α׾ƿ� �� �� ����ϴ� �޼���
				msg = "���� ��ü ���� �Ϸ�";
			} else {
				msg = "������ ���� �������� ����";
			}
		// ���ǿ� �Ӽ� �߰�
		} else if (param.equals("add")) {
			session = request.getSession(true);
			// ���ǿ� �Ӽ� �߰� : ���� �ʿ��� �� get���� ������
			session.setAttribute("msg", "�޽����Դϴ�.");
			msg = "���� ��ü�� ������(�Ӽ�) ��� �Ϸ�";
		// ������ �������ڴ�.
		} else if(param.equals("get")) {
			session = request.getSession(false);
			if(session != null) { // ������ �����ϸ�
				String str = (String) session.getAttribute("msg");
				msg = str;
			} else {
				msg = "������(�Ӽ�)�� ������ ���� ��ü �������� ����";
			}
		// add �ڿ� ����, �Ӽ� ����
		} else if(param.equals("remove")) {
			session = request.getSession(false);
			if(session != null) {
				session.removeAttribute("msg");
				msg = "���� ��ü�� ������(�Ӽ�) ���� �Ϸ�";
			} else {
				msg = "������(�Ӽ�)�� ������ ���� ��ü�� �������� ����";
			}
		// ��ü�ϰ� ���� �Ӽ�!
		} else if(param.equals("replace")) {
			session = request.getSession();
			session.setAttribute("msg", "���ο� �޽����Դϴ�.");
			msg = "���� ��ü�� ���ο� ������(�Ӽ�) ��� �Ϸ�";
		}
		
		out.println("<DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<link rel='shortcut icon' href='/servletExample/image/icon.png' />");
		out.println("<link rel='apple-touch-icon' href='/servletExample/image/icon.png' />");
		out.println("<title>���� ��ü</title></head><body>");
		
		out.print("ó�� ��� : " + msg);
		out.println("</body></html>");
		out.close();
	}

}
