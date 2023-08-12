package sec03.ex01;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

// Filter, /* : 모든 요청이 필터를 거친다
@WebFilter("/*")

// 사용자 정의 필터는 반드시 Filter 인터페이스 구현해야함
public class EncodingFilter extends HttpFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("utf-8 인코딩.........");
	}
	
	public void destroy() {
		System.out.println("destroy 호출");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter 호출");
		request.setCharacterEncoding("utf-8");
		
		// 웹 애플리케이션의 context 이름을 가져옴
		String context = ((HttpServletRequest)request).getContextPath();
		
		// 웹 브라우정서 요청한 요청 URI 가져옴
		String pathinfo = ((HttpServletRequest)request).getRequestURI();
		
		// 요청 URI의 실제 경로
		String realPath = request.getRealPath(pathinfo);
		
		String mesg = " Context 정보 : " + context + "\n URI 정보 : " + pathinfo + "\n 물리적 경로 : " + realPath;
		System.out.println(mesg);
		
		long begin = System.currentTimeMillis(); // 요청 필터에서 요청 처리 전의 시각을 구함
		
		// 다음 필터로 넘기는 작업
		chain.doFilter(request, response);
		// 이 코드 위 쪽은 요청 필터 기능, 아래쪽은 응답 필터 기능
		
		// 응답 필터 기능
		long end = System.currentTimeMillis(); // 응답 필터에서 요청 처리 후의 시각을 구함
		System.out.println("작업 시간 : " + (end+begin)+"ms");
		
	}

	

}
