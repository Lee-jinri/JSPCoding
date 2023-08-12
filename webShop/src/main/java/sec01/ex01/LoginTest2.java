package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginTest2")
public class LoginTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}

	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
		
		if(id != null && id.length() != 0) {
			if(id.equals("admin")) {
				out.print("<html><body>");
				out.print("<h3>관리자로 로그인 하셨습니다</h3><br>");
				out.print("<input type='button' value='회원정보 수정하기' /> ");
				out.print("<input type='button' value='회원정보 삭제하기' /> ");
				out.print("</body></html>");
			}else {
				out.print("<html><body>");
				out.print("<h3>" + id +"님 안녕하세요.</h3>");
				out.print("</body></html>");
			}
		}else {
			out.print("<html><body>");
			out.print("<h3> 아이디를 입력하세요 </h3>");
			out.print("<br>");
			out.print("<a href='http://localhost:8080/webShop/test01/login.html'>로그인창으로 이동</a>");
			out.print("</body></html>");
		}
	}

}
