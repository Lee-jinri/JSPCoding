package com.mvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.common.controller.Controller;

public class InsertFormController implements Controller {

	@Override // Controller의 추상메소드 오버라이딩
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "/board/insertForm"; 
	}

}
