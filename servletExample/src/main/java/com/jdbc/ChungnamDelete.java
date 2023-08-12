package com.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chungnam.ChungnamDAO;
import com.chungnam.ChungnamVO;

/**
 * Servlet implementation class ChungnamDelete
 */
@WebServlet("/ChungnamDelete")
public class ChungnamDelete extends HttpServlet {
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
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ChungnamDAO dao = ChungnamDAO.getInstance();
		
		ChungnamVO vo = new ChungnamVO();
		vo.setMng_no(Integer.parseInt(request.getParameter("mng_no")));
		
		int result = dao.chungnamDelete(vo);
		
		if(result == 1) {
			response.sendRedirect("/servletExample/select");
		} else {
			out.println("<!DOCTYPE html><html>");
			out.println("<head><meta charset='UTF-8' />");
			out.println("<title>Chungnam ���� ����</title>");
			out.println("<link rel='icon' href='data:,'>");
			out.println("script type='text/javascript'>");
			out.println("	alert('������ �߻��Ͽ� ���� �۾��� �Ϸ��� �� �����ϴ�. �ٽ� ������ �ּ���.");
			out.println("	history.back();");
			out.println("</script>");
			out.println("</head><body></body></html>");
		}
		
		out.close();
	}

}
