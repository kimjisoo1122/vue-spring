package com.study.controller.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.dto.FileDto;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.dto.api.ResponseValidFormDto;
import com.study.enums.BoardType;
import com.study.exception.BoardNotFoundException;
import com.study.exception.NotAuthorisedToBoardException;
import com.study.service.board.BoardService;
import com.study.service.board.GalleryService;
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
 * 갤러리게시판 API 컨트롤러
 */

@RestController
@RequestMapping("/api/galleries")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;
    private final BoardService boardService;

    /**
     * 갤러리를 등록 합니다.
     *
     * @param form 갤러리 등록 폼
     * @param bindingResult 유효성검증객체
     * @throws IOException 첨부파일을 저장하는데 발생하는 예외
     */
    @PostMapping()
    public ResponseEntity<ResponseValidFormDto> register(
            @Validated @ModelAttribute BoardForm form,
            BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(BoardUtil.createValidFormFailResponse(bindingResult));
        }

        SecurityUtil.setFormUser(form);
        form.setBoardType(BoardType.GALLERY);

        ResponseValidFormDto response = new ResponseValidFormDto(ResponseApiStatus.SUCCESS);
        response.setData(galleryService.register(form));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * 갤러리 목록을 조회합니다.
     *
     * @param condition 검색조건
     * @return galleryList: 갤러리 목록, totalCnt: 갤러리 총 개수
     */
    @GetMapping
    public ResponseEntity<ResponseDto> galleyList(
            @ModelAttribute BoardSearchCondition condition) {

        condition.setSearchParams(BoardType.GALLERY);

        List<BoardDto> galleryList = galleryService.findGalleryList(condition);
        int totalCnt = galleryService.getTotalCnt(condition);

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(Map.of("galleryList", galleryList, "totalCnt", totalCnt));

        return ResponseEntity.ok(response);
    }

    /**
     * 갤러리 상세정보를 조회합니다.
     *
     * @param boardId 갤러리 번호
     * @throws BoardNotFoundException 게시글이 존재하지 않는 경우 발생하는 예외
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto> freeDetail(
            @PathVariable("boardId") Long boardId) {

        boardService.increaseViewCnt(boardId);

        BoardDto galleryBoard =
                galleryService.findGallery(boardId).orElseThrow(BoardNotFoundException::new);

        List<FileDto> fileList = galleryService.findGalleryFileList(boardId);

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(Map.of("gallery", galleryBoard, "fileList", fileList));

        return ResponseEntity.ok(response);
    }

    /**
     * 갤러리를 업데이트 합니다.
     *
     * @param form 갤러리 업데이트 폼
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
        galleryService.update(form);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * 갤러리를 삭제합니다.
     * @param boardId 게시글번호
     */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto> delete(
            @PathVariable("boardId") Long boardId) {

        checkRegisteredUserId(boardId);

        galleryService.delete(boardId);

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
        galleryService.findGallery(boardId)
                .ifPresentOrElse(board -> {
                    if (!board.getUserId().equals(SecurityUtil.getUserId())) {
                        throw new NotAuthorisedToBoardException();
                    }
                }, () -> {
                    throw new BoardNotFoundException();
                });
    }
}
