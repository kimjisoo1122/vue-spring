package com.study.controller.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.dto.api.ResponseValidFormDto;
import com.study.enums.BoardType;
import com.study.exception.BoardNotFoundException;
import com.study.exception.NotAuthorisedToBoardException;
import com.study.service.board.BoardService;
import com.study.service.board.QnaService;
import com.study.util.BoardUtil;
import com.study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 문의게시판 API 컨트롤러
 */
@RestController
@RequestMapping("/api/qna")
@RequiredArgsConstructor
public class QnaController {

    private final BoardService boardService;
    private final QnaService qnaService;

    /**
     * 문의게시글을 등록 합니다.
     *
     * @param form 문의게시글 등록 폼
     * @param bindingResult 유효성검증객체
     */
    @PostMapping()
    public ResponseEntity<ResponseValidFormDto> register(
            @Validated @ModelAttribute BoardForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(BoardUtil.createValidFormFailResponse(bindingResult));
        }

        SecurityUtil.setFormUser(form);
        form.setBoardType(BoardType.QNA);

        ResponseValidFormDto response = new ResponseValidFormDto(ResponseApiStatus.SUCCESS);
        response.setData(qnaService.register(form));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * 문의게시글 목록을 조회합니다.
     *
     * @param condition 검색조건
     * @return qnaList: 문의게시글 목록, totalCnt: 자유게시글 총 개수
     */
    @GetMapping
    public ResponseEntity<ResponseDto> qnaList(
            @ModelAttribute BoardSearchCondition condition) {

        condition.setSearchParams(BoardType.QNA);

        List<BoardDto> qnaList = qnaService.findQnaList(condition);
        int totalCnt = qnaService.getTotalCnt(condition);

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(Map.of("qnaList", qnaList, "totalCnt", totalCnt));

        return ResponseEntity.ok(response);
    }

    /**
     * 문의게시글 상세정보를 조회합니다.
     *
     * @param boardId 문의게시글 번호
     * @throws BoardNotFoundException 게시글이 존재하지 않는 경우 발생하는 예외
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto> freeDetail(
            @PathVariable("boardId") Long boardId) {

        boardService.increaseViewCnt(boardId);

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);

        qnaService.findQnaDetail(boardId)
                .ifPresentOrElse(response::setData,
                        () -> {
                            throw new BoardNotFoundException();
                        });

        return ResponseEntity.ok(response);
    }

    /**
     * 문의게시글을 업데이트 합니다.
     *
     * @param form 문의게시글 업데이트 폼
     * @param bindingResult 유효성검증객체
     * @throws IOException 첨부파일을 저장하는데 발생하는 예외
     */
    @PutMapping("/{boardId}")
    public ResponseEntity<ResponseValidFormDto> update(
            @PathVariable("boardId") Long boardId,
            @Validated @ModelAttribute BoardForm form,
            BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(BoardUtil.createValidFormFailResponse(bindingResult));
        }

        checkRegisteredUserId(boardId);

        SecurityUtil.setFormUser(form);
        form.setBoardId(boardId);

        ResponseValidFormDto response = new ResponseValidFormDto(ResponseApiStatus.SUCCESS);
        qnaService.update(form);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * 문의게시글을 삭제합니다.
     *
     * @param boardId 게시글번호
     */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto> delete(
            @PathVariable("boardId") Long boardId) {

        checkRegisteredUserId(boardId);

        qnaService.delete(boardId);

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(response);
    }


    /**
     * 해당 게시글에 대한 작성자와 현재 요청한 사용자가 동일한지 확인합니다.
     *
     * @param boardId 게시글번호
     */
    private void checkRegisteredUserId(Long boardId) {
        qnaService.findQnaDetail(boardId)
                .ifPresentOrElse(board -> {
                    if (!board.getUserId().equals(SecurityUtil.getUserId())) {
                        throw new NotAuthorisedToBoardException();
                    }
                }, () -> {
                    throw new BoardNotFoundException();
                });
    }
}
