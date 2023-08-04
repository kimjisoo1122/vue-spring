package com.study.util;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;

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
     * 해당 게시글을 작성한 사용자인지 확인합니다.
     * @param userId 게시글 작성자 아이디
     * @return 해당 게시글을 작성한 사용자면 true
     */
    static boolean isRegisteredUserId(String userId) {
        if (SecurityUtil.getUserId() == null || userId == null) {
            return false;
        }
        return userId.equals(SecurityUtil.getUserId());
    }
}
