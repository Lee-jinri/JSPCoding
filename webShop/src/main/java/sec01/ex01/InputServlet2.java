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
		System.out.println("init 메서드 호출");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// Enumeration 타입으로 받아오기
		// name을 모를때 사용하는 메소드 
		Enumeration enu = request.getParameterNames();
		
		// enu에 남은게 없을 때까지 true
		while (enu.hasMoreElements()) {
			// name의 원소를 받아옵니다.
			String name = (String)enu.nextElement(); 
			// name을 하나씩 대응해서 파라미터 값을 가져옵니다.
			String[] values = request.getParameterValues(name);
			for(String value : values) {
				System.out.println("name= " + name + ", value= " + value);
			}
		}
	}

}
