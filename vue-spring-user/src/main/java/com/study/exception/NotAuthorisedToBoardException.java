package com.study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorisedToBoardException extends RuntimeException {
    public NotAuthorisedToBoardException() {
        super("해당 게시글의 작성자가 아닙니다.");
    }
}
