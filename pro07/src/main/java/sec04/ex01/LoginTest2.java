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
		
		// 이벤트 핸들러 생성
		LoginImpl loginUser = new LoginImpl(user_id,user_pw);
		if(session.isNew()) {
			session.setAttribute("loginUser", loginUser);
		}
		
		out.println("<html><head>");
		out.println("<script type='text/javascript'>");
		
		// 자바 스크리브의 setTimeout() 함수 사용 : 5초마다 서블릿에 재요청하여 현재 접속자수 표시
		out.println("setTimeout('history.go(0);',5000)");
		out.println("</script></head><body>");
		// 현재 접속된 아이디를 표시해야하기 때문에 loginUser의 user_id
		out.println("아이디는 " + loginUser.user_id +"<br>");
		out.println("총 접속자 수는 "+ LoginImpl.total_user + "<br>");
		out.println("</body></html>");
		
	}

}
