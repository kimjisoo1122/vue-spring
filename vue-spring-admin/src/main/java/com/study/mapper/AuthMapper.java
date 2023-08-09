package com.study.mapper;

import com.study.enums.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 'auth' 테이블 매퍼
 */
@Mapper
public interface AuthMapper {

    List<Auth> selectAdminAuthList(String userId);
}
