package com.study.util;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseValidFormDto;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 통합게시판 유틸
 */
public interface BoardUtil {

    /**
     * 업데이트에 사용되는 게시글 DTO를 생성합니다.
     * @param form 수정 폼
     * @return 업데이트에 사용되는 DTO
     */
    static BoardDto createUpdateBoard(BoardForm form) {
        BoardDto updateBoard = createCommonBoard(form);
        updateBoard.setBoardId(form.getBoardId());

        return updateBoard;
    }

    /**
     * 등록에 사용되는 게시글 DTO를 생성합니다.
     * @param form 등록 폼
     * @return 등록에 사용되는 DTO
     */
    static BoardDto createRegisterBoard(BoardForm form) {
        BoardDto registerBoard = createCommonBoard(form);

        registerBoard.setBoardType(form.getBoardType());
        registerBoard.setUserId(form.getUserId());
        registerBoard.setUserName(form.getUserName());

        return registerBoard;
    }

    /**
     * 등록/수정에 쓰이는 공통 게시글 DTO를 생성합니다.
     * @param form 등록/수정 폼
     * @return 등록/수정에 공통으로 쓰이는 게시글 DTO
     */
    static BoardDto createCommonBoard(BoardForm form) {
        BoardDto commonBoard = new BoardDto();
        commonBoard.setCategoryId(form.getCategoryId());
        commonBoard.setBoardTitle(form.getBoardTitle());
        commonBoard.setBoardContent(form.getBoardContent());

        return commonBoard;
    }

    /**
     * 유효성검증에 실패한 Form데이터의 에러값을 저장해서 반환합니다.
     *
     * @param bindingResult 유효성검증객체
     * @return ResponseValidFormDto
     */
    static ResponseValidFormDto createValidFormFailResponse(BindingResult bindingResult) {
        ResponseValidFormDto response = new ResponseValidFormDto(ResponseApiStatus.FAIL);
        response.setErrorMessage("잘못된 데이터입니다.");

        // 에러필드이름과 에러메시지를 응답값에 담습니다.
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            response.getErrorFields().put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }
}
