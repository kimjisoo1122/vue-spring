package com.study.dto;

import com.study.enums.BoardType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 'board' 테이블 DTO
 */
@Data
public class BoardDto {

    private Long boardId; // 게시글 번호
    private Long categoryId; // 카테고리 번호
    private String categoryName; // 카테고리 이름
    private BoardType boardType; // 게시글 타입

    private String boardTitle; // 게시글 제목
    private String boardContent; // 게시글 내용

    private Integer viewCnt; // 조회수
    private String userId; // 등록자 아이디
    private String userName; // 등록자 이름
    private LocalDateTime createDate; // 생성일시
    private LocalDateTime updateDate; // 수정일시

    /* 문의 게시판 */
    private String boardSecret; // 비밀글 여부
}
