package com.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getCookie")
public class GetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;chatset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>Cookie 예제</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body>");
		
		// 쿠키 배열로 반환
		Cookie[] allValues = request.getCookies();
		for(int i=0; i<allValues.length; i++) {
			out.println("<h5>Cookie 값 가져오기 : ");
			if(!allValues[i].getName().equals("cookieName")) { // 이름이 쿠키네임(영어값)이 아니면
				out.print(URLDecoder.decode(allValues[i].getValue(),"utf-8")); // 인코딩 된 값을 한글로 다시 표현
			} else {
				out.print(allValues[i].getValue()); // 쿠키 네임인 것은 그냥 출력
			}
			out.println("</h5>");
		}
		out.println("</body></html>");
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
