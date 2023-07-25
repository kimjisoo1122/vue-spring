package com.study.controller.admin;

import com.study.dto.BoardRegisterForm;
import com.study.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final CategoryRepository categoryRepository;

    @GetMapping()
    public String noticeList(
            Model model) {

        model.addAttribute("headTitle", "공지사항 관리");
        model.addAttribute("title", "공지사항 관리");

        return "board/boardList";
    }

    @GetMapping("/register")
    public String registerForm(
            @RequestParam("categoryId") Long categoryId,
            Model model) {

        model.addAttribute("headTitle", "공지사항 관리");
        model.addAttribute("title", "공지사항 관리");
        model.addAttribute("boardType", "notice");

        model.addAttribute("categoryList", categoryRepository.selectByParentId(categoryId));

        return "board/boardForm";
    }

    @PostMapping("register")
    public String register(
            @Validated @ModelAttribute("form") BoardRegisterForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "board/boardForm";
        }

        System.out.println("form = " + form);

        return "redirect:/admin/notice";
    }
}
