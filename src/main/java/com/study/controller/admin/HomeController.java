package com.study.controller.admin;

import com.study.dto.BoardSearchCondition;
import com.study.enums.BoardType;
import com.study.service.board.FreeService;
import com.study.service.board.GalleryService;
import com.study.service.board.NoticeService;
import com.study.service.board.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 홈 컨트롤러
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class HomeController {

    private final NoticeService noticeService;
    private final FreeService freeService;
    private final QnaService qnaService;
    private final GalleryService galleryService;

    @GetMapping
    public String home(
            Model model) {
        BoardSearchCondition condition = new BoardSearchCondition();
        condition.setSearchCondition(BoardType.NOTICE, 1, 5);
        model.addAttribute("noticeList", noticeService.findNoticeList(condition));

        condition.setSearchCondition(BoardType.FREE, 1, 5);
        model.addAttribute("freeList", freeService.findFreeList(condition));

        condition.setSearchCondition(BoardType.QNA, 1, 5);
        model.addAttribute("qnaList", qnaService.findQnaList(condition));

        condition.setSearchCondition(BoardType.GALLERY, 1, 3);
        model.addAttribute("galleryList",  galleryService.findGalleryList(condition));

        return "admin/common/home";
    }
}
