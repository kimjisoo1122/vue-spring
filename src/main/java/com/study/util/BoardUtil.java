package com.study.util;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;

public interface BoardUtil {

    /**
     * 게시글폼 VIEW 경로
     */
    String FORM_PATH = "admin/board/boardForm";
    String LIST_PATH = "admin/board/boardList";

    /**
     * 업데이트에 사용되는 게시글 DTO를 생성합니다
     * @param form 업데이트 폼
     * @return BoardDto 업데이트에 사용되는 DTO
     */
    static BoardDto createUpdateBoard(BoardForm form) {
        BoardDto updateBoard = createCommonBoard(form);
        updateBoard.setBoardId(form.getBoardId());

        return updateBoard;
    }

    /**
     * 등록에 사용되는 게시글 DTO를 생성합니다
     * @param form 등록 폼
     * @return BoardDto 등록에 사용되는 DTO
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
}
