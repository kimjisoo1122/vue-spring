package com.study.controller.admin;

import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.enums.BoardType;
import com.study.enums.Category;
import com.study.enums.FormType;
import com.study.repository.BoardRepository;
import com.study.repository.CategoryRepository;
import com.study.repository.NoticeRepository;
import com.study.service.NoticeService;
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

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;
    private final NoticeRepository noticeRepository;

    private final NoticeService noticeService;

    /**
     * 공지사항 목록을 조회합니다.
     * @param page 현재페이지 default: 1
     * @param limit 페이지사이즈 default: 10
     * @param condition 검색조건
     * @param model boardList: 공지사항목록, boardType: 게시글타입, categoryList: 카테고리목록
     * @return board/boardList 게시글목록
     */
    @GetMapping()
    public String noticeList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        condition.setBoardType(BoardType.NOTICE);
        condition.setLimitOffset(page, limit);

        model.addAttribute("boardList", noticeService.findNoticeList(condition));
        model.addAttribute("boardType", BoardType.NOTICE);
        model.addAttribute("categoryList",
                categoryRepository.selectByParentId(Category.NOTICE));

        return "board/boardList";
    }

    /**
     * 공지사항 등록폼을 조회합니다.
     * @param form 공지사항등록폼
     * @param condition 검색조건
     * @param model categoryList: 카테고리목록
     * @return board/boardForm 게시글폼
     */
    @GetMapping("/register")
    public String registerForm(
            @ModelAttribute("form") BoardForm form,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        form.setBoardType(BoardType.NOTICE);
        form.setFormType(FormType.REGISTER);

        model.addAttribute("categoryList",
                categoryRepository.selectByParentId(Category.NOTICE));

        return "board/boardForm";
    }

    /**
     * 공지사항을 등록합니다
     * @param form 공지사항등록폼
     * @param bindingResult 폼 유효성검증객체
     * @param condition 검색조건
     * @return redirect:/admin/notice 공지사항목록 리다이렉트
     */
    @PostMapping("register")
    public String register(
            @Validated @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        form.setBoardType(BoardType.NOTICE);

        if (bindingResult.hasErrors()) {
            return "board/boardForm";
        }

        // 현재 인증된 사용자정보로 유저정보를 설정합니다.
        SecurityUtil.setFormUser(form);

        noticeService.register(form);

        return "redirect:/admin/notice" + condition.getEncodedQueryParam();
    }

    /**
     * 공지사항 상세페이지를 조회합니다.
     * @param boardId 공지사항번호
     * @param condition 검색조건
     * @param model form 검색한 공지사항, categoryList: 카테고리목록
     * @return board/boardForm 게시글폼
     */
    @GetMapping("/{boardId}")
    public String noticeDetail(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        boardRepository.increaseViewCnt(boardId);

        BoardForm noticeForm = noticeRepository.selectNoticeFormByBoardId(boardId);
        noticeForm.setFormType(FormType.UPDATE);

        model.addAttribute("form", noticeForm);
        model.addAttribute("categoryList",
                categoryRepository.selectByParentId(Category.NOTICE));

        return "board/boardForm";
    }

    /**
     * 공지사항을 업데이트 합니다.
     * @param boardId 공지사항 번호
     * @param condition 검색조건
     * @param form 공지사항 수정폼
     * @param bindingResult 공지사항 유효성검증 객체
     * @return 수정된 공지사항으로 리다이렉트
     */
    @PostMapping("/{boardId}")
    public String update(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            @Validated @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "board/boardForm";
        }

        SecurityUtil.setFormUser(form);
        form.setBoardId(boardId);

        noticeService.update(form);

        return "redirect:/admin/notice/" + boardId + condition.getEncodedQueryParam();
    }

    /**
     * 공지사항을 삭제합니다.
     * @param boardId 공지사항 번호
     * @param condition 검색조건
     * @return redirect:/admin/notice 공지사항목록으로 리다이렉트
     */
    @PostMapping("/{boardId}/delete")
    public String delete(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        noticeService.delete(boardId);

        return "redirect:/admin/notice" + condition.getEncodedQueryParam();
    }
}
