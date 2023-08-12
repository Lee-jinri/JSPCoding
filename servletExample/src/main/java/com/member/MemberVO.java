package com.member;

import java.sql.Timestamp;

/* VO(Value Object) 클래스는 데이터를 담는 컨테이너 역할을 하는 클래스로 데이터 전달을 목적으로 만들어진 클래스이다.
 * VO 클래스는 어플리케이션 구조에서 각 단계의 입출력 정보를 전달하는 역할을 수행하며, 
 * 속성(attribute: 필드), setter(설정자)와 getter(접근자)로 구성된다. */

public class MemberVO {
	private String m_id;
	private String m_passwd;
	private String m_name;
	private String m_email;
	private String m_tel;
	private Timestamp reg_date; // Timestamp는 년월일시분초밀리초까지 가져올 때 사용할 수 있음. 
	
	public MemberVO() { }
	
	public MemberVO(String m_id, String m_passwd, String m_name, String m_email, String m_tel, Timestamp reg_date) {
		this.m_id = m_id;
		this.m_passwd = m_passwd;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_tel = m_tel;
		this.reg_date = reg_date;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_passwd() {
		return m_passwd;
	}

	public void setM_passwd(String m_passwd) {
		this.m_passwd = m_passwd;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_tel() {
		return m_tel;
	}

	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "MemberVO [m_id=" + m_id + ", m_passwd=" + m_passwd + ", m_name=" + m_name + ", m_email=" + m_email
				+ ", m_tel=" + m_tel + ", reg_date=" + reg_date + "]";
	}
	
	
}
