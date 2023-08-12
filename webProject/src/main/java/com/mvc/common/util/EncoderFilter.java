package com.mvc.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncoderFilter implements Filter { // 사용자 정의 필터는 반드시 Filter 인터페이스 구현
	
	private String encoding;
	
	// web.xml에 초기파라미터 값으로 encoding 방식 설정할 거임.
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		
		// 한글 인코딩 설정 작업, 초기 파라미터 값으로 가져온 인코딩 방식으로 설정
		request.setCharacterEncoding(encoding);
		
		// ↑ 요청 필터 기능
		chain.doFilter(request, response); // 다음 필터로 넘기는 작업 수행
		// ↓ 응답 필터 기능
	}
}
