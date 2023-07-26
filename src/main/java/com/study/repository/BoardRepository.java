package com.study.repository;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import com.study.mapper.BoardMapper;
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
     * @param board 게시글 DTO
     */
    public void insertBoard(BoardDto board) {
        boardMapper.insertBoard(board);
    }

    /**
     * 조건으로 게시글을 조회합니다.
     * @param condition 검색조건
     * @return List<BoardDto>
     */
    public List<BoardDto> selectByCondition(BoardSearchCondition condition) {
        return boardMapper.selectByCondition(condition);
    }

    /**
     * 조회수를 1 증가시킵니다.
     * @param boardId 게시글번호
     */
    public void increaseViewCnt(Long boardId) {
        boardMapper.increaseViewCnt(boardId);
    }

    /**
     * 게시글을 수정합니다.
     * @param board 게시글 DTO
     */
    public void updateBoard(BoardDto board) {
        boardMapper.updateBoard(board);
    }

    /**
     * 게시글을 삭제합니다.
     * @param boardId 게시글번호
     */
    public void deleteBoard(Long boardId) {
        boardMapper.deleteBoard(boardId);
    }
}
