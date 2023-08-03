package com.study.mapper.board;

import com.study.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 공지사항 매퍼
 */
@Mapper
public interface NoticeMapper {
    void insertNoticeAlarm(Long boardId);
    List<BoardDto> selectNoticeAlarm(int limit);
    BoardDto selectNoticeDetail(BoardDto board);
    void deleteNoticeAlarm(Long boardId);
}
