package com.douzone.mysite.vo;

public class GuestbookVo {

	private Long no;
	private String name;
	private String contents;
	private String pass;
	private String regDate;

	public GuestbookVo() {
	}

	public GuestbookVo(Long no, String pass) {
		super();
		this.no = no;
		this.pass = pass;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", contents=" + contents + ", pass=" + pass + ", regDate="
				+ regDate + "]";
	}

}
