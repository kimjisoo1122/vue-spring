package com.study.enums;

import lombok.Getter;

@Getter
public enum UserAuth {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    UserAuth(String value) {
        this.value = value;
    }

    private final String value;
}
