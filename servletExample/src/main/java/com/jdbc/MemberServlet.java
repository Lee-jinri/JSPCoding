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
		out.println("<title>관리자 페이지 회원 리스트</title>");
		out.println("<link rel='stylesheet' type='text/css' href='/servletExample/css/member.css' />");
		out.println("<link rel='icon' href='data:,'>");
		out.println("<script type='text/javascript' src='/servletExample/js/jquery-1.12.4.min.js'></script>");
		
		out.println("<script type='text/javascript'>");
		out.println("$(function(){");
		out.println("	$('.memDelete').click(function(){"); // 회원 삭제를 클릭하면
		out.println("		if(confirm('회원정보를 삭제하시겠습니까?')) {"); 
		// 삭제를 확인하면 this:클릭된 본인의 부모인 tr의 속성을 data-id('xswxsw970')으로 바꿈
		out.println("			let id = $(this).parents('tr').attr('data-id');");
		// form에 있는 m_id의 값은 id로 
		out.println("			$('#m_id').val(id);");
		// post 방식으로  memberDelete에 전송
		out.println("			$('#form').attr({'method':'post','action':'/servletExample/memberDelete'});");
		out.println("			$('#form').submit();");
		out.println("		}");
		out.println("	})");
		out.println("})");
		
		
		out.println("</script>");
		out.println("</head>");
		out.println("<body><h1 class='title'>회원리스트</h1>");
		
		// servlet 실행할 수 있게 함.
		out.println("<form id='form'>");
		out.println("<input type='hidden' name='m_id' id='m_id' />"); // input type="hidden" --> post 방식
		out.println("</form>");
		
		
		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>회원 아이디</th>");
		out.println("<th>회원 이름</th>");
		out.println("<th>회원 전화번호</th>");
		out.println("<th>회원 이메일</th>");
		out.println("<th>등록일</th>");
		out.println("<th>삭제여부</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("</tbody>");
		
		if (counter > 0) { // 멤버에 레코드가 있을 때
			for ( MemberVO mvo : list ) { // int i =0; i < count; i++동안 반복 list를 반복
				
				// 값을 식별하기 위한 속성 설정 : data-명칭(내맴), 회원삭제하면 삭제할 아이디값
				// tr의 속성이 <tr data-id("M_passwd()")> 가 됨. passwd에 들어있는 값으로 id 선택자를 만드는 것
				out.println("<tr data-id="+mvo.getM_passwd()+">"); 				
				out.println("<td>"+mvo.getM_id()+"</td>");
				out.println("<td>"+mvo.getM_name()+"</td>");
				out.println("<td>"+mvo.getM_tel()+"</td>");
				out.println("<td>"+mvo.getM_email()+"</td>");
				out.println("<td>"+mvo.getReg_date()+"</td>");
				out.println("<td><a href='#' class='memDelete'>회원삭제</a></td>"); // 반복문이라서 class로 설정해야함
				out.println("</tr>");
				
			}
		} else {
			out.println("<tr>");
			out.println("<td colspan='6'>등록된 회원정보가 존재하지 않습니다.</td>");
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
