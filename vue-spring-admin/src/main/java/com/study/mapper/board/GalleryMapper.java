package com.study.mapper.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 갤러리게시판 매퍼
 */
@Mapper
public interface GalleryMapper {

    void insertGalleryDetail(BoardDto board);
    List<BoardDto> selectByCondition(BoardSearchCondition condition);
    String selectThumbNameByFileId(Long fileId);
    void deleteDetailByFileId(Long fileId);
}
