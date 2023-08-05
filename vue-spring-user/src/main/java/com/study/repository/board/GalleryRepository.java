package com.study.repository.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import com.study.mapper.board.GalleryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 갤러리게시판 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class GalleryRepository {

    private final GalleryMapper galleryMapper;

    /**
     * 갤러리디테일에서 가장 순서가 빠른 썸네일,
     * 게시글이 포함한 갤러리 갯수를 포함한 갤러리게시글 정보를 조회합니다.
     *
     * @param condition 검색조건
     * @return 갤러리 게시글 목록
     */
    public List<BoardDto> selectByCondition(BoardSearchCondition condition) {
        return galleryMapper.selectByCondition(condition);
    }

    /**
     * 게시글의 마지막오더를 조회한 후 갤러리디테일을 등록합니다.
     *
     * @param board 게시글 DTO
     */
    public void insertGalleryDetail(BoardDto board) {
        galleryMapper.insertGalleryDetail(board);
    }

    /**
     * 파일번호로 등록된 썸네일이름을 조회합니다.
     *
     * @param fileId 파일번호
     * @return 썸네일 이름
     */

    public String selectThumbNameByFileId(Long fileId) {
        return galleryMapper.selectThumbNameByFileId(fileId);
    }

    /**
     * 파일번호로 갤러리디테일을 삭제합니다.
     *
     * @param fileId 파일번호
     */
    public void deleteDetailByFileId(Long fileId) {
        galleryMapper.deleteDetailByFileId(fileId);
    }
}
