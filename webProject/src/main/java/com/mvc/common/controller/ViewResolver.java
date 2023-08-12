package com.mvc.common.controller;

public class ViewResolver {
	public String prefix; // 공통 경로(폴더)
	public String suffix; // 확장자
	
	// DispatcherServlet에서 setPrefix, setSuffix 설정함
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	// viewName = /board/getBoardList 
	// prefix = /WEB-INF 
	// suffix = .jsp
	// view를 반환하는 메소드 공통경로 + 뷰이름 + 확장자로 반환
	public String getView(String viewName) { 
		return prefix + viewName + suffix; // /WEB-INF/board/getBoardList.jsp를 반환
	}
}
