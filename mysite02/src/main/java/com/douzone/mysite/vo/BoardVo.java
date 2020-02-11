package com.douzone.mysite.vo;

public class BoardVo {
	
	
	private long no;
	private long userNo;
	private String title;
	private String contents;
	private long hit;
	private String regDate;
	private long gno;
	private long ono;
	private long depth;
	
	private String name;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public long getHit() {
		return hit;
	}

	public void setHit(long hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public long getGno() {
		return gno;
	}

	public void setGno(long gno) {
		this.gno = gno;
	}

	public long getOno() {
		return ono;
	}

	public void setOno(long ono) {
		this.ono = ono;
	}

	public long getDepth() {
		return depth;
	}

	public void setDepth(long depth) {
		this.depth = depth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", contents=" + contents + ", hit="
				+ hit + ", regDate=" + regDate + ", gno=" + gno + ", ono=" + ono + ", depth=" + depth + ", name=" + name
				+ "]";
	}
	
	
	
}