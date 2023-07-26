package com.study.repository;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import com.study.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final BoardMapper boardMapper;

    public void insertBoard(BoardDto board) {
        boardMapper.insertBoard(board);
    }

    public List<BoardDto> selectByCondition(BoardSearchCondition condition) {
        return boardMapper.selectByCondition(condition);
    }

}
