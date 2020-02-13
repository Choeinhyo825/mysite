package com.douzone.mysite.vo;

public class PageInfo {

	private long currentPage;
	private long limit;
	private long maxPage;
	private long startPage;
	private long endpage;
	private long listCount;
	private long blockStartNum;
	private long blockLastNum;

	public PageInfo() {
	}

	public PageInfo(long currentPage, long limit, long maxPage, long startPage, long endpage, long listCount,
			long blockStartNum, long blockLastNum) {
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

	public long getBlockStartNum() {
		return blockStartNum;
	}

	public void setBlockStartNum(long blockStartNum) {
		this.blockStartNum = blockStartNum;
	}

	public long getBlockLastNum() {
		return blockLastNum;
	}

	public void setBlockLastNum(long blockLastNum) {
		this.blockLastNum = blockLastNum;
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
				+ startPage + ", endpage=" + endpage + ", listCount=" + listCount + ", blockStartNum=" + blockStartNum
				+ ", blockLastNum=" + blockLastNum + "]";
	}

	
	
	

}
