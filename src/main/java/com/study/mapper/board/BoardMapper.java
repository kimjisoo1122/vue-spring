package com.study.mapper.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 통합게시판 매퍼
 */
@Mapper
public interface BoardMapper {

    void insert(BoardDto board);
    List<BoardDto> selectByCondition(BoardSearchCondition condition);
    BoardForm selectForm(BoardDto board);
    void update(BoardDto board);
    void delete(Long boardId);
    void increaseViewCnt(Long boardId);
    int countByCondition(BoardSearchCondition condition);

}
