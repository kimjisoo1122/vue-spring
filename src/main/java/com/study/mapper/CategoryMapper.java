package com.study.mapper;

import com.study.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 'category' 테이블 매퍼 입니다.
 */
@Mapper
public interface CategoryMapper {
    List<CategoryDto> selectByParentId(Long parentId);
}
