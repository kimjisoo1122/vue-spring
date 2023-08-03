package com.study.advice;

import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.exception.BoardNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    /**
     * 게시글 상세페이지가 존재하지 않는 경우 발생하는 예외처리
     * @param e BoardNotFoundException
     */
    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ResponseDto> handleBoardNotFoundEx(BoardNotFoundException e) {
        ResponseDto responseFail = new ResponseDto(ResponseApiStatus.FAIL);
        responseFail.setErrorMessage(e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(responseFail);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleEx(Exception e) {
        ResponseDto responseFail = new ResponseDto(ResponseApiStatus.FAIL);
        responseFail.setErrorMessage(e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(responseFail);
    }
}
