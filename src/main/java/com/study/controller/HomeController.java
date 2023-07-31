package com.study.controller;

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

    /**
     * 관리자페이지 메인 홈 화면을 조회합니다.
     * @param model noticeList: 공지사항 목록, freeList: 자유게시글 목록,
     *             qnaList: 문의게시글 목록, galleryList: 갤러리게시글 목록
     * @return 홈 화면 뷰
     */
    @GetMapping
    public String home(
            Model model) {
        BoardSearchCondition condition = new BoardSearchCondition();
        condition.setPage(1);
        condition.setLimit(5);
        condition.setSearchParams(BoardType.NOTICE);

        model.addAttribute("noticeList", noticeService.findNoticeList(condition));

        condition.setBoardType(BoardType.FREE);
        model.addAttribute("freeList", freeService.findFreeList(condition));

        condition.setBoardType(BoardType.QNA);
        model.addAttribute("qnaList", qnaService.findQnaList(condition));

        condition.setLimit(3);
        condition.setSearchParams(BoardType.GALLERY);
        model.addAttribute("galleryList",  galleryService.findGalleryList(condition));

        return "admin/common/home";
    }
}
