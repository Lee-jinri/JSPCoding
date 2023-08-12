package com.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextFileServlet
 */
@WebServlet("/contextFile")
public class ContextFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ������ ���� Ÿ��
		response.setContentType("text/html;charset=UTF-8");
		
		// ��� ��Ʈ��
		PrintWriter out = response.getWriter();
		
		// context �ν��Ͻ� ����
		ServletContext context = getServletContext();
		
		// �ܺο� �ִ� ���� �о��. ResourceAsStream : 1����Ʈ�� �д� �Է½�Ʈ��
		InputStream is = context.getResourceAsStream("/WEB-INF/config/init.txt");
		// InputStreamReader : �� ���ھ� �д� �Է½�Ʈ�� -> BufferedReader : �� ���ξ� �д� ���� , �� �پ� �а� ��
		BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
		
		String menu = null;
		String menu_member = null;
		String menu_order = null;
		String menu_goods = null;
		
		// �� �� �о �޴��� ����
		while ((menu = buffer.readLine()) != null){
			// ��ǥ�� �������� �� ��ū�� �и�
			StringTokenizer tokens = new StringTokenizer(menu, ",");
			menu_member = tokens.nextToken(); // Ŀ���� ������ ������ ����
			menu_order = tokens.nextToken();
			menu_goods = tokens.nextToken();
		}
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>ServletContext ����</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body>");
		
		out.print(menu_member + "<br />");
		out.print(menu_order + "<br />");
		out.print(menu_goods + "<br />");
		out.print("</body></html>");
		out.close();		
	}

}
