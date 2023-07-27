package com.study.repository.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import com.study.mapper.board.GalleryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 갤러리 게시판 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class GalleryRepository {

    private final GalleryMapper galleryMapper;

    public List<BoardDto> selectByCondition(BoardSearchCondition condition) {
        return galleryMapper.selectByCondition(condition);
    }
}
