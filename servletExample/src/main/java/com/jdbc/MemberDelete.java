package com.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.MemberDAO;

/**
 * Servlet implementation class MemberDelete
 */
@WebServlet("/memberDelete")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // ��û ��ü : ��û�� ���� ���ڵ� ���
		response.setContentType("text/html;charset=UTF-8"); // ���� ��ü : ������ ���� Ÿ��, ���ڵ� ���
		PrintWriter out = response.getWriter(); // ��� ��Ʈ�� ����
		
		MemberDAO dao = MemberDAO.getInstance(); // MemberDAO�� �ν��Ͻ��� �������� �����´�
		String m_id = request.getParameter("m_id"); // ��û���� �Ķ���� m_id�� ������ ��´�
		int result = dao.memberDelete(m_id); // ������ ���� m_id�� dao.memberDelete ���� -> ����Ǹ� result ���� 1�� ��
		
		if (result == 1) { // dao.memberDelete�� ����Ǹ� 
			response.sendRedirect("/servletExample/memberList"); // ������ memberList�� -> ���� ������
		} else { // dao.memberDelete�� ������� ������ html���� ����
 			out.println("<!DOCTYPE html><html>");
			out.println("<head><meta charset='UTF-8' />");
			out.println("<title>ȸ�� ���� ����</title>");
			out.println("<link rel='icon' href='data:,'>");
			out.println("script type='text/javascript'>");
			out.println("	alert('������ �߻��Ͽ� ���� �۾��� �Ϸ��� �� �����ϴ�. �ٽ� �������ּ���.');");
			out.println("	history.back();"); // �ǵ��ư�
			out.println("</script>");
			out.println("</head><body></body></html>");
		}
		out.close();
	}

}
