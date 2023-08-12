package com.context;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetServletContext
 */
@WebServlet("/setContext")
public class SetServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// ServletContextÀÇ ÀÎ½ºÅÏ½º¸¦ ¾ò´Â´Ù.
		ServletContext context = getServletContext();
		
		List<String> member = new ArrayList<String>();
		member.add("È«±æµ¿");
		member.add("ÇÑ´Ãº½");
		member.add("±è¿µÈñ");
		context.setAttribute("member", member);
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>ServletContext ¿¹Á¦</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.print("<body><div>ServletContext ¼Ó¼º ¼³Á¤</div>");
		out.print("</body></html>");
		
	}


}
