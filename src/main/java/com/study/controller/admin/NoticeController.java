package com.study.controller.admin;

import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.enums.BoardType;
import com.study.enums.Category;
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
    private final NoticeRepository noticeRepository;

    private final NoticeService noticeService;

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
                categoryRepository.selectByParentId(Category.NOTICE.getValue()));

        return "board/boardList";
    }

    @GetMapping("/register")
    public String registerForm(
            @ModelAttribute("form") BoardForm form,
            Model model) {

        form.setBoardType(BoardType.NOTICE);

        model.addAttribute("categoryList",
                categoryRepository.selectByParentId(Category.NOTICE.getValue()));

        return "board/boardForm";
    }

    @PostMapping("register")
    public String register(
            @Validated @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            form.setBoardType(BoardType.NOTICE);

            return "board/boardForm";
        }

        form.setUserId(SecurityUtil.getUserId());
        form.setUserName(SecurityUtil.getUserName());

        noticeService.register(form);

        return "redirect:/admin/notice";
    }

    @GetMapping("/{boardId}")
    public String boardDetail(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        BoardForm noticeForm = noticeRepository.selectNoticeByBoardId(boardId);
        model.addAttribute("form", noticeForm);
        model.addAttribute("categoryList",
                categoryRepository.selectByParentId(Category.NOTICE.getValue()));

        return "board/boardForm";
    }
}
