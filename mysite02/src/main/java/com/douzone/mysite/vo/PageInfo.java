package com.douzone.mysite.vo;

public class PageInfo {

	private Long currentPage;
	private Long limit;
	private Long maxPage;
	private Long startPage;
	private Long endpage;
	private Long listCount;
	private Long blockStartNum;
	private Long blockLastNum;

	public PageInfo() {
	}

	public PageInfo(Long currentPage, Long limit, Long maxPage, Long startPage, Long endpage, Long listCount,
			Long blockStartNum, Long blockLastNum) {
		super();
		this.currentPage = currentPage;
		this.limit = limit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endpage = endpage;
		this.listCount = listCount;
		this.blockStartNum = blockStartNum;
		this.blockLastNum = blockLastNum;

	}

	public Long getBlockStartNum() {
		return blockStartNum;
	}

	public void setBlockStartNum(Long blockStartNum) {
		this.blockStartNum = blockStartNum;
	}

	public Long getBlockLastNum() {
		return blockLastNum;
	}

	public void setBlockLastNum(Long blockLastNum) {
		this.blockLastNum = blockLastNum;
	}

	public Long getListCount() {
		return listCount;
	}

	public void setListCount(Long listCount) {
		this.listCount = listCount;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Long maxPage) {
		this.maxPage = maxPage;
	}

	public Long getStartPage() {
		return startPage;
	}

	public void setStartPage(Long startPage) {
		this.startPage = startPage;
	}

	public Long getEndpage() {
		return endpage;
	}

	public void setEndpage(Long endpage) {
		this.endpage = endpage;
	}

	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", limit=" + limit + ", maxPage=" + maxPage + ", startPage="
				+ startPage + ", endpage=" + endpage + ", listCount=" + listCount + ", blockStartNum=" + blockStartNum
				+ ", blockLastNum=" + blockLastNum + "]";
	}

	
	
	

}
