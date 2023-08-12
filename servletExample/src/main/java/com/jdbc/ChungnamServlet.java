package com.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chungnam.ChungnamDAO;
import com.chungnam.ChungnamVO;

/**
 * Servlet implementation class ChungnamServlet
 */
@WebServlet("/select")
public class ChungnamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// �̱� �� ���� : �ϳ��� �ν��Ͻ��� ���� ����, new �Ұ�~~
		ChungnamDAO dao = ChungnamDAO.getInstance();
		ArrayList<ChungnamVO> list = dao.getChungnamList();
		int counter = list.size();
		
		out.println("<DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>Chugnam ���� ����Ʈ ����</title>");
		out.println("<link rel='stylesheet' type='text/css' href='/servletExample/css/chungnam.css' /" );
		out.println("<link rel='icon' href='data:,'>");
		out.println("<script type=\"text/javascript\" src=\"../js/jquery-1.12.4.min.js\"></script>");
		
		// delete ��������
		out.println("<script type='text/javascript'>");
		out.println("$(function(){");
		out.println("	$('.delBtn').click(fucntion(){");
		out.println("		if(confirm('������ �����Ͻðڽ��ϱ�?')){");
		out.println("			let mng_no = $(this).parents('li').attr('data-no'); console.log(mng_no);");
		out.println("			location.href='/servletExample/delete?mng_no='+mng_no;"); // get ���
		
		out.println("		}");
		out.println("	})");
		out.println("</script>");
		out.println("</head>");
		out.println("<body><h1 class='title'>�泲���� - �������</h1>");
		// ���� ��� ��ũ
		out.println("<div class='btn'><a href='/servletExample/jdbc/chungnamForm.jsp'>�������</a></div>");
		
		out.println("<ul id='list'>");
		// ���ڵ尡 ������ 
		if (counter > 0) {
			for( ChungnamVO cvo : list ) { // ArrayList�� �ݺ���
				out.println("<li class='item' data-no='" +cvo.getMng_no()+"'>");
				out.println("<span class='item-thumb'><img src='"+ cvo.getList_img() +"' /> </span>");
				out.println("<span class='text'>");
				
				out.println("<span class='item-type'>" + cvo.getType() +"</span>");
				out.println("<span class='item-title'>" + cvo.getNm());
				out.println("<button type='button' class='delBtn'>����</button>");
				out.println("</span>");
				out.println("<span class='item-sub'>" + cvo.getNm_sub() + "</span>");
				out.println("<spanc class='item-content'>"+cvo.getDescription() + "</span>");
				out.println("</span>");
				out.println("</li>");
			}
		}
		out.println("</ul>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
