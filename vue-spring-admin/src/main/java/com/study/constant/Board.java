package com.study.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 게시판 상수 클래스
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Board {

    public static final String FORM_PATH = "admin/board/boardForm"; /* 게시글 등록/수정 폼 VIEW */
    public static final String LIST_PATH = "admin/board/boardList"; /* 게시글 목록 VIEW */

}
