package com.study.repository;

import com.study.dto.UserDto;
import com.study.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 'user' 테이블 리포지토리 입니다.
 */
@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    /**
     * 사용자아이디로 사용자를 조회합니다.
     * @param userId 사용자 아이디
     * @return UserDto
     */
    public UserDto selectUserById(String userId) {
        return userMapper.selectUserById(userId);
    }
}
