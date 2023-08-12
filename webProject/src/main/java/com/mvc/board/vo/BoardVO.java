package com.mvc.board.vo;

public class BoardVO {
	private int num;			// �� ��ȣ
	private String author;		// �� �ۼ���
	private String title;		// �� ����
	private String content;		// �� ����
	private int readcnt;		// �� ��ȸ��
	private String writeday;	// �� �ۼ���
	private int repRoot;		// �亯�� �ۼ� �� ��� (���� ���� ��ȣ ����)
	private int repStep;		// �亯�� �ۼ� �� ��� (�亯 ���� �鿩���� ����)
	private int repIndent;		// �亯�� �ۼ� �� ��� (�亯 ���� ���� ����)
	private String passwd;		// ��й�ȣ
	private int rCount;			// �亯�� ����
	
	// ���� �˻��� ����� �Ӽ�
	private String search = "";  // �⺻���� null�� �ƴ϶� ""(����)�� ����, �˻� ���
	private String keyword = ""; // �˻���
	
	public BoardVO() {}

	public BoardVO(int num, String author, String title, String content, int readcnt, String writeday, 
			int repRoot, int repStep, int repIndent) {
		this.num = num;
		this.author = author;
		this.title = title;
		this.content = content;
		this.readcnt = readcnt;
		this.writeday = writeday;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public String getWriteday() {
		return writeday;
	}

	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}

	public int getRepRoot() {
		return repRoot;
	}

	public void setRepRoot(int repRoot) {
		this.repRoot = repRoot;
	}

	public int getRepStep() {
		return repStep;
	}

	public void setRepStep(int repStep) {
		this.repStep = repStep;
	}

	public int getRepIndent() {
		return repIndent;
	}

	public void setRepIndent(int repIndent) {
		this.repIndent = repIndent;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getRCount() {
		return rCount;
	}
	
	public void setRCount(int rCount) {
		this.rCount = rCount;
	}
	

	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", author=" + author + ", title=" + title + ", content=" + content + ", readcnt="
				+ readcnt + ", writeday=" + writeday + ", repRoot=" + repRoot + ", repStep=" + repStep + ", repIndent="
				+ repIndent + ", passwd=" + passwd + "]";
	}
	
	
	
	
	
}
