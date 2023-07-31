package com.study.controller.board;

import com.study.constant.Board;
import com.study.exception.BoardNotFoundException;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.dto.QnaValidation;
import com.study.enums.BoardType;
import com.study.enums.FormType;
import com.study.page.PageHandler;
import com.study.service.board.QnaService;
import com.study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 문의게시판 컨트롤러
 */
@Controller
@RequestMapping("/admin/qna")
@RequiredArgsConstructor
public class QnaController {

    /**
     * 자유게시판 목록 리다이렉트 경로
     */
    private final String QNA_REDIRECT_PATH = "redirect:/admin/qna";

    private final QnaService qnaService;

    /**
     * 문의게시글 목록을 조회합니다.
     * @param condition 검색조건
     * @param model boardList: 문의게시글 목록, categoryList: 카테고리 목록, pageHandler: 페이징 정보
     * @return 게시글목록 뷰
     */
    @GetMapping()
    public String qnaList(
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        condition.setSearchParams(BoardType.QNA);

        model.addAttribute("boardList", qnaService.findQnaList(condition));
        model.addAttribute("pageHandler",
                new PageHandler(condition, qnaService.getTotalCnt(condition)));

        return Board.LIST_PATH;
    }

    /**
     * 문의게시글 상세페이지를 조회합니다.
     * @param boardId 문의게시글 번호
     * @param condition 검색조건
     * @param model form 문의게시글 수정 폼
     * @return 문의게시글 수정 폼
     */
    @GetMapping("/{boardId}")
    public String qnaDetail(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        BoardForm qnaForm = qnaService.findQnaForm(boardId);
        if (qnaForm == null) {
            throw new BoardNotFoundException();
        }

        qnaForm.setFormType(FormType.ANSWER);

        model.addAttribute("form", qnaForm);

        return Board.FORM_PATH;
    }

    /**
     * 문의게시글 답변을 등록합니다.
     *
     * @param boardId 문의게시글 번호
     * @param condition 검색조건
     * @param form 문의게시글 수정 폼
     * @param bindingResult 유효성검증객체
     * @return 유효성검증에 실패 한 경우 폼으로, 수정에 성공 한 경우 수정된 게시글로 리다이렉트
     */
    @PostMapping("/{boardId}")
    public String registerAnswer(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            @Validated(QnaValidation.class) @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Board.FORM_PATH;
        }

        // 현재 인증된 사용자정보로 유저정보를 설정합니다.
        SecurityUtil.setFormUser(form);
        form.setBoardId(boardId);

        qnaService.registerAnswer(form);

        return QNA_REDIRECT_PATH + "/" +  boardId + condition.getEncodedQueryParam();
    }

    /**
     * 문의게시글을 삭제합니다.
     * @param boardId 게시글 번호
     * @param condition 검색조건
     * @return 게시글 목록으로 리다이렉트
     */
    @PostMapping("/{boardId}/delete")
    public String delete(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        qnaService.delete(boardId);

        return QNA_REDIRECT_PATH + condition.getEncodedQueryParam();
    }
}
