package com.study.dto;

import com.study.enums.Auth;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 'user' 테이블 DTO
 */
@Data
public class UserDto {

    @NotBlank(message = "{userId.notblank}")
    @Size(min = 4, max = 11, message = "userId.size")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).*$", message = "{userId.regexp}")
    private String userId; // 사용자 아이디

    @NotBlank(message = "{userPw.notblank}")
    @Size(min = 4, max = 11, message = "{userPw.size}")
    @Pattern(regexp = "^(?!.*?(.)\\\\1{2,}).*$", message = "{userPw.regexp}")
    private String userPw; // 사용자 비밀번호 (스프링 시큐리티 암호화)

    @NotBlank(message = "{userName.notblank}")
    @Size(min = 2, max = 4, message = "{userName.size}")
    private String userName; // 사용자 이름

    private Auth userAuth; // 사용자 권한

    private LocalDateTime createDate; // 생성일시
    private LocalDateTime updateDate; // 수정일시
}
