package com.study.advice;

import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.exception.BoardNotFoundException;
import com.study.exception.NotAuthorisedToBoardException;
import com.study.exception.NotAuthorisedToReplyException;
import com.study.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * API 예외를 처리하는 어드바이스
 */
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
                .status(HttpStatus.NOT_FOUND)
                .body(responseFail);
    }

    /**
     * 사용자가 존재하지 않는 경우 발생하는 예외처리
     * @param e UserNotFoundException
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto> handleUserNotFoundEx(UserNotFoundException e) {
        ResponseDto responseFail = new ResponseDto(ResponseApiStatus.FAIL);
        responseFail.setErrorMessage(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseFail);
    }


    /**
     * 해당 게시글의 작성자가 아닌 경우 발생하는 예외를 처리하는 핸들러
     * @param e NotAuthorisedToBoardException
     */
    @ExceptionHandler(NotAuthorisedToBoardException.class)
    public ResponseEntity<ResponseDto> handleNotAuthToBoardEx(NotAuthorisedToBoardException e) {
        ResponseDto responseFail = new ResponseDto(ResponseApiStatus.FAIL);
        responseFail.setErrorMessage(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(responseFail);
    }

    /**
     * 해당 댓글의 작성자가 아닌 경우 발생하는 예외를 처리하는 핸들러
     * @param e NotAuthorisedToReplyException
     */
    @ExceptionHandler(NotAuthorisedToReplyException.class)
    public ResponseEntity<ResponseDto> handleNotAuthToReplyEx(NotAuthorisedToReplyException e) {
        ResponseDto responseFail = new ResponseDto(ResponseApiStatus.FAIL);
        responseFail.setErrorMessage(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(responseFail);
    }


    /**
     * API 통신에 에러가 발생한 경우 처리한느 핸들러
     * @param e Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleEx(Exception e) {
        ResponseDto responseFail = new ResponseDto(ResponseApiStatus.FAIL);
        responseFail.setErrorMessage("API 통신에 실패하였습니다.");

        return ResponseEntity
                .badRequest()
                .body(responseFail);
    }
}
