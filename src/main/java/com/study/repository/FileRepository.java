package com.study.repository;

import com.study.dto.FileDto;
import com.study.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final FileMapper fileMapper;

    /**
     * 파일을 생성합니다.
     * @param fileDto 파일DTO
     * @return 파일 PK
     */
    public Long insert(FileDto fileDto) {
        return fileMapper.insert(fileDto);
    }

    /**
     * 게시글에 등록된 파일을 조회합니다.
     * @param boardId 게시글 번호
     * @return List<FileDto> 파일 목록
     */
    public List<FileDto> selectByBoardId(Long boardId) {
        return fileMapper.selectByBoardId(boardId);
    }

    /**
     * 파일을 조회합니다.
     * @param fileId 파일 번호
     * @return FileDto
     */
    public FileDto selectById(Long fileId) {
        return fileMapper.selectById(fileId);
    }

    /**
     * 파일을 삭제합니다.
     * @param fileId 파일 번호
     */
    public void delete(Long fileId) {
        fileMapper.delete(fileId);
    }

    /**
     * 게시글에 첨부된 파일을 삭제합니다.
     * @param boardId 게시글 번호
     */
    public void deleteByBoardId(Long boardId) {
        fileMapper.deleteByBoardId(boardId);
    }
}
