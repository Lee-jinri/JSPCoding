package com.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/setCookie")
public class SetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
	
		// ���� ���ϴ� �������� ��¥ ��µ� �� �ֵ��� dateFormat ���
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = dateFormat.format(d);
		
		// ��Ű ������ ������ ���Ե� �� ���� ������ ���ڵ����־����
		// ��Ű(name, value);
		Cookie c1 = new Cookie("connectTime", URLEncoder.encode(now, "utf-8"));
		// ��Ű�� ��ȿ�Ⱓ ����: ����(-1)�� �������� ����Ǹ� ��Ű �Ҹ�
		c1.setMaxAge(24 * 60 * 60); // �Ϸ絿�� ��Ű ��ȿ 
		// c1.setMaxAge(-1);
		response.addCookie(c1); // ��Ű �� ����
		
		// �ѱ��� �����ϱ� ���ؼ��� ���ڵ� �ؾ���, ������ ���ڵ� ���ص���
		Cookie c2 = new Cookie("cookieString", URLEncoder.encode("JSP ���α׷����Դϴ�.","utf-8"));
		c2.setMaxAge(24 * 60 * 60);
		response.addCookie(c2);
		
		Cookie c3 = new Cookie("cookieName", "Youngsu" );
		c3.setMaxAge(24 * 60 * 60);
		response.addCookie(c3);
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>Cookie ����</title>");
		out.println("<link rel='icon' href='data:'></head>");
		out.println("<body>");
		
		out.println("<div>���� �ð� : " + now + "</div>");
		out.println("<div>���ڿ��� Cookie�� �����մϴ�.</div>");
		out.println("</body></html>");
		out.close();
			
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
