package com.study.repository;

import com.study.dto.NoticeBoardDto;
import com.study.mapper.NoticeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 'notice_board'테이블 리포지토리 입니다.
 */
@Repository
@RequiredArgsConstructor
public class NoticeBoardRepository {

    private final NoticeBoardMapper noticeBoardMapper;

    // 공지사항을 등록합니다.
    public int insertNotice(NoticeBoardDto notice) {
        return noticeBoardMapper.insertNotice(notice);
    };

    // 공지사항을 조회합니다.
    public NoticeBoardDto selectNoticeById(Long noticeId) {
        return noticeBoardMapper.selectNoticeById(noticeId);
    }

    // 공지사항을 수정합니다.
    public int updateNotice(NoticeBoardDto notice) {
        return noticeBoardMapper.updateNotice(notice);
    }

    // 공지사항을 삭제합니다.
    public int deleteNotice(Long noticeId) {
        return noticeBoardMapper.deleteNotice(noticeId);
    }
}
