package com.study.repository;

import com.study.dto.CategoryDto;
import com.study.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 'category' 테이블 리포지토리 입니다.
 */
@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final CategoryMapper categoryMapper;

    public List<CategoryDto> selectByParentId(Long parentId) {
        return categoryMapper.selectByParentId(parentId);
    };
}
