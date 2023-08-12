package com.mvc.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	// 재정의 할 추상 메서드
	String execute(HttpServletRequest request, HttpServletResponse response);
	
}
