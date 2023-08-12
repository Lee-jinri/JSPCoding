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
		
		// 요청받을 때 한글데이터 인코딩 방식 utf-8로 할게요~~
		request.setCharacterEncoding("UTF-8");
		// 응답할 문서 타입은 text/html;charset=utf-8입니다
		response.setContentType("text/html;charset=utf-8");
		
		String paramName = request.getParameter("name");
		String paramAddress =request.getParameter("address");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' /><title>POST DATA</title>");
		out.println("<script type='text/javascript'>");
		
		// 자바 스크립트
		out.println("window.onload=function(){");
		out.println("var backPage = document.getElementByID('btn')");
		out.println("backPage.onclick=function(){");
		out.println("history.back() };");
		out.println("};");
		out.println("</script></head>");
		
		out.println("<body>");
		out.println("<h2 style='text-align:center'>POST 방식 테스트입니다.</h2><ul>");
		if(!(paramName.equals("") && paramAddress.equals(""))) {
			out.println("<li>입력한 이름 : " + paramName +"</li>");
			out.println("<li>입력한 주소 : " + paramAddress + "</li>");
		}
//		out.println("<a href='#' id='btn' onclick='history.back()'>이전 페이지</a>");
		out.println("<a href='#' id='btn'>이전 페이지</a>");
		out.println("</html>");
		
		out.close();
		
				
	}

}
