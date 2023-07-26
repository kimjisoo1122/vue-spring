package com.study.dto;

import com.study.enums.UserAuth;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long userNo;
    private String userId;
    private String userPw;
    private String userName;
    private UserAuth userAuth;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
