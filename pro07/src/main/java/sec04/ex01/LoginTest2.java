package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login2")
public class LoginTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		// �̺�Ʈ �ڵ鷯 ����
		LoginImpl loginUser = new LoginImpl(user_id,user_pw);
		if(session.isNew()) {
			session.setAttribute("loginUser", loginUser);
		}
		
		out.println("<html><head>");
		out.println("<script type='text/javascript'>");
		
		// �ڹ� ��ũ������ setTimeout() �Լ� ��� : 5�ʸ��� ������ ���û�Ͽ� ���� �����ڼ� ǥ��
		out.println("setTimeout('history.go(0);',5000)");
		out.println("</script></head><body>");
		// ���� ���ӵ� ���̵� ǥ���ؾ��ϱ� ������ loginUser�� user_id
		out.println("���̵�� " + loginUser.user_id +"<br>");
		out.println("�� ������ ���� "+ LoginImpl.total_user + "<br>");
		out.println("</body></html>");
		
	}

}
