package com.study.mapper;

import com.study.dto.UserDto;
import com.study.enums.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 'user' 테이블 매퍼
 */
@Mapper
public interface UserMapper {

    void insert(UserDto userDto);
    UserDto selectUserById(String userId);
}
