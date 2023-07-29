package com.study.controller.admin.board;

import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.enums.BoardType;
import com.study.enums.Category;
import com.study.enums.FormType;
import com.study.page.PageHandler;
import com.study.repository.CategoryRepository;
import com.study.repository.FileRepository;
import com.study.repository.board.BoardRepository;
import com.study.service.board.GalleryService;
import com.study.util.BoardUtil;
import com.study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 갤러리 게시판 컨트롤러
 */
@Controller
@RequestMapping("/admin/gallery")
@RequiredArgsConstructor
public class GalleryController {

    /**
     * 자유게시판 목록 리다이렉트 경로
     */
    private final String GALLERY_REDIRECT_PATH = "redirect:/admin/gallery";

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    private final GalleryService galleryService;

    /**
     * 갤러리게시판 목록을 조회합니다.
     * @param page 현재페이지 default: 1
     * @param limit 페이지사이즈 default: 10
     * @param condition 검색조건
     * @param model boardList: 갤러리게시판 목록, categoryList: 카테고리목록, pageHandler: 페이징객체
     * @return board/boardList 게시글목록
     */
    @GetMapping()
    public String galleryList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        condition.setBoardTypeAndPagination(BoardType.GALLERY, page, limit);

        model.addAttribute("categoryList", categoryRepository.selectByParentId(Category.GALLERY));
        model.addAttribute("boardList", galleryService.findGalleryList(condition));
        model.addAttribute("pageHandler",
                new PageHandler(page, boardRepository.countByCondition(condition), limit));

        return BoardUtil.LIST_PATH;
    }

    /**
     * 갤러리게시글 상세페이지를 조회합니다.
     * @param boardId 갤러리게시글 번호
     * @param condition 검색조건
     * @param model form 검색한 갤러리게시글
     * @return board/boardForm 게시글폼
     */
    @GetMapping("/{boardId}")
    public String galleryDetail(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            Model model) {

        boardRepository.increaseViewCnt(boardId);

        BoardForm galleryForm = boardRepository.selectForm(boardId);
        galleryForm.setBoardFormType(BoardType.GALLERY, FormType.UPDATE);
        galleryForm.setCategoryList(categoryRepository.selectByParentId(Category.GALLERY));
        galleryForm.setFileList(fileRepository.selectGalleryFile(boardId));

        model.addAttribute("form", galleryForm);

        return BoardUtil.FORM_PATH;
    }

    /**
     * 갤러리게시글 등록폼을 조회합니다.
     * @param form 갤러리게시판 등록폼
     * @param condition 검색조건
     * @return board/boardForm 게시글폼
     */
    @GetMapping("/register")
    public String registerForm(
            @ModelAttribute("form") BoardForm form,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        form.setBoardFormType(BoardType.GALLERY, FormType.REGISTER);
        form.setCategoryList(categoryRepository.selectByParentId(Category.GALLERY));

        return BoardUtil.FORM_PATH;
    }

    /**
     * 갤러리게시글을 등록합니다
     * @param form 갤러리게시글 등록폼
     * @param bindingResult 폼 유효성검증객체
     * @param condition 검색조건
     * @return 갤러리게시판 목록 리다이렉트
     * @throws IOException 첨부파일을 저장하는데 발생하는 예외
     */
    @PostMapping("register")
    public String register(
            @Validated @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult,
            @ModelAttribute("condition") BoardSearchCondition condition) throws IOException {

        if (bindingResult.hasErrors()) {
            form.setCategoryList(categoryRepository.selectByParentId(Category.GALLERY));

            return BoardUtil.FORM_PATH;
        }

        // 현재 인증된 사용자정보로 유저정보를 설정합니다.
        SecurityUtil.setFormUser(form);

        galleryService.register(form);

        return GALLERY_REDIRECT_PATH + condition.getEncodedQueryParam();
    }

    /**
     * 갤러리게시글을 업데이트 합니다.
     * @param boardId 갤러리게시글 번호
     * @param condition 검색조건
     * @param form 갤러리게시글 수정폼
     * @param bindingResult 유효성검증 객체
     * @return 수정된 갤러리게시글 리다이렉트
     */
    @PostMapping("/{boardId}")
    public String update(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition,
            @Validated @ModelAttribute("form") BoardForm form,
            BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            form.setCategoryList(categoryRepository.selectByParentId(Category.GALLERY));

            return BoardUtil.FORM_PATH;
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
     * @return 갤러리게시글 목록으로 리다이렉트
     */
    @PostMapping("/{boardId}/delete")
    public String delete(
            @PathVariable("boardId") Long boardId,
            @ModelAttribute("condition") BoardSearchCondition condition) {

        galleryService.delete(boardId);

        return GALLERY_REDIRECT_PATH + condition.getEncodedQueryParam();
    }
}
