package com.study.mapper.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 갤러리 게시판 매퍼
 */
@Mapper
public interface GalleryMapper {

    List<BoardDto> selectByCondition(BoardSearchCondition condition);
}
