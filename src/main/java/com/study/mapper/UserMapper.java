package com.study.mapper;

import com.study.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * 'user' 테이블 매퍼
 */
@Mapper
public interface UserMapper {

    UserDto selectUserById(String userId);
    void insert(UserDto userDto);
}
