package com.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		// urlPatterns : 서블릿의 매핑 정보, 여러개 지정 가능! 
		urlPatterns = { 
				"/init1", 
				"/init2"
		}, 
		// initParams : 초기 매개변수 추가 
		initParams = { 
				@WebInitParam(name = "name", value = "이진리"), 
				@WebInitParam(name = "email", value = "ar971004@naver.com"), 
				@WebInitParam(name = "tel", value = "010-8851-7167")
		})

public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 파라미터 값 변수에 담음
		String name = getInitParameter("name");
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>ServletConfig 예제</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<tr><th>이름 : </th><td>" + name + "</td></tr>");
		out.println("<tr><th>이메일 : </th><td>" + email + "</td></tr>");
		out.println("<tr><th>전화번호 : </th><td>" + tel + "</td></tr>");
		out.println("</table></body></html>");
		out.close();
		 
	}

}
