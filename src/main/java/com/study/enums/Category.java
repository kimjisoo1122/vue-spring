package com.study.enums;

import lombok.Getter;

@Getter
public enum Category {
    NOTICE(1L),
    FREE(2L),
    GALLERY(3L);

    private Long value;
    Category(Long value) {
        this.value = value;
    }
}
