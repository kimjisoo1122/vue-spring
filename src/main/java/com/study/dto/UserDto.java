package com.study.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 'user' 테이블 DTO
 */
@Data
public class UserDto {

    private String userId; // 사용자 아이디
    private String userPw; // 사용자 비밀번호 (스프링 시큐리티 암호화)
    private String userName; // 사용자 이름
    private LocalDateTime createDate; // 생성일시
    private LocalDateTime updateDate; // 수정일시
}
