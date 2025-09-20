package khj.app.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//getter setter 그리고 생성자들 -> 스프링으로 불러옵니다.

public class PageInfo {
	private int pageNum; // 현재 페이지 번호
	private int listLimit;  // 페이지 당 게시물 목록 갯수
	private int listCount;  // 총 게시물 수
	private int pageTotalNum; // 페이지 당 표시할 페이지 번호 수
	
	private int maxPage; // 전체 페이지 수
	private int startPage; // 시작 페이지 번호
	private int endPage; // 끝 페이지 번호
	private int startRow; // DB 조회 시작 row
	private int rowCount; // DB 조회 끝 row

	
	public PageInfo(int listCount, int pageNum, int listLimit, int pageTotalNum) {
		
		this.listCount = listCount;
		this.pageNum = pageNum;
		this.listLimit = listLimit;
		this.pageTotalNum = (pageTotalNum > 0 ? pageTotalNum : 3);
		
		
		this.maxPage = (int)Math.ceil((double)listCount / this.listLimit);
		
		this.endPage = (int)Math.ceil((double)pageNum / pageTotalNum) * pageTotalNum;
		if (endPage > maxPage) endPage = maxPage;
		
		this.startPage = endPage - pageTotalNum + 1;
		if (startPage < 1) startPage = 1;
		
		this.startRow = (pageNum - 1) * this.listLimit;  // 오프셋??
		this.rowCount = listLimit;
	}
	

	// 제일 마지막 페이지 계산
	public void calcLastPage(int listCount, int listLimit) {
		setEndPage((int) Math.ceil((double) listCount / (double) listLimit));
		// Math.ceil 올림 메소드
	}
	
	// 시작, 끝 페이지 계산
	/*
	
	public void calcStartEndPage(int pageNum, int listLimit) {
		setEndPage(((int) Math.ceil((double)pageNum / (double)listLimit)) * pageNum);
		if (getEndPage())
	}
	*/
	
	// 시작 페이지, 끝 페이지 계산
	
	/*
	private void calcStartEndPage() {
		
		// 현재 페이지가 속한 "페이지 그룹"의 마지막 번호
		this.endPage = (int) (Math.ceil((double) pageNum / pageListLimit)) * pageListLimit;
		
		
		// 실제 maxPage보다 크면 조정
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 시작 페이지 번호
		this.startPage = endPage - pageListLimit + 1;
		if (startPage < 1) {
			startPage = 1;
		}
	}
	*/
}

