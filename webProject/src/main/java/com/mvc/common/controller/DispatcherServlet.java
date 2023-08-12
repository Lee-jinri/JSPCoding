package com.mvc.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ������̼��� web.xml��
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HandlerMapping handlerMapping;  // ���ϸ��� �޾ƿ�
	private ViewResolver viewResolver;      // Ȯ���ڸ� �޾ƿ�
	
	public void init() throws ServletException {
		// �ν��Ͻ� ����
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		
		// viewResolver�� �����ο� Ȯ���� ����
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
		/* 1. Ŭ���̾�Ʈ�� ��û path ������ �����Ѵ�.
		 * 
		 * ��û URL���� http://localhost:8080/webProject/board/getBoardList.do
		 * URI�� /webProject/board/getBoardList.do�� ��´�.
		 * 
		 * String path = uri.substring(request.getContextPath().length());
		 * URI���� /webProject�� ã�� ������ /board/getBoardList.do�� ��´�. */
		
		// uri ���� ��´�. /board/getBoardList.do
		String path = request.getRequestURI();
		
		// 2. HandlerMapping�� ���� path�� �ش��ϴ� Controller�� �˻��Ѵ�.
		Controller ctrl = handlerMapping.getController(path);
		// ctrl = new GetBoardListController()�� �ּڰ� ��ȯ
		
		// 3. �˻��� Controller�� �����Ѵ�.
		String viewName = ctrl.execute(request, response); // GetBoardListController Ŭ������ execute() �޼��� ȣ��
		
		// 4. ViewResolver�� ���� viewName�� �ش��ϴ� ȭ���� �˻��Ѵ�.
		String view = null;
		
		if (!viewName.contains(".do")) { // ����ӿ� .do�� ���ԵǾ����� ���� ��, do��� ��û�ؾ� ����Ʈ�� ������ �� ����.
			view = viewResolver.getView(viewName); // viewResolver���� ������ ���·� ����
			
			// viewResolver.getView("/board/getBoardList") -> /WEB-INF/board/getBoardList.jsp
			
			// 5. �˻��� ȭ������ �̵��Ѵ�. (������, Ȯ���� ���� ���ϸ� �޾ƿ�)
			try {
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			} catch (Exception ex) {
				System.out.println("forward ���� : " + ex);
			}
		}else {
			// 5. �˻��� ȭ������ �̵��Ѵ�. (���������� ���û)
			view = viewName;
			response.sendRedirect(view);
		}
	}

}
