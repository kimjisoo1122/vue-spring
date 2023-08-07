package com.study.mapper.board;

import com.study.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 공지사항 매퍼
 */
@Mapper
public interface NoticeMapper {
    List<BoardDto> selectNoticeAlarm(int limit);
    BoardDto selectNoticeDetail(BoardDto board);
}
