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
import com.study.service.board.NoticeService;
import com.study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 공지사항 컨트롤러
 */
@Controller
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class NoticeController {

    /**
     * 공지사항 목록 리다이렉트 경로
     */
    private final String NOTICE_REDIRECT_PATH = "redirect:/admin/notice";;

    private final NoticeService noticeService;
    private final CategoryService categoryService;

    /**
     * 공지사항 목록을 조회합니다.
     * @param condition 검색조건
     * @param model boardList: 공지사항 목록, categoryList: 카테고리 목록, pageHandler: 페이징 정보
     * @return 공지사항 목록 뷰
     */
    @GetMapping()
    public String noticeList(
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        condition.setSearchParams(BoardType.NOTICE);

        model.addAttribute("boardList", noticeService.findNoticeList(condition));
        model.addAttribute("categoryList", categoryService.findByParentId(Category.NOTICE));
        model.addAttribute("pageHandler",
                new PageHandler(condition, noticeService.getTotalCnt(condition)));

        return Board.LIST_PATH;
    }

    /**
     * 공지사항 등록 폼을 조회합니다.
     * @param form 공지사항 등록폼
     * @param condition 검색조건
     * @return 공지사항 등록 폼 뷰
     */
    @GetMapping("/register")
    public String registerForm(
            @ModelAttribute("form") BoardForm form,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        form.setBoardFormType(BoardType.NOTICE, FormType.REGISTER);
        form.setCategoryList(categoryService.findByParentId(Category.NOTICE));

        return Board.FORM_PATH;
    }

    /**
     * 공지사항을 등록합니다.
     *
     * @param form 공지사항 등록 폼
     * @param bindingResult 유효성검증객체
     * @param condition 검색조건
     * @return 유효성검증에 실패 한 경우 폼으로, 등록에 성공 한 경우 목록으로 리다이렉트
     */
    @PostMapping("register")
    public String register(
            @Validated @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        if (bindingResult.hasErrors()) {
            form.setCategoryList(categoryService.findByParentId(Category.NOTICE));

            return Board.FORM_PATH;
        }

        // 현재 인증된 사용자정보로 유저정보를 설정합니다.
        SecurityUtil.setFormUser(form);

        noticeService.register(form);

        return NOTICE_REDIRECT_PATH + condition.getEncodedQueryParam();
    }

    /**
     * 공지사항 상세페이지를 조회합니다.
     *
     * @param boardId 공지사항 번호
     * @param condition 검색조건
     * @param model form 공지사항 수정 폼, categoryList: 카테고리 목록
     * @return 공지사항 수정 폼 뷰
     */
    @GetMapping("/{boardId}")
    public String noticeDetail(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        BoardForm noticeForm = noticeService.findNoticeForm(boardId);
        if (noticeForm == null) {
            throw new BoardNotFoundException();
        }

        noticeForm.setBoardFormType(BoardType.NOTICE, FormType.UPDATE);
        noticeForm.setCategoryList(categoryService.findByParentId(Category.NOTICE));

        model.addAttribute("form", noticeForm);

        return Board.FORM_PATH;
    }

    /**
     * 공지사항을 업데이트 합니다.
     *
     * @param boardId 공지사항 번호
     * @param condition 검색조건
     * @param form 공지사항 수정 폼
     * @param bindingResult 유효성검증객체
     * @return 유효성검증에 실패 한 경우 폼으로, 수정에 성공 한 경우 수정된 게시글로 리다이렉트
     */
    @PostMapping("/{boardId}")
    public String update(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            @Validated @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            form.setCategoryList(categoryService.findByParentId(Category.NOTICE));

            return Board.FORM_PATH;
        }

        SecurityUtil.setFormUser(form);
        form.setBoardId(boardId);

        noticeService.update(form);

        return NOTICE_REDIRECT_PATH + "/" +  boardId + condition.getEncodedQueryParam();
    }

    /**
     * 공지사항을 삭제합니다.
     * @param boardId 공지사항 번호
     * @param condition 검색조건
     * @return 공지사항 목록 리다이렉트
     */
    @PostMapping("/{boardId}/delete")
    public String delete(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        noticeService.delete(boardId);

        return NOTICE_REDIRECT_PATH + condition.getEncodedQueryParam();
    }
}
