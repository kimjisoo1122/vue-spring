package com.study.mapper.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 자유게시판 매퍼
 */
@Mapper
public interface FreeMapper {

    List<BoardDto> selectFreeListByCondition(BoardSearchCondition condition);
}
