package com.kitri.miniboard.board;

public class Pagination {
	private int contentCnt;	//한 페이지 내에 보여줄 게시물 - 직접 값 대입
	private int pageCnt = 5;	//한 블럭에 보여줄 페이지 수 - 직접 값 대입
	private int totalContent;	//전체 게시물 수 - select count(*) 받아옴
	private int totalPage;		//전체 페이지
	private int curBlock;		//현재 블럭
	private int startBlock;		//시작 블럭
	private int endBlock;		//마지막 블럭
	private int curPage;		//현재 나의 페이지 - url을 통해서 받아옴
	private int startPage;		//시작 페이지
	private int endPage;		//마지막 페이지
	private boolean prevBtn;	//이전 버튼
	private boolean nextBtn;	//다음 버튼
	
	public Pagination(int curPage, int totalContent, int contentCnt) {
		this.curPage = curPage;
		this.totalContent = totalContent;
		this.contentCnt = contentCnt;
		
		totalPage = (int) Math.ceil(totalContent/(double)contentCnt);
		curBlock = (int) Math.ceil(curPage/(double)pageCnt);
		endBlock = (int) Math.ceil(totalContent/(double)(pageCnt*contentCnt));
		startPage = (curBlock*pageCnt) - (pageCnt-1);
		if(endBlock == curBlock) {
			endPage = totalPage;
		} else {
			endPage=startPage+(pageCnt-1);
		}
		
		if(totalPage>0 && totalPage<(pageCnt+1)) {
			//전체 게시물의 개수가 25개 이하일 때
			prevBtn = false;
			nextBtn = false;
		} else if(curPage > 0 && curPage<(pageCnt+1)) {
			//전체 게시물 개수가 25개 이상 일 때 현재 블럭이 1블럭일 때
			prevBtn = false;
			nextBtn = true;
		} else if(endBlock == curBlock) {
			//마지막 블럭이 현재 블럭하고 같을 때
			prevBtn = true;
			nextBtn = false;
		} else {
			//현재 블럭이 첫 블럭도 마지막 블럭도 아닌 중간 블럭일 때
			prevBtn = true;
			nextBtn = true;
		}
	}
	
	
	
	
	public int getContentCnt() {
		return contentCnt;
	}
	public void setContentCnt(int contentCnt) {
		this.contentCnt = contentCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getTotalContent() {
		return totalContent;
	}
	public void setTotalContent(int totalContent) {
		this.totalContent = totalContent;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getStartBlock() {
		return startBlock;
	}
	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}
	public int getEndBlock() {
		return endBlock;
	}
	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrevBtn() {
		return prevBtn;
	}
	public void setPrevBtn(boolean prevBtn) {
		this.prevBtn = prevBtn;
	}
	public boolean isNextBtn() {
		return nextBtn;
	}
	public void setNextBtn(boolean nextBtn) {
		this.nextBtn = nextBtn;
	}
	
	
}
