package com.notice.vo;

/* VO(Value Object) Ŭ������ �����͸� ��� �����̳� ������ �ϴ� Ŭ������ ������ ������ �������� ������� Ŭ�����̴�.
 * VO Ŭ������ ���ø����̼� �������� �� �ܰ��� ����� ������ �����ϴ� ������ �����ϸ�, 
 * �Ӽ�(attribute: �ʵ�), setter(������)�� getter(������)�� �����ȴ�. */

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
