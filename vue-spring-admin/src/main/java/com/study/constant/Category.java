package com.study.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 상위카테고리 상수 클래스
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Category {

    public static final Long NOTICE = 1L;
    public static final Long FREE = 2L;
    public static final Long GALLERY = 3L;
}
