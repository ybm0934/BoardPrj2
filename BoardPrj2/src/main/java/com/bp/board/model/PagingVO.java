package com.bp.board.model;

public class PagingVO {

	private int currentPage; // 현재 페이지
	private int totalRecord; // 총 레코드 수
	private int pageSize; // 페이지 당 보여질 레코드 수
	private int totalPage; // 총 페이지 수
	private int blockSize; // 블럭 당 보여질 페이지 수
	private int firstPage; // 블럭 당 시작 페이지
	private int lastPage; // 블럭 당 끝 페이지
	private int curPos; // 페이지 당 시작 인덱스
	private int num; // 반복문 브레이크용 인덱스

	public PagingVO(int currentPage, int totalRecord, int pageSize, int blockSize) {
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		this.totalPage = (int) Math.ceil((double) totalRecord / pageSize);
		this.firstPage = currentPage - ((currentPage - 1) % blockSize);
		this.lastPage = firstPage + (blockSize - 1);
		this.curPos = (currentPage - 1) * pageSize;
		this.num = totalRecord - curPos;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurPos() {
		return curPos;
	}

	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "PagingVO [currentPage=" + currentPage + ", totalRecord=" + totalRecord + ", pageSize=" + pageSize
				+ ", totalPage=" + totalPage + ", blockSize=" + blockSize + ", firstPage=" + firstPage + ", lastPage="
				+ lastPage + ", curPos=" + curPos + ", num=" + num + "]";
	}

}