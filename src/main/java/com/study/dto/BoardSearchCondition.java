package com.study.dto;

import com.study.enums.BoardType;
import lombok.Data;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

/**
 * 게시글 검색 조건을 설정하는 클래스입니다.
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


    // 조회 SQL에 사용합니다.
    private int offset; // SQL OFFSET
    private int limit; // SQL LIMIT
    private String orderCondition = ""; // SQL ORDER 조건
    private String order = "DESC"; // SQL ORDER 방향

    /**
     * 검색조건 페이징처리를 위한 값을 설정합니다.
     * @param page 현재 페이지
     */
    public void setLimitOffset(int page, int limit) {
        this.page = page;
        this.limit = limit;
        this.offset = (page - 1) * limit;
    }

    /**
     * 검색조건들을 쿼리스트링으로 변환합니다.
     * @return 리다이렉트에 사용하는 쿼리스트링
     */
    public String getQueryParam(int page) {
        return String.format(
                "?page=%s&fromDate=%s&toDate=%s&search=%s&searchCategory=%s&limit=%s&orderCondition=%s&order=%s",
                page, fromDate, toDate, search , searchCategory, limit, orderCondition, order);
    }

    public String getQueryParam() {
        return getQueryParam(page);
    }

    /**
     * 쿼리파라미터중 한글을 인코딩합니다.
     */
    public String getEncodedQueryParam() {
        this.search = UriUtils.encode(search, StandardCharsets.UTF_8);

        return getQueryParam(page);
    }
}