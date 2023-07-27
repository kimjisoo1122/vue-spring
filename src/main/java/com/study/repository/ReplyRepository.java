package com.study.repository;

import com.study.dto.ReplyDto;
import com.study.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 'reply' 테이블 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final ReplyMapper replyMapper;

    /**
     * 댓글을 등록합니다.
     * @param reply 댓글DTO
     */
    public void insert(ReplyDto reply) {
        replyMapper.insert(reply);
    }

    /**
     * 댓글번호로 댓글을 조회합니다.
     * @param replyId 댓글번호
     * @return ReplyDto
     */
    public ReplyDto selectById(Long replyId) {
        return replyMapper.selectById(replyId);
    }

    /**
     * 게시글에 등록된 댓글을 조회합니다.
     * @param boardId 게시글번호
     * @return List<ReplyDto>
     */
    public List<ReplyDto> selectByBoardId(Long boardId) {
        return replyMapper.selectByBoardId(boardId);
    }

    /**
     * 댓글을 삭제합니다.
     * @param replyId 댓글번호
     */
    public void delete(Long replyId) {
        replyMapper.delete(replyId);
    }

    /**
     * 게시글에 등록된 댓글을 삭제합니다.
     * @param boardId 게시글 번호
     */
    public void deleteByBoardId(Long boardId) {
        replyMapper.deleteByBoardId(boardId);
    }
}
