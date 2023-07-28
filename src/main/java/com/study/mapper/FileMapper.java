package com.study.mapper;

import com.study.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 'file' 테이블 매퍼
 */
@Mapper
public interface FileMapper {

    Long insert(FileDto fileDto);
    List<FileDto> selectByBoardId(Long boardId);
    FileDto selectById(Long fileId);
    void delete(Long fileId);
    List<FileDto> selectGalleryFile(Long boardId);
}
