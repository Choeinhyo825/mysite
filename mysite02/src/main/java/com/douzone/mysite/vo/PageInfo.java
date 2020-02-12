package com.douzone.mysite.vo;

public class PageInfo {
	private long currentPage;
	private long limit;
	private long maxPage;
	private long startPage;
	private long endpage;
	private long listCount;

	public PageInfo() {
	}

	public PageInfo(long currentPage, long limit, long maxPage, long startPage, long endpage, long listCount) {
		super();
		this.currentPage = currentPage;
		this.limit = limit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endpage = endpage;
		this.listCount = listCount;

	}

	public long getListCount() {
		return listCount;
	}

	public void setListCount(long listCount) {
		this.listCount = listCount;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public long getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(long maxPage) {
		this.maxPage = maxPage;
	}

	public long getStartPage() {
		return startPage;
	}

	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}

	public long getEndpage() {
		return endpage;
	}

	public void setEndpage(long endpage) {
		this.endpage = endpage;
	}

	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", limit=" + limit + ", maxPage=" + maxPage + ", startPage="
				+ startPage + ", endpage=" + endpage + ", listCount=" + listCount + "]";
	}

}
