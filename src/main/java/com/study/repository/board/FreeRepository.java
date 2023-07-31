package com.study.repository.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import com.study.mapper.board.FreeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 자유게시판 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class FreeRepository {

    private final FreeMapper freeMapper;

    /**
     * 검색조건에 따라 자유게시글 목록을 조회합니다.
     *
     * @param condition 검색조건
     * @return 자유게시글 목록
     */
    public List<BoardDto> selectFreeListByCondition(BoardSearchCondition condition) {
        return freeMapper.selectFreeListByCondition(condition);
    }
}
