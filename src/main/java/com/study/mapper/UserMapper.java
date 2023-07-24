package com.study.mapper;

import com.study.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDto selectUserById(String userId);
    void insertUser(UserDto userDto);
}
