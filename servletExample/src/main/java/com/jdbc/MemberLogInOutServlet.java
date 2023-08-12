package com.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberDAO;
import com.member.MemberVO;

@WebServlet("/loginOut")
public class MemberLogInOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// �α����� doPost ���
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		// ����ڰ� �α����Ϸ��� �Է��� ���̵�� ��й�ȣ
		String m_id = request.getParameter("m_id");
		String m_passwd = request.getParameter("m_passwd");
		
		// �α����� �������� �� �α��λ��¸� ��� �����ϱ� ���� ����
		HttpSession session = request.getSession();
		
		// DAO���� login �޼��带 MemberVO�� �����߱� ������ MemberVO�� �ʿ���
		MemberVO vo = new MemberVO();
		// ����ڰ� �Է��� ���̵�� ��й�ȣ�� VO�� ����
		vo.setM_id(m_id);
		vo.setM_passwd(m_passwd);
		
		// DAO�� ����ϱ� ���� �̱��� ������ ��ü ����
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO mVO = dao.login(vo); // �α��� ȣ��, ���̵� ��й�ȣ�� ������ �ν��Ͻ� �� ����, Ʋ���� null ��ȯ
		
		// �α��� �������� ���� �������� ���� ��� ���� �ٸ��� ��Ÿ������ ������ html�� ��������
		StringBuffer message = new StringBuffer();
		
		if(mVO != null) { // �α��� ����
			session.setAttribute("login", mVO); // mVO��ü�� login�̶�� �̸����� ����, �ٸ� ���������� ����� ���� get�ϰ� login�̶�� �̸����� ���������
			// �α��� ������ Ȯ���� �� �ִ� �������� �̵�! a�±״� get���
			message.append("<div><a href='/servletExample/loginInfo'>�α��� ���� Ȯ���ϱ�</a></div>"); 
		} else { // �α��� ����
			message.append("<div>���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�</div>");
			message.append("<div><a href='#' onclick='history.back()'>���� ȭ������</a></div>");
		}
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>�α��� ����</title>");
		out.println("<link rel='icon' href='data,:'>");
		out.println("</head><body>");
		if(session.getAttribute("login") != null) { // �α����� �����ϸ�
			// ���ǿ��� login�̶�� �̸����� ��û->mVO ��ȯ, ��ȯ Ÿ���� MemberVO (���� ��ȯŸ���� Object)
			// ���� �α��εǾ� �ִ� ������� �̸��� ��û�ؾ��ϱ� ������ session ���
			out.print(((MemberVO)session.getAttribute("login")).getM_name() + "�� �ݰ����ϴ�."); 
		}
		out.print(message.toString());
		out.println("</body></html>");
		out.close();
		
	}
	
	// �α׾ƿ��� doGet ���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // false�� ���� ���� X -> ������ ������ ����ϰڴ�.
		
		// ������ null�� �ƴϰų� �α����̶�� ������ null�� �ƴϸ� -> ���� �α����̸�
		if (session != null && session.getAttribute("login") != null) {
			session.invalidate(); // ���� ��ȿȭ
			// �ٽ� �α��� �������� �ǵ��ư� 
			response.sendRedirect("/servletExample/jdbc/login.html");
		}
		
		String username = request.getParameter("username");
		String[] interest = request.getParameterValues("interest");
	}

}
