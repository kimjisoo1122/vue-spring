package com.study.controller.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.enums.BoardType;
import com.study.exception.BoardNotFoundException;
import com.study.service.board.BoardService;
import com.study.service.board.FreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 자유게시판 API 컨트롤러
 */

@RestController
@RequestMapping("/api/frees")
@RequiredArgsConstructor
public class FreeController {

    private final FreeService freeService;
    private final BoardService boardService;

    /**
     * 공지사항 목록을 조회합니다.
     *
     * @param condition 검색조건
     * @return noticeList: 공지사항목록, totalCnt: 공지사항 총 개수
     */
    @GetMapping
    public ResponseEntity<ResponseDto> freeList(
            @ModelAttribute BoardSearchCondition condition) {

        condition.setSearchParams(BoardType.FREE);

        List<BoardDto> freeList = freeService.findFreeList(condition);
        int totalCnt = freeService.getTotalCnt(condition);

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(Map.of("freeList", freeList, "totalCnt", totalCnt));

        return ResponseEntity.ok(response);
    }

    /**
     * 자유게시글 상세정보를 조회합니다.
     *
     * @param boardId 자유게시글 번호
     * @throws BoardNotFoundException 게시글이 존재하지 않는 경우 발생하는 예외
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto> noticeDetail(
            @PathVariable("boardId") Long boardId) {

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);

        freeService.findFreeDetail(boardId)
                .ifPresentOrElse(board -> {
                            boardService.increaseViewCnt(boardId);
                            response.setData(board);
                        },
                        () -> {
                            throw new BoardNotFoundException();
                        });

        return ResponseEntity.ok(response);
    }
}
