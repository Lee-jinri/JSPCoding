package com.mvc.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 어노테이션은 web.xml에
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HandlerMapping handlerMapping;  // 파일명을 받아옴
	private ViewResolver viewResolver;      // 확장자를 받아옴
	
	public void init() throws ServletException {
		// 인스턴스 생성
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		
		// viewResolver의 공통경로와 확장자 지정
		viewResolver.setPrefix("/WEB-INF");
		viewResolver.setSuffix(".jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/* 1. 클라이언트의 요청 path 정보를 추출한다.
		 * 
		 * 요청 URL에서 http://localhost:8080/webProject/board/getBoardList.do
		 * URI는 /webProject/board/getBoardList.do를 얻는다.
		 * 
		 * String path = uri.substring(request.getContextPath().length());
		 * URI에서 /webProject를 찾아 나머지 /board/getBoardList.do를 얻는다. */
		
		// uri 값을 얻는다. /board/getBoardList.do
		String path = request.getRequestURI();
		
		// 2. HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
		Controller ctrl = handlerMapping.getController(path);
		// ctrl = new GetBoardListController()의 주솟값 반환
		
		// 3. 검색한 Controller를 실행한다.
		String viewName = ctrl.execute(request, response); // GetBoardListController 클래스의 execute() 메서드 호출
		
		// 4. ViewResolver를 통해 viewName에 해당하는 화면을 검색한다.
		String view = null;
		
		if (!viewName.contains(".do")) { // 뷰네임에 .do가 포함되어있지 않을 때, do라고 요청해야 리스트를 보여줄 수 있음.
			view = viewResolver.getView(viewName); // viewResolver에서 온전한 형태로 만듦
			
			// viewResolver.getView("/board/getBoardList") -> /WEB-INF/board/getBoardList.jsp
			
			// 5. 검색된 화면으로 이동한다. (포워드, 확장자 없이 파일명만 받아옴)
			try {
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			} catch (Exception ex) {
				System.out.println("forward 오류 : " + ex);
			}
		}else {
			// 5. 검색된 화면으로 이동한다. (웹브라우저에 재요청)
			view = viewName;
			response.sendRedirect(view);
		}
	}

}
