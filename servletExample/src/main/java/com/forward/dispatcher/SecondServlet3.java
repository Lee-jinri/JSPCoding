package com.forward.dispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecondServlet3
 */
@WebServlet("/secondDispatcher")
public class SecondServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// dispatch�� Ŭ���̾�Ʈ�� �������� ��ġ�� �ʰ� �������� ������ ����, 
		// Ŭ���̾�Ʈ ������������ ù ��° ������ ��û, ù ��° ������ ���� �� ��° ���� ������
		// ȭ�鿡 �� ��° ������ �������� �ּҰ��� ù ��° ������ �ּҰ��� ����
		// �ٸ� ������� Ŭ���̾�Ʈ ������������ ù ��° ������ ��û, ù ��° �������� ���������� �� ��° ���� ��û,
		// Ŭ���̾�Ʈ ������������ �� ��° ����������
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// ���޹��� ���� ������ ����, �Ķ���ͷ� �ޱ�
		/* String language = request.getParameter("language"); */
		
		// request�� �Ӽ� ������ �� �ޱ�, getAttribute : ObjectŸ������ ���޵�, String���� ���� ����ȯ~~
		String language = (String)request.getAttribute("language");
		String name = (String)request.getAttribute("name");
		String address = (String)request.getAttribute("address");
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>Despatcher ����</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body><div>Despatcher�� �̿��� redirect �ǽ��Դϴ�.</div>");
		out.println("<div>��� : " + language + "</div>");
		out.println("<div>�̸� : " + name + "</div>");
		out.println("<div>�ּ� : " + address + "</div>");
		out.println("</body></html>");
	}

}
