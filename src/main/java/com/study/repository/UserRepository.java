package com.study.repository;

import com.study.dto.UserDto;
import com.study.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    public Optional<UserDto> selectUserById(String userId) {
        return Optional.ofNullable(userMapper.selectUserById(userId));
    }
}
