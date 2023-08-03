package com.study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 게시글을 찾을 수 없는경우 발생하는 예외
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException() {
        super("게시글을 찾을 수 없습니다.");
    }

    public BoardNotFoundException(String message) {
        super(message);
    }
}

