package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login4")
public class LoginServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init �޼ҵ�");
	}

	public void destroy() {
		System.out.println("destroy �޼ҵ�");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet �޼��� ȣ��");
		doHandle(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost �޼��� ȣ��");
		doHandle(request,response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("doHandle �޼��� ȣ��");
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		PrintWriter out = response.getWriter();
		
		out.println("���̵� : " + user_id);
		out.println("��й�ȣ : " + user_pw);
	}

}
