package com.study.service;

import com.study.dto.ReplyDto;
import com.study.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 댓글울 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    /**
     * 게시글의 댓글을 등록한 후
     * 등록된 댓글을 조회하여 반환합니다.
     *
     * @param replyDto 댓글정보
     * @return 등록된 댓글
     */
    @Transactional
    public ReplyDto register(ReplyDto replyDto) {
        replyRepository.insert(replyDto);
        Long replyId = replyDto.getReplyId();

        return replyRepository.selectById(replyId);
    }

    /**
     * 댓글을 조회합니다.
     * @param replyId 댓글번호
     * @return ReplyDto 댓글정보
     */
    public ReplyDto findById(Long replyId) {
        return replyRepository.selectById(replyId);
    }

    /**
     * 게시글에 등록된 댓글을 조회합니다.
     *
     * @param boardId 게시글 번호
     * @return 등록된 댓글 목록
     */
    public List<ReplyDto> findByBoardId(Long boardId) {
        return replyRepository.selectByBoardId(boardId);
    }

    /**
     * 댓글을 삭제합니다
     * @param replyId 댓글 번호
     */
    public void delete(Long replyId) {
        replyRepository.delete(replyId);
    }
}
