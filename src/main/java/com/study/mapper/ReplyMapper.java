package com.study.mapper;

import com.study.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    void insert(ReplyDto reply);
    ReplyDto selectById(Long replyId);
    List<ReplyDto> selectByBoardId(Long boardId);
    void delete(Long replyId);
    void deleteByBoardId(Long boardId);
}
