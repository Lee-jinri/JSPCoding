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

/* 로그인 정보를 확인할 수 있는 서블릿 */
@WebServlet("/loginInfo")
public class MemberLoginInfoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(); // 세션 객체 생성
		// 로그인 되어있는 세션을 MemberVO로 형변환해서 vo에 담음
		MemberVO vo = (MemberVO)session.getAttribute("login");
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>로그인 예제</title>");
		out.println("<link rel='icon' href='data:,'>");
		out.println("</head><body>");
		
		if(vo != null) { // vo가 null이 아니면 -> 정상 로그인 됨
			out.println("<div><table border='1'>");
			out.println("<tr><th colspan='2'>세션에 저장된 정보 얻기</th></tr>");
			out.println("<tr>");
			out.println("<td>아이디</td>");
			out.println("<td>" + vo.getM_id() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>이름</td>");
			out.println("<td>" + vo.getM_name() + "</td>");
			out.println("</tr><tr>");
			out.println("<td>이메일</td>");
			out.println("<td>"+vo.getM_email()+"</td>");
			out.println("</tr></table>");
			out.println("</div>");
			
			out.println("<div>");
			// a 태그는 무조건 get 방식
			out.println("<a href='/servletExample/loginOut'>로그아웃</a></div>");		
			
		}else { // 로그인 실패
			out.println("<div>로그인 상태가 아닙니다</br />로그인 해주세요.");
			out.println("<div><a href='/servletExample/jsp/login.html'>로그인 화면으로 이동하기</a></div>");
		}
		out.println("</body></html>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
