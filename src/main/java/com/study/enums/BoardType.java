package com.study.enums;

import lombok.Getter;

@Getter
public enum BoardType {
    NOTICE("notice", "공지사항 관리"),
    FREE("free", "문의 게시판 관리"),
    QNA("qna", "자유 게시판 관리"),
    GALLERY("gallery", "갤러리 게시판 관리");

    private String value;
    private String title;

    BoardType(String value, String title) {
        this.value = value;
        this.title = title;
    }
}
