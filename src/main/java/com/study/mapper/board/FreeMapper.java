package com.study.mapper.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeMapper {

    List<BoardDto> selectFreeListByCondition(BoardSearchCondition condition);
}
