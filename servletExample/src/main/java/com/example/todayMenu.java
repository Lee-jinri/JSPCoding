package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class todayMenu
 */
@WebServlet("/todayMenu")
public class todayMenu extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html><head><mata charset='UTF-8' />");
		out.println("<title>SELECT & POST</title></head>");
		out.println("<body>");
		out.println("<div style='text-align:center'>���� ������ <strong>");
		
		// ���� �׸� 2�� �̻��� ���� getParameterValues
		String values[] = request.getParameterValues("lunch");
		for(int i=0; i < values.length; i++) {
			out.print(values[i]);
			if(i<values.length-1) out.print(", ");
		}
		// Arrays.toString(values);
		out.println("</strong>�̳� �Ծ�߰ڴ�.</div>");
		out.println("</body></html>");
		out.close();
		
	}

}
