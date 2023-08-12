package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GugudanServlet
 */
@WebServlet("/gugudanServlet")
public class GugudanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트에게 요청받아 올 문서 타입
		request.setCharacterEncoding("UTF-8");
		// 응답할 문서 타입
		response.setContentType("text/html; charset=UTF-8");
		
		int dan = Integer.parseInt(request.getParameter("dan"));
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8 /><title>간단한 연산 결과</title>");
		out.println("<link rel='icon' href='data:,'>");
		
		out.println("<style type='text/css'>");
		out.println("table{ border-collapse: collapse; border-width:1px; width:500px; text-align:center }");
		out.println("tr{ background-color:#FFFF66; text-align:center}");
		out.println("td{ width:250px; border:1px solid #000;");
		out.println("</style>");
		
		out.println("</head><body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td colspan='2'>" + dan + " 단 출력 </td>");
		out.println("</tr>");
		
		for (int i = 1; i < 10; i++) {
			if (i % 2 == 0) {
				out.print("<tr style='background-color:#ACFA58'>");
			}else {
				out.print("<tr style='background-color:#81BEF7'> ");
			}
			out.print("<td>");
			out.print(dan + " x " + i);
			out.print("</td>");
			out.print("<td>");
			out.print(i * dan);
			out.print("</td>");
			out.print("</tr>");
		}
		
		out.print("</tabel>");
		out.print("</body></html>");
		out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
