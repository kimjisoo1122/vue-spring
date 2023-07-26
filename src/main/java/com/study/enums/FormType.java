package com.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 폼 타입 ENUM
 */
@Getter
@RequiredArgsConstructor
public enum FormType {
    REGISTER("등록"),
    UPDATE("수정");

    private final String value;
}
