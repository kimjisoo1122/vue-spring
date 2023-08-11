package com.study.controller;

import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 카테고리 API 컨트롤러
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리를 조회합니다.
     * @param categoryId 상위카테고리 번호
     * @return 카테고리 목록
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseDto> categories(
            @PathVariable("categoryId") Long categoryId) {

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(categoryService.findByParentId(categoryId));

        return ResponseEntity.ok(response);
    }
}