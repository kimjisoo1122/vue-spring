package com.study.dto;

import com.study.enums.BoardType;
import lombok.Data;

/**
 * 게시글 검색 조건
 */
@Data
public class BoardSearchCondition {

    // 게시글 검색조건
    private BoardType boardType; // 게시글 타입
    private String fromDate = ""; // 작성일 이후
    private String toDate = ""; // 작성일 이전
    private String search = ""; // 검색어
    private String searchCategory = ""; // 검색 카테고리
    private int page = 1; // 현재 페이지
    private int newDay; // 최신글 new 일자 조건 (기본 : 7)

    /* 문의게시판 나의 문의 내역 */
    private String userId; // 작성한 아이디

    // 조회 SQL에 사용합니다.
    private int offset; // SQL OFFSET
    private int limit = 10; // SQL LIMIT
    private String orderCondition = ""; // SQL ORDER 조건
    private String order = "DESC"; // SQL ORDER 방향

    /**
     * 검색조건을 설정합니다.
     * 게시글 타입, new 조건, 페이징 설정을 합니다.
     *
     * @param boardType 게시글타입
     */
    public void setSearchParams(BoardType boardType) {
        this.boardType = boardType;
        this.newDay = boardType.getNewDay();
        this.offset = (page - 1) * limit;
    }
}