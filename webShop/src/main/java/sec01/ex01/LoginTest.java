package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTest
 */
@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init �޼��� ȣ��");
	}

	public void destroy() {
		System.out.println("destroy �޼��� ȣ��");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		System.out.println("���̵� : " + id);
		System.out.println("��й�ȣ : " + pw);
		
		if(id != null &&( id.length() != 0)) {
			out.print("<html><body>");
			out.print(id +"���� �α��� �ϼ̽��ϴ�.");
			out.print("</body></html>");
		} else {
			out.print("<html><body>");
			out.print("���̵� �Է��ϼ���.<br>");
			out.print("<a href='http://localhost:8080/webShop/test01/login.html'>�α��� â���� �̵� </a>");
			out.print("</body></html>");
		}
	}


}