package com.study.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 'board' 테이블 DTO
 */
@Data
public class BoardDto {

    private Long boardId; // 게시글 번호
    private Long categoryId; // 카테고리 번호
//    private BoardType boardType; // 게시글 타입
    private String boardTitle; // 게시글 제목
    private String boardContent; // 게시글 내용
    private Integer viewCnt; // 조회수
    private boolean newStatus; // new 상태
    private LocalDateTime createDate; // 생성일시
    private LocalDateTime updateDate; // 수정일시

    /* 'category' 테이블 */
    private String categoryName; // '카테고리 이름

    /* 'user' 테이블 */
    private String userId; // 등록자 아이디
    private String userName; // 등록자 이름


    /***************특정 게시판 전용 필드**************/

    /* 자유게시판 */
    private boolean fileStatus; // 첨부파일 여부
    private int replyCnt; // 댓글 갯수

    /* 갤러리 게시판 */
    private Long fileId; // 갤러리 파일 아이디
    private String galleryImgName; // 갤러리 이미지 이름
    private String galleryThumbName; // 갤러리 썸네일 이름
    private int galleryCnt; // 갤러리 갯수
    private int galleryOrder; // 갤러리 등록순서

    /* 문의 게시판 */
    private String qnaAnswer; // 문의게시글 답변
    private boolean qnaSecret; // 문의게시글 비밀여부
    private boolean answerStatus; // 문의게시글 답변여부
}
