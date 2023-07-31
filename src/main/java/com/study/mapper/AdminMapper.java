package com.study.mapper;

import com.study.dto.AdminDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 'admin' 테이블 매퍼
 */
@Mapper
public interface AdminMapper {

    AdminDto selectById(String adminId);
    List<String> selectAuthById(String adminId);
}
