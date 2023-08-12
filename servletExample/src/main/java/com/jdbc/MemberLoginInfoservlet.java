package com.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberVO;

/* �α��� ������ Ȯ���� �� �ִ� ���� */
@WebServlet("/loginInfo")
public class MemberLoginInfoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(); // ���� ��ü ����
		// �α��� �Ǿ��ִ� ������ MemberVO�� ����ȯ�ؼ� vo�� ����
		MemberVO vo = (MemberVO)session.getAttribute("login");
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>�α��� ����</title>");
		out.println("<link rel='icon' href='data:,'>");
		out.println("</head><body>");
		
		if(vo != null) { // vo�� null�� �ƴϸ� -> ���� �α��� ��
			out.println("<div><table border='1'>");
			out.println("<tr><th colspan='2'>���ǿ� ����� ���� ���</th></tr>");
			out.println("<tr>");
			out.println("<td>���̵�</td>");
			out.println("<td>" + vo.getM_id() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>�̸�</td>");
			out.println("<td>" + vo.getM_name() + "</td>");
			out.println("</tr><tr>");
			out.println("<td>�̸���</td>");
			out.println("<td>"+vo.getM_email()+"</td>");
			out.println("</tr></table>");
			out.println("</div>");
			
			out.println("<div>");
			// a �±״� ������ get ���
			out.println("<a href='/servletExample/loginOut'>�α׾ƿ�</a></div>");		
			
		}else { // �α��� ����
			out.println("<div>�α��� ���°� �ƴմϴ�</br />�α��� ���ּ���.");
			out.println("<div><a href='/servletExample/jsp/login.html'>�α��� ȭ������ �̵��ϱ�</a></div>");
		}
		out.println("</body></html>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
