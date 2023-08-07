package com.study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 게시글을 수정/삭제하는 사용자가 해당 게시글의 작성자가 아닌경우 발생하는 예외
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorisedToBoardException extends RuntimeException {
    public NotAuthorisedToBoardException() {
        super("해당 게시글의 작성자가 아닙니다.");
    }
}
