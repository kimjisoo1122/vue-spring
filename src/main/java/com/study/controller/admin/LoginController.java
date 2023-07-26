package com.study.controller.admin;

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

    @GetMapping()
    public String login(
            Authentication authentication,
            HttpSession session,
            Model model) {

        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/admin";
        }

        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            session.removeAttribute("userId");
            model.addAttribute("userId", userId);
            model.addAttribute("errorMsg", "아이디 또는 비밀번호가 맞지 않습니다.");
        }

        return "common/login";
    }
}
