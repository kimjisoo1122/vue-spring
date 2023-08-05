package com.study.controller;

import com.study.dto.BoardDto;
import com.study.dto.BoardSearchCondition;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.enums.BoardType;
import com.study.service.board.FreeService;
import com.study.service.board.GalleryService;
import com.study.service.board.NoticeService;
import com.study.service.board.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 게시판 메인 홈 컨트롤러
 */
@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final NoticeService noticeService;
    private final FreeService freeService;
    private final GalleryService galleryService;
    private final QnaService qnaService;

    /**
     * 홈 화면의 게시글 목록들을 조회합니다.
     */
    @GetMapping
    public ResponseEntity<ResponseDto> home() {

        BoardSearchCondition condition = new BoardSearchCondition();
        condition.setLimit(5);
        condition.setSearchParams(BoardType.NOTICE);
        List<BoardDto> noticeList = noticeService.findNoticeList(condition);
        List<BoardDto> alarmList = noticeService.findAlarmList(3);
        alarmList.addAll(noticeList);

        condition.setBoardType(BoardType.FREE);
        List<BoardDto> freeList = freeService.findFreeList(condition);

        condition.setBoardType(BoardType.QNA);
        List<BoardDto> qnaList = qnaService.findQnaList(condition);

        condition.setLimit(3);
        condition.setBoardType(BoardType.GALLERY);
        List<BoardDto> galleryList = galleryService.findGalleryList(condition);

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(Map.of(
                "noticeList", alarmList,
                "freeList", freeList,
                "qnaList", qnaList,
                "galleryList", galleryList)
        );

        return ResponseEntity.ok(response);
    }
}
