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
    private BoardType boardType; // 게시글 타입
    private String boardTitle; // 게시글 제목
    private String boardContent; // 게시글 내용
    private Integer viewCnt; // 조회수
    private LocalDateTime createDate; // 생성일시
    private LocalDateTime updateDate; // 수정일시

    private String categoryName; // 'category' 테이블 카테고리 이름

    /* 'user' 테이블 */
    private String userId; // 등록자 아이디
    private String userName; // 등록자 이름


    /***************특정 게시판 전용 필드**************/

    /* 자유게시판 */
    private boolean hasFile; // 첨부파일 여부
    private int replyCnt; // 댓글 갯수

    /* 문의 게시판 */
    private String boardSecret; // 비밀글 여부
}
