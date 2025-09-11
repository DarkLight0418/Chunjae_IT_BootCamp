package khj.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageInfo {
    private int pageNum;        // 현재 페이지 번호
    private int listLimit;      // 한 페이지에 보여줄 게시물 수
    private int listCount;      // 전체 게시물 수
    private int pageListLimit;  // 한 화면에 보여줄 페이지 번호 수 (ex. 5개, 10개)
    private int maxPage;        // 전체 페이지 수
    private int startPage;      // 시작 페이지 번호
    private int endPage;        // 끝 페이지 번호

    //  생성자 (필수값만 받아서 계산)
    public PageInfo(int pageNum, int listLimit, int listCount, int pageListLimit) {
        this.pageNum = pageNum;
        this.listLimit = listLimit;
        this.listCount = listCount;
        this.pageListLimit = pageListLimit;

        calcMaxPage();          // 전체 페이지 수 계산
        calcStartEndPage();     // 시작/끝 페이지 번호 계산
    }

    //  전체 페이지 수 계산
    private void calcMaxPage() {
        this.maxPage = (int) Math.ceil((double) listCount / listLimit);
    }

    //  시작 페이지, 끝 페이지 계산
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
}
