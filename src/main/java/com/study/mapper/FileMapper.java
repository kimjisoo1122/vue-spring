package com.study.mapper;

import com.study.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    Long insert(FileDto fileDto);
    List<FileDto> selectByBoardId(Long boardId);
    FileDto selectById(Long fileId);
    void delete(Long fileId);
    void deleteByBoardId(Long boardId);
}
