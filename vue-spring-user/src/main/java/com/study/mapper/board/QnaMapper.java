package com.study.mapper.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 문의게시판 매퍼
 */
@Mapper
public interface QnaMapper {

    void insertQnaDetail(BoardDto board);
    List<BoardDto> selectByCondition(BoardSearchCondition condition);
    BoardDto selectDetailById(Long boardId);
    void updateQnaDetail(BoardDto board);
}
