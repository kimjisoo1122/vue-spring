package com.study.mapper;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    void insertBoard(BoardDto board);

    List<BoardDto> selectByCondition(BoardSearchCondition condition);
}
