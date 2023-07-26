package com.study.repository;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 공지사항 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class NoticeRepository {

    private final NoticeMapper noticeMapper;

    /**
     * 공지사항 알림을 등록합니다.
     * @param boardId 공지사항 번호
     */
    public void insertNoticeAlarm(Long boardId) {
        noticeMapper.insertNoticeAlarm(boardId);
    }

    /**
     * 알림으로 등록된 게시글을 limit만큼 최신순으로 조회합니다.
     * @param limit 알림글갯수
     * @return List<BoardDto>
     */
    public List<BoardDto> selectNoticeAlarm(int limit) {
        return noticeMapper.selectNoticeAlarm(limit);
    }

    /**
     * 공지사항 폼을 게시글번호로 조회합니다.
     * @param boardId 공지사항 번호
     * @return BoardForm
     */
    public BoardForm selectNoticeFormByBoardId(Long boardId) {
        return noticeMapper.selectNoticeFormByBoardId(boardId);
    }

    /**
     * 공지사항 알림을 삭제합니다.
     * @param boardId 공지사항 번호
     */
    public void deleteNoticeAlarm(Long boardId) {
        noticeMapper.deleteNoticeAlarm(boardId);
    }
}
