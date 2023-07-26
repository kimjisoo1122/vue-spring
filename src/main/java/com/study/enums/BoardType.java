package com.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 게시글타입 ENUM
 */
@Getter
@RequiredArgsConstructor
public enum BoardType {
    NOTICE("notice", "공지사항 관리"),
    FREE("free", "문의 게시판 관리"),
    QNA("qna", "자유 게시판 관리"),
    GALLERY("gallery", "갤러리 게시판 관리");

    private final String value;
    private final String title;
}
