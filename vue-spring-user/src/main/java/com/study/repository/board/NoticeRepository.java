package com.study.repository.board;

import com.study.dto.BoardDto;
import com.study.mapper.board.NoticeMapper;
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
     *
     * @param boardId 공지사항 번호
     */
    public void insertNoticeAlarm(Long boardId) {
        noticeMapper.insertNoticeAlarm(boardId);
    }

    /**
     * 알림으로 등록된 게시글을 limit만큼 최신순으로 조회합니다.
     *
     * @param limit 알림글갯수
     * @return List<BoardDto> 알림글 목록
     */
    public List<BoardDto> selectNoticeAlarm(int limit) {
        return noticeMapper.selectNoticeAlarm(limit);
    }

    /**
     * 공지사항 상세정보를 조회합니다.
     *
     * @param board 공지사항 DTO
     * @return 공지사항 상세정보
     */
    public BoardDto selectNoticeDetail(BoardDto board) {
        return noticeMapper.selectNoticeDetail(board);
    }

    /**
     * 공지사항 알림을 삭제합니다.
     *
     * @param boardId 공지사항 번호
     */
    public void deleteNoticeAlarm(Long boardId) {
        noticeMapper.deleteNoticeAlarm(boardId);
    }
}
