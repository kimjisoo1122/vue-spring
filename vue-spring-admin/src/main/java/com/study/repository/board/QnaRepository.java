package com.study.repository.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.mapper.board.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 문의게시판 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class QnaRepository {

    private final QnaMapper qnaMapper;

    /**
     * 문의게시글 상세정보를 등록합니다.
     *
     * @param board 문의게시글상세 정보
     */
    public void insertQnaDetail(BoardDto board) {
        qnaMapper.insertQnaDetail(board);
    }

    /**
     * 문의게시글 목록을 조건에 따라 조회합니다.
     *
     * @param condition 검색조건
     * @return 문의게시글 목록
     */
    public List<BoardDto> selectByCondition(BoardSearchCondition condition) {
        return qnaMapper.selectByCondition(condition);
    }

    /**
     * 문의게시글 번호로 문의게시글 폼을 조회합니다.
     *
     * @param board 문의게시글 정보
     * @return 문의게시글 폼
     */
    public BoardForm selectQnaForm(BoardDto board) {
        return qnaMapper.selectQnaForm(board);
    }

    /**
     * 문의게시글 상세정보를 업데이트합니다.
     */
    public void updateQnaDetail(BoardDto board) {
        qnaMapper.updateQnaDetail(board);
    }
}
