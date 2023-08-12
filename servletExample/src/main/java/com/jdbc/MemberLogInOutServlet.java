package com.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberDAO;
import com.member.MemberVO;

@WebServlet("/loginOut")
public class MemberLogInOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 로그인은 doPost 사용
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		// 사용자가 로그인하려고 입력한 아이디와 비밀번호
		String m_id = request.getParameter("m_id");
		String m_passwd = request.getParameter("m_passwd");
		
		// 로그인이 성공했을 때 로그인상태를 계속 유지하기 위한 세션
		HttpSession session = request.getSession();
		
		// DAO에서 login 메서드를 MemberVO로 전달했기 때문에 MemberVO가 필요함
		MemberVO vo = new MemberVO();
		// 사용자가 입력한 아이디와 비밀번호를 VO에 설정
		vo.setM_id(m_id);
		vo.setM_passwd(m_passwd);
		
		// DAO를 사용하기 위해 싱글톤 패턴의 객체 생성
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO mVO = dao.login(vo); // 로그인 호출, 아이디 비밀번호가 맞으면 인스턴스 값 얻음, 틀리면 null 반환
		
		// 로그인 성공했을 때와 실패했을 때의 결과 값이 다르게 나타나도록 변수에 html을 담을거임
		StringBuffer message = new StringBuffer();
		
		if(mVO != null) { // 로그인 성공
			session.setAttribute("login", mVO); // mVO자체를 login이라는 이름으로 설정, 다른 페이지에서 사용할 때는 get하고 login이라는 이름으로 꺼내오면됨
			// 로그인 정보를 확인할 수 있는 서블릿으로 이동! a태그는 get방식
			message.append("<div><a href='/servletExample/loginInfo'>로그인 정보 확인하기</a></div>"); 
		} else { // 로그인 실패
			message.append("<div>아이디 또는 비밀번호가 일치하지 않습니다</div>");
			message.append("<div><a href='#' onclick='history.back()'>이전 화면으로</a></div>");
		}
		
		out.println("<!DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<title>로그인 예제</title>");
		out.println("<link rel='icon' href='data,:'>");
		out.println("</head><body>");
		if(session.getAttribute("login") != null) { // 로그인이 성공하면
			// 세션에서 login이라는 이름으로 요청->mVO 반환, 반환 타입은 MemberVO (원래 반환타입은 Object)
			// 지금 로그인되어 있는 사용자의 이름을 요청해야하기 때문에 session 사용
			out.print(((MemberVO)session.getAttribute("login")).getM_name() + "님 반갑습니다."); 
		}
		out.print(message.toString());
		out.println("</body></html>");
		out.close();
		
	}
	
	// 로그아웃은 doGet 사용
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // false면 세션 생성 X -> 기존의 세션을 사용하겠다.
		
		// 세션이 null이 아니거나 로그인이라는 세션이 null이 아니면 -> 정상 로그인이면
		if (session != null && session.getAttribute("login") != null) {
			session.invalidate(); // 세션 무효화
			// 다시 로그인 페이지로 되돌아감 
			response.sendRedirect("/servletExample/jdbc/login.html");
		}
		
		String username = request.getParameter("username");
		String[] interest = request.getParameterValues("interest");
	}

}
