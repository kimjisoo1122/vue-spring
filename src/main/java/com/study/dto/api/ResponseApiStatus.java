package com.study.dto.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * API 응답에 사용하는 상태코드 ENUM 입니다.
 */
@Getter
@RequiredArgsConstructor
public enum ResponseApiStatus {
    SUCCESS("success"),
    FAIL("fail");

    private final String message;
}