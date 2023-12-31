package com.study.repository;

import com.study.dto.CategoryDto;
import com.study.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 'category' 테이블 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final CategoryMapper categoryMapper;

    /**
     * 상위카테고리로 카테고리를 조회합니다.
     *
     * @param parentId 상위카테고리
     * @return 해당 상위카테고리의 하위카테고리 목록
     */
    public List<CategoryDto> selectByParentId(Long parentId) {
        return categoryMapper.selectByParentId(parentId);
    };
}
