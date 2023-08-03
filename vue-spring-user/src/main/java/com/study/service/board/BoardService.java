package com.study.service.board;

import com.study.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 통합게시판 서비스
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 조회수를 1 증가시킵니다.
     */
    public void increaseViewCnt(Long boardId) {
        boardRepository.increaseViewCnt(boardId);
    }
}
