package com.study.page;

import com.study.dto.BoardSearchCondition;
import lombok.Data;

/**
 * 게시글의 페이지처리를 담당하는 핸들러
 */
@Data
public class PageHandler {

    private int page; // 현재 페이지
    private int navSize;// 네비게이션 사이즈
    private int pageSize; // 페이지 사이즈
    private int beginPage; // 시작 페이지
    private int endPage; // 마지막 페이지
    private int maxPage; // 게시글 총 페이지
    private int totalCnt; // 게시글 총 갯수
    private boolean isPrevious; // 이전페이지 유무
    private boolean isNext; // 다음페이지 유무
    private boolean isPreviousTotal; // 통합 이전페이지 유무
    private boolean isNextTotal; // 통합 다음페이지 유무

    public PageHandler(BoardSearchCondition condition, int totalCnt) {
        this.page = condition.getPage();
        this.pageSize = condition.getLimit();
        this.totalCnt = totalCnt;
        this.navSize = 10;

        maxPage = (int) Math.ceil((double) totalCnt / pageSize);
        beginPage = (int) ((double) (page - 1) / navSize) * navSize + 1;
        endPage = Math.min((beginPage + navSize - 1), maxPage);

        isPrevious = totalCnt != 0 && page != beginPage;
        isNext = totalCnt != 0 && page != endPage;

        isPreviousTotal = beginPage > 1;
        isNextTotal = maxPage != endPage;
    }
}
