package com.study.controller.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.enums.BoardType;
import com.study.exception.BoardNotFoundException;
import com.study.service.board.BoardService;
import com.study.service.board.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 공지사항 API 컨트롤러
 */

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final BoardService boardService;

    /**
     * 공지사항 목록을 조회합니다.
     *
     * @param condition 검색조건
     * @return noticeList: 공지사항목록, totalCnt: 공지사항 총 개수
     */
    @GetMapping
    public ResponseEntity<ResponseDto> noticeList(
            @ModelAttribute BoardSearchCondition condition) {

        condition.setSearchParams(BoardType.NOTICE);

        List<BoardDto> noticeList = noticeService.findNoticeList(condition);
        int totalCnt = noticeService.getTotalCnt(condition);

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(Map.of("noticeList", noticeList, "totalCnt", totalCnt));

        return ResponseEntity.ok(response);
    }

    /**
     * 공지사항 알림글 목록을 조회합니다.
     *
     * @param limit 알림글 개수
     * @return 알림글 목록
     */
    @GetMapping("/alarms")
    public ResponseEntity<ResponseDto> alarmList(
            @RequestParam(value = "limit", defaultValue = "5") int limit) {

        List<BoardDto> alarmList = noticeService.findAlarmList(limit);

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(alarmList);

        return ResponseEntity.ok(response);
    }

    /**
     * 공지사항 상세정보를 조회합니다.
     *
     * @param boardId 공지사항 번호
     * @throws BoardNotFoundException 게시글이 존재하지 않는 경우 발생하는 예외
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto> noticeDetail(
            @PathVariable("boardId") Long boardId) {

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);

        noticeService.findNoticeDetail(boardId)
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
