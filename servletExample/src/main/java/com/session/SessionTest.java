package com.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 세션 생성
		HttpSession session = request.getSession();
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>Session 객체의 메서드 예제</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body>");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		out.println("세션 아이디 : " + session.getId() + "<br />");
		// 내가 원하는 포맷으로 날짜를 표시함
		out.println("최근 세션 생성 시각 : " + sdf.format(new Date(session.getCreationTime())) + "<br />");
		out.println("최근 세션 접근 시각 : " + sdf.format(new Date(session.getLastAccessedTime())) + "<br/>");
		out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br />");
		// 만약 세션이 새로 만들어졌다면
		if (session.isNew()) {
			out.println("세션이 만들어졌습니다.");
		}
		out.println("</body></html>");
		out.close();
		
	
	}

}
