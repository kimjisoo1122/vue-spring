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
    UPDATE("수정"),
    ANSWER("답변");

    private final String value;

    /**
     * @return 등록 폼 여부
     */
    public boolean isRegisterForm() {
        return this == FormType.REGISTER;
    }

    /**
     * @return 수정 폼 여부
     */
    public boolean isUpdateForm() {
        return this == FormType.UPDATE;
    }

    /**
     * @return 수정 폼 여부
     */
    public boolean isAnswerForm() {
        return this == FormType.ANSWER;
    }
}
