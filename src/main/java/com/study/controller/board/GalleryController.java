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
import com.study.service.board.GalleryService;
import com.study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 갤러리게시판 컨트롤러
 */
@Controller
@RequestMapping("/admin/gallery")
@RequiredArgsConstructor
public class GalleryController {

    /**
     * 갤러리게시판 목록 리다이렉트 경로
     */
    private final String GALLERY_REDIRECT_PATH = "redirect:/admin/gallery";

    private final GalleryService galleryService;
    private final CategoryService categoryService;
    private final FileService fileService;

    /**
     * 갤러리게시글 목록을 조회 합니다.
     *
     * @param condition 검색조건
     * @param model boardList: 갤러리게시글 목록, categoryList: 카테고리 목록, pageHandler: 페이징 정보
     * @return 갤러리게시글 목록 뷰
     */
    @GetMapping()
    public String galleryList(
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        condition.setSearchParams(BoardType.GALLERY);

        model.addAttribute("categoryList", categoryService.findByParentId(Category.GALLERY));
        model.addAttribute("boardList", galleryService.findGalleryList(condition));
        model.addAttribute("pageHandler",
                new PageHandler(condition,  galleryService.getTotalCnt(condition)));

        return Board.LIST_PATH;
    }

    /**
     * 갤러리게시글 상세페이지를 조회합니다.
     *
     * @param boardId 갤러리게시글 번호
     * @param condition 검색조건
     * @param model form 갤러리게시글 수정 폼
     * @return 갤러리게시글 수정 폼
     */
    @GetMapping("/{boardId}")
    public String galleryDetail(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        BoardForm galleryForm = galleryService.findGalleryForm(boardId);
        if (galleryForm == null) {
            throw new BoardNotFoundException();
        }

        galleryForm.setFormType(FormType.UPDATE);
        galleryForm.setFileList(galleryService.findGalleryFileList(boardId));
        galleryForm.setCategoryList(categoryService.findByParentId(Category.GALLERY));

        model.addAttribute("form", galleryForm);

        return Board.FORM_PATH;
    }

    /**
     * 갤러리게시글 등록 폼을 조회합니다.
     *
     * @param form 갤러리게시판 등록 폼
     * @param condition 검색조건
     * @return 갤러리게시글 등록 폼 뷰
     */
    @GetMapping("/register")
    public String registerForm(
            @ModelAttribute("form") BoardForm form,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        form.setBoardFormType(BoardType.GALLERY, FormType.REGISTER);
        form.setCategoryList(categoryService.findByParentId(Category.GALLERY));

        return Board.FORM_PATH;
    }

    /**
     * 갤러리게시글을 등록합니다
     *
     * @param form 갤러리게시글 등록 폼
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
            form.setCategoryList(categoryService.findByParentId(Category.GALLERY));

            return Board.FORM_PATH;
        }

        // 현재 인증된 사용자정보로 유저정보를 설정합니다.
        SecurityUtil.setFormUser(form);

        galleryService.register(form);

        return GALLERY_REDIRECT_PATH + condition.getEncodedQueryParam();
    }

    /**
     * 갤러리게시글을 업데이트 합니다.
     *
     * @param boardId 갤러리게시글 번호
     * @param condition 검색조건
     * @param form 갤러리게시글 수정 폼
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
            form.setCategoryList(categoryService.findByParentId(Category.GALLERY));

            return Board.FORM_PATH;
        }

        // 현재 인증된 사용자정보로 유저정보를 설정합니다.
        SecurityUtil.setFormUser(form);
        form.setBoardId(boardId);

        galleryService.update(form);

        return GALLERY_REDIRECT_PATH + "/" +  boardId + condition.getEncodedQueryParam();
    }

    /**
     * 갤러리게시글을 삭제합니다.
     * @param boardId 갤러리게시글 번호
     * @param condition 검색조건
     * @return 갤러리게시글 목록 으로 리다이렉트
     */
    @PostMapping("/{boardId}/delete")
    public String delete(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        galleryService.delete(boardId);

        return GALLERY_REDIRECT_PATH + condition.getEncodedQueryParam();
    }
}
