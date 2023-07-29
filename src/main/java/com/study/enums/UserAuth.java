package com.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 사용자 권한 ENUM
 */
@Getter
@RequiredArgsConstructor
public enum UserAuth {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}