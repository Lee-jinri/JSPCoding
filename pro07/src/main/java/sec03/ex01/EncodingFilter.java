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

// Filter, /* : ��� ��û�� ���͸� ��ģ��
@WebFilter("/*")

// ����� ���� ���ʹ� �ݵ�� Filter �������̽� �����ؾ���
public class EncodingFilter extends HttpFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("utf-8 ���ڵ�.........");
	}
	
	public void destroy() {
		System.out.println("destroy ȣ��");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter ȣ��");
		request.setCharacterEncoding("utf-8");
		
		// �� ���ø����̼��� context �̸��� ������
		String context = ((HttpServletRequest)request).getContextPath();
		
		// �� �������� ��û�� ��û URI ������
		String pathinfo = ((HttpServletRequest)request).getRequestURI();
		
		// ��û URI�� ���� ���
		String realPath = request.getRealPath(pathinfo);
		
		String mesg = " Context ���� : " + context + "\n URI ���� : " + pathinfo + "\n ������ ��� : " + realPath;
		System.out.println(mesg);
		
		long begin = System.currentTimeMillis(); // ��û ���Ϳ��� ��û ó�� ���� �ð��� ����
		
		// ���� ���ͷ� �ѱ�� �۾�
		chain.doFilter(request, response);
		// �� �ڵ� �� ���� ��û ���� ���, �Ʒ����� ���� ���� ���
		
		// ���� ���� ���
		long end = System.currentTimeMillis(); // ���� ���Ϳ��� ��û ó�� ���� �ð��� ����
		System.out.println("�۾� �ð� : " + (end+begin)+"ms");
		
	}

	

}
