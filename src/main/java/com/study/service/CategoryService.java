package com.study.service;

import com.study.dto.CategoryDto;
import com.study.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 카테고리 서비스입니다.
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Cacheable(value = "categoryCache", key = "#parentId")
    public List<CategoryDto> findByParentId(Long parentId) {
        return categoryRepository.selectByParentId(parentId);
    };
}
