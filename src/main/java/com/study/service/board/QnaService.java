package com.study.service.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.repository.board.BoardRepository;
import com.study.repository.board.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 문의게시판 서비스
 */
@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;
    private final BoardRepository boardRepository;

    /**
     * 문의게시글 목록을 조회합니다.
     * @param condition 검색조건
     * @return List<BoardDto>
     */
    public List<BoardDto> findQnaList(BoardSearchCondition condition) {
        return qnaRepository.selectByCondition(condition);
    }

    /**
     * 문의게시글의 답변을 등록/수정합니다.
     * @param form 답변 폼
     */
    public void registerAnswer(BoardForm form) {
        BoardDto qnaBoard = new BoardDto();
        qnaBoard.setBoardId(form.getBoardId());
        qnaBoard.setQnaAnswer(form.getQnaAnswer());
        qnaRepository.updateQnaDetail(qnaBoard);
    }

    /**
     * 문의게시글을 삭제합니다.
     * @param boardId 게시글번호
     */
    public void delete(Long boardId) {
        boardRepository.delete(boardId);
    }
}