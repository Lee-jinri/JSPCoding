package com.example;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstSuvlet
 */
// @WebServlet("/FirstSuvlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// �����ڰ� �޼��常 ȣ�����ָ� ���� �����̳ʰ� �ν��Ͻ� ������ ��ü ������
	public FirstServlet() {
		System.out.println("FirstServlet ��ü ����");
	}
	
	/* init() �޼���� ������ ���ʷ� ȣ��Ǿ��� �� ���� �����̳ʰ� �ڵ�����.
	 * ���� ��ü ���� �� �� �ѹ��� ���� */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() �޼��� ȣ��");
	}

	/* service() �޼���� Ŭ���̾�Ʈ�� ��û�� ���� ������ �Ź� ���� �����̳ʰ� �ڵ� ���� */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service(ServletRequest, ServletResponse) �޼��� ȣ��");
	}

}
