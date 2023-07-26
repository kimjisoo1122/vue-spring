package com.study.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 홈 컨트롤러
 */
@Controller
@RequestMapping("/admin")
public class HomeController {

    @GetMapping
    public String home() {
        return "common/home";
    }
}
