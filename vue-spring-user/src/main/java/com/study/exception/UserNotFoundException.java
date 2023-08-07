package com.study.exception;

/**
 * 사용자를 찾을 수 없을때 발생하는 예외
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("사용자를 찾을 수 없습니다.");
    }
}
