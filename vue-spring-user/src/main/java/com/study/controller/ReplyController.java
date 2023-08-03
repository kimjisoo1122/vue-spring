package com.study.controller;

import com.study.dto.ReplyDto;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.service.ReplyService;
import com.study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 댓글을 처리하는 API 컨트롤러
 */
@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 댓글을 등록합니다.
     *
     * @param replyDto 댓글 정보
     * @return 등록된 댓글 객체
     */
    @PostMapping
    public ResponseEntity<ResponseDto> register(
            @RequestBody ReplyDto replyDto) {

        replyDto.setUserId(SecurityUtil.getUserId());
        replyDto.setUserName(SecurityUtil.getUserName());

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(replyService.register(replyDto));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * 게시글에 등록된 댓글을 조회합니다.
     *
     * @param boardId 게시글 번호
     * @return 게시글에 등록된 댓글목록
     */
    @GetMapping("/board/{boardId}")
    public ResponseEntity<ResponseDto> boardReply(
            @PathVariable("boardId") Long boardId) {

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(replyService.findByBoardId(boardId));

        return ResponseEntity.ok(response);
    }


    /**
     * 댓글을 삭제합니다.
     *
     * @param replyId 댓글 번호
     */
    @DeleteMapping("/{replyId}")
    public ResponseEntity<ResponseDto> delete(
            @PathVariable Long replyId) {

        replyService.delete(replyId);

        ResponseDto response = new ResponseDto();
        response.setStatus(ResponseApiStatus.SUCCESS);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(response);
    }
}
