package com.study.service.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.repository.board.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 갤러리 게시판 서비스
 */
@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public List<BoardDto> findGalleryList(BoardSearchCondition condition) {
        return galleryRepository.selectByCondition(condition);
    }

    public void register(BoardForm form) {

    }

    public void update(BoardForm form) {
    }

    public void delete(Long boardId) {

    }
}
