package com.javabean;

public class ScoreBean {
	private String name; // 이름
	private int point;   // 성적
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	// 성적과 학점을 계산하는 메소드 직접 구현
	public String getGrade() {
		String grade = "";
		if (point >= 90 && point <= 100)
			grade = "A";
		else if (point >= 80 && point < 90)
			grade = "B";
		else if (point >= 70 && point < 80)
			grade = "C";
		else if (point >= 60 && point < 70)
			grade = "D";
		else if (point >= 0 && point < 60) 
			grade = "F";
		else 
			grade = "잘못된 점수 입니다.";
		return grade;
	}
}
