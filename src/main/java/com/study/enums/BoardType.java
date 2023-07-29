package com.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 게시글타입 ENUM
 */
@Getter
@RequiredArgsConstructor
public enum BoardType {
    NOTICE("notice", "공지사항 관리", 7),
    FREE("free", "자유 게시판 관리", 7),
    QNA("qna", "문의 게시판 관리", 10),
    GALLERY("gallery", "갤러리 게시판 관리", 7);

    private final String value;
    private final String title;
    private final int newDay;

    /**
     * @return 문의게시판 타입여부
     */
    public boolean isQnaType() {
        return this == BoardType.QNA;
    }

    /**
     * @return 공지사항 타입여부
     */
    public boolean isNoticeType() {
        return this == BoardType.NOTICE;
    }

    /**
     * @return 갤러리게시판 타입여부
     */
    public boolean isGalleryType() {
        return this == BoardType.GALLERY;
    }

    /**
     * @return 자유게시판 타입여부
     */
    public boolean isFreeType() {
        return this == BoardType.FREE;
    }

}
