package com.study.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 게시판 상수 클래스
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Board {
    /**
     * 게시글폼 VIEW 경로
     */
    public static final String FORM_PATH = "admin/board/boardForm";
    public static final String LIST_PATH = "admin/board/boardList";

}
