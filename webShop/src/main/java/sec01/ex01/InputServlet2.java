package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input2")
public class InputServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init �޼��� ȣ��");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("destroy �޼��� ȣ��");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// Enumeration Ÿ������ �޾ƿ���
		// name�� �𸦶� ����ϴ� �޼ҵ� 
		Enumeration enu = request.getParameterNames();
		
		// enu�� ������ ���� ������ true
		while (enu.hasMoreElements()) {
			// name�� ���Ҹ� �޾ƿɴϴ�.
			String name = (String)enu.nextElement(); 
			// name�� �ϳ��� �����ؼ� �Ķ���� ���� �����ɴϴ�.
			String[] values = request.getParameterValues(name);
			for(String value : values) {
				System.out.println("name= " + name + ", value= " + value);
			}
		}
	}

}
