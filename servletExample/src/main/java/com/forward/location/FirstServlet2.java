package com.forward.location;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet2
 */
@WebServlet("/firstLocation")
public class FirstServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>location 예제</title>");
		out.println("<link rel='icon' href='data:,'>");
		
		// 자바스크립트 location을 사용, firstLocation이 실행되면 secondLocation이 사용자에게 응답
		out.print("<script type='text/javascript'>");
		out.print("location.href='secondLocation?name=LeeJinRi&age=24';"); // 전달할 값의 이름은 name, age 값은 이름과 나이~~ (get방식)
		out.print("</script></head>");
		
		out.println("<body></body></html>");
	}

}
