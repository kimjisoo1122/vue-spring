package com.study.service;

import com.study.enums.UserAuth;
import com.study.dto.UserDto;
import com.study.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserMapper userMapper;

    @Test
    void createUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("admin");
        userDto.setUserPw(bCryptPasswordEncoder.encode("admin"));
        userDto.setUserName("관리자");
        userDto.setUserAuth(UserAuth.ADMIN);

        userMapper.insertUser(userDto);

    }
}
