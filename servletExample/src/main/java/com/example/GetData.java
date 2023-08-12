package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetData
 */
@WebServlet("/getData")
public class GetData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetData() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		
		// ��û���� �� �ѱ۵����� ���ڵ� ��� utf-8�� �ҰԿ�~~
		request.setCharacterEncoding("UTF-8");
		// ������ ���� Ÿ���� text/html;charset=utf-8�Դϴ�
		response.setContentType("text/html;charset=utf-8");
		
		String paramName = request.getParameter("name");
		String paramAddress =request.getParameter("address");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' /><title>POST DATA</title>");
		out.println("<script type='text/javascript'>");
		
		// �ڹ� ��ũ��Ʈ
		out.println("window.onload=function(){");
		out.println("var backPage = document.getElementByID('btn')");
		out.println("backPage.onclick=function(){");
		out.println("history.back() };");
		out.println("};");
		out.println("</script></head>");
		
		out.println("<body>");
		out.println("<h2 style='text-align:center'>POST ��� �׽�Ʈ�Դϴ�.</h2><ul>");
		if(!(paramName.equals("") && paramAddress.equals(""))) {
			out.println("<li>�Է��� �̸� : " + paramName +"</li>");
			out.println("<li>�Է��� �ּ� : " + paramAddress + "</li>");
		}
//		out.println("<a href='#' id='btn' onclick='history.back()'>���� ������</a>");
		out.println("<a href='#' id='btn'>���� ������</a>");
		out.println("</html>");
		
		out.close();
		
				
	}

}
