package com.notice.vo;

/* VO(Value Object) 클래스는 데이터를 담는 컨테이너 역할을 하는 클래스로 데이터 전달을 목적으로 만들어진 클래스이다.
 * VO 클래스는 어플리케이션 구조에서 각 단계의 입출력 정보를 전달하는 역할을 수행하며, 
 * 속성(attribute: 필드), setter(설정자)와 getter(접근자)로 구성된다. */

public class NoticeVO {
	
	private int n_num;
	private String n_writer;
	private String n_title;
	private String n_content;
	private String n_date;
	
	public NoticeVO() {	}

	public NoticeVO(int n_num, String n_writer, String n_title, String n_content, String n_date) {
		this.n_num = n_num;
		this.n_writer = n_writer;
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_date = n_date;
	}

	public int getN_num() {
		return n_num;
	}

	public void setN_num(int n_num) {
		this.n_num = n_num;
	}

	public String getN_writer() {
		return n_writer;
	}

	public void setN_writer(String n_writer) {
		this.n_writer = n_writer;
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public String getN_date() {
		return n_date;
	}

	public void setN_date(String n_date) {
		this.n_date = n_date;
	}

	@Override
	public String toString() {
		return "NoticeVO [n_num=" + n_num + ", n_writer=" + n_writer + ", n_title=" + n_title + ", n_content="
				+ n_content + ", n_date=" + n_date + "]";
	}
	
	
	
	
	

	
}
