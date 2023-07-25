package com.study.dto;

import lombok.Data;

/**
 * 'category' 테이블 DTO 입니다.
 */
@Data
public class CategoryDto {

    /**
     * 카테고리 번호
     */
    private Long categoryId;

    /**
     * 카테고리 이름
     */
    private String categoryName;

    /**
     * 상의카테고리 번호
     */
    private Long parentId;
}
