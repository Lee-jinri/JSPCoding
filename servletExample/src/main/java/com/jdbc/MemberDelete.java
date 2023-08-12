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
		request.setCharacterEncoding("UTF-8"); // 요청 객체 : 요청할 문서 인코딩 방식
		response.setContentType("text/html;charset=UTF-8"); // 응답 객체 : 응답할 문서 타입, 인코딩 방식
		PrintWriter out = response.getWriter(); // 출력 스트림 생성
		
		MemberDAO dao = MemberDAO.getInstance(); // MemberDAO의 인스턴스로 참조값을 가져온다
		String m_id = request.getParameter("m_id"); // 요청받은 파라미터 m_id를 변수에 담는다
		int result = dao.memberDelete(m_id); // 변수에 담은 m_id를 dao.memberDelete 실행 -> 실행되면 result 값이 1이 됨
		
		if (result == 1) { // dao.memberDelete가 실행되면 
			response.sendRedirect("/servletExample/memberList"); // 포워드 memberList로 -> 행이 삭제됨
		} else { // dao.memberDelete가 실행되지 않으면 html으로 응답
 			out.println("<!DOCTYPE html><html>");
			out.println("<head><meta charset='UTF-8' />");
			out.println("<title>회원 정보 삭제</title>");
			out.println("<link rel='icon' href='data:,'>");
			out.println("script type='text/javascript'>");
			out.println("	alert('오류가 발생하여 삭제 작업을 완료할 수 없습니다. 다시 실행해주세요.');");
			out.println("	history.back();"); // 되돌아감
			out.println("</script>");
			out.println("</head><body></body></html>");
		}
		out.close();
	}

}
