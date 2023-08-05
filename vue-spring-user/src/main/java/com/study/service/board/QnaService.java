package com.study.service.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.enums.BoardType;
import com.study.repository.board.BoardRepository;
import com.study.repository.board.QnaRepository;
import com.study.util.BoardUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     *
     * @param condition 검색조건
     * @return 문의 게시글 목록
     */
    public List<BoardDto> findQnaList(BoardSearchCondition condition) {
        return qnaRepository.selectByCondition(condition);
    }

    /**
     * 검색조건에 맞는 문의게시글 총 개수를 조회합니다.
     *
     * @param condition 검색조건
     * @return 문의게시글 총 개수
     */
    public int getTotalCnt(BoardSearchCondition condition) {
        return boardRepository.countByCondition(condition);
    }

    /**
     * 문의게시글의 답변을 등록/수정합니다.
     *
     * @param form 답변 폼
     */
    public void registerAnswer(BoardForm form) {
        BoardDto qnaBoard = new BoardDto();
        qnaBoard.setBoardId(form.getBoardId());
        qnaBoard.setQnaAnswer(form.getQnaAnswer());

        qnaRepository.updateQnaDetail(qnaBoard);
    }

    /**
     * 문의게시글을 수정합니다.
     *
     * @param form 수정 폼
     */
    public void update(BoardForm form) {
        BoardDto updateBoard = BoardUtil.createUpdateBoard(form);
        boardRepository.update(updateBoard);

        updateBoard.setQnaSecret(form.isQnaSecret());
        qnaRepository.updateQnaDetail(updateBoard);
    }

    /**
     * 문의게시글을 삭제합니다.
     *
     * @param boardId 게시글번호
     */
    public void delete(Long boardId) {
        boardRepository.delete(boardId);
    }

    public Optional<BoardDto> findQnaDetail(Long boardId) {
        BoardDto board = new BoardDto();
        board.setBoardId(boardId);
        board.setBoardType(BoardType.QNA);

        return Optional.ofNullable(qnaRepository.selectDetailById(boardId));
    }

    /**
     * 문의게시글을 등록합니다.
     *
     * @param form 등록 폼
     * @return 등록된 문의게시글 번호
     */
    public Long register(BoardForm form) {
        BoardDto registerBoard = BoardUtil.createRegisterBoard(form);
        registerBoard.setBoardType(BoardType.QNA);

        boardRepository.insert(registerBoard);

        registerBoard.setQnaSecret(form.isQnaSecret());
        qnaRepository.insertQnaDetail(registerBoard);

        return registerBoard.getBoardId();
    }
}
