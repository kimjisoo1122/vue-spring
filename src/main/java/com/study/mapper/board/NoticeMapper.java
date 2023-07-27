package com.study.mapper.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 'notice' 테이블 매퍼 입니다.
 */
@Mapper
public interface NoticeMapper {
    void insertNoticeAlarm(Long boardId);

    List<BoardDto> selectNoticeAlarm(int limit);

    BoardForm selectNoticeForm(Long boardId);

    void deleteNoticeAlarm(Long boardId);
}
