package com.study.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 'reply' 테이블 DTO
 */
@Data
public class ReplyDto {

    private Long replyId; // 댓글번호
    private Long boardId; // 게시글번호
    private String replyContent; // 댓글내용
    private LocalDateTime createDate; // 생성일시
    private LocalDateTime updateDate; // 수정일시

    /* 'user' 테이블 */
    private String userId; // 댓글 등록자 아이디
    private String userName; // 댓글 등록자 이름
}