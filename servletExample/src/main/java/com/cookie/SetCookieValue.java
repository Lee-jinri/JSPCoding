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
	
		// 내가 원하는 포맷으로 날짜 출력될 수 있도록 dateFormat 사용
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = dateFormat.format(d);
		
		// 쿠키 값에는 공백이 포함될 수 없기 때문에 인코딩해주어야함
		// 쿠키(name, value);
		Cookie c1 = new Cookie("connectTime", URLEncoder.encode(now, "utf-8"));
		// 쿠키의 유효기간 설정: 음수(-1)면 브라우저가 종료되면 쿠키 소멸
		c1.setMaxAge(24 * 60 * 60); // 하루동안 쿠키 유효 
		// c1.setMaxAge(-1);
		response.addCookie(c1); // 쿠키 값 전달
		
		// 한글을 전달하기 위해서도 인코딩 해야함, 영문은 인코딩 안해도됨
		Cookie c2 = new Cookie("cookieString", URLEncoder.encode("JSP 프로그래밍입니다.","utf-8"));
		c2.setMaxAge(24 * 60 * 60);
		response.addCookie(c2);
		
		Cookie c3 = new Cookie("cookieName", "Youngsu" );
		c3.setMaxAge(24 * 60 * 60);
		response.addCookie(c3);
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>Cookie 예제</title>");
		out.println("<link rel='icon' href='data:'></head>");
		out.println("<body>");
		
		out.println("<div>현재 시간 : " + now + "</div>");
		out.println("<div>문자열을 Cookie에 저장합니다.</div>");
		out.println("</body></html>");
		out.close();
			
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
