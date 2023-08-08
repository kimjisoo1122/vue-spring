package com.study.controller.board;

import com.study.constant.Board;
import com.study.constant.Category;
import com.study.exception.BoardNotFoundException;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.enums.BoardType;
import com.study.enums.FormType;
import com.study.page.PageHandler;
import com.study.service.CategoryService;
import com.study.service.FileService;
import com.study.service.ReplyService;
import com.study.service.board.FreeService;
import com.study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 자유게시판 컨트롤러
 */
@Controller
@RequestMapping("/admin/free")
@RequiredArgsConstructor
public class FreeController {

    /**
     * 자유게시글 목록 리다이렉트 경로
     */
    private final String FREE_REDIRECT_PATH = "redirect:/admin/free";

    private final FreeService freeService;
    private final FileService fileService;
    private final ReplyService replyService;
    private final CategoryService categoryService;

    /**
     * 자유게시글 목록을 조회합니다.
     *
     * @param condition 검색조건
     * @param model boardList: 자유게시글 목록, categoryList: 카테고리 목록, pageHandler: 페이징 정보
     * @return 자유게시글 목록 뷰
     */
    @GetMapping()
    public String freeList(
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        condition.setSearchParams(BoardType.FREE);

        model.addAttribute("categoryList", categoryService.findByParentId(Category.FREE));
        model.addAttribute("boardList", freeService.findFreeList(condition));
        model.addAttribute("pageHandler",
                new PageHandler(condition, freeService.getTotalCnt(condition)));

        return Board.LIST_PATH;
    }

    /**
     * 자유게시글 등록 폼을 조회합니다.
     *
     * @param form 자유게시글 등록 폼
     * @param condition 검색조건
     * @return 자유게시글 등록 폼
     */
    @GetMapping("/register")
    public String registerForm(
            @ModelAttribute("form") BoardForm form,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        form.setBoardFormType(BoardType.FREE, FormType.REGISTER);
        form.setCategoryList(categoryService.findByParentId(Category.FREE));

        return Board.FORM_PATH;
    }

    /**
     * 자유게시글 상세페이지를 조회합니다.
     *
     * @param boardId 자유게시글 번호
     * @param condition 검색조건
     * @param model form 자유게시글 수정 폼
     * @return 자유게시글 폼 뷰
     */
    @GetMapping("/{boardId}")
    public String freeDetail(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        BoardForm freeForm = freeService.findFreeForm(boardId);
        if (freeForm == null) {
            throw new BoardNotFoundException();
        }

        freeForm.setFormType(FormType.UPDATE);
        freeForm.setCategoryList(categoryService.findByParentId(Category.FREE));
        freeForm.setFileList(fileService.findByBoardId(boardId));
        freeForm.setReplyList(replyService.findByBoardId(boardId));

        model.addAttribute("form", freeForm);

        return Board.FORM_PATH;
    }

    /**
     * 자유게시글을 등록 합니다.
     *
     * @param form 자유게시글 등록 폼
     * @param bindingResult 유효성검증객체
     * @param condition 검색조건
     * @return 유효성검증에 실패 한 경우 폼으로, 등록에 성공 한 경우 목록으로 리다이렉트
     * @throws IOException 첨부파일을 저장하는데 발생하는 예외
     */
    @PostMapping("register")
    public String register(
            @Validated @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult,
            @ModelAttribute("condition") BoardSearchCondition condition) throws IOException {

        if (bindingResult.hasErrors()) {
            form.setCategoryList(categoryService.findByParentId(Category.FREE));

            return Board.FORM_PATH;
        }

        // 현재 인증된 사용자정보로 유저정보를 설정합니다.
        SecurityUtil.setFormUser(form);

        freeService.register(form);

        return FREE_REDIRECT_PATH + condition.getEncodedQueryParam();
    }

    /**
     * 자유게시글을 업데이트 합니다.
     *
     * @param boardId 자유게시글 번호
     * @param condition 검색조건
     * @param form 자유게시글 수정 폼
     * @param bindingResult 유효성검증객체
     * @return 유효성검증에 실패 한 경우 폼으로, 수정에 성공 한 경우 수정된 게시글로 리다이렉트
     */
    @PostMapping("/{boardId}")
    public String update(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            @Validated @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            form.setCategoryList(categoryService.findByParentId(Category.FREE));

            return Board.FORM_PATH;
        }

        // 현재 인증된 사용자정보로 유저정보를 설정합니다.
        SecurityUtil.setFormUser(form);
        form.setBoardId(boardId);

        freeService.update(form);

        return FREE_REDIRECT_PATH + "/" +  boardId + condition.getEncodedQueryParam();
    }

    /**
     * 자유게시글을 삭제합니다.
     *
     * @param boardId 자유게시글 번호
     * @param condition 검색조건
     * @return 자유게시글 목록 으로 리다이렉트
     */
    @PostMapping("/{boardId}/delete")
    public String delete(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        freeService.delete(boardId);

        return FREE_REDIRECT_PATH + condition.getEncodedQueryParam();
    }
}
