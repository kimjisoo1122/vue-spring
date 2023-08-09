package com.study.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 로그인 컨트롤러
 */
@Controller
@RequestMapping("/admin/login")
public class LoginController {

    /**
     * 스프링 시큐리티에서 로그인에 실패한 경우 다시 폼으로 이동합니다.
     *
     * @param authentication 인증객체
     * @param session 로그인 실패시 세션에 입력했던 아이디가 담깁니다.
     * @param model adminId: 입력한 아이디, errorMsg: 에러메시지
     * @return 로그인 폼 뷰
     */
    @GetMapping()
    public String loginFail(
            Authentication authentication,
            HttpSession session,
            Model model) {

        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/admin";
        }

        String adminId = (String) session.getAttribute("adminId");
        if (adminId != null) {
            session.removeAttribute("adminId");
            model.addAttribute("adminId", adminId);
            model.addAttribute("errorMsg", "아이디 또는 비밀번호가 맞지 않습니다.");
        }

        return "admin/common/login";
    }
}
