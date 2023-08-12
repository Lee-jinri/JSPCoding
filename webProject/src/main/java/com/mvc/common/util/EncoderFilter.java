package com.mvc.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncoderFilter implements Filter { // ����� ���� ���ʹ� �ݵ�� Filter �������̽� ����
	
	private String encoding;
	
	// web.xml�� �ʱ��Ķ���� ������ encoding ��� ������ ����.
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		
		// �ѱ� ���ڵ� ���� �۾�, �ʱ� �Ķ���� ������ ������ ���ڵ� ������� ����
		request.setCharacterEncoding(encoding);
		
		// �� ��û ���� ���
		chain.doFilter(request, response); // ���� ���ͷ� �ѱ�� �۾� ����
		// �� ���� ���� ���
	}
}
