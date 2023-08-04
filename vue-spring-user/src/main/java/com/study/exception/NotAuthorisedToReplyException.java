package com.study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorisedToReplyException extends RuntimeException {
    public NotAuthorisedToReplyException() {
        super("해당 댓글의 작성자가 아닙니다.");
    }
}
