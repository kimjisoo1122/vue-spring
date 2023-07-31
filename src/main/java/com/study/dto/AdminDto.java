package com.study.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 'admin' 테이블 DTO
 */
@Data
public class AdminDto {

    private String adminId; // 관리자 아이디
    private String adminName; // 관리자 이름
    private String adminPw; // 관리자 비밀번호
    private LocalDateTime createDate; // 생성일시
    private LocalDateTime updateDate; // 수정일시
}
