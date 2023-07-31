package com.study.repository.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 'board' 테이블 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final BoardMapper boardMapper;

    /**
     * 게시글을 등록합니다.
     *
     * @param board 게시글 DTO
     */
    public void insert(BoardDto board) {
        boardMapper.insert(board);
    }

    /**
     * 조건으로 게시글을 조회합니다.
     *
     * @param condition 검색조건
     * @return 게시글 목록
     */
    public List<BoardDto> selectByCondition(BoardSearchCondition condition) {
        return boardMapper.selectByCondition(condition);
    }

    /**
     * 조건으로 게시글을 갯수를 조회합니다.
     *
     * @param condition 검색조건
     * @return 게시글 총 갯수
     */
    public int countByCondition(BoardSearchCondition condition) {
        return boardMapper.countByCondition(condition);
    }


    /**
     * 게시글번호로 게시글 폼을 조회합니다.
     *
     * @param board) 게시글 DTO
     * @return 게시글 폼
     */
    public BoardForm selectForm(BoardDto board) {
        return boardMapper.selectForm(board);
    }



    /**
     * 조회수를 1 증가시킵니다.
     *
     * @param boardId 게시글번호
     */
    public void increaseViewCnt(Long boardId) {
        boardMapper.increaseViewCnt(boardId);
    }

    /**
     * 게시글을 수정합니다.
     *
     * @param board 게시글 DTO
     */
    public void update(BoardDto board) {
        boardMapper.update(board);
    }

    /**
     * 게시글을 삭제합니다.
     *
     * @param boardId 게시글번호
     */
    public void delete(Long boardId) {
        boardMapper.delete(boardId);
    }
}
