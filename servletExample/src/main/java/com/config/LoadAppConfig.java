package com.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 매핑 정보의 이름과 매핑 주소, loadOnStartup = 1 : 컨테이너 실행 시 서블릿이 로드됨. 
// @WebServlet(name="loadConfig", urlPatterns = {"/loadConfig"}, loadOnStartup=1)
public class LoadAppConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletContext context;
	
	public LoadAppConfig() {
		// loadOnStartup이 언제 실행되는지 알기위해 콘솔에 출력
		System.out.println("LoadAppConfig 인스턴스 생성");
	}
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LoadAppConfig의 init 메서드 호출");
		
		context = config.getServletContext();
		// 초기 파라미터 설정(web.xml)해두었던 것들임
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		
		context.setAttribute("menu_member", menu_member);
		context.setAttribute("menu_order", menu_order);
		context.setAttribute("menu_goods", menu_goods);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String menu_member = (String)context.getAttribute("menu_member");
		String menu_order = (String)context.getAttribute("menu_order");
		String menu_goods = (String)context.getAttribute("menu_goods");
		
		out.println("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
		out.println("<title>ServletConfig 예제</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body>");
		out.println("<table border='1'><caption>메뉴 이름</caption>");
		out.println("<tr><td>" + menu_member + "</td></tr>");
		out.println("<tr><td>" + menu_order + "</td></tr>");
		out.println("<tr><td>" + menu_goods + "</td></tr>");
		out.println("</tr></table></body></html>");
		out.close();
	}

}
