package com.study.mapper.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    void insert(BoardDto board);

    List<BoardDto> selectByCondition(BoardSearchCondition condition);
    BoardForm selectForm(Long boardId);

    void increaseViewCnt(Long boardId);

    void update(BoardDto boardDto);

    void delete(Long boardId);
}
