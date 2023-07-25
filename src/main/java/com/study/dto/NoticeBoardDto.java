package com.study.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 'notice_board' 테이블 DTO 입니다.
 */
@Data
public class NoticeBoardDto {
    /**
     *  'notice_board' 테이블의 기본 키
     */
    private Long noticeId;
    /**
     * 'category' 테이블을 참조하는 외래 키
     */
    private Long categoryId;
    /**
     * 'user' 테이블을 참조하는 외래 키, 여기서는 공지사항의 작성자를 의미함
     */
    private String noticeWriter;
    /**
     * 공지사항의 제목
     */
    private String noticeTitle;
    /**
     * 공지사항의 제목
     */
    private String noticeContent;
    /**
     * 공지사항의 제목
     */
    private long noticeViewCnt;
    /**
     * 공지사항의 생성일시
     */
    private LocalDateTime createDate;
    /**
     * 공지사항의 수정일시
     */
    private LocalDateTime updateDate;
}