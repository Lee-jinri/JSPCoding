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
 * Servlet implementation class ChungnamInsert
 */
@WebServlet("/insert")
public class ChungnamInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ChungnamDAO dao = ChungnamDAO.getInstance();
		
		ChungnamVO vo = new ChungnamVO();
		vo.setMng_no(Integer.parseInt(request.getParameter("mng_no")));
		vo.setLocal_nm(request.getParameter("local_nm"));
		vo.setType(request.getParameter("type"));
		vo.setNm(request.getParameter("nm"));
		vo.setNm_sub(request.getParameter("nm_sub"));
		vo.setAddr(request.getParameter("addr"));
		vo.setLat(Double.parseDouble(request.getParameter("lat")));
		vo.setLng(Double.parseDouble(request.getParameter("lng")));
		vo.setDescription(request.getParameter("description"));
		vo.setList_img(request.getParameter("list_img"));
		
		int result = dao.chungnamInsert(vo);
		
		if(result == 1) {
			response.sendRedirect("/servletExample/select");
		}else {
			out.println("<!DOCTYPE html><html>");
			out.println("<head><meta charset='UTF-8' />");
			out.println("<title>Chungnam ���� ����</title>");
			out.println("<link rel='icon' href='data:,'>");
			out.println("<script type='text/javascript'>");
			out.println("	alert('������ �߻��Ͽ� ��� �۾��� �Ϸ��� �� �����ϴ�. �ٽ� �������ּ���.");
			out.println("	history.back();"); // form���� �ٽ� �ǵ��ư� 
			out.println("</script>");
			out.println("</head><body></body></html>");
		}
		
		out.close();
	}

}
