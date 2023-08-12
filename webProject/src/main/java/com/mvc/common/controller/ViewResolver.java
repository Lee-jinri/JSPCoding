package com.mvc.common.controller;

public class ViewResolver {
	public String prefix; // ���� ���(����)
	public String suffix; // Ȯ����
	
	// DispatcherServlet���� setPrefix, setSuffix ������
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	// viewName = /board/getBoardList 
	// prefix = /WEB-INF 
	// suffix = .jsp
	// view�� ��ȯ�ϴ� �޼ҵ� ������ + ���̸� + Ȯ���ڷ� ��ȯ
	public String getView(String viewName) { 
		return prefix + viewName + suffix; // /WEB-INF/board/getBoardList.jsp�� ��ȯ
	}
}
