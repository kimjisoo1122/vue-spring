package com.study.repository;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeRepository {

    private final NoticeMapper noticeMapper;

    public void insertNoticeAlarm(Long boardId) {
        noticeMapper.insertNoticeAlarm(boardId);
    }

    public List<BoardDto> selectNoticeAlarm(int limit) {
        return noticeMapper.selectNoticeAlarm(limit);
    }

    public BoardForm selectNoticeByBoardId(Long boardId) {
        return noticeMapper.selectNoticeByBoardId(boardId);
    }
}
