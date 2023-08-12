package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet("/calcServlet")
public class CalcServlet extends HttpServlet {
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
		
		// ���� ����
		int num1, num2;
		int result;
		String op;
		
		// Ŭ���̾�Ʈ���� ��û�޾� �� ���� Ÿ��
		request.setCharacterEncoding("UTF-8");
		// ������ ���� Ÿ��
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		num1 = Integer.parseInt(request.getParameter("num1"));
		num2 = Integer.parseInt(request.getParameter("num2"));
		op = request.getParameter("operator");
		
		// calc() �޼��� ȣ��� ��� �޾ƿ�.
		result = calc(num1, num2, op);
		
		out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' /> <title>������ ���� ���</title>");
		out.println("<link rel='icon' href='data;,'></head>");
		out.println("<body>");
		out.println("<h2>��� ���</h2>");
		out.println("<hr style='width:28%; margin-left:0px' />");
		out.println(num1+ " " + op + " " + num2 + " = " + result);
		out.println("</body></html>");
		
		out.close();
	}
	
	// ���� ��� ����� �����ϴ� �޼���
	public int calc(int num1, int num2, String op) {
		int result = 0;
		if(op.equals("+")) {
			result = num1 + num2;
		}else if(op.equals("-")) {
			result = num1 - num2;
		}else if(op.equals("x")) {
			result = num1 * num2;
		}else if(op.equals("/")) {
			if(num2!=0) 
				result = num1 / num2;
			else
				result = 0;
		}
		return result;
	}
	

}
