package com.study.repository;

import com.study.dto.UserDto;
import com.study.enums.Auth;
import com.study.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 'user' 테이블 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    public void insert(UserDto user) {
        userMapper.insert(user);
    }

    /**
     * 사용자를 조회합니다.
     *
     * @param userId 사용자 아이디
     * @return UserDto 사용자정보 or null
     */
    public UserDto selectById(String userId) {
        return userMapper.selectUserById(userId);
    }
}
