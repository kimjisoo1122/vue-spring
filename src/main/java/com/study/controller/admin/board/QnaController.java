package com.study.controller.admin.board;

import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.dto.QnaValidation;
import com.study.enums.BoardType;
import com.study.enums.FormType;
import com.study.page.PageHandler;
import com.study.repository.board.BoardRepository;
import com.study.repository.board.QnaRepository;
import com.study.service.board.QnaService;
import com.study.util.BoardUtil;
import com.study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 문의 게시판 컨트롤러
 */
@Controller
@RequestMapping("/admin/qna")
@RequiredArgsConstructor
public class QnaController {

    /**
     * 자유게시판 목록 리다이렉트 경로
     */
    private final String QNA_REDIRECT_PATH = "redirect:/admin/qna";

    private final BoardRepository boardRepository;
    private final QnaRepository qnaRepository;

    private final QnaService qnaService;

    /**
     * 문의게시판 목록을 조회합니다.
     * @param page 현재페이지 default: 1
     * @param limit 페이지사이즈 default: 10
     * @param condition 검색조건
     * @param model boardList: 게시글 목록, categoryList: 카테고리목록, pageHandler: 페이징객체
     * @return board/boardList 게시글목록
     */
    @GetMapping()
    public String qnaList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        condition.setSearchCondition(BoardType.QNA, page, limit);

        model.addAttribute("boardList", qnaService.findQnaList(condition));
        model.addAttribute("pageHandler",
                new PageHandler(page, boardRepository.countByCondition(condition), limit));

        return BoardUtil.LIST_PATH;
    }

    /**
     * 문의게시글 상세페이지를 조회합니다.
     * @param boardId 게시글 번호
     * @param condition 검색조건
     * @param model form 게시글 폼
     * @return board/boardForm 게시글폼
     */
    @GetMapping("/{boardId}")
    public String qnaDetail(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        boardRepository.increaseViewCnt(boardId);

        BoardForm qnaForm = qnaRepository.selectQnaForm(boardId);
        qnaForm.setBoardFormType(BoardType.QNA, FormType.ANSWER);

        model.addAttribute("form", qnaForm);

        return BoardUtil.FORM_PATH;
    }




    /**
     * 문의게시글 답변을 등록/수정합니다.
     * @param boardId 게시글 번호
     * @param condition 검색조건
     * @param form  답변폼
     * @param bindingResult 유효성검증 객체
     * @return 해당 게시글로 리다이렉트
     */
    @PostMapping("/{boardId}")
    public String registerAnswer(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            @Validated(QnaValidation.class) @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BoardUtil.FORM_PATH;
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
