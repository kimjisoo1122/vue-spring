package com.study.controller.advice;

import com.study.controller.advice.exception.FileNotAllowedExtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    //TODO 파일업로드용량 초과하는 경우 에러처리
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView maxUploadExHandler(MaxUploadSizeExceededException e) {
        return new ModelAndView("error");
    }

    //TODO 파일확장자 에러처리
    @ExceptionHandler(FileNotAllowedExtException.class)
    public ModelAndView fileExtExHandler(FileNotAllowedExtException e) {
        return new ModelAndView("error");
    }
}
