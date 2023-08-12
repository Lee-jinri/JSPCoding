package com.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionTest")
public class SessionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 파라미터 값을 가져옵니다.
		String param = request.getParameter("param");
		String msg = null;
		HttpSession session = null; // 세션 객체 선언만하고 생성은 안함
		
		// 파라미터 값이 없으면 
		if(param == null) {
			out.print("값은 create, delete, add, get, remove, replace 중 하나를 선택하세요. <br />");
			out.print("요청 URL은 http://localhost:8080/servletExample/sessionTest?param=\"값\"을 명시해주세요.");
			return;
		}
		
		if (param.equals("create")) {
			session = request.getSession(); // 최초 생성!!! 세션 생성, 기존 세션 존재시 반환, 없으면 객체 생성
			// 세션이 새로 만들어졌으면
			if(session.isNew()) {
				msg = "새로운 세션 객체가 생성되었습니다.";
			} else { // 이미 생성된 세션이 있다면
				msg = "기존의 세션 객체가 리턴되었습니다.";
			}
		} else if (param.equals("delete")) {
			session = request.getSession(false); // 세션 객체가 존재하면 반환. 없으면 null
			if(session != null) { // 세션이 존재하면
				session.invalidate(); // 세션 제거, 로그아웃 할 때 사용하는 메서드
				msg = "세션 객체 삭제 완료";
			} else {
				msg = "삭제할 세션 존재하지 않음";
			}
		// 세션에 속성 추가
		} else if (param.equals("add")) {
			session = request.getSession(true);
			// 세션에 속성 추가 : 내가 필요할 때 get으로 꺼내옴
			session.setAttribute("msg", "메시지입니다.");
			msg = "세션 객체에 데이터(속성) 등록 완료";
		// 세션을 꺼내오겠다.
		} else if(param.equals("get")) {
			session = request.getSession(false);
			if(session != null) { // 세션이 존재하면
				String str = (String) session.getAttribute("msg");
				msg = str;
			} else {
				msg = "데이터(속성)을 추출할 세션 객체 존재하지 않음";
			}
		// add 뒤에 실행, 속성 삭제
		} else if(param.equals("remove")) {
			session = request.getSession(false);
			if(session != null) {
				session.removeAttribute("msg");
				msg = "세션 객체의 데이터(속성) 삭제 완료";
			} else {
				msg = "데이터(속성)을 삭제할 세션 객체가 존재하지 않음";
			}
		// 대체하고 싶은 속성!
		} else if(param.equals("replace")) {
			session = request.getSession();
			session.setAttribute("msg", "새로운 메시지입니다.");
			msg = "세션 객체에 새로운 데이터(속성) 등록 완료";
		}
		
		out.println("<DOCTYPE html><html>");
		out.println("<head><meta charset='UTF-8' />");
		out.println("<link rel='shortcut icon' href='/servletExample/image/icon.png' />");
		out.println("<link rel='apple-touch-icon' href='/servletExample/image/icon.png' />");
		out.println("<title>세션 객체</title></head><body>");
		
		out.print("처리 결과 : " + msg);
		out.println("</body></html>");
		out.close();
	}

}
