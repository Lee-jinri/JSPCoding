package com.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.MemberDAO;
import com.member.MemberVO;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberList")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberDAO dao = MemberDAO.getInstance();
		ArrayList<MemberVO> list = dao.memberList();
		int counter = list.size();
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UFT-8' />");
		out.println("<title>������ ������ ȸ�� ����Ʈ</title>");
		out.println("<link rel='stylesheet' type='text/css' href='/servletExample/css/member.css' />");
		out.println("<link rel='icon' href='data:,'>");
		out.println("<script type='text/javascript' src='/servletExample/js/jquery-1.12.4.min.js'></script>");
		
		out.println("<script type='text/javascript'>");
		out.println("$(function(){");
		out.println("	$('.memDelete').click(function(){"); // ȸ�� ������ Ŭ���ϸ�
		out.println("		if(confirm('ȸ�������� �����Ͻðڽ��ϱ�?')) {"); 
		// ������ Ȯ���ϸ� this:Ŭ���� ������ �θ��� tr�� �Ӽ��� data-id('xswxsw970')���� �ٲ�
		out.println("			let id = $(this).parents('tr').attr('data-id');");
		// form�� �ִ� m_id�� ���� id�� 
		out.println("			$('#m_id').val(id);");
		// post �������  memberDelete�� ����
		out.println("			$('#form').attr({'method':'post','action':'/servletExample/memberDelete'});");
		out.println("			$('#form').submit();");
		out.println("		}");
		out.println("	})");
		out.println("})");
		
		
		out.println("</script>");
		out.println("</head>");
		out.println("<body><h1 class='title'>ȸ������Ʈ</h1>");
		
		// servlet ������ �� �ְ� ��.
		out.println("<form id='form'>");
		out.println("<input type='hidden' name='m_id' id='m_id' />"); // input type="hidden" --> post ���
		out.println("</form>");
		
		
		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>ȸ�� ���̵�</th>");
		out.println("<th>ȸ�� �̸�</th>");
		out.println("<th>ȸ�� ��ȭ��ȣ</th>");
		out.println("<th>ȸ�� �̸���</th>");
		out.println("<th>�����</th>");
		out.println("<th>��������</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("</tbody>");
		
		if (counter > 0) { // ����� ���ڵ尡 ���� ��
			for ( MemberVO mvo : list ) { // int i =0; i < count; i++���� �ݺ� list�� �ݺ�
				
				// ���� �ĺ��ϱ� ���� �Ӽ� ���� : data-��Ī(����), ȸ�������ϸ� ������ ���̵�
				// tr�� �Ӽ��� <tr data-id("M_passwd()")> �� ��. passwd�� ����ִ� ������ id �����ڸ� ����� ��
				out.println("<tr data-id="+mvo.getM_passwd()+">"); 				
				out.println("<td>"+mvo.getM_id()+"</td>");
				out.println("<td>"+mvo.getM_name()+"</td>");
				out.println("<td>"+mvo.getM_tel()+"</td>");
				out.println("<td>"+mvo.getM_email()+"</td>");
				out.println("<td>"+mvo.getReg_date()+"</td>");
				out.println("<td><a href='#' class='memDelete'>ȸ������</a></td>"); // �ݺ����̶� class�� �����ؾ���
				out.println("</tr>");
				
			}
		} else {
			out.println("<tr>");
			out.println("<td colspan='6'>��ϵ� ȸ�������� �������� �ʽ��ϴ�.</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</body></html>");
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
