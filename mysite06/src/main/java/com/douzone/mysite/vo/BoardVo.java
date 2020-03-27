package com.douzone.mysite.vo;

public class BoardVo {

	private Long   no;
	private Long   userNo;
	private String title;
	private String contents;
	private Long   hit;
	private String regDate;
	private Long   gno;
	private Long   ono;
	private Long   depth;
	private String status;

	private Long   rnum;
	private String name;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long   getRnum() {
		return rnum;
	}

	public void setRnum(Long   rnum) {
		this.rnum = rnum;
	}

	public Long   getNo() {
		return no;
	}

	public void setNo(Long   no) {
		this.no = no;
	}

	public Long   getUserNo() {
		return userNo;
	}

	public void setUserNo(Long   userNo) {
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

	public Long   getHit() {
		return hit;
	}

	public void setHit(Long   hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Long   getGno() {
		return gno;
	}

	public void setGno(Long   gno) {
		this.gno = gno;
	}

	public Long   getOno() {
		return ono;
	}

	public void setOno(Long   ono) {
		this.ono = ono;
	}

	public Long   getDepth() {
		return depth;
	}

	public void setDepth(Long  depth) {
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
				+ hit + ", regDate=" + regDate + ", gno=" + gno + ", ono=" + ono + ", depth=" + depth + ", status="
				+ status + ", rnum=" + rnum + ", name=" + name + "]";
	}

}