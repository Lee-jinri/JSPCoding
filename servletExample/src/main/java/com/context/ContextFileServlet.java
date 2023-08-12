package com.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextFileServlet
 */
@WebServlet("/contextFile")
public class ContextFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답할 문서 타입
		response.setContentType("text/html;charset=UTF-8");
		
		// 출력 스트림
		PrintWriter out = response.getWriter();
		
		// context 인스턴스 얻음
		ServletContext context = getServletContext();
		
		// 외부에 있는 문서 읽어옴. ResourceAsStream : 1바이트씩 읽는 입력스트림
		InputStream is = context.getResourceAsStream("/WEB-INF/config/init.txt");
		// InputStreamReader : 한 문자씩 읽는 입력스트림 -> BufferedReader : 한 라인씩 읽는 버퍼 , 한 줄씩 읽게 됨
		BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
		
		String menu = null;
		String menu_member = null;
		String menu_order = null;
		String menu_goods = null;
		
		// 한 줄 읽어서 메뉴에 담음
		while ((menu = buffer.readLine()) != null){
			// 쉼표를 기준으로 한 토큰씩 분리
			StringTokenizer tokens = new StringTokenizer(menu, ",");
			menu_member = tokens.nextToken(); // 커서가 움직일 때마다 읽음
			menu_order = tokens.nextToken();
			menu_goods = tokens.nextToken();
		}
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>ServletContext 예제</title>");
		out.println("<link rel='icon' href='data:,'></head>");
		out.println("<body>");
		
		out.print(menu_member + "<br />");
		out.print(menu_order + "<br />");
		out.print(menu_goods + "<br />");
		out.print("</body></html>");
		out.close();		
	}

}
