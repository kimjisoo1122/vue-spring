package com.study.repository.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import com.study.mapper.board.FreeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FreeRepository {

    private final FreeMapper freeMapper;

    public List<BoardDto> selectFreeListByCondition(BoardSearchCondition condition) {
        return freeMapper.selectFreeListByCondition(condition);
    }
}
