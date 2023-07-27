package com.study.controller.advice.exception;

/**
 * 파일의 확장자가 유효하지 않는경우 발생하는 사용자 예외
 */
public class FileNotAllowedExtException extends RuntimeException{
    public FileNotAllowedExtException(String message) {
        super(message);
    }
}
